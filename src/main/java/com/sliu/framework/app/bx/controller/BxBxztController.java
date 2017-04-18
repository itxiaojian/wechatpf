package com.sliu.framework.app.bx.controller;

import java.util.List;
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
import com.likegene.framework.core.Result;
import com.likegene.framework.core.formvalidator.FormValidatorManager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.bx.model.BxBxzt;
import com.sliu.framework.app.bx.service.BxBxztService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 报修主题后台配置
 * @author oufeng
 * @since 2015-08-06 13:16:17
 */
@Controller
@RequestMapping("/bx/bxzt")
public class BxBxztController extends BaseController {

	protected final transient static Logger logger = LoggerFactory
			.getPresentationLog(BxBxztController.class);

	@Autowired
	private BxBxztService bxztService;

	/**
	 * 打开页面
	 * @author:chenhui
	 * @version 创建时间：2016年7月27日  
	 * @return
	 */
	@RequestMapping(value="/openBxztPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/bx/bxzt/bxzt");
		return modelAndView;
	}
	
	@RequestMapping(value="orgOrMebTree")
	@ResponseBody
	public  List<Map<String, Object>>  orgOrMebTree(String node){
		 List<Map<String, Object>> list  = bxztService.getOrgOrMebTree(node);
		 if(!list.isEmpty()){
			for(Map<String, Object> map :list){
				map.put("leaf", false);
				map.put("cls", "folder");
			}
		 }
		return list;
	}
	
	/**
	 * 编辑
	 * @author:oufeng
	 * @version 创建时间：2016年7月27日  
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public ResponseData editor(HttpServletRequest request){
		String code="1";
		String str=bxztService.update(request);
		if("1".equals(str)){
			return  ResponseData.SUCCESS_NO_DATA;
		}else if("2".equals(str)){
			 return new ResponseData(true, code);
		}else{
			return ResponseData.FAILED_NO_DATA;
		}
	}
	
	/**
	 * 保存
	 * @author:oufeng
	 * @version 创建时间：2016年7月27日  
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public ResponseData save(HttpServletRequest request){
		String str=bxztService.save(request);
		if("1".equals(str)){
			return  ResponseData.SUCCESS_NO_DATA;
		}else if("2".equals(str)){
			 return new ResponseData(true,"1");
		}else{
			return ResponseData.FAILED_NO_DATA;
		}
	}
	
	/**
	 * 删除
	 * @author:oufeng
	 * @version 创建时间：2016年7月27日  
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResponseData delete(HttpServletRequest request){
		String str=bxztService.delete(request);
		if("1".equals(str)){
			return  ResponseData.SUCCESS_NO_DATA;
		}else{
			return ResponseData.FAILED_NO_DATA;
		}
	}
	
	/***********
	 * 查询机构下的所有信息
	 * @param id
	 * @author:chenhui
	 * @param isparentId
	 * @return
	 */
	@RequestMapping(value="findAttacheArea")
	@ResponseBody
	public List<Map<String, Object>> findAttacheArea(String id,boolean isparentId){
		return bxztService.findAttacheArea(id, isparentId);
	}
	
//	@RequestMapping(value = "/add")
//	public String add(HttpServletRequest request, HttpServletResponse response) {
//		return "/bx/bxzt/add";
//	}
//	
//	@RequestMapping(value = "/detail")
//	public String detail(String id, HttpServletRequest request,
//			HttpServletResponse response) {
//		request.setAttribute("bxzt", bxztService.get(Long.parseLong(id)));
//		return "/bx/bxzt/detail";
//	}
//	
//	@RequestMapping({ "/list" })
//	public String tree(HttpServletRequest request, HttpServletResponse response) {
//		QueryFilter filter = new QueryFilter(request);
//		filter.addSorted("px", "asc");
//		filter.setLimit(100000);
//		request.setAttribute("bxzt", this.bxztService.getAll(filter));
//		return "/bx/bxzt/list";
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String save(ModelMap model,
//			@ModelAttribute("bxzt") BxBxzt entity,
//			HttpServletRequest request, HttpServletResponse response) {
//		Map<String, Object> errors = FormValidatorManager.validate(
//				"saveBxztConfig", request);
//		if (errors.size() != 0) {
//			return "/bx/bxzt/add";
//		}
//		String sjzt=request.getParameter("parentId");
//		entity.setId(Long.parseLong(bxztService.getMaxId().get(0).get("id")+"")+1);
//		BxBxzt bxzt=bxztService.get(Long.parseLong(sjzt));
//		entity.setZtjb(bxzt.getZtjb()+1);
//		entity.setSjzt(sjzt);
//		Result result = bxztService.save(entity);
//		if (!result.isSuccess()) {
//			model.putAll(result);
//			return "/bx/bxzt/add";
//		}
//		return "/common/success";
//	}
//	
//	@RequestMapping(value = "/delete")
//	public String delete(ModelMap model, String[] ids) {
//		for (String id : ids) {
//			Result result = bxztService.remove(id);
//			if (!result.isSuccess()) {
//				model.putAll(result);
//				return "/common/error";
//			}
//		}
//
//		return "/common/success";
//	}
//	
//	@RequestMapping(value = "/edit")
//	public String edit(String id, HttpServletRequest request,
//			HttpServletResponse response) {
//		request.setAttribute("bxzt", bxztService.get(Long.parseLong(id)));
//		return "/bx/bxzt/edit";
//	}
//
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(String id, ModelMap model,
//			@ModelAttribute("bxzt") BxBxzt entity,
//			HttpServletRequest request, HttpServletResponse response) {
//		Map<String, Object> errors = FormValidatorManager.validate(
//				"saveBxztConfig", request);
//		if (errors.size() != 0) {
//			return "/bx/bxzt/edit";
//		}
//		String sjzt=request.getParameter("parentId");
//		entity.setSjzt(sjzt);
//		Result result = bxztService.update(entity);
//		if (!result.isSuccess()) {
//			model.putAll(result);
//			return "/bx/bxzt/edit";
//		}
//		return "/common/success";
//	}
	
	@RequestMapping(value = "/findAllBxzt")
	public String findAllSysArea(String id, HttpServletRequest request,
			HttpServletResponse response) {
		List<BxBxzt> bxzt = this.bxztService.getAll();
		writeJson(response, this.bxztService.getBxztToString(bxzt));
		return null;
	}

}
