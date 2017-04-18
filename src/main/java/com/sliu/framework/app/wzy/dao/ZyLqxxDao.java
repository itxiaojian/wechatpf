package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wzy.model.ZyLqxx;

/**
 * 主页--录取信息
 * @author zhangyan
 * @version 创建时间：2016年7月4日  
 */
@Repository
public class ZyLqxxDao extends HibernateBaseDaoImpl<ZyLqxx,Long> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	
	/**
	 * 查询录取信息
	 * @author zhangyan
	 * @version 创建时间：2016年7月4日  
	 * @return
	 */
	public List<Map<String,Object>> getLqxxCx(String ksh,String sfzh){
		String str="";
		if(ksh!=null &&!"".equals(ksh)){
			str=str+" and a.ksh= '"+ksh+"' ";
		}
		if(sfzh!=null &&!"".equals(sfzh)){
			str=str+" and a.sfzh= '"+sfzh+"' ";
		}
		
		String sql="select distinct a.ksh,a.sfzh,a.xm,a.lqzy,a.zf from gz_lqxx a where 1=1 "+str;
		
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 后台查询录取信息
	 * @author liusong
	 * @version 创建时间：2016年7月5日  
	 * @return
	 */
	public List<Map<String,Object>> getLqxxList(String code){
		String str="";
		if(code!=null&&!"".equals(code)){
			str=" and (a.id like '%"+code+"%' or a.ksh like '%"+code+"%' or a.xm like '%"+code+"%' or a.sfzh like '%"+code+"%' or a.lqzy like '%"+code+"%' or a.zf like '%"+code+"%' ) ";
		}
		String sql="select a.ksh,a.sfzh,a.xm,a.lqzy,a.zf from gz_lqxx a where 1=1 "+str;
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 分页查询电话黄页
	 * @author  liusong
	 * @version 创建时间：2015年6月3日  下午3:48:54
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getLqxxList(Integer start, Integer limit,String code){
		String str="";
		if(code!=null&&!"".equals(code)){
			str=" and (a.id like '%"+code+"%' or a.ksh like '%"+code+"%' or a.xm like '%"+code+"%' or a.sfzh like '%"+code+"%' or a.lqzy like '%"+code+"%' or a.zf like '%"+code+"%' ) ";
		}
		
		String sql = " select a.id, a.ksh, a.xm, a.sfzh,a.lqzy,a.zf from gz_lqxx a where 1=1 "+str;
		
		return jdbcPager.queryPage(sql, start, limit);
	}
}
