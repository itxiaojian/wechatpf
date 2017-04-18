package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsWljfDao;


/**
 * 网络计费
 * @author wangxiangyang
 * @date 2016年8月8日
 */
@Service
public class ZsWljfService {
	
	@Autowired
	ZsWljfDao zsWljfdao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取网络计费信息
	 * @author wangxiangyang
	 * @date  2016年8月9日
	 * @return
	 */
	public List<Map<String,Object>> getWljfList(String openId,String pages){
		
		return zsWljfdao.getWljfList(openId,pages);
		
	}
	
	/**
	 * 获取网络计费详细信息
	 * @author wangxiangyang
	 * @date  2016年8月9日
	 * @return
	 */
	public List<Map<String,Object>> getWljfListxx(String openId,String zh){
		
		return zsWljfdao.getWljfListxx(openId,zh);
		
	}

}
