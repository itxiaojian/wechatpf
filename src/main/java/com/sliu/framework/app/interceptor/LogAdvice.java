package com.sliu.framework.app.interceptor;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sliu.framework.app.sys.bo.SysLogBO;
import com.sliu.framework.app.sys.controller.SysLogController;
import com.sliu.framework.app.sys.model.SysLog;
import com.sliu.framework.app.util.AppUtil;

/**
 * 
 * Description of the class
 * 
 * @author lxt
 * @version 1.0
 * @since 2014-4-17
 */
@Aspect
public class LogAdvice {
	@Autowired
	private SysLogBO logBO;

	// @Pointcut("execution(* com.jincai.app.danbao.*.controller.*.*(..))")
	// public void controller(){};
	//
	// @Pointcut("execution(* com.jincai.app.danbao.*.controller.*.*(..))")
	// public void service(){};

	// 匹配 org.crazyit.app.service.impl 包下所有类的、
	// 所有方法的执行作为切入点
	@Before("execution(* com.hrxx.framework.app.sys.controller.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		if (!(joinPoint.getThis() instanceof SysLogController))

		{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();

			SysLog entity = new SysLog();

			entity.setOperation(request.getRequestURI());
			entity.setOperDate(new Date());

			Enumeration<String> enumeration = request.getParameterNames();
			StringBuffer sb = new StringBuffer("|");
			while (enumeration.hasMoreElements()) {
				String paramName = (String) enumeration.nextElement();
				String paramValue = request.getParameter(paramName);
				sb.append(paramName).append("=").append(paramValue).append("|");
			}
			entity.setOperContent(sb.toString());
			if (AppUtil.getCurrentUser() != null) {
				entity.setUserId(AppUtil.getCurrentUser().getUsername());
				entity.setUsername(AppUtil.getCurrentUser().getXm());
			}
			entity.setIp(request.getRemoteAddr());
			entity.setUserAgent(request.getHeader("user-agent"));
			logBO.save(entity);
		}
	}

	/*
	 * @After("execution(* com.hrxx.framework.app.sys.controller.*.*(..))")
	 * public void logAfter(JoinPoint joinPoint) {
	 * 
	 * System.out.println("调用后"); System.out.println("hijacked : " +
	 * joinPoint.getSignature().getName());
	 * 
	 * }
	 * 
	 * // 匹配 org.crazyit.app.service.impl 包下所有类的、 // 所有方法的执行作为切入点
	 * 
	 * @AfterReturning(returning = "result", pointcut =
	 * "execution(* com.hrxx.framework.app.sys.controller.*.*(..))") public void
	 * logAfterReturning(JoinPoint joinPoint, Object result) {
	 * 
	 * System.out.println("logAfterReturning() is running!");
	 * System.out.println("hijacked : " + joinPoint.getSignature().getName());
	 * System.out.println("Method returned value is : " + result);
	 * 
	 * }
	 * 
	 * @AfterThrowing(pointcut =
	 * "execution(* com.hrxx.framework.app.sys.controller.*.*(..))", throwing =
	 * "error") public void logAfterThrowing(JoinPoint joinPoint, Throwable
	 * error) { System.out.println("调用方法" + joinPoint.getSignature().getName() +
	 * " : " + error);
	 * 
	 * }
	 */
}