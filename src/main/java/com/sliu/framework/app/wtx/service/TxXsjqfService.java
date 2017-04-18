package com.sliu.framework.app.wtx.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.model.ZsXsjqfxx;
import com.sliu.framework.app.wtx.dao.TxXsjqfDao;
import com.sliu.framework.app.wtx.model.TxTshstx;
import com.sliu.framework.app.wtx.model.TxXsjqftx;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

@Service
public class TxXsjqfService extends BaseBO<TxXsjqftx> {
	
	@Autowired
	private TxXsjqfDao dao;
	
	
	/**
	 * 获取提醒的数据
	 * @author oufeng
	 * @date 2016年8月10日
	 * @param openId
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public void sendJqftx() throws ParseException{
		List<Map<String,Object>> list =new  ArrayList<Map<String,Object>>();
		String xn = AppUtil.fromNowGetXn(new Date()).get("xn").toString();
		List<Map<String,Object>> listSize=dao.getJqfjlSize(xn);
		if(listSize.size()==0){
			list=dao.getJqftx(xn);
		}else{
			list=dao.getJqftx1(xn);
		}
		String url = "";
		List<Map<String,Object>> listlj = dao.getDict("xtlj");
		if(listlj.size()>0){
			url = listlj.get(0).get("zdz").toString();
		}
		if(list.size()>0){
			for(Map<String,Object> map:list){
				String toUser = map.get("wxh").toString();
				WxTemplate temp = new WxTemplate();
				temp.setTemplate_id("1pehuL-_ifNy2FWBa8gUEnmEePnF_arvmsNmD11V3Po");
				temp.setTopcolor("#000000");
				temp.setTouser(toUser);
	 			temp.setUrl(url+toUser);
				Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
				//提醒
				TemplateDataView dataView1 = new TemplateDataView();
				dataView1.setColor("#FF6600");
				dataView1.setValue(" 第"+xn+"学年,"+map.get("xm").toString()+"同学的缴费通知 ");
				mapdata.put("first", dataView1);
				//学生姓名
				TemplateDataView dataView2 = new TemplateDataView();
				dataView2.setColor("#000000");
				dataView2.setValue(" "+map.get("xm").toString()+" ");
				mapdata.put("keyword1", dataView2);
				//缴费类型
				TemplateDataView dataView3 = new TemplateDataView();
				dataView3.setColor("#000000");
				dataView3.setValue(" "+map.get("jfxm").toString()+" ");
				mapdata.put("keyword2", dataView3);
				//缴费金额
				TemplateDataView dataView4 = new TemplateDataView();
				dataView4.setColor("#000000");
				dataView4.setValue(" "+map.get("qfje").toString()+" ");
				mapdata.put("keyword3", dataView4);
				//截止日期
				TemplateDataView dataView5 = new TemplateDataView();
				dataView5.setColor("#000000");
				dataView5.setValue(" "+"为有助于学校各项工作的顺利开展，请您尽快缴纳欠费！"+" ");
				mapdata.put("keyword4", dataView5);
				//备注
				TemplateDataView dataView6 = new TemplateDataView();
				dataView6.setColor("#99CC00");
				dataView6.setValue("  ★  您可以微信的微服务查看详情！ ★");
				mapdata.put("remark", dataView6);
				temp.setData(mapdata);
				WeixinUtils.sendTemplateMessage(toUser,temp);
			} 
		}
		if(list.size()>0){
			for(Map<String,Object> map:list){
				String toUser = map.get("wxh").toString();
				TxXsjqftx entity = new TxXsjqftx();
				entity.setWxh(toUser);
				entity.setXh(map.get("xh").toString());
				entity.setXm(map.get("xm").toString());
				entity.setXn(map.get("xn").toString());
				entity.setTxnr(map.get("jfxm").toString()+";"+map.get("Qfje").toString());
				dao.save(entity);
			}
		}
		//删除上个学期的记录
		 dao.deleteJqfjl(Integer.parseInt(xn)-1);
	}
}
