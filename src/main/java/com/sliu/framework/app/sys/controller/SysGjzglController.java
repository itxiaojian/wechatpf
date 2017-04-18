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

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysGjzgl;
import com.sliu.framework.app.sys.service.SysGjzglService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 关键字管理
 * @author : chenhui
 * @version 创建时间：2015年10月26日 上午10:45:28
 */
@Controller
@RequestMapping(value = "/sys/sysGjzgl")
public class SysGjzglController extends BaseController{

	@Autowired
	private SysGjzglService sysGjzglService;
	
	@RequestMapping(value = "/gjzglPage")
	public String openSysAdpzPage() {
		return "/sys/SysGjzgl/gjzglList";
	}
	
	/**
	 * 分页查询关键字管理列表
	 * @author: chenhui 
	 * @version 创建时间：2015年10月26日 下午2:44:18 
	 * @param start
	 * @param limit
	 * @return
	 */

	@RequestMapping(value = "/getSysGjzglList")
	@ResponseBody
	public Pagination<Map<String, Object>> getSysGjzglList(Integer start, Integer limit) {
		return sysGjzglService.getSysGjzglList(start,limit);
	}
	
	/**
	 * 添加
	 * @author: chenhui 
	 * @version 创建时间：2015年10月23日 下午3:06:19 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ModelMap model, SysGjzgl entity,HttpServletRequest request, HttpServletResponse response) {
		String result = sysGjzglService.update(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;		
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData add(ModelMap model, SysGjzgl wxZdycd,HttpServletRequest request, HttpServletResponse response) {
		String result = sysGjzglService.save(wxZdycd);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
	     return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除配置
	 * @author:chenhui 
	 * @version 创建时间：2015年10月23日 下午3:07:22 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids){
      for (Long id : ids) {
    	 sysGjzglService.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 根据字典种类找出字典
	   @author:zhangyi 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicByLx", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return sysGjzglService.getDicByLx("zt");
	}
}
