package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysMenu;


@Repository
public class SysMenuDao extends HibernateBaseDaoImpl<SysMenu, String> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	public List<Map<String, Object>> getMenuList(String str) {
		String cs="";
		if(str!=null && !"".equals(str)){
			if("root".equals(str)){
				cs = "and cdjb = 1";
			}else{
				cs= " and cdjb = 2 ";
			}
		}
		String sql = "select CDBH,CDMC,CDURL,SJCD,CDJB,BZ from sys_yh where 1 = 1"+cs;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取一级菜单
	 * @author:chenhui
	 * @version 创建时间：2016年8月3日 上午11:09:25 
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Object> getFirstMenu(Integer start,Integer limit) {
		String rowHql = "from SysMenu t where t.cdjb=1 order by t.px asc";
		String countHql = "select count(t.id) from SysMenu t where t.cdjb=1";
		return this.findPageByHQL(rowHql, countHql, start, limit);
	}
	
	/**
	 * 根据上级菜单查出子级菜单
	 * @author:sliu 
	 * @version 创建时间：2016年8月3日 上午11:13:41 
	 * @param start
	 * @param limit
	 * @param sjid 上级id
	 * @return
	 */
	public Pagination<Object> getSonMenu(Integer start, Integer limit,
			Integer sjid) {
		String rowHql = "from SysMenu t where t.cdjb=2 and t.sjcd="+sjid+" order by t.px asc";
		String countHql = "select count(t.id) from SysMenu t where t.cdjb=2 and t.sjcd="+sjid;
		return this.findPageByHQL(rowHql, countHql, start, limit);
	}
	
	/**
	 * 根据上级id找出下级菜单
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:14:06 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSonMenuList(String cdbh) {
		String hql = "select t.cdbh from SysMenu t where t.sjcd="+cdbh;
		return this.findByHQL(hql);
	}
	
	/**
	 * 校验菜单编号是否重复
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:14:06 
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getcdbh(Long cdbh) {
		String sql = "select t.cdbh,t.cdmc from SysMenu t where t.cdbh="+cdbh;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询所有菜单列表
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:14:06 
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getAllList() {
		String sql = "select t.cdbh,t.cdmc,t.cdurl,t.sjcd,t.px,t.cdjb,t.bz from sys_cd t order by px";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询当前用户菜单列表
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午10:14:06 
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getCurrentUserMenu(String yhbh) {
		String sql = "select t.cdbh,t.cdmc,t.cdurl,t.sjcd,t.cdjb,t.px,t.bz from sys_cd t "
				+ " where t.cdbh in(select cdbh from sys_jscd where jsbh in(select jsbh from sys_yhjs where yhbh = '"+yhbh+"')) order by px";
		return jdbcTemplate.queryForList(sql);
	}

}
