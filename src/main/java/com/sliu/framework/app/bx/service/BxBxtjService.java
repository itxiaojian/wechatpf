package com.sliu.framework.app.bx.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.bx.dao.BxBxtjDao;
import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.sbgl.dao.SbSbtjDao;

/**
*
*
@Author oufeng	
@Date 2015年8月26日 下午2:46:25
@Version 1.0

*/
@Service
public class BxBxtjService extends BaseBO<BxBxsq> {

	@Autowired
	private BxBxtjDao bxBxtjDao;
	
	/**
	*获得报修按楼宇的echarts的图标数据
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getDataLy(HttpServletRequest request,HttpServletResponse response)
			throws UnsupportedEncodingException { 
		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		String ly= request.getParameter("id");
		if(ly !=null){
			try {
	   			ly= new String(ly.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return bxBxtjDao.getDataLy(ly,stime,etime);
       }
	
	/**
	*获得报修类型的echarts的图标数据
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getDataLx(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		String lx= request.getParameter("id");
		if(lx !=null){
			try {
	   			lx= new String(lx.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return bxBxtjDao.getDataLx(lx,stime,etime);
       }
	
	/**
	*获得报修按楼宇和状态的echarts的图标数据
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getDataLyZt(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		String id= request.getParameter("id");
		String etime= request.getParameter("etime");
		String stime= request.getParameter("stime");
		if(id !=null){
		try {
   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return bxBxtjDao.getDataLyZt(id,stime,etime);
       }
	
	/**
	*报修的楼宇的tab
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getLyTab(HttpServletRequest request,HttpServletResponse response)
			throws UnsupportedEncodingException {
		String id= request.getParameter("id");
		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		if(id !=null){
		try {
   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return bxBxtjDao.getLyTabDate(id,stime,etime);
	}
	
	/**
	*报修类型的tab数据
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getLxTab(HttpServletRequest request,HttpServletResponse response)
			throws UnsupportedEncodingException {
		String id= request.getParameter("id");
		String etime= request.getParameter("etime");
		String stime= request.getParameter("stime");
		if(id !=null){
		try {
   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return bxBxtjDao.getLxTabDate(id,stime,etime);
	}
	
	/**
	*报修按楼宇的字典
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getLyZd(HttpServletRequest request,HttpServletResponse response)
			throws UnsupportedEncodingException {
		return bxBxtjDao.getLyZd();
       }
	
	/**
	*报修类型的字典
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getLxZd(HttpServletRequest request,HttpServletResponse response)
			throws UnsupportedEncodingException {
		return bxBxtjDao.getLxZd();
       }
	
	
	/**
	*报修按楼宇状态的tab
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年8月26日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getLyZtTab(HttpServletRequest request,HttpServletResponse response)
			throws UnsupportedEncodingException {
		String id= request.getParameter("id");
		String etime= request.getParameter("etime");
		String stime= request.getParameter("stime");
		if(id !=null){
		try {
   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return bxBxtjDao.getLyZtTab(id,stime,etime);
       }
	
}
