package com.sliu.framework.app.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.sys.model.SysZzjg;
import com.sliu.framework.app.sys.service.SysZzjgService;
import com.sliu.framework.app.util.ResponseData;


@Controller
@RequestMapping("/sys/SysZzjg")
public class SysZzjgController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SysZzjgController.class);
	
	@Autowired
	private SysZzjgService zzjgService;
	
	
	/**
	 * 打开页面
	 * @author:oufeng
	 * @version 创建时间：2016年7月27日  
	 * @return
	 */
	@RequestMapping(value="/openZzjgPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sys/SysZzjg/sysZzjg");
		return modelAndView;
	}
	
	
	@RequestMapping(value="orgOrMebTree")
	@ResponseBody
	public  List<Map<String, Object>>  orgOrMebTree(String node){
		 List<Map<String, Object>> list  = zzjgService.getOrgOrMebTree(node);
		 if(!list.isEmpty()){
			for(Map<String, Object> map :list){
				map.put("leaf", false);
				map.put("cls", "folder");
			}
		 }
		return list;
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
		return zzjgService.findAttacheArea(id, isparentId);
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
		String str=zzjgService.update(request);
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
		String str=zzjgService.save(request);
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
		String str=zzjgService.delete(request);
		if("1".equals(str)){
			return  ResponseData.SUCCESS_NO_DATA;
		}else{
			return ResponseData.FAILED_NO_DATA;
		}
	}
	
}
