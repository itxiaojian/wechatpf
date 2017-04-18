package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.stat.model.DsTsfl;

@Repository
public class DsGctsTjDao extends HibernateBaseDaoImpl<DsTsfl, String> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 @Author oufeng
	 * @Date 2015年7月22日 下午3:09:56
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getData(String str) {
		String str1="";
			if(  str!=null && str!=""){
				str1="  and id="+str;
		}else{
			str1="";
		}
		String sql = "select id,zl,sum(sl) as sl  from zs_tsfl    where 1=1 "
				+str1
				+ " group by id,zl  "
				+ " order  by  id";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门名称
	 * */
	public List<Map<String, Object>> getTszl() {
		String sql ="select  id,zl  from zs_tsfl	group by id,zl  order by id";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 *获取教职工所借图书的数据
	 @Author oufeng
	 *@Date 2016年8月15日 
	 *@Version 1.0
	 */
	public List<Map<String, Object>> getTsData(String openId,String qh,String pages) {
		String str="";
		String str1="";
		if(qh!=null && qh!=""){
		   str+="  and xn="+"'"+qh+"'";
		}else{
			str="";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str1+=" order by sl desc limit "+num+",10 ";
		}
		String sql = 
				" SELECT xn,yxbh,yxmc,sl from tj_jzgzjts where 1=1 "
				+str
				+str1;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 *获取教职工所借图书的数据
	 @Author oufeng
	 *@Date 2016年8月15日 
	 *@Version 1.0
	 */
	public List<Map<String, Object>> getTsStuData(String openId,String qh,String pages) {
		String str="";
		String str1="";
		if(qh!=null && qh!=""){
		   str+=" and SUBSTR(e.jcsj,1,4)="+"'"+qh+"'";
		}else{
			str="";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str1+=" order by g.bmbh desc limit "+num+",10 ";
		}
		String sql = 
			   "SELECT g.bmbh as yxbh,g.bmmc as yxmc,SUBSTR(e.jcsj, 1,4)AS xn,COUNT(e.bh)AS sl "
		  +"  FROM zs_tsjyxx e, sys_yh a,sys_yhjs b ,sys_js c,"
		  +"  sys_zzjg d ,sys_zzjg f,sys_zzjg g"
		  +"  WHERE e.bh=a.yhbh "
		  +"  AND a.yhbh = b.yhbh  "
		  +"  AND b.jsbh= c.jsbh  "
		  +"  AND a.bmbh=d.bmbh "
		  +"  AND d.SJBH=f.BMBH "
		  +"  AND f.SJBH=g.BMBH "
		  +"  AND e.ghsj IS NULL" 
		  +str
		  +"  AND c.jsmc='ROLE_STUDENT' "
		  +"  GROUP BY g.bmmc  "
		  +str1;
		return jdbcTemplate.queryForList(sql);
	}
}
