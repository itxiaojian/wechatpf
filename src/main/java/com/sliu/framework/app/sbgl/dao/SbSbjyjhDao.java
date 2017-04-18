package com.sliu.framework.app.sbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbjyjh;

/**
 * 设备检验计划
 * @author duanpeijun
 * @date 2015年8月20日
 */
@Repository
public class SbSbjyjhDao  extends HibernateBaseDaoImpl<SbSbjyjh, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 后台:查询设备检验计划 列表
	 * @author duanpeijun
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbjyjhList(Integer start, Integer limit,String jh,String wxrbh){
		
		String str = "";
		if(jh!=null&&!"".equals(jh)){
			str=str+" and concat(ifnull(b.sbbh,''),ifnull(b.sbmc,''),ifnull(b.sbxh,'')) like '%"+jh+"%'";
		}
		if(wxrbh!=null&&!"".equals(wxrbh)){
			str=str+" and a.wxrbh ='"+wxrbh+"'";
		}
		String sql = " select a.id,a.sbbh,b.sbmc,b.sbxh,b.sybm,a.jyfs,DATE_FORMAT(a.scwxsj,'%Y-%m-%d') as scwxsj,a.jg,DATE_FORMAT(a.xcwxsj,'%Y-%m-%d') as xcwxsj,"
				   + "a.wxrbh,a.jddw from sb_sbjyjh a left join sb_sbxx b on a.sbbh=b.sbbh where 1=1"+str;
		
		sql += " order by xcwxsj desc";
		
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
