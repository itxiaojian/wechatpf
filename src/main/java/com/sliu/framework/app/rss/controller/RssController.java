package com.sliu.framework.app.rss.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.util.RssUtil;

/**
*
@Author oufeng	
@Date 2015年7月13日 上午11:21:52
@Version 1.0
*/
	@Controller
	@RequestMapping("/rss")
	public class RssController extends BaseController {

		protected final transient Logger logger = LoggerFactory
				.getPresentationLog(RssController.class);

		/*@Autowired
		private ShWhbMbService service;*/
	
		/**
		*综合新闻
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssList")
		public ModelAndView toRssList(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
			ModelAndView mav = new ModelAndView("/rss/rssTest");
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
			mav.addObject("map", ret);
			return mav;
		}
		
		/**
		*通知公告
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssTzgg")
		public ModelAndView toRssTzgg(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
			ModelAndView mav = new ModelAndView("/rss/rssTzgg");
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
			mav.addObject("map", ret);
			return mav;
		}
		
		/**
		*最新公告的正文页面
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/main_ttgg")
		public ModelAndView toMianZxgg(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			ModelAndView mav = new ModelAndView("/rss/rss_ttgg");
			String url = request.getParameter("url");
			String title = request.getParameter("title");
			String time = request.getParameter("time").substring(0, 10);
			String author = request.getParameter("author");
			 String openId = AppUtil.getOpenId(request, response);
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); 
			author= new String(author.getBytes("ISO-8859-1"), "UTF-8"); 
			mav.addObject("url",url);
			mav.addObject("title",title);
			mav.addObject("author",author);
			mav.addObject("time",time);
			mav.addObject("openId", openId);
			return mav;
		}
		
		/**
		*最新公告的正文页面
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/main_xygk")
		public ModelAndView toMianXygk(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			ModelAndView mav = new ModelAndView("/rss/rss_xygk");
			String url = request.getParameter("url");
			String title = request.getParameter("title");
			String time = request.getParameter("time").substring(0, 10);
			String author = request.getParameter("author");
			 String openId = AppUtil.getOpenId(request, response);
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); 
			author= new String(author.getBytes("ISO-8859-1"), "UTF-8"); 
			mav.addObject("url",url);
			mav.addObject("title",title);
			mav.addObject("author",author);
			mav.addObject("time",time);
			mav.addObject("openId", openId);
			return mav;
		}
		
		/**
		*最新公告的正文页面
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/main_xsjl")
		public ModelAndView toMianXsjl(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			ModelAndView mav = new ModelAndView("/rss/rss_xsjl");
			String url = request.getParameter("url");
			String title = request.getParameter("title");
			String time = request.getParameter("time").substring(0, 10);
			String author = request.getParameter("author");
			 String openId = AppUtil.getOpenId(request, response);
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); 
			author= new String(author.getBytes("ISO-8859-1"), "UTF-8"); 
			mav.addObject("url",url);
			mav.addObject("title",title);
			mav.addObject("author",author);
			mav.addObject("time",time);
			mav.addObject("openId", openId);
			return mav;
		}
		
		/**
		*校园快讯
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssXykx")
		public ModelAndView toRssXykx(HttpServletRequest request,
				HttpServletResponse response) throws UnsupportedEncodingException {
			ModelAndView mav = new ModelAndView("/rss/rssXykx");
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
			mav.addObject("map", ret);
			return mav;
		}
		
		/**
		*学术交流
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssXsjl")
		public ModelAndView toRssXsjl(HttpServletRequest request,
				HttpServletResponse response) throws UnsupportedEncodingException {
			ModelAndView mav = new ModelAndView("/rss/rssXsjl");
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
			mav.addObject("map", ret);
			return mav;
		}
		
		@RequestMapping(value = "/rssXyxw")
		public ModelAndView toRssXyxw(HttpServletRequest request,
				HttpServletResponse response) throws UnsupportedEncodingException {
			ModelAndView mav = new ModelAndView("/rss/rssXyxw");
		  /* String openId = request.getParameter("openId");
			if(openId == null || "".equalsIgnoreCase(openId)){
				mav = AppUtil.runWx(openId);
			}else{
			mav.addObject("openId", openId);
			}
			System.out.println("openId------------------------------"+openId);*/
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
			mav.addObject("map", ret);
			return mav;
		}
		
		/**
		*综合新闻正文
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssTest")
		@ResponseBody
		public Object toNews(HttpServletRequest request,
				HttpServletResponse response,String url_rss) throws IOException {
			String msg = RssUtil.downloadXMLFile(url_rss);
			return new ResponseData(true, msg);
		}
		
		/**
		*通知公告正文
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssTzggDetail")
		@ResponseBody
		public Object toTzggNews(String url_rss) throws IOException {
			String msg = RssUtil.downloadXMLFile(url_rss);
			return new ResponseData(true, msg);
		}
		
		/**
		*校园快讯正文
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssXykxDetail")
		@ResponseBody
		public Object toXykxNews(String url_rss) throws IOException {
			String msg = RssUtil.downloadXMLFile(url_rss);
			return new ResponseData(true, msg);
		}
		
		/**
		*学术交流正文
		@Author oufeng	
		@Date 2015年7月13日 上午11:21:52
		@Version 1.0
		*/
		@RequestMapping(value = "/rssXsjlDetail")
		@ResponseBody
		public Object toXsjlNews(String url_rss) throws IOException {
			String msg = RssUtil.downloadXMLFile(url_rss);
			return new ResponseData(true, msg);
		}
		
		@RequestMapping(value = "/main")
		public ModelAndView toMian(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			ModelAndView mav = new ModelAndView("/rss/rss_main");
			String url = request.getParameter("url");
			String title = request.getParameter("title");
			String time = request.getParameter("time").substring(0, 10);
			String author = request.getParameter("author");
			 String openId = AppUtil.getOpenId(request, response);
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); 
			author= new String(author.getBytes("ISO-8859-1"), "UTF-8"); 
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
			mav.addObject("map", ret);
			
			mav.addObject("url",url);
			mav.addObject("title",title);
			mav.addObject("author",author);
			mav.addObject("time",time);
			mav.addObject("openId", openId);
			return mav;
		}
		
		@RequestMapping(value = "/mainList")
		@ResponseBody
		public Object toMainList(String url) throws IOException {
			String msg =  RssUtil.downloadXMLFile(url);
			return new ResponseData(true, msg);
		}
		
		/**
		 * 点击学院新闻出现rss的列表
		 * @author   oufeng
		 * @version 创建时间：2016年1月18日  
		 * @return
		 */
		@RequestMapping(value = "/torsspage")
		@ResponseBody
		public ModelAndView toRssPage(HttpServletRequest request,HttpServletResponse response,String openId){
			ModelAndView modelAndView = new ModelAndView( "/wzy/zyRssList");
	     	//List<Map<String, Object>> list = zyXyxwService.getXyxwList();
			//modelAndView.addObject("list", list);
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
			/*for (Map.Entry entry : ret.entrySet()) {
			    System.out.println(entry.getKey() + ", " + entry.getValue());
			}*/
			modelAndView.addObject("map", ret);
			modelAndView.addObject("openId",openId);
			return modelAndView;
		}
	}