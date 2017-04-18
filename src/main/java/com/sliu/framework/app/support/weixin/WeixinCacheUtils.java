package com.sliu.framework.app.support.weixin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.alibaba.fastjson.JSONObject;
import com.sliu.framework.app.dto.AccessToken;
import com.sliu.framework.app.dto.JsapiTicket;
import com.sliu.framework.app.spring.SpringApplicationContextHolder;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.wxutil.model.WxToken;
import com.sliu.framework.app.wxutil.service.WxTokenService;

public class WeixinCacheUtils {

	public static final String WX_CACHE_NAME = "weixin";
	public static final String TOKEN_CACHE_KEY = "tokenCache";//tokenKey缓存KEY
	public static final String JSAPI_TICKET_KEY = "ticketCache";//ticketKey缓存KEY
	public static final String WD_CACHE_KEY = "wdCache";//网点缓存KEY
	public static final String QKDD_CACHE_KEY = "qkddCache"; //取款订单
	
	//获取jsapi_ticket调用微信JS接口的地址（GET）
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";  
	
	private static CacheManager cacheManager = (CacheManager) SpringApplicationContextHolder.getBean(CacheManager.class);
	
	private static WxTokenService wxTokenService = (WxTokenService) SpringApplicationContextHolder.getBean(WxTokenService.class);
//	private static final FjwdDao fjwdDao = SpringApplicationContextHolder.getBean(FjwdDao.class);
//	private static final QkyyDao qkyyDao = SpringApplicationContextHolder.getBean(QkyyDao.class);

	/**
	 * 从缓存中取得accessToken,若不存在或者accessToken已经过期,则重新调微信接口获取并放入缓存
	 * @return
	 * @throws ParseException 
	 */
	public static String getAccessToken() {

		String accessToken = "";
		WxToken wxtoken = wxTokenService.get();//获取数据库中保存的token
		if(wxtoken == null){
			String dateStr = "";
			Date date = new Date(); //format的格式可以任意
		    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    dateStr = sdf.format(date);
			AccessToken token = WeixinUtils.getAccessToken(PropertiesInfo.appId, PropertiesInfo.appSecret);
			accessToken = token.getToken();
			WxToken newtoken = new WxToken();	
			newtoken.setCreateTime(dateStr);
			newtoken.setToken(token.getToken());
			newtoken.setExpiresIn(token.getExpiresIn());
			wxTokenService.save(newtoken);
		}else{
			String dateStr = wxtoken.getCreateTime();
	        Date createTime = new Date();
	        //注意format的格式要与日期String的格式相匹配
	        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        try {
				createTime = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//Date createTime = wxtoken.getCreateTime();
			int expiresIn = wxtoken.getExpiresIn();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(createTime);
			calendar.add(Calendar.SECOND, expiresIn-300);
			//access_token创建时间+有效时长得出TOKEN的失效时间(access_token有效时长手动减了5分钟,防止在有效时间临界点时出问题)
			Date invalidTime = calendar.getTime();
			//若当前时间小于TOKEN的失效时间,则重新获取access_token
			if(new Date().after(invalidTime)) {
				String dateStr1 = "";
				Date date = new Date(); //format的格式可以任意
			    DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			    dateStr1 = sdf1.format(date);
				AccessToken token = WeixinUtils.getAccessToken(PropertiesInfo.appId, PropertiesInfo.appSecret);
				accessToken = token.getToken();
				wxtoken.setCreateTime(dateStr1);
				wxtoken.setToken(token.getToken());
				wxtoken.setExpiresIn(token.getExpiresIn());
				wxTokenService.update(wxtoken);//修改数据库中的token
			} else {
				String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", wxtoken.getToken());  
				JSONObject jsonObject = WeixinUtils.httpRequest(requestUrl, "GET", null);
				if (null != jsonObject) {
				    if (40001 == jsonObject.getInteger("errcode")) {
				    	String dateStr1 = "";
						Date date = new Date(); //format的格式可以任意
					    DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					    dateStr1 = sdf1.format(date);
						AccessToken token = WeixinUtils.getAccessToken(PropertiesInfo.appId, PropertiesInfo.appSecret);
						accessToken = token.getToken();
						wxtoken.setCreateTime(dateStr1);
						wxtoken.setToken(token.getToken());
						wxtoken.setExpiresIn(token.getExpiresIn());
						wxTokenService.update(wxtoken);//修改数据库中的token
				    } else {
				    	accessToken = wxtoken.getToken();
				    }
			    }else{
			    	accessToken = wxtoken.getToken();
			    }
			}			
		}
		
//		Cache cache = cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME);
//		ValueWrapper wrapper = null;
//		if(cache!=null){
//			wrapper = cache.get(WeixinCacheUtils.TOKEN_CACHE_KEY);
//		}
//		if(wrapper == null) {//缓存中无tokenCache缓存
//			accessToken = WeixinCacheUtils.createAccessTokenPutCache();
//		}else {
//			TokenCacheDto tokenCacheDto = (TokenCacheDto) wrapper.get();
//			Date createTime = tokenCacheDto.getCreateTime();
//			int expiresIn = tokenCacheDto.getExpiresIn();
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(createTime);
//			calendar.add(Calendar.SECOND, expiresIn-300);
//			//access_token创建时间+有效时长得出TOKEN的失效时间(access_token有效时长手动减了5分钟,防止在有效时间临界点时出问题)
//			Date invalidTime = calendar.getTime();
//			//若当前时间小于TOKEN的失效时间,则重新获取access_token
//			if(new Date().after(invalidTime)) {
//				accessToken = WeixinCacheUtils.createAccessTokenPutCache();
//			}else {		
//				accessToken = tokenCacheDto.getToken();
//			}			
//		}		
		return accessToken;
	}
	
	/**
	 * 从缓存中取得accessToken,若不存在或者accessToken已经过期,则重新调微信接口获取并放入缓存
	 * @return
	 */
//	public static String getAccessToken() {
//		String accessToken = "";
//		Cache cache = cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME);
//		ValueWrapper wrapper = null;
//		if(cache!=null){
//			wrapper = cache.get(WeixinCacheUtils.TOKEN_CACHE_KEY);
//		}
//		if(wrapper == null) {//缓存中无tokenCache缓存
//			accessToken = WeixinCacheUtils.createAccessTokenPutCache();
//		}else {
//			TokenCacheDto tokenCacheDto = (TokenCacheDto) wrapper.get();
//			Date createTime = tokenCacheDto.getCreateTime();
//			int expiresIn = tokenCacheDto.getExpiresIn();
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(createTime);
//			calendar.add(Calendar.SECOND, expiresIn-300);
//			//access_token创建时间+有效时长得出TOKEN的失效时间(access_token有效时长手动减了5分钟,防止在有效时间临界点时出问题)
//			Date invalidTime = calendar.getTime();
//			//若当前时间小于TOKEN的失效时间,则重新获取access_token
//			if(new Date().after(invalidTime)) {
//				accessToken = WeixinCacheUtils.createAccessTokenPutCache();
//			}else {		
//				accessToken = tokenCacheDto.getToken();
//			}			
//		}		
//		return accessToken;
//	}
	
	/**
	 * 调用微信接口获取accessToken,并放入缓存中
	 * @return
	 */
	public static String createAccessTokenPutCache() {
		AccessToken token = WeixinUtils.getAccessToken(PropertiesInfo.appId, PropertiesInfo.appSecret);
		TokenCacheDto newCacheDto = new TokenCacheDto();
		newCacheDto.setCreateTime(new Date());
		newCacheDto.setToken(token.getToken());
		newCacheDto.setExpiresIn(token.getExpiresIn());
		cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME).put(WeixinCacheUtils.TOKEN_CACHE_KEY, newCacheDto);
		return token.getToken();
	}
	
	
	/**
	 * 从缓存中取得jsapiTicket,若不存在或者jsapiTicket已经过期,则重新调微信接口获取并放入缓存
	 * @return
	 */
	public static String getJsapiTicket() {
		String jsapiTicket = "";
		Cache cache = cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME);
		ValueWrapper wrapper = null;
		if(cache!=null){
			wrapper = cache.get(WeixinCacheUtils.JSAPI_TICKET_KEY);
		}
		if(wrapper == null) {//缓存中无ticketCache缓存
			jsapiTicket = WeixinCacheUtils.createJsapiTicketPutCache();
		}else {
			TokenCacheDto tokenCacheDto = (TokenCacheDto) wrapper.get();
			Date createTime = tokenCacheDto.getCreateTime();
			int expiresIn = tokenCacheDto.getExpiresIn();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(createTime);
			calendar.add(Calendar.SECOND, expiresIn-300);
			//jsapi_ticket创建时间+有效时长得出TICKET的失效时间(access_token有效时长手动减了5分钟,防止在有效时间临界点时出问题)
			Date invalidTime = calendar.getTime();
			//若当前时间小于TICKET的失效时间,则重新获取access_token
			if(new Date().after(invalidTime)) {
				jsapiTicket = WeixinCacheUtils.createJsapiTicketPutCache();
			}else {		
				jsapiTicket = tokenCacheDto.getToken();
			}			
		}		
		return jsapiTicket;
	}
	
	/**
	 * 调用微信接口获取jsapiTicket,并放入缓存中
	 * @return
	 */
	private static String createJsapiTicketPutCache() {
		String accessToken=WeixinCacheUtils.getAccessToken();
		JsapiTicket token = WeixinUtils.getJsapiTicket(accessToken);
		TokenCacheDto newCacheDto = new TokenCacheDto();
		newCacheDto.setCreateTime(new Date());
		newCacheDto.setToken(token.getTicket());
		newCacheDto.setExpiresIn(token.getExpiresIn());
		cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME).put(WeixinCacheUtils.JSAPI_TICKET_KEY, newCacheDto);
		return token.getTicket();
	}
	
//	/**
//	 * 从 缓存中获取网点信息,如果缓存没有从数据库查询出来并放入缓存
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static List<Map<String,Object>> getWdcxList() {
//		Cache cache = cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME);
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		ValueWrapper wrapper = cache.get(WeixinCacheUtils.WD_CACHE_KEY);
//		if(wrapper == null) {//缓存中无tokenCache缓存
//			list = WeixinCacheUtils.getWdListPutCache();
//		}else {
//			list = (List<Map<String,Object>>) wrapper.get();			
//		}		
//		return list;
//	}
	
//	private static List<Map<String,Object>> getWdListPutCache() {
//		List<Map<String,Object>> list = fjwdDao.getAllWangdianInfo();
//		cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME).put(WeixinCacheUtils.WD_CACHE_KEY, list);
//		return list;
//	}
	
//	/**
//	 * 从 缓存中获取我的取款订单信息,如果缓存没有从数据库查询出来并放入缓存
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static List<Map<String,Object>> getWdddList(String openid){
//		Cache cache = cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME);
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		ValueWrapper wrapper = cache.get(WeixinCacheUtils.QKDD_CACHE_KEY); 
//		if(wrapper == null) {//缓存中无tokenCache缓存
//			list = WeixinCacheUtils.getQkddListPutCache(openid);
//		}else {
//			list = (List<Map<String,Object>>) wrapper.get();			
//		}		
//		return list;
//	}
	
//	private static List<Map<String,Object>> getQkddListPutCache(String openid){
//		List<Map<String,Object>> list = qkyyDao.getWdqkdd(openid); //TODO
//		cacheManager.getCache(WeixinCacheUtils.WX_CACHE_NAME).put(WeixinCacheUtils.QKDD_CACHE_KEY, list);
//		return list;
//	}
	

}
