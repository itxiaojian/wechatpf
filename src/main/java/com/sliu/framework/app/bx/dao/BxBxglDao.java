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
 * 报修管理
 * @author oufeng
 * @date 2015年8月07日
 */
@Repository
public class BxBxglDao extends HibernateBaseDaoImpl<BxBxsq, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 报修待审核
	 * @author chenhui
	 * @date 2016年9月3日
	 * @param bxbh 报修编号
	 * @return
	 */
	public  Pagination<Map<String,Object>> getBxsh(Integer start, Integer limit,String bm,String wxdl,String bxzt) {
		String str="";
		if(bm!=null&&!"".equals(bm)){
			str+=" and( a.bxbh like '%"+bm+"%'"   + " or a.sqrxm like '%"+bm+"%'  or a.lxhm like '%"+bm+"%')" ;
		}
		if(wxdl!=null&&!"".equals(wxdl)){
			str+=" and  a.wxdl ='"+wxdl+"'"  ;
		}
		if(bxzt!=null&&!"".equals(bxzt)){
			str+=" and  a.sbztmc ='"+bxzt+"'"  ;
		}
		String sql =  "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,"
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2  and a.zt=1 "
				+str
				+" order by a.yysj desc ";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	
	/**
	 * 分页查询信息数据
	 * @author chenhui
	 * @date 2015年8月07日
	 * @param start
	 * @param limit
	 * @param tz 状态
	 * @return
	 */
	public Pagination<Map<String,Object>> pager(Integer start, Integer limit,String zt,String code,String starttime,String endtime) {
		String str="";
		if(zt!=null&&!"".equals(zt)){
			str=str+" and a.zt ='"+zt+"'";
		}
		if(code!=null&&!"".equals(code)){
			str+=" and concat(ifnull(a.lxhm,''),ifnull(a.sqrxm,''),ifnull(a.xgh,'')) like '%"+code+"%'";
		}
		if(starttime!=null&&!"".equals(starttime)){
			str+=" and DATE_FORMAT(a.yysj,'%Y-%m-%d')>='"+starttime+"'";
		}
		if(endtime!=null&&!"".equals(endtime)){
			str+=" and DATE_FORMAT(a.yysj,'%Y-%m-%d')<='"+endtime+"'";
		}
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,"
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2 "
				+str
		        +" order by yysj desc ";
		return jdbcPager.queryPage(sql, start, limit);
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
	 * 分页查询信息数据
	 * @author oufeng
	 * @date 2015年8月07日
	 * @param start
	 * @param limit
	 * @param tz 状态
	 * @return
	 */
	public  List<Map<String,Object>> getList(String zt,String bm,String wxdl,String bxzt,String pages) {
		String str="";
		if(zt!=null&&!"".equals(zt)){
			str=str+" and a.zt ='"+zt+"'";
		}
		if(bm!=null&&!"".equals(bm)){
			str=str+" and( a.bxbh like'%"+bm+"%'"   + " or a.sqrxm like'%"+bm+"%'  or a.lxhm like'%"+bm+"%')" ;
		}
		if(wxdl!=null&&!"".equals(wxdl)){
			str+=" and  a.wxdl ='"+wxdl+"'"  ;
		}
		if(bxzt!=null&&!"".equals(bxzt)){
			str+=" and  a.sbztmc ='"+bxzt+"'"  ;
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" order by  a.yysj desc limit "+num+",10";
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
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,d.sprxm, d.spsj,c.wxrxm,c.pdsj,c.fyed,c.hcyl,c.wxys,"
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+" left join (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl FROM    bx_bxwxjl  a WHERE NOT EXISTS "
                +" (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
				+" left join (SELECT bxid,MAX(spsj) AS spsj,sprxm,zt FROM bx_bxspyjb  "
	            +" GROUP BY bxid) d  on a.id = d.bxid  "
				+" where 1=1 " 
				+" and b.zl= 'ztlx'  and b.jb=2 "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取总条数
	 * @author oufeng
	 * @date 2015年8月10日
	 * @return
	 */
	public int getCount(String zt,String bm,String wxdl,String bxzt){
		int rows = this.getList(zt,bm,wxdl,bxzt,"").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 获取报修审核总条数
	 * @author oufeng
	 * @date 2015年8月10日
	 * @return
	 */
	public int getBxshCount(String bm,String bxzt,String wxdl){
		int rows = this.getBxsh(bm,wxdl,bxzt,"").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	/**
	 * 报修待审核
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getBxsh(String bm,String wxdl,String bxzt,String pages) {
		String str="";
		if(bm!=null&&!"".equals(bm)){
			str+=" and( a.bxbh like '%"+bm+"%'"   + " or a.sqrxm like '%"+bm+"%'  or a.lxhm like '%"+bm+"%')" ;
		}
		if(wxdl!=null&&!"".equals(wxdl)){
			str+=" and  a.wxdl ='"+wxdl+"'"  ;
		}
		if(bxzt!=null&&!"".equals(bxzt)){
			str+=" and  a.sbztmc ='"+bxzt+"'"  ;
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" order by  a.yysj desc limit "+num+",10 ";
		}
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,"
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2  and a.zt=1 "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 报修审核
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void setsh(String dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str=str+"and  id ='"+dm+"'" ;
		}
		String sql = "update  "
			+"  bx_bxsq set zt =2  where 1=1 "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void setbh(String dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str=str+"and  id ='"+dm+"'" ;
		}
		String sql = "update  "
			+"  bx_bxsq set zt =3  where 1=1 "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修主题
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getBxzt  (String wxdl) {
		String str = "";
		if (str != null && !"".equals(wxdl)) {
			str += " and  a.ztmc ='" + wxdl + "'";
		}
		String sql = "select  a.id ,ztmc,b.sbztmc from bx_bxzt a,bx_bxsq b   "
				+ "where a.ztmc = b.wxdl  and  ztjb= 2  "
				+str 
				+ " group by a.ztmc ,a.id,b.sbztmc   order by px   " ;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 报修的维修大类
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getBxbz(String id) {
		String sql = "select bz from bx_bxsq where id = '"+id+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 报修的维修大类
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getWxdl() {
		String sql = "select  a.id ,ztmc from bx_bxzt a,bx_bxsq b   "
				+ " where a.ztmc = b.wxdl and  ztjb= 2   group by a.ztmc ,a.id   order by px  ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 批量的结束
	 * @author oufeng
	 * @date 2016年9月9日
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public void pljs(String starttime,String endtime) {
		String str="";
		if(starttime!=null&&!"".equals(starttime)){
			str+=" and DATE_FORMAT(yysj,'%Y-%m-%d')>='"+starttime+"'";
		}else{str+=" and 1=2";}
		if(endtime!=null&&!"".equals(endtime)){
			str+=" and DATE_FORMAT(yysj,'%Y-%m-%d')<='"+endtime+"'";
		}else{str+=" and 1=2";}
		String sql = "update bx_bxsq set zt=8  where 1=1 and  zt in('1','2','3','4','5','6','7') "+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修管理删除报修意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public  void deleteBxspyjb(String id) {
		String str ="";
		String sql ="";
		if (id != null && !"".equals(id)) {
			str += " and  bxid ='" + id + "'";
		    sql = " delete from bx_bxspyjb where 1=1 "+str;
		}else{sql = " select count(1) from bx_bxspyjb";}
	 jdbcTemplate.execute(sql);
	}
	
	
	/**
	 * 报修管理删除报修维修表
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public  void deleteBxwxjl(String id) {
		String str ="";
		String sql="";
		if (id != null && !"".equals(id)) {
			str += " and  bxid ='" + id + "'";
		   sql = " delete from bx_bxwxjl where 1=1 "+str;
		}else{ sql = " select count(1) from bx_bxwxjl ";}
	 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修管理删除报修维修表
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public  void deleteBxPhoto(String bz) {
		String sql="";
		String str="";
		if(bz.split(";").length==1 && !"".equals(bz)){
			str=bz.substring(bz.indexOf("=")+1);
			sql="delete from sys_fjgl where id ='"+str+"'";
		}else if(bz.split(";").length>1){
			for(int i=0;i<bz.split(";").length;i++){
				str=bz.split(";")[i].substring(bz.split(";")[i].indexOf("=")+1);
				sql="delete from sys_fjgl where id ='"+str+"'";
			}
		}else{
			sql="select count(1) from sys_fjgl";
		}
	 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获得角色
	 * @author oufeng
	 * @date 2015年9月2日
	 * @return
	 */
	public List<Map<String,Object>> getJs(String yhbh){
		String str="";
		if(yhbh!=null&&!"".equals(yhbh)){
			str=str+" and a.yhbh='"+yhbh+"'";
		}
		String sql="select c.jsmc  from sys_yh a ,sys_yhjs b ,sys_js c where a.yhbh = b.yhbh and b.jsbh = c.jsbh"+str;
		return jdbcTemplate.queryForList(sql);
	}
}
