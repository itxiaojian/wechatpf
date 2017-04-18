package com.sliu.framework.app.wsh.dao;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wsh.model.ShWxcjpz;

/**
 * 微信抽奖配置 DAO
 * @author            liujiansen
 * @since             2015-06-18
 */
@Repository
public class ShWxcjpzDao extends HibernateBaseDaoImpl<ShWxcjpz, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 微信抽奖配置列表
	 * @author liujiasen
	 * @date 2015年6月18日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String,Object>> getWxpzList(Integer start, Integer limit) {
		String str="";
		String sql = "SELECT id, DATE_FORMAT(qssj,'%Y-%m-%d') as qssj, DATE_FORMAT(jssj,'%Y-%m-%d') as jssj, "
				+ "cjcs, ydjmc, ydjsl, edjmc, rdjsl, sdjmc, sdjsl, bz FROM sh_wxcjpz where 1=1 "+str;
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 获取结束日期为2999-12-31的数据
	 * @author liujiansen
	 * @date 2015年6月19日
	 * @param time
	 * @return
	 */
	public List<Map<String,Object>> getWxcj(){
		String sql="select id,cjcs,ydjmc,ydjsl,edjmc,rdjsl,DATE_FORMAT(qssj,'%Y-%m-%d') as qssj,"
				+ "DATE_FORMAT(jssj,'%Y-%m-%d') as jssj,sdjmc,sdjsl,bz from sh_wxcjpz where jssj="
				+ "(select max(jssj) from sh_wxcjpz)";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据结束时间获取微信抽奖配置信息 
	 * @author liujiansen
	 * @date 2015年6月19日
	 * @param jssj
	 * @return
	 */
	public List<Map<String,Object>> getWxcjByTime(String jssj){
		String sql="select id,cjcs,ydjmc,ydjsl,edjmc,rdjsl,DATE_FORMAT(qssj,'%Y-%m-%d') as qssj,"
				+ "DATE_FORMAT(jssj,'%Y-%m-%d') as jssj,sdjmc,sdjsl,bz from sh_wxcjpz where DATE_FORMAT(jssj,'%Y-%m-%d')="
				+ "'"+jssj+"'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获取最大的截止时间
	 * @author liujiansen
	 * @date 2015年12月3日
	 * @return
	 */
	public List<Map<String, Object>> getMaxJssj() {
		String sql="select DATE_FORMAT(max(jssj),'%Y-%m-%d') as jssj from sh_wxcjpz";
		return jdbcTemplate.queryForList(sql);
	}
}

