package com.sliu.framework.app.wtx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wtx.service.TxTxxxlsjlService;
import com.sliu.framework.app.wtx.service.TxWdyjService;

/**
*未读邮件
@Author oufeng	
@Date 2016年8月10日 下午15:02:48
@Version 1.0
*/
@Controller
@RequestMapping("/wtx/Tx")
public class TxWdyjController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxWdyjController.class);
	
	@Autowired
	private TxWdyjService service;
	
}


