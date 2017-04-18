package com.sliu.framework.app.wxzdycd.controller;

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

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxZdycd;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxzdycd.service.WxZdycdService;

@Controller
@RequestMapping(value = "/wxauth/menu")
public class WxZdycdController extends BaseController{

	@Autowired
	private WxZdycdService wxZdycdService;
	
	@RequestMapping(value = "/menuPage")
	public String openWxZdycdPage() {		
		return "/weixin/menu/menuList";
	}
	
	/**
	 * 分页查询微信分组信息
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getFirstMenu", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Object> getFirstMenu(Integer start,Integer limit) {
		return wxZdycdService.getFirstMenu(start, limit);
	}
	
	@RequestMapping(value = "/getSonMenu", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Object> getSonMenu(Integer start,Integer limit,Integer sjid) {
		return wxZdycdService.getSonMenu(start, limit,sjid);
	}
	
	/**
	 * 根据字典种类找出字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicByLx", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return wxZdycdService.getDicByLx("cdlx");
	}
	
	/**
	 * 添加校园新闻
	 * 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, WxZdycd entity,
			HttpServletRequest request, HttpServletResponse response) {
		
		String result = wxZdycdService.save(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:19:04 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ModelMap model, WxZdycd entity,
			HttpServletRequest request, HttpServletResponse response) {
		
		String result = wxZdycdService.update(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午10:10:07 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids)
    {
      for (Long id : ids)
      {
    	  //查询该图文是否绑定属性cd信息
    	  List<Map<String,Object>> list = wxZdycdService.getSonMenu(id);
    	  if(list.isEmpty()){
    		  //删除
    		  wxZdycdService.delete(id);
    	  }else{
    		  return new ResponseData(false,"该菜单还有下级菜单，请先删除下级菜单");
    	  }
    	 
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 与微信平台同步自定义菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 下午2:20:01 
	 * @return
	 */
	@RequestMapping(value = "/refreshWxZdycd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refreshWxZdycd() {
		wxZdycdService.refreshWxZdycd();
		return ResponseData.SUCCESS_NO_DATA;
	}
	
//	/**
//	 * 添加微信分组信息
//	 * @param groupName
//	 * @return
//	 */
//	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseData addGroup(String groupName) {
//		return wxZdycdService.addGroup(groupName);
//	}
//	
//	/**
//	 * 更新微信分组信息
//	 * @param groupId
//	 * @param groupName
//	 * @return
//	 */
//	@RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseData updateGroup(Long groupId, String groupName) {
//		return wxZdycdService.updateGroup(groupId, groupName);
//	}
//	
//	/**
//	 * 与微信平台同步微信分组信息
//	 * @return
//	 */
//	@RequestMapping(value = "/refreshWxZdycd", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseData refreshWxZdycd() {
//		wxZdycdService.refreshWxZdycd();
//		return ResponseData.SUCCESS_NO_DATA;
//	}
}
