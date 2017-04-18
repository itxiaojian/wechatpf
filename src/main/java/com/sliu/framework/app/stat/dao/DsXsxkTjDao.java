package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.stat.model.DsXxkxx;

@Repository
public class DsXsxkTjDao extends HibernateBaseDaoImpl<DsXxkxx, String> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 @Author oufeng
	 * @Date 2015年7月22日 下午3:09:56
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getData(String str) {
		String str1="";
			if(  str!=null && str!=""){
				str1="  and a.kcbh="+"'"+str+"'";
		}else{
			str1="";
		}
		String sql = "select a.kcbh, a.kcmc,count(a.kcbh) as sl  from zs_xxkxx  a where 1=1 "
				+str1
				+ " group by  a.kcbh,a.kcmc "
				+ " order  by  a.kcbh";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得图书外借种类名称
	 * */
	public List<Map<String, Object>> getLession() {
		String sql ="select  a.kcbh,a.kcmc  from zs_xxkxx	a order by a.kcbh ";
		return jdbcTemplate.queryForList(sql);
	}
}
