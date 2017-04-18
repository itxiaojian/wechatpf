package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysGjzgl;

/**
 * 关键字dao
 * @author : zhangyi
 * @version 创建时间：2016年8月23日 下午3:07:30
 */
@Repository
public class SysGjzglDao extends HibernateBaseDaoImpl<SysGjzgl, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	public Pagination<Map<String, Object>> getSysGjzglList(Integer start, Integer limit) {
		String sql = "select t.id,t.gjz,t.zt FROM  sys_gjzgl t ";
        return jdbcPager.queryPage(sql, start, limit);
	}

	/**
	 * 获取所有关键字
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月24日 上午9:16:41 
	 * @return
	 */
	public List<Map<String, Object>> getSysGjzglListAll() {
		String sql = "select t.id ,t.gjz,t.zt from sys_gjzgl t ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据字典种类找出字典
	   @author: chenhui 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
}
