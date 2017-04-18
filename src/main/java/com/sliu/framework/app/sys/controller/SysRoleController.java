//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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
import com.sliu.framework.app.sys.bo.SysJsBO;
import com.sliu.framework.app.sys.bo.SysJscdBO;
import com.sliu.framework.app.sys.bo.SysYhjsBO;
import com.sliu.framework.app.sys.model.SysJs;
import com.sliu.framework.app.sys.service.SysMenuService;
import com.sliu.framework.app.sys.service.SysRoleService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 系统角色 Controller
 * 
 * @author lxt
 * @since 2014-11-04
 */
@Controller
@RequestMapping("/sys/SysRole")
public class SysRoleController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SysRoleController.class);

	@Autowired
	private SysJsBO sysJsBO;
	@Autowired
	private SysJscdBO sysJscdBO;
	@Autowired
	private SysYhjsBO sysYhjsBo;
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private SysMenuService menuService;

	@RequestMapping(value = "/rolelist")
	public ModelAndView openSysAdpzPage() {
		ModelAndView modelAndView = new ModelAndView("/sys/SysRole/rolelist");
		List<Map<String, Object>> listmyrole = sysRoleService.getMyRoleCd();
		if(listmyrole.size()>0){
			modelAndView.addObject("mycdbhs", ","+listmyrole.get(0).get("cdbh")+",");
		}
		return modelAndView;
	}
	
	/**
	 * 分页查询用户表
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月26日 下午2:44:18 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String, Object>> getList(Integer start,Integer limit,String jsmc,String jsms) {
		return sysRoleService.getList(start, limit,jsmc,jsms);
	}
	
	/**
	 * 添加用户角色
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:06:19 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData saveRole(ModelMap model, SysJs entity, HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> listRole = this.sysRoleService.findRoleByName(entity.getJsmc());
		if(listRole.size() >0){
			return ResponseData.FAILED_ROLE_RE;
		}
		sysRoleService.save(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 添加用户角色
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:06:19 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData updateRole(ModelMap model, SysJs entity,String newjsmc) {
		if(entity!=null){
			if(!entity.getJsmc().equalsIgnoreCase(newjsmc)){
				List<Map<String, Object>> listRole = this.sysRoleService.findRoleByName(newjsmc);
				if(listRole.size() >0){
					return ResponseData.FAILED_ROLE_RE;
				}
			}
		}
		sysRoleService.update(entity,newjsmc);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除系统用户
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:07:22 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteRole")
    @ResponseBody
    public ResponseData deleteRole(String[] jsbhs){
		sysRoleService.delete(jsbhs);
		return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 配置角色菜单
	 * @author:chenhui
	 * @version 创建时间：2015年10月23日 下午3:07:22 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/editJsCd")
    @ResponseBody
    public ResponseData editJsCd(String jsbh,String[] cdbhs){
		//sysRoleService.delete(jsbhs);
		sysRoleService.editJsCd(jsbh,cdbhs);
		return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 保存用户角色配置
	 * @author:liusong
	 * @version 创建时间：2015年10月23日 下午3:07:22 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/saveUserRole")
    @ResponseBody
    public ResponseData saveUserRole(String jsbh,String[] yhbhs){
		//sysRoleService.delete(jsbhs);
		sysRoleService.saveUserRole(jsbh,yhbhs);
		return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 取消用户角色配置
	 * @author:liusong
	 * @version 创建时间：2015年10月23日 下午3:07:22 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteUserRole")
    @ResponseBody
    public ResponseData deleteUserRole(String jsbh,String[] yhbhs){
		//sysRoleService.delete(jsbhs);
		sysRoleService.deleteUserRole(jsbh,yhbhs);
		return ResponseData.SUCCESS_NO_DATA;
    }
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysRole/add";
	}

	/*@RequestMapping(value = "/detail")
	public String detail(String roleId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysRole", sysRoleBO.get(roleId));
		return "/sys/SysRole/detail";
	}*/
	
	@RequestMapping(value = "/detail")
	public String detail(String roleId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysRole", sysJsBO.get(roleId));
		return "/sys/SysRole/detail";
	}

	/*@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		Pager pager = sysRoleBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysRole/list";
	}*/
	
	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		Pager pager = sysJsBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysRole/list";
	}

	/*@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model,
			@ModelAttribute("sysRole") SysRole entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysRoleConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysRole/add";
		}
		Result result = sysRoleBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysRole/add";
		}
		return "/common/success";
	}*/
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model,
			@ModelAttribute("sysRole") SysJs entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysJsConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysRole/add";
		}
		Result result = sysJsBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysRole/add";
		}
		return "/common/success";
	}

	/*@RequestMapping(value = "/delete")
	public String delete(String[] ids) {
		for (String id : ids) {
			sysRoleBO.remove(id);
			sysRoleResourceBO.removeRoleResource(id);
			sysUserRoleBo.removeAllUserRole(id);
		}
		return "/common/success";
	}*/
	
	@RequestMapping(value = "/delete")
	public String delete(String[] ids) {
		for (String id : ids) {
			sysJsBO.remove(id);
			sysJscdBO.removeRoleResource(id);
			sysYhjsBo.removeAllUserRole(id);
		}
		return "/common/success";
	}

	/*@RequestMapping(value = "/edit")
	public String edit(String roleId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysRole", sysRoleBO.get(roleId));
		return "/sys/SysRole/edit";
	}*/
	
	@RequestMapping(value = "/edit")
	public String edit(String roleId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysRole", sysJsBO.get(roleId));
		return "/sys/SysRole/edit";
	}

	/*@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysRole") SysRole entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysRoleConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysRole/edit";
		}
		Result result = sysRoleBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysRole/edit";
		}

		return "/common/success";
	}*/
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysRole") SysJs entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysJsConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysRole/edit";
		}
		Result result = sysJsBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysRole/edit";
		}

		return "/common/success";
	}

	/*@RequestMapping(value = "/authorize")
	public String ConfigurePermissions(String roleId, String roleResourceIds,
			HttpServletRequest request, HttpServletResponse response) {
		roleResourceIds = this.sysRoleResourceBO.findRoleResourceIds(roleId);
		request.setAttribute("roleResourceIds", roleResourceIds);
		return "/sys/SysRole/authorize";
	}*/
	
	@RequestMapping(value = "/authorize")
	public String ConfigurePermissions(String roleId, String roleResourceIds,
			HttpServletRequest request, HttpServletResponse response) {
		roleResourceIds = this.sysJscdBO.findRoleResourceIds(roleId);
		request.setAttribute("roleResourceIds", roleResourceIds);
		List<Map<String, Object>> alllist = menuService.getAllList();
		request.setAttribute("alllist", alllist);
		return "/sys/SysRole/authorize";
	}

	/*@RequestMapping(value = "/submitResource", method = RequestMethod.POST)
	public String submitResource(ModelMap model, String roleId,
			String resourceIds, HttpServletRequest request,
			HttpServletResponse response) {
		Result result = this.sysRoleResourceBO.doSubmitResource(resourceIds,
				roleId);
		if ("" != result.getErrormsg()) {
			model.putAll(result);
			return "/sys/SysRole/authorize";
		}
		cacheManager.getCache("resources").evict("FilterInvoSecMetaKey");
		return "/common/success";
	}*/
	
	@RequestMapping(value = "/submitResource", method = RequestMethod.POST)
	public String submitResource(ModelMap model, String roleId,
			String resourceIds, HttpServletRequest request,
			HttpServletResponse response) {
		Result result = this.sysJscdBO.doSubmitResource(resourceIds,
				roleId);
		if ("" != result.getErrormsg()) {
			model.putAll(result);
			return "/sys/SysRole/authorize";
		}
		cacheManager.getCache("resources").evict("FilterInvoSecMetaKey");
		return "/common/success";
	}


	/*@RequestMapping(value = "/authorizedUser")
	public String authorizedUser(String roleId, String initUserIds,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sysRole", sysRoleBO.get(roleId));
		request.setAttribute("initUserIds",
				sysUserRoleBo.findUserRoleIds(roleId));
		return "/sys/SysRole/authorize_user";
	}*/
	
	@RequestMapping(value = "/authorizedUser")
	public String authorizedUser(String roleId, String initUserIds,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sysRole", sysJsBO.get(roleId));
		request.setAttribute("initUserIds",
				sysYhjsBo.findUserRoleIds(roleId));
		return "/sys/SysRole/authorize_user";
	}

	/*@RequestMapping(value = "/doAnthorize", method = RequestMethod.POST)
	public String doAnthorize(ModelMap model, String roleId, String userIds,
			HttpServletRequest request, HttpServletResponse response) {
		Result result = this.sysUserRoleBo.doAnthorize(userIds, roleId);
		if ("" != result.getErrormsg()) {
			model.putAll(result);
			return "/sys/SysRole/authorize_user";
		}
		return "/common/success";
	}
	*/
	@RequestMapping(value = "/doAnthorize", method = RequestMethod.POST)
	public String doAnthorize(ModelMap model, String roleId, String userIds,
			HttpServletRequest request, HttpServletResponse response) {
		Result result = this.sysYhjsBo.doAnthorize(userIds, roleId);
		if ("" != result.getErrormsg()) {
			model.putAll(result);
			return "/sys/SysRole/authorize_user";
		}
		return "/common/success";
	}

	/*@RequestMapping(value = "/closeAnthorize", method = RequestMethod.POST)
	public String closeAnthorize(ModelMap model, String roleId, String userIds,
			HttpServletRequest request, HttpServletResponse response) {
		Result result = this.sysUserRoleBo.removeSysRoleResource(userIds,
				roleId);
		if ("" != result.getErrormsg()) {
			model.putAll(result);
			return "/sys/SysRole/authorize_user";
		}
		return "/common/success";
	}*/
	
	@RequestMapping(value = "/closeAnthorize", method = RequestMethod.POST)
	public String closeAnthorize(ModelMap model, String roleId, String userIds,
			HttpServletRequest request, HttpServletResponse response) {
		Result result = this.sysYhjsBo.removeSysRoleResource(userIds,
				roleId);
		if ("" != result.getErrormsg()) {
			model.putAll(result);
			return "/sys/SysRole/authorize_user";
		}
		return "/common/success";
	}

	/**
	 * 删除钱检验
	 * @author liujiansen
	 * @date 2016年2月22日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Integer check(String ids) {
		int str = 0;
		String[] arr=ids.split(";");
		for(int i=0;i<arr.length;i++){
			List<Map<String,Object>> list=sysYhjsBo.getYhByJs(arr[i]);
			if(list.size()!=0){
				str++;
			}
		}
		return str;
	}	
}
