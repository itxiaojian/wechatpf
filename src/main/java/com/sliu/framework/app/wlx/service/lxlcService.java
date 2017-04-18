package com.sliu.framework.app.wlx.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wlx.dao.lxlcDao;

/**
 * @author wangxiangyang
 * @date 2015年5月18日
 */
@Service
public class lxlcService {

	@Autowired
	private lxlcDao lxlcDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取当前登录人的离校流程信息
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlx(String openId){
		return lxlcDao.getGrlx(openId);
	}
	
	/**
	 * 获取当前登录人的离校流程信息
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlx1(String openId){
		return lxlcDao.getGrlx1(openId);
	}
	
/**
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx(int tacheid,String openId){
		return lxlcDao.getGrlxxx(tacheid,openId);
	}
	
	/**
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx1(int tacheid,String openId){
		return lxlcDao.getGrlxxx1(tacheid,openId);
	}
	
	
}
