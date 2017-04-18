package com.sliu.framework.app.wtx.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wtx.dao.TxZggzDao;
import com.sliu.framework.app.wtx.model.TxZggzTxjl;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

@Service
public class TxZggzService extends BaseBO<TxZggzTxjl> {
	
	@Autowired
	private TxZggzDao dao;
	
	
	/**
	 * 定时发送工资提醒消息
	 * @author:oufeng
	 * @version 创建时间：2016年8月8日
	 */
	@Transactional
	public void sendWagesNews(){
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);
		int month = a.get(Calendar.MONTH)+1;
		int _month = month-1;
		int _year=0;
		if(_month==0){_year=year-1;_month=12;}{_year=year;}
		List<Map<String,Object>> listWage  =new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list = dao.getGrgzjl(year,month);
		if(list.size()==0){
			listWage= dao.getGrgzForSendNews(year,month);
		}else{
			listWage=dao.getGrgzForSendNews1(year,month);
		}
		String url = "";
		List<Map<String,Object>> listlj = dao.getDict("xtlj");
		if(listlj.size()>0){
			url = listlj.get(0).get("zdz").toString();
		}
		if(listWage.size()>0){
			for(Map<String,Object> map:listWage){
				String toUser = map.get("wxh").toString();
				WxTemplate temp = new WxTemplate();
				temp.setTemplate_id("cQzhCMc43i-P4hWWWi6eVDlULr74f8au7Xg_I_vvf7g");
				temp.setTopcolor("#000000");
				temp.setTouser(toUser);
	 			temp.setUrl(url+toUser);
				Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
				//提醒
				TemplateDataView dataView1 = new TemplateDataView();
				dataView1.setColor("#FF6600");
				dataView1.setValue("您好，您这个月的总工资为 "+map.get("yfhj").toString()+"元，请注意查收。");
				mapdata.put("first", dataView1);
				//名称
				TemplateDataView dataView2 = new TemplateDataView();
				dataView2.setColor("#000000");
				dataView2.setValue(" "+map.get("nf").toString()+" 年 "+map.get("yf").toString()+" 月工资");
				mapdata.put("keyword1", dataView2);
				//应发金额
				TemplateDataView dataView3 = new TemplateDataView();
				dataView3.setColor("#000000");
				dataView3.setValue(" "+map.get("yfhj").toString()+" 元");
				mapdata.put("keyword2", dataView3);
				//实发金额
				TemplateDataView dataView4 = new TemplateDataView();
				dataView4.setColor("#000000");
				dataView4.setValue(" "+map.get("sfhj").toString()+"元,扣款合计为 "+map.get("kkhj").toString()+"元");
				mapdata.put("keyword3", dataView4);
				//时间
				TemplateDataView dataView5 = new TemplateDataView();
				dataView5.setColor("#000000");
				dataView5.setValue(" "+new Date()+"");
				mapdata.put("keyword4", dataView5);
				//备注
				TemplateDataView dataView6 = new TemplateDataView();
				dataView6.setColor("#99CC00");
				dataView6.setValue("  ★  查看详细请点击，祝工作愉快！  ★");
				mapdata.put("remark", dataView6);
				temp.setData(mapdata);
				WeixinUtils.sendTemplateMessage(toUser,temp);
			} 
		}
		 if(listWage.size()>0){
				for(Map<String,Object> map:listWage){
					TxZggzTxjl txjl =new TxZggzTxjl();
					String toUser = map.get("wxh").toString();
					txjl.setNf(map.get("nf").toString());
					txjl.setXm(map.get("xm").toString());
					txjl.setYf(map.get("yf").toString());
					txjl.setGh(map.get("gh").toString());
					txjl.setWxh(toUser);
					txjl.setZt("1");
					dao.save(txjl);
				}
		    }
		 dao.deleteGrgzjl(_year,_month);
	   }
}
