package com.sliu.framework.app.stat.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.stat.dao.DsYxblqkDao;



@Service
public class DsYxblqkService {
	
	@Autowired
	private DsYxblqkDao dao;
	
	/**
	*获取迎新的数据
	*@Author oufeng	
	@Date 2016年8月15日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getYxData(HttpServletRequest request) throws UnsupportedEncodingException {
		String pages= request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		return dao.getYxData(pages);
       }
	
	/**
	*根据查询条件获取迎新的数据
	*@Author oufeng	
	@Date 2016年8月15日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getYxData1(String yxmc,String pages) throws UnsupportedEncodingException {
		return dao.getYxData1(yxmc,pages);
       }

}
