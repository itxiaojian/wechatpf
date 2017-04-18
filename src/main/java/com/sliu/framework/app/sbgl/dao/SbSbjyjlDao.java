package com.sliu.framework.app.sbgl.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbjyjl;

/**
 * 设备检验记录
 * @author duanpeijun
 * @date 2015年8月21日
 */
@Repository
public class SbSbjyjlDao  extends HibernateBaseDaoImpl<SbSbjyjl, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 后台:查询设备检验记录列表
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbjyjlList(Integer start, Integer limit,String jl,String jyzt){
		
		String str = "";
		if(jl!=null&&!"".equals(jl)){
			str=str+" and concat(ifnull(b.sbbh,''),ifnull(b.sbmc,''),ifnull(b.sbxh,'')) like '%"+jl+"%'";
		}
		if(jyzt!=null&&!"".equals(jyzt)){
			str=str+" and a.jyzt ='"+jyzt+"'";
		}
		String sql = " select a.id,a.sbbh,b.sbmc,b.sbxh,b.sybm,a.jyfs,DATE_FORMAT(a.sjsj,'%Y-%m-%d') as sjsj,a.sjr,a.jydw,a.lxdh,a.jyzt,a.jyfy,a.bz"
				   + " from sb_sbjyjl a left join sb_sbxx b on a.sbbh=b.sbbh where 1=1"+str;
		
		sql += " order by sjsj desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
}
