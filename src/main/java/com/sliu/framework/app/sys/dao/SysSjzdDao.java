package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysSjzd;

/**
 * @author:zhangyi
 * @version 创建时间：2015年9月2日 下午4:38:07 类说明
 */
@Repository
public class SysSjzdDao extends HibernateBaseDaoImpl<SysSjzd, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;


	/**
	 * 获取用户列表
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月21日 上午11:10:16 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	public Pagination<Map<String, Object>> getFirstList(Integer start, Integer limit,String zdzl,String zdmc ) {
		String str="";
		if(zdzl!=null&&!"".equals(zdzl)) {
			str = str +" and t.zl like '%"+zdzl+"%'";
		}
		
		if(zdmc!=null&&!"".equals(zdmc)) {
			str = str +" and t.zdmc like '%"+zdmc+"%'";
		}
		
		String sql = " select t.id,t.zdbm,t.zdmc,t.zdz,t.jb,t.zl,t.px,b.count from sys_sjzd t left JOIN "
				+ " (select max(px)as count,zl from sys_sjzd group by zl) b on t.zl = b.zl where t.jb='1' "+str+" order by t.id ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	

	public Pagination<Map<String, Object>> getSecondDict(Integer start,
			Integer limit, String zl) {
		String sql = " select t.id,t.zdbm,t.zdmc,t.zdz,t.jb,t.zl,t.px from sys_sjzd t where t.jb='2' and t.zl='"+zl+"' order by t.px ";
		return jdbcPager.queryPage(sql, start, limit);
	}


	public List<Map<String, Object>> checkzl(String zl) {
		String sql = " select * from sys_sjzd t where t.jb='1' and t.zl='"+zl+"'";
		return jdbcTemplate.queryForList(sql);
	}


	public void deleteson(String zl) {
		String sql = "delete from sys_sjzd where zl = '"+zl+"'";
		jdbcTemplate.execute(sql);
	}

}
