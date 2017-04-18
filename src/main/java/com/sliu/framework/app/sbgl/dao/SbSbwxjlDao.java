package com.sliu.framework.app.sbgl.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbwxjl;

/**
 * 设备检验记录
 * @author duanpeijun
 * @date 2015年8月21日
 */
@Repository
public class SbSbwxjlDao  extends HibernateBaseDaoImpl<SbSbwxjl, Long> {

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
		String sql = " SELECT a.id, a.sbbh,b.sbmc,b.sbxh,b.sybm,a.jjd, a.wxdh, a.wxsqr, a.gzms, DATE_FORMAT(a.sxsj,'%Y-%m-%d') as sxsj, "
				+ "a.wxdw, a.wxfzr, a.wxzt, a.wxjb,c.zdmc as jb, a.gzlb,d.zdmc as lb, DATE_FORMAT(a.kssj,'%Y-%m-%d') as kssj, "
				+ "DATE_FORMAT(a.wcsj,'%Y-%m-%d') as wcsj,a.wxfy, a.gzfxjcl FROM sb_sbwxjl a left join sb_sbxx b on a.sbbh=b.sbbh left join "
				+ "(select zdz,zdmc from sys_sjzd where zl='wxjb') c on a.wxjb=c.zdz left join "
				+ "(select zdz,zdmc from sys_sjzd where zl='gzlb') d on a.gzlb=d.zdz where 1=1"+str;
		
		sql += " order by a.sxsj desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}

	/**
	 * 更新计划
	 * @author liujiansen
	 * @date 2015年8月25日
	 * @param sbbh
	 * @param gzfxjcl
	 */
	public void updateJh(String sbbh,String gzfxjcl) {
		String sql="UPDATE sb_sbwxjh SET  ZT = '2', GZMS = '"+gzfxjcl+"' WHERE SBBH = '"+sbbh+"'";
		jdbcTemplate.execute(sql);
	}
	
}
