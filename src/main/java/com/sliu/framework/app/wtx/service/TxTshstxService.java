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
import com.sliu.framework.app.wtx.dao.TxTshstxDao;
import com.sliu.framework.app.wtx.dao.TxTxxxlsjlDao;
import com.sliu.framework.app.wtx.model.TxTshstx;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

/**
 * 图书还书提醒
 * @author duanpeijun
 * @date 2015年8月6日
 */
@Service
public class TxTshstxService extends BaseBO<TxTshstx>{

	@Autowired
	private TxTshstxDao dao;
	@Autowired
	private TxTxxxlsjlDao txDao;
	
	/**
	 * 数据的定时的归集
	 * @author duanpeijun
	 * @date 2015年8月6日
	 */
/*	@Transactional
	public void tsHstxSave(){
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
			List<Map<String,Object>> tshs=dao.getGjsj(sim.format(date));
			for(int i=0;i<tshs.size();i++){
				String txnr=tshs.get(i).get("xm")+"你好，你于"+tshs.get(i).get("jcsj")+"借走编号为"+tshs.get(i).get("tsbh")+"的《"
						+tshs.get(i).get("tsmc")+"》。请你按时归还，归还日期为："+tshs.get(i).get("ghsj")+"。感谢你的配合。";
				TxTshstx tsjytx=new TxTshstx();
				tsjytx.setTxdx(tshs.get(i).get("xm")+"");
				tsjytx.setOpenid(tshs.get(i).get("wxh")+"");
				tsjytx.setTxnr(txnr);
				tsjytx.setTxsj(time);
				dao.save(tsjytx);
				TxTxxxlsjl lsjl=new TxTxxxlsjl();
				//存入类型
				lsjl.setTxlx("2");
				lsjl.setOpenid(tshs.get(i).get("wxh")+"");
				lsjl.setTxdx(tshs.get(i).get("xm")+"");
				lsjl.setTxnr(txnr);
				lsjl.setTxsj(time);
				txDao.save(lsjl);
			}
		//}
	}*/
	
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
	 *  前台：图书还书提醒数量
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:37
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTshstxsl(String openId){
		return dao.getTshstxsl(openId);
	}
	
	/**
	 * 前台：图书还书提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getTshstxList(String openId){
		return dao.getTshstxList(openId);
	}
	
	/**
	 * 查看还书提醒详情
	  @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	@Transactional
	public TxTxxxlsjl getTxTxxxlsjlById(Long id,String openId) {
		TxTxxxlsjl entity = txDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}
	
	
	/**
	 * 获取提醒的数据
	 * @author oufeng
	 * @date 2016年8月10日
	 * @param openId
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public void sendTshstx() throws ParseException{
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat s1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,Object>> list =new  ArrayList<Map<String,Object>>();
		String title="";
		String date =s.format(new Date());
		List<Map<String,Object>> listSize=dao.getTstxjlSize();
		if(listSize.size()==0){
			list=dao.getTshstx(date);
		}else{
			list=dao.getTshstx1(date);
		}
		String url = "";
		List<Map<String,Object>> listlj = dao.getDict("xtlj");
		if(listlj.size()>0){
			url = listlj.get(0).get("zdz").toString();
		}
		if(list.size()>0){
			for(Map<String,Object> map:list){
				String toUser = map.get("wxh").toString();
				String ygq = map.get("kgq").toString();
				if("2".equals(ygq)){title="您借的图书即将到期";}else if("1".equals(ygq)){title="您借的图书已到期";}
				WxTemplate temp = new WxTemplate();
				temp.setTemplate_id("KurcXJG-0WLRIcufUhUoPII0MI9qdkEdbaBWJdXC7BU");
				temp.setTopcolor("#000000");
				temp.setTouser(toUser);
	 			temp.setUrl(url+toUser);
				Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
				//提醒
				TemplateDataView dataView1 = new TemplateDataView();
				dataView1.setColor("#FF6600");
				dataView1.setValue(" "+title+" ");
				mapdata.put("first", dataView1);
				//应归还书名
				TemplateDataView dataView2 = new TemplateDataView();
				dataView2.setColor("#000000");
				dataView2.setValue(" "+map.get("tsmc").toString()+" ");
				mapdata.put("name", dataView2);
				//归还日期
				TemplateDataView dataView3 = new TemplateDataView();
				dataView3.setColor("#000000");
				dataView3.setValue(" "+map.get("yghsj").toString()+" ");
				mapdata.put("date", dataView3);
				//备注
				TemplateDataView dataView6 = new TemplateDataView();
				dataView6.setColor("#99CC00");
				dataView6.setValue("  ★ 请合理安排时间，归还图书！  ★");
				mapdata.put("remark", dataView6);
				temp.setData(mapdata);
				WeixinUtils.sendTemplateMessage(toUser,temp);
			} 
		}
		if(list.size()>0){
			for(Map<String,Object> map:list){
				String toUser = map.get("wxh").toString();
				TxTshstx entity = new TxTshstx();
				entity.setBh(map.get("bh").toString());
				entity.setWxh(toUser);
				entity.setTsmc(map.get("tsmc").toString());
				entity.setTsbh(map.get("tsbh").toString());
				entity.setYghsj(s1.parse(map.get("yghsj").toString()));
				//entity.setGhsj(s1.parse(map.get("ghsj").toString()));
				entity.setJcsj(s1.parse(map.get("jcsj").toString()));
				dao.save(entity);
			}
		}
	}
}
