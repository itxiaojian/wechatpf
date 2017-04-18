//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.dto.WeiXinOauth2Token;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.wfw.service.WfwZyService;

/**
 * 进入生活主页
 * 
 * @author duanpeijun
 * @date  2015年7月2日
 */
@Controller
@RequestMapping("/wsh/zy")
public class WshZyController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WshZyController.class);

	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private WfwZyService wfzyservice;
	/**
	 * 
	 * @author duanpeijun
	 * @date  2015年7月2日
	 * @param request
	 * @param response
	 * @param openId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/zhuye")
	public ModelAndView zhuye(HttpServletRequest request,
			HttpServletResponse response, String openId)
			throws UnsupportedEncodingException {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		
		List<Map<String,Object>> list=wfzyservice.getSwzlList("1");
		modelAndView.addObject("swzl",list);
		
		if (openId == null || "".equalsIgnoreCase(openId)) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String code = request.getParameter("code"); // 获取code
			if (openId == null || "".equalsIgnoreCase(openId)) {
			openId = "";
			}

			if (code == null || "".equalsIgnoreCase(code)) {
				url = "/error/wxd";
			} else {
				if (!"authdeny".equals(code)) {
					WeiXinOauth2Token weiXinOauth2Token = WeixinUtils
							.getOauth2AccessToken(code);// 根据code获取 页面授权信息类
					if(weiXinOauth2Token != null){
						openId = weiXinOauth2Token.getOpenId();// 获取当前微信用户的openId
	                 }else{
	                	 String path = request.getContextPath();
		             	 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
	          		     String result3 = basePath+"/wsh/zy/zhuye";//进入微生活

		                 try {
	                          result3 = java.net.URLEncoder.encode(result3,"utf-8");
		                 } catch (UnsupportedEncodingException e) {
	                          e.printStackTrace();
		                 }
	                  
		                 String url3 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result3+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

	                	 url = "/wsh/zhuyetz";
	                	 modelAndView.addObject("newurl", url3);
	                	 modelAndView.setViewName(url);
	                	 return modelAndView;
	                 }
				}
				/*String accessToken = WeixinCacheUtils.getAccessToken();// 获取微信公众号的accessToken
				WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(accessToken,
						openId);// 根据openId和公众号Token获取关注用户的基本信息
				modelAndView.addObject("openId", openId);*/
				List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
//				List<Map<String,Object>> user = sysWxyhDao.getUser("admin");
				if(user.size()!=0){
					List<Map<String, Object>> yhjs = sysWxyhDao.getJs((user.get(0).get("yhid") + "").trim());
//					String jsmc = (yhjs.get(0).get("jsmc") + "").trim();
//					List<Map<String, Object>> listmenu = wfzyservice.getListMenu("3",jsmc);
//					modelAndView.addObject("listmenu", listmenu);
					String jsmc = "";
					if(yhjs.size()>0){
						for(Map<String, Object> jsmcstr:yhjs){
							if(jsmc.equalsIgnoreCase("")){
								jsmc = "'"+(jsmcstr.get("jsmc")+"").trim()+"'";
							}else{
								jsmc = jsmc +",'"+(jsmcstr.get("jsmc")+"").trim()+"'";
							}
						}
						List<Map<String, Object>> listmenu = wfzyservice.getListAllMenu("3",jsmc);
						modelAndView.addObject("listmenu", listmenu);
						modelAndView.addObject("menusize", listmenu.size());
					}
					
					url = "/wsh/zhuye";
				}else{
					url="/sys/SysWxyh/wxbd";
					modelAndView.addObject("url","/wsh/zy/zhuye");
				}
			}
		}else{
			List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
//			List<Map<String,Object>> user = sysWxyhDao.getUser("admin");
			if(user.size()!=0){
				List<Map<String, Object>> yhjs = sysWxyhDao.getJs((user.get(0).get("yhid") + "").trim());
//				String jsmc = (yhjs.get(0).get("jsmc") + "").trim();
//				List<Map<String, Object>> listmenu = wfzyservice.getListMenu("3",jsmc);
//				modelAndView.addObject("listmenu", listmenu);
				String jsmc = "";
				if(yhjs.size()>0){
					for(Map<String, Object> jsmcstr:yhjs){
						if(jsmc.equalsIgnoreCase("")){
							jsmc = "'"+(jsmcstr.get("jsmc")+"").trim()+"'";
						}else{
							jsmc = jsmc +",'"+(jsmcstr.get("jsmc")+"").trim()+"'";
						}
					}
					List<Map<String, Object>> listmenu = wfzyservice.getListAllMenu("3",jsmc);
					modelAndView.addObject("listmenu", listmenu);
					modelAndView.addObject("menusize", listmenu.size());
				}
				url = "/wsh/zhuye";
			}else{
				url="/sys/SysWxyh/wxbd";
				modelAndView.addObject("url","/wsh/zy/zhuye");
			}
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
//		List<Map<String,Object>> user = sysWxyhDao.getUser("admin");
		if(user.size()!=0){
			url = "/wsh/zhuye";
		}else{
			url="/sys/SysWxyh/wxbd";
			modelAndView.addObject("url","/wsh/zy/zhuye");
		}
		modelAndView.addObject("openId", openId);
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 后台主页
	 * @author liujiansen
	 * @date 2015年8月12日
	 * @param request
	 * @param response
	 * @param openId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/zhuyeHt")
	public ModelAndView zhuyeHt(HttpServletRequest request,
			HttpServletResponse response, String openId)
			throws UnsupportedEncodingException {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		if (openId == null || "".equalsIgnoreCase(openId)) {
			openId = "";
		}
		url = "/wsh/zhuye";
		modelAndView.addObject("openId", openId);
		modelAndView.setViewName(url);
		return modelAndView;
	}
}
