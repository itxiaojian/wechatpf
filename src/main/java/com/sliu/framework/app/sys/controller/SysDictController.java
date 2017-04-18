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

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.bo.SysSjzdBO;
import com.sliu.framework.app.sys.model.SysSjzd;
import com.sliu.framework.app.sys.service.SysSjzdService;
import com.sliu.framework.app.util.DictionaryMap;
import com.sliu.framework.app.util.ResponseData;

/**
 * 数据字典 Controller
 * 
 * @author lxt
 * @since 2014-11-06 11:15:04
 */
@Controller
@RequestMapping("/sys/SysDict")
public class SysDictController extends BaseController {

	protected final transient static Logger logger = LoggerFactory
			.getPresentationLog(SysDictController.class);

	@Autowired
	private SysSjzdBO sysSjzdBO;
	
	@Autowired
	private SysSjzdService sysSjzdService;

	@Autowired
	private DictionaryMap dictionaryMap;
	
	
	@RequestMapping(value = "/dictlist")
	public String openSysDictPage() {
		return "/sys/SysDict/dictlist";
	}
	
	/**
	 * 分页查询字典列表
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月26日 下午2:44:18 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getFirstDict", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String, Object>> getFirstDict(Integer start,Integer limit,String zdzl,String zdmc) {
		return sysSjzdService.getFirstList(start, limit,zdzl,zdmc);
	}	
	/**
	 * 分页查询字典列表
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月26日 下午2:44:18 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSecondDict", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String, Object>> getSecondDict(Integer start,Integer limit,String zl) {
		return sysSjzdService.getSecondDict(start, limit,zl);
	}

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysDict/add";
	}

	/*@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysDict", sysDictBO.get(id));
		return "/sys/SysDict/detail";
	}*/
	
	@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysDict", sysSjzdBO.get(id));
		return "/sys/SysDict/detail";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addSorted("px", "asc");
		filter.addSorted("zl", "asc");
		Pager pager = sysSjzdBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysDict/list";
	}

	/*@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model,
			@ModelAttribute("sysDict") SysDict entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysDictConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysDict/add";
		}
		Result result = sysDictBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysDict/add";
		}
		return "/common/success";
	}*/
	
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String save(ModelMap model,
//			@ModelAttribute("sysDict") SysSjzd entity,
//			HttpServletRequest request, HttpServletResponse response) {
//		Map<String, Object> errors = FormValidatorManager.validate(
//				"saveSysSjzdConfig", request);
//		if (errors.size() != 0) {
//			return "/sys/SysDict/add";
//		}
//		Result result = sysSjzdBO.save(entity);
//		if (!result.isSuccess()) {
//			model.putAll(result);
//			return "/sys/SysDict/add";
//		}
//		return "/common/success";
//	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model,
			@ModelAttribute("sysDict") SysSjzd entity,
			HttpServletRequest request, HttpServletResponse response) {
		String checkzl="";
		if(entity.getJb().equals(1)){
			checkzl= sysSjzdService.checkzl(entity.getZl());
		}
		if(checkzl.equals("")){
			sysSjzdService.save(entity);
			return ResponseData.SUCCESS_NO_DATA;
		}else{
			return new ResponseData(false,"已存在"+entity.getZl()+"种类的数据字典，请修改后重新提交！");
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ModelMap model,
			@ModelAttribute("sysDict") SysSjzd entity,
			HttpServletRequest request, HttpServletResponse response) {
		 String str = sysSjzdService.update(entity);
		if(str.equals("1")){
			return ResponseData.SUCCESS_NO_DATA;
		}else{
			return new ResponseData(false,"修改失败，请检查后重新提交！");
		}
	}

	/*@RequestMapping(value = "/delete")
	public String delete(String[] ids) {
		for (String id : ids) {
			sysDictBO.remove(id);
		}
		dictionaryMap.clear();
		return "/common/success";
	}*/
	
//	@RequestMapping(value = "/delete")
//	public String delete(String[] ids) {
//		for (String id : ids) {
//			sysSjzdBO.remove(id);
//		}
//		dictionaryMap.clear();
//		return "/common/success";
//	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(String id,String jb,String zl) {
		if(jb.equals("1")){
			sysSjzdService.deleteson(zl);
		}
			sysSjzdService.delete(id);
		return ResponseData.SUCCESS_NO_DATA;
	}

	/*@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysDict", sysDictBO.get(id));
		return "/sys/SysDict/edit";
	}*/
	
	@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysDict", sysSjzdBO.get(id));
		return "/sys/SysDict/edit";
	}

	/*@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysDict") SysDict entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysDictConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysDict/edit";
		}
		Result result = sysDictBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysDict/edit";
		}
		dictionaryMap.clear();
		return "/common/success";
	}*/
	
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(ModelMap model,
//			@ModelAttribute("sysDict") SysSjzd entity,
//			HttpServletRequest request, HttpServletResponse response) {
//		Map<String, Object> errors = FormValidatorManager.validate(
//				"saveSysSjzdConfig", request);
//		if (errors.size() != 0) {
//			return "/sys/SysDict/edit";
//		}
//		Result result = sysSjzdBO.update(entity);
//		if (!result.isSuccess()) {
//			model.putAll(result);
//			return "/sys/SysDict/edit";
//		}
//		dictionaryMap.clear();
//		return "/common/success";
//	}
}
