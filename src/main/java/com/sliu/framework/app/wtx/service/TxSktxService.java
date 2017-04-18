package com.sliu.framework.app.wtx.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.sliu.framework.app.wtx.dao.TxSktxDao;
import com.sliu.framework.app.wtx.dao.TxTxxxlsjlDao;
import com.sliu.framework.app.wtx.model.TxSktx;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

@Service
public class TxSktxService extends BaseBO<TxSktx>{

	@Autowired
	private TxSktxDao dao;
	@Autowired
	private TxTxxxlsjlDao txDao;

	
	@Transactional
	public void toSkSend(Long id){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date date = calendar.getTime();
		List<Map<String,Object>> skjl =new  ArrayList<Map<String,Object>>();
//		List<Map<String,Object>> listSize=dao.getSktxjlSize(sim.format(date));
//		if(listSize.size()==0){
			skjl=dao.skxx1(sim.format(date),id);
//		}else{
//			skjl=dao.skxx1(sim.format(date),id);
//		}
		
		String url = "";
		List<Map<String,Object>> listlj = dao.getDict("sktx");
		if(listlj.size()>0){
			url = listlj.get(0).get("zdz").toString()+"id="+id+""+"&openId=";
		}
		
		
			if(skjl.size()>0){
				for(Map<String,Object> map:skjl){
					String toUser = map.get("wxh").toString();
					String txnr="  "+map.get("xm")+""+",您好,"+map.get("fbrxm")+""+"在"+map.get("dd")+""+"捡到卡号为"+map.get("yktkh")+""+"的卡,"+""+"请速与之联系！";
					WxTemplate temp = new WxTemplate();
//					temp.setTemplate_id("MeBjKZmxV0rglj-YP2306iDrkd5oLFumWb1junejVh0");  //公司
//    				temp.setTemplate_id("1lpNCcFu7lZU-qsAOS7KGwbvCKQW0AItugR-PuJxdos"); //工商
					temp.setTemplate_id("2X3Xyfo4AHN84ngLHdpbFbU5gZFAJATd3hFA5zrS8Tg"); //运维
					temp.setTopcolor("#000000");
					temp.setTouser(toUser);
		 			temp.setUrl(url+toUser);
					Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
					//提醒
					TemplateDataView dataView1 = new TemplateDataView();
					dataView1.setColor("#FF6600");
					dataView1.setValue(" "+txnr+" ");
					mapdata.put("first", dataView1);
					
					//发布时间
					TemplateDataView dataView2 = new TemplateDataView();
					dataView2.setColor("#000000");
					dataView2.setValue(" "+map.get("fqsj").toString()+" ");
					mapdata.put("PickedTime", dataView2);
					
					TemplateDataView dataView3 = new TemplateDataView();
					dataView3.setColor("#000000");
					dataView3.setValue("卡号");
					mapdata.put("CardType", dataView3);
					//卡号
					TemplateDataView dataView4 = new TemplateDataView();
					dataView4.setColor("#000000");
					dataView4.setValue(" "+map.get("yktkh").toString()+" ");
					mapdata.put("CardNum", dataView4);
					//捡获者
					TemplateDataView dataView5 = new TemplateDataView();
					dataView5.setColor("#000000");
					dataView5.setValue(" "+map.get("fbrxm").toString()+" ");
					mapdata.put("PickerName", dataView5);
					//备注
					TemplateDataView dataView6 = new TemplateDataView();
					dataView6.setColor("#99CC00");
					dataView6.setValue(" 备注："+map.get("xwzs").toString()+" ");
					mapdata.put("remark", dataView6);
					//备注
//					TemplateDataView dataView7 = new TemplateDataView();
//					dataView7.setColor("#99CC00");
//					dataView7.setValue("  ★本提醒不作为入账凭证，最终交易结果和余额以一卡通系统为准（充值之后余额不变，消费一笔后余额会变为正确余额，请不必担心）。点击可以进入微服务查看消费详情！ ★");
//					mapdata.put("remark", dataView7);
					temp.setData(mapdata);
					WeixinUtils.sendTemplateMessage(toUser,temp);
				} 
			}
			if(skjl.size()>0){
				for(Map<String,Object> map:skjl){
					String txnr= map.get("xm")+"您好，"
							+map.get("fbrxm")+"在"+map.get("dd")+"捡到卡号为"
							+map.get("yktkh")+"的卡"
							+"请速与之联系！";
					//保存数据
					
					TxSktx sktx =new TxSktx();
					sktx.setTxdx(map.get("xm")+"");
					sktx.setOpenid(map.get("wxh").toString());
					sktx.setTxnr(txnr);
//					String sj1 = s.format(map.get("fqsj"));
//					try {
//						sktx.setTxsj(s.parse(sj1));
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					try {
						sktx.setTxsj(s.parse(map.get("fqsj")+""));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					sktx.setKh(map.get("yktkh")+"");
					dao.save(sktx);
			    }
			}
	}
	
	
}
