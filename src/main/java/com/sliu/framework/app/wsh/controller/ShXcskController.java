package com.sliu.framework.app.wsh.controller;

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
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.model.ShXcsk;
import com.sliu.framework.app.wsh.service.ShXcskService;

/**
 * 
 * @author  oufeng
 * @version 创建时间：2015年6月16日  下午13:49:44
 */
@Controller
@RequestMapping("/wsh/ShXcsk")
public class ShXcskController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShXcskController.class);
	
	@Autowired
	private ShXcskService shXcskService;
	
	/**
	 * 
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getXcskList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXcskList(Integer start,Integer limit,String cph){
	    if(cph != null){
		  return shXcskService.getXcskListByCph(start,limit,cph);
	}else{
		 return shXcskService.getXcskList(start,limit);
	}	
 }
	
	/**
	 * 
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @return
	 */
	@RequestMapping(value = "/shXcsk")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wsh/ShXcsk/shXcsk");
		return modelAndView;
	}
	
	/**
	 * 增加
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,ShXcsk entity,
			HttpServletRequest request,HttpServletResponse response){
		shXcskService.save(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 编辑
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ShXcsk entity, String id) {
		shXcskService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
		shXcskService.delete(ids);
	return ResponseData.SUCCESS_NO_DATA;
	}
}
