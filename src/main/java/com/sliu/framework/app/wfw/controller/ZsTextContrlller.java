package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsTextService;
import com.sliu.framework.app.wfw.service.ZsYktxxService;
import com.sliu.framework.app.wsh.model.ShSwzl;


@Controller
@RequestMapping("/wfw/ZsText")
public class ZsTextContrlller extends BaseController {
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsTsflController.class);
	
	@Autowired
	private ZsTextService zsTextService;
	
	/**
	 * 失物和招领的初始页面
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toText")
	@ResponseBody
	public ModelAndView toXsdkxxZj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		   ModelAndView mav = new ModelAndView("/wfw/zsText");
		   String openId= request.getParameter("openId");
			if(openId == null || "".equalsIgnoreCase(openId)){
				mav = AppUtil.runWx(openId);
			}
		   mav.addObject("openId",openId);
	       return mav;
	}
	
	/**
	 * 发布失物和招领
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public List<Map<String, Object>> getSwzlxx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = zsTextService.getSwzlxx(request);
	    return list;
	}
	
	/**
	 * 我发布的失物和招领
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mine")
	@ResponseBody
	public List<Map<String, Object>> getMySwzlxx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = zsTextService.getMySwzlxx(request);
	    return list;
	}
	
	/**
	 * 我发布的
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mylost")
	@ResponseBody
	public  ModelAndView  toMyLost(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		 ModelAndView mav = new ModelAndView("/wfw/zsTextSon");
		 String openId = request.getParameter("openId");
			if(openId == null || "".equalsIgnoreCase(openId)){
				mav = AppUtil.runWx(openId);
			}
		 mav.addObject("openId",openId);
	    return mav;
	}
	
	/**
	 * 新增
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/creat")
	@ResponseBody
	public  String   creatLost(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String str=zsTextService.save(request);
	    return str;
	}
	
	/**
	 * 更新
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public  String   updateLost(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String str=zsTextService.update(request);
	    return str;
	}
	
	/**
	 * 更新查看
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/show")
	@ResponseBody
	public  List<Map<String, Object>>   showLost(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = zsTextService.showLost(request);
	    return list;
	}
	
	/**
	 * 删除
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public  String  deleteLost(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String str=zsTextService.delete(request);
	    return str;
	}
	
	/**
	 * 完成
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/end")
	@ResponseBody
	public  String  endLost(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String str=zsTextService.endLost(request);
	    return str;
	}
}
