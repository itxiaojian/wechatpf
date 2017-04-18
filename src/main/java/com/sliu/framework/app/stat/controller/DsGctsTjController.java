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
import com.sliu.framework.app.stat.service.DsGctsTjService;

@Controller
@RequestMapping("/stat/echarts")
public class DsGctsTjController extends BaseController {
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(DsGctsTjController.class);

	@Autowired
	private DsGctsTjService service;

	/**
	 * 
	 * 
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年7月22日 下午3:09:10
	 * @Version 1.0
	 */
	/* 图书分类页面 */
	@RequestMapping(value = "/library")
	public ModelAndView toStudentStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/gctsTj");
		List<Map<String, Object>> list = service.getData(request, response);
		List<Map<String, Object>> listBm = service.getTszl(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/* 图书分类页面 */
	@RequestMapping(value = "/todatalib")
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
	@RequestMapping(value = "/getdatalib")
	@ResponseBody
	public List<Map<String, Object>> toGetData(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = service.getData(request, response);
		return list;
	}
	
	/**
	 * 教职工的所借图书统计
	 @throws UnsupportedEncodingException 
	 *@Author oufeng
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/jszj")
	public ModelAndView toZygkStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/zjtsTeaTj");
		String pages = request.getParameter("pages");
		String qh = request.getParameter("qh");
		String openId = request.getParameter("openId");
		if(pages==null || "".equals(pages)){
			pages="1";
		}
		List<Map<String, Object>> list = service.getTsData(openId,qh,pages);
		mav.addObject("list",list);
		mav.addObject("qh",qh);
		mav.addObject("pages",pages);
		mav.addObject("openId",openId);
		return mav;
	}
	
	
	
	/**
	 * 教职工所借图书统计
	 @throws UnsupportedEncodingException 
	 *@Author oufeng
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/jszjZj")
	@ResponseBody
	public List<Map<String, Object>> toZygkZjStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/zjtsTeaTj");
		String pages = request.getParameter("pages");
		String qh = request.getParameter("qh");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = service.getTsData(openId,qh,pages);
		if(pages==null || "".equals(pages)){
			pages="1";
		}
		mav.addObject("list",list);
		mav.addObject("qh",qh);
		mav.addObject("pages",pages);
		mav.addObject("openId",openId);
		return list;
	}
	
	/**
	 * 学生的所借图书统计
	 @throws UnsupportedEncodingException 
	 *@Author oufeng
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/xszj")
	public ModelAndView toXszjStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/zjtsStuTj");
		String pages = request.getParameter("pages");
		String qh = request.getParameter("qh");
		String openId = request.getParameter("openId");
		if(pages==null || "".equals(pages)){
			pages="1";
		}
		List<Map<String, Object>> list = service.getTsStuData(openId,qh,pages);
		mav.addObject("list",list);
		mav.addObject("qh",qh);
		mav.addObject("pages",pages);
		mav.addObject("openId",openId);
		return mav;
	}
	
	
	
	/**
	 * 学生所借图书统计
	 @throws UnsupportedEncodingException 
	 *@Author oufeng
	 *@Date 2016年8月15日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/xszjZj")
	@ResponseBody
	public List<Map<String, Object>> toXszjZjStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/zjtsStuTj");
		String pages = request.getParameter("pages");
		String qh = request.getParameter("qh");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = service.getTsStuData(openId,qh,pages);
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

