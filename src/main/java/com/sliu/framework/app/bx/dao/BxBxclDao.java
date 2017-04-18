package com.sliu.framework.app.bx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;


/**
*报修处理
@Author oufeng	
@Date 2015年8月14日 下午2:47:21
@Version 1.0

*/
@Repository
public class BxBxclDao extends HibernateBaseDaoImpl<BxBxsq, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	/**
	 * 获取查看数据
	 * @author oufeng
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getBxcl(String bm,String wxdl,String bxzt,String pages,String xm,List<Object> jsList) {
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
			str+=" and  1=2 ";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" order by  a.yysj desc limit "+num+",10 ";
		}
		String sql = 
				"select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.xq,a.wxdl,a.ly,a.lh,a. zt as ztbh,"
						+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
					     +"b.zdbm as zt,d.sprxm, d.spsj,c.wxrxm,c.pdsj, "
						+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
						+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
						+" left join (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl FROM    bx_bxwxjl  a "
						+ " WHERE NOT EXISTS "
			              +"  (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj)) c on a.id = c.bxid  "
						+ " left join (select bxid,max(spsj) as spsj,sprxm "
						+ " from bx_bxspyjb  group by bxid)d  on a.id = d.bxid  "
						+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2  and (a.zt=4 )"
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取总条数
	 * @author oufeng
	 * @date 2015年8月10日
	 * @return
	 */
	public int getBxclCount(String bm,String wxdl,String bxzt,String xm,List<Object> jsList){
		int rows = this.getBxcl(bm,wxdl,bxzt,"",xm,jsList).size();
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
	public List<Map<String,Object>> getSee(Integer idInt,String xm) {
		String str="";
		if(idInt!=null&&!"".equals(idInt)){
			str=str+" and  a.id ='"+idInt+"'"  ;
		}
	/*	if("admin".equals(xm)){
		      str+="";
			}else{
				str+=" and  c.wxrxm=" +"'"+xm+"'";
			}*/
		String sql = "select a.id,a.bxbh,a.xgh,a.lxhm,a.sqrxm,a.wxdl,a.xq,a.ly,a.lh,a. zt as ztbh,"
				+"case when fw =1 then '教材设备' when fw=2 then '维修' end as fw,"
			     +"b.zdbm as zt,d.sprxm, d.spsj,c.wxrxm,c.pdsj, "
				+ "DATE_FORMAT(a.yysj,'%Y-%m-%d') as yysj,a.sbztbh,"
				+ "a.sbztmc,a.dz,a.nr,a.pj,a.bz  from  bx_bxsq a  left join  sys_sjzd b  on a.zt =b.zdz  "
				+" left join (SELECT a.bxid,a.pdsj,a.wxrxm,a.wxys,a.fyed,a.hcyl FROM    bx_bxwxjl  a WHERE NOT EXISTS "
	            +"  (SELECT 1 FROM    bx_bxwxjl  WHERE bxid = a.bxid AND pdsj>a.pdsj) ) c on a.id = c.bxid  "
				+ " left join (select bxid,max(spsj) as spsj,sprxm "
				+ " from bx_bxspyjb  group by bxid)d on a.id = d.bxid  "
				+ " where 1=1 " 
				+ " and b.zl= 'ztlx'  and b.jb=2  and (a.zt=4 ) "
				+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 报修完成
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
			+"  bx_bxwxjl set  wxzt =5 "
			+ "  where 1=1 "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修完成
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
			+"  bx_bxsq set  zt =5  where 1=1  "
				+str;
		 jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修完成
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh 报修编号
	 * @return
	 */
	public void toSuccSpyj(Integer dm) {
		String str1="";
		if(dm!=null&&!"".equals(dm)){
			str1+=" and  bxid ="+dm+"" ;
		}
		String sql = "update  "
			+"  bx_bxspyjb set  zt =5  "
			+ " where 1=1  "
				+str1;
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
			+"  bx_bxwxjl set  wxzt =6  where 1=1 "
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
			+"  bx_bxsq set  zt =6  where 1=1  "
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
			+"  bx_bxspyjb set  zt =6,  "
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
	 * 报修主题
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getBxzt  (String wxdl) {
		String str = "";
		if (wxdl != null && !"".equals(wxdl)) {
			str += " and  a.ztmc ='" + wxdl + "'";
		}
		String sql = "select  a.id ,ztmc,b.sbztmc from bx_bxzt a,bx_bxsq b   "
				+ "where a.ztmc = b.wxdl  and  ztjb= 2 and  b.zt=4 "
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
	public List<Map<String, Object>> getWxdl() {
		String sql = "select  a.id ,ztmc from bx_bxzt a,bx_bxsq b   "
				+ " where a.ztmc = b.wxdl and  ztjb= 2 and   b.zt=4  group by a.ztmc ,a.id   order by px  ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学工号
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getXgh(String id ){
		String str = "";
		if (id != null && !"".equals(id)) {
			str += " and  id ='" + id + "'";
		}
		String sql = "select xgh from bx_bxsq where 1=1"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取openId
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getOpenId(String xgh){
		String str = "";
		if (xgh != null && !"".equals(xgh)) {
			str += " and  b.dlm ='" + xgh + "'";
		}
		String sql = "select a.wxh from sys_wxyh a ,sys_yh b  where a.yhid=b.yhbh "+str;
		return jdbcTemplate.queryForList(sql);
	}
}


