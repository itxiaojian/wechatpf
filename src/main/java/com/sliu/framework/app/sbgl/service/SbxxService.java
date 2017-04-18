package com.sliu.framework.app.sbgl.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbxxDao;
import com.sliu.framework.app.sbgl.model.SbSbxx;

/**
 * 设备管理service
 * @author : zhangyi
 * @version 创建时间：2015年8月20日 上午9:59:36
 */
@Service
public class SbxxService  extends BaseBO<SbSbxx>{

	@Autowired
	private SbxxDao sbxxDao;

	
	/**
	 * 设备信息list
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月20日 上午9:58:40 
	 * @param start
	 * @param limit
	 * @param sybm
	 * @param syzt
	 * @param gjz
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbxxList(Integer start,
			Integer limit, String sybm, String syzt,String gjz,String cd) {
		return sbxxDao.getSbxxList(start, limit, sybm, syzt,gjz,cd);
	}
	
	/**
	 * 添加设备信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月20日 上午10:01:42 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveSbxx(SbSbxx entity,String sybmbh,String sbwhr) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String sbbh=entity.getSblb()+sim.format(new Date());
		entity.setSbbh(sbbh);
		entity.setSbwhr(sbwhr.split(",")[0]);
		sbxxDao.save(entity);
		return "1";
	}

	/**
	 * 根据id查看设备信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月20日 上午10:03:19 
	 * @param id
	 * @return
	 */
	@Transactional
	public SbSbxx getSbxxById(Long id) {
		SbSbxx entity = sbxxDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}

	/**
	 * 删除
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月10日 上午11:20:04 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		sbxxDao.delete(id);
	}

	/**
	 * 根据字典种类找到菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月20日 下午3:57:48 
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return sbxxDao.getDicByLx(zdzl);
	}

	/**
	 * 报废功能
	 * @author:liujiansen
	 * @version 创建时间：2015年6月10日 上午11:22:07 
	 * @param ids
	 * @return
	 */
	@Transactional
	public void sbBfcl(String id) {
		sbxxDao.sbBfcl(id);
	}
}
