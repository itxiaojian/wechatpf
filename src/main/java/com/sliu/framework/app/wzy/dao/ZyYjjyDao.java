package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wzy.model.ZyYjjy;



/**
 * 主页--  一键救援
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:54:35
 */
@Repository
public class ZyYjjyDao  extends HibernateBaseDaoImpl<ZyYjjy, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询一键救援
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:54:42
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getYjjyList(Integer start, Integer limit){
		
		String sql = " select a.id, a.bmmc, a.dhhm, a.bz from ZY_YJJY a  ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 查询
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:54:48
	 * @return
	 */
	public List<Map<String, Object>> getCx(){
		String sql = " select a.id, a.bmmc, a.dhhm, a.bz from ZY_YJJY a  ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 删除
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:54:54
	 * @param id  主键
	 */
	public void deleteYjjy(Long id){
		String sql = "delete from zy_yjjy where id="+id;
				jdbcTemplate.execute(sql);
	}
}
