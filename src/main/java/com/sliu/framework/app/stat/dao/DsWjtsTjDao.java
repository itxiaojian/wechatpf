package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.stat.model.DsTsjyxx;

@Repository
public class DsWjtsTjDao extends HibernateBaseDaoImpl<DsTsjyxx, String> {
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
				str1="  and a.tsbh="+str;
		}else{
			str1="";
		}
		String sql = "select a.tsbh as id, b.zl  as  tsmc,count(b.zl) as sl  from zs_tsjyxx  a,zs_tsfl b  where 1=1 "
				+ " and  a.tsbh=b.id"
				+str1
				+ " group by a.id,b.zl  "
				+ " order  by  a.tsbh";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得图书外借种类名称
	 * */
	public List<Map<String, Object>> getTsjyxx() {
		String sql ="select  a.tsbh as id,b.zl as  tsmc  from zs_tsjyxx	a,zs_tsfl b  where a.tsbh=b.id  group by a.tsbh,b.zl  order by a.tsbh";
		return jdbcTemplate.queryForList(sql);
	}
}
