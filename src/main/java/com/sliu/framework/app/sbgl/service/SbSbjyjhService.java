package com.sliu.framework.app.sbgl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbSbjyjhDao;
import com.sliu.framework.app.sbgl.model.SbSbjyjh;


@Service
public class SbSbjyjhService extends BaseBO<SbSbjyjh> {

	@Autowired
	private SbSbjyjhDao sbSbjyjhDao;
	
	/**
	 * 后台：查询设备检验计划列表
	 * @author duanpeijun
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbjyjhList(Integer start, Integer limit,String jh,String wxrbh){
		
		return sbSbjyjhDao.getSbjyjhList(start, limit,jh,wxrbh);
		
	}
	
	/**
	 * 后台： 添加设备检验计划
	 * @author duanpeijun
	 * @date 2015年8月20日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveSbjyjh(SbSbjyjh entity) {
		sbSbjyjhDao.save(entity);
		return "1";
	}
	
	/**
	 * 后台： 修改设备检验计划
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:57:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(SbSbjyjh entity,String id){
		entity.setId(Long.parseLong(id));
		sbSbjyjhDao.update(entity);
	}
	
	/**
	 * 后台： 删除设备检验计划
	 * @author duanpeijun
	 * @date 2015年8月20日
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		sbSbjyjhDao.delete(id);
	}
	
	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:36:46 
	 * @param zdzl 字典种类
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return sbSbjyjhDao.getDicByLx(zdzl);
	}
	
	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:36:46 
	 * @param zdzl 字典种类
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDicByYxlx(String zdzl) {
		return sbSbjyjhDao.getDicByYxlx(zdzl);
	}
}
