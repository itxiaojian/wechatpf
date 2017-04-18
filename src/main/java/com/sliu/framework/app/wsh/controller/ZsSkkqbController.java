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

import com.google.gson.Gson;
import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.service.ZsSkkqbService;

/**
 * 二手市场
 * @author liujiasen
 * @date 2015年6月30日
 */
@Controller
@RequestMapping("/wsh/ZsSkkqb")
public class ZsSkkqbController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsSkkqbController.class);

	@Autowired
	private ZsSkkqbService service;
	
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
		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsSkkqbList");

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
		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsSkkqbAdd");
		String openId = request.getParameter("openId");
		if(openId==null||"".equals(openId)){
			openId = AppUtil.getOpenId(request, response);
		}else{
			List<Map<String,Object>> kc=service.getKc(openId);
			if(kc!=null&&kc.size()!=0){
				mav.addObject("kcList", kc);
			}
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
		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsSkkqewm");
		String str=service.saveGood(request, response);
		String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
		     String result2 = basePath+"/wsh/ZsSkkqjlb/save?id="+str;//进入微服务

         try {
                 result2 = java.net.URLEncoder.encode(result2,"utf-8");
         } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
         }
     
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result2+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		String openId = request.getParameter("openId");
		mav.addObject("str", str);
		mav.addObject("openId", openId);
		mav.addObject("url", url);
		return mav;
	}
	
	/**
	 * 跳转到二维码页面
	 * @author liujiansen
	 * @date 2016年3月29日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toEwmPage")
	public ModelAndView toEwmPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsSkkqewm");
		String id = request.getParameter("id");
		String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
		     String result2 = basePath+"/wsh/ZsSkkqjlb/save?id="+id;//进入微服务

         try {
                 result2 = java.net.URLEncoder.encode(result2,"utf-8");
         } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
         }
     
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result2+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		String openId = request.getParameter("openId");
		mav.addObject("str", "1");
		mav.addObject("openId", openId);
		mav.addObject("url", url);
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2016年3月30日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSkdd")
	public String getSkdd(HttpServletRequest request, HttpServletResponse response) {
		String kcbh=request.getParameter("kcbh");
		String openId=request.getParameter("openId");
		Object obj = service.getSkdd(kcbh,openId);
		Gson gson = new Gson();
		writeJson(response,gson.toJson(obj));
		return null;
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
	 * 根据商品Id删除商品
	 * @author liujiasen
	 * @date 2015年7月20日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/shWdm/zsSkkqbList");
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
	
}
