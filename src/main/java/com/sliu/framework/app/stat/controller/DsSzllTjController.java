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
import com.sliu.framework.app.stat.service.DsSzllTjService;

@Controller
@RequestMapping("/stat/echarts")
public class DsSzllTjController extends BaseController {
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(DsSzllTjController.class);

	@Autowired
	private DsSzllTjService service;

	/**
	 * 
	 * 
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年7月22日 下午3:09:10
	 * @Version 1.0
	 */
	/* 师资力量页面 */
	@RequestMapping(value = "/teacher")
	public ModelAndView toStudentStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/szllTj");
		List<Map<String, Object>> list = service.getData(request, response);
		List<Map<String, Object>> listBm = service.getBmmc(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/**
	 * 
	 * 
	 @throws UnsupportedEncodingException 
	 * @Author wangxiangyang
	 * @Date 2016年8月17日
	 * @Version 1.0
	 */
	/* 师资力量页面 */
	@RequestMapping(value = "/teacher1")
	public ModelAndView toStudentStat1(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/szllTj1");
		String pages = request.getParameter("pages");
		String openId = request.getParameter("openId");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = service.getData1(request);
		mav.addObject("openId", openId);
		mav.addObject("list", list);
		Integer size = list.size();
		mav.addObject("size", size);
		mav.addObject("pages", pages);
		return mav;
	}
	
	/* 师资力量页面(选择框) */
	@RequestMapping(value = "/data")
	@ResponseBody
	public List<Map<String, Object>> toFilter(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		//ModelAndView mav = new ModelAndView("/stat/student");
		List<Map<String, Object>> list = service.getData(request, response);
		//List<Map<String, Object>> listBm = service.getBmmc(request, response);
		/*mav.addObject("list", list);
		mav.addObject("listBm", listBm);*/
		return list;
	}

	/**
	 * 获得页面的图表数据
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping(value = "/getdatatea")
	@ResponseBody
	public List<Map<String, Object>> toGetData(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = service.getData(request, response);
		return list;
	}
	
	/**
	 * 师资力量统计分页
	 @throws UnsupportedEncodingException 
	 *@Author wangxiangyang
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/data1")
	@ResponseBody
	public List<Map<String,Object>> toStudentStat2(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/stat/szllTj1");
		String pages = request.getParameter("pages");
		String openId = request.getParameter("openId");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = service.getData1(request);
		mav.addObject("list", list);
		mav.addObject("openId", openId);
		Integer size = list.size();
		mav.addObject("size", size);
		mav.addObject("pages", pages);
		return list;
	}
	
	/**
	 * 师资力量统计分页
	 @throws UnsupportedEncodingException 
	 *@Author wangxiangyang
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	/* 师资力量页面(选择框) */
	@RequestMapping(value = "/data2")
	@ResponseBody
	public ModelAndView toStudentStat3(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/szllTj1");
		String pages = request.getParameter("pages");
		String openId = request.getParameter("openId");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = service.getData2(request);
		mav.addObject("openId", openId);
		mav.addObject("list", list);
		Integer size = list.size();
		mav.addObject("size", size);
		mav.addObject("pages", pages);
		return mav;
	}
}
