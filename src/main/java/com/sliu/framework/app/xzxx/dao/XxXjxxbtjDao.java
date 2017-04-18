package com.sliu.framework.app.xzxx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.xzxx.model.XxXjxxb;

/**
 * 校长邮箱的统计
 * @author  oufeng
 * @since 2015-09-01
 */
@Repository
public class XxXjxxbtjDao extends HibernateBaseDaoImpl<XxXjxxb,Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 校长邮箱的图标数据
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getDataXxXjxxtj(String bmbh,String stime,String etime) {
		String str="";
		String str1="";
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  bmbh!=null && bmbh!="" &&!"".equals(bmbh)){
			str1="  and b.slbmbh='"+bmbh+"'";
	}else{
		str1="";
	}
		String sql =
				"select   a.bmmc,"
			+"	count(case when b.clzt   =1 then b.clzt end) as bxsl1,"
			+"	count(case when b.clzt   =2 then b.clzt end) as bxsl2,"
			+"	count(case when b.clzt   =3 then b.clzt end) as bxsl3,"
			+"	count(case when b.clzt   =4 then b.clzt end) as bxsl4,"
			+"	count(case when b.clzt   =5 then b.clzt end) as bxsl5,"
			+"	count(case when b.clzt   =6 then b.clzt end) as bxsl6,"
			+"	count(case when b.clzt   =7 then b.clzt end) as bxsl7"
			+"   from sys_zzjg a left join  (select xxsj,clzt,slbmbh from  xx_xjxxb    where 1=1 "
			+str
			+ " ) b "
			+ "  on a.bmbh = b.slbmbh where a.jb=2  "
			+str1
			+ "  group by a.bmmc   "
			+ "   order by a.bmmc ";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 校长邮箱的tab数据
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getDataTab(String bmbh,String stime,String etime) {
		String str="";
		String str1="";
		if(  bmbh!=null && bmbh!="" && !"".equals(bmbh)){
				str1="  and a.bmbh='"+bmbh+"'";
				}else{
						str1="";
			}
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		String sql =
				"select   a.bmmc,"
			+"	count(case when b.clzt   =1 then b.clzt end) as bxsl1,"
			+"	count(case when b.clzt   =2 then b.clzt end) as bxsl2,"
			+"	count(case when b.clzt   =3 then b.clzt end) as bxsl3,"
			+"	count(case when b.clzt   =4 then b.clzt end) as bxsl4,"
			+"	count(case when b.clzt   =5 then b.clzt end) as bxsl5,"
			+"	count(case when b.clzt   =6 then b.clzt end) as bxsl6,"
			+"	count(case when b.clzt   =7 then b.clzt end) as bxsl7"
			+"   from sys_zzjg a left join  (select xxsj,clzt,slbmbh from  xx_xjxxb    where 1=1 "
			+str
			+ " ) b "
			+ "  on a.bmbh = b.slbmbh where a.jb=2  "
			+str1
			+ "  group by a.bmmc   "
			+ "   order by a.bmmc ";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 校长邮箱的满意度的tab数据
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getXzyxmydt(String bmbh,String stime,String etime) {
		String str="";
		String str1="";
		if(  bmbh!=null && bmbh!="" && !"".equals(bmbh)){
				str1="  and a.bmbh='"+bmbh+"'";
				}else{
						str1="";
			}
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		String sql =
				"select   a.bmmc,"
			+"	count(case when b.myd   =1 then b.myd end) as myd1,"
			+"	count(case when b.myd   =2 then b.myd end) as myd2,"
			+"	count(case when b.myd   =3 then b.myd end) as myd3 "
			+"   from sys_zzjg a left join (select xxsj,myd,slbmbh from  xx_xjxxb    where 1=1 "
			+str
			+ "  )b "
			+ "  on a.bmbh = b.slbmbh where a.jb=2  "
			+str1
			+ "  group by a.bmmc   "
			+ "   order by a.bmmc ";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 校长邮箱的满意度的图表数据
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getXzyxmydb(String bmbh,String stime,String etime) {
		String str="";
		String str1="";
		if(  bmbh!=null && bmbh!="" && !"".equals(bmbh)){
				str1="  and a.bmbh='"+bmbh+"'";
				}else{
						str1="";
			}
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(xxsj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		String sql =
				"select   a.bmmc,"
			+"	count(case when b.myd   =1 then b.myd end) as myd1,"
			+"	count(case when b.myd   =2 then b.myd end) as myd2,"
			+"	count(case when b.myd   =3 then b.myd end) as myd3 "
			+"   from sys_zzjg a left join (select xxsj,myd,slbmbh from  xx_xjxxb    where 1=1 "
			+str
			+ "  )b "
			+ "  on a.bmbh = b.slbmbh where a.jb=2  "
			+str1
			+ "  group by a.bmmc   "
			+ "   order by a.bmmc ";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 获得部门名称
	 * */
	public List<Map<String, Object>> getBmmc() {
		String sql ="select b.bmbh, b.bmmc  from xx_xjxxb a  right  join sys_zzjg b  "
				+" on  a.slbmbh = b.bmbh where b.jb=2"
				+ " group by b.bmbh  "
				+ " order  by  b.bmbh";
		return jdbcTemplate.queryForList(sql);
	}
}

