package com.sliu.framework.app.xzxx.controller;


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
import com.sliu.framework.app.xzxx.service.XxXjxxbtjService;

/**
*校长邮箱-数据统计
@Author oufeng	
@Date 2015年9月2日 
@Version 1.0
*/
@Controller
@RequestMapping("/xzxx/XxXjxxb")
public class XxXjxxbtjController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(XxXjxxbtjController.class);
	
	@Autowired
	private XxXjxxbtjService XxXjxxbtjService;
	
	/**
	 * 校长邮箱统计
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年9月2日
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toXxXjxxbtjPage")
	public ModelAndView toXzyxzttjPage(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/xzxx/xzXxtj");
		List<Map<String, Object>> list = XxXjxxbtjService.getData(request, response);
		List<Map<String, Object>> listBm = XxXjxxbtjService.getBmmc();
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/**
	 * 校长邮箱统计的图标数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年9月2日
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getXxXjxxtj")
	@ResponseBody
	public List<Map<String, Object>> toBxlyzt(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = XxXjxxbtjService.getDataXxXjxxtj(request, response);
		return list;
	}
	
	/**
	 * 校长邮箱统计的图标数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年9月2日
	 * @Version 1.0
	 */
	@RequestMapping(value = "/todataxztj")
	@ResponseBody
	public List<Map<String, Object>> toXztj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = XxXjxxbtjService.getData(request, response);
		return list;
	}
	
	/**
	 * 校长满意度的统计
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年9月2日
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toXzyxmydPage")
	public ModelAndView toBxLyzttjPage(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/xzxx/xzXxmydtj");
		List<Map<String, Object>> list = XxXjxxbtjService.getXzyxmydt(request, response);
		List<Map<String, Object>> listBm = XxXjxxbtjService.getBmmc();
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/**
	 * 校长邮箱满意度统计的图表数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年9月2日
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getXzyxmydtjt")
	@ResponseBody
	public List<Map<String, Object>> toXzyxmydt(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = XxXjxxbtjService.getXzyxmydt(request, response);
		return list;
	}
	
	/**
	 * 校长邮箱满意度统计的table
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年9月2日
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getXzyxmydtjb")
	@ResponseBody
	public List<Map<String, Object>> toXzyxmydb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = XxXjxxbtjService.getXzyxmydb(request, response);
		return list;
	}
}


