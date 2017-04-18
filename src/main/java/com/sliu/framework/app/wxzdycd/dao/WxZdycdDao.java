package com.sliu.framework.app.wxzdycd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxZdycd;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
 * 微信自定义菜单
 * @author zhangyi
 * 2015-6-3
 */
@Repository
public class WxZdycdDao extends HibernateBaseDaoImpl<WxZdycd, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWxZdycdList() {
		String hql = "select new Map(t.groupId as groupId, t.name as name) from WxZdycd t";
		return this.findByHQL(hql);
	}
	
	/**
	 * 获取一级菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 上午11:09:25 
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Object> getFirstMenu(Integer start,Integer limit) {
		String rowHql = "from WxZdycd t where t.dqjb=1 order by t.px asc";
		String countHql = "select count(t.id) from WxZdycd t where t.dqjb=1";
		return this.findPageByHQL(rowHql, countHql, start, limit);
	}
	
	/**
	 * 根据上级菜单查出子级菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 上午11:13:41 
	 * @param start
	 * @param limit
	 * @param sjid 上级id
	 * @return
	 */
	public Pagination<Object> getSonMenu(Integer start, Integer limit,
			Integer sjid) {
		String rowHql = "from WxZdycd t where t.dqjb=2 and t.sjid="+sjid+" order by t.px asc";
		String countHql = "select count(t.id) from WxZdycd t where t.dqjb=2 and t.sjid="+sjid;
		return this.findPageByHQL(rowHql, countHql, start, limit);
	}
	
	
	@Transactional
	public void saveGroupInfo(WxZdycd groupInfo) {
		this.saveOrUpdate(groupInfo);
	}
	
	public void updateWxZdycd(Long groupId, String groupName) {
		String hql = "update WxZdycd t set t.name = ? where t.groupId = ?";
		this.bulkUpdate(hql, groupName, groupId);
	}

	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:38:44 
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}

	/**
	 * 根据上级id找出下级菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午10:14:06 
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getSonMenuList(Long id) {
		String hql = "select new Map(t.id as id, t.mc as mc) from WxZdycd t where t.sjid="+id;
		return this.findByHQL(hql);
	}

	/**
	 * 查出以及菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 下午4:33:36 
	 * @return
	 */
	public List<Map<String, Object>> getListMapParent() {
		String sql = "select t.mc as \"name\",t.id as \"id\",t.lx as \"type\",t.kz as \"key\",t.lj as \"url\" from wx_zdycd t where t.dqjb=1 order by t.px asc ";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据上级id查出下级菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 下午4:48:37 
	 * @param parseInt
	 * @return
	 */
	public List<Map<String, Object>> getListMapSon(Integer sjid) {
		String sql = "select t.id as \"id\",t.mc as \"name\",t.lx as \"type\",t.kz as \"key\",t.lj as \"url\" from wx_zdycd t "+
					 "where t.dqjb=2 and t.sjid="+sjid+" order by t.px asc";
		return jdbcTemplate.queryForList(sql);
	}


}
