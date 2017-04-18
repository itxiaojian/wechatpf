//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.dto.WeiXinOauth2Token;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wsh.service.ZsSkkqjlbService;

/**
 * 二手市场
 * @author liujiasen
 * @date 2015年6月30日
 */
@Controller
@RequestMapping("/wsh/ZsSkkqjlb")
public class ZsSkkqjlbController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsSkkqjlbController.class);

	@Autowired
	private ZsSkkqjlbService service;
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2016年3月29日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsSkkqjlList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		String id = request.getParameter("id");
		List<Map<String, Object>> list = service.getGoodList(request, response);
		int count=service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("count", count);
		}
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2016年3月29日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		String url = "";
//		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsSkkqbList");
		String openId = request.getParameter("openId");
		String id = request.getParameter("id"); // 获取code
		if (openId == null || "".equalsIgnoreCase(openId)) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String code = request.getParameter("code"); // 获取code
			if (openId == null || "".equalsIgnoreCase(openId)) {
			    openId = "";
			}

			if (code == null || "".equalsIgnoreCase(code)) {
				url = "/error/wxd";
			} else {
				if (!"authdeny".equals(code)) {
					WeiXinOauth2Token weiXinOauth2Token = WeixinUtils
							.getOauth2AccessToken(code);// 根据code获取 页面授权信息类
					
					 if(weiXinOauth2Token != null){
						openId = weiXinOauth2Token.getOpenId();// 获取当前微信用户的openId
					 }
				}
				url="/wsh/shWdm/zsEwmsmjg";
			}
			String str=service.saveMs(id,openId);
			mav.setViewName(url);
			mav.addObject("str", str);
			mav.addObject("openId", openId);
		}else{
			String str=service.saveMs(id,openId);
			mav.setViewName(url);
			mav.addObject("str", str);
			mav.addObject("openId", openId);
		}
		return mav;
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2016年3月29日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toJg")
	public ModelAndView toJg(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsEwmsmjg");
		String openId = request.getParameter("openId");
		mav.addObject("str", "0");
		mav.addObject("openId", openId);
		return mav;
	}
}
