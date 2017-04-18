package com.sliu.framework.app.wtx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;
import com.sliu.framework.app.wtx.service.TxLssktxService;
import com.sliu.framework.app.wtx.service.TxTshstxService;

/**
 * 老师上课提醒列表以及详情
 * @author duanpeijun
 * @date 2015年8月11日
 */
@Controller
@RequestMapping("/wtx/TxLssktx")
public class TxLssktxController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxLssktxController.class);
	
	@Autowired
	private TxLssktxService txLssktxService;
	@Autowired
	private TxTshstxService service;
	
	/**
	 * 前台：学生上课提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/totxLssktxList")
	@ResponseBody
	public ModelAndView txLssktxList(String openId){
		
		ModelAndView modelAndView = new ModelAndView();
		String url = "";

		url = "/wfw/txlssktx/txLssktxList";
		
		List<Map<String, Object>> list = txLssktxService.getLssktxList(openId);
		modelAndView.addObject("list", list);
		modelAndView.addObject("openId",openId);
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 前台：老师上课提醒详情
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/totxLssktxDetail")
	@ResponseBody
	public ModelAndView txLssktxDetail(Long id,String openId) {

		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wfw/txlssktx/txLssktxDetail";

		TxTxxxlsjl txtxxlsjl = service.getTxTxxxlsjlById(id, openId);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("txnr", txtxxlsjl.getTxnr());
		map.put("txsj", txtxxlsjl.getTxsj());
		modelAndView.addObject("map", map);
		modelAndView.setViewName(url);

		return modelAndView;
	}
}
