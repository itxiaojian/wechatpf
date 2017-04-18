package com.sliu.framework.app.sys.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysMenu;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.sys.service.SysMenuService;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;



@Controller
@RequestMapping("/sys/SysMenu")
public class SysMenuController extends BaseController{
	
	protected final transient Logger logger = LoggerFactory.getPresentationLog(SysMenuController.class);
	
	@Autowired
	private SysMenuService service;
	
	
	@RequestMapping(value = "/menulist")
	public ModelAndView openSysAdpzPage() {
		ModelAndView modelAndView = new ModelAndView("/sys/SysMenu/menulist");
		return modelAndView;
	}
	
	/**
	 * 分页查询菜单父节点信息
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getFirstMenu", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Object> getFirstMenu(Integer start,Integer limit) {
		return service.getFirstMenu(start, limit);
	}
	
	/**
	 * 分页查询菜单子节点信息
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSonMenu", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Object> getSonMenu(Integer start,Integer limit,Integer sjid) {
		return service.getSonMenu(start, limit,sjid);
	}
	
	/**
	 * 保存父菜单
	 * 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, SysMenu entity,
			HttpServletRequest request, HttpServletResponse response) {
  		String result = service.save(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return new ResponseData(false,"保存失败，请联系管理员！");
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改父菜单
	 * @author:chenhui
	 * @version 创建时间：2016年6月4日 上午9:19:04 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ModelMap model, SysMenu entity,
			HttpServletRequest request, HttpServletResponse response) {
		//查询该图文是否绑定属性cd信息
		     String  result = service.update(entity);
  			 if ("1".equalsIgnoreCase(result)) {
  				 return ResponseData.SUCCESS_NO_DATA;
  			 }else{
  				 return new ResponseData(false,"保存失败，请联系管理员！");
  			 }
	}
	
	/**
	 * 删除菜单
	 * @author:chenhui
	 * @version 创建时间：2015年6月4日 上午10:10:07 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(String[] ids)
    {
      for (String cdbh : ids)
      {
    	  //查询该图文是否绑定属性cd信息
    	  List<Map<String,Object>> list = service.getSonMenu(cdbh);
    	  if(list.isEmpty()){
    		  //删除
    		  service.delete(cdbh);
    	  }else{
    		  return new ResponseData(false,"该菜单还有下级菜单，请先删除下级菜单");
    	  }
    	 
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 登陆后页面重定向跳转至mainjsp
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午10:10:07 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView("/sys/SysMenu/main");
		SysYh user=AppUtil.getCurrentUser();
		List<Map<String,Object>> CurrentUserMenu = service.getCurrentUserMenu(user.getYhbh());
		modelAndView.addObject("CurrentUserMenu", CurrentUserMenu);
		return modelAndView;
	}

}
