package com.sliu.framework.app.sbgl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sbgl.model.SbSbsgyjb;


/**
 * 设备申购审批意见表
 * @author oufeng
 * @date 2015年8月20日
 */
@Repository
public class SbSbsgyjDao extends HibernateBaseDaoImpl<SbSbsgyjb, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
}
