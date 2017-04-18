package com.sliu.framework.app.util;

import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.bo.SysSjzdBO;

/**
 * @author liuxiantao
 */
@Scope("singleton")
@Component(value = "dictionaryMap")
public class DictionaryMap extends HashMap<Object, Object> implements
		ServletContextAware, InitializingBean {

	protected Logger logger = LoggerFactory.getServiceLog(DictionaryMap.class);

	@Autowired
	private SysSjzdBO sjzdBO;

	@Autowired
	private CacheManager cacheManager;

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (servletContext != null) {
			servletContext.setAttribute("dictMap", this);
			servletContext.setAttribute("context", this);
		}
	}

	public Object get(Object key) {
		String k = (String) key;
		if (k.contains(".")) {
			String[] str = k.split("\\.");
			Cache cache = cacheManager.getCache(str[0]);
			return cache.get(str[1]).get();
		} else {
			Object t = super.get(key);
			if (t == null) {
				QueryFilter filter = new QueryFilter();
				filter.addSorted("px", "asc");
				filter.addFilter("Q_zl_S_EQ", key);
				t = sjzdBO.getAll(filter);
				put((String) key, t);
			}
			return t;
		}
	}

}
