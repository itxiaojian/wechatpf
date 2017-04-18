package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsJxzlpj;

@Repository
public class ZsJxzlpjDao extends HibernateBaseDaoImpl<ZsJxzlpj, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 判断是否已经评价过
	 * @author duanpeijun
	 * @date  2015年6月25日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getJxzlpjList(Long id){
		
		String sql = "select a.id  from zs_jxzlpj a where a.pjid = "+id;
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
		
		}
	
	
}
