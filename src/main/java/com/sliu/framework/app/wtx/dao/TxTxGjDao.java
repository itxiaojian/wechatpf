package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TxTxGjDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取提醒开关
	 * @author oufeng
	 * @date 2016年8月15日
	 * @return
	 */
	public List<Map<String,Object>> getSfkq(String zl){
		String sql="SELECT kgmc,kgzl,kgzt FROM tx_kg where kgzl='"+zl+"'";
		return jdbcTemplate.queryForList(sql);
	}

}
