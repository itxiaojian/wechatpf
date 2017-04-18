package com.sliu.framework.app.wtx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;

/**
*未读邮件
@Author oufeng	
@Date 2016年8月10日 下午15:02:48
@Version 1.0
*/
@Repository
public class TxWdyjDao extends HibernateBaseDaoImpl<TxTxxxlsjl, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	
}
