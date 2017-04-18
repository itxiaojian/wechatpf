package com.sliu.framework.app.wzy.controller;

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
import com.sliu.framework.app.calmutil.CalmUtil;
import com.sliu.framework.app.calmutil.filter.core.FilterService;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.sys.service.SysGjzglService;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyXyxgxx;
import com.sliu.framework.app.wzy.service.ZyXyxgxxService;
import com.sliu.framework.app.wzy.service.ZyXyxwService;

/**
 * 主页学院相关信息
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午4:09:24
 */
@Controller
@RequestMapping("/wzy/ZyXyxgxx")
public class ZyXyxgxxController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZyXyxgxxController.class);

	@Autowired
	private ZyXyxgxxService zyXyxgxxService;
	
	@Autowired
	private SysGjzglService sysGjzglService;
	
	@Autowired
	private ZyXyxwService zyXyxwService;
	
	/**
	 * 功能：点击“保存”按钮
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:09:33
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveXYXGXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model,ZyXyxgxx entity,
			HttpServletRequest request,HttpServletResponse response){
		
		String result  = zyXyxgxxService.saveXyxgxx(entity);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改学院相关信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年12月15日 上午11:25:06 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateXYXGXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ZyXyxgxx entity,Long id){
		
		String result  = zyXyxgxxService.updateXyxgxx(entity,id);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:09:42
	 * @return
	 */
	@RequestMapping(value = "/xyxgxxPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyXyxgxxList");
		return modelAndView;
	}
	
	/**
	 *  功能：点击学院相关信息信息之后弹出的信息列表
	 * zyXyxgxx.js
	 * zyXyxgxx.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:09:48
	 * @param start
	 * @param limit
	 * @param xwlx
	 * @param xwbt
	 * @return
	 */
	@RequestMapping(value = "/getXyxgxxList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXyxgxxList(Integer start,
			Integer limit, String xwlx, String xwbt){
		Pagination<Map<String, Object>> list = zyXyxgxxService.getXyxgxxList(start, 
				limit, xwlx, xwbt);
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
	@RequestMapping(value = "/zyXyxgxxListPage")
	public ModelAndView openTaskDealPage(String zdmc, Integer zdz) {

		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();

		String url = "";
		
		url = "/wzy/zyXyxgxxListPage";
		
       List<Map<String, Object>> list = zyXyxgxxService.getTjcx(zdmc, zdz);	
		modelAndView.addObject("list",list);
		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:04:41
	 * @return
	 */
	@RequestMapping(value = "/zyXyxgxxUpdatePage")
	public ModelAndView zyXyxgxxUpdatePage(String id) {

		SysYh user = AppUtil.getCurrentUser();
		ZyXyxgxx zyXyxgxx = zyXyxgxxService.getXyxgxxById(Long.parseLong(id));

		Map<String, Object> map = (zyXyxgxx instanceof Map) ? (Map<String, Object>) zyXyxgxx : beanToMap(zyXyxgxx);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("map", map);
		
		List<Map<String, Object>> list = zyXyxgxxService.getTjcx("", 2);	
		modelAndView.addObject("list",list);
		
		String url = "";

		url = "/wzy/zyXyxgxxUpdatePage";

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 功能：跳转到新闻详情页面
	 * viewXyxgxx.jsp
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:10:02
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/zyXyxgxxDetail")
	@ResponseBody
	public ModelAndView zyXyxgxxDetail(Long id) {

		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wzy/zyXyxgxxDetail";

		ZyXyxgxx zyXyxgxx = zyXyxgxxService.getXyxgxxById(id);

		Map<String, Object> map = (zyXyxgxx instanceof Map) ? (Map<String, Object>) zyXyxgxx : beanToMap(zyXyxgxx);
		
		if(map!=null){
			String xwnr = map.get("xwnr").toString();
			String newstr = CalmUtil.filterGjz(xwnr);//获取过滤后的值
			System.out.println(newstr);
			map.put("xwnr", newstr);
		}

		modelAndView.addObject("map", map);

		modelAndView.setViewName(url);

		return modelAndView;
	}
	
	/**
	 * 分类查询页面跳转：学院简介，学院风光，办事流程等。。。
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月5日  下午4:54:12
	 * @param zdbm    字典编码
	 * @return
	 */
	@RequestMapping(value = "/zyXyjjDetail")
	@ResponseBody
    public ModelAndView zyXyjjDetail(String zdbm,String openId){
		
		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		List<Map<String, Object>> list = zyXyxgxxService.getXyjj(zdbm);
		if(list.size()==0){
			Map<String, Object> map = null;
			//学院简介
			if("xyjj".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("map",map);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXyjjDetail";
			//办事流程
			}else if("bslc".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyBslclbDetail";
			//学院风光
			}else if("xyfg".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list); 
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXyfglbDetail";
			//规章制度
			}else if("gzzd".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyGzzdlbDetail";
			//宿管动态
			}else if("sgdt".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list);
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zySgdtlbDetail";
					//后勤动态
			}else if("hqdt".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list);
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyHqdtlbDetail";
			//服务帮助
			}else if("fwbz".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyFwbzlbDetail";
			//组织架构
			}else if("zzjg".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyZzjglbDetail";
			//学院校历	
			}else if("xyxl".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("map",map);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXlDetail";
			//招生考试
			}else if("yztj".equalsIgnoreCase(zdbm)){
					List<Map<String, Object>> listmenu = zyXyxwService.getListMenu("0");
					modelAndView.addObject("listmenu", listmenu);
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyYztjlbDetail";
			//学生荣誉墙
			}else if("xsryq".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list); 
//					modelAndView.addObject("map",map);
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXsryqlbDetail";
			//关于我们
			}else if("gywm".equalsIgnoreCase(zdbm)){
//					modelAndView.addObject("list",list); 
					modelAndView.addObject("map",map);
//					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyGywm";
			}else if("httz".equalsIgnoreCase(zdbm)){
				modelAndView.addObject("list",list); 
//				modelAndView.addObject("map",map);
				modelAndView.addObject("num",list.size()); 
				modelAndView.addObject("openId",openId);
				url = "/wfw/zyHttzlbList";
			}
		}else{
			Map<String, Object> map = (list.get(0) instanceof Map) ? (Map<String, Object>) list.get(0) : beanToMap(list.get(0));
			//学院简介
			if("xyjj".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("map",map);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXyjjDetail";
				}
			//办事流程
			}else if("bslc".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyBslclbDetail";
			//学院风光
			}else if("xyfg".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("list",list); 
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXyfglbDetail";
				}
			//规章制度
			}else if("gzzd".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyGzzdlbDetail";
				}
			//宿管动态
			}else if("sgdt".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("list",list);
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zySgdtlbDetail";
				}
				//后勤动态
			}else if("hqdt".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("list",list);
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyHqdtlbDetail";
				}
			//服务帮助
			}else if("fwbz".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("list",list);
//					modelAndView.addObject("map",map);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyFwbzlbDetail";
				}
			//组织架构
			}else if("zzjg".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyZzjglbDetail";
				}
			//学院校历	
			}else if("xyxl".equalsIgnoreCase(zdbm)){
				if(map.size()>0){
					modelAndView.addObject("map",map);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXlDetail";
							}
				//招生考试
			}else if("yztj".equalsIgnoreCase(zdbm)){
					List<Map<String, Object>> listmenu = zyXyxwService.getListMenu("0");
					modelAndView.addObject("listmenu", listmenu);
					modelAndView.addObject("list",list);
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyYztjlbDetail";
			//学生荣誉墙
			}else if("xsryq".equalsIgnoreCase(zdbm)){
					modelAndView.addObject("list",list); 
	//				modelAndView.addObject("map",map);
					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyXsryqlbDetail";
					//关于我们
			}else if("gywm".equalsIgnoreCase(zdbm)){
//					modelAndView.addObject("list",list); 
					modelAndView.addObject("map",map);
//					modelAndView.addObject("num",list.size()); 
					modelAndView.addObject("openId",openId);
					url = "/wzy/zyGywm";
			}else if("httz".equalsIgnoreCase(zdbm)){
				modelAndView.addObject("list",list); 
//				modelAndView.addObject("map",map);
				modelAndView.addObject("num",list.size()); 
				modelAndView.addObject("openId",openId);
				url = "/wfw/zyHttzlbList";
				//服务帮助
			}else if("fwbz".equalsIgnoreCase(zdbm)){
			if(map.size()>0){
				modelAndView.addObject("map",map);
				modelAndView.addObject("openId",openId);
				url = "/wzy/zyFwbzDetail";
			}
		//组织架构
		}else if("zzjg".equalsIgnoreCase(zdbm)){
			if(map.size()>0){
				modelAndView.addObject("list",list);
				modelAndView.addObject("openId",openId);
				url = "/wzy/zyZzjglbDetail";
			}
		//学院校历	
		}else if("xyxl".equalsIgnoreCase(zdbm)){
			if(map.size()>0){
				modelAndView.addObject("map",map);
				modelAndView.addObject("openId",openId);
				url = "/wzy/zyXlDetail";
			}
		//迎新专栏
		}else if("yxgg".equalsIgnoreCase(zdbm)){
			modelAndView.addObject("list",list);
			modelAndView.addObject("openId",openId);
			url = "/wzy/zyYxggDetail";
		//关于我们
		}else if("gywm".equalsIgnoreCase(zdbm)){
						if(map.size()>0){
							modelAndView.addObject("map",map);
							modelAndView.addObject("list",list);
							modelAndView.addObject("openId",openId);
							url = "/wzy/zyGywm";
			}
						}
		}
		modelAndView.setViewName(url);
			return modelAndView;
	}
	
	/**
	 * 删除学院相关信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月11日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		zyXyxgxxService.deleteXyxgxx(ids);
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
