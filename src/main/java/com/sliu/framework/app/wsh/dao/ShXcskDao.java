package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wsh.model.ShXcsk;

@Repository
public class ShXcskDao extends HibernateBaseDaoImpl<ShXcsk, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询校车时刻查询
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getXcskList(Integer start, Integer limit){
		
		String sql = " select a.id, a.cph,DATE_FORMAT(a.rq,'%Y-%m-%d %H:%i:%S') as rq,  "
				+ "DATE_FORMAT(a.cfsj,'%Y-%m-%d %H:%i:%S') as cfsj,a.cfd,a.mdd,"
				+ "a.fbr,a.fbrxm,a.zt from sh_xcsk a  ";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @return
	 */
	public List<Map<String, Object>> getCx(){
		
		String sql = " select a.id, a.cph,DATE_FORMAT(a.rq,'%Y-%m-%d %H:%i:%S') as rq,  "
				+ "DATE_FORMAT(a.cfsj,'%Y-%m-%d %H:%i:%S') as cfsj,a.cfd,"
				+ "a.mdd,a.fbr,a.fbrxm,a.zt from sh_xcsk a    ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 按车牌号查询
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @return
	 */
	public Pagination<Map<String, Object>> getCph(Integer start, Integer limit,String cph){
		
		String sql = " select a.id, a.cph,DATE_FORMAT(a.rq,'%Y-%m-%d %H:%i:%S') as rq,  "
				+ "DATE_FORMAT(a.cfsj,'%Y-%m-%d %H:%i:%S') as cfsj,a.cfd,"
				+ "a.mdd,a.fbr,a.fbrxm,a.zt from sh_xcsk a   where  a.cph like '"+cph+"%'";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 删除
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param id  主键ID
	 */
	public void deleteShxcsk(Long id){
		String sql = "delete from sh_xcsk where id="+id;
				jdbcTemplate.execute(sql);
	}
}
