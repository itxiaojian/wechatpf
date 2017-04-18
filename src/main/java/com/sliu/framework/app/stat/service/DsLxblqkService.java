package com.sliu.framework.app.stat.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.stat.dao.DsLxblqkDao;



@Service
public class DsLxblqkService {
	
	@Autowired
	private DsLxblqkDao dao;
	
	/**
	*获取离校的数据
	*@Author oufeng	
	@Date 2016年8月15日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getLxData(HttpServletRequest request) throws UnsupportedEncodingException {
		String pages= request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		return dao.getLxData(pages);
       }
	
	/**
	*根据查询条件获取离校的数据
	*@Author oufeng	
	@Date 2016年8月15日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getLxData1(String yxmc,String pages) throws UnsupportedEncodingException {
		return dao.getLxData1(yxmc,pages);
       }

}
