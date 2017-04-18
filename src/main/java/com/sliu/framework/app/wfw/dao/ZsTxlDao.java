package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysWxyh;

@Repository
public class ZsTxlDao extends HibernateBaseDaoImpl<SysWxyh, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * @author wangxiangyang
	 * @date 2016年6月12日
	 * @return
	 */
	public List<Map<String,Object>> getTxlList1(String openId){

		String sql="select a.yhxm,a.dhhm,a.bz from zs_txl a where 1=1 order by convert(a.yhxm using gbk) asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * @author wangxiangyang
	 * @date 2016年6月12日
	 * @return
	 */
	public List<Map<String,Object>> getTxlList(String txlxx,String openId){

		String sql="select a.yhxm,a.dhhm,a.bz from zs_txl a where 1=1 and a.yhxm like '%"+txlxx+"%' or a.dhhm like '%"+txlxx+"%'";
		return jdbcTemplate.queryForList(sql);
	}
}
