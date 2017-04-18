package com.sliu.framework.app.bx.controller;

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
import com.sliu.framework.app.bx.service.BxBxwxrwService;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;

/**
*报修维修任务
@Author oufeng	
@Date 2016年11月2日 下午8:40:09
@Version 1.0
*/
@Controller
@RequestMapping("/bx/bxrw")
public class BxBxwxrwController  extends BaseController {
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(BxBxwcController.class);

	@Autowired
	private BxBxwxrwService service;
	
	/**
	*报修维修任务内容
	@Author oufeng	
	@Date 2016年11月2日 下午8:40:09
	@Version 1.0
	*/
	@RequestMapping(value="/getrw")
	public ModelAndView getrw(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("/bx/bxsl/bxWxgRw");
		String openId= request.getParameter("openId");
		String page= request.getParameter("page");
		if(page == null || "".equalsIgnoreCase(page)){
			page="1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){mav = AppUtil.runWx(openId);}
		List<Map<String,Object>> list=	 service.getRw(openId,page);
		mav.addObject("list",list);
		mav.addObject("openId",openId);
		mav.addObject("page",page);
		return mav;
	}
	
	/**
	*报修维修任务内容加载更多
	@Author oufeng	
	@Date 2016年11月2日 下午8:40:09
	@Version 1.0
	*/
	@RequestMapping(value="/getrwZj")
	@ResponseBody
	public  List<Map<String,Object>>  getrwZj(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("/bx/bxsl/bxWxgRw");
		String openId= request.getParameter("openId");
		String page= request.getParameter("page");
		if(page == null || "".equalsIgnoreCase(page)){
			page="1";
		}
		List<Map<String,Object>> list=	 service.getRw(openId,page);
		mav.addObject("list",list);
		mav.addObject("openId",openId);
		mav.addObject("page",page);
		return list;
	}
	
	/**
	*报修删除
	@Author oufeng	
	@Date 2016年11月2日 下午8:40:09
	@Version 1.0
	*/
	@RequestMapping(value="/sc")
	@ResponseBody
	public  ResponseData  sc(HttpServletRequest request) {
		String id= request.getParameter("id");
	    String str= service.delete(id);
	    if("1".equals(str)){
		    return ResponseData.SUCCESS_NO_DATA;
	    }else{
	    	return ResponseData.FAILED_NO_DATA;
	    }
	}


}

