package com.sliu.framework.app.cache.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.cache.service.CacheService;
import com.sliu.framework.app.util.ResponseData;

@Controller
@RequestMapping(value = "/wxauth/cache")
public class CacheController extends BaseController{

	@Autowired
	private CacheService cacheService;
	
	@RequestMapping(value = "/cacheMgr")
	public String openPage() {		
		return "/weixin/cache/cacheMgrList";
	}
	
	@RequestMapping(value = "/getWxCacheList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getWxCacheList() {
		return cacheService.getWxCacheList();
	}
	
	/**
	 * 清除缓存
	 * @return
	 */
	@RequestMapping(value = "/evictCache", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData evictCache(String[] keys) {
		cacheService.evictCache(keys);
		return ResponseData.SUCCESS_NO_DATA;
	}
}
