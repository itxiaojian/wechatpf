package com.sliu.framework.app.wfw.controller;

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
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsSscxService;

/**
 * 
 * 
 @Author oufeng
 * @Date 2015年6月30日 下午3:07:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/wfw/ZsSscx")
public class ZsSscxController extends BaseController {
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsSscxController.class);

	@Autowired
	private ZsSscxService service;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	/**
	 * 
	 * 宿舍查询
	 @Author oufeng
	 * @Date 2015年6月30日 下午3:07:30
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toSscx")
	public ModelAndView toSscx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsSscxList");
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
		List<Map<String, Object>> list = service.getSscxList(request);
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		}
		if (list.size() != 0) {
			mav.addObject("list", list);
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
	 * 
	 * 宿舍查询
	 @Author oufeng
	 * @Date 2015年6月30日 下午3:07:30
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toSscxZj")
	@ResponseBody
	public List<Map<String, Object>> toSscxZj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsSscxList");
		List<Map<String, Object>> list = service.getSscxList(request);
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
		if (list.size() != 0) {
			mav.addObject("list", list);
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
		
		return list;
	}
	
	
}
