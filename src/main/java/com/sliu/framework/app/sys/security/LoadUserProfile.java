//-------------------------------------------------------------------------
// 
//-------------------------------------------------------------------------

package com.sliu.framework.app.sys.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sliu.framework.app.sys.model.SysYh;

/**
 * Description of the class
 * 
 * @author lxt
 * @version 1.0
 * @since 2013-10-22
 */

public interface LoadUserProfile {
	/**
	 * 
	 * @author lxt
	 * @since 2013-10-22
	 * @param targetUrlMap
	 * @param request
	 * @param response
	 * @param user
	 * @return 重定向页面地址
	 */
	/*String loadProfile(Map<String, String> targetUrlMap,
			HttpServletRequest request, HttpServletResponse response,
			SysUser user);*/
	
	String loadProfile(Map<String, String> targetUrlMap,
			HttpServletRequest request, HttpServletResponse response,
			SysYh sysYh);
}
