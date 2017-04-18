package com.sliu.framework.app.wzy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wzy.dao.ZyDhhlDao;
import com.sliu.framework.app.wzy.model.ZyDhhl;


/**
 * 主页--  电话黄历
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:55:24
 */
@Service
public class ZyDhhlService extends BaseBO<ZyDhhlDao>{

	@Autowired
	private ZyDhhlDao zyDhhlDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	
	/**
	 * 分页查询  getYjjyList
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getDhhlList(Integer start, Integer limit){
		
		return zyDhhlDao.getDhhlList(start, limit);
		
	}
	
	
	/**
	 *  查看电话黄页信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:37
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getDhhlList(){
		return zyDhhlDao.getCx();
	}
	
	/**
	 * 增加
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:44
	 * @param entity  
	 */
	@Transactional
	public void save(ZyDhhl entity) {
		zyDhhlDao.save(entity);
	}
	
	/**
	 * 编辑
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:57:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(ZyDhhl entity,String id){
		entity.setId(Long.parseLong(id));
		zyDhhlDao.update(entity);
	}
	
	/**
	 * 删除
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:57:36
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
		zyDhhlDao.delete(ids[i]);
		}
	}
}
