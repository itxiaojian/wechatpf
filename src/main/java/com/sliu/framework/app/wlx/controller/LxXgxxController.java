package com.sliu.framework.app.wlx.controller;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wlx.model.LxXgxx;
import com.sliu.framework.app.wlx.service.LxXgxxService;

/**
 * 离校相关信息
 * @author zhangyan
 * @version 创建时间：2016年6月6日  
 */
@Controller
@RequestMapping("/wlx/lxxgxx")
public class LxXgxxController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(LxXgxxController.class);

	@Autowired
	private LxXgxxService lxXgxxService;
	
	/**
	 * 功能：点击“保存”按钮
	 * @author  zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveLXXGXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model,LxXgxx entity,
			HttpServletRequest request,HttpServletResponse response){
		
		String result  = lxXgxxService.saveLyxgxx(entity);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改离校相关信息
	 * @author:zhangyan
	 * @version 创建时间：2016年6月6日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateLXXGXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(LxXgxx entity,Long id){
		
		String result  = lxXgxxService.updateLxxgxx(entity,id);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 
	 * @author   zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @return
	 */
	@RequestMapping(value = "/lxxgxxPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wlx/lxXgxxList");
		return modelAndView;
	}
	
	/**
	 *  功能：点击离校相关信息信息之后弹出的信息列表
	 * zyXyxgxx.js
	 * zyXyxgxx.jsp
	 * @author   zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @param start
	 * @param limit
	 * @param type
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/getLxxgxxList")
	@ResponseBody
	public Pagination<Map<String, Object>> getLxxgxxList(Integer start,
			Integer limit, String type, String title){
		Pagination<Map<String, Object>> list = lxXgxxService.getXyxgxxList(start, 
				limit, type, title);
		return list;
	}
	
	/**
	 * 功能：点击左上角“发布”按钮的发布信息
	 * openXyxgxxAddPage.jsp
	 * 流程种类需要在Dao和Sercive写个方法，传参数到这里，然后在openXyxgxxAddPage.jsp中遍历流程种类
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:09:55
	 * @param zdmc
	 * @param zdz
	 * @return
	 */
	@RequestMapping(value = "/lxXgxxListPage")
	public ModelAndView openTaskDealPage(String zdmc, Integer zdz) {

		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();

		String url = "";
		
		url = "/wlx/lxXgxxListPage";
		
       List<Map<String, Object>> list = lxXgxxService.getTjcx(zdmc, zdz);	
		modelAndView.addObject("list",list);
		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**修改离校相关信息
	 * 
	 * @author   zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @return
	 */
	@RequestMapping(value = "/lxXgxxUpdatePage")
	public ModelAndView zyXyxgxxUpdatePage(String id) {

		SysYh user = AppUtil.getCurrentUser();
		LxXgxx lxXgxx = lxXgxxService.getXyxgxxById(Long.parseLong(id));

		Map<String, Object> map = (lxXgxx instanceof Map) ? (Map<String, Object>) lxXgxx : beanToMap(lxXgxx);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("map", map);
		
		List<Map<String, Object>> list = lxXgxxService.getTjcx("", 2);	
		modelAndView.addObject("list",list);
		
		String url = "";

		url = "/wlx/lxXgxxUpdatePage";

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 功能：跳转到新闻详情页面
	 * viewXyxgxx.jsp
	 * @author  zhangyan
	 * @version 创建时间：2016年6月7日  
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/lxXgxxDetail")
	@ResponseBody
	public ModelAndView zyXyxgxxDetail(Long id) {

		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wlx/lxXgxxDetail";

		LxXgxx lxXgxx = lxXgxxService.getXyxgxxById(id);

		Map<String, Object> map = (lxXgxx instanceof Map) ? (Map<String, Object>) lxXgxx : beanToMap(lxXgxx);

		modelAndView.addObject("map", map);

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 分类查询页面跳转：办理指南、离校公告
	 * @author   zhangyan
	 * @version 创建时间：2016年6月7日 
	 * @param type    类型
	 * @return
	 */
	@RequestMapping(value = "/lxjjDetail")
	@ResponseBody
	public ModelAndView zyXyjjDetail(HttpServletRequest request,HttpServletResponse response,String type,String openId){
		
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		List<Map<String, Object>> list = lxXgxxService.getXyjj(type);
		Map<String, Object> map = (list.get(0) instanceof Map) ? (Map<String, Object>) list.get(0) : beanToMap(list.get(0));
		
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

		// 注意 URL 一定要动态获取，不能 hardcode
		//String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
		HttpServletRequest httpRequest=(HttpServletRequest)request; 
		String url1 = "http://" + request.getServerName() //服务器地址  
		        + ":"   
		        + request.getServerPort()           //端口号  
		        + httpRequest.getContextPath()      //项目名称  
		        + httpRequest.getServletPath()      //请求页面或其他地址  
		        + "?" + (httpRequest.getQueryString()); //参数 
		Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
		modelAndView.addObject("map1", ret);
		
		//办理指南
		 if("0".equalsIgnoreCase(type)){
			if(map.size()>0){
				modelAndView.addObject("list",list);
				modelAndView.addObject("openId",openId);
				url = "/wlx/lxBlznDetail";
			}
		//离校公告
		}else if("1".equalsIgnoreCase(type)){
			if(map.size()>0){
				modelAndView.addObject("list",list);
				modelAndView.addObject("openId",openId);
				url = "/wlx/lxGgDetail";
			}
		}
		modelAndView.setViewName(url);
			return modelAndView;
	}
	

	
	/**
	 * 删除离校相关信息
	 * @author   zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		lxXgxxService.deleteXyxgxx(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}

	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:10:09
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean
					.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!StringUtils.pathEquals(name, "class")) {
					params.put(name,
							propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
}
