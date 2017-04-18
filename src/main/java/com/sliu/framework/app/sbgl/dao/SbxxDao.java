package com.sliu.framework.app.sbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbxx;

/**
 * 设备管理Dao
 * @author : zhangyi
 * @version 创建时间：2015年8月20日 上午9:28:45
 */
@Repository
public class SbxxDao extends HibernateBaseDaoImpl<SbSbxx, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	

	public Pagination<Map<String,Object>> getSbxxList(Integer start, Integer limit,String sybm,String syzt,String gjz,String cd) {

//		String sql = "select a.id,a.xwbt,a.xwzs,a.xwnr,DATE_FORMAT(a.sxsj,'%Y-%m-%d %H:%i:%S') as sxsj,DATE_FORMAT(a.gqsj,'%Y-%m-%d %H:%i:%S') as gqsj,a.fbr,a.fbrxm,b.bmmc,a.bmbh,a.xwlx,a.xwzt from ZY_XYXW a "
//				+ " left join SYS_ZZJG b on a.BMBH=b.BMBH where 1=1";
		String str = "";
		if(cd!=null&&!"".equals(cd)){
			str=str+" and concat(ifnull(sbbh,''),ifnull(sbmc,''),ifnull(sbxh,''),ifnull(sybm,'')) like '%"+cd+"%'";
		}
		
		String sql = "select a.id,a.SBLB as sblb,d.zdmc as lbmc,a.SBMC as sbmc,a.SBBH as sbbh,a.SBXH as sbxh,a.SBJB as sbjb,a.SYBMBH as sybmbh"
				+ ",a.SYBM as sybm,a.SYFW as syfw,a.SCCJ as sccj,a.CCBH as ccbh,DATE_FORMAT(a.CCRQ,'%Y-%m-%d') as ccrq,"
				+ "DATE_FORMAT(a.GMRQ,'%Y-%m-%d') as gmrq,a.GMJG as gmjg,a.JDZQ as jdzq,a.SYQX as syqx,DATE_FORMAT(a.SCJDRQ,'%Y-%m-%d') as scjdrq,"
				+ "DATE_FORMAT(a.XCJDRQ,'%Y-%m-%d') as xcjdrq,a.SYZT as syzt,a.SBWHR as sbwhr,b.xm as sbwhrxm,a.ZJFF as zjff,e.zdmc as syztmc,f.zdmc as zjffmc,"
				+ "a.ZCYZ as zcyz,a.JCL as jcl,a.ZJNX as zjnx,a.ZCJZ as zcjz, a.jddw,case when a.jddw='0' then CONCAT(a.jdzq,'年') "
				+ "when a.jddw='1' then CONCAT(a.jdzq,'月') when a.jddw='2' then CONCAT(a.jdzq,'日') end as jdzqs from SB_SBXX a "
				+ "left join sys_yh b on a.sbwhr=b.dlm left join (select zdz,zdmc,zdbm from sys_sjzd where zl='sbsyzk' and jb='2' ) e on a.syzt=e.zdz "
				+ "left join (select zdz,zdmc,zdbm from sys_sjzd where zl='zjff' and jb='2' ) f on a.zjff=f.zdz "
				+ "left join (select zdz,zdmc,zdbm from sys_sjzd where zl='sblb' and jb='2' ) d on a.sblb=d.zdz where 1=1 "+str;
		
		if (StringUtils.hasText(sybm)) {
			sql += " and a.sybm = '"+sybm+"'";
		}
		if (StringUtils.hasText(syzt)) {
			sql += " and a.syzt = '"+syzt+"'";
		}
		
		sql += " order by a.GMRQ desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}


	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:38:44 
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}


	/**
	 * 报废功能
	 * @author:liujiansen
	 * @version 创建时间：2015年6月10日 上午11:22:07 
	 * @param ids
	 * @return
	 */
	public void sbBfcl(String id) {
		String sql="UPDATE sb_sbxx SET  SYZT = '6'  WHERE ID = "+id;
		jdbcTemplate.execute(sql);
	}

}
