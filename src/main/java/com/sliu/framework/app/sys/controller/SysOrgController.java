//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
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
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.formvalidator.FormValidatorManager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.bo.SysOrgBO;
import com.sliu.framework.app.sys.model.SysOrg;

/**
 * 组织机构 Controller
 * 
 * @author lxt
 * @since 2014-11-04
 */
@Controller
@RequestMapping("/sys/SysOrg")
public class SysOrgController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SysOrgController.class);

	@Autowired
	private SysOrgBO sysOrgBO;

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysOrg/add";
	}

	@RequestMapping(value = "/detail")
	public String detail(String orgId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysOrg", sysOrgBO.get(orgId));
		return "/sys/SysOrg/detail";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		Pager pager = sysOrgBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysOrg/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model, @ModelAttribute("sysOrg") SysOrg entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysOrgConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysOrg/add";
		}
		Result result = sysOrgBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysOrg/add";
		}
		return "/common/success";
	}

	@RequestMapping(value = "/delete")
	public String delete(String[] ids) {
		for (String id : ids) {
			sysOrgBO.remove(id);
		}
		return "/common/success";
	}

	@RequestMapping(value = "/edit")
	public String edit(String orgId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysOrg", sysOrgBO.get(orgId));
		return "/sys/SysOrg/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysOrg") SysOrg entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysOrgConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysOrg/edit";
		}
		Result result = sysOrgBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysOrg/edit";
		}
		return "/common/success";
	}
}
