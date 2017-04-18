package com.sliu.framework.app.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysGjzglDao;
import com.sliu.framework.app.sys.model.SysGjzgl;

/**
 * 关键字管理
 * @author : zhangyi
 * @version 创建时间：2016年8月23日 下午3:15:59
 */
@Service
public class SysGjzglService {

	@Autowired
	private SysGjzglDao sysGjzglDao;
	
	@Transactional
	public Pagination<Map<String, Object>> getSysGjzglList(Integer start, Integer limit) {
		return sysGjzglDao.getSysGjzglList(start,limit);
	}

	/**
	 * 保存
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:28:47 
	 * @param wxZdycd
	 * @return
	 */
	@Transactional
	public String save(SysGjzgl wxZdycd) {
		Long re = sysGjzglDao.save(wxZdycd);
		return "1";
	}
	
	/**
	 * 修改
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String update(SysGjzgl entity) {
		SysGjzgl sysGjzgl = sysGjzglDao.get(entity.getId());
		sysGjzgl.setGjz(entity.getGjz());
		sysGjzgl.setZt(entity.getZt());
		sysGjzglDao.update(sysGjzgl);
		return "1";
	}

	/**
	 * 删除
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午10:17:03 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		sysGjzglDao.delete(id);
	}

	/**
	 * 获取所有的关键字列表
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月24日 上午9:14:05 
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getSysGjzglListAll() {
		List<Map<String, Object>> list = sysGjzglDao.getSysGjzglListAll();
		return list;
	}

	/**
	 * 根据字典种类找出字典
	   @author:zhangyi 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zt) {
		return sysGjzglDao.getDicByLx(zt);
	}
	
}
