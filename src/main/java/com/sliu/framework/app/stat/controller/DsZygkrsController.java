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
import com.sliu.framework.app.stat.service.DsZygkrsService;


@Controller
@RequestMapping("/stat/echarts")
public class DsZygkrsController extends BaseController {
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(DsZygkrsController.class);
	
	@Autowired
	private DsZygkrsService service;
	
	
	/**
	 * 各专业挂科人数统计
	 @throws UnsupportedEncodingException 
	 *@Author oufeng
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/zygk")
	public ModelAndView toZygkStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/zygkrsTj");
		String pages = request.getParameter("pages");
		String qh = request.getParameter("qh");
		if(qh!=null){
			qh = new String(qh.getBytes("ISO-8859-1"), "UTF-8"); 
		}else{qh="";}
		String openId = request.getParameter("openId");
		if(pages==null || "".equals(pages)){
			pages="1";
		}
		List<Map<String, Object>> list = service.getGkData(openId,qh,pages);
		mav.addObject("list",list);
		mav.addObject("qh",qh);
		mav.addObject("pages",pages);
		mav.addObject("openId",openId);
		return mav;
	}
	
	
	
	/**
	 * 各专业挂科人数统计
	 @throws UnsupportedEncodingException 
	 *@Author oufeng
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/zygkZj")
	@ResponseBody
	public List<Map<String, Object>> toZygkZjStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/zygkrsTj");
		String pages = request.getParameter("pages");
		String qh = request.getParameter("qh");
		if(qh!=null){
			qh = new String(qh.getBytes("ISO-8859-1"), "UTF-8"); 
		}else{qh="";}
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = service.getGkData(openId,qh,pages);
		if(pages==null || "".equals(pages)){
			pages="1";
		}
		mav.addObject("list",list);
		mav.addObject("qh",qh);
		mav.addObject("pages",pages);
		mav.addObject("openId",openId);
		return list;
	}

}
