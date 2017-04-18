package com.sliu.framework.app.wzy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wzy.dao.ZyYjjyDao;
import com.sliu.framework.app.wzy.model.ZyYjjy;


/**
 * 主页--  一键救援
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午4:13:48
 */
@Service
public class ZyYjjyService extends BaseBO<ZyYjjyDao>{

	@Autowired
	private ZyYjjyDao zyYjjyDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 分页查询  getYjjyList
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:13:54
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getYjjyList(Integer start, Integer limit){
		
		return zyYjjyDao.getYjjyList(start, limit);
		
	}
	
	/**
	 * 查看一键救援信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:14:08
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getYjjyList(){
		return zyYjjyDao.getCx();
	}
	
	/**
	 * 增加
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:14:14
	 * @param entity
	 */
	@Transactional
	public void save(ZyYjjy entity) {
		zyYjjyDao.save(entity);
	}
	
	/**
	 * 编辑
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:14:20
	 * @param entity
	 * @param id
	 */
	@Transactional
	public void update(ZyYjjy entity,String id){
		entity.setId(Long.parseLong(id));
		zyYjjyDao.update(entity);
	}
	
	/**
	 * 删除
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:14:28
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
		zyYjjyDao.delete(ids[i]);
		}
	}
}
