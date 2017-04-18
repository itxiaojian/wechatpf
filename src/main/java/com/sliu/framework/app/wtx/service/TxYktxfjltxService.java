package com.sliu.framework.app.wtx.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wtx.dao.TxTxxxlsjlDao;
import com.sliu.framework.app.wtx.dao.TxYktxfjltxDao;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;
import com.sliu.framework.app.wtx.model.TxYktxfjltx;

@Service
public class TxYktxfjltxService extends BaseBO<TxYktxfjltx>{

	@Autowired
	private TxYktxfjltxDao dao;
	@Autowired
	private TxTxxxlsjlDao txDao;

	/**
	 * 数据的定时的归集
	 * @author duanpeijun
	 * @date 2015年8月6日
	 */
	@Transactional
	public void yktXfjltxSave(){
		//List<Map<String,Object>> list=this.getTime();
		//if(list.size()!=0){
			//String sjgjsj=list.get(0).get("sjgjsj")+"";
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			//calendar.add(Calendar.DATE, 1);//增加一天   
	        //calendar.add(Calendar.MONTH, 1);//增加一个月 
			Date date = calendar.getTime();
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time=new Date();
//			String txsj=list.get(0).get("txsj")+"";
			String txsj=sim.format(date)+" 08:00:00";
			try {
				time=s.parse(txsj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			List<Map<String,Object>> yktxfjl=dao.getGjsj(sim.format(date));
			for(int i=0;i<yktxfjl.size();i++){
				String txnr=yktxfjl.get(i).get("xm")+"你好，你的卡号："+yktxfjl.get(i).get("kh")+"于"+yktxfjl.get(i).get("xfsj")+"在"
							+yktxfjl.get(i).get("xfdd")+"消费了"+yktxfjl.get(i).get("xfje")+"元，交易后余额为："+yktxfjl.get(i).get("ye")+
							"元。感谢你的使用。";
				TxYktxfjltx yktxfjltx =new TxYktxfjltx();
				yktxfjltx.setTxdx(yktxfjl.get(i).get("xm")+"");
				yktxfjltx.setOpenid(yktxfjl.get(i).get("wxh")+"");
				yktxfjltx.setTxnr(txnr);
				yktxfjltx.setTxsj(time);
				dao.save(yktxfjltx);
				TxTxxxlsjl lsjl=new TxTxxxlsjl();
				//存入类型
				lsjl.setTxlx("1");
				lsjl.setOpenid(yktxfjl.get(i).get("wxh")+"");
				lsjl.setTxdx(yktxfjl.get(i).get("xm")+"");
				lsjl.setTxnr(txnr);
				lsjl.setTxsj(time);
				txDao.save(lsjl);
			}
		//}
	}
	
	/**
	 * 消息的定时发送
	 * @author liujiansen
	 * @date 2015年7月30日
	 */
	public void toSend(){
		List<Map<String,Object>> list=dao.getTxxx();
		for(int i=0;i<list.size();i++){
			//消息循环发送
			WeixinUtils.massPreview("text", (list.get(i).get("openid")+""), (list.get(i).get("txnr")+""));
		}
		//发送完成后清除表数据
		dao.delete();
	}
	
	/**
	 *  前台：一卡通消费记录提醒数量
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:37
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getYktxfjltxsl(String openId){
		return dao.getYktxfjltxsl(openId);
	}
	
	/**
	 * 前台：一卡通消费记录提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getYktxfjltxList(String openId){
		return dao.getYktxfjltxList(openId);
	}
}
