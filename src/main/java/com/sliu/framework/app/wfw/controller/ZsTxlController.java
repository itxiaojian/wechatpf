package com.sliu.framework.app.wfw.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsTxlService;


/**
 * 主页--   通讯录
 * @author wangxiangyang
 * @version 创建时间：2016年6月12日
 */

@Controller
@RequestMapping("/wfw/ZsTxl")
public class ZsTxlController extends BaseController {
	
	/*protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsTxlController.class);*/
	
	@Autowired
	private ZsTxlService zsTxlService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 *  跳转到通讯录页面
	 * @author   wangxiangyang
	 * @version 创建时间：2016年6月12日
	 * @return
	 */
	@RequestMapping(value = "/zsTxlDetail")
	public ModelAndView zsTxlDetail(HttpServletRequest request) {
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			mav=new ModelAndView("/wfw/zsTxlDetail");
	   		List<Map<String,Object>> list=zsTxlService.getTxlList1(openId);
	   		mav.addObject("map1", list);
	   		mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsTxl/zsTxlDetail");
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
//     for (Map.Entry entry : ret.entrySet()) {
//         System.out.println(entry.getKey() + ", " + entry.getValue());
//     }
     mav.addObject("map", ret);
		return mav;
	}
	
	/**
	 *  查找某个人的通讯录页面
	 * @author   wangxiangyang
	 * @version 创建时间：2016年6月12日
	 * @return
	 */
	@RequestMapping(value = "/zsTxlDetail1")
	public ModelAndView zsTxlDetail1(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("/wfw/zsTxlDetail");
		String txlxx =request.getParameter("txlxx");
		String openId =request.getParameter("openId");

		List<Map<String,Object>> list=zsTxlService.getTxlList(txlxx,openId);
		if(list!=null && list.size()!=0){
			mav.addObject("map1", list);
		}

		mav.addObject("openId",openId);
		
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
//     for (Map.Entry entry : ret.entrySet()) {
//         System.out.println(entry.getKey() + ", " + entry.getValue());
//     }
     mav.addObject("map", ret);
		return mav;
	}

}

