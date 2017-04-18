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
import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

@Service
public class BxBxclService extends BaseBO<BxBxsq> {

	@Autowired
	private BxBxclDao dao;

	@Autowired
	private BxBxshDao bxshDao;
	
	@Autowired
	private BxBxglDao bxglDao;

	/**
	 * 总页数
	 * 
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	public int getBxclCount(HttpServletRequest request) throws UnsupportedEncodingException {
		String bm = request.getParameter("bm");
		SysYh yh = AppUtil.getCurrentUser();
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
	   		
		return dao.getBxclCount(bm,wxdl,bxzt, xm,jsList);
	}

	/**
	 * 报修处理的数据
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getBxcl(String bm, String wxdl,String bxzt,String pages, String xm) {
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
		return dao.getBxcl(bm.trim(),wxdl,bxzt, pages, xm,jsList);
	}

	/**
	 * 报修处理的查看
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getSee(String id) {
		Integer idInt = Integer.parseInt(id);
		SysYh yh = AppUtil.getCurrentUser();
		String xm = yh.getXm();
		return dao.getSee(idInt, xm);
	}

	/**
	 * 报修处理完成更新申请表，保存维修表
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccCon(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		// str 驳回理由
		/*String qc = request.getParameter("qc");
		String time = request.getParameter("time");
		try {
			qc = new String(qc.getBytes("ISO-8859-1"), "UTF-8");
			time = new String(time.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		dao.toSuccWxjl(Integer.parseInt(id));
		dao.toSuccSp(Integer.parseInt(id));
		// dao.toSuccSpyj(Integer.parseInt(id));
	}

	/**
	 * 报修处理保存报修意见表
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccSpyjCon(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		SysYh yh = AppUtil.getCurrentUser();
		String yhxm = yh.getXm();
		String yhbh = yh.getYhbh();
		BxBxspyjb bxspyjb = new BxBxspyjb();
		bxspyjb.setBxid(Long.parseLong(id));
		bxspyjb.setZt("5");
		bxspyjb.setSftg("0");
		bxspyjb.setSpyj("处理");
		bxspyjb.setSpsj(new Date());
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		bxshDao.save(bxspyjb);
	}

	/**
	 * 批量的报修处理完成更新申请表，保存维修表
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccCheckBox(HttpServletRequest request,
			HttpServletResponse response) {
		/*
		 * SysYh yh =AppUtil.getCurrentUser(); String yhbh =yh.getYhbh(); String
		 * yhxm = yh.getXm(); BxBxspyjb bxspyjb = new BxBxspyjb();
		 */
		// String id=request.getParameter("id");
		// str 驳回理由
		String idList = request.getParameter("idList");
		String[] ids = idList.trim().split(",");
		for (int i = 0; i < ids.length; i++) {
			dao.toSuccWxjl(Integer.parseInt(ids[i]));
			dao.toSuccSp(Integer.parseInt(ids[i]));
			// dao.toSuccSpyj(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 批量的报修处理保存结果到审批意见
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccSpyjCheckBox(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		BxBxspyjb bxspyjb ;
		String idList = request.getParameter("idList");
		String[] ids = idList.trim().split(",");
		for (int i = 0; i < ids.length; i++) {
		     bxspyjb = new BxBxspyjb();
			bxspyjb.setBxid(Long.parseLong(ids[i]));
			bxspyjb.setZt("5");
			bxspyjb.setSftg("0");
			bxspyjb.setSpyj("处理");
			bxspyjb.setSpsj(new Date());
			bxspyjb.setSprbh(yhbh);
			bxspyjb.setSprxm(yhxm);
			bxshDao.save(bxspyjb);
		}
	}

	/**
	 * 根据维修大类获得报修的主题
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getBxzt(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			id = new String(id.getBytes("ISO-8859-1"), "UTF-8");
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
	 * 回访的提醒
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void hfTx(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String xgh ="";
		String openId="";
		if(dao.getXgh(id).size()!=0){
		 xgh = dao.getXgh(id).get(0).get("xgh")+"";
		}
		List<Map<String,Object>> list =dao.getOpenId(xgh);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = (Map<String,Object>) list.get(i);
				Iterator<String> iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object object = map.get(key);
					openId= object +"";
				  //消息循环发送
				    WeixinUtils.massPreview("text", openId, "您的报修已完成,请评价");
				}
			}
		}
	}
}
