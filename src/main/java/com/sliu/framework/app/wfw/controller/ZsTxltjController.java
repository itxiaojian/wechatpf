package com.sliu.framework.app.wfw.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wfw.model.ZsTxltj;
import com.sliu.framework.app.wfw.service.ZsTxltjService;

/**
 * 主页--   通讯录
 * @author wangxiangyang
 * @version 创建时间：2016年7月1日
 */

@Controller
@RequestMapping("/wfw/ZsTxltj")
public class ZsTxltjController  extends BaseController{


	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsTxltj.class);
	
	@Autowired
	private ZsTxltjService zsTxltjService;
	
	/**
	 * 功能：点击一键救援之后弹出的信息列表
	 * zyDhhl.js
	 * zyDhhl.jsp
	 * @author   wangxiangyang
	 * @version 创建时间：2015年6月3日  下午4:06:01
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getTxltjList")
	@ResponseBody
	public Pagination<Map<String, Object>> getTxltjList(Integer start,Integer limit){
		
		return zsTxltjService.getTxltjList(start, limit);
		
	}
	
	/**
	 * 
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @return
	 */
	@RequestMapping(value = "/txltjPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wfw/zsTxltjList");
		return modelAndView;
	}
	
	/**
	 *  通讯录添加
	 * viewtxltj.jsp
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @return
	 */
	@RequestMapping(value = "/zsTxltjDetail")
	@ResponseBody
	public ModelAndView viewTxltj(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wfw/zsTxltjDetail";
		
		List<Map<String,Object>> zsTxltj = zsTxltjService.getTxltjList();
		
		modelAndView.addObject("map", zsTxltj);
		
		modelAndView.addObject("openId",openId);
		
		modelAndView.setViewName(url);
		
		return modelAndView;
	}
	
	/**
	 * 增加
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,ZsTxltj entity,String yhxm,String dhhm,
			HttpServletRequest request,HttpServletResponse response){
		String str =zsTxltjService.save(entity,yhxm,dhhm);
		if(str=="1"){
			return ResponseData.SUCCESS_NO_DATA;
		}else{
			return ResponseData.FAILED_NO_DATA;
		}
		
	}
	
	/**
	 * 编辑
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ZsTxltj entity, String id) {
		zsTxltjService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
	zsTxltjService.delete(ids);
	return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 通讯录信息导出
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月4日
	 * @return
	 */
	@RequestMapping(value = "/export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String code) throws Exception{
		zsTxltjService.exportExcel(request,response,code);
	}
	
	/** 
	 * 录取信息导入
	 * @author liusong E-mail: 2629690209@qq.com
	 * @version 创建时间：2015年11月11日 上午10:19:22 
	 * 方法说明 
	 */
	@RequestMapping(value="/upload")
    @ResponseBody
    public String upload(@RequestParam("fileData") MultipartFile fileData)
    {
        String message = zsTxltjService.importMember(fileData);
        if (message == null)
            return "{\"success\":true}";
        else
            return "{\"success\":false,\"message\":\""+message+"\"}";
    }
}
