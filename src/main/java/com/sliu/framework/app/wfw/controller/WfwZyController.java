//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.dto.WeiXinOauth2Token;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.DESUtil;
import com.sliu.framework.app.wfw.service.WfwZyService;
import com.sliu.framework.app.wtx.service.TxLssktxService;
import com.sliu.framework.app.wtx.service.TxTshstxService;
import com.sliu.framework.app.wtx.service.TxXssktxService;
import com.sliu.framework.app.wtx.service.TxYktdexftxService;
import com.sliu.framework.app.wtx.service.TxYktxfjltxService;
import com.sliu.framework.app.xzxx.service.XxXjxxbService;


/**
 * 进入微服务主页
 * 
 * @author : zhangyi
 * @version 创建时间：2015年6月11日 下午1:49:00
 */
@Controller
@RequestMapping("/wfw/zy")
public class WfwZyController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WfwZyController.class);
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	@Autowired
	private TxXssktxService txXssktxservice;
	@Autowired
	private TxTshstxService txTshstxservice;
	@Autowired
	private TxYktxfjltxService txYktxfjltxservice;
	@Autowired
	private TxYktdexftxService txYktdexftxservice;
	@Autowired
	private TxLssktxService txLssktxservice;
	@Autowired
	private XxXjxxbService xxXjxxbservice;
	@Autowired
	private WfwZyService wfzyservice;
	@Autowired
	private SysYhBO yhBo;

	/**
	 * 
	 * @author zhangyi
	 * @version 创建时间：2015年6月3日 下午4:04:51
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/zhuye")
	public ModelAndView zhuye(HttpServletRequest request,HttpServletResponse response, String openId) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		List<Map<String, Object>> xjxxb = xxXjxxbservice.getXjxxb(openId);
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
					WeiXinOauth2Token weiXinOauth2Token = WeixinUtils.getOauth2AccessToken(code);// 根据code获取 页面授权信息类
					if(weiXinOauth2Token != null){
						openId = weiXinOauth2Token.getOpenId();// 获取当前微信用户的openId
	                }else{
	                	String path = request.getContextPath();
		             	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
	          		    String result2 = basePath+"/wfw/zy/zhuye";//进入微服务
		                try {
		                      result2 = java.net.URLEncoder.encode(result2,"utf-8");
		                } catch (UnsupportedEncodingException e) {
		                      e.printStackTrace();
		                }
		                String url2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result2+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	                	url = "/wfw/zhuyetz";
	                	modelAndView.addObject("newurl", url2);
	                	modelAndView.setViewName(url);
	                	return modelAndView;
	                 }
				}
				/*String accessToken = WeixinCacheUtils.getAccessToken();// 获取微信公众号的accessToken
				WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(accessToken,
						openId);// 根据openId和公众号Token获取关注用户的基本信息
				modelAndView.addObject("openId", openId);*/
				List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
				if(user.size()!=0){
					List<Map<String,Object>> yhjs=sysWxyhDao.getJs((user.get(0).get("yhid")+"").trim());
					//String jsmc=(yhjs.get(0).get("jsmc")+"").trim();
					String jsmc = "";
					if(yhjs.size()>0){
						for(Map<String, Object> jsmcstr:yhjs){
							if(jsmc.equalsIgnoreCase("")){
								jsmc = "'"+(jsmcstr.get("jsmc")+"").trim()+"'";
							}else{
								jsmc = jsmc +",'"+(jsmcstr.get("jsmc")+"").trim()+"'";
							}
						}
						List<Map<String, Object>> listmenu = wfzyservice.getListAllMenu("2",jsmc);
						modelAndView.addObject("listmenu", listmenu);
						modelAndView.addObject("menusize", listmenu.size());
					}
//					List<Map<String, Object>> listmenu = wfzyservice.getListAllMenu("2",jsmc);
//					modelAndView.addObject("listmenu", listmenu);
					String yhid =user.get(0).get("yhid")+"";
					modelAndView.addObject("jsmc", jsmc);
					SysYh yh=yhBo.get(yhid);
					modelAndView.addObject("text", yh.getUsername()+":"+yh.getXm());
					url = "/wfw/zhuye";
				}else{
					List<Map<String,Object>> yh = sysWxyhDao.getBdyh(openId);
					if(yh.size()!=0){
						modelAndView.addObject("yhjy", "Y");
					}
					url="/sys/SysWxyh/wxbd";
					modelAndView.addObject("url","/wfw/zy/zhuye");
				}
			}
		}else{
			//解密openId
//			byte[] results = new sun.misc.BASE64Decoder().decodeBuffer(openId);
//			byte[] decryResult = DESUtil.decrypt(results);
//			openId = new String(decryResult);
			
			List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
			if(user.size()!=0){
				List<Map<String,Object>> yhjs=sysWxyhDao.getJs((user.get(0).get("yhid")+"").trim());
				String jsmc = "";
				if(yhjs.size()>0){
					for(Map<String, Object> jsmcstr:yhjs){
						if(jsmc.equalsIgnoreCase("")){
							jsmc = "'"+(jsmcstr.get("jsmc")+"").trim()+"'";
						}else{
							jsmc = jsmc +",'"+(jsmcstr.get("jsmc")+"").trim()+"'";
						}
					}
					List<Map<String, Object>> listmenu = wfzyservice.getListAllMenu("2",jsmc);
					modelAndView.addObject("listmenu", listmenu);
					modelAndView.addObject("menusize", listmenu.size());
				}
				
				String yhid =user.get(0).get("yhid")+"";
				modelAndView.addObject("jsmc", jsmc);
//				SysYh yh=yhBo.get(yhid);
//				modelAndView.addObject("text", yh.getUsername()+":"+yh.getXm());
				url = "/wfw/zhuye";
				
			}else{
				List<Map<String,Object>> yh = sysWxyhDao.getBdyh(openId);
				if(yh.size()!=0){
					modelAndView.addObject("yhjy", "Y");
				}
				url="/sys/SysWxyh/wxbd";
				modelAndView.addObject("url","/wfw/zy/zhuye");
			}
		}
		//url = "/wfw/zhuye";
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

        // 注意 URL 一定要动态获取，不能 hardcode
        //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
        HttpServletRequest httpRequest=(HttpServletRequest)request; 
        String url1 = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + httpRequest.getContextPath()      //项目名称  
                + httpRequest.getServletPath();      //请求页面或其他地址  
            //+ "?" + (httpRequest.getQueryString()); //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        modelAndView.addObject("map", ret);
        
		//加密openid
//	    byte[] result = DESUtil.encrypt(openId.getBytes());
//	    openId = new sun.misc.BASE64Encoder().encodeBuffer(result);
	    
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("xjxxb", xjxxb);
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
	 * @throws Exception 
	 */
	@RequestMapping(value = "/zhuyeHt")
	public ModelAndView zhuyeHt(HttpServletRequest request,
			HttpServletResponse response, String openId)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		if (openId == null || "".equalsIgnoreCase(openId)) {
			openId = "";
		}else{
			//解密
			byte[] results = new sun.misc.BASE64Decoder().decodeBuffer(openId);
			byte[] decryResult = DESUtil.decrypt(results);
			openId = new String(decryResult);
		}
		List<Map<String, Object>> xjxxb = xxXjxxbservice.getXjxxb(openId);
		String url = "";

//		//加密
//	    byte[] result = DESUtil.encrypt(openId.getBytes());
//	    openId = new sun.misc.BASE64Encoder().encodeBuffer(result);
//	     //解密
//		byte[] results = new sun.misc.BASE64Decoder().decodeBuffer(strin);
//		byte[] decryResult = DESUtil.decrypt(results);
//      String newpassword = new String(decryResult);
	     
		//带openId
		List<Map<String, Object>> user = sysWxyhDao.getWxyh(openId);  
		if (user.size() != 0) {
			List<Map<String, Object>> yhjs = sysWxyhDao.getJs((user.get(0).get("yhid") + "").trim());
			String jsmc = (yhjs.get(0).get("jsmc") + "").trim();
			if ("ROLE_STUDENT".equals(jsmc)) {      // 学生角色
				
				/*学生上课提醒*/
				List<Map<String, Object>> xssktxsl = txXssktxservice.getXssktxsl(openId);
				if(xssktxsl == null){
					modelAndView.addObject("xssktxsl", 0);
				}else{
					modelAndView.addObject("xssktxsl", xssktxsl.size());
				}
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 学生角色
				url = "/wfw/Xs";
			} else if ("ROLE_TEACHER".equals(jsmc)) {      // 老师角色
				
				 /*老师上课提醒*/
				List<Map<String, Object>> lssktxsl = txLssktxservice.getLssktxsl(openId);
				if(lssktxsl == null){
					modelAndView.addObject("lssktxsl", 0);
				}else{
					modelAndView.addObject("lssktxsl", lssktxsl.size());
				}
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 老师角色
				url = "/wfw/Ls"; 
			} else if ("ROLE_INSTRUCTOR".equals(jsmc)) {      // 辅导员角色
				
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 辅导员角色
				url = "/wfw/Fdy";
			} else if ("ROLE_LEADER".equals(jsmc)) {         // 校领导角色
				
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 校领导角色
				url = "/wfw/Xld";
			} else {
				 /*学生上课提醒*/
				List<Map<String, Object>> xssktxsl = txXssktxservice.getXssktxsl(openId);
				if(xssktxsl == null){
					modelAndView.addObject("xssktxsl", 0);
				}else{
					modelAndView.addObject("xssktxsl", xssktxsl.size());
				}
				 /*老师上课提醒*/
				List<Map<String, Object>> lssktxsl = txLssktxservice.getLssktxsl(openId);
				if(lssktxsl == null){
					modelAndView.addObject("lssktxsl", 0);
				}else{
					modelAndView.addObject("lssktxsl", lssktxsl.size());
				}
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 管理员
				url = "/wfw/zhuye";
			}
		} else {
			//不带openId
			SysYh yh=AppUtil.getCurrentUser();
			List<Map<String, Object>> yhjs = sysWxyhDao.getJs(yh.getYhbh());
			String jsmc = (yhjs.get(0).get("jsmc") + "").trim();
			if ("ROLE_STUDENT".equals(jsmc)) {      // 学生角色
				
				 /*学生上课提醒*/
				List<Map<String, Object>> xssktxsl = txXssktxservice.getXssktxsl(openId);
				if(xssktxsl == null){
					modelAndView.addObject("xssktxsl", 0);
				}else{
					modelAndView.addObject("xssktxsl", xssktxsl.size());
				}
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 学生角色
				url = "/wfw/Xs";
			} else if ("ROLE_TEACHER".equals(jsmc)) {     // 老师角色
				
				 /*老师上课提醒*/
				List<Map<String, Object>> lssktxsl = txLssktxservice.getLssktxsl(openId);
				if(lssktxsl == null){
					modelAndView.addObject("lssktxsl", 0);
				}else{
					modelAndView.addObject("lssktxsl", lssktxsl.size());
				}
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 老师角色
				url = "/wfw/Ls";
			} else if ("ROLE_INSTRUCTOR".equals(jsmc)) {       // 辅导员角色
				
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 辅导员角色
				url = "/wfw/Fdy";
			} else if ("ROLE_LEADER".equals(jsmc)) {        // 校领导角色
				
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 校领导角色
				url = "/wfw/Xld";
			} else {
				 /*学生上课提醒*/
				List<Map<String, Object>> xssktxsl = txXssktxservice.getXssktxsl(openId);
				if(xssktxsl == null){
					modelAndView.addObject("xssktxsl", 0);
				}else{
					modelAndView.addObject("xssktxsl", xssktxsl.size());
				}
				 /*老师上课提醒*/
				List<Map<String, Object>> lssktxsl = txLssktxservice.getLssktxsl(openId);
				if(lssktxsl == null){
					modelAndView.addObject("lssktxsl", 0);
				}else{
					modelAndView.addObject("lssktxsl", lssktxsl.size());
				}
				/*图书还书提醒*/
				List<Map<String, Object>> tshstxsl = txTshstxservice.getTshstxsl(openId);
				if(tshstxsl == null){
					modelAndView.addObject("tshstxsl", 0);
				}else{
					modelAndView.addObject("tshstxsl", tshstxsl.size());
				}
				/*一卡通消费记录提醒*/
				List<Map<String, Object>> yktxfjltxsl = txYktxfjltxservice.getYktxfjltxsl(openId);
				if (yktxfjltxsl == null ) {
					modelAndView.addObject("yktxfjltxsl", 0);
				}else{
					modelAndView.addObject("yktxfjltxsl", yktxfjltxsl.size());
				}
				/*一卡通大额消费提醒*/
				List<Map<String, Object>> txYktdexftxsl = txYktdexftxservice.getYktdexftxsl(openId);
				if (txYktdexftxsl == null ) {
					modelAndView.addObject("txYktdexftxsl", 0);
				}else{
					modelAndView.addObject("txYktdexftxsl", txYktdexftxsl.size());
				}
				// 管理员
				url = "/wfw/zhuye";
			}
		}
		
		//加密openid
	    byte[] result = DESUtil.encrypt(openId.getBytes());
	    openId = new sun.misc.BASE64Encoder().encodeBuffer(result);
		
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("xjxxb", xjxxb);
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
/**
	 * 解除绑定
	 * @author oufeng
	 * @version 创建时间：2015年6月3日 下午4:04:51
	 * @return
	 * @throws UnsupportedEncodingException
	 */
@RequestMapping(value = "/jcbd")
@ResponseBody
	public String  jcbd(HttpServletRequest request,	HttpServletResponse response)  {
	ModelAndView modelAndView = new ModelAndView();
		 String openId = request.getParameter("openId");
			if(openId == null || "".equalsIgnoreCase(openId)){
				modelAndView = AppUtil.runWx(openId);
			}
		String result =  wfzyservice.jsbd(openId);
		return result;
 }

/**
 * 建设中页面
 * @author liujiansen
 * @date 2016年3月15日
 * @param request
 * @param response
 * @return
 */
@RequestMapping(value = "/jsym")
public ModelAndView jsym(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView mav = new ModelAndView("/wzy/buidIndex");
	String openId = request.getParameter("openId");
	if (openId == null || "".equalsIgnoreCase(openId)) {
		mav = AppUtil.runWx(openId);
	}else{
		mav.addObject("openId", openId);
	}
	String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

	// 注意 URL 一定要动态获取，不能 hardcode
	// String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	String url1 = "http://" + request.getServerName() // 服务器地址
			+ ":" + request.getServerPort() // 端口号
			+ httpRequest.getContextPath() // 项目名称
			+ httpRequest.getServletPath(); // 请求页面或其他地址
	// + "?" + (httpRequest.getQueryString()); //参数
	Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
	// for (Map.Entry entry : ret.entrySet()) {
	// System.out.println(entry.getKey() + ", " + entry.getValue());
	// }
	mav.addObject("map", ret);
	return mav;
}
	}
