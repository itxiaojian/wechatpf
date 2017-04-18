package com.sliu.framework.app.sbgl.contorller;

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
import com.sliu.framework.app.sbgl.service.SbSbtjService;

/**
*设备统计
@Author oufeng	
@Date 2015年8月26日 下午2:45:36
@Version 1.0
*/
@Controller
@RequestMapping("/sbgl/SbSbtj")
public class SbSbtjController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SbSbsgController.class);
	
	@Autowired
	private SbSbtjService sbSbtjService;
	
	/**
	 * 设备类型统计数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getSblxtj")
	@ResponseBody
	public List<Map<String, Object>> toSblxtj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = sbSbtjService.getDataLx(request, response);
		return list;
	}
	
	/**
	 * 设备总资产统计数据
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/getSbzzctj")
	@ResponseBody
	public List<Map<String, Object>> toSbzzctj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = sbSbtjService.getDataZzc(request, response);
		return list;
	}
	
	/**
	 * 设备类型统计数据页面
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toSblxtjPage")
	public ModelAndView toSblxtjYm(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/sbgl/sbtj/sblxtj");
		List<Map<String, Object>> list = sbSbtjService.getData(request, response);
		List<Map<String, Object>> listBm = sbSbtjService.getSblx(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/**
	 * 设备总资产统计数据页面
	 @throws UnsupportedEncodingException 
	 * @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	@RequestMapping(value = "/toSbzzctjPage")
	public ModelAndView toSbzzctjYm(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/sbgl/sbtj/sbzzctj");
		List<Map<String, Object>> list = sbSbtjService.getZzc(request, response);
		List<Map<String, Object>> listBm = sbSbtjService.getBmmc(request, response);
		mav.addObject("list", list);
		mav.addObject("listBm", listBm);
		return mav;
	}
	
	/* 设备类型图表 */
	@RequestMapping(value = "/todataxh")
	@ResponseBody
	public List<Map<String, Object>> toFilterXh(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = sbSbtjService.getData(request, response);
		return list;
	}
	
	/* 设备总资产图表 */
	@RequestMapping(value = "/todatazzc")
	@ResponseBody
	public List<Map<String, Object>> toFilterZzc(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = sbSbtjService.getZzc(request, response);
		return list;
	}
}


