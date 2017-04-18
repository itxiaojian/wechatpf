package com.sliu.framework.app.sbgl.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbjcxx;

/**
 * 设备借出信息
 * @author duanpeijun
 * @date 2015年8月24日
 */
@Repository
public class SbSbjcxxDao  extends HibernateBaseDaoImpl<SbSbjcxx, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 后台:查询设备借出信息 列表
	 * @author duanpeijun
	 * @date 2015年8月24日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbjcxxList(Integer start, Integer limit,String jczt,String jc){
		
		String str = "";
		if(jc!=null&&!"".equals(jc)){
			str=str+" and concat(ifnull(a.sbbh,''),ifnull(b.sbmc,''),ifnull(a.jcrbh,''),ifnull(a.jcrxm,''),ifnull(a.jcdwbh,'')"
					+ ",ifnull(a.jcdwxm,''),ifnull(a.fzrbh,''),ifnull(a.fzrxm,'')) like '%"+jc+"%'";
		}
		if(jczt!=null&&!"".equals(jczt)){
			str=str+" and a.jczt ='"+jczt+"'";
		}
		String sql = " select a.id,a.sbbh,b.sbmc,a.jcrbh,a.jcrxm,a.jcdwbh,a.jcdwxm,a.fzrbh,a.fzrxm,DATE_FORMAT(a.jcsj,'%Y-%m-%d') as jcsj,"
					+ "DATE_FORMAT(a.ghsj,'%Y-%m-%d') as ghsj,a.jczt,a.bz from sb_sbjcxx a left join sb_sbxx b on a.sbbh=b.sbbh where 1=1"+ str;
		
		sql += " order by ghsj desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
}
