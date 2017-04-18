package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsYktxxDao;

/**
 * 一卡通信息
 * @author liujiasen
 * @date 2015年5月25日
 */
@Service
public class ZsYktxxService {

	@Autowired
	private ZsYktxxDao zsYktxxDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取当前登录人的一卡通信息
	 * @author liujiasen
	 * @date 2015年5月22日
	 * @return
	 */
	public List<Map<String,Object>> getYktxx(String openId){
		return zsYktxxDao.getYktxx(openId);
	}
	
	/**
	 * 获取当前登录人的一卡通消费信息（默认当前月的的消费信息）
	 * @author liujiasen
	 * @date 2015年5月25日
	 * @return
	 */
	public List<Map<String,Object>> getYktxfxx(String xfsj,String openId){
		return zsYktxxDao.getYktxfxx(xfsj,openId);
	}
	
	/**
	 * 学生擅自离校预警
	 * @author 
	 * @date 2016年8月16日
	 * @return
	 */
	public List<Map<String,Object>> getXfList(HttpServletRequest request,String code){
		String pages=request.getParameter("pages");
//		String code=request.getParameter("code");
		String openId = request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsYktxxDao.getXfList(pages, code,openId);
	}
	
}
