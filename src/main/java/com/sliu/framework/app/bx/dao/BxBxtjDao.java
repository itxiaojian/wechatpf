package com.sliu.framework.app.bx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;

/**
*设备统计
@Author oufeng	
@Date 2015年8月26日 下午2:46:03
@Version 1.0
*/
@Repository
public class BxBxtjDao extends HibernateBaseDaoImpl<BxBxsq, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 报修类型统计
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getDataLy(String ly,String stime,String etime) {
		String str="";
		String str1 ="";
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(yysj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(yysj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  ly!=null && ly!="" && !"".equals(ly)){
			str1+="  and aa.zdmc='"+ly+"'";
	}else{
		str1="";
	}
		String sql = "select aa.zdmc as ly,count(bb.ly) as bxsl from "
					+" (select  zdbm,zdmc from  sys_sjzd where zl='lylx' and jb=2)aa "
					+" left join (select ly from bx_bxsq where 1=1 "
					+str
					+ " )bb "
					+ " on aa.zdmc = bb.ly where 1=1"
					+str1
					+ " group by aa.zdmc "
					+ " order by aa.zdbm ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 报修类型统计
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getDataLx(String lx,String stime,String etime) {
		String str="";
		String str1="";
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(b.yysj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(b.yysj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  lx!=null && lx!="" && !"".equals(lx)){
			str1+="  and aa.id='"+lx+"'";
	}else{
		str1="";
	}
		String sql = "select aa.ztmc as lx ,bb.yysj,count(bb.sjzt) as bxsl "
				+ " from   (select id ,ztmc from bx_bxzt where ztjb=2) aa left  join "
				+" (select  a.sjzt,b.yysj  from bx_bxzt  a ,bx_bxsq b  "
				+" where a.id = b.sbztbh  "
				+str
				+ " and a.sjzt !='bxzl') bb " 
				+ " on  bb.sjzt = aa .id   where 1=1 "
				+str1
                + " group by aa.id  order by aa.id";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 报修按楼宇和状态统计
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getDataLyZt(String lxzt,String stime,String etime) {
		String str="";
		String str1 ="";
		if (stime != null && !"".equals(stime)) {
			str += " and  DATE_FORMAT(yysj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str += " and  DATE_FORMAT(yysj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  lxzt!=null && lxzt!="" && !"".equals(lxzt)){
			str1+="  and aa.zdmc='"+lxzt+"'";
	}else{
		str1="";
	}
		String sql = "select aa.zdmc as ly," 
+" count(case when bb.zt =1 then bb.zt end) as bxsl1,"
+" count(case when bb.zt =2 then bb.zt end) as bxsl2,"
+" count(case when bb.zt =3 then bb.zt end) as bxsl3,"
+" count(case when bb.zt =4 then bb.zt end) as bxsl4,"
+" count(case when bb.zt =5 then bb.zt end) as bxsl5,"
+" count(case when bb.zt =6 then bb.zt end) as bxsl6,"
+" count(case when bb.zt =7 then bb.zt end) as bxsl7,"
+" count(case when bb.zt =8 then bb.zt end) as bxsl8"
+"  from (select  zdbm,zdmc from  sys_sjzd where zl='lylx' and jb=2)aa "
 + " left join (select ly,zt from bx_bxsq where 1=1 "
 + str
 + " )bb on aa.zdmc = bb.ly  where 1=1"
 + str1
 + " group by aa.zdmc order by aa.zdbm";
		return jdbcTemplate.queryForList(sql);
}

	/**
	 * 报修楼宇统计的图标数据
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getLyTabDate(String str,String stime,String etime) {
		String str1="";
		String str2 ="";
		if (stime != null && !"".equals(stime)) {
			str2 += " and  DATE_FORMAT(yysj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str2 += " and  DATE_FORMAT(yysj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  str!=null && str!="" && !"".equals(str)){
			str1+="  and aa.zdmc='"+str+"'";
	}else{
		str1="";
	}
		String sql = " select aa.zdmc as zl,count(bb.ly) as sl from "
				+" (select  zdbm,zdmc from  sys_sjzd where zl='lylx' and jb=2)aa "
				+" left join (select ly from bx_bxsq where 1=1 "
				+ str2
				+ ")bb "
				+ " on aa.zdmc = bb.ly where 1=1 "
				+str1
				+ " group by aa.zdmc "
				+ " order by aa.zdbm ";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 报修类型统计的图标数据
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getLxTabDate(String str,String stime,String etime) {
		String str1="";
		String str2 ="";
		if (stime != null && !"".equals(stime)) {
			str2 += " and  DATE_FORMAT(b.yysj,'%Y-%m-%d') >='" + stime + "'";
		}
		if (etime != null && !"".equals(etime)) {
			str2 += " and  DATE_FORMAT(b.yysj,'%Y-%m-%d')  <= '" + etime + "'";
		}
		if(  str!=null && str!="" && !"".equals(str)){
			str1+="  and aa.id='"+str+"'";
	}else{
		str1="";
	}
		String sql = "select aa.ztmc as zl ,count(bb.sjzt) as sl "
				+ " from   (select id ,ztmc from bx_bxzt where ztjb=2) aa left  join "
				+" (select  a.sjzt  from bx_bxzt  a ,bx_bxsq b  "
				+" where a.id = b.sbztbh    and a.sjzt !='bxzl'"
				+ str2
				+ " ) bb " 
				+ " on  bb.sjzt = aa .id  where 1=1 "
				+ str1
				+ " group by aa.id"
                + "   order by aa.id";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 报修楼宇统计的数据字典
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getLyZd() {
		String sql = "select  zdbm,zdmc from  sys_sjzd where zl='lylx' and jb=2";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 报修类型统计的数据字典
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getLxZd() {
		String sql = "select id ,ztmc from bx_bxzt where ztjb=2 order by px";
		return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 报修按楼宇和状态的tab
	 @Author oufeng
	 * @Date 2015年8月26日 
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getLyZtTab(String lxzt,String stime,String etime) {
		          String str="";
					String str1="";
							if(  lxzt!=null && lxzt!="" && !"".equals(lxzt)){
									str1="  and aa.zdmc='"+lxzt+"'";
									}else{
											str1="";
								}
							if (stime != null && !"".equals(stime)) {
								str += " and  DATE_FORMAT(yysj,'%Y-%m-%d') >='" + stime + "'";
							}
							if (etime != null && !"".equals(etime)) {
								str += " and  DATE_FORMAT(yysj,'%Y-%m-%d')  <= '" + etime + "'";
							}
							String sql = "select aa.zdmc as ly," 
									+" count(case when bb.zt =1 then bb.zt end) as bxsl1,"
									+" count(case when bb.zt =2 then bb.zt end) as bxsl2,"
									+" count(case when bb.zt =3 then bb.zt end) as bxsl3,"
									+" count(case when bb.zt =4 then bb.zt end) as bxsl4,"
									+" count(case when bb.zt =5 then bb.zt end) as bxsl5,"
									+" count(case when bb.zt =6 then bb.zt end) as bxsl6,"
									+" count(case when bb.zt =7 then bb.zt end) as bxsl7,"
									+" count(case when bb.zt =8 then bb.zt end) as bxsl8"
									+"  from (select  zdbm,zdmc from  sys_sjzd where zl='lylx' and jb=2)aa "
									 + " left join (select ly,zt from bx_bxsq where 1=1 "
									 + str
									 + " )bb on aa.zdmc = bb.ly  where 1=1"
									 + str1
									 + " group by aa.zdmc order by aa.zdbm";
											return jdbcTemplate.queryForList(sql);
									}
}


