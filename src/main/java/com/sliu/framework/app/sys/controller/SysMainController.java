package com.sliu.framework.app.sys.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.dto.WeiXinOauth2Token;
import com.sliu.framework.app.support.weixin.WeixinUtils;

/** 
 * @author:zhangyi 
 * @version 创建时间：2016年7月1日 上午10:59:08 
 * 首页内容模块展示
 */
@Controller
@RequestMapping("/sys/main")
public class SysMainController extends BaseController {
	
	@RequestMapping(value="/mainpage")
	public ModelAndView wxbdPage(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/sys/main/index";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
}
