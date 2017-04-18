package com.sliu.framework.app.sys.controller;

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
import com.sliu.framework.app.sys.model.SysAdpz;
import com.sliu.framework.app.sys.service.SysAdpzService;
import com.sliu.framework.app.util.ResponseData;

/**
 * ad域服务器配置管理
 * @author : zhangyi
 * @version 创建时间：2015年10月26日 上午10:45:28
 */
@Controller
@RequestMapping(value = "/sys/sysAdpz")
public class SysAdpzController extends BaseController{

	@Autowired
	private SysAdpzService sysAdpzService;
	
	@RequestMapping(value = "/adpzPage")
	public String openSysAdpzPage() {
		return "/sys/SysAdpz/adpzList";
	}
	
	/**
	 * 分页查询ad服务器配置列表
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月26日 下午2:44:18 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Object> getList(Integer start,Integer limit) {
		return sysAdpzService.getList(start, limit);
	}
	
	
	
	/**
	 * 添加ad配置
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:06:19 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, SysAdpz entity,
			HttpServletRequest request, HttpServletResponse response) {
		String resultTest = sysAdpzService.testConnect(entity);
		if (!"1".equalsIgnoreCase(resultTest)) {
			return ResponseData.FAILED_TEST_ADCONNET;
		}
		String result = sysAdpzService.save(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 测试连接
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月27日 下午3:48:28 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/testConnect", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData testConnect(ModelMap model, SysAdpz entity,
			HttpServletRequest request, HttpServletResponse response) {
		
		String result = sysAdpzService.testConnect(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_TEST_ADCONNET;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改配置
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:07:09 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ModelMap model, SysAdpz entity,
			HttpServletRequest request, HttpServletResponse response) {
		
		String resultTest = sysAdpzService.testConnect(entity);
		if (!"1".equalsIgnoreCase(resultTest)) {
			return ResponseData.FAILED_TEST_ADCONNET;
		}
		String result = sysAdpzService.update(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除配置
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:07:22 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids){
      for (Long id : ids) {
    	 sysAdpzService.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
}
