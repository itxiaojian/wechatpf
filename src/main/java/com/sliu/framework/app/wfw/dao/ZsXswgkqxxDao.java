package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXswgkqxx;

/**
 * 学生晚归考勤信息
 * @author zhangyan
 * @date  20165年8月9日
 */
@Repository
public class ZsXswgkqxxDao extends HibernateBaseDaoImpl<ZsXswgkqxx, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据xh查看学生的晚归考勤信息
	 * @author zhangyan
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getXskqxxDetail(String xh){
		
		String sql="select  a.id,a.xm,a.xh,a.bjmc,a.bjbh,a.ly,a.fjh,DATE_FORMAT(a.wgsj,'%Y-%m-%d %H:%i:%S')as wgsj,a.djls,a.djlsgh,a.bz from zs_xswgkqxx a "
				+ "where a.xh='"+ xh +"' order by a.wgsj desc limit 5";
		return jdbcTemplate.queryForList(sql);
	}
	
}
