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

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.formvalidator.FormValidatorManager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.bo.SysFolderBO;
import com.sliu.framework.app.sys.model.SysFolder;
import com.sliu.framework.app.util.AppUtil;

/**
 * 文件夹 Controller
 * 
 * @author lxt
 * @since 2014-11-17 14:01:11
 */
@Controller
@RequestMapping("/sys/SysFolder")
public class SysFolderController extends BaseController {

	protected final transient static Logger logger = LoggerFactory
			.getPresentationLog(SysFolderController.class);

	@Autowired
	private SysFolderBO sysFolderBO;

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysFolder/add";
	}

	@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysFolder", sysFolderBO.get(id));
		return "/sys/SysFolder/detail";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		return "/sys/SysFolder/list";
	}

	@RequestMapping({ "/tree" })
	public String tree(HttpServletRequest request, HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_userId_S_EQ", AppUtil.getCurrentUser().getYhbh());
		filter.addSorted("sn", "asc");
		filter.setLimit(100000);
		request.setAttribute("folders", this.sysFolderBO.getAll(filter));
		return "/sys/SysFolder/tree";
	}

	@RequestMapping(value = "/select")
	public String select() {
		return "/sys/SysFolder/select_list";
	}

	@RequestMapping(value = "/selectTree")
	public String selectTree(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_userId_S_EQ", AppUtil.getCurrentUser().getYhbh());
		filter.addSorted("sn", "asc");
		filter.setLimit(100000);
		request.setAttribute("folders", this.sysFolderBO.getAll(filter));
		return "/sys/SysFolder/select_tree";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model,
			@ModelAttribute("sysFolder") SysFolder entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysFolderConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysFolder/add";
		}
		entity.setUserId(AppUtil.getCurrentUser().getYhbh());
		Result result = sysFolderBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysFolder/add";
		}
		return "/common/success";
	}

	@RequestMapping(value = "/delete")
	public String delete(ModelMap model, String[] ids) {
		for (String id : ids) {
			Result result = sysFolderBO.remove(id);
			if (!result.isSuccess()) {
				model.putAll(result);
				return "/common/error";
			}
		}

		return "/common/success";
	}

	@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysFolder", sysFolderBO.get(id));
		return "/sys/SysFolder/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysFolder") SysFolder entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysFolderConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysFolder/edit";
		}
		Result result = sysFolderBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysFolder/edit";
		}
		return "/common/success";
	}
}
