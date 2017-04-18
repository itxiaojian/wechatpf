package com.sliu.framework.app.sbgl.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.sbgl.dao.SbSbtjDao;
import com.sliu.framework.app.sbgl.model.SbSbxx;

/**
*
*
@Author oufeng	
@Date 2015年8月26日 下午2:46:25
@Version 1.0

*/
@Service
public class SbSbtjService extends BaseBO<SbSbxx> {

	@Autowired
	private SbSbtjDao sbSbtjDao;
	
	/**
	*
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getDataLx(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		String id= request.getParameter("id");
		if(id !=null){
			try {
	   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return sbSbtjDao.getDataLx(id,stime,etime);
       }
	
	/**
	*获取图标数据
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getDataZzc(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		String id= request.getParameter("id");
		if(id !=null){
			try {
	   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return sbSbtjDao.getDataZzc(id,stime,etime);
       }
	
	/**
	*获取统计数据
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getZzc(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		String id= request.getParameter("id");
		if(id !=null){
			try {
	   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return sbSbtjDao.getZzc(id,stime,etime);
       }
	
	/**
	*
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getData(HttpServletRequest request,HttpServletResponse response)
			throws UnsupportedEncodingException {
		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		String id= request.getParameter("id");
		if(id !=null){
			try {
	   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return sbSbtjDao.getData(id,stime,etime);
       }
	
	/**
	 * 获得设备类型
	 * */
	public List<Map<String,Object>> getSblx(HttpServletRequest request,HttpServletResponse response) {
		return sbSbtjDao.getSblx();
	}
	
	/**
	 * 获得部门名称
	 * */
	public List<Map<String,Object>> getBmmc(HttpServletRequest request,HttpServletResponse response) {
		return sbSbtjDao.getBmmc();
	}
}
