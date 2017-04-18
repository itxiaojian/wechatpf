//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsXsjqfxxService;

/**
 * 学生缴欠费信息
 * 
 * @author liujiasen
 * @date 2015年6月1日
 */
@Controller
@RequestMapping("/wfw/ZsXsjqfxx")
public class ZsXsjqfxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXsjqfxxController.class);
	
	@Autowired
	private ZsXsjqfxxService zsXsjqfxxService;
	
	/**
	 * 学生缴欠费信息
	 * @author duanpeijun
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toXsjqfxx")
	@ResponseBody
	public ModelAndView toXsjqfxx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsXsjqfxxList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		//String openId = AppUtil.getOpenId(request, response);
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = zsXsjqfxxService.getXsjqfxxList(request);
		List<Map<String, Object>> jqflist = zsXsjqfxxService.getXsjqfList(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		if (jqflist.size() != 0) {
			mav.addObject("jqflist", jqflist);
		}
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("size",list.size());
		}
		
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
        mav.addObject("map", ret);
		return mav;
}
	
	/**
	 * 学生缴欠费信息
	 * @author duanpeijun
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toXsjqfxxZj")
	@ResponseBody
	public List<List<Map<String,Object>>> toXsjqfxxZj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsXsjqfxxList");
		List<List<Map<String,Object>>>  newlist=new ArrayList<List<Map<String,Object>>>();
		List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jqflist =new ArrayList<Map<String, Object>>();
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		//String openId = AppUtil.getOpenId(request, response);
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		list = zsXsjqfxxService.getXsjqfxxList(request);
		jqflist = zsXsjqfxxService.getXsjqfList(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		if (jqflist.size() != 0) {
			mav.addObject("jqflist", jqflist);
		}
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("size",list.size());
		}
		newlist.add(list);
		newlist.add(jqflist);
		return newlist;
}
}
