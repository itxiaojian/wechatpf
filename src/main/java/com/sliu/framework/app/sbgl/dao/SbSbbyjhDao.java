package com.sliu.framework.app.sbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbbyjh;

/**
 * 设备检验计划
 * @author liujiansen
 * @date 2015年8月20日
 */
@Repository
public class SbSbbyjhDao  extends HibernateBaseDaoImpl<SbSbbyjh, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 后台:查询设备检验计划 列表
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbbyjhList(Integer start, Integer limit,String code,String byjb){
		
		String str = "";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.sbbh,''),ifnull(b.sbmc,'')) like '%"+code+"%'";
		}
		if(byjb!=null&&!"".equals(byjb)){
			str=str+" and a.byjb ='"+byjb+"'";
		}
		String sql = " SELECT a.id, a.sbbh,b.sbmc, a.byjb,c.zdmc,  a.xhfs, b.sbxh,b.sybm, "
				+ "DATE_FORMAT(a.scbysj,'%Y-%m-%d') as scbysj, a.jg, DATE_FORMAT(a.xcbysj,'%Y-%m-%d') as xcbysj, "
				+ "a.byrbh, a.byrxm, a.zt, a.gzms FROM sb_sbbyjh a "
				+ "left join sb_sbxx b on a.sbbh=b.sbbh left join (select zdz,zdmc from sys_sjzd where zl='byjb') c on a.byjb=c.zdz where 1=1"+str;
		
		sql += " order by a.xcbysj desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:38:44 
	 * @param zdzl  字典种类
	 * @return 
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:38:44 
	 * @param zdzl  字典种类
	 * @return 
	 */
	public List<Map<String, Object>> getDicByYxlx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2' and a.zdz='1'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
}
