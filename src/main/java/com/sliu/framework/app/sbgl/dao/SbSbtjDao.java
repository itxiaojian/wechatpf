package com.sliu.framework.app.sbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sbgl.model.SbSbxx;

/**
*设备统计
@Author oufeng	
@Date 2015年8月26日 下午2:46:03
@Version 1.0
*/
@Repository
public class SbSbtjDao extends HibernateBaseDaoImpl<SbSbxx, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 设备类型统计
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getDataLx(String lx,String stime,String etime) {
		String str="";
		String str1="";
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(gmrq,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(gmrq,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  lx!=null && lx!="" && !"".equals(lx)){
			str1="  and sbxh='"+lx+"'";
	}else{
		str1="";
	}
		String sql = "select sbxh as sblx,count(sbxh) as sbsl  from sb_sbxx  where 1=1 "
				+str
				+str1
				+ " group by sbxh  "
				+ " order  by  sbxh";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 设备总资产统计
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getDataZzc(String zzc,String stime,String etime) {
		String str="";
		String str1="";
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(a.gmrq,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(a.gmrq,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  zzc!=null && zzc!="" &&!"".equals(zzc)){
			str1="  and a.sybmbh='"+zzc+"'";
	   }else{
		str1="";
	}
		String sql = "select b.bmmc as bmmc,sum(zcyz) as zcyz  from sb_sbxx a ,sys_zzjg b  where 1=1 "
				+" and a.sybmbh = b.bmbh "
				+str
				+str1
				+ " group by b.bmmc  "
				+ " order  by  a.sybm";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 设备总资产统计
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getZzc(String zzc,String stime,String etime) {
		String str1="";
		String str ="";
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(a.gmrq,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(a.gmrq,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  zzc!=null && zzc!="" &&!"".equals(zzc)){
			str1="  and a.sybmbh='"+zzc+"'";
	}else{
		str1="";
	}
		String sql = "select b.bmmc as zl,sum(zcyz) as sl  from sb_sbxx a ,sys_zzjg b  where 1=1 "
				+" and a.sybmbh = b.bmbh "
				+str1
				+str
				+ " group by b.bmmc  "
				+ " order  by  a.sybm";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 @Author oufeng
	* @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getData(String lx,String stime,String etime) {
		String str1="";
		String str="";
		if(  lx!=null && lx!="" &&! "".equals(lx)){
				str1="  and sbxh='"+lx+"'";
		}else{
			str1="";
		}
			if (stime != null && !"".equals(stime)) {
				str += " and  DATE_FORMAT(gmrq,'%Y-%m-%d') >='" + stime + "'";
			}
			if (etime != null && !"".equals(etime)) {
				str += " and  DATE_FORMAT(gmrq,'%Y-%m-%d')  <= '" + etime + "'";
			}
			String sql = "select id,sbxh as zl,count(sbxh) as sl  from sb_sbxx    where 1=1 "
					+str1
					+str
					+ " group by id,sbxh  "
					+ " order  by  id";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得设备类型
	 * */
	public List<Map<String, Object>> getSblx() {
		String sql ="select  sbxh  from sb_sbxx 	group by sbxh order by id";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门名称
	 * */
	public List<Map<String, Object>> getBmmc() {
		String sql ="select a.sybmbh, b.bmmc  from sb_sbxx a ,sys_zzjg b  where 1=1 "
				+" and a.sybmbh = b.bmbh"
				+ " group by b.bmmc  "
				+ " order  by  a.sybm";
		return jdbcTemplate.queryForList(sql);
	}
}


