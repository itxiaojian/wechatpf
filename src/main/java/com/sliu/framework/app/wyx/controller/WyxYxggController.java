package com.sliu.framework.app.wyx.controller;

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

import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wyx.model.WyxYxgg;
import com.sliu.framework.app.wyx.service.WyxYxggService;

/**
 * 迎新公告
 * @author wangxiangyang
 * @date 2016年8月9日
 */
@Controller
@RequestMapping("/wyx/WyxYxgg")
public class WyxYxggController {
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WyxYxggController.class);

	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	@Autowired
	private WyxYxggService wyxYxggService;
	
	/**
	 * 迎新公告列表
	 * @author wangxiangyang
	 * @date 2016年8月9日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toYxgg")
	@ResponseBody
	public ModelAndView toYxgg(HttpServletRequest request){
		ModelAndView mav=null;
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wsh/shyxggList");
	   		List<Map<String,Object>> Yxgglist=wyxYxggService.getYxggList(openId);
	   		if(Yxgglist.size()!=0){
	   			mav.addObject("Yxgglist", Yxgglist);
	   		}
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
	   		mav.addObject("openId",openId);
			mav.addObject("size",Yxgglist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wsh/WyxYxgg/toYxgg");
		}
		
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
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
		
		return mav;
	}
	
	/**
	 * @author  wangxiangyang
	 * @version 创建时间：2016年8月9日  
	 * @param id
	 */
	@RequestMapping(value = "/YxggxxDetail")
	@ResponseBody
	public ModelAndView wyxYxggxxDetail(Long id) {
		ModelAndView mav=null;
		mav=new ModelAndView("/wsh/YxggxxDetail");
		List<Map<String,Object>> map = wyxYxggService.getYxggxxById1(id);
		mav.addObject("map", map);
		return mav;

	}
	
	/**
	 * 
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月10日  
	 * @return
	 */
	@RequestMapping(value = "/yxPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wsh/wyxList");
		return modelAndView;
	}
	
	/**
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月10日  
	 * @param start
	 * @param limit
	 * @param type
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/getyxList")
	@ResponseBody
	public Pagination<Map<String, Object>> getyxList(Integer start,
			Integer limit, String type, String title){
		Pagination<Map<String, Object>> list = wyxYxggService.getyxList(start,limit, type, title);
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
	@RequestMapping(value = "/yxggListPage")
	public ModelAndView openTaskDealPage(String zdmc, Integer zdz) {

		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();

		String url = "";
		
		url = "/wsh/yxggListPage";
		
       List<Map<String, Object>> list = wyxYxggService.getTjcx(zdmc, zdz);	
		modelAndView.addObject("list",list);
		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**修改离校相关信息
	 * 
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月8日  
	 * @return
	 */
	@RequestMapping(value = "/yxggUpdatePage")
	public ModelAndView zyXyxgxxUpdatePage(String id) {

		SysYh user = AppUtil.getCurrentUser();
		WyxYxgg wyxYxgg = wyxYxggService.getXyxgxxById(Long.parseLong(id));

		Map<String, Object> map = (wyxYxgg instanceof Map) ? (Map<String, Object>) wyxYxgg : beanToMap(wyxYxgg);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("map", map);
		
		List<Map<String, Object>> list = wyxYxggService.getTjcx("", 2);	
		modelAndView.addObject("list",list);
		
		String url = "";

		url = "/wsh/yxggUpdatePage";

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 
	 * @author   wangxiangyang
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
	
	/**
	 * 功能：点击“保存”按钮
	 * @author  wangxiangyang
	 * @version 创建时间：2016年8月10日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveYXGG", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model,WyxYxgg entity,
			HttpServletRequest request,HttpServletResponse response){
		
		String result  = wyxYxggService.saveYxgg(entity);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改离校相关信息
	 * @author:wangxiangyang
	 * @version 创建时间：2016年8月10日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateYXGG", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(WyxYxgg entity,Long id){
		
		String result  = wyxYxggService.updateYxgg(entity,id);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除离校相关信息
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月10日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		wyxYxggService.deleteYxgg(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
}
