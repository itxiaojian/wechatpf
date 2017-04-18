package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.stat.model.DsJsxl;

@Repository
public class DsJsxlTjDao extends HibernateBaseDaoImpl<DsJsxl, String> {
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
				str1="  and xlbh="+str;
		}else{
			str1="";
		}
		String sql = "select xlbh,jsxl,count(jsxl) as sl  from zs_jsxl_test   where 1=1 "
				+str1
				+ " group by xlbh,jsxl  "
				+ " order  by  xlbh";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得教师学历名称
	 * */
	public List<Map<String, Object>> getJsxl() {
		String sql ="select  xlbh,jsxl  from zs_jsxl_test	 group by xlbh,jsxl  order by id";
		return jdbcTemplate.queryForList(sql);
	}
}
