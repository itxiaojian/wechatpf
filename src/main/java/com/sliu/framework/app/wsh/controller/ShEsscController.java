//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.service.ShEsscService;

/**
 * 二手市场
 * @author liujiasen
 * @date 2015年6月30日
 */
@Controller
@RequestMapping("/wsh/ShEssc")
public class ShEsscController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShEsscController.class);

	@Autowired
	private ShEsscService service;
	
	/**
	 * 二手市场首页
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toEsscList")
	public ModelAndView toEsscList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/shEsscList");
		
		String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
		     String result2 = basePath+"/wsh/ShEssc/toEsscAdd";//进入微服务

         try {
                 result2 = java.net.URLEncoder.encode(result2,"utf-8");
         } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
         }
     
         String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result2+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}else{
			List<Map<String, Object>> list = service.getGoodList(request, response);
			if (list.size() != 0) {
				mav.addObject("list", list);
				mav.addObject("pages", pages);
			}
		}
		mav.addObject("openId", openId);
		mav.addObject("newurl", url);
		return mav;
	}
	
	/**
	 * 二手市场发布
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toEsscAdd")
	public ModelAndView toEsscAdd(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/shEsscAdd");
		String openId = request.getParameter("openId");
		if(openId==null||"".equals(openId)){
			openId = AppUtil.getOpenId(request, response);
		}
		mav.addObject("openId", openId);
		return mav;
	}
	
	/**
	 * 保存发布商品信息
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/shEsscList");
		service.saveGood(request, response);
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = service.getGoodList(request, response);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
		}
		mav.addObject("openId", openId);
		return mav;
	}
	
	/**
	 * 二手市场商品列表
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toEsscSpList")
	public ModelAndView toEsscSpList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/shEsscSpList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		String splx=request.getParameter("splx");
		List<Map<String, Object>> list = service.getGoodList(request, response);
		int count=service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("count", count);
		}
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("splx", splx);
		return mav;
	}
	
	/**
	 * 商品详情
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toEsscDetail")
	public ModelAndView toEsscDetail(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/shEsscDetail");
		String id = request.getParameter("id");
		String openId = request.getParameter("openId");
		List<Map<String,Object>> list=service.getGood(id);
		if(list.size()!=0){
			mav.addObject("map", list.get(0));
		}
		mav.addObject("openId", openId);
		return mav;
	}
	
	/**
	 * 商品售出或下架
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/shEsscDetail");
		String id = request.getParameter("id");
		String openId = request.getParameter("openId");
		service.updateGood(request, response);
		List<Map<String,Object>> list=service.getGood(id);
		if(list.size()!=0){
			mav.addObject("map", list.get(0));
		}
		mav.addObject("openId", openId);
		return mav;
	}
	
	/**
	 * 根据商品Id删除商品
	 * @author liujiasen
	 * @date 2015年7月20日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/shEsscList");
		String id = request.getParameter("id");
		String openId = request.getParameter("openId");
		service.delete(id);
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = service.getGoodList(request, response);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
		}
		mav.addObject("openId", openId);
		return mav;
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月2日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toUpdateTest")
	public ModelAndView toUpdateTest(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShEssc/uploadTest");
		return mav;
	}
}
