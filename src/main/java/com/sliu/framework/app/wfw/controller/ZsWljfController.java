package com.sliu.framework.app.wfw.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsXscjDao;
import com.sliu.framework.app.wfw.service.ZsWljfService;

/**
 * 网络计费
 * @author wangxiangyang
 * @date 2016年8月8日
 */
@Controller
@RequestMapping("/wfw/ZsWljf")
public class ZsWljfController {
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsWljfController.class);
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	@Autowired
	ZsWljfService zsWljfService;

	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 网络计费信息
	 * @author wangxiangyang
	 * @date 2016年8月8日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWljf")
	@ResponseBody
	public ModelAndView toWljf(HttpServletRequest request){
		ModelAndView mav=null;
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		List<Map<String,Object>> yh1=dao.getYh(user.get(0).get("yhid")+"");
		String js=(yh1.get(0).get("jsmc")+"").trim();
		if(user.size()!=0){
	   		mav=new ModelAndView("/wsh/zswljfList");
	   		List<Map<String,Object>> Wljflist=zsWljfService.getWljfList(openId,pages);
	   		if(Wljflist.size()!=0){
	   			mav.addObject("Wljflist", Wljflist);
	   		}
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
	   		mav.addObject("openId",openId);
	   		mav.addObject("js",js);
	   		mav.addObject("pages", pages);
			mav.addObject("size",Wljflist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsWljf/toWljf");
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
	 * 网络计费详细信息
	 * @author duanpeijun
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWljfxx")
	@ResponseBody
	public ModelAndView toWljfxx(HttpServletRequest request){
		ModelAndView mav=null;
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String zh = request.getParameter("zh");
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wsh/zswljfDetail");
	   		List<Map<String,Object>> Wljflistxx=zsWljfService.getWljfListxx(openId,zh);
	   		if(Wljflistxx.size()!=0){
	   			mav.addObject("Wljflistxx", Wljflistxx);
	   		}
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
	   		mav.addObject("openId",openId);
			mav.addObject("size",Wljflistxx.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsWljf/toWljf");
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

}
