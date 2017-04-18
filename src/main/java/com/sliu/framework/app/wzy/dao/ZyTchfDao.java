package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wzy.model.ZyTchf;

/**
 * 吐槽回复
 * @author duanpeijun
 * @version 创建时间：2015年6月16日
 */
@Repository
public class ZyTchfDao extends HibernateBaseDaoImpl<ZyTchf, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 回复列表的查询
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月17日  
	 * @param id
	 * @return
	 */
       public List<Map<String, Object>> getHfCx(Long id){
		
		String sql = " select a.id, a.tczj, a.hfnr, DATE_FORMAT(a.hfsj,'%Y-%m-%d') as hfsj, a.gqsj, a.hfr, a.hfrxm, a.bmmc, a.bmbh, a.zt from ZY_tchf  a "
				+" where a.tczj = "+id +" order by a.hfsj desc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
       
   	/**
   	 * 回复列表的查询
   	 * @author   duanpeijun
   	 * @version 创建时间：2015年6月17日  
   	 * @param id
   	 * @return
   	 */
          public List<Map<String, Object>> getHfCx1(){
   		
   		String sql = " select a.id, a.tczj, a.hfnr, DATE_FORMAT(a.hfsj,'%Y-%m-%d') as hfsj, a.gqsj, a.hfr, a.hfrxm, a.bmmc, a.bmbh, a.zt from ZY_tchf  a "
   				+" order by a.hfsj desc";
   		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
   		return list;
   	}
	
	
	
	
	
}
