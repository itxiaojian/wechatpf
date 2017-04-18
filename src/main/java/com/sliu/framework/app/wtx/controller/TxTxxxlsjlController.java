//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wtx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wtx.service.TxTxxxlsjlService;

/**
 * 提醒信息历史记录
 * 
 * @author liujiasen
 * @date 2015年5月18日
 */
@Controller
@RequestMapping("/wtx/TxTxxxlsjl")
public class TxTxxxlsjlController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxTxxxlsjlController.class);

	@Autowired
	private TxTxxxlsjlService service;

	@RequestMapping(value = "/index")
	public String index() {
		return "/wtx/TxTxxxlsjl/txTxxxlsjlList";
	}

	@RequestMapping(value = "/pager")
	@ResponseBody
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit,
			String txlx) {
		return service.pager(start, limit, txlx);
	}
	
	@RequestMapping(value = "/getLx")
	@ResponseBody
	public List<Map<String,Object>> getLx(){
		return service.getLx();
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		for (Long id : ids) {
			service.deleteEntity(id);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	@RequestMapping(value = "/getTxlsjl")
	public ModelAndView getTxlsjl(HttpServletRequest request) throws AxisFault{
		ModelAndView mav=new ModelAndView("/wtx/TxTxxxlsjl/txTxxxlsjl");
		String openId= request.getParameter("openId");
		String page= request.getParameter("page");
		if(page == null || "".equalsIgnoreCase(page)){
			page="1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){mav = AppUtil.runWx(openId);}
		List<Map<String,Object>> list=	 service.getTxlsjl(openId,page);
		String  role= service.getRole(openId);
		List<Map<String,Object>> txlist= service.getTxlx(role);
		mav.addObject("list",list);
		mav.addObject("txlist",txlist);
		mav.addObject("openId",openId);
		mav.addObject("size",list.size());
		mav.addObject("page",page);
		return mav;
	}
	
	@RequestMapping(value = "/getTxlsjlZj")
	@ResponseBody
	public List<Map<String,Object>> getTxlsjlZj(HttpServletRequest request) throws AxisFault{
		ModelAndView mav=new ModelAndView("/wtx/TxTxxxlsjl/txTxxxlsjl");
		String openId= request.getParameter("openId");
		String page= request.getParameter("page");
		if(page == null || "".equalsIgnoreCase(page)){
			page="1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){mav = AppUtil.runWx(openId);}
		List<Map<String,Object>> list=	service.getTxlsjl(openId,page);
		String  role= service.getRole(openId);
		List<Map<String,Object>> txlist= service.getTxlx(role);
		mav.addObject("list",list);
		mav.addObject("txlist",txlist);
		mav.addObject("openId",openId);
		mav.addObject("page",page);
		return list;
	}
	
	@RequestMapping(value = "/updateTxkg")
	@ResponseBody
	public String updateTxkg(HttpServletRequest request) throws AxisFault{
		String openId= request.getParameter("openId");
		String lx= request.getParameter("lx");
		String zt= request.getParameter("zt");
		service.updateGrtx(openId,lx,zt);
		return "1";
		//return ResponseData.SUCCESS_NO_DATA;
	}
}
