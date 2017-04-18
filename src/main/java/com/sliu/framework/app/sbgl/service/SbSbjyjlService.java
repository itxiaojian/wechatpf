package com.sliu.framework.app.sbgl.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbSbjyjlDao;
import com.sliu.framework.app.sbgl.model.SbSbjyjl;

@Service
public class SbSbjyjlService extends BaseBO<SbSbjyjl>{

	@Autowired
	private SbSbjyjlDao sbSbjyjlDao;
	
	/**
	 * 后台：查询设备检验记录列表
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbjyjlList(Integer start, Integer limit,String jl,String jyzt){
		
		return sbSbjyjlDao.getSbjyjlList(start, limit,jl,jyzt);
		}
	
	/**
	 * 后台： 添加设备检验记录
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveSbjyjl(SbSbjyjl entity) {
		sbSbjyjlDao.save(entity);
		return "1";
	}
	
	/**
	 * 后台： 修改设备检验记录
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:57:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(SbSbjyjl entity,String id){
		entity.setId(Long.parseLong(id));
		sbSbjyjlDao.update(entity);
	}
	
	/**
	 * 后台： 删除设备检验记录
	 * @author duanpeijun
	 * @date 2015年8月20日
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		sbSbjyjlDao.delete(id);
	}
	
}