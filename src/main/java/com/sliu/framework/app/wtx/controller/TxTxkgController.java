package com.sliu.framework.app.wtx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wtx.model.TxTxkg;
import com.sliu.framework.app.wtx.service.TxTxkgService;



/**
 * 提醒开关
 * @author oufeng
 * @date 2016年8月16日
 */
@Controller
@RequestMapping("/wtx/Txkg")
public class TxTxkgController  extends BaseController{
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxTxkgController.class);
	
	@Autowired
	private TxTxkgService service;
	
	/**
	 * 后台：查询设备申购
	 * @author   oufeng
	 * @version 创建时间：2015-08-15
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wtx/TxKg/TxKgList");
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
	@RequestMapping(value = "/getTxkgList")
	@ResponseBody
	public Pagination<Map<String, Object>> getTxkgList(Integer start,Integer limit,String code){
		return service.getTxkgList(start, limit, code);
	}
	
	/**
	 * 后台：获取状态
	 * @author oufeng
	 * @date 2016年8月15日
	 * @return
	 */
	@RequestMapping(value = "/getZt")
	@ResponseBody
	public List<Map<String,Object>> getZt(){
		return service.getZt();
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseData save(TxTxkg entity) {
		service.insertEntity(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseData update(TxTxkg entity,Long id) {
		TxTxkg oldEntity = (TxTxkg) service.getEntity(id);
		if (oldEntity == null) {
			return new ResponseData(false, "记录不存在");
		}
		oldEntity.setKgmc(entity.getKgmc());
		oldEntity.setKgzl(entity.getKgzl());
		oldEntity.setKgzt(entity.getKgzt());
		oldEntity.setBz(entity.getBz());
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
