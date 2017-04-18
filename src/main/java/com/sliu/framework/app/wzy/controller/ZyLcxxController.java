package com.sliu.framework.app.wzy.controller;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.identity.User;
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
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyLcxx;
import com.sliu.framework.app.wzy.service.ZyLcxxService;


/**
 * 主页--流程信息
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午4:06:52
 */

@Controller
@RequestMapping("/wzy/ZyLcxx")
public class ZyLcxxController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZyLcxx.class);
	
	@Autowired
	private ZyLcxxService zyLcxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 功能：点击流程信息之后弹出的信息列表
	 * zyLcxx.js
	 * zyLcxx.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:59
	 * @param start
	 * @param limit
	 * @param lczl   流程种类
	 * @param lcmc 流程名称
	 * @return
	 */
	@RequestMapping(value = "/getLcxxList")
	@ResponseBody
	public Pagination<Map<String, Object>> getLcxxList(Integer start,
			   Integer limit, String lczl, String lcmc){
		return zyLcxxService.getLcxxList(start, limit, lczl, lcmc);
	}
	
	/**
	 * 
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:07:19
	 * @return
	 */
	@RequestMapping(value = "/lcxxPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxList");
		return modelAndView;
	}
	
	/**
	 * 功能：点击左上角“发布”按钮的发布信息
	 * openLcxxAddPage.jsp
	 * 流程种类需要在Dao和Sercive写个方法，传参数到这里，然后在openLcxxAddPage.jsp中遍历流程种类
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:08:03
	 * @param zdmc  字段名称
	 * @param zdz     字段值
	 * @return
	 */
	@RequestMapping(value = "/zyLcxxListPage")
	public ModelAndView openTaskDealPage(String zdmc, Integer zdz){
		
		ModelAndView modelAndView = new ModelAndView();
		
		String url = "";
		url = "/wzy/zyLcxxListPage";
		
		List<Map<String, Object>> list = zyLcxxService.getTjcx(zdmc, zdz);
        modelAndView.addObject("list", list);
		modelAndView.setViewName(url);
		
		return modelAndView;
	}
	
	/**
	 * 功能：点击“保存”按钮
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:08:32
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveLCXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model,ZyLcxx entity,
			HttpServletRequest request,HttpServletResponse response){
		
		String result  = zyLcxxService.saveLCXX(entity);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 功能：跳转到新闻详情页面
	 * zyLcxxDetail.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:08:39
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/zyLcxxDetail")
	@ResponseBody
	public ModelAndView zyLcxxDetail(Long id) {

		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wzy/zyLcxxDetail";

		ZyLcxx zyLcxx = zyLcxxService.getLcxxById(id);

		Map<String, Object> map = (zyLcxx instanceof Map) ? (Map<String, Object>) zyLcxx : beanToMap(zyLcxx);

		modelAndView.addObject("map", map);

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:08:45
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
	 * 跳转到申请请假的页面
	 * @author   oufeng
	 * @version 2016年1月5日  
	 * @return
	 */
	@RequestMapping(value = "/toSqjjPage")
	public ModelAndView opentoSqjjPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxQjxx");
		//获取审核人的下拉框
		String openId = request.getParameter("openId");
		if(openId==null){
			SysYh uesr = AppUtil.getCurrentUser();
			List<Map<String, Object>> shrlist = zyLcxxService.getShr(uesr.getYhbh());
	        modelAndView.addObject("shrlist", shrlist);
		}else if(!"".equals(openId)){
			List<Map<String,Object>> user = zyLcxxService.getUser(openId);
			List<Map<String, Object>> shrlist = zyLcxxService.getShr(user.get(0).get("yhbh")+"");
	        modelAndView.addObject("shrlist", shrlist);
		}
		modelAndView.addObject("openId", openId);
		return modelAndView;
	}
	
	/**
	 * 跳转到教师的流程信息的页面
	 * @author   oufeng
	 * @version 2016年1月5日  
	 * @return
	 */
	@RequestMapping(value = "/toJsxxPage")
	public ModelAndView opentoJsxxPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxJsxx");
		String openId = request.getParameter("openId");
		modelAndView.addObject("openId", openId);
		return modelAndView;
	}
	
	/**
	 * 功能：学生请假的保存
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/saveQjxx", method = RequestMethod.POST)
	@ResponseBody
	public String  saveQjxx(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		String result  = zyLcxxService.saveQjxx(request);
		return result;
	}
	
	/**
	 * 功能：教师出差申请的保存
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/saveCcxx", method = RequestMethod.POST)
	@ResponseBody
	public String  saveCcxx(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		String result  = zyLcxxService.saveCcxx(request);
		return result;
	}
	
	/**
	 * 需要教师审批的请假的页面
	 * @author   oufeng
	 * @version 2016年1月5日  
	 * @return
	 */
	@RequestMapping(value = "/toSqxx")
	public ModelAndView toSqxx1(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxSqxxList");
		String openId = request.getParameter("openId");
		List<Map<String,Object>> user = zyLcxxService.getUser(openId);
		String yhbh = user.get(0).get("yhbh")+"";
		List<Map<String, Object>> list = zyLcxxService.getSqxx(yhbh);
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	/**
	 * 需要教师审批的请假的页面
	 * @author   oufeng
	 * @version 2016年1月5日  
	 * @return
	 */
	@RequestMapping(value = "/	toSqxxXq")
	public ModelAndView 	toSqxxXq(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxSqxxDetail");
		String id = request.getParameter("id");
		List<Map<String, Object>> list = zyLcxxService.getDetail(id);
		String openId = request.getParameter("openId");
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	

/** 
 * 审核通过
 * @author oufeng
 * @version 创建时间：2016年1月5日 
 * @throws ParseException 
 * @throws UnsupportedEncodingException 
 */
@RequestMapping(value = "/shtg")
@ResponseBody
public String shtg(HttpServletRequest request,
		HttpServletResponse response, String openId)
		throws  ParseException, UnsupportedEncodingException {
	String str="";
	str=zyLcxxService.updateSqxxtg(request);
	return str;
}

/** 
 * 驳回
 * @author oufeng
 * @version 创建时间：2016年1月5日 
 * @throws ParseException 
 * @throws UnsupportedEncodingException 
 */
@RequestMapping(value = "/bh")
@ResponseBody
public String bh(HttpServletRequest request,
		HttpServletResponse response, String openId)
		throws  ParseException, UnsupportedEncodingException {
	
	String str="";
	str=zyLcxxService.updateSqxxbh(request);
	return str;
}

/**
 * 学生请假的申请列表的页面
 * @author   oufeng
 * @version 2016年1月5日  
 * @return
 */
@RequestMapping(value = "/	toQjjl")
public ModelAndView 	toQjjl(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxQjList");
	String openId= request.getParameter("openId");
	if(openId==null ){
		SysYh uesr = AppUtil.getCurrentUser();
		List<Map<String,Object>> spxx=zyLcxxService.getSpxx(uesr.getYhbh());
		modelAndView.addObject("spxx", spxx);
		modelAndView.addObject("yhbh", uesr.getYhbh());
	}else if(!"".equals(openId)){
		List<Map<String,Object>> user = zyLcxxService.getUser(openId);
		List<Map<String,Object>> spxx=zyLcxxService.getSpxx(user.get(0).get("yhbh")+"");
		modelAndView.addObject("spxx", spxx);
		modelAndView.addObject("yhbh", user.get(0).get("yhbh")+"");
	}
	modelAndView.addObject("openId", openId);
	return modelAndView;
}

/**
 * 出差申请的申请列表的页面
 * @author   oufeng
 * @version 2016年1月5日  
 * @return
 */
@RequestMapping(value = "/	toCcjl")
public ModelAndView 	toCcjl(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxCcList");
	String openId= request.getParameter("openId");
	if(openId==null){
		SysYh uesr = AppUtil.getCurrentUser();
		List<Map<String,Object>> spxx=zyLcxxService.getSpxx(uesr.getYhbh());
		List<Map<String, Object>> map = zyLcxxService.getCcjl(uesr.getYhbh());
		modelAndView.addObject("spxx", spxx);
		modelAndView.addObject("map", map);
		modelAndView.addObject("size", map.size());
		modelAndView.addObject("yhbh", uesr.getYhbh());
	}else if(!"".equals(openId)){
		List<Map<String,Object>> user = zyLcxxService.getUser(openId);
		List<Map<String,Object>> spxx=zyLcxxService.getSpxx(user.get(0).get("yhbh")+"");
		List<Map<String, Object>> map = zyLcxxService.getCcjl(user.get(0).get("yhbh")+"");
		modelAndView.addObject("spxx", spxx);
		modelAndView.addObject("map", map);
		modelAndView.addObject("size", map.size());
		modelAndView.addObject("yhbh", user.get(0).get("yhbh")+"");
	}
	modelAndView.addObject("openId", openId);
	return modelAndView;
}




/**
 * 学生请假的申请列表的页面
 * @author   oufeng
 * @version 2016年1月5日  
 * @return
 */
@RequestMapping(value = "/ccsq")
public ModelAndView toCcsqPage(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxCcxx");
	String openId= request.getParameter("openId");
	if(openId==null){
		SysYh uesr = AppUtil.getCurrentUser();
		String yhbh = uesr.getYhbh();
		List<Map<String, Object>> shrlist = zyLcxxService.getShr1(yhbh);
		modelAndView.addObject("yhbh", yhbh);
        modelAndView.addObject("shrlist", shrlist);
	}else if(!"".equals(openId)){
		List<Map<String,Object>> user = zyLcxxService.getUser(openId);
		List<Map<String, Object>> shrlist = zyLcxxService.getShr1(user.get(0).get("yhbh")+"");
		modelAndView.addObject("openId", openId);
        modelAndView.addObject("shrlist", shrlist);
	}
	return modelAndView;
}

/**
 *出差驳回修改的页面
 * @author   oufeng
 * @version 2016年1月5日  
 * @return
 */
@RequestMapping(value = "/ccsqUpdate")
public ModelAndView toCcsqUpdate(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxCcUpdate");
	String openId= request.getParameter("openId");
	String id= request.getParameter("id");
	String yhbh= request.getParameter("yhbh");
	List<Map<String, Object>> map = zyLcxxService.getUpdatexx(id);
	if(openId==null ){
		List<Map<String, Object>> shrlist = zyLcxxService.getShr1(yhbh);
		modelAndView.addObject("shrlist",shrlist);
	}else if(!"".equals(openId)){
		String user  =  zyLcxxService.getUser(openId).get(0).get("yhbh")+"";
		List<Map<String, Object>> shrlist = zyLcxxService.getShr1(user);
		modelAndView.addObject("shrlist",shrlist);
	}
	modelAndView.addObject("map",map);
	modelAndView.addObject("yhbh",yhbh);
	modelAndView.addObject("openId",openId);
	return modelAndView;
}

/**
 *请假驳回修改的页面
 * @author   oufeng
 * @version 2016年1月5日  
 * @return
 */
@RequestMapping(value = "/toSqxxUpdate")
public ModelAndView toSqxxUpdate(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView("/wzy/zyLcxxQjUpdate");
	String openId= request.getParameter("openId");
	String id= request.getParameter("id");
	String yhbh= request.getParameter("yhbh");
	List<Map<String, Object>> map = zyLcxxService.getUpdatexx(id);
	if(openId==null ){
		List<Map<String, Object>> shrlist = zyLcxxService.getShr(yhbh);
		modelAndView.addObject("shrlist",shrlist);
	}else if(!"".equals(openId)){
		String user  =  zyLcxxService.getUser(openId).get(0).get("yhbh")+"";
		List<Map<String, Object>> shrlist = zyLcxxService.getShr(user);
		modelAndView.addObject("shrlist",shrlist);
	}
	modelAndView.addObject("map",map);
	modelAndView.addObject("yhbh",yhbh);
	modelAndView.addObject("openId",openId);
	return modelAndView;
}

/**
 * 微主页点击流程信息
 * @author   oufeng
 * @version 2016年1月5日  
 * @return
 */
@RequestMapping(value = "/toLcxxPage")
public ModelAndView toLcxxPage(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView();
	 String url="";
	String openId = request.getParameter("openId");
	if(openId==null ){
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh  = yh.getYhbh();
		 List<Object> jsList = zyLcxxService.getJsList(yhbh);
		 if(jsList.contains("ROLE_STUDENT")){
			 url="/wzy/zyLcxxQjList";
			List<Map<String,Object>> spxx=zyLcxxService.getSpxx(yhbh);
			List<Map<String, Object>> map = zyLcxxService.getCcjl(yhbh);
			modelAndView.addObject("spxx", spxx);
			modelAndView.addObject("map", map);
			modelAndView.addObject("yhbh", yhbh);
		 }else{
			 url="/wzy/zyLcxxJsxx";
		 }
	}else if(!"".equals(openId)){
		List<Map<String, Object>> user = zyLcxxService.getUser(openId);
		String yhbh = user.get(0).get("yhbh")+"";
		 List<Object> jsList = zyLcxxService.getJsList(yhbh);
		 if(jsList.contains("ROLE_STUDENT")){
			 url="/wzy/zyLcxxQjList";
			 	List<Map<String,Object>> spxx=zyLcxxService.getSpxx(yhbh);
				modelAndView.addObject("spxx", spxx);
				List<Map<String, Object>> map = zyLcxxService.getCcjl(yhbh);
				modelAndView.addObject("spxx", spxx);
				modelAndView.addObject("map", map);
				modelAndView.addObject("yhbh", yhbh);
		 }else{
			 url="/wzy/zyLcxxJsxx";
		 }
	}
	modelAndView.setViewName(url);
	modelAndView.addObject("openId", openId);
	return modelAndView;
}

/**
 * 功能：流程信息的绑定与跳转
 * @author   oufeng
 * @version 创建时间：2015年1月6日  
 * @return
 */
@RequestMapping(value = "/zyLcxxBd")
public ModelAndView zyLcxxBd(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView();
	String url="";
	String openId = request.getParameter("openId");
	List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
	if(user.size()!=0){
		List<Map<String, Object>> users = zyLcxxService.getUser(openId);
		String yhbh = users.get(0).get("yhbh")+"";	
		 List<Object> jsList = zyLcxxService.getJsList(yhbh);
		 if(jsList.contains("ROLE_STUDENT")){
			 url="/wzy/zyLcxxQjList";
			 	List<Map<String,Object>> spxx=zyLcxxService.getSpxx(yhbh);
				modelAndView.addObject("spxx", spxx);
				List<Map<String, Object>> map = zyLcxxService.getCcjl(yhbh);
				modelAndView.addObject("spxx", spxx);
				modelAndView.addObject("map", map);
				modelAndView.addObject("size", map.size());
				modelAndView.addObject("openId", openId);
				modelAndView.addObject("yhbh", yhbh);
		 }else{
			 url="/wzy/zyLcxxJsxx";
		 }
	}else{
		url="/sys/SysWxyh/wxbd";
		modelAndView.addObject("url","/wzy/ZyLcxx/zyLcxxBd");
	}
	modelAndView.addObject("openId", openId);
	modelAndView.setViewName(url);
	return modelAndView;
}

/**
 * 申请数据修改保存
 * @author oufeng
 * @date 2016年1月6日
 * @param request
 * @return
 * @throws ParseException 
 */
@RequestMapping(value = "/updateCcxx")
@ResponseBody
public String updateCcxx(HttpServletRequest request) throws ParseException {
	String str="";
	str=zyLcxxService.updateCcxx(request);
	return str;
}

/**
 * 申请数据修改保存
 * @author oufeng
 * @date 2016年1月6日
 * @param request
 * @return
 * @throws ParseException 
 */
@RequestMapping(value = "/updateQjxx")
@ResponseBody
public String updateQjxx(HttpServletRequest request) throws ParseException {
	String str="";
	str=zyLcxxService.updateQjxx(request);
	return str;
}

}