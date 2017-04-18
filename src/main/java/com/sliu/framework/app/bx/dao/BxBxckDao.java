package com.sliu.framework.app.bx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;

/**
 * 报修门户查看
 * @author oufeng
 * @date 2015年8月07日
 */
@Repository
public class BxBxckDao extends HibernateBaseDaoImpl<BxBxsq, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 分页查询信息数据
	 * @author oufeng
	 * @date 2015年8月07日
	 * @param start
	 * @param limit
	 * @param tz 状态
	 * @return
	 */
	public   Pagination<Map<String,Object>> getList(Integer start, Integer limit,String zt,String bm,String wxdl,String bxzt) {
		String str="";
		if(zt!=null&&!"".equals(zt)){
			str=str+" and a.zt ='"+zt+"'";
		}
		if(bm!=null&&!"".equals(bm)){
			str=str+" and( a.bxbh like '%"+bm+"%'"   + " or a.sqrxm like '%"+bm+"%')" ;
		}
		if(wxdl!=null&&!"".equals(wxdl)){
			str+=" and  a.wxdl ='"+wxdl+"'"  ;
		}
		if(bxzt!=null&&!"".equals(bxzt)){
			str+=" and  a.sbztmc ='"+bxzt+"'"  ;
		}
		/*String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.wxdl,a.sqrxm,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+" case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			    +" b.zdbm as zt,a.myd,"
				+" DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+" a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+" where 1=1 " 
				+" and b.zl= 'ztlx'  and b.jb=2 "
				+str;*/
		String sql=" select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt AS ztbh,"
				   +" CASE WHEN fw =1 THEN '教材设备' WHEN fw=2 THEN '维修' END AS fw,"
				   +" CASE WHEN a.myd =1 THEN '非常满意' WHEN a.myd=2 THEN '满意' when a.myd='3' then '不满意' END AS myd,"
			       +" b.zdbm AS zt,d.sprxm, DATE_FORMAT(d.spsj,'%Y-%m-%d') as spsj,c.wxrxm,"
			       + " DATE_FORMAT(c.pdsj,'%Y-%m-%d') as pdsj,"
				   +" DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				   +" a.sbztmc,a.dz,a.nr,a.pj,a.bz from  bx_bxsq a "
				   +" left join sys_sjzd b on a.zt =b.zdz " 
				   +" left join(select a.bxid,a.pdsj,a.wxrxm from bx_bxwxjl  a where not exists "
                   +" (select 1 from bx_bxwxjl  where bxid = a.bxid and pdsj>a.pdsj) ) c on a.id = c.bxid  "
				   +" left join (select bxid,max(spsj) AS spsj,sprxm,zt from bx_bxspyjb  "
	               +" group by bxid) d  on a.id = d.bxid  "
				   +" where 1=1 "
				   +" and b.zl= 'ztlx'  and b.jb=2 "
				   +str
				   +" order by a.yysj desc ";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 分页查询门户查看
	 * @author oufeng
	 * @date 2015年8月07日
	 * @param start
	 * @param limit
	 * @param tz 状态
	 * @return
	 */
	public  List<Map<String,Object>> getList(String zt,String nr,String time,String pages) {
		String str="";
		if(zt!=null&&!"".equals(zt)){
			str+=" and a.zt ='"+zt+"'";
		}
		if(nr!=null&&!"".equals(nr)){
			str+=" and a.nr like'%"+nr+"%'"   ;
		}
		if(time!=null&&!"".equals(time)){
			str+=" and  DATE_FORMAT(a.yysj,'%Y-%m-%d')='"+time+"'"  ;
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str+=" order by  a.yysj desc limit "+num+",10";
		}
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.wxdl,a.sqrxm,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,"
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2 "
				+str;
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获取查看数据
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getCheck(String bxbh) {
		String str="";
		if(bxbh!=null&&!"".equals(bxbh)){
			str=str+" and a.id ='"+bxbh+"'";
		}
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.wxdl,a.sqrxm,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,"
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2 "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取总条数
	 * @author oufeng
	 * @date 2015年8月10日
	 * @return
	 */
	public int getCount(String zt,String nr,String time){
		int rows = this.getList(zt,nr,time,"").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 获取状态
	 * @author 状态
	 * @date 2015年8月07日
	 * @return
	 */
	public List<Map<String,Object>> getZt(){
		String sql=
				"select  zdbm as ztmc,zdz as zt  from  sys_sjzd  where zl= 'ztlx'"
				+"  and jb=2 order by zdz";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 报修的维修大类
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
public List<Map<String, Object>> getWxdl(){
		String sql = "select  b.id ,b.ztmc from bx_bxzt b where ztjb = 2  ";
		return jdbcTemplate.queryForList(sql);
	}
	
/**
 * 报修主题
 * @author oufeng
 * @date 2015年8月11日
 * @param bxbh
 * @return
 */
public List<Map<String, Object>> getBxzt  (String id) {
	String str = "";
	if (str != null && !"".equals(id)) {
		str += " and  a.sjzt ='" + id + "'";
	}
	String sql = "select  a.id,a.ztmc from bx_bxzt a where  1=1 " +str;
	return jdbcTemplate.queryForList(sql);
}

	
	/**
	 * 获取手机门户查看数据
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getCkList(String id) {
		String str="";
		if(id!=null&&!"".equals(id)){
			str=str+" and a.id ='"+id+"'";
		}
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,d.sprxm, d.spsj,c.wxrxm,c.pdsj,c.fyed,c.hcyl,c.wxys,"
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+" left join (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl FROM    bx_bxwxjl  a WHERE NOT EXISTS "
              +"  (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
				+ " left join bx_bxspyjb d on a.id = d.bxid   and a.zt = d.zt "
				+ " where 1=1 " 
				
				+ " and b.zl= 'ztlx'  and b.jb=2 "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
//	/**
//	 * 报修主题
//	 * @author oufeng
//	 * @date 2015年8月11日
//	 * @param bxbh
//	 * @return
//	 */
//	public List<Map<String, Object>> getBxzt  (String wxdl) {
//		String str = "";
//		if (str != null && !"".equals(wxdl)) {
//			str += " and  a.ztmc ='" + wxdl + "'";
//		}
//		String sql = "select  a.id ,ztmc,b.sbztmc from bx_bxzt a,bx_bxsq b   "
//				+ "where a.ztmc = b.wxdl  and  ztjb= 2  "
//				+str 
//				+ " group by a.ztmc ,a.id,b.sbztmc   order by px   " ;
//		return jdbcTemplate.queryForList(sql);
//	}
//	
}
