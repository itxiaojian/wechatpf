package com.sliu.framework.app.sbgl.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbbyjl;

/**
 * 设备检验记录
 * @author duanpeijun
 * @date 2015年8月21日
 */
@Repository
public class SbSbbyjlDao  extends HibernateBaseDaoImpl<SbSbbyjl, Long> {

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
	public Pagination<Map<String, Object>> getSbwxjlList(Integer start, Integer limit,String code,String jyzt,String sblb){
		
		String str = "";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.sbbh,''),ifnull(b.sbmc,'')) like '%"+code+"%'";
		}
		if(jyzt!=null&&!"".equals(jyzt)){
			str=str+" and a.wxjb ='"+jyzt+"'";
		}
		if(sblb!=null&&!"".equals(sblb)){
			str=str+" and b.sblb ='"+sblb+"'";
		}
		String sql = "SELECT a.id, a.sbbh,b.sbmc,b.sbxh,b.sybm, a.wxdh, a.wxsqr, a.wxdw, a.wxfzr, a.wxzt, a.wxjb,c.zdmc as jb, "
				+ "DATE_FORMAT(a.kssj,'%Y-%m-%d') as kssj, DATE_FORMAT(a.wcsj,'%Y-%m-%d') as wcsj, a.bypf, a.gzms FROM sb_sbbyjl a "
				+ "left join sb_sbxx b on a.sbbh=b.sbbh left join (select zdz,zdmc from sys_sjzd where zl='byjb') c "
				+ "on a.wxjb=c.zdz where 1=1"+str;
		
		sql += " order by a.id desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
}
