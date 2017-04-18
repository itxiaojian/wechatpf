//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wtx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wtx.model.TxGlpz;
import com.sliu.framework.app.wtx.service.TxGlpzService;

/**
 * 提醒管理配置
 * 
 * @author liujiasen
 * @date 2015年5月18日
 */
@Controller
@RequestMapping("/wtx/TxGlpz")
public class TxGlpzController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(TxGlpzController.class);

	@Autowired
	private TxGlpzService service;

	@RequestMapping(value = "/index")
	public String index() {
		return "/wtx/TxGlpz/txGlpzList";
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
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseData save(TxGlpz entity) {
		service.insertEntity(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseData update(TxGlpz entity,Long id) {
		TxGlpz oldEntity = (TxGlpz) service.getEntity(id);
		if (oldEntity == null) {
			return new ResponseData(false, "记录不存在");
		}
		oldEntity.setSjgjsj(entity.getSjgjsj());
		oldEntity.setTxsj(entity.getTxsj());
		oldEntity.setTxlx(entity.getTxlx());
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
