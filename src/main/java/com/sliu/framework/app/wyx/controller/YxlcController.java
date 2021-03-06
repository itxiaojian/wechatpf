package com.sliu.framework.app.wyx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wyx.service.YxlcService;

/**
* 离校流程
* @author wangxiangyang
* @date 2016年6月7日
*/
@Controller
@RequestMapping("/wyx/ZsYxlc")
public class YxlcController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(YxlcController.class);
	
	@Autowired
	private YxlcService yxlcService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 跳转到离校流程页面
	 * @author wangxiangyang
	 * @date 2016年8月10日
	 * @return
	 */
	@RequestMapping(value="/toYxlc")
	public ModelAndView toZggzxx(HttpServletRequest request){
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			mav=new ModelAndView("/wsh/YxlcList");
	   		List<Map<String,Object>> list=yxlcService.getGrlx(openId);
	   		List<Map<String,Object>> _list=yxlcService.getGrlx1(openId);
	   		mav.addObject("map1", list);
	   		mav.addObject("map2", _list);
	   		mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wyx/ZsYxlc/toYxlc");
		}
		
		
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

     // 注意 URL 一定要动态获取，不能 hardcode
     //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
     HttpServletRequest httpRequest=(HttpServletRequest)request; 
     String url1 = "http://" + request.getServerName() //服务器地址  
             + ":"   
             + request.getServerPort()           //端口号  
             + httpRequest.getContextPath()      //项目名称  
             + httpRequest.getServletPath()     //请求页面或其他地址  
             + "?" + (httpRequest.getQueryString()); //参数 
     Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//     for (Map.Entry entry : ret.entrySet()) {
//         System.out.println(entry.getKey() + ", " + entry.getValue());
//     }
     mav.addObject("map", ret);
		return mav;
	}
	
	/**
	 * 查看流程详细信息
	 * @author wangxiangyang
	 * @date 2016年8月10日
	 * @return
	 */
	@RequestMapping(value="/toYxlcxx")
	public ModelAndView toZggzxxBysj(HttpServletRequest request){
		ModelAndView mav=new ModelAndView("/wsh/YxlcDetail");
		int tacheid = Integer.parseInt(request.getParameter("tacheid"));
		String openId =request.getParameter("openId");

		List<Map<String,Object>> list=yxlcService.getGrlxxx(tacheid,openId);
		if(list!=null && list.size()!=0){
			mav.addObject("map1", list);
		}

		List<Map<String,Object>> _list=yxlcService.getGrlxxx1(tacheid,openId);
		if(_list!=null && _list.size()!=0){
			mav.addObject("map2", _list);
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
             + httpRequest.getServletPath()  //请求页面或其他地址  
             + "?" + (httpRequest.getQueryString()); //参数 
     Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//     for (Map.Entry entry : ret.entrySet()) {
//         System.out.println(entry.getKey() + ", " + entry.getValue());
//     }
     mav.addObject("map", ret);
		return mav;
	}
}
