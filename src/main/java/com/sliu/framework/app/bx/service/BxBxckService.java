package com.sliu.framework.app.bx.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.bx.dao.BxBxckDao;
import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.common.dao.support.Pagination;

@Service
public class BxBxckService extends BaseBO<BxBxsq> {

	@Autowired
	private BxBxckDao dao;

	/**
	 * 查看门户页面
	 * @author oufeng
	 * @date 2015年8月07日
	 */
	@Transactional
	public List<Map<String,Object>>getList(String zt,String nr,String time,String pages){
		return dao.getList(zt,nr,time,pages);
}
	
	/**
	 * 总页数
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	public int getCount(HttpServletRequest request) 
			throws UnsupportedEncodingException{
		String zt=request.getParameter("zt");
		String nr = request.getParameter("nr");
		String time=request.getParameter("time");
		if(nr!=null){
	   		nr = new String(nr.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			nr="";
	   		}
		return dao.getCount(zt,nr,time);
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
	 * 获得维修大类的数据字典
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getWxdl(){
		return dao.getWxdl();
	}
	
	/**
	 * 报修门户查看的详情查看
	 * @author oufeng
	 * @date 2015年8月10日
	 */
	@Transactional
	public List<Map<String,Object>>getCkList(String id){
		return dao.getCkList(id);
}

	/**
	 * 获得维修主题的数据
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getBxzt(String wxdl){
		return dao.getBxzt(wxdl);
	}
	
	/**
	 * 保修查看
	 * @author chenhui
	 * @date 2016年9月03日
	 */
	@Transactional
	public Pagination<Map<String, Object>> getList(Integer start,Integer limit,String zt,String bm,String wxdl,String bxzt){
		return dao.getList(start, limit,zt,bm,wxdl,bxzt);
}
	
}
