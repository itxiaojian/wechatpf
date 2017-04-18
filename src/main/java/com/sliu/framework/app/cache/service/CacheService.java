package com.sliu.framework.app.cache.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.support.weixin.WeixinCacheUtils;


@Service
public class CacheService {

	private Logger logger = LoggerFactory.getLogger(CacheService.class); 
	
	@Autowired
	private CacheManager cacheManager;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWxCacheList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Cache cache = cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME);
		ConcurrentMap<String, Map<String, String>> store = (ConcurrentMap<String, Map<String, String>>)cache.getNativeCache();
		Set<String> keySet = store.keySet();
		Iterator<String> iterator=keySet.iterator();
		while(iterator.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			String key=(String)iterator.next();
			ValueWrapper valueWrapper = cache.get(key);			
			map.put("key", key);
			map.put("value", valueWrapper.get());
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 清除缓存
	 * @param keys
	 * @return
	 */
	public void evictCache(String[] keys) {
		Cache cache = cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME);
		for(int i =0; i<keys.length; i++) {
			String key = keys[i];
			cache.evict(key);
			logger.info("成功清除KEY--【{}】的缓存", key);
		}	
	}
}
