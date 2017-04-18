package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsTsfl;

/**
 * 图书分类
 * @author duanpeijun
 * @date  2015年6月19日
 */
@Repository
public class ZsTsflDao extends HibernateBaseDaoImpl<ZsTsfl, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据页数和标题查询图书分类数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param zl   种类
	 * @return
	 */
	public List<Map<String,Object>> getTsflList(String pages,String zl){
		String str="";
		if(zl!=null&&!"".equals(zl)){
			str=str+" and zl like '%"+zl+"%' ";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			str=str+" order by id DESC limit "+num+" ";
		}
		String sql="select id,zl,sl,ifnull(bz,'')as bz from "
				+ "zs_tsfl where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 根据页数和标题查询图书分类数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param zl   种类
	 * @return
	 */
	public List<Map<String,Object>> getTsflListZj(String pages,String zl){
		String str="";
		if(zl!=null&&!"".equals(zl)){
			str=str+" and zl like '%"+zl+"%' ";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*5;
			str=str+" order by id DESC limit "+num+",5 ";
		}
		String sql="select id,zl,sl,ifnull(bz,'')as bz from "
				+ "zs_tsfl where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
