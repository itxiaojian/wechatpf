package com.sliu.framework.app.wtx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wtx.service.TxZggzService;


@Controller
@RequestMapping("/wtx/TxZggz")
public class TxZggzController extends BaseController{
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxZggzController.class);
	
	@Autowired
	private TxZggzService service;

}
