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
import com.sliu.framework.app.wtx.service.TxTshstxService;
import com.sliu.framework.app.wtx.service.TxXssktxService;

/**
 * 学生上课提醒列表以及详情
 * @author duanpeijun
 * @date 2015年8月11日
 */
@Controller
@RequestMapping("/wtx/TxXssktx")
public class TxXssktxController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxXssktxController.class);
	
	@Autowired
	private TxXssktxService txXssktxService;
	@Autowired
	private TxTshstxService service;
	
	/**
	 * 前台：学生上课提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/totxXssktxList")
	@ResponseBody
	public ModelAndView txXssktxList(String openId){
		
		ModelAndView modelAndView = new ModelAndView();
		String url = "";

		url = "/wfw/txxssktx/txXssktxList";
		
		List<Map<String, Object>> list = txXssktxService.getXssktxList(openId);
		modelAndView.addObject("list", list);
		modelAndView.addObject("openId",openId);
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 前台：学生上课提醒详情
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/totxXssktxDetail")
	@ResponseBody
	public ModelAndView txXssktxDetail(Long id,String openId) {

		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wfw/txxssktx/txXssktxDetail";

		TxTxxxlsjl txtxxlsjl = service.getTxTxxxlsjlById(id, openId);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("txnr", txtxxlsjl.getTxnr());
		map.put("txsj", txtxxlsjl.getTxsj());
		modelAndView.addObject("map", map);
		modelAndView.setViewName(url);

		return modelAndView;
	}
}
