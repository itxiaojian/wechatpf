package com.sliu.framework.app.wlx.controller;

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
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wlx.service.WlxLxtxService;


/**
* 离校流程提醒
* @author oufeng
* @date 2016年6月8日
*/
@Controller
@RequestMapping("/wlx/ZsLxTx")
public class WlxLxtxController extends BaseController {
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WlxLxtxController.class);
	
	@Autowired
	private WlxLxtxService lxtxService;
	
	/**
	 *  获取提醒的列表数据
	 * @author   oufeng
	 * @version 2016年6月6日  
	 * @return
	 */
	@RequestMapping(value = "/getTxlist")
	public ModelAndView getTxlist(HttpServletRequest request){
		ModelAndView mav=new ModelAndView("/wlx/lxtxList");
		String openId =request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String,Object>> list = lxtxService.getTxlist(openId);
		mav.addObject("list", list);
		mav.addObject("openId", openId);
		  // 注意 URL 一定要动态获取，不能 hardcode
		 String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();
	     //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
	     HttpServletRequest httpRequest=(HttpServletRequest)request; 
	     String url1 = "http://" + request.getServerName() //服务器地址  
	             + ":"   
	             + request.getServerPort()           //端口号  
	             + httpRequest.getContextPath()      //项目名称  
	             + httpRequest.getServletPath()    //请求页面或其他地址  
	             + "?" + (httpRequest.getQueryString()); //参数 
	     Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//	     for (Map.Entry entry : ret.entrySet()) {
//	         System.out.println(entry.getKey() + ", " + entry.getValue());
//	     }
	     mav.addObject("map", ret);
		return mav;
	}
	
	/**
	 * 查看流程详细信息
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	@RequestMapping(value="/totxDetail")
	public ModelAndView toZggzxxBysj(HttpServletRequest request){
		ModelAndView mav=new ModelAndView("/wlx/lxtxDetail");
		int tacheid = Integer.parseInt(request.getParameter("tacheid"));
		String openId =request.getParameter("openId");

		List<Map<String,Object>> list=lxtxService.getGrlxxx(tacheid,openId);
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
             + httpRequest.getServletPath()    //请求页面或其他地址  
             + "?" + (httpRequest.getQueryString()); //参数 
     Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//     for (Map.Entry entry : ret.entrySet()) {
//         System.out.println(entry.getKey() + ", " + entry.getValue());
//     }
     mav.addObject("map", ret);
		return mav;
	}
}
