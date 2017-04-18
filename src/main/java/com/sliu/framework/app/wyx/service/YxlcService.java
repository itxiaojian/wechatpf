package com.sliu.framework.app.wyx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wyx.dao.YxlcDao;

/**
 * @author wangxiangyang
 * @date 2016年8月10日
 */
@Service
public class YxlcService {

	@Autowired
	private YxlcDao yxlcDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取当前登录人的离校流程信息
	 * @author wangxiangyang
	 * @date 2016年8月10日
	 * @return
	 */
	public List<Map<String,Object>> getGrlx(String openId){
		return yxlcDao.getGrlx(openId);
	}
	
	/**
	 * 获取当前登录人的离校流程信息
	 * @author wangxiangyang
	 * @date 2016年8月10日
	 * @return
	 */
	public List<Map<String,Object>> getGrlx1(String openId){
		return yxlcDao.getGrlx1(openId);
	}
	
/**
	 * @author wangxiangyang
	 * @date 2016年8月10日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx(int tacheid,String openId){
		return yxlcDao.getGrlxxx(tacheid,openId);
	}
	
	/**
	 * @author wangxiangyang
	 * @date 2016年8月10日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx1(int tacheid,String openId){
		return yxlcDao.getGrlxxx1(tacheid,openId);
	}
	
	
}
