package com.sliu.framework.app.wfw.controller;

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
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wfw.model.ZsDzxq;
import com.sliu.framework.app.wfw.service.ZsDzxqService;
import com.sliu.framework.app.wfw.service.ZsJscxService;
import com.sliu.framework.app.wtx.model.TxTxkg;


/**
 * 周次学期
 * @author : oufeng
 * @version 创建时间：2016年8月25日 
 */
@Controller
@RequestMapping("/wfw/dzxq")
public class ZsDzxqController extends BaseController {
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsDzxqController.class);
	
	@Autowired
	private ZsDzxqService service;  
	
	/**
	 * 后台：周次和学期的打开的页面
	 * @author  oufeng
	 * @version 创建时间：2016-08-24
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wfw/zsDzxqList");
		return modelAndView;
	}

	/**
	 * 后台：获取列表的页面
	 * @author oufeng
	 * @date 2016年8月15日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getDzxqList")
	@ResponseBody
	public Pagination<Map<String, Object>> getDzxqList(Integer start,Integer limit,String code){
		return service.getDzxqList(start, limit, code);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseData save(ZsDzxq entity) {
		service.insertEntity(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseData update(ZsDzxq entity,Long id) {
		ZsDzxq oldEntity = (ZsDzxq) service.getEntity(id);
		if (oldEntity == null) {
			return new ResponseData(false, "记录不存在");
		}
		oldEntity.setXn(entity.getXn());
		oldEntity.setXq(entity.getXq());
		oldEntity.setDqzc(entity.getDqzc());
		oldEntity.setKsrq(entity.getKsrq());
		oldEntity.setJsrq(entity.getJsrq());
		service.updateEntity(oldEntity);
		return ResponseData.SUCCESS_NO_DATA;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		for (Long id : ids) {
			service.deleteEntity(id);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}

}
