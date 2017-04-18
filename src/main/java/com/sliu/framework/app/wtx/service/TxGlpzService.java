package com.sliu.framework.app.wtx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wtx.dao.TxGlpzDao;
import com.sliu.framework.app.wtx.model.TxGlpz;

/**
 * 提醒管理配置
 * @author liujiansen
 * @date 2015年7月29日
 */
@Service
public class TxGlpzService extends BaseBO<TxGlpz> {

	@Autowired
	private TxGlpzDao dao;
	
	/**
	 * 分页查询信息数据
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param start
	 * @param limit
	 * @param txlx 提醒类型
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> pager(Integer start,Integer limit, String txlx) {
		return dao.pager(start, limit, txlx);
	}
	
	/**
	 * 获取字典表中的提醒类型
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getLx(){
		return dao.getLx();
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param id
	 * @return
	 */
	@Transactional
	public TxGlpz getEntity(Long id){
		return dao.get(id);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param oldEntity
	 */
	@Transactional
	public void updateEntity(TxGlpz oldEntity) {
		dao.update(oldEntity);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param id
	 */
	@Transactional
	public void deleteEntity(Long id) {
		dao.delete(id);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param entity
	 */
	@Transactional
	public void insertEntity(TxGlpz entity) {
		List<Map<String,Object>> list=dao.getEntityByLx(entity.getTxlx());
		if(list.size()==0){
			dao.save(entity);
		}else{
			entity.setId(Long.parseLong(list.get(0).get("id")+""));
			dao.update(entity);
		}
	}
}
