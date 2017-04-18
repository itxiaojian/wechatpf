//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wzy.controller;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
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
import com.sliu.framework.app.dto.WeiXinOauth2Token;
import com.sliu.framework.app.model.WxUserInfo;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyXyxw;
import com.sliu.framework.app.wzy.service.ZyTppzService;
import com.sliu.framework.app.wzy.service.ZyXyxwService;

/**
 * 主页学院新闻
 * @author zhangyi
 * @version 创建时间：2015年6月3日  下午4:03:51
 */
@Controller
@RequestMapping("/wzy/ZyXyxw")
public class ZyXyxwController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZyXyxwController.class);

	@Autowired
	private ZyXyxwService zyXyxwService;
	@Autowired
	private ZyTppzService zyTppzService;

	/**
	 * 添加校园新闻
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:01
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveXYXW", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, ZyXyxw entity,
			HttpServletRequest request, HttpServletResponse response) {

		String result = zyXyxwService.saveXyxw(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除功能
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月10日 上午11:22:07 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids)
    {
      for (Long id : ids) {
    	  	zyXyxwService.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }

	@RequestMapping(value = "/xyxwPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/wzy/zyXyxwList");
		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:11
	 * @param start
	 * @param limit
	 * @param xwlx  新闻类型
	 * @param xwbt  新闻标题
	 * @return
	 */
	@RequestMapping(value = "/getXyxwList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXyxwList(Integer start,
			Integer limit, String xwlx, String xwbt) {
		Pagination<Map<String, Object>> list = zyXyxwService.getXyxwList(start,
				limit, xwlx, xwbt);
		return list;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:41
	 * @return
	 */
	@RequestMapping(value = "/zyXyxwListPage")
	public ModelAndView openTaskDealPage() {

		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wzy/zyXyxwListPage";

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:41
	 * @return
	 */
	@RequestMapping(value = "/zyXyxwUpdatePage")
	public ModelAndView zyXyxwUpdatePage(String id) {

		SysYh user = AppUtil.getCurrentUser();
		ZyXyxw zyXyxw = zyXyxwService.getXyxwById(Long.parseLong(id));

		Map<String, Object> map = (zyXyxw instanceof Map) ? (Map<String, Object>) zyXyxw : beanToMap(zyXyxw);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("map", map);
		String url = "";

		url = "/wzy/zyXyxwUpatePage";

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:51
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/zhuye")
	public ModelAndView zhuye(HttpServletRequest request,HttpServletResponse response,String openId) throws UnsupportedEncodingException {
//		StringBuffer path = request.getRequestURL();
//		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
//		String result1 = basePath+"/wzy/ZyXyxw/zhuye";//进入微主页
		ModelAndView modelAndView = new ModelAndView();
		List<Map<String, Object>> listmenu = zyXyxwService.getListMenu("1");
		modelAndView.addObject("listmenu", listmenu);
		String url = "";
		//获取轮播图
				List<Map<String, Object>> listLbt = zyTppzService.getListLbt();
				modelAndView.addObject("listLbt", listLbt);
		if(openId == null || "".equalsIgnoreCase(openId)){
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        
	        String code = request.getParameter("code"); //获取code
	        if(openId == null || "".equalsIgnoreCase(openId)){
	        	openId ="";
	        }
	        
			 if(code == null || "".equalsIgnoreCase(code)){
				 url = "/error/wxd";
			 }else{
		         if (!"authdeny".equals(code)) {
	                 WeiXinOauth2Token weiXinOauth2Token = WeixinUtils.getOauth2AccessToken(code);//根据code获取 页面授权信息类
	                 if(weiXinOauth2Token != null){
	                	 openId = weiXinOauth2Token.getOpenId();//获取当前微信用户的openId
	                 }else{
//	                	 String path = request.getContextPath();
//	                	 String result1 = path+"/wzy/ZyXyxw/zhuye";//进入微主页
	                	String path = request.getContextPath();
	             		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
	            		String result1 = basePath+"/wzy/ZyXyxw/zhuye";//进入微主页
		                 try {
		                         result1 = java.net.URLEncoder.encode(result1,"utf-8");
		                 } catch (UnsupportedEncodingException e) {
		                         e.printStackTrace();
		                 }
		                 String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result1+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	                	 url = "/wzy/zhuyetz";
	                	 modelAndView.addObject("newurl", url1);
	                	 modelAndView.setViewName(url);
	                	 return modelAndView;
	                 }
		         }
				//ocFFwuHOpoUHVQQGNgcRsMFbYVGg
				url = "/wzy/zhuye";
			 }
		 }else{
			 url = "/wzy/zhuye";
		 }
		 url = "/wzy/zhuye";
		 
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
		/*for (Map.Entry entry : ret.entrySet()) {
		    System.out.println(entry.getKey() + ", " + entry.getValue());
		}*/
		modelAndView.addObject("map", ret);
		
		modelAndView.addObject("openId", openId);
		modelAndView.setViewName(url);
		return modelAndView;
	}

	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:05:01
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/zyXyxwDetail")
	@ResponseBody
	public ModelAndView zyXyxwDetail(Long id) {

		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wzy/zyXyxwDetail";

		ZyXyxw zyXyxw = zyXyxwService.getXyxwById(id);

		Map<String, Object> map = (zyXyxw instanceof Map) ? (Map<String, Object>) zyXyxw : beanToMap(zyXyxw);

		modelAndView.addObject("map", map);

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 查询新闻列表
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:05:15
	 * @return
	 */
	@RequestMapping(value = "/zyXwlbDetail")
	@ResponseBody
	public ModelAndView xysyList(String openId){
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wzy/zyXwlbDetail";
		List<Map<String, Object>> list = zyXyxwService.getXyxwList();
		modelAndView.addObject("list", list);
		modelAndView.addObject("openId",openId);
		modelAndView.setViewName(url);
		return modelAndView;
	}
  
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:05:34
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
	 * 招生新闻发布
	 * @author zhangyan
	 * @date 2016年10月11日 上午10:20:10
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/zsxwPage")
	public ModelAndView openPage1() {
		ModelAndView modelAndView = new ModelAndView("/wzy/zyZsxwList");
		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:11
	 * @param start
	 * @param limit
	 * @param xwlx  新闻类型
	 * @param xwbt  新闻标题
	 * @return
	 */
	@RequestMapping(value = "/getZsxwList")
	@ResponseBody
	public Pagination<Map<String, Object>> getZsxwList(Integer start,
			Integer limit, String xwlx, String xwbt) {
		Pagination<Map<String, Object>> list = zyXyxwService.getZsxwList(start,
				limit, xwlx, xwbt);
		return list;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:41
	 * @return
	 */
	@RequestMapping(value = "/zyZsxwListPage")
	public ModelAndView openTaskDealPage1() {
		ModelAndView modelAndView = new ModelAndView();
		List<Map<String, Object>> list = zyXyxwService.getTjcx("", 2);	
		modelAndView.addObject("list",list);
		String url = "";
		url = "/wzy/zyZsxwListPage";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:41
	 * @return
	 */
	@RequestMapping(value = "/zyZsxwUpdatePage")
	public ModelAndView zyZsxwUpdatePage(String id) {
		ZyXyxw zyXyxw = zyXyxwService.getXyxwById(Long.parseLong(id));
		Map<String, Object> map = (zyXyxw instanceof Map) ? (Map<String, Object>) zyXyxw : beanToMap(zyXyxw);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("map", map);
		List<Map<String, Object>> list = zyXyxwService.getTjcx("", 2);	
		modelAndView.addObject("list",list);
		String url = "";
		url = "/wzy/zyZsxwUpatePage";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 上移功能
	 * @author:zhangyan 
	 * @version 创建时间：2016年6月23日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/up")
    @ResponseBody
    public ResponseData upTuWenMcInfo(String[] ids)
    {
      for (int i=0;i<ids.length;i++) {
    	  ZyXyxw entity=zyXyxwService.getXyxwById(Long.parseLong(ids[i]));
    	  String id=zyXyxwService.getBypx(entity.getPx()-1,entity.getXwlx()).get(0).get("id").toString();
    	  ZyXyxw entity1 =zyXyxwService.get(Long.parseLong(id));
    	  entity1.setPx(entity1.getPx()+1);
    	  entity.setPx(entity.getPx()-1);
    	  	zyXyxwService.update(entity);
    	  	zyXyxwService.update(entity1);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 下移功能
	 * @author:zhangyan 
	 * @version 创建时间：2016年6月23日 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/down")
    @ResponseBody
    public ResponseData downTuWenMcInfo(String[] ids)
    {
		for (int i=0;i<ids.length;i++) {
	    	  ZyXyxw entity=zyXyxwService.getXyxwById(Long.parseLong(ids[i]));
	    	  String id=zyXyxwService.getBypx(entity.getPx()+1,entity.getXwlx()).get(0).get("id").toString();
//	    	  String maxPx=zyXyxwService.getMaxpx(entity.getXwlx()).get(0).get("id").toString();
	    	  ZyXyxw entity1 =zyXyxwService.get(Long.parseLong(id));
	    	  entity1.setPx(entity1.getPx()-1);
	    	  entity.setPx(entity.getPx()+1);
	    	  	zyXyxwService.update(entity);
	    	  	zyXyxwService.update(entity1);
		}
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 获取考试的新闻列表的页面
	 * @author   oufeng
	 * @version 创建时间：2015年6月3日  下午4:04:41
	 * @return
	 */
	@RequestMapping(value = "/zyZsksList")
	public ModelAndView getListByZdbm(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String zdbm = request.getParameter("zdbm");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = zyXyxwService.getListByZdbm(zdbm);	
		List<Map<String, Object>> listTitle = zyXyxwService.getTitle(zdbm);	
		modelAndView.addObject("list",list);
		if(listTitle.size()!=0){
		modelAndView.addObject("listTitle",listTitle.get(0).get("zdmc")+"");
		}
		modelAndView.addObject("openId",openId);
		modelAndView.addObject("zdbm",zdbm);
		String url = "";
		url = "/wzy/zyZsksList";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 获取考试的新闻列表的详细页面
	 * @author   oufeng
	 * @version 创建时间：2015年6月3日  下午4:04:41
	 * @return
	 */
	@RequestMapping(value = "/zyZsksDetail")
	public ModelAndView getDetailById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String id = request.getParameter("id");
		String zdbm = request.getParameter("zdbm");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = zyXyxwService.getDetailById(id);
		List<Map<String, Object>> listTitle = zyXyxwService.getTitle(zdbm);
		if(listTitle.size()!=0){
		modelAndView.addObject("listTitle",listTitle.get(0).get("zdmc")+"");
		}
		modelAndView.addObject("list",list);
		modelAndView.addObject("openId",openId);
		String url = "";
		url = "/wzy/zyZsksDetail";
		modelAndView.setViewName(url);
		return modelAndView;
	}

}
