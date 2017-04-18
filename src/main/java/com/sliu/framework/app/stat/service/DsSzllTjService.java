package com.sliu.framework.app.stat.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.stat.dao.DsSzllTjDao;
import com.sliu.framework.app.sys.model.SysYh;

@Service
public class DsSzllTjService extends BaseBO<SysYh>{
protected Logger logger = LoggerFactory.getServiceLog(SysYh.class);
	
	@Autowired
	private DsSzllTjDao dao;
	/**
	*
	*
	@throws UnsupportedEncodingException 
	 * @Author oufeng	
	@Date 2015年7月22日 下午3:10:50
	@Version 1.0
	*/
	public List<Map<String,Object>> getData(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
 		String yx= request.getParameter("yx");
		return dao.getData(yx);
       }
	
	
	/**
	 * 获得部分名称
	 * */
	public List<Map<String,Object>> getBmmc(HttpServletRequest request,HttpServletResponse response) {
		return dao.getBmmc();
	}
	

	/**
	*
	*
	@throws UnsupportedEncodingException 
	 * @Author wangxiangyang	
	@Date 2016年8月17日
	@Version 1.0
	*/
	public List<Map<String,Object>> getData1(HttpServletRequest request) throws UnsupportedEncodingException {
 		String pages= request.getParameter("pages");
 		if (pages == null) {
			pages = "1";
		}
		return dao.getData1(pages);
       }
	
	/**
	*
	*
	@throws UnsupportedEncodingException 
	 * @Author wangxiangyang	
	@Date 2016年8月17日
	@Version 1.0
	*/
	public List<Map<String,Object>> getData2(HttpServletRequest request) throws UnsupportedEncodingException {
		String bmmc1 = request.getParameter("bmmc");
   		String bmmc = new String(bmmc1.getBytes("iso8859-1"),"utf-8");
		return dao.getData2(bmmc);
       }
}

