package com.sliu.framework.app.sys.security;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.sliu.framework.app.sys.bo.SysLogBO;
import com.sliu.framework.app.sys.model.SysLog;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
 * 
 * Description of the class
 * 
 * @author lxt
 * @version 1.0
 * @since 2013-3-13
 */
public class MyAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	private RequestCache requestCache = new HttpSessionRequestCache();

	private Map<String, String> targetUrlMap;

	@Autowired
	protected SysLogBO sysLogBO;

	protected LoadUserProfile loadUserProfile;

	public LoadUserProfile getLoadUserProfile() {
		return loadUserProfile;
	}

	public void setLoadUserProfile(LoadUserProfile loadUserProfile) {
		this.loadUserProfile = loadUserProfile;
	}

	/*@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		// SysUser sysUser = (SysUser)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		SysLog sll = new SysLog();
		sll.setOperDate(new Date());
		sll.setUserId(AppUtil.getCurrentUser().getUsername());
		sll.setUsername(AppUtil.getCurrentUser().getFullname());
		sll.setOperation("登录");
		sll.setIp(request.getRemoteAddr());
		sll.setUserAgent(request.getHeader("user-agent"));
		sysLogBO.save(sll);
		request.getSession().removeAttribute("error");
		SysUser sysUser = (SysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		request.getSession().setAttribute("LOGIN_USER", sysUser);

		if (loadUserProfile != null) {
			String targetUrl = loadUserProfile.loadProfile(targetUrlMap,
					request, response, sysUser);
			if (targetUrl != null) {
				response.sendRedirect(request.getContextPath() + targetUrl);
				return;
			}
		}

		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		if (isAlwaysUseDefaultTargetUrl()
				|| StringUtils.isNotBlank(request
						.getParameter(getTargetUrlParameter()))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}

		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}*/
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		// SysUser sysUser = (SysUser)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		SysLog sll = new SysLog();
		sll.setOperDate(new Date());
		sll.setUserId(AppUtil.getCurrentUser().getUsername());
		sll.setUsername(AppUtil.getCurrentUser().getXm());
		sll.setOperation("登录");
		sll.setIp(request.getRemoteAddr());
		sll.setUserAgent(request.getHeader("user-agent"));
		sysLogBO.save(sll);
		request.getSession().removeAttribute("error");
		/*SysUser sysUser = (SysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();*/
		
		SysYh sysYh = (SysYh) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		request.getSession().setAttribute("LOGIN_USER", sysYh);

		if (loadUserProfile != null) {
			String targetUrl = loadUserProfile.loadProfile(targetUrlMap,
					request, response, sysYh);
			if (targetUrl != null) {
				response.sendRedirect(request.getContextPath() + targetUrl);
				return;
			}
		}

		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		if(targetUrl.indexOf("wyxx")!=-1){
			response.sendRedirect(targetUrl);
			//getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}else if(targetUrl.indexOf("toBxsq")!=-1){
			response.sendRedirect(targetUrl);
			//getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}else{
			if (isAlwaysUseDefaultTargetUrl()
					|| StringUtils.isNotBlank(request
							.getParameter(getTargetUrlParameter()))) {
				requestCache.removeRequest(request, response);
				super.onAuthenticationSuccess(request, response, authentication);
				return;
			}
			getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}
	}

	public void setTargetUrlMap(Map<String, String> targetUrlMap) {
		this.targetUrlMap = targetUrlMap;
	}
}