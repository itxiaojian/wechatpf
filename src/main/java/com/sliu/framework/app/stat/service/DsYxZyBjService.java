package com.sliu.framework.app.stat.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.stat.dao.DsYxZyBjDao;



@Service
public class DsYxZyBjService {

	@Autowired
	private DsYxZyBjDao dao;
	
	/**
	*获取院系专业班级人数的统计
	*@Author oufeng	
	@Date 2016年8月15日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getYxbjData(String openId,String qh,String pages)
			throws UnsupportedEncodingException {
		return dao.getYxbjData(openId,qh,pages);
       }

}
