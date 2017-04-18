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
import com.sliu.framework.app.wtx.dao.TxLssktxDao;
import com.sliu.framework.app.wtx.dao.TxTxxxlsjlDao;
import com.sliu.framework.app.wtx.model.TxLssktx;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;

@Service
public class TxLssktxService extends BaseBO<TxLssktx> {

	@Autowired
	private TxLssktxDao dao;
	@Autowired
	private TxTxxxlsjlDao txDao;

	/**
	 * 数据的定时的归集
	 * @author duanpeijun
	 * @date 2015年8月6日
	 */
	@Transactional
	public void lsSktxSave(){
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
			List<Map<String,Object>> lssk=dao.getGjsj(sim.format(date));
			for(int i=0;i<lssk.size();i++){
				String txnr=lssk.get(i).get("lsxm")+"老师你好，"+lssk.get(i).get("skrq")+lssk.get(i).get("xq")+"；你的课表："
						+lssk.get(i).get("dtskxh")+lssk.get(i).get("kcmc")+lssk.get(i).get("skdd");
				TxLssktx lstx=new TxLssktx();
				lstx.setTxdx(lssk.get(i).get("lsxm")+"");
				lstx.setOpenid(lssk.get(i).get("wxh")+"");
				lstx.setTxnr(txnr);
				lstx.setTxsj(time);
				dao.save(lstx);
				TxTxxxlsjl lsjl=new TxTxxxlsjl();
				//存入类型
				lsjl.setTxlx("5");
				lsjl.setOpenid(lssk.get(i).get("wxh")+"");
				lsjl.setTxdx(lssk.get(i).get("lsxm")+"");
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
	 *  前台：老师上课提醒数量
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:37
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getLssktxsl(String openId){
		return dao.getLssktxsl(openId);
	}
	
	/**
	 * 前台：学生上课提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getLssktxList(String openId){
		return dao.getLssktxList(openId);
	}
}
