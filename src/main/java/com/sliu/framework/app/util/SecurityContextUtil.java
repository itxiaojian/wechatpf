package com.sliu.framework.app.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



import com.sliu.framework.app.sys.model.SysYh;

/**
 * SecurityContextHolder 帮助类
 *
 * @datetime 2010-8-12 下午01:45:25
 * @author libinsong1204@gmail.com
 */
public class SecurityContextUtil {

	/**
	 * 获取当前登录用户编码
	 * 
	 * @return String 用户编码
	 */
	public static String getLoginUserCode() {
		if(SecurityContextHolder.getContext().getAuthentication() == null)
			return "anonymous";
		else {
			String userCode = SecurityContextHolder.getContext().getAuthentication().getName();
			return userCode;
		}
	}
	
	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static SysYh getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null)
			return (SysYh)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		else
			return null;
	}
	

}
