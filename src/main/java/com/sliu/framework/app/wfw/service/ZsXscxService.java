package com.sliu.framework.app.wfw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsXscxDao;


/**
 * 学生查询
 * @author oufeng
 * @date 2016年12月20日
 */
@Service
public class ZsXscxService {
	

	@Autowired
	private ZsXscxDao dao;
	
	
	
	/**
	 * 获取学生数据
	 * @author oufeng
	 * @date 2016年12月20日
	 * @return
	 */
	public List<Map<String,Object>> getXsxx(String yhbh,String code,String pages){
		List<Map<String,Object>> list=dao.getXsxx(yhbh,code,pages);
		return list;
	}
	
	/**
	 * 获取学生数据
	 * @author oufeng
	 * @date 2016年12月20日
	 * @return
	 */
	public int getPagecount(String yhid,String code){
		return 	dao.getPagecount(yhid,code);
	}

}
