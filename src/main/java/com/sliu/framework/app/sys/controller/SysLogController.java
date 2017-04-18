package com.sliu.framework.app.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.formvalidator.FormValidatorManager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.bo.SysLogBO;
import com.sliu.framework.app.sys.model.SysLog;
import com.sliu.framework.app.sys.service.SysLogService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 系统日志 Controller
 * 
 * @author lxt
 * @since 2014-11-20 09:40:56
 */
@Controller
@RequestMapping("/sys/SysLog")
public class SysLogController extends BaseController {

	protected final transient static Logger logger = LoggerFactory
			.getPresentationLog(SysLogController.class);

	@Autowired
	private SysLogBO sysLogBO;
	
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 功能：点击系统日志之后弹出的信息列表
	 * sysLog.js
	 * sysLog.jsp
	 * @author   chenhui
	 * @version 创建时间：2016年7月25日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getLogList")
	@ResponseBody
	public Pagination<Map<String, Object>> getLogList(Integer start,Integer limit){
		
		return sysLogService.getLogList(start, limit);
		
	}
	
	/**
	 * 
	 * @author   chenhui
	 * @version 创建时间：2016年7月25日
	 * @return
	 */
	@RequestMapping(value = "/logPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sys/SysLog/sysLogList");
		return modelAndView;
	}
	
	/**
	 * 删除
	 * @author   chenhui
	 * @version 创建时间：2016年7月25日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(String[] ids){
	sysLogService.delete(ids);
	return ResponseData.SUCCESS_NO_DATA;
	}
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysLog/add";
	}

	@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysLog", sysLogBO.get(id));
		return "/sys/SysLog/detail";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addSorted("operDate", "desc");
		Pager pager = sysLogBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysLog/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model, @ModelAttribute("sysLog") SysLog entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysLogConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysLog/add";
		}
		Result result = sysLogBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysLog/add";
		}
		return "/common/success";
	}

//	@RequestMapping(value = "/delete")
//	public String delete(String[] ids) {
//		for (String id : ids) {
//			sysLogBO.remove(id);
//		}
//		return "/common/success";
//	}

	@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysLog", sysLogBO.get(id));
		return "/sys/SysLog/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysLog") SysLog entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysLogConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysLog/edit";
		}
		Result result = sysLogBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysLog/edit";
		}
		return "/common/success";
	}
}
