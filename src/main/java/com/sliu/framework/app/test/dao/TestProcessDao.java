package com.sliu.framework.app.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.process.model.TestProcess;

@Repository
public class TestProcessDao extends HibernateBaseDaoImpl<TestProcess, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Pagination<Object> getAllTestProcess(Integer start, Integer limit) {
		String rowHql = "select new Map(t.id as id,t.name as name,t.processcode as processcode,t.processStatus as processStatus) from TestProcess t";
		String countHql = "select count(t.id) from TestProcess t ";
		return this.findPageByHQL(rowHql, countHql, start, limit );
	}
	
}
