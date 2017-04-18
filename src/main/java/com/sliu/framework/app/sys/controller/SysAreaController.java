package com.sliu.framework.app.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.formvalidator.FormValidatorManager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.bo.SysZzjgBO;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.sys.model.SysZzjg;
import com.sliu.framework.app.util.AppUtil;

/**
 * 区域 Controller
 * 
 * @author lxt
 * @since 2014-11-19 17:16:17
 */
@Controller
@RequestMapping("/sys/SysArea")
public class SysAreaController extends BaseController {

	protected final transient static Logger logger = LoggerFactory
			.getPresentationLog(SysAreaController.class);

	@Autowired
	private SysZzjgBO sysZzjgBO;

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysArea/add";
	}

	/*@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysArea", sysAreaBO.get(id));
		return "/sys/SysArea/detail";
	}*/
	
	@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysArea", sysZzjgBO.get(id));
		return "/sys/SysArea/detail";
	}

	/*@RequestMapping({ "/list" })
	public String tree(HttpServletRequest request, HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addSorted("sn", "asc");
		filter.setLimit(100000);
		request.setAttribute("area", this.sysAreaBO.getAll(filter));
		return "/sys/SysArea/list";
	}*/
	
	@RequestMapping({ "/list" })
	public String tree(HttpServletRequest request, HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addSorted("px", "asc");
		filter.setLimit(100000);
		request.setAttribute("area", this.sysZzjgBO.getAll(filter));
		return "/sys/SysArea/list";
	}

	/*@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model,
			@ModelAttribute("sysArea") SysArea entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysAreaConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysArea/add";
		}
		entity.setId(entity.getAreaCode());
		Result result = sysAreaBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysArea/add";
		}
		return "/common/success";
	}*/
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model,
			@ModelAttribute("sysArea") SysZzjg entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysZzjgConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysArea/add";
		}
		String sjbh=request.getParameter("parentId");
		entity.setId(entity.getBmbh());
		SysZzjg zzjg=sysZzjgBO.get(sjbh);
		entity.setJb(zzjg.getJb()+1);
		entity.setSjbh(sjbh);
		Result result = sysZzjgBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysArea/add";
		}
		return "/common/success";
	}

	/*@RequestMapping(value = "/delete")
	public String delete(ModelMap model, String[] ids) {
		for (String id : ids) {
			Result result = sysAreaBO.remove(id);
			if (!result.isSuccess()) {
				model.putAll(result);
				return "/common/error";
			}
		}

		return "/common/success";
	}*/
	
	@RequestMapping(value = "/delete")
	public String delete(ModelMap model, String[] ids) {
		for (String id : ids) {
			System.out.println("id:"+id);
			SysZzjg jg=sysZzjgBO.get(id);
			List<Map<String,Object>> user=sysZzjgBO.getYhByjg(jg.getBmbh());
			if(user.size()==0){
				Result result = sysZzjgBO.remove(id);
				if (!result.isSuccess()) {
					model.putAll(result);
					return "/common/error";
				}
			}else{
				return "/common/errorJg";
			}
		}

		return "/common/success";
	}

	/*@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysArea", sysAreaBO.get(id));
		return "/sys/SysArea/edit";
	}*/
	
	@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysArea", sysZzjgBO.get(id));
		return "/sys/SysArea/edit";
	}

	/*@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(String id, ModelMap model,
			@ModelAttribute("sysArea") SysArea entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysAreaConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysArea/edit";
		}

		Result result = sysAreaBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysArea/edit";
		}
		return "/common/success";
	}*/
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(String id, ModelMap model,
			@ModelAttribute("sysArea") SysZzjg entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysZzjgConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysArea/edit";
		}
		String sjbh=request.getParameter("parentId");
		entity.setSjbh(sjbh);
		Result result = sysZzjgBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysArea/edit";
		}
		return "/common/success";
	}

	/*@RequestMapping(value = "/findAllSysArea")
	public String findAllSysArea(String id, HttpServletRequest request,
			HttpServletResponse response) {
		List<SysArea> sysAreas = this.sysAreaBO.getAll();
		writeJson(response, this.sysAreaBO.getAreaToString(sysAreas));
		return null;
	}*/
	
	@RequestMapping(value = "/findAllSysArea")
	public String findAllSysArea(String id, HttpServletRequest request,
			HttpServletResponse response) {
		List<SysZzjg> sysAreas = this.sysZzjgBO.getAll();
		writeJson(response, this.sysZzjgBO.getAreaToString(sysAreas));
		return null;
	}

	/***********
	 * 查询机构下的所有信息
	 * @param id
	 * @param isparentId
	 * @return
	 */
	@RequestMapping(value="findAttacheArea")
	@ResponseBody
	public List<Map<String, Object>> findAttacheArea(String id,boolean isparentId){
		return sysZzjgBO.findAttacheArea(id, isparentId);
	}
	
	/************
	 * 查询某机构下的所有人员
	 * @return
	 */
	@RequestMapping(value="findOrgArchive")
	@ResponseBody
	public List<Map<String,Object>> findOrgArchive(Integer start,Integer limit,String[] OrgId){
		String str = OrgId==null?null:OrgId[(OrgId.length-1)].split(",")[0];
		return sysZzjgBO.findOrgArchive(start, limit, str);
	}
	
	@RequestMapping(value="orgTree2")
	@ResponseBody
	public  List<Map<String, Object>>  orgTree2(String node){
		 List<Map<String, Object>> list  = sysZzjgBO.getOrgTree(node);
		 SysYh user = AppUtil.getCurrentUser();
		 if (!user.getUsername().equals("admin") && StringUtils.equals(node, ""+user.getBmbh())) {
		     return null;
		 }
		 if(!list.isEmpty()){
			for(Map<String, Object> map :list){
				map.put("leaf", false);
				map.put("cls", "folder");
				map.put("checked", false);
			}
		 }
		return list;
	}
	
	@RequestMapping(value="orgTree")
	@ResponseBody
	public  List<Map<String, Object>>  orgTree(String node){
		 List<Map<String, Object>> list  = sysZzjgBO.getOrgTree(node);
		 if(!list.isEmpty()){
			for(Map<String, Object> map :list){
				map.put("leaf", false);
				map.put("cls", "folder");
			}
		 }
		return list;
	}
	
	@RequestMapping(value="orgTree1")
	@ResponseBody
	public  List<Map<String, Object>>  orgTree1(String node){
		 List<Map<String, Object>> list  = sysZzjgBO.getOrgTree1(node);
		 SysYh user = AppUtil.getCurrentUser();
		 if (!user.getUsername().equals("admin") && StringUtils.equals(node, ""+user.getBmbh())) {
		     return null;
		 }
		 if(!list.isEmpty()){
			for(Map<String, Object> map :list){
				map.put("leaf", false);
				map.put("cls", "folder");
				map.put("checked", false);
			}
		 }
		return list;
	}
	
	@RequestMapping(value="orgOrMebTree")
	@ResponseBody
	public  List<Map<String, Object>>  orgOrMebTree(String node){
		 List<Map<String, Object>> list  = sysZzjgBO.getOrgOrMebTree(node);
		 if(!list.isEmpty()){
			for(Map<String, Object> map :list){
				map.put("leaf", false);
				map.put("cls", "folder");
			}
		 }
		return list;
	}
	
	@RequestMapping(value="getOrgOrMebTree")
	@ResponseBody
	public  Map<String, Object>  getOrgOrMebTree(String _dc){
		 List<Map<String, Object>> list  = sysZzjgBO.getYhOrgOrMebTree(_dc);
//		 if(!list.isEmpty()){
//			for(Map<String, Object> map :list){
//				map.put("leaf", false);
//				map.put("cls", "folder");
//			}
//		 }
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("datasource", list);
		return map;
	}
}
