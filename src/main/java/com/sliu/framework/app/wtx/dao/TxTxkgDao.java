package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wtx.model.TxTxkg;


@Repository
public class TxTxkgDao extends HibernateBaseDaoImpl<TxTxkg, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 后台:提醒列表的页面
	 * @author oufeng
	 * @date 2016年8月15日
	 * @param start
	 * @param limit
	 * @param code  关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getTxkgList(Integer start, Integer limit,String code){
		String str = "";
		if(code!=null&&!"".equals(code)){
			str+=" and  kgmc like '%"+code+"%'";
		}
		String sql = "SELECT a.id,a.kgmc,a.kgzl,b.zdmc,a.kgzt,a.bz FROM tx_kg a,sys_sjzd b"
		+ " WHERE a.kgzt = b.zdz  AND b.zl='kgzt' AND jb='2'"+str;
		return jdbcPager.queryPage(sql,start, limit);
	}
	
	/**
	 * 获取字典表中的提醒类型
	 * @author oufeng
	 * @date 2016年8月15日
	 * @return
	 */
	public List<Map<String,Object>> getZt(){
		String sql="select zdmc, zdz from sys_sjzd where zl='kgzt' and jb=2";
		return jdbcTemplate.queryForList(sql);
	}

}
