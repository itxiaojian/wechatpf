package com.sliu.framework.app.wzy.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyDhhl;
import com.sliu.framework.app.wzy.service.ZyDhhlService;


/**
 * 主页--   电话黄历
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午4:05:51
 */

@Controller
@RequestMapping("/wzy/ZyDhhl")
public class ZyDhhlController  extends BaseController{

	protected final transient Logger logger = LoggerFactory.getPresentationLog(ZyDhhl.class);
	
	@Autowired
	private ZyDhhlService zyDhhlService;
	
	/**
	 * 功能：点击一键救援之后弹出的信息列表
	 * zyDhhl.js
	 * zyDhhl.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:01
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getDhhlList")
	@ResponseBody
	public Pagination<Map<String, Object>> getDhhlList(Integer start,Integer limit){
		
		return zyDhhlService.getDhhlList(start, limit);
		
	}
	
	/**
	 * 
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:07:44
	 * @return
	 */
	@RequestMapping(value = "/dhhlPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyDhhlList");
		return modelAndView;
	}
	
	/**
	 *  功能：跳转到电话黄页详情页面
	 * viewDhhl.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:14
	 * @return
	 */
	@RequestMapping(value = "/zyDhhlDetail")
	@ResponseBody
	public ModelAndView viewDhhl(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wzy/zyDhhlDetail";
		
		List<Map<String,Object>> zyDhhl = zyDhhlService.getDhhlList();
		
		modelAndView.addObject("map", zyDhhl);
		
		modelAndView.addObject("openId",openId);
		
		modelAndView.setViewName(url);
		
		return modelAndView;
	}
	
	/**
	 * 增加
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:27
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,ZyDhhl entity,
			HttpServletRequest request,HttpServletResponse response){
		zyDhhlService.save(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 编辑
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ZyDhhl entity, String id) {
		zyDhhlService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:43
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
	zyDhhlService.delete(ids);
	return ResponseData.SUCCESS_NO_DATA;
	}
	
}
