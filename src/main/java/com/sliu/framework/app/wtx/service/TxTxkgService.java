package com.sliu.framework.app.wtx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wtx.dao.TxTxkgDao;
import com.sliu.framework.app.wtx.model.TxTxkg;

@Service
public class TxTxkgService extends BaseBO<TxTxkg>{
	
	@Autowired
	private TxTxkgDao dao;
	
	
	/**
	 * 后台：获取提醒的开关的列表的页面
	 * @author oufeng
	 * @date 2016年8月15日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTxkgList(Integer start,Integer limit, String code) {
		return dao.getTxkgList(start, limit,code);
	}
	
	
	/**
	 * 获取字典表中的提醒状态
	 * @author oufeng
	 * @date 2016年8月15日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getZt(){
		return dao.getZt();
	}
	
	
	/**
	 * 
	 * @author oufeng
	 * @date 2016年08月15日
	 * @param id
	 * @return
	 */
	@Transactional
	public TxTxkg getEntity(Long id){
		return dao.get(id);
	}

	/**
	 * 
	 * @author oufeng
	 * @date 2016年08月15日
	 * @param oldEntity
	 */
	@Transactional
	public void updateEntity(TxTxkg oldEntity) {
		dao.update(oldEntity);
	}

	/**
	 * 
	 * @author oufeng
	 * @date 2016年08月15日
	 * @param id
	 */
	@Transactional
	public void deleteEntity(Long id) {
		dao.delete(id);
	}

	/**
	 * 
	 * @author oufeng
	 * @date 2016年08月15日
	 * @param entity
	 */
	@Transactional
	public void insertEntity(TxTxkg entity) {
		//List<Map<String,Object>> list=dao.getEntityByLx(entity.getTxlx());
		//if(list.size()==0){
			dao.save(entity);
		//}else{
		//	entity.setId(Long.parseLong(list.get(0).get("id")+""));
	    //		dao.update(entity);
		//}
	}
}
