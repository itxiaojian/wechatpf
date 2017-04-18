package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsBjkb;

@Repository
public class ZsBjkbDao extends HibernateBaseDaoImpl<ZsBjkb, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 获取班级课表信息
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	public List<Map<String,Object>> getXskb(String start,String end,String openId){
		List<Map<String,Object>> list=getBjxx(openId);
		String sql="select a.id,a.bjbh,DATE_FORMAT(a.skrq,'%Y.%m.%d') as skrq,a.xq,a.dyzs,DATE_FORMAT(a.sksj,'%m\\\\/%d\\\\/%Y %H:%i') as sksj,"
				+ "DATE_FORMAT(a.xksj,'%m\\\\/%d\\\\/%Y %H:%i') as xksj,a.kcbh,a.kcmc,a.skdd,a.sklsgh,a.dtskxh,a.bz,b.xm from zs_bjkb a left join "
				+ "sys_yh b on a.sklsgh=b.dlm where a.bjbh='"+list.get(0).get("bjbh")+"' and DATE_FORMAT(a.skrq,'%Y-%m-%d') between '"+start+"' and '"+end+"' order by a.skrq,a.sksj";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据当前登陆人获取登录人的班级信息
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	public List<Map<String,Object>> getBjxx(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and dlm='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select id,dlm,xm,bjbh,bjmc,bz from sys_xsbjb where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
