package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;


@Repository
public class TxOaDbsxDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取用户编号
	 * @author oufeng
	 * @date 2016年8月3日
	 * @return
	 */
	public  List<Map<String,Object>> getYhbh(){
		String sql="select a.yhbh,a.xm,c.jsmc,d.wxh "
				+ " from sys_yh a,sys_yhjs b,sys_js c, "
				+ " sys_wxyh d where a.yhbh=b.yhbh"
				+ " and b.jsbh=c.jsbh and a.yhbh= d.yhid and c.jsmc "
				+ " in('ROLE_TEACHER','ROLE_INSTRUCTOR','ROLE_LEADER')"
				+ " order by a.yhbh ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户编号
	 * @author oufeng
	 * @date 2016年8月4日
	 * @return
	 */
	public List<Map<String,Object>> getWxyh(String wxh){
		String sql="select b.yhbh,b.xm,a.wxh  from sys_wxyh a ,sys_yh b "
				+ " where a.yhid=b.yhbh and b.yhzt='1' and a.zt='1' and  wxh='"+wxh+"'";
		return jdbcTemplate.queryForList(sql);
	}

}
