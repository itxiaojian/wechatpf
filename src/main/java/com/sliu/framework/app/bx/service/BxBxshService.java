package com.sliu.framework.app.bx.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.bx.dao.BxBxclDao;
import com.sliu.framework.app.bx.dao.BxBxshDao;
import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
*
@Author oufeng	
@Date 2015年8月10日 下午4:37:51
@Version 1.0
*/
@Service
public class BxBxshService extends BaseBO<BxBxspyjb> {
                  
	@Autowired
	private BxBxshDao dao;
	
	@Autowired
	private BxBxclDao cldao;
	
	
	/**
	 * 根据维修大类获得报修的主题
	 * @author chenhui
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getBxzt(String wxdl) {
		return dao.getBxzt(wxdl);
	}
	
	/**
	 * 获得维修大类的数据字典
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getWxdl(String id){
		return dao.getWxdl(id);
	}
	
	/**
	 * 获得维修大类的数据字典
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getWxdl(){
		return dao.getWxdl();
	}
	
	/**
	 * 获得派出人
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getSerder(String id){
		return dao.getSender(Integer.parseInt(id));
	}
	
	/**
	 * 获得派出人
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getWxzl(HttpServletRequest request, HttpServletResponse response){
		String id=request.getParameter("id");
		return dao.getWxzl(id);
	}
	
	/**
	 * 获得维修工
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getWxg(){
		return dao.getWxg();
	}
	
	/**
	 * 
	 * 保存到审批意见表
	 * @throws ParseException 
	 * @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月11日 
	 * @Version 1.0
	 */
	@Transactional
	public void savetg(HttpServletRequest request,
			HttpServletResponse response) {
		String shyj=request.getParameter("shyj");
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		try {
			shyj = new String(shyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   		String shId=request.getParameter("shId");
		BxBxspyjb  bxspyjb = new BxBxspyjb();
		bxspyjb.setBxid(Long.parseLong(shId));
		bxspyjb.setZt("2");
		bxspyjb.setSftg("0");
		bxspyjb.setSpsj(new Date());
		bxspyjb.setSpyj(shyj);
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		dao.save(bxspyjb);
	}
	
	/**
	 * 
	 * 保存到审批意见表
	 * @throws ParseException 
	 * @Author oufeng
	 * @Date 2015年8月11日 
	 * @Version 1.0
	 */
	@Transactional
	public void savebh(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		String shyj=request.getParameter("shyj");
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		try {
			shyj = new String(shyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   		String shId=request.getParameter("shId");
		BxBxspyjb  bxspyjb = new BxBxspyjb();
		bxspyjb.setBxid(Long.parseLong(shId));
		bxspyjb.setZt("3");
		bxspyjb.setSftg("1");
		bxspyjb.setSpsj(new Date());
		bxspyjb.setSpyj(shyj);
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		dao.save(bxspyjb);
	}
	
	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public  void toRejecCon(HttpServletRequest request,
			HttpServletResponse response){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		
		BxBxspyjb  bxspyjb = new BxBxspyjb();
		String id=request.getParameter("id");
		//str 驳回理由
		String str=request.getParameter("spyj");
		try {
			str= new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.toRejecWxjl(Integer.parseInt(id));
		dao.toRejecSp(Integer.parseInt(id));
       bxspyjb.setBxid(Long.parseLong(id));
	  bxspyjb.setZt("3");
	 bxspyjb.setSftg("1");
	 bxspyjb.setSpsj(new Date());
	bxspyjb.setSprbh(yhbh);
	bxspyjb.setSprxm(yhxm);
	bxspyjb.setSpyj(str);
	dao.save(bxspyjb);
	//dao.toRejecSpyj(Integer.parseInt(id),yhbh,yhxm,new Date(),str);
	}
	
	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public  void toRejecCheckBox(HttpServletRequest request,
			HttpServletResponse response){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		BxBxspyjb  bxspyjb ;
	//	String id=request.getParameter("id");
		//str 驳回理由
		String str=request.getParameter("spyj");
		try {
			str= new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	 String idList=request.getParameter("id");
	   	 String[] ids = idList.trim().split(","); 
		  for(int i=0; i<ids.length; i++) { 
	    bxspyjb = new BxBxspyjb();
		dao.toRejecWxjl(Integer.parseInt(ids[i]));
		dao.toRejecSp(Integer.parseInt(ids[i]));
		 bxspyjb.setBxid(Long.parseLong(ids[i]));
		  bxspyjb.setZt("3");
		 bxspyjb.setSftg("1");
		 bxspyjb.setSpsj(new Date());
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		bxspyjb.setSpyj(str);
		dao.save(bxspyjb);
		//dao.toRejecSpyj(Integer.parseInt(ids[i]),yhbh,yhxm,new Date(),str);
		  }
	}
	
	/**
	 * 更新审批表的状态
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void updateSp(HttpServletRequest request,
			HttpServletResponse response){
   		String bxId=request.getParameter("id");
   		dao.updateBxsp(Long.parseLong(bxId));
	}
	
	/**
	 * 更新审批表的状态
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void updateSpCheckBox(HttpServletRequest request,
			HttpServletResponse response){
   	 String idList=request.getParameter("id");
   	 String[] ids = idList.trim().split(","); 
	  for(int i=0; i<ids.length; i++) { 
   		dao.updateBxsp(Long.parseLong(ids[i]));
	  }
	}
	
	/**
	 * 保存评价表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void updateSpyj(HttpServletRequest request,
			HttpServletResponse response){
   		String bxId=request.getParameter("id");
   	   	SysYh yh =AppUtil.getCurrentUser();
   		String yhbh =yh.getYhbh();
   		String yhxm = yh.getXm();
   		BxBxspyjb  bxspyjb = new BxBxspyjb();
       bxspyjb.setBxid(Long.parseLong(bxId));
	  bxspyjb.setZt("2");
	 bxspyjb.setSftg("0");
	 bxspyjb.setSpsj(new Date());
	bxspyjb.setSprbh(yhbh);
	bxspyjb.setSprxm(yhxm);
	 bxspyjb.setSpyj("审批通过");
	dao.save(bxspyjb);
   	//	dao.updateSpyj(Long.parseLong(bxId));
   		
	}
	
	/**
	 * 批量保存评价表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void saveSpyjCheckBox(HttpServletRequest request,
			HttpServletResponse response){
	  	 String idList=request.getParameter("id");
	   	 String[] ids = idList.trim().split(","); 
	   	BxBxspyjb  bxspyjb ;
		  for(int i=0; i<ids.length; i++) { 
   	   	SysYh yh =AppUtil.getCurrentUser();
   		String yhbh =yh.getYhbh();
   		String yhxm = yh.getXm();
   	   bxspyjb = new BxBxspyjb();
       bxspyjb.setBxid(Long.parseLong(ids[i]));
	  bxspyjb.setZt("4");
	 bxspyjb.setSftg("0");
	 bxspyjb.setSpyj("审批通过");
	 bxspyjb.setSpsj(new Date());
	bxspyjb.setSprbh(yhbh);
	bxspyjb.setSprxm(yhxm);
	dao.save(bxspyjb);
	}
	}
	
	
	/**
	 * 根据维修大类获得报修的主题
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getBxzt(HttpServletRequest request,
			HttpServletResponse response){
	 	 String id=request.getParameter("id");
	 	try {
			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.getBxzt(id);
	}
	
	/**
	 * 派单的提醒
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void pdTxCheckBox(HttpServletRequest request,HttpServletResponse response) {
		 String idList=request.getParameter("id");
		 String[] ids = idList.trim().split(","); 
		 for(int i=0; i<ids.length; i++) { 
		String wxrbh ="";
		String openId="";
		String xgh ="";
		if(dao.getWxrbh(ids[i]).size()!=0){
			wxrbh = dao.getWxrbh(ids[i]).get(0).get("wxrbh")+"";
		}
		List<Map<String,Object>> list =dao.getOpenId(wxrbh);
		if(list.size()!=0){
			for (int j  = 0; j< list.size(); j++) {
				Map<String,Object> map = (Map<String,Object>) list.get(j);
				Iterator<String> iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object object = map.get(key);
					openId= object +"";
				  //消息循环发送
				    WeixinUtils.massPreview("text", openId, "您有新的派单,请领取!");
				  }
			   }
		    }
		
		if(cldao.getXgh(ids[i]).size()!=0){
		 xgh = cldao.getXgh(ids[i]).get(0).get("xgh")+"";
		}
		List<Map<String,Object>> list1 =dao.getOpenId(xgh);
		if(list1.size()!=0){
			for (int j = 0; j< list1.size(); j++) {
				Map<String,Object> map = (Map<String,Object>) list1.get(j);
				Iterator<String> iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object object = map.get(key);
					openId= object +"";
				  //消息循环发送
				    WeixinUtils.massPreview("text", openId, "您的报修审核结束");
				}
			  }
		    }
	     }
	   }
	
	/**
	 * 驳回的提醒
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void rejecTxCheckBox(HttpServletRequest request,HttpServletResponse response) {
		 String idList=request.getParameter("id");
		 String[] ids = idList.trim().split(","); 
		 for(int i=0; i<ids.length; i++) { 
		String openId="";
		String xgh ="";
		if(cldao.getXgh(ids[i]).size()!=0){
		 xgh = cldao.getXgh(ids[i]).get(0).get("xgh")+"";
		}
		List<Map<String,Object>> list1 =dao.getOpenId(xgh);
		if(list1.size()!=0){
			for (int j = 0; j< list1.size(); j++) {
				Map<String,Object> map = (Map<String,Object>) list1.get(j);
				Iterator<String> iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object object = map.get(key);
					openId= object +"";
				  //消息循环发送
				    WeixinUtils.massPreview("text", openId, "您的报修已被驳回");
				}
			  }
		    }
	     }
	   }
	
	/**
	 * 驳回的提醒
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void rejecTx(HttpServletRequest request,HttpServletResponse response) {
		 String id=request.getParameter("id");
		String openId="";
		String xgh ="";
		if(cldao.getXgh(id).size()!=0){
		 xgh = cldao.getXgh(id).get(0).get("xgh")+"";
		}
		List<Map<String,Object>> list1 =dao.getOpenId(xgh);
		if(list1.size()!=0){
			for (int j = 0; j< list1.size(); j++) {
				Map<String,Object> map = (Map<String,Object>) list1.get(j);
				Iterator<String> iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object object = map.get(key);
					openId= object +"";
				  //消息循环发送
				    WeixinUtils.massPreview("text", openId, "您的报修已被驳回");
				}
			  }
	       }
	   }
     }
