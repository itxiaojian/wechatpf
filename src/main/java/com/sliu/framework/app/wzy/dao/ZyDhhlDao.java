package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wzy.model.ZyDhhl;



/**
 * 主页--电话黄历
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:48:46
 */
@Repository
public class ZyDhhlDao  extends HibernateBaseDaoImpl<ZyDhhl, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询电话黄页
	 * @author  duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:48:54
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getDhhlList(Integer start, Integer limit){
		
		String sql = " select a.id, a.bmmc, a.dhhm, a.bz from ZY_DHHL a  ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 查询一键救援详情
	 * @author duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:49:06
	 * @return
	 */
	public List<Map<String, Object>> getCx(){
		
		String sql = " select a.id, a.bmmc, a.dhhm, a.bz from ZY_DHHL a  ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 删除
	 * @author duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:49:13
	 * @param id  主键ID
	 */
	public void deleteDhhl(Long id){
		String sql = "delete from zy_dhhl where id="+id;
				jdbcTemplate.execute(sql);
	}
}
