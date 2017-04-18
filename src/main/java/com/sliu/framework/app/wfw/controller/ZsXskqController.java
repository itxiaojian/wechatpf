package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsXskqService;

/**
 * 
 * @author chenhui
 * @version 创建时间：2016年8月25日
 */
@Controller
@RequestMapping("/wfw/ZsXskq")
public class ZsXskqController extends BaseController {

	protected final transient Logger logger = LoggerFactory.getPresentationLog(ZsXskqController.class);

	@Autowired
	private ZsXskqService zsXskqService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;

	/**
	 * 学生考勤信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toZsXskqxx")
	@ResponseBody
	public ModelAndView toZsXskqxx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsXskqPage");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
        List<Map<String, Object>> xslist = zsXskqService.getXs(openId);
		List<Map<String, Object>> list = zsXskqService.getZsxskqxxList(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		if (xslist.size() != 0) {
			mav.addObject("xslist", xslist);
		}
			mav.addObject("pages", pages);
			mav.addObject("openId", openId);
			mav.addObject("size",list.size());
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
	/**
	 * 学生考勤信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/Query")
	@ResponseBody
	public ModelAndView Query(Long id,String openId) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/Query");
		List<Map<String, Object>> list = zsXskqService.getQuery(id,openId);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		mav.addObject("openId",openId);
		return mav;
	}
	
	/**
	 * 学生考勤信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toFdyDate")
	@ResponseBody
	public List<Map<String, Object>> toFdyDate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsXskqPage");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = zsXskqService.getZsxskqxxList(request);
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("openId", openId);
		return list;	
}
	
	/**
	 * 学生考勤信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toFdyDate1")
	@ResponseBody
	public List<Map<String, Object>> toFdyDate1(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsXskqxxfdy");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String bjmc = request.getParameter("bjmc");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = zsXskqService.getXskqyjList(bjmc,request);
		    mav.addObject("bjmc", bjmc);
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("openId", openId);
		return list;	
}

	/**
	 * 辅导员查看学生考勤信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toZsXskqxxfdy")
	@ResponseBody
	public ModelAndView toZsXskqxxfdy(String classname,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/ZsXskqxxfdy");
		String openId = request.getParameter("openId");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String bjmc = "";
		if(classname !=null && !"".equalsIgnoreCase(classname)){
			bjmc = classname;
		}
		bjmc  = new String(bjmc.getBytes("ISO-8859-1"),"UTF-8");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = zsXskqService.getXskqyjList(bjmc,request);
		List<Map<String, Object>> bjs = zsXskqService.getBj(openId);
		if (bjs.size() != 0) {
			mav.addObject("bjs", bjs);
		}
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		    mav.addObject("bjmc", bjmc);
			mav.addObject("pages", pages);
			mav.addObject("openId", openId);
			mav.addObject("size",list.size());
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
	
	/**
	 * 学生考勤信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toZsxskqxxDetail")
	@ResponseBody
	public ModelAndView toZsxsjzdxxDetail(String xsxh,String openId)  {
		ModelAndView mav=new ModelAndView("/wfw/zsXskqPage");
		
   	            List<Map<String, Object>> xslist = zsXskqService.getXsxx(xsxh);
				List<Map<String, Object>> list = zsXskqService.getxskqxxListDetail(xsxh);
				if(xslist.size()!=0){
		   			mav.addObject("xslist", xslist);
		   		}
		   		if(list.size()!=0){
		   			mav.addObject("list", list);
		   		}
		   		mav.addObject("openId",openId);
		   		return mav;		
		}
	
	/**
	 * 根据字典种类找出字典
	   @author: chenhui 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/sfkq", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return zsXskqService.getDicByLx("sfkq");
	}
}
