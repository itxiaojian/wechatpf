package com.sliu.framework.app.bx.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.sliu.framework.app.bx.dao.BxBxglDao;
import com.sliu.framework.app.bx.dao.BxBxshDao;
import com.sliu.framework.app.bx.dao.BxBxslDao;
import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.bx.model.BxBxwxjl;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
*
@Author oufeng	
@Date 2015年8月13日 上午11:28:25
@Version 1.0
*/
@Service
public class BxBxslService extends BaseBO<BxBxwxjl> {

	@Autowired
	private BxBxslDao dao;
	
	@Autowired
	private BxBxclDao bxclDao;
	
	@Autowired
	private BxBxshDao bxshDao;
	
	@Autowired
	private BxBxglDao bxglDao;

	/**
	 * 报修受理的数据
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public Pagination<Map<String, Object>>getBxsl(Integer start,Integer limit,String bm,
			String wxdl,String bxzt,String xm,String dlm,String time){
		return dao.getBxsl(start, limit, bm,wxdl,bxzt,xm,dlm,time);
	}
	
	/**
	 * 获取报修主题
	 * 
	 * @author chenhui
	 * @date 2015年8月7日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getBxzt(String wxdl) {
		return dao.getBxzt(wxdl);
	}
	
	/**
	 * 总页数
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	public int getBxslCount(HttpServletRequest request) throws UnsupportedEncodingException{
		String bm=request.getParameter("bm");
		SysYh yh =AppUtil.getCurrentUser();
		String xm = yh.getXm();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = bxglDao.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		String dlm="";
		if(dao.getDlm(yhbh).size()!=0){
		 dlm = dao.getDlm(yhbh).get(0).get("dlm")+"";}
		String wxdl=request.getParameter("wxdl");
		String bxzt=request.getParameter("bxzt");
		if(bm!=null){
	   		bm = new String(bm.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bm="";
	   		}
	   		if(bxzt!=null){
	   	   		bxzt = new String(bxzt.getBytes("ISO-8859-1"), "UTF-8"); 
	   	   		}else{
	   	   			bxzt="";
	   	   		}
	   		if(wxdl!=null){
	   	   		wxdl = new String(wxdl.getBytes("ISO-8859-1"), "UTF-8"); 
	   	   		}else{
	   	   			wxdl="";
	   	   		}
		return dao.getBxslCount(bm,wxdl,bxzt,xm,dlm,jsList);
	}
	
	/**
	 * 报修受理的数据
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getBxsl(String bm,String wxdl,String bxzt,String pages,String xm,String dlm){
 	    SysYh yh =AppUtil.getCurrentUser();
			String bmbh =yh.getBmbh();
			String yhbh = yh.getYhbh();
			List<Map<String, Object>> list = bxglDao.getJs(yhbh);
			List<Object> jsList = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = (Map<String,Object>) list.get(i);
				Iterator<String> iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object object = map.get(key);
				    jsList.add(object);
				}
			}
		return dao.getBxsl(bm.trim(),wxdl,bxzt,pages,xm,dlm,jsList);
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
		String str=request.getParameter("str");
		try {
			str= new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.toRejecWxjl(Integer.parseInt(id));
		dao.toRejecSp(Integer.parseInt(id));
       bxspyjb.setBxid(Long.parseLong(id));
	  bxspyjb.setZt("1");
	 bxspyjb.setSftg("1");
	 bxspyjb.setSpsj(new Date());
	bxspyjb.setSprbh(yhbh);
	bxspyjb.setSprxm(yhxm);
	bxspyjb.setSpyj(str);
	bxshDao.save(bxspyjb);
	//dao.toRejecSpyj(Integer.parseInt(id),yhbh,yhxm,new Date(),str);
	}
	
	/**
	 * 报修受理的查看
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getSee(String id){
		Integer idInt  = Integer.parseInt(id);
		SysYh yh =AppUtil.getCurrentUser();
		String xm = yh.getXm();
		String yhbh=yh.getYhbh();
		String dlm="";
		if(dao.getDlm(yhbh).size()!=0){
		 dlm = dao.getDlm(yhbh).get(0).get("dlm")+"";}
		return dao.getSee(idInt,xm,dlm);
	}
	
	/**
	 * 报修受理
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public  void toDeal(HttpServletRequest request,
			HttpServletResponse response){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		String id=request.getParameter("id");
		dao.toDealWxjl(Integer.parseInt(id));
		dao.toDealSp(Integer.parseInt(id));
	}
	
	/**
	 * 报修受理保存到审批意见
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public  void toDealSpyj(HttpServletRequest request,
			HttpServletResponse response){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		String id=request.getParameter("id");
		BxBxspyjb  bxspyjb = new BxBxspyjb();
		 bxspyjb.setBxid(Long.parseLong(id));
		  bxspyjb.setZt("4");
		 bxspyjb.setSftg("0");
		 bxspyjb.setSpsj(new Date());
		 bxspyjb.setSpyj("受理");
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		bxshDao.save(bxspyjb);
	}
	
	
	/**
	 * 处理的提醒
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void dealTx(HttpServletRequest request,HttpServletResponse response) {
		 String idList=request.getParameter("id");
		 String[] ids = idList.trim().split(","); 
		 for(int i=0; i<ids.length; i++) { 
		String openId="";
		String xgh ="";
		if(bxclDao.getXgh(ids[i]).size()!=0){
		 xgh = bxclDao.getXgh(ids[i]).get(0).get("xgh")+"";
		}
		List<Map<String,Object>> list1 =bxclDao.getOpenId(xgh);
		if(list1.size()!=0){
			for (int j = 0; j< list1.size(); j++) {
				Map<String,Object> map = (Map<String,Object>) list1.get(j);
				Iterator<String> iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object object = map.get(key);
					openId= object +"";
				  //消息循环发送
				    WeixinUtils.massPreview("text", openId, "您的报修已派单");
				}
			  }
		    }
	     }
	   }
	
	/**
	 * 报修受理保存意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public  void toDealSpyjCheckBox(HttpServletRequest request,
			HttpServletResponse response){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
	  	 String idList=request.getParameter("idList");
	   	 String[] ids = idList.trim().split(","); 
		 BxBxspyjb  bxspyjb;
		  for(int i=0; i<ids.length; i++) { 
	      bxspyjb = new BxBxspyjb();
		  bxspyjb.setBxid(Long.parseLong(ids[i]));
		  bxspyjb.setZt("4");
		 bxspyjb.setSftg("0");
		 bxspyjb.setSpyj("受理");
		 bxspyjb.setSpsj(new Date());
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		bxshDao.save(bxspyjb);
	}
	}
	
	/**
	 * 报修受理更新维修表和申请表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public  void toDealCheckBox(HttpServletRequest request,
			HttpServletResponse response){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
	  	 String idList=request.getParameter("idList");
	   	 String[] ids = idList.trim().split(","); 
		  for(int i=0; i<ids.length; i++) { 
		  dao.toDealWxjl(Integer.parseInt(ids[i]));
		  dao.toDealSpyj(Integer.parseInt(ids[i]));
		  dao.toDealSp(Integer.parseInt(ids[i]));
	}
	}
	
	/**
	 * 获得派出人
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void saveSender(HttpServletRequest request,
			HttpServletResponse response){
		String sendBh=request.getParameter("sendBh");
   		String sender=request.getParameter("sender");
   		try {
   			sender= new String(sender.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		if("".equals(sender)&& !"".equals(sendBh)){
   		      if(dao.getSenderByBh(sendBh).size()!=0){
   		    	sender =  dao.getSenderByBh(sendBh).get(0).get("xm")+"";
   		      }
   		}
		//List<Map<String, Object>>  xm = dao.getWxrxm(sendBh);
   		String bxId=request.getParameter("bxId");
   		BxBxwxjl  bxwxyj = new BxBxwxjl();
   		bxwxyj.setBxid(Long.parseLong(bxId));
   		bxwxyj.setWxbh(sendBh);
     	bxwxyj.setWxrxm(sender);
   		bxwxyj.setPdsj(new Date());
   		bxwxyj.setWxzt("2");
       dao.save(bxwxyj);
	}
	
	/**
	 * 审核通过派单并保存
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void savePd(HttpServletRequest request,
			HttpServletResponse response){
		String sendBh=request.getParameter("sendBh");
   		String sender=request.getParameter("sender");
   		try {
   			sender= new String(sender.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		if("".equals(sender)&& !"".equals(sendBh)){
 		      if(dao.getSenderByBh(sendBh).size()!=0){
 		    	sender =  dao.getSenderByBh(sendBh).get(0).get("xm")+"";
 		      }
 		}
		//List<Map<String, Object>>  xm = dao.getWxrxm(sendBh);
   		String bxId=request.getParameter("bxId");
   		BxBxwxjl  bxwxyj = new BxBxwxjl();
   		bxwxyj.setBxid(Long.parseLong(bxId));
   		bxwxyj.setWxbh(sendBh);
     	bxwxyj.setWxrxm(sender);
   		bxwxyj.setPdsj(new Date());
   		bxwxyj.setWxzt("2");
       dao.save(bxwxyj);
	}
	
	/**
	 * 批量报修的派单
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void saveSenderCheckBox(HttpServletRequest request,
			HttpServletResponse response){
		  String idList=request.getParameter("id");
		  String sendBh=request.getParameter("sendBh");
		  String sender=request.getParameter("sender");
   		try {
   			sender= new String(sender.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
 		if("".equals(sender)&& !"".equals(sendBh)){
 		      if(dao.getSenderByBh(sendBh).size()!=0){
 		    	sender =  dao.getSenderByBh(sendBh).get(0).get("xm")+"";
 		      }
 		}
   	  String[] ids = idList.trim().split(","); 
   	  BxBxwxjl  bxwxyj ;
	  for(int i=0; i<ids.length; i++) { 
		  bxwxyj = new BxBxwxjl();
		bxwxyj.setBxid(Long.parseLong(ids[i]));
   		bxwxyj.setWxbh(sendBh);
     	bxwxyj.setWxrxm(sender);
   		bxwxyj.setPdsj(new Date());
   		bxwxyj.setWxzt("4");
       dao.save(bxwxyj);
	  }
		//List<Map<String, Object>>  xm = dao.getWxrxm(sendBh);
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
	 * 获得维修大类的数据字典
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getWxdl(){
		return dao.getWxdl();
	}
	
	/**
	 * 获得登录名
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getDlm(String yhbh){
		return dao.getDlm(yhbh);
	}
	
	/**
	 * 更新审批表的状态
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void updateBxsq(HttpServletRequest request,
			HttpServletResponse response){
   		String id=request.getParameter("id");
   		dao.updateBxsq(Long.parseLong(id));
	}
	
	/**
	 * 批量的报修受理的驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void updateBxsqCheckBox(HttpServletRequest request,
			HttpServletResponse response) {
		String idList = request.getParameter("idList");
		String[] ids = idList.trim().split(",");
		for (int i = 0; i < ids.length; i++) {
			dao.updateBxsq(Long.parseLong(ids[i]));
		}
	}
	
	/**
	 * 获得派单预览的信息
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getPdList(HttpServletRequest request){
	    String id=request.getParameter("bxId");
		return dao.getPdList(id);
	}
	
	/**
	 * 报修处理结束保存到报修意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccCon(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		String id = request.getParameter("id");
		dao.toSuccWxjl(Integer.parseInt(id));
		dao.toSuccSp(Integer.parseInt(id));
		// dao.toSuccSpyj(Integer.parseInt(id));
	}

	/**
	 * 报修处理结束保存审批意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccSpyjCon(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		String id = request.getParameter("id");
		BxBxspyjb bxspyjb = new BxBxspyjb();
		bxspyjb.setBxid(Long.parseLong(id));
		bxspyjb.setZt("9");
		bxspyjb.setSftg("0");
		bxspyjb.setSpsj(new Date());
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		bxspyjb.setSpyj("结束");
		bxshDao.save(bxspyjb);
	}
	
	/**
	 * 维修工提醒的详细页面
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getDetail(String id){
		return dao.getDetail(id);
	}
	
	/**
	 * 耗材列表的页面
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getHcList(){
		return dao.getHcList();
	}
	

	/**
	 * 耗材列表的页面
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getWxzt(String id){
		return dao.getWxzt(id);
	}
}


