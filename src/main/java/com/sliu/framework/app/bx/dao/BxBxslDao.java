package com.sliu.framework.app.bx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxwxjl;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;


/**
*报修受理
@Author oufeng	
@Date 2015年8月13日 上午11:27:19
@Version 1.0
*/
@Repository
public class BxBxslDao extends HibernateBaseDaoImpl<BxBxwxjl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 报修受理
	 * 
	 * @Author oufeng
	 * @Date 2015年8月13日 上午11:27:19
	 * @Version 1.0
	 */

	public Pagination<Map<String, Object>> getBxsl(Integer start,
			Integer limit, String bm, String wxdl, String bxzt, String xm,
			String dlm,String time) {
		String str = "";
		if (bm != null && !"".equals(bm)) {
			str = str + " and( a.bxbh like '%" + bm + "%'"
					+ " or a.sqrxm like '%" + bm + "%'  or a.lxhm like '%" + bm
					+ "%')";
		}
		if (wxdl != null && !"".equals(wxdl)) {
			str += " and  a.wxdl ='" + wxdl + "'";
		}
		if (bxzt != null && !"".equals(bxzt)) {
			str += " and  a.sbztmc ='" + bxzt + "'";
		}
		if ("admin".equals(xm) || "admin_bxxt".equals(dlm)) {
			str += "";
		} else {
			str += " and  c.wxrxm=" + "'" + xm + "'";
		}
		String sql ="select a.a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.xq,a.wxdl,a.ly,a.lh,a.ztbh,"
				+ "a.fw,a.zt,a.spsj,a.wxrxm,a.pdsj,a.yysj,a.sbztbh,a.myd,a.sbztmc,a.dz,a.nr,a.pj,a.bz,sprxm  "
				+ " from (select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.xq,a.wxdl,a.ly,a.lh,a.zt as ztbh,"
				+ " case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			    + " b.zdbm as zt,d.sprxm, DATE_FORMAT(d.spsj,'%Y-%m-%d') as spsj,c.wxrxm, "
			    + " DATE_FORMAT(c.pdsj,'%Y-%m-%d') as pdsj,"
				+ " DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,a.myd,"
				+ " a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b on a.zt =b.zdz  "
				+ " left join (SELECT a.bxid,DATE_FORMAT(a.pdsj,'%Y-%m-%d')as pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl"
				+ " FROM    bx_bxwxjl  a WHERE NOT EXISTS "
	            + " (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
				+ " left join  (select bxid,max(spsj) as spsj,sprxm,zt from bx_bxspyjb  "
				+ " group by bxid)d on a.id = d.bxid "
				+ " where 1=1 " 
				+str
		        + " and b.zl= 'ztlx'  and b.jb=2  and (a.zt=2 or a.zt=7 or a.zt=4)"
		        + " union all "
		        + " select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.xq,a.wxdl,a.ly,a.lh,a. zt as ztbh,"
				+ " case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			    + " b.zdbm as zt,d.sprxm, DATE_FORMAT(d.spsj,'%Y-%m-%d') as spsj,c.wxrxm, "
			    + " DATE_FORMAT(c.pdsj,'%Y-%m-%d') as pdsj,"
				+ " DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,a.myd,"
				+ " a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+ " left join (SELECT a.bxid,DATE_FORMAT(a.pdsj,'%Y-%m-%d')as pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl"
				+ " FROM    bx_bxwxjl  a WHERE NOT EXISTS "
	            + " (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
				+ " left join  (select bxid,max(spsj) as spsj,sprxm,zt from bx_bxspyjb  "
				+ " group by bxid)d on a.id = d.bxid "
				+ " where 1=1 " 
		        + " and b.zl= 'ztlx' and b.jb=2 and a.zt=5 "
				+ "  and DATE_FORMAT(d.spsj,'%Y-%m-%d') <='"+time+"'"
		        + str
		        +")a"
		        + " order by a.yysj desc ";
		return jdbcPager.queryPage(sql, start, limit);
	}

	
	/**
	 * 获取查看数据
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getBxsl(String bm,String wxdl,String bxzt,String pages,String xm,String dlm,List<Object> jsList) {
		String str="";
		if(bm!=null&&!"".equals(bm)){
			str=str+" and( a.bxbh like '%"+bm+"%'"   + " or a.sqrxm like '%"+bm+"%'  or a.lxhm like '%"+bm+"%')" ;
		}
		if(wxdl!=null&&!"".equals(wxdl)){
			str+=" and  a.wxdl ='"+wxdl+"'"  ;
		}
		if(bxzt!=null&&!"".equals(bxzt)){
			str+=" and  a.sbztmc ='"+bxzt+"'"  ;
		}
		if (jsList.size()!= 0  &&(jsList.contains("ROLE_BXSH") ||  jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_BX"))) {
		      str+="";
			}else{
				str+=" and 1=2";
			}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" order by  a.yysj desc limit "+num+",10 ";
		}
		String sql = 
				"select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.xq,a.wxdl,a.ly,a.lh,a. zt as ztbh,"
						+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
					     +"b.zdbm as zt,d.sprxm, d.spsj,c.wxrxm,c.pdsj, "
					     + " DATE_FORMAT(c.pdsj,'%Y-%m-%d') as pdsj,c.fyed,c.hcyl,c.wxys,"
						 +" DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,a.myd,"
						+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
						+" left join (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl FROM    bx_bxwxjl  a WHERE NOT EXISTS "
			            +"  (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
						+ " left join  (select bxid,max(spsj) as spsj,sprxm,zt from bx_bxspyjb  "
						+ " group by bxid)d on a.id = d.bxid "
						+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2  and (a.zt=2 or a.zt=7 or a.zt =5  or a.zt=4) "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取总条数
	 * @author oufeng
	 * @date 2015年8月10日
	 * @return
	 */
	public int getBxslCount(String bm,String wxdl,String bxzt,String xm,String dlm,List<Object> jsList){
		int rows = this.getBxsl(bm,wxdl,bxzt,"",xm,dlm,jsList).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 获取查看数据
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getSee(Integer idInt,String xm,String dlm) {
		String str="";
		if(idInt!=null&&!"".equals(idInt)){
			str=str+" and  a.id ='"+idInt+"'"  ;
		}
		/*if("admin".equals(xm)  ||  "admin_bxxt".equals(dlm)){
		      str+="";
			}else{
				str+=" and  c.wxrxm=" +"'"+xm+"'";
			}*/
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,d.sprxm, d.spsj,c.wxrxm,c.pdsj, "
			     + " DATE_FORMAT(c.pdsj,'%Y-%m-%d') as pdsj,c.fyed,c.hcyl,c.wxys,"
				 +" DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,a.myd,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+" left join (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl FROM    bx_bxwxjl  a WHERE NOT EXISTS "
	            +"  (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
				+ " left join (select bxid,max(spsj) as spsj,sprxm,zt from "
				+ " bx_bxspyjb  group by bxid)d on a.id = d.bxid "
				//+ "  and a.zt =d.zt  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2   and (a.zt=2 or a.zt=7 or a.zt =5 or a.zt=4)  "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 报修受理
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toDealSp(Integer dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str+="and  id ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxsq set zt =4  where 1=1  "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修受理
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toDealSpyj(Integer dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str+="and  bxid ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxspyjb set zt =4  where 1=1  "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修受理
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toDealWxjl(Integer dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str+="and  bxid ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxwxjl set  wxzt =4  where 1=1  "
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
	public void toRejecWxjl(Integer dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str+=" and  bxid ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxwxjl set  wxzt =3  where 1=1 "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修受理
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toRejecSp(Integer dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str+=" and  id ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxsq set  zt =3  where 1=1  "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修受理
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toRejecSpyj(Integer dm,String yhbh,String yhxm,Date data,String str) {
		String str1="";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 String data1 = sdf.format(data);
		if(dm!=null&&!"".equals(dm)){
			str1+=" and  bxid ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxspyjb set  zt =3,  "
			+ "  spyj = '"+str+"',"
			+" spsj='"+data1+"',"
			+" sprbh='"+yhbh+"',"
			+"sprxm='"+yhxm+"',"
			+" sftg=1 "
			+ " where 1=1  "
				+str1;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修人姓名
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String, Object>> getWxrxm(String wxrBh) {
		String str="";
		if(wxrBh!=null&&!"".equals(wxrBh)){
			str+=" and yhbh   ="+wxrBh+"" ;
		}
		String sql = "select xm from   "
			+"  sys_yh   where 1=1  "
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
//				+ "where a.ztmc = b.wxdl  and  ztjb= 2 and  (b.zt=2 or b.zt=7 or b.zt =5 or b.zt=4) "
//				+str 
//				+ " group by a.ztmc ,a.id,b.sbztmc   order by px   " ;
//		return jdbcTemplate.queryForList(sql);
//	}
//	
	/**
	 * 报修主题
	 * 
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
	 * 报修的维修大类
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getWxdl() {
		String sql = "select  a.id ,ztmc from bx_bxzt a,bx_bxsq b   "
				+ " where a.ztmc = b.wxdl and  ztjb= 2 and  "
				+ " (b.zt=2 or b.zt=7 or b.zt =5 or b.zt=4)  "
				+ " group by a.ztmc ,a.id order by px ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 报修受理驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public void updateBxsq(Long Id) {
		String str = "";
		if (Id != null && !"".equals(Id)) {
			str += " and  id =" + Id + "";
		}
		String sql = "update  " + "  bx_bxsq set  zt =1  where 1=1  " + str;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获得派单打印的预览信息
	 * @author oufeng
	 * @date 2016年3月17日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getPdList(String id) {
		String str = "";
		if (id != null && !"".equals(id)) {
			str += " and  a.id ='" + id + "'";
		}
		String sql = "SELECT a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,"
                     +" a.xq,a.ly,a.lh,a. zt AS ztbh,a.myd,"
                     + " b.zdbm AS zt,DATE_FORMAT(a.yysj,'%Y-%m-%d') AS yysj,a.sbztbh,"
                     +" a.sbztmc,a.dz,a.nr,a.pj,a.bz,c.bxid,c.wxrbh,c.wxzt,c.pdsj,c.wxsj,c.wxrxm "
                     +" FROM  bx_bxsq a  LEFT JOIN  sys_sjzd b ON a.zt =b.zdz  "
                     +" LEFT JOIN  (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxrbh,a.wxzt,a.wxsj  "
                     + " FROM    bx_bxwxjl  a WHERE NOT EXISTS "
	                 +" (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj)) c ON a.id = c.bxid "
                     +" WHERE  b.zl= 'ztlx'  AND b.jb=2  " +str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 报修完成(用户长时间没有反馈)
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toSuccWxjl(Integer dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str+=" and  bxid ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxwxjl set  wxzt =9  "
			+ "  where 1=1 "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修完成(用户长时间没有反馈)
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toSuccSp(Integer dm) {
		String str="";
		if(dm!=null&&!"".equals(dm)){
			str+=" and  id ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxsq set  zt =9  where 1=1  "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修人姓名
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String, Object>> getDlm(String yhbh) {
		String str="";
		if(yhbh!=null&&!"".equals(yhbh)){
			str+=" and yhbh   ='"+yhbh+"'" ;
		}
		String sql = "select dlm from   "
			+"  sys_yh   where 1=1  "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 报修主题
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getSenderByBh  (String sendBh) {
		String str = "";
		if (sendBh != null && !"".equals(sendBh)) {
			str += " and  yhbh ='" + sendBh + "'";
		}else{str+="1=2";}
		String sql = " select xm,yhbh from sys_yh where 1=1 "+str ;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取查看数据
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getDetail(String id) {
		String str="";
		if(id!=null&&!"".equals(id)){
			str=str+" and  a.id ='"+id+"'";
		}else{
			str+=" and 1=2";
		}
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+ " a.fw as fwbh,e.zdmc as fw,"
			    + " b.zdbm as zt,d.sprxm, d.spsj,c.wxrxm,c.pdsj, "
				+ " DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ " a.sbztmc,a.dz,a.nr,a.pj,a.bz,a.myd  from  bx_bxsq a  "
				+ " left join  sys_sjzd b  on a.zt =b.zdz  "
				+ " left join  sys_sjzd e  on a.fw =e.zdz  "
				+ " left join (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl FROM    bx_bxwxjl  a WHERE NOT EXISTS "
	            + "  (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
				+ " left join (select bxid,max(spsj) as spsj,sprxm,zt from "
				+ " bx_bxspyjb  group by bxid)d on a.id = d.bxid "
				//+ "  and a.zt =d.zt  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2"
				+ " and e.zl= 'bxfwzl'  and e.jb=2 "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 耗材列表的下拉框数据
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getHcList() {
		String sql = "select hcbh,hcmc from bx_bxhc group by hcbh,hcmc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 维修状态
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getWxzt(String id) {
		String sql = "select  bxzt  from  bx_bxwxrw where bxid='"+id+"'";
		return jdbcTemplate.queryForList(sql);
	}
}
