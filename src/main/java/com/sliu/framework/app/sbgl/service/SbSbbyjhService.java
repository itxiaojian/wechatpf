package com.sliu.framework.app.sbgl.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbSbbyjhDao;
import com.sliu.framework.app.sbgl.model.SbSbbyjh;


@Service
public class SbSbbyjhService extends BaseBO<SbSbbyjh> {

	@Autowired
	private SbSbbyjhDao dao;
	
	/**
	 * 后台：查询设备检验计划列表
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbbyjhList(Integer start, Integer limit,String code,String byjb){
		
		return dao.getSbbyjhList(start, limit,code,byjb);
		
	}
	
	/**
	 * 后台： 添加设备检验计划
	 * @author liujiansen
	 * @date 2015年8月24日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveBy(SbSbbyjh entity) {
		if("1".equals(entity.getXhfs())){
			Date date=entity.getScbysj();
			Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(date);
	    	calendar.add(Calendar.MONTH, entity.getJg());
	    	date = calendar.getTime();
			entity.setXcbysj(date);
		}else{
			entity.setJg(null);
		}
		dao.save(entity);
		return "1";
	}
	
	/**
	 * 后台： 修改设备检验计划
	 * @author   liujiansen
	 * @version 创建时间：2015年6月3日  下午3:57:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(SbSbbyjh entity,String id){
		entity.setId(Long.parseLong(id));
		if("1".equals(entity.getXhfs())){
			Date date=entity.getScbysj();
			Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(date);
	    	calendar.add(Calendar.MONTH, entity.getJg());
	    	date = calendar.getTime();
			entity.setXcbysj(date);
		}else{
			entity.setJg(null);
		}
		dao.update(entity);
	}
	
	/**
	 * 后台： 删除设备检验计划
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
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
		return dao.getDicByLx(zdzl);
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
		return dao.getDicByYxlx(zdzl);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月24日
	 * @param id
	 * @param zt
	 * @param gzms
	 */
	@Transactional
	public void updateWc(String id, String zt, String gzms) {
		SbSbbyjh entity=dao.get(Long.parseLong(id));
		entity.setGzms(gzms.split(",")[0]);
		entity.setZt(zt.split(",")[0]);
		dao.update(entity);
	}
}
