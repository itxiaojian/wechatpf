package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
import com.sliu.framework.app.wfw.service.ZsXscxService;



/**
 * 在校学生查询
 * @author oufeng
 * @date 2016年12月20日
 */
@Controller
@RequestMapping("/wfw/ZsXscx")
public class ZsXscxController extends BaseController {
	
	@Autowired
	private ZsXscxService service;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	
	/**
	 * 跳转到学生查询
	 * @author oufeng
	 * @date 2016年12月20日
	 * @return
	 */
	@RequestMapping(value="/toXscx")
   	public ModelAndView toXscj(HttpServletRequest request) 
   			throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		String code =request.getParameter("code");
		if(code!=null){code = new String(code.getBytes("ISO-8859-1"), "UTF-8"); 
   		}else{code="";}
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			mav=new ModelAndView("/wfw/zsXscxList");
			String yhid =user.get(0).get("yhid")+"";
			List<Map<String,Object>> list = service.getXsxx(yhid,code,pages);
			int pagecount = service.getPagecount(yhid,code);
			mav.addObject("openId",openId);
			mav.addObject("list",list);
			mav.addObject("pages", pages);
			mav.addObject("tj",code);
			mav.addObject("pagecount",pagecount);
			mav.addObject("size",list.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXscx/toXscx");
		}
		
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

        // 注意 URL 一定要动态获取，不能 hardcode
        HttpServletRequest httpRequest=(HttpServletRequest)request; 
        String url1 = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()              //端口号  
                + httpRequest.getContextPath()         //项目名称  
                + httpRequest.getServletPath();        //请求页面或其他地址  
            //+ "?" + (httpRequest.getQueryString());  //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
        mav.addObject("map", ret);
		return mav;
   	}

}
