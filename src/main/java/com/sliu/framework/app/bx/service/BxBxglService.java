package com.sliu.framework.app.bx.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.bx.dao.BxBxglDao;
import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
 * 报修管理
 * @author oufeng
 * @date 2015年8月07日
 */
@Service
public class BxBxglService extends BaseBO<BxBxsq> {

	@Autowired
	private BxBxglDao dao;

	
	/**
	 * 报修审核的数据
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public Pagination<Map<String, Object>> getBxsh(Integer start,Integer limit,String bm,String wxdl,String bxzt){
		return dao.getBxsh(start, limit, bm, wxdl, bxzt);
}
	
	/**
	 * 分页查询信息数据
	 * @author oufeng
	 * @date 2015年8月07日
	 * @param start
	 * @param limit
	 * @param zt 状态
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> pager(Integer start,Integer limit, String zt,String code,String starttime,String endtime) {
		return dao.pager(start, limit, zt,code,starttime,endtime);
	}
	
	/**
	 * 获取字典表中的状态
	 * @author oufeng
	 * @date 2015年8月07日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getZt(){
		return dao.getZt();
	}
	
	/**
	 * @author oufeng
	 * @date 2015年8月07日
	 * @param id
	 * @return
	 */
	@Transactional
	public BxBxsq getEntity(Long id){
		return dao.get(id);
	}

	/**
	 * 删除
	 * @author oufeng
	 * @date 2015年8月07日
	 * @param id
	 */
	@Transactional
	public void deleteEntity(Long id) {
		dao.delete(id);
		dao.deleteBxspyjb(id.toString());
		dao.deleteBxwxjl(id.toString());
		if(dao.getBxbz(id+"").size()!=0){
		String bz =dao.getBxbz(id+"").get(0).get("bz")+"";
		dao.deleteBxPhoto(bz);
		}
	}
		
		/**
		 * 手机端页面
		 * @author oufeng
		 * @date 2015年8月07日
		 */
		@Transactional
		public List<Map<String,Object>>getList(String zt,String bm,String wxdl,String bxzt,String pages){
			return dao.getList(zt,bm,wxdl,bxzt,pages);
	}
		
		/**
		 * 手机端页面查看
		 * @author oufeng
		 * @date 2015年8月10日
		 */
		@Transactional
		public List<Map<String,Object>>getCheck(String bxbh){
			return dao.getCheck(bxbh.trim());
	}
		
		/**
		 * 总页数
		 * @author oufeng
		 * @throws UnsupportedEncodingException 
		 * @date 2015年8月10日
		 */
		public int getCount(HttpServletRequest request) throws UnsupportedEncodingException{
			String zt=request.getParameter("zt");
			String bm = request.getParameter("bm");
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
			return dao.getCount(zt,bm,wxdl,bxzt);
		}
		
		/**
		 * 总页数
		 * @author oufeng
		 * @throws UnsupportedEncodingException 
		 * @date 2015年8月10日
		 */
		public int getBxshCount(HttpServletRequest request) throws UnsupportedEncodingException{
			String bm=request.getParameter("bm");
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
			return dao.getBxshCount(bm,bxzt,wxdl);
		}
		
		/**
		 * 报修审核的数据
		 * @author oufeng
		 * @date 2015年8月11日
		 */
		@Transactional
		public List<Map<String,Object>>getBxsh(String bm,String wxdl,String bxzt,String pages){
			return dao.getBxsh(bm.trim(),wxdl,bxzt,pages);
		}
		
		/**
		 * 审核
		 * @author oufeng
		 * @date 2015年8月11日
		 */
		@Transactional
		public void settg(String dm){
			 dao.setsh(dm);
		}
		
		/**
		 * 驳回
		 * @author oufeng
		 * @date 2015年8月11日
		 */
		@Transactional
		public void setbh(String dm){
			 dao.setbh(dm);
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
		 * 获得维修大类的数据字典
		 * @author oufeng
		 * @date 2015年8月11日
		 */
		@Transactional
		public String pljs(String starttime,String endtime){
			String str="";
			 dao.pljs(starttime,endtime);
			 str="1";
			 return str;
		}
	}
