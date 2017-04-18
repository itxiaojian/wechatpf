package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wfw.dao.ZsDzxqDao;
import com.sliu.framework.app.wfw.model.ZsDzxq;


/**
 * 周次学期
 * @author : oufeng
 * @version 创建时间：2016年8月25日 
 */
@Service
public class ZsDzxqService {

	@Autowired
	private ZsDzxqDao dao;
	
	
	/**
	 * 后台：获取提醒的开关的列表的页面
	 * @author oufeng
	 * @date 2016年8月15日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getDzxqList(Integer start,Integer limit, String code) {
		return dao.getDzxqList(start, limit,code);
	}
	
	/**
	 * 
	 * @author oufeng
	 * @date 2016年08月15日
	 * @param id
	 * @return
	 */
	@Transactional
	public ZsDzxq getEntity(Long id){
		return dao.get(id);
	}

	/**
	 * 
	 * @author oufeng
	 * @date 2016年08月15日
	 * @param oldEntity
	 */
	@Transactional
	public void updateEntity(ZsDzxq oldEntity) {
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
	public void insertEntity(ZsDzxq entity) {
		//List<Map<String,Object>> list=dao.getEntityByLx(entity.getTxlx());
		//if(list.size()==0){
			dao.save(entity);
		//}else{
		//	entity.setId(Long.parseLong(list.get(0).get("id")+""));
	    //		dao.update(entity);
		//}
	}
	
}
