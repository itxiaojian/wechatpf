package com.sliu.framework.app.stat.controller;

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
import com.sliu.framework.app.stat.service.DsLxblqkService;

@Controller
@RequestMapping("/stat/echarts")
public class DsLxblqkController extends BaseController {
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(DsLxblqkController.class);

	@Autowired
	private DsLxblqkService service;

	
	/**
	 * 离校办理情况统计
	 @throws UnsupportedEncodingException 
	 *@Author wangxiangyang
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/lxbl")
	@ResponseBody
	public ModelAndView toStudentStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/lxblqkTj");
		String pages = request.getParameter("pages");
		String openId = request.getParameter("openId");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = service.getLxData(request);
		mav.addObject("list", list);
		Integer size = list.size();
		mav.addObject("size", size);
		mav.addObject("openId", openId);
		mav.addObject("pages", pages);
		return mav;
	}
	
	/**
	 * 离校办理情况统计分页
	 @throws UnsupportedEncodingException 
	 *@Author wangxiangyang
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/lxblfy")
	@ResponseBody
	public List<Map<String,Object>> toStudentStat2(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/stat/lxblqkTj");
		String pages = request.getParameter("pages");
		String yxmc1 = request.getParameter("yxmc");
   		String yxmc = new String(yxmc1.getBytes("iso8859-1"),"utf-8");
		String openId = request.getParameter("openId");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = service.getLxData1(yxmc,pages);
		mav.addObject("list", list);
		Integer size = list.size();
		mav.addObject("size", size);
		mav.addObject("openId", openId);
		mav.addObject("pages", pages);
		return list;
	}
	
	/**
	 * 离校办理情况统计
	 @throws UnsupportedEncodingException 
	 *@Author wangxiangyang
	 *@Date 2016年8月16日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/lxbl1")
	public ModelAndView toStudentStat1(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/lxblqkTj");
		String openId = request.getParameter("openId");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String yxmc1 = request.getParameter("yxmc");
   		String yxmc = new String(yxmc1.getBytes("iso8859-1"),"utf-8");
		List<Map<String, Object>> list = service.getLxData1(yxmc,pages);
		mav.addObject("list", list);
		Integer size = list.size();
		mav.addObject("size", size);
		mav.addObject("openId", openId);
		mav.addObject("pages", pages);
		mav.addObject("yxmc", yxmc);
		return mav;
	}
}
