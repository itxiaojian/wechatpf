package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsXssxqkDao;

/**
 * 学生实习情况
 * @author liujiasen
 * @date 2015年5月28日
 */
@Service
public class ZsXssxqkService {

	@Autowired
	private ZsXssxqkDao zsXssxqkDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 按学年和学期获取当前登录人的奖惩数据
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @param ksqh
	 * @return
	 */
	public List<Map<String,Object>> getXssxqk(String bjbh,String openId,HttpServletRequest request){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXssxqkDao.getXssxqk(bjbh,openId,pages);
	}

	/**
	 * 获取班级列表
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	public List<Map<String,Object>> getBjlb(String openId){
		return zsXssxqkDao.getBjlb(openId);
	}
}
