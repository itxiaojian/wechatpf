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
import com.sliu.framework.app.stat.service.DsZxxsTjService;

@Controller
@RequestMapping("/stat/echarts")
public class DsZxxsTjController extends BaseController {
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(DsZxxsTjController.class);

	@Autowired
	private DsZxxsTjService service;

	/**
	 * 
	 * 
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年7月22日 下午3:09:10
	 * @Version 1.0
	 */
	/* 学生统计页面 */
	@RequestMapping(value = "/student")
	public ModelAndView toStudentStat(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/stat/zxxsTj");
		List<Map<String, Object>> list = service.getData(request, response);
		List<Map<String, Object>> listBm = service.getBmmc(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/* 学生统计页面 */
	@RequestMapping(value = "/tofilter")
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
	@RequestMapping(value = "/getdata")
	@ResponseBody
	public List<Map<String, Object>> toGetData(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = service.getData(request, response);
		return list;
	}
}
