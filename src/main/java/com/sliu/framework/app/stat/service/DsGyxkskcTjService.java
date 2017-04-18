package com.sliu.framework.app.stat.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.stat.dao.DsGyxkskcDao;
import com.sliu.framework.app.util.AppUtil;


@Service
public class DsGyxkskcTjService {
	
	@Autowired
	private DsGyxkskcDao dao;
	
	/**
	*获取院系专业班级人数的统计
	*@Author oufeng	
	@Date 2016年8月15日 
	@Version 1.0
	*/
	public List<Map<String,Object>> getYxbjData(String openId,String qh,String pages)
			throws UnsupportedEncodingException {
		Map<String,Object> map= AppUtil.getXnNew(new Date());
		String xn=map.get("xn").toString();
		String xq= map.get("xq").toString();
		return dao.getYxbjData(xn,xq,openId,qh,pages);
       }


}
