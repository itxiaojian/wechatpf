package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysYh;

@Repository
public class DsSzllTjDao extends HibernateBaseDaoImpl<SysYh, String> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 
	 * 
	 @Author oufeng
	 * @Date 2015年7月22日 下午3:09:56
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getData(String str) {
		String str1="";
			if(  str!=null && str!=""){
				str1="  and d.bmbh="+"'"+str+"'";
		}else{
			str1="";
		}
		String sql = "select d.bmmc,count(1) as num  from sys_yh a,sys_yhjs b ,sys_js c,sys_zzjg d "
				+ " where  1=1 "
				+str1
				+ "   and a.yhbh = b.yhbh"
				+ "  and b.jsbh = c.jsbh"
				+ "  and a.bmbh= d.bmbh"
				+ "  and c.jsmc='ROLE_TEACHER'"
				+ "  group by d.bmmc ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门名称
	 * */
	public List<Map<String, Object>> getBmmc() {
		String sql = "select d.bmmc,d. bmbh   from sys_yh a,sys_yhjs b ,sys_js c,sys_zzjg d "
				+ " where  1=1 "
				+ "and a.yhbh = b.yhbh"
				+ " and b.jsbh = c.jsbh"
				+ " and a.bmbh= d.bmbh"
				+ " and c.jsmc='ROLE_TEACHER'"
				+ " group by d.bmmc ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门名称
	 * @Author wangxiangyang	
	 * @Date 2016年8月17日
	 * */
	public List<Map<String, Object>> getData1(String pages) {
		String str="";
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" DESC limit "+num+",10";
			}
		}
		String sql = "select d.bmmc,count(1) as num  from sys_yh a,sys_yhjs b ,sys_js c,sys_zzjg d "
				+ " where  1=1 "
				+ "   and a.yhbh = b.yhbh"
				+ "  and b.jsbh = c.jsbh"
				+ "  and a.bmbh= d.bmbh"
				+ "  and c.jsmc='ROLE_TEACHER'"
				+ "  group by d.bmmc "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门名称
	 * @Author wangxiangyang	
	 * @Date 2016年8月17日
	 * */
	public List<Map<String, Object>> getData2(String bmmc) {
		String sql = "select d.bmmc,count(1) as num  from sys_yh a,sys_yhjs b ,sys_js c,sys_zzjg d "
				+ " where  1=1 "
				+ "  and d.bmmc like '%"+bmmc+"%'"
				+ "   and a.yhbh = b.yhbh"
				+ "  and b.jsbh = c.jsbh"
				+ "  and a.bmbh= d.bmbh"
				+ "  and c.jsmc='ROLE_TEACHER'"
				+ "  group by d.bmmc ";
		return jdbcTemplate.queryForList(sql);
	}
}
