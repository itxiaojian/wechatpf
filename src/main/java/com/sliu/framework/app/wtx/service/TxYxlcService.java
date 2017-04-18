package com.sliu.framework.app.wtx.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wtx.dao.TxYxlcDao;
import com.sliu.framework.app.wtx.model.TxYxlcTxjl;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

@Service
public class TxYxlcService extends BaseBO<TxYxlcTxjl> {
	
	@Autowired
	private TxYxlcDao dao;
	
	
	/**
	 * 定时发送离校提醒
	 * @author:wangxiangyang
	 * @version 创建时间：2016年8月11日
	 */
	@Transactional
	public void sendWagesNews(){
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);
		int lastyear = year-1;
		String blzt1="";
		List<Map<String,Object>> listWage  =new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list = dao.getYxlcjl(year);
		if(list.size()==0){
			listWage= dao.getGrgzForSendNews(year);
		}else{
			listWage=dao.getGrgzForSendNews1(year);
		}
		String url = "";
		List<Map<String,Object>> listlj = dao.getDict("yxlj");
		if(listlj.size()>0){
			url = listlj.get(0).get("zdz").toString();
		}
		if(listWage.size()>0){
			for(Map<String,Object> map:listWage){
				String toUser = map.get("wxh").toString();
				WxTemplate temp = new WxTemplate();
				temp.setTemplate_id("ZOXpGnm34XmpeVtMxuWbtyvhVSXLSsH_OvHSTyC3624");
				temp.setTopcolor("#000000");
				temp.setTouser(toUser);
	 			temp.setUrl(url+toUser);
				Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
				//提醒
				TemplateDataView dataView1 = new TemplateDataView();
				dataView1.setColor("#FF6600");
				dataView1.setValue("您好，您有新的流程信息： "+map.get("tachename").toString()+"变动，请注意查看。");
				mapdata.put("first", dataView1);
				//姓名
				TemplateDataView dataView2 = new TemplateDataView();
				dataView2.setColor("#000000");
				dataView2.setValue(" "+map.get("stuname").toString());
				mapdata.put("keyword1", dataView2);
				//审核结果
				TemplateDataView dataView3 = new TemplateDataView();
				dataView3.setColor("#000000");
				if("1".equals(map.get("blzt").toString())){
					blzt1 = "通过";
				}else if("0".equals(map.get("blzt").toString())){
					blzt1 = "未通过";
				}
				dataView3.setValue(" "+blzt1);
				mapdata.put("keyword2", dataView3);
				//备注
				TemplateDataView dataView4 = new TemplateDataView();
				dataView4.setColor("#99CC00");
				dataView4.setValue("  ★  查看详细请点击，祝工作愉快！  ★");
				mapdata.put("remark", dataView4);
				temp.setData(mapdata);
				WeixinUtils.sendTemplateMessage(toUser,temp);
			} 
		}
		 if(listWage.size()>0){
				for(Map<String,Object> map:listWage){
					TxYxlcTxjl txjl =new TxYxlcTxjl();
					String toUser = map.get("wxh").toString();
					txjl.setTacheid(map.get("tacheid").toString());
					txjl.setStuid(map.get("stuid").toString());
					txjl.setBlzt(map.get("blzt").toString());
					txjl.setWxh(toUser);
					dao.save(txjl);
				}
		    }
		 dao.deleteYxlcjl(lastyear);
	   }
}
