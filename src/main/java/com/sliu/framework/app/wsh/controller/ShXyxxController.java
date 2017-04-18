package com.sliu.framework.app.wsh.controller;

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
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.service.ShXyxxService;

/**
 * 校友信息
 * @author duanpeijun
 * @date  2015年7月7日
 */
@Controller
@RequestMapping("/wsh/ShXyxx")
public class ShXyxxController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShXyxxController.class);

	@Autowired
	private ShXyxxService shXyxxService;
	
	/**
	 * 校友信息
	 * @author duanpeijun
	 * @date  2015年7月7日
	 * @param request
	 * @param response
	 * @param openId 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toXyxx")
	public ModelAndView toSscx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShXyxx/shXyxxList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		//String openId = AppUtil.getOpenId(request, response);
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = shXyxxService.getXyxxList(request,code);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		mav.addObject("tj", code);
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("size",list.size());
		}
		return mav;
	}
	
	/**
	 * 校友信息加载更多
	 * @author zhangyan
	 * @date 2016年9月7日 下午3:30:42
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/xyjzgd")
	@ResponseBody
	public List<Map<String,Object>> xyjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/wsh/ShXyxx/shXyxxList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String tj = request.getParameter("tj");
//	    code = new String(code.getBytes("ISO-8859-1"), "UTF-8"); 
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = shXyxxService.getXyxxList(request,tj);
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}else{
			if (list.size() != 0) {
				mav.addObject("list", list);
		}
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("size",list.size());
		}
		return list;
		
	}
}
