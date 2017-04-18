package com.sliu.framework.app.wsh.controller;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
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
import com.sliu.framework.app.wsh.model.ShZttl;
import com.sliu.framework.app.wsh.service.ShZttlService;
import com.sliu.framework.app.wsh.service.ShZttlplService;

/**
 * 主页——专题讨论
 * 
 * @author zhangyan
 * @version 创建时间：2016年7月14日 
 */
@Controller
@RequestMapping("/wsh/ShZttl")
public class ShZttlController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShZttlController.class);

	@Autowired
	private ShZttlService shZttlService;
	@Autowired
	private ShZttlplService shZttlplService;
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 *  功能：点击主菜单"专题讨论"后显示的列表
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @return
	 */
	@RequestMapping(value = "/zttlPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/wsh/ShZttl/shZttlList");
		return modelAndView;
	}
	
	
	/**
	 * 分页查询 专题讨论
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	* @param start
	 * @param limit
	 * @param ztmc  专题名称
	 * @param ztbt  专题标题
	 * @return
	 */
	@RequestMapping(value = "/getZttlList")
	@ResponseBody
	public Pagination<Map<String, Object>> getZttlList(Integer start,
			Integer limit, String ztmc, String ztbt) {
		Pagination<Map<String, Object>> list = shZttlService.getZttlList(start,
				limit, ztmc, ztbt);
		return list;
	}
	
	/**
	 * 
	 * @author   zhangyan
	 * @version 创建时间：2016年7月18日  
	 * @return
	 */
	@RequestMapping(value = "/shZttlListPage")
	public ModelAndView openTaskDealPage() {
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShZttl/shZttlListPage";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 后台修改页面
	 * @author   zhangyan
	 * @version 创建时间：2016年7月20日  
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/shZttlUpdatePage")
	public ModelAndView zyXyxwUpdatePage(String id) throws ParseException {
		ShZttl shZttl = shZttlService.getXyxwById(Long.parseLong(id));
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sj = time.format(shZttl.getJhjssj());
		shZttl.setJhjssj(time.parse(sj));
		Map<String, Object> map = (shZttl instanceof Map) ? (Map<String, Object>) shZttl : beanToMap(shZttl);
		ModelAndView modelAndView = new ModelAndView();
		map.put("jssj", sj);
		modelAndView.addObject("map", map);
		String url = "";
		url = "/wsh/ShZttl/shZttlUpatePage";
		modelAndView.setViewName(url);
		return modelAndView;
	}

	/**
	 * 添加专题
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveZttl", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, ShZttl entity,
			HttpServletRequest request, HttpServletResponse response) {
		String result = shZttlService.saveZttl(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改专题
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateZttl", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ShZttl entity,Long id){
		
		String result  = shZttlService.updateZttl(entity,id);
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
    	  shZttlService.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	
	
	/**
	 * 查看详细专题内容
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/shZttlDetail")
	@ResponseBody
	public ModelAndView shZttlDetail(Long id) {
		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShZttl/shZttlDetail";
		ShZttl shZttl = shZttlService.getXyxwById(id);
		Map<String, Object> map = (shZttl instanceof Map) ? (Map<String, Object>) shZttl : beanToMap(shZttl);
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
	 * 专题讨论,前台页面
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年7月19日 
	 * @return
	 */
	@RequestMapping(value = "/shZttlPage")
	@ResponseBody
	public ModelAndView zyWdtcDetail(String openId,HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			url = "/wsh/ShZttl/shZttlPage";
		}else{
			url="/sys/SysWxyh/wxbd";
			modelAndView.addObject("url","/wsh/ShZttl/shZttlPage");
		}
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){
			modelAndView = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = shZttlService.getList(request);

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
	@RequestMapping(value = "/shZttlpageDetail")
	public ModelAndView getDetailById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String id = request.getParameter("id");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> list = shZttlService.getDetailById(id);
		for (Map<String, Object> map : list) {
			Long ids = Long.parseLong(map.get("id").toString());

			// 根据吐槽的ID获取回复的内容
			List<Map<String, Object>> pllist = shZttlplService.getPinglun(ids,"");
			
			map.put("pllist", pllist);
		}
		modelAndView.addObject("list",list);
		modelAndView.addObject("openId",openId);
		String url = "";
		url = "/wsh/ShZttl/shZttlpageDetail";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	
	/**
	 * 专题评论
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

		
		String result = shZttlplService.savepl(plnr, id,openId);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 前台：专题详情页面评论“删除”按钮
	 * @author zhangyan
	 * @date 2016年7月21日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteWdtcList")
	@ResponseBody
	public ResponseData deleteWdtcList(Long id,HttpServletRequest request){
		shZttlService.deleteWdtcList(id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 查看评论
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
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
		int count = shZttlplService.getCount(id);
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/wsh/ShZttl/shCkplDetail";
		List<Map<String, Object>> ckpllist = shZttlplService.getPinglunht(id,pages);
		modelAndView.addObject("ckpllist", ckpllist);
		modelAndView.setViewName(url);
		modelAndView.addObject("id",id);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("count",count);
		return modelAndView;
	}
	
	/**
	 * 添加专题最后结果
	 * @author   zhangyan
	 * @version 创建时间：2016年7月21日  
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/savezhjg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData savezhjg(Long id,String ztzhjg) {
		
//		String id =request.getParameter("id");
//		String ztzhjg =request.getParameter("ztzhjg");
		ShZttl entity =shZttlService.get(id);
		entity.setZtzhjg(ztzhjg);
		entity.setZt("2");
		shZttlService.update(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	 
}

