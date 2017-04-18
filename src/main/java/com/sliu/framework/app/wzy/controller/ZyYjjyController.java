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
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyYjjy;
import com.sliu.framework.app.wzy.service.ZyYjjyService;


/**
 * 主页--   一键救援
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午4:10:47
 */

@Controller
@RequestMapping("/wzy/ZyYjjy")
public class ZyYjjyController  extends BaseController{


	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZyYjjy.class);
	
	@Autowired
	private ZyYjjyService zyYjjyService;
	
	/**
	 * 功能：点击一键救援之后弹出的信息列表
	 * zyYjjy.js
	 * zyYjjy.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:10:53
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getYjjyList")
	@ResponseBody
	public Pagination<Map<String, Object>> getYjjyList(Integer start,Integer limit){
		
		return zyYjjyService.getYjjyList(start, limit);
		
	}
	
	/**
	 * 
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:11:19
	 * @return
	 */
	@RequestMapping(value = "/yjjyPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyYjjyList");
		return modelAndView;
	}
	
	/**
	 * 功能：跳转到新闻详情页面
	 * viewYjjy.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:11:08
	 * @return
	 */
	@RequestMapping(value = "/zyYjjyDetail")
	@ResponseBody
	public ModelAndView viewYjjy(HttpServletRequest request,HttpServletResponse response,String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wzy/zyYjjyDetail";
		
		List<Map<String,Object>> zyYjjy = zyYjjyService.getYjjyList();
		
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

		// 注意 URL 一定要动态获取，不能 hardcode
		//String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
		HttpServletRequest httpRequest=(HttpServletRequest)request; 
		String url1 = "http://" + request.getServerName() //服务器地址  
		        + ":"   
		        + request.getServerPort()           //端口号  
		        + httpRequest.getContextPath()      //项目名称  
		        + httpRequest.getServletPath();      //请求页面或其他地址  
		    //+ "?" + (httpRequest.getQueryString()); //参数 
		Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
		modelAndView.addObject("map1", ret);
		
		modelAndView.addObject("map", zyYjjy);
		
		modelAndView.addObject("openId",openId);
		
		modelAndView.setViewName(url);
		
		return modelAndView;
	}
	
	/**
	 * 增加
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:11:31
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,ZyYjjy entity,
			HttpServletRequest request,HttpServletResponse response){
		zyYjjyService.save(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 编辑
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:11:36
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ZyYjjy entity, String id) {
		zyYjjyService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:11:41
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
	zyYjjyService.delete(ids);
	return ResponseData.SUCCESS_NO_DATA;
	}
	
}
