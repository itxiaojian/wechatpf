package com.sliu.framework.app.wsh.controller;

import java.beans.PropertyDescriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.model.ShTpzl;
import com.sliu.framework.app.wsh.service.ShTpjlService;
import com.sliu.framework.app.wsh.service.ShTpzlService;
import com.sliu.framework.app.wsh.service.ShTpzlxxService;

/**
 * 主页——投票专栏
 * 
 * @author zhangyan
 * @version 创建时间：2016年7月14日 
 */
@Controller
@RequestMapping("/wsh/ShTpzl")
public class ShTpzlController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShTpzlController.class);

	@Autowired
	private ShTpzlService shTpzlService;
	@Autowired
	private ShTpzlxxService shTpzlxxService;
	@Autowired
	private ShTpjlService shTpjlService;
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 *  功能：点击主菜单"投票专栏"后显示的列表
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @return
	 */
	@RequestMapping(value = "/tpzlPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/wsh/ShTpzl/shTpzlList");
		return modelAndView;
	}
	
	
	/**
	 * 分页查询 投票专栏
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	* @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getTpzlList")
	@ResponseBody
	public Pagination<Map<String, Object>> getTpzlList(Integer start,
			Integer limit) {
		Pagination<Map<String, Object>> list = shTpzlService.getTpzlList(start,
				limit);
		return list;
	}
	
	/**
	 * 
	 * @author   zhangyan
	 * @version 创建时间：2016年7月18日  
	 * @return
	 */
	@RequestMapping(value = "/shTpzlListPage")
	public ModelAndView openTaskDealPage() {
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShTpzl/shTpzlListPage";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyan
	 * @version 创建时间：2016年7月18日  
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/shTpzlUpdatePage")
	public ModelAndView zyXyxwUpdatePage(String id) throws ParseException {
		ShTpzl shTpzl = shTpzlService.getXyxwById(Long.parseLong(id));
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sj = time.format(shTpzl.getJssj());
		shTpzl.setJssj(time.parse(sj));
		Map<String, Object> map = (shTpzl instanceof Map) ? (Map<String, Object>) shTpzl : beanToMap(shTpzl);
		ModelAndView modelAndView = new ModelAndView();
		map.put("yjssj", sj);
		modelAndView.addObject("map", map);
		String url = "";
		url = "/wsh/ShTpzl/shTpzlUpatePage";
		modelAndView.setViewName(url);
		return modelAndView;
	}

	/**
	 * 添加
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveTpzl", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, ShTpzl entity,
			HttpServletRequest request, HttpServletResponse response) {
		String result = shTpzlService.saveTpzl(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateTpzl", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ShTpzl entity,Long id){
		
		String result  = shTpzlService.updateTpzl(entity,id);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除功能
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids)
    {
      for (Long id : ids) {
    	  shTpzlService.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	
	
	/**
	 * 查看详细投票内容
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/shTpzlDetail")
	@ResponseBody
	public ModelAndView shZttlDetail(Long id) {
		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShTpzl/shTpzlDetail";
		ShTpzl shTpzl = shTpzlService.getXyxwById(id);
		Map<String, Object> map = (shTpzl instanceof Map) ? (Map<String, Object>) shTpzl : beanToMap(shTpzl);
		modelAndView.addObject("map", map);
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
					params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	/**
	 * 编辑投票选项
	 * @author   zhangyan
	 * @version 创建时间：2016年7月22日  
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/shTpzlXx")
	@ResponseBody
	public ModelAndView shCkplDetail(Long id){
//		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShTpzl/shTpzlXx";
		if(id!=null&&!"".equals(id)){
			modelAndView.addObject("id", id);
   		}
		modelAndView.setViewName(url);
		modelAndView.addObject("id",id);
		return modelAndView;
	}
	
	
	/**
	 * 插入选项
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/saveXx")
	public ModelAndView saveQes(HttpServletRequest request,HttpServletResponse response){
		String oid = request.getParameter("id");
		int id = Integer.parseInt(oid);
		
		String selCount = request.getParameter("listCnt");
		int listCnt = Integer.parseInt(selCount);
		
				for (int i = 1; i <= listCnt; i++) {
					String name = String.valueOf("txt" + i);
					String value = request.getParameter(name);
					shTpzlxxService.addSelecter(id , value);
				}
		ModelAndView mav=new ModelAndView("/wsh/ShTpzl/shTpzlList");
   		return mav;
	}
	
	/**
	 * 查看选项
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="/tpzlckxx")
   	public ModelAndView toAddQues(Long id){
   		ModelAndView mav=new ModelAndView("/wsh/ShTpzl/tpzlCkxx");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("id", id);
   		}
		List<Map<String,Object>> xxlist=shTpzlxxService.getXxlist(id);
		mav.addObject("xxlist",xxlist);
   		return mav;
   	}
	
	/**
	 * 投票专栏,前台页面
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年7月26日 
	 * @return
	 */
	@RequestMapping(value = "/shtpzlPage")
	@ResponseBody
	public ModelAndView tpzlPage(String openId,HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			url = "/wsh/ShTpzl/tpzlPage";
		}else{
			url="/sys/SysWxyh/wxbd";
			modelAndView.addObject("url","/wsh/ShTpzl/tpzlPage");
		}
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){
			modelAndView = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = shTpzlService.getList(request);

		modelAndView.addObject("openId", openId);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("size",list.size());
		modelAndView.setViewName(url);
		}

		return modelAndView;
	}
	
	/**
	 * 前台点击专题列表进入详细页面
	 * @author   zhangyan
	 * @version 创建时间：2016年7月21日  
	 * @return
	 */
	@RequestMapping(value = "/shtpzlDetail")
	public ModelAndView getDetailById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String id = request.getParameter("id");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> tplist = shTpzlService.getDetailById(id);
		
		List<Map<String, Object>> jllist = shTpjlService.getJllist(id,openId);
		
		if(jllist.size()!=0){
			List<Map<String, Object>> xxlist = shTpzlxxService.getXxlist(Long.parseLong(id));
			int sum =shTpjlService.getSum(id);
			String xxnr =(String) jllist.get(0).get("xxnr");
			String url = "";
			url = "/wsh/ShTpzl/tpjgDetail";
			modelAndView.setViewName(url);
			modelAndView.addObject("xxlist",xxlist);
			modelAndView.addObject("sum",sum);
			
			modelAndView.addObject("xxnr",xxnr);
		}else{
			List<Map<String, Object>> xxnrlist = shTpzlxxService.getXxnrlist(Long.parseLong(id));
			String url = "";
			url = "/wsh/ShTpzl/tpzlDetail";
			modelAndView.setViewName(url);
			modelAndView.addObject("xxnrlist",xxnrlist);
		}
		modelAndView.addObject("tplist",tplist);
		modelAndView.addObject("id",id);
		modelAndView.addObject("openId",openId);
		return modelAndView;
	}
	
	/**
	 * 保存投票结果
	 * @author zhangyan
	 * @date 2016年7月27日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveTp")
	@ResponseBody
	public String saveTp(HttpServletRequest request) {
		return shTpzlService.saveTp(request);
	}
	
	/**
	 * 查看投票结果
	 * @author zhangyan
	 * @date 2016年7月27日
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tpjgDetail")
   	public ModelAndView toWjDcjgSj(String id,String openId){
   		ModelAndView mav=new ModelAndView("/wsh/ShTpzl/tpjgDetail");
   		List<Map<String, Object>> tplist = shTpzlService.getDetailById(id);
   		List<Map<String, Object>> xxlist = shTpzlxxService.getXxlist(Long.parseLong(id));
   		int sum =shTpjlService.getSum(id);
   		mav.addObject("tplist",tplist);
   		mav.addObject("xxlist",xxlist);
   		mav.addObject("sum",sum);
   		mav.addObject("openId",openId);
   		return mav;
   	}
}

