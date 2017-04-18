package com.sliu.framework.app.wfw.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsZggzDao;

/**
 * 微信用户
 * @author liujiasen
 * @date 2015年5月18日
 */
@Service
public class ZsZggzService {

	@Autowired
	private ZsZggzDao zsZggzDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取当前登录人的图书借阅信息
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	public List<Map<String,Object>> getGrgz(String openId){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		String gzffsj = sim.format(c.getTime());
		return zsZggzDao.getGrgz(gzffsj,openId);
	}
	
/**
	 * @author wangxiangyang
	 * @date 2016年4月29日
	 * @return
	 */
	public List<Map<String,Object>> getGrgz1(String openId){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-M");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String gzffsj = sim.format(c.getTime());
		return zsZggzDao.getGrgz1(gzffsj,openId);
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年5月22日
	 * @param gzffsj
	 * @return
	 */
	public List<Map<String,Object>> getGrgzBysj(String gzffsj,String openId){
		return zsZggzDao.getGrgz(gzffsj,openId);
	}
}
