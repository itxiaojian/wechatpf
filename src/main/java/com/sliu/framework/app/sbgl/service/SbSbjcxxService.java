package com.sliu.framework.app.sbgl.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbSbjcxxDao;
import com.sliu.framework.app.sbgl.model.SbSbjcxx;

@Service
public class SbSbjcxxService extends BaseBO<SbSbjcxx> {

	@Autowired
	private SbSbjcxxDao sbSbjcxxDao;
	
	/**
	 * 后台：查询设备借出信息列表
	 * @author duanpeijun
	 * @date 2015年8月24日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbjyjhList(Integer start, Integer limit,String jczt,String jc){
		
		return sbSbjcxxDao.getSbjcxxList(start, limit,jczt,jc);
		
	}
	
	/**
	 * 后台： 添加设备借出信息
	 * @author duanpeijun
	 * @date 2015年8月24日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveSbjcxx(SbSbjcxx entity) {
		sbSbjcxxDao.save(entity);
		return "1";
	}
	
	/**
	 * 后台： 修改设备借出信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:57:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(SbSbjcxx entity,String id){
		entity.setId(Long.parseLong(id));
		sbSbjcxxDao.update(entity);
	}
	
	/**
	 * 后台： 删除设备借出信息
	 * @author duanpeijun
	 * @date 2015年8月20日
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		sbSbjcxxDao.delete(id);
	}
	
}
