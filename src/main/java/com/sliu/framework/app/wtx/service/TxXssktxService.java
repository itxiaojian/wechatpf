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
import com.sliu.framework.app.wtx.dao.TxXssktxDao;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;
import com.sliu.framework.app.wtx.model.TxXssktx;

/**
 * 提醒管理配置
 * @author liujiansen
 * @date 2015年7月29日
 */
@Service
public class TxXssktxService extends BaseBO<TxXssktx> {

	@Autowired
	private TxXssktxDao dao;
	@Autowired
	private TxTxxxlsjlDao txDao;

	/**
	 * 数据的定时的归集
	 * @author liujiansen
	 * @date 2015年7月29日
	 */
	@Transactional
	public void xsSktxSave(){
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
			List<Map<String,Object>> xssk=dao.getGjsj(sim.format(date));
			for(int i=0;i<xssk.size();i++){
				String txnr=xssk.get(i).get("xsxm")+"同学你好，"+xssk.get(i).get("skrq")+xssk.get(i).get("xq")+"；你的课表："
						+xssk.get(i).get("dtskxh")+xssk.get(i).get("kcmc")+xssk.get(i).get("skdd")+xssk.get(i).get("lsxm");
				TxXssktx xstx=new TxXssktx();
				xstx.setTxdx(xssk.get(i).get("xsxm")+"");
				xstx.setOpenid(xssk.get(i).get("wxh")+"");
				xstx.setTxnr(txnr);
				xstx.setTxsj(time);
				dao.save(xstx);
				TxTxxxlsjl lsjl=new TxTxxxlsjl();
				//存入类型
				lsjl.setTxlx("3");
				lsjl.setOpenid(xssk.get(i).get("wxh")+"");
				lsjl.setTxdx(xssk.get(i).get("xsxm")+"");
				lsjl.setTxnr(txnr);
				lsjl.setTxsj(time);
				txDao.save(lsjl);
			}
		//}
	}
	
	/**
	 * 获取时间
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTime(){
		return dao.getTime();
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
	 *  前台：学生上课提醒数量
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:37
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getXssktxsl(String openId){
		return dao.getXssktxsl(openId);
	}
	
	/**
	 * 前台：学生上课提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getXssktxList(String openId){
		return dao.getXssktxList(openId);
	}
}
