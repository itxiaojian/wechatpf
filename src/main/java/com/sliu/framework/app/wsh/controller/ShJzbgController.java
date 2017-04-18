package com.sliu.framework.app.wsh.controller;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
import com.sliu.framework.app.calmutil.CalmUtil;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.model.ShJzbg;
import com.sliu.framework.app.wsh.service.ShJzbgService;
import com.sliu.framework.app.wsh.service.ShJzbgplService;

/**
 * 讲座报告
 * 
 * @author zhangyan
 * @version 创建时间：2016年8月1日 
 */
@Controller
@RequestMapping("/wsh/ShJzbg")
public class ShJzbgController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShJzbgController.class);

	@Autowired
	private ShJzbgService shJzbgService;
	@Autowired
	private ShJzbgplService shJzbgplService;
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 *  功能：点击主菜单"讲座报告"后显示的列表
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @return
	 */
	@RequestMapping(value = "/jzbgPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/wsh/ShJzbg/shJzbgList");
		return modelAndView;
	}
	
	
	/**
	 * 分页查询 讲座报告
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	* @param start
	 * @param limit
	 * @param jzmc  讲座名称
	 * @param jzbt  讲座标题
	 * @return
	 */
	@RequestMapping(value = "/getJzbgList")
	@ResponseBody
	public Pagination<Map<String, Object>> getJzbgList(Integer start,
			Integer limit, String jzmc, String jzbt) {
		Pagination<Map<String, Object>> list = shJzbgService.getJzbgList(start,
				limit, jzmc, jzbt);
		return list;
	}
	
	/**
	 * 
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @return
	 */
	@RequestMapping(value = "/shJzbgListPage")
	public ModelAndView openTaskDealPage() {
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShJzbg/shJzbgListPage";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 后台修改页面
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/shJzbgUpdatePage")
	public ModelAndView zyXyxwUpdatePage(String id) throws ParseException {
		ShJzbg shJzbg = shJzbgService.getXyxwById(Long.parseLong(id));
		Map<String, Object> map = (shJzbg instanceof Map) ? (Map<String, Object>) shJzbg : beanToMap(shJzbg);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("map", map);
		String url = "";
		url = "/wsh/ShJzbg/shJzbgUpdatePage";
		modelAndView.setViewName(url);
		return modelAndView;
	}

	/**
	 * 添加讲座报告
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveJzbg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData saveJzbg(ModelMap model, ShJzbg entity,
			HttpServletRequest request, HttpServletResponse response) {
		String result = shJzbgService.saveJzbg(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改讲座报告
	 * @author:zhangyan 
	 * @version 创建时间：2016年8月1日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateJzbg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData updateJzbg(ShJzbg entity,Long id){
		
		String result  = shJzbgService.updateJzbg(entity,id);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除功能
	 * @author:zhangyan 
	 * @version 创建时间：2016年8月1日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteJzbg(Long[] ids)
    {
      for (Long id : ids) {
    	  shJzbgService.deleteJzbg(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	
	
	/**
	 * 查看详细讲座内容
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/shJzbgDetail")
	@ResponseBody
	public ModelAndView shJzbgDetail(Long id) {
		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShJzbg/shJzbgDetail";
		ShJzbg shJzbg = shJzbgService.getXyxwById(id);
		Map<String, Object> map = (shJzbg instanceof Map) ? (Map<String, Object>) shJzbg : beanToMap(shJzbg);
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
	 * 关闭讲座
	 * @author:zhangyan 
	 * @version 创建时间：2016年8月3日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/close")
    @ResponseBody
    public ResponseData passTuWenMcInfo(String[] ids)
    {
      for (int i=0;i<ids.length;i++) {
    	  ShJzbg entity=shJzbgService.getXyxwById(Long.parseLong(ids[i]));
    	  entity.setZt("2");
    	  shJzbgService.update(entity);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 讲座报告,前台页面
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年8月1日 
	 * @return
	 */
	@RequestMapping(value = "/shJzbgPage")
	@ResponseBody
	public ModelAndView shJzbgPage(String openId,HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			url = "/wsh/ShJzbg/shJzbgPage";
		}else{
			url="/sys/SysWxyh/wxbd";
			modelAndView.addObject("url","/wsh/ShJzbg/shJzbgPage");
		}
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){
			modelAndView = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = shJzbgService.getList(request);

		modelAndView.addObject("openId", openId);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("size",list.size());
		modelAndView.setViewName(url);
		}

		return modelAndView;
	}
	
	/**
	 * 前台点击讲座报告列表进入详细页面
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @return
	 */
	@RequestMapping(value = "/shJzbgpageDetail")
	public ModelAndView getDetailById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String id = request.getParameter("id");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = shJzbgService.getDetailById(id);
		for (Map<String, Object> map : list) {
			Long ids = Long.parseLong(map.get("id").toString());

			// 根据吐槽的ID获取回复的内容
			List<Map<String, Object>> pllist = shJzbgplService.getPinglun(ids,"");
			
			
			if(pllist.size()!=0){
				for(int i=0;i<pllist.size();i++){
					Map<String,Object> maps = pllist.get(i);
					String plnr = pllist.get(i).get("plnr").toString();
					String plstr = CalmUtil.filterGjz(plnr);
					System.out.println(plstr);
					maps.put("plnr", plstr);
					
				}
			}
			modelAndView.addObject("pllist",pllist);
//			map.put("pllist", pllist);
			
		}
		modelAndView.addObject("list",list);
		modelAndView.addObject("openId",openId);
		String url = "";
		url = "/wsh/ShJzbg/shJzbgpageDetail";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	
	/**
	 * 讲座报告评论
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年7月20日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doPlreply")
	@ResponseBody
	public ResponseData Reply(String plnr, Long id, String openId) {

		
		String result = shJzbgplService.savepl(plnr, id,openId);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 前台：讲座报告详情页面评论“删除”按钮
	 * @author zhangyan
	 * @date 2016年8月1日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletePj")
	@ResponseBody
	public ResponseData deletePj(Long id,HttpServletRequest request){
		shJzbgService.deletePj(id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台查看评论
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/shCkplDetail")
	@ResponseBody
	public ModelAndView shCkplDetail(Long id,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		SysYh user = AppUtil.getCurrentUser();
		String pages=request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		int count = shJzbgplService.getCount(id);
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShJzbg/shCkplDetail";
		List<Map<String, Object>> ckpllist = shJzbgplService.getPinglunht(id,pages);
		modelAndView.addObject("ckpllist", ckpllist);
		modelAndView.setViewName(url);
		modelAndView.addObject("id",id);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("count",count);
		return modelAndView;
	}
	
}


