//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sys.controller;

import java.io.UnsupportedEncodingException;
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
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.dto.WeiXinOauth2Token;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.bo.SysWxyhBO;
import com.sliu.framework.app.util.ResponseData;

/**
 * 微信用户
 * @author liujiasen
 * @date 2015年5月18日
 */
@Controller
@RequestMapping("/sys/SysWxyh")
public class SysWxyhController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SysWxyhController.class);
	
	@Autowired
	private SysWxyhBO sysWxyhBO;
	
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(String username,String password,String openId){
		String str=sysWxyhBO.save(username,password,openId);
		return str;
	}
	
	@RequestMapping(value="/wxbdPage")
	public ModelAndView wxbdPage(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		String uri=request.getRequestURI();
		System.out.println(uri+"------------------------------------");
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String code = request.getParameter("code"); //获取code
		String openId ="";
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		 
		if(code == null || "".equalsIgnoreCase(code)){
			url = "/error/wxd";
		}else{
        if (!"authdeny".equals(code)) {
                WeiXinOauth2Token weiXinOauth2Token = WeixinUtils.getOauth2AccessToken(code);//根据code获取 页面授权信息类
                openId = weiXinOauth2Token.getOpenId();//获取当前微信用户的openId
        }
		modelAndView.addObject("openId",openId);
		url = "/sys/SysWxyh/wxbd";
		}
		modelAndView.setViewName(url);
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
        modelAndView.addObject("map", ret);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/wxyhPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sys/SysWxyh/SysWxyh");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getWxyhList")
	@ResponseBody
	public Pagination<Map<String,Object>> getWxyhList(Integer start, Integer limit,String xm,String wxh) {
		Pagination<Map<String,Object>> list = sysWxyhBO.getWxyhList(start, limit,xm,wxh);
		return list;
	}

	@RequestMapping(value="/update")
	@ResponseBody
	public ResponseData update(String id,String zt){
		sysWxyhBO.update(id,zt);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResponseData delete(String[] ids){
		sysWxyhBO.delete(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
}
