package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsXsttkxxService;

/**
*课表异动
@Author oufeng	
@Date 2016年8月10日 下午15:02:48
@Version 1.0
*/
@Controller
@RequestMapping("/wfw/ZsXsTkxx")
public class ZsXsttkxxController extends BaseController{
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXsttkxxController.class);
	
	@Autowired
	private ZsXsttkxxService service;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 跳转到学生调停课信息页面
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toTkxx")
   	public ModelAndView toTkxx(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		String ksqh =request.getParameter("ksqh");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wfw/zsXsttkxxList");
	   		List<Map<String,Object>> xslist=service.getXsxx(user.get(0).get("yhid").toString());
	   		List<Map<String,Object>> tklist=service.getTkxx(ksqh,user.get(0).get("yhid").toString(),pages);
	   		if(tklist.size()!=0){
	   			mav.addObject("tklist", tklist);
	   		}
	   		if(xslist.size()!=0){
				mav.addObject("xslist", xslist);
			}
	   		mav.addObject("qh", ksqh); 
	   		mav.addObject("openId",openId);
	   		mav.addObject("pages", pages);
			mav.addObject("size",tklist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXsTkxx/toTkxx");
		}
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();
        // 注意 URL 一定要动态获取，不能 hardcode
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
	 * 跳转到学生调停课信息页面
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toTkxxZj")
	@ResponseBody
   	public List<Map<String,Object>> toTkxxZj(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		String ksqh =request.getParameter("ksqh");
		List<Map<String,Object>> tklist=new ArrayList<Map<String,Object>>();
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wfw/zsXsttkxxList");
	   		List<Map<String,Object>> xslist=service.getXsxx(user.get(0).get("yhid").toString());
	   		tklist=service.getTkxx(ksqh,user.get(0).get("yhid").toString(),pages);
	   		if(tklist.size()!=0){
	   			mav.addObject("tklist", tklist);
	   		}
	   		if(xslist.size()!=0){
				mav.addObject("xslist", xslist);
			}
	   		mav.addObject("qh", ksqh); 
	   		mav.addObject("openId",openId);
	   		mav.addObject("pages", pages);
			mav.addObject("size",tklist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXsTkxx/toTkxx");
		}
		return tklist;
   	}
}