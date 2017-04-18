// -------------------------------------------------------------------------
// Copyright (c) 2000-2009 Ufinity. All Rights Reserved.
//
// This software is the confidential and proprietary information of
// Ufinity
//
// Original author: lxt
//
// -------------------------------------------------------------------------
// UFINITY MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
// THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
// TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
// PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UFINITY SHALL NOT BE
// LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
// MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
//
// THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
// CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
// PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
// NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
// SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
// SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
// PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES"). UFINITY
// SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
// HIGH RISK ACTIVITIES.
// -------------------------------------------------------------------------

package com.sliu.framework.app.sys.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.sys.bo.SysJsBO;

/**
 * 
 * 
 * @author lxt
 * @version 1.0
 * @since 2010-6-8
 */
public class DefaultInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private Logger log = LoggerFactory
			.getSystemLog(DefaultInvocationSecurityMetadataSource.class);

	public static final String CACHE_KEY = "FilterInvoSecMetaKey";

	@Autowired
	private CacheManager cacheManager;

	private Map<String, String> idUrlMap = null;

	private Map<Object, Collection<ConfigAttribute>> httpMap = null;

	private UrlMatcher urlMatcher = new AntUrlPathMatcher(false);

	private boolean stripQueryStringFromUrls;

	@Autowired
	private SysJsBO jsBO;

	private long lastmodified;

	private org.springframework.core.io.Resource menu;

	public DefaultInvocationSecurityMetadataSource() {
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		menu = patternResolver.getResource("/spring/menu.xml");
		try {
			lastmodified = menu.getFile().lastModified();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadSecurityMetadataSource() throws Exception {
		ApplicationContext context = null;
		if (menu.getFile().getAbsolutePath().startsWith("/")) {
			context = new ClassPathXmlApplicationContext("/spring/menu.xml");
		} else {
			context = new FileSystemXmlApplicationContext(menu.getFile()
					.getPath());
		}
		List<String> resourceList = (List<String>) context
				.getBean("resourceList");
		Map<Long, List<Function>> functionChildMap = new HashMap<Long, List<Function>>();
		Map<Long, Function> functionMap = new HashMap<Long, Function>();
		functionChildMap.put(0L, new LinkedList<Function>());
		idUrlMap = new HashMap<String, String>();

		Map<Long, List<String>> resourceRoleMapList = new HashMap<Long, List<String>>();
		try {
			List<Map<String, Object>> list = jsBO.listAllRoleResources();
			for (Map<String, Object> resourceRole : list) {
				Long id = NumberUtils.toLong((String) resourceRole
						.get("resourceId"));
				List<String> rl = resourceRoleMapList.get(id);
				if (rl != null) {
					rl.add((String) resourceRole.get("roleName"));
				} else {
					rl = new ArrayList<String>();
					rl.add((String) resourceRole.get("roleName"));
					resourceRoleMapList.put(id, rl);
				}
			}
		} catch (Exception e) {
			log.error("", "", e);
		}
		try {

			httpMap = new LinkedHashMap<Object, Collection<ConfigAttribute>>();
			List<Function> functionList = new ArrayList<Function>();
			for (String fun : resourceList) {
				String[] props = fun.split(",");
				Function function = new Function();
				if (StringUtils.isNotBlank(props[0])) {
					function.setId(NumberUtils.toLong(props[0]));
				}
				if (StringUtils.isNotBlank(props[1])) {
					function.setParentId(NumberUtils.toLong(props[1]));
				} else {
					function.setParentId(0L);
				}
				if (StringUtils.isNotBlank(props[2])) {
					function.setName(props[2]);
				}
				if (StringUtils.isNotBlank(props[3])) {
					function.setUrl(props[3]);
				}
				function.setOrderno(NumberUtils.toInt(props[4], 0));

				if (StringUtils.isNotBlank(props[5])) {
					function.setRolenames(Arrays.asList(StringUtils.split(
							props[5], "|")));
				}
				if (props.length >= 7)
					function.setIconcls(props[6]);
				functionList.add(function);

				functionMap.put(function.getId(), function);
				functionChildMap.put(function.getId(),
						new LinkedList<Function>());
				idUrlMap.put(function.getId().toString(), function.getUrl());
			}

			for (Function function : functionList) {
				List<Function> childList = functionChildMap.get(function
						.getParentId());
				if (childList != null)
					childList.add(function);

				if (function.getUrl() == null)
					continue;
				Collection<ConfigAttribute> attrs = new HashSet<ConfigAttribute>();

				List<String> roles = resourceRoleMapList.get(function.getId());
				if (roles != null) {
					for (String rolename : roles) {
						attrs.add(new SecurityConfig(rolename));
					}
				}

				if (function.getRolenames() != null) {
					for (String rolename : function.getRolenames()) {
						attrs.add(new SecurityConfig(rolename));
					}
				}
				function.setAuthorities(attrs);

				if (attrs.size() != 0) {
					httpMap.put(this.urlMatcher.compile(function.getUrl()),
							attrs);
					log.info("reload",
							function.getName() + "[" + function.getUrl()
									+ "] - " + attrs);
				}
			}

			Cache cache = this.cacheManager.getCache("resources");
			cache.put("FilterInvoSecMetaKey", this.httpMap);
			cache.put("FunctionChildMap", functionChildMap);
			cache.put("FunctionMap", functionMap);
			cache.put("functionList", functionList);
			lastmodified = menu.getFile().lastModified();
		} catch (Exception e) {
			log.error("", "", e);
		}

	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = "";

		if (object instanceof String) {
			url = (String) object;
		} else {
			url = ((FilterInvocation) object).getRequestUrl();
		}

		// if(AppUtil.getCurrentUser() == null) {
		// throw new AuthenticationServiceException("必须登录");
		// }

		return lookupAttributes(url);
	}

	public final Collection<ConfigAttribute> lookupAttributes(String url) {
		if (this.stripQueryStringFromUrls) {
			int firstQuestionMarkIndex = url.indexOf("?");
			if (firstQuestionMarkIndex != -1) {
				url = url.substring(0, firstQuestionMarkIndex);
			}
		}
		if (this.urlMatcher.requiresLowerCaseUrl()) {
			url = url.toLowerCase();
			log.debug("lookupAttributes", "Converted URL to lowercase, from: '"
					+ url + "'; to: '" + url + "'");
		}
		Cache cache = this.cacheManager.getCache("resources");
		Object obj = cache.get("FilterInvoSecMetaKey");
		if (obj == null) {
			try {
				loadSecurityMetadataSource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			if (lastmodified != menu.getFile().lastModified()) {
				cache.evict("FilterInvoSecMetaKey");
				cache.evict("FunctionChildMap");
				cache.evict("FunctionMap");
				loadSecurityMetadataSource();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (idUrlMap.get(url) != null)
			url = idUrlMap.get(url);

		Collection<ConfigAttribute> attributes = extractMatchingAttributes(url,
				this.httpMap);

		return attributes;
	}

	private Collection<ConfigAttribute> extractMatchingAttributes(String url,
			Map<Object, Collection<ConfigAttribute>> map) {
		for (Map.Entry<Object, Collection<ConfigAttribute>> entry : map
				.entrySet()) {
			boolean matched = this.urlMatcher.pathMatchesUrl(entry.getKey(),
					url);
			if (matched) {
				return (Collection) entry.getValue();
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}
