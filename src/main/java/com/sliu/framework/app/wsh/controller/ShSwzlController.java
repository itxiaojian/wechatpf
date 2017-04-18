//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.model.ShSwzl;
import com.sliu.framework.app.wsh.service.ShSwzlService;

/**
 * 失物招领
 * @author liujiansen
 * @date 2015年6月16日
 */
@Controller
@RequestMapping("/wsh/ShSwzl")
public class ShSwzlController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShSwzlController.class);
	
	@Autowired
	private ShSwzlService service;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "/wsh/ShSwzl/ShSwzl";
	}

	@RequestMapping(value = "/pager")
	@ResponseBody
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit,String code,String fbrxm) {
		return service.pager(start, limit, code, fbrxm);
	}
	
	@RequestMapping(value = "/deleteByHt")
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		for (Long id : ids) {
			service.deleteEntity(id);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 失物招领列表
	 * @author liujiansen
	 * @date 2015年6月16日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toSwzlList")
   	public ModelAndView toSwzlList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
   		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSwzlList");
   		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		String lx=request.getParameter("lx");
		if(lx==null||"".equals(lx)){
   			lx="";
   		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
   		List<Map<String,Object>> list=service.getSwzlList(request);
   		//int count=service.getCount(request);
   		if(list.size()!=0){
   			mav.addObject("list",list);
   			mav.addObject("pages",pages);
   			//mav.addObject("count",count);
   			mav.addObject("lx",lx);
   			mav.addObject("size",list.size());
   		}
		}
		mav.addObject("openId",openId);
   		return mav;
   	}
	
	/**
	 * 
	 * 转到失物招领添加页面 
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/toSwzlAdd")
   	public ModelAndView toSwzlAdd(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSwzlAdd");
   		String openId = request.getParameter("openId");
   		if(openId==null||"".equals(openId)){
			openId = AppUtil.getOpenId(request, response);
		}
   		mav.addObject("openId",openId);
   		return mav;
   	}
	
	/**
	 * 
	 * 转到拾卡招领添加页面 
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/toSkzlAdd")
   	public ModelAndView toSkzlAdd(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSkzlAdd");
   		String openId = request.getParameter("openId");
   		if(openId==null||"".equals(openId)){
			openId = AppUtil.getOpenId(request, response);
		}
   		mav.addObject("openId",openId);
   		return mav;
   	}
	
	/**
	 * 失物招领添加
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response){
		String openId = request.getParameter("openId");
		String title = request.getParameter("title");
		if(!"".equals(openId) && openId!=null && !"".equals(title) && openId!=null){
			service.saveSwzl(request, response);
		}
		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSwzlList");
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		String lx=request.getParameter("lx");
		if(lx==null||"".equals(lx)){
   			lx="";
   		}
   		List<Map<String,Object>> list=service.getSwzlList(request);
   		//int count=service.getCount(request);
   		if(list.size()!=0){
   			mav.addObject("list",list);
   			mav.addObject("pages",pages);
   			//mav.addObject("count",count);
   			mav.addObject("lx",lx);
   		}
   		mav.addObject("openId",openId);
   		return mav;
	}
	
	/**
	 * 拾卡招领添加
	 * @author zhangyan
	 * @date 2016年11月9日 上午10:25:29
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/saveskzl")
	@ResponseBody
	public ModelAndView saveskzl(HttpServletRequest request, HttpServletResponse response){
		String openId = request.getParameter("openId");
		String title = request.getParameter("title");
		if(!"".equals(openId) && openId!=null && !"".equals(title) && openId!=null){
			service.saveSwzl(request, response);
		}
		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSkzlList");
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		String lx=request.getParameter("lx");
		if(lx==null||"".equals(lx)){
   			lx="";
   		}
   		List<Map<String,Object>> list=service.getSkzlList(request);
   		//int count=service.getCount(request);
   		if(list.size()!=0){
   			mav.addObject("list",list);
   			mav.addObject("pages",pages);
   			//mav.addObject("count",count);
   			mav.addObject("lx",lx);
   		}
   		mav.addObject("openId",openId);
   		return mav;
	}
	
	/**
	 * 删除失物招领信息
	 * @author liujiasen
	 * @date 2015年7月20日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response){
		service.deleteSwzl(request);
		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSwzlList");
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		String lx=request.getParameter("lx");
		if(lx==null||"".equals(lx)){
   			lx="";
   		}
		String openId = request.getParameter("openId");
   		List<Map<String,Object>> list=service.getSwzlList(request);
   		//int count=service.getCount(request);
   		if(list.size()!=0){
   			mav.addObject("list",list);
   			mav.addObject("pages",pages);
   			//mav.addObject("count",count);
   			mav.addObject("lx",lx);
   		}
   		mav.addObject("openId",openId);
   		return mav;
	}
	
	/**
	 * 删除拾卡招领信息
	 * @author zhangyan
	 * @date 2016年11月10日 上午11:36:54
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/deleteskzl")
	@ResponseBody
	public ModelAndView deleteskzl(HttpServletRequest request, HttpServletResponse response){
		service.deleteSwzl(request);
		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSkzlList");
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		String lx=request.getParameter("lx");
		if(lx==null||"".equals(lx)){
   			lx="";
   		}
		String openId = request.getParameter("openId");
   		List<Map<String,Object>> list=service.getSkzlList(request);
   		//int count=service.getCount(request);
   		if(list.size()!=0){
   			mav.addObject("list",list);
   			mav.addObject("pages",pages);
   			//mav.addObject("count",count);
   			mav.addObject("lx",lx);
   		}
   		mav.addObject("openId",openId);
   		return mav;
	}
	
	/**
	 * 转到失物认领查看页面 
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toSwzlDetail")
   	public ModelAndView toSwzlDetail(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSwzlDetail");
   		String id=request.getParameter("id");
   		String openId = request.getParameter("openId");
   		List<Map<String,Object>> list=service.getSwzlById(openId,id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		mav.addObject("openId", openId);
   		return mav;
   	}
	
	/**
	 * 转到拾卡招领详细页面
	 * @author zhangyan
	 * @date 2016年11月9日 上午11:25:42
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toSkzlDetail")
   	public ModelAndView toSkzlDetail(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSkzlDetail");
   		String id=request.getParameter("id");
   		String openId = request.getParameter("openId");
   		List<Map<String,Object>> list=service.getSwzlById(openId,id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		mav.addObject("openId", openId);
   		return mav;
   	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSwzlDetail");
   		String id=request.getParameter("id");
   		service.updateSwzl(id);
   		String openId = request.getParameter("openId");
   		List<Map<String,Object>> list=service.getSwzlById(openId,id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		mav.addObject("openId", openId);
   		return mav;
	}
	
	/**
	 * 拾卡招领列表
	 * @author zhangyan
	 * @date 2016年11月8日 下午4:12:23
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toSkzlList")
   	public ModelAndView toSkzlList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
   		ModelAndView mav=new ModelAndView("/wsh/ShSwzl/ShSkzlList");
   		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		String lx=request.getParameter("lx");
		if(lx==null||"".equals(lx)){
   			lx="";
   		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
   		List<Map<String,Object>> list=service.getSkzlList(request);
   		//int count=service.getCount(request);
   		if(list.size()!=0){
   			mav.addObject("list",list);
   			mav.addObject("pages",pages);
   			//mav.addObject("count",count);
   			mav.addObject("lx",lx);
   			mav.addObject("size",list.size());
   		}
		}
		mav.addObject("openId",openId);
   		return mav;
   	}
	
	/**
	 * 后台跳转拾卡页面
	 * @author zhangyan
	 * @date 2016年11月14日 上午9:30:41
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/skPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wsh/ShSwzl/skPage");
		return modelAndView;
	}
	
	/**
	 * 分页查询拾卡信息
	 * @author zhangyan
	 * @date 2016年11月14日 上午9:38:22
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getSkList")
	@ResponseBody
	public Pagination<Map<String,Object>> getSkList(Integer start,Integer limit,String code){
		return service.getSkList(start,limit,code);
	}
	
	/**
	 * 后台添加拾卡信息
	 * @author zhangyan
	 * @date 2016年11月14日 上午10:07:32
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/sksave")
	@ResponseBody
	public ResponseData sksave(ModelMap model,ShSwzl entity,
			HttpServletRequest request,HttpServletResponse response){
		
		String result = service.sksave(entity);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台修改拾卡信息
	 * @author zhangyan
	 * @date 2016年11月14日 上午10:16:51
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/skupdate")
	@ResponseBody
	public ResponseData skupdate(ShSwzl entity,Long id){
		
		String result = service.skupdate(entity,id);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 拾卡信息导入
	 * @author zhangyan
	 * @date 2016年11月14日 上午10:20:24
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/upload")
    @ResponseBody
    public String upload(@RequestParam("fileData") MultipartFile fileData)
    {
        String message = service.importMember(fileData);
        if (message == null)
            return "{\"success\":true}";
        else
            return "{\"success\":false,\"message\":\""+message+"\"}";
    }
	
	
	@RequestMapping(value = "/sendsktx")
	@ResponseBody
	public ResponseData sendsktx(Long[] ids) {
		for (Long id : ids) {
			service.sendsktx(id);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
}
