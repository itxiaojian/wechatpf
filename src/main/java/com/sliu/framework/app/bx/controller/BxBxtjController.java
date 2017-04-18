package com.sliu.framework.app.bx.controller;

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
import com.sliu.framework.app.bx.service.BxBxtjService;
import com.sliu.framework.app.sbgl.service.SbSbtjService;

/**
*报修统计
@Author oufeng	
@Date 2015年8月26日 下午2:45:36
@Version 1.0
*/
@Controller
@RequestMapping("/bx/bxtj")
public class BxBxtjController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(BxBxtjController.class);
	
	@Autowired
	private BxBxtjService bxBxtjService;
	
	/**
	 * 报修按楼宇统计数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getBxLytj")
	@ResponseBody
	public List<Map<String, Object>> toBxlytj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = bxBxtjService.getDataLy(request, response);
		return list;
	}
	
	/**
	 * 报修类型数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getBxLxtj")
	@ResponseBody
	public List<Map<String, Object>> toBxLxtj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = bxBxtjService.getDataLx(request, response);
		return list;
	}
	
	/**
	 * 报修按楼宇和状态数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getBxLyzttj")
	@ResponseBody
	public List<Map<String, Object>> toBxlyzt(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = bxBxtjService.getDataLyZt(request, response);
		return list;
	}
	
	/**
	 * 报修按楼宇统计的数据页面
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toBxLytjPage")
	public ModelAndView toSblxtjYm(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/bx/bxtj/bxlytj");
		List<Map<String, Object>> list = bxBxtjService.getLyTab(request, response);
		List<Map<String, Object>> listBm = bxBxtjService.getLyZd(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/**
	 * 报修类型数据页面
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toBxLxtjPage")
	public ModelAndView toBxLxtjPage(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/bx/bxtj/bxlxtj");
		List<Map<String, Object>> list = bxBxtjService.getLxTab(request, response);
		List<Map<String, Object>> listBm = bxBxtjService.getLxZd(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/**
	 * 报修楼宇和状态数据页面
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toBxLyzttjPage")
	public ModelAndView toBxLyzttjPage(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/bx/bxtj/bxluzttj");
		List<Map<String, Object>> list = bxBxtjService.getLyZtTab(request, response);
		List<Map<String, Object>> listBm = bxBxtjService.getLyZd(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/**
	 * 楼宇统计获得tab表格的数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/todataly")
	@ResponseBody
	public List<Map<String, Object>> toFilterLy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		   List<Map<String, Object>> list = bxBxtjService.getLyTab(request, response);
		return list;
	}
	
	/**
	 * 报修类型获得tab表格的数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/todatalx")
	@ResponseBody
	public List<Map<String, Object>> toFilterLx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = bxBxtjService.getLxTab(request, response);
		return list;
	}
	
	/**
	 * 报修按楼宇状态获得tab表格的数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/todatalyzt")
	@ResponseBody
	public List<Map<String, Object>> toFilterLyZt(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = bxBxtjService.getLyZtTab(request, response);
		return list;
	}
}


