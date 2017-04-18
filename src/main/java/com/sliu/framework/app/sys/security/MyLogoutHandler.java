//-------------------------------------------------------------------------
// 
//-------------------------------------------------------------------------

package com.sliu.framework.app.sys.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.sliu.framework.app.sys.bo.SysLogBO;
import com.sliu.framework.app.sys.model.SysLog;
import com.sliu.framework.app.util.AppUtil;

/**
 * Description of the class
 * 
 * @author lxt
 * @version 1.0
 * @since 2013-5-12
 */

public class MyLogoutHandler extends SecurityContextLogoutHandler implements
		LogoutHandler {

	@Autowired
	protected SysLogBO sysLogBO;

	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		if (AppUtil.getCurrentUser() != null) {
			SysLog sll = new SysLog();
			sll.setOperDate(new Date());
			sll.setUserId(AppUtil.getCurrentUser().getUsername());
			sll.setUsername(AppUtil.getCurrentUser().getXm());
			sll.setIp(request.getRemoteAddr());
			sll.setUserAgent(request.getHeader("user-agent"));
			sll.setOperation("退出");
			sysLogBO.save(sll);
		}
	}

}
