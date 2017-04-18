package com.sliu.framework.app.wtx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.AxisFault;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wtx.service.TxOaDbsxService;

@Controller
@RequestMapping("/wtx/oadbsx")
public class TxOaDbsxController extends BaseController{


	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxLssktxController.class);
	
	@Autowired
	private TxOaDbsxService service;
	
	@RequestMapping(value = "/getaOaData")
	public void getaOaData() throws AxisFault{
		service.getOaDbsx();
	}
	
	@RequestMapping(value = "/getaOaDataById")
	public ModelAndView getaOaDataById(HttpServletRequest request) throws AxisFault{
		ModelAndView mav=new ModelAndView("/wtx/TxOadbxx/oadbList");
		String openId= request.getParameter("openId");
		String page= request.getParameter("page");
		if(page == null || "".equalsIgnoreCase(page)){
			page="1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){mav = AppUtil.runWx(openId);}
		List<Map<String,Object>> list=	service.getOaDbsxById(openId,page);
		mav.addObject("list",list);
		mav.addObject("openId",openId);
		mav.addObject("page",page);
		return mav;
	}
	
	@RequestMapping(value = "/getaOaDataByIdZj")
	@ResponseBody
	public List<Map<String,Object>> getaOaDataByIdZj(HttpServletRequest request) throws AxisFault{
		ModelAndView mav=new ModelAndView("/wtx/TxOadbxx/oadbList");
		String openId= request.getParameter("openId");
		String page= request.getParameter("page");
		if(page == null || "".equalsIgnoreCase(page)){
			page="1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){mav = AppUtil.runWx(openId);}
		List<Map<String,Object>> list=	service.getOaDbsxById(openId,page);
		mav.addObject("list",list);
		mav.addObject("openId",openId);
		mav.addObject("page",page);
		return list;
	}
}
