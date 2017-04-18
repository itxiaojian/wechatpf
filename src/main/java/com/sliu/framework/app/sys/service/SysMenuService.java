package com.sliu.framework.app.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysMenuDao;
import com.sliu.framework.app.sys.model.SysMenu;

@Service
public class SysMenuService {
	
	@Autowired
	private SysMenuDao dao;
	
	/**
	 * 查找菜单列表
	 * @author:sliu 
	 * @version 创建时间：2016年7月25日 上午11:25:22 
	 * @param jsmc
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getMenuList(String str) {
		return dao.getMenuList(str);
	}
	
	/**
	 * 分页得到一级菜单
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional
	public Pagination<Object> getFirstMenu(Integer start,Integer limit) {
		return dao.getFirstMenu(start, limit);
	}
	
	/**
	 * 获取子集菜单
	 * @author:sliu 
	 * @version 创建时间：2016年8月3日 上午11:11:20 
	 * @param start
	 * @param limit
	 * @param sjid 上级菜单Id
	 * @return
	 */
	@Transactional
	public Pagination<Object> getSonMenu(Integer start,Integer limit,Integer sjid) {
		return dao.getSonMenu(start, limit,sjid);
	}
	
	/**
	 * 保存
	 * @author:sliu 
	 * @version 创建时间：2016年6月4日 上午9:28:47 
	 * @param wxZdycd
	 * @return
	 */
	@Transactional
	public String save(SysMenu entity) {
		dao.save(entity);
		return "1";
	}
	
	/**
	 * 修改父菜单信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String update(SysMenu entity) {
		dao.update(entity);
		return "1";
	}
	
	/**
	 * 根据上级菜单找出下级菜单
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:12:27 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getSonMenu(String cdbh) {
		return dao.getSonMenuList(cdbh);
	}
	/**
	 * 校验菜单编号是否重复
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:12:27 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getcdbh(Long cdbh) {
		return dao.getcdbh(cdbh);
	}
	
	/**
	 * 删除
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午10:17:03 
	 * @param id
	 * @return 
	 */
	@Transactional
	public void delete(String cdbh) {
		dao.delete(cdbh);
	}
	
	/**
	 * 查询全部菜单用于角色菜单配置
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:12:27 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getAllList() {
		return dao.getAllList();
	}
	
	/**
	 * 查询当前用户菜单
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:12:27 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getCurrentUserMenu(String yhbh) {
		return dao.getCurrentUserMenu(yhbh);
	}

}
