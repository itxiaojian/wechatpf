package com.sliu.framework.app.sbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbsgxx;

/**
 * 设备申购
 * @author oufeng
 * @date 2015年8月20日
 */
@Repository
public class SbSbsgDao  extends HibernateBaseDaoImpl<SbSbsgxx, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 后台:查询设备申购
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param gjz  关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgList(Integer start, Integer limit,	String yhbh,
		String code,String sblb,String sqzt){
		String str = "";
		if(code!=null&&!"".equals(code)){
			str+=" and concat(ifnull(a.sbxh,''),ifnull(a.sbmc,'')) like '%"+code+"%'";
		}
		if(sqzt!=null&&!"".equals(sqzt)){
			str+=" and a.sgzt ='"+sqzt+"'";
		}
		if(sblb!=null&&!"".equals(sblb)){
			str+=" and a.sblb ='"+sblb+"'";
		}
		if (yhbh != null && !"".equals(yhbh) && !"1".equals(yhbh)) {
			str += " and  b.yhbh ='" + yhbh + "'";
		}else if( "1".equals(yhbh)){
			str +="";
		}
		String sql = " select a.id,a.sblb,a.sbmc,a.sbbh,a.sbxh,a.sbjb,a.sybm,d.bmmc,a.syfw,a.sccj,a.ccbh,"
				+ "DATE_FORMAT(a.ccrq,'%Y-%m-%d') as ccrq, a.sgr, b.xm,"
				+ "DATE_FORMAT(a.sgrq,'%Y-%m-%d') as sgrq,c.zdbm as sgzt,cgsl "
				+ "  from sb_sbsgxx  a, sys_yh b, sys_sjzd c,sys_zzjg d "
				+ " where  a.sgr = b.yhbh and  a.sgzt =c.zdz and a.sybm = d.bmbh    "
				+ " and c.zl= 'sgztlx'  and c.jb=2"
				+str;
		sql += " order by sgrq desc";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	
	/**
	 * 后台:查询设备部门申购
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param gjz  关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgBmspList(Integer start, Integer limit,	
			String yhbh,String code,String sblb,String sqzt){
		String str ="";
		String sql ="";
		if(code!=null&&!"".equals(code)){
			str+=" and concat(ifnull(a.sbxh,''),ifnull(a.sbmc,'')) like '%"+code+"%'";
		}
		if(sqzt!=null&&!"".equals(sqzt)){
			str+=" and a.sgzt ='"+sqzt+"'";
		}
		if(sblb!=null&&!"".equals(sblb)){
			str+=" and a.sblb ='"+sblb+"'";
		}
		if (yhbh != null && !"".equals(yhbh) && !"1".equals(yhbh)) {
			str += " and  b.yhbh ='" + yhbh + "'";
		}else if( "1".equals(yhbh)){
			str +="";
		}
		if (yhbh != null && !"".equals(yhbh) &&  !"1".equals(yhbh) ) {
			 str += " and  b.yhbh ='" + yhbh + "'  and c.jsmc='ROLE_SG_BM'";
			 sql = " select a.id,a.sblb,a.sbmc,a.sbbh,a.sbxh,a.sbjb,a.sybm,e.bmmc,a.syfw,a.sccj,a.ccbh,"
						+ "DATE_FORMAT(a.ccrq,'%Y-%m-%d') as ccrq,a.sgr,f.xm,"
						+ "DATE_FORMAT(a.sgrq,'%Y-%m-%d') as sgrq,d.zdbm as sgzt,a.cgsl,g.sprxm,g.spyj  "
						+ "  from sb_sbsgxx a left join sys_yh f on  a.sgr = f.yhbh "
						+" left join sys_zzjg e on a.sybm = e.bmbh "
						+" left join sys_sjzd d on a.sgzt =d.zdz "
						+" left join sb_sbsgyj g on a.id = g.sgid  and a.sgzt = g.spzt"
						+ " sys_yhjs b left join  sys_js c  on b.jsbh = c.jsbh "
						+ " where   1=1 "
						+ "  and d.zl= 'sgztlx'  and d.jb=2"
						+ "  and (a.sgzt=1 or a.sgzt=5)"+str;
				sql += " order by sgrq desc";
		}else if("1".equals(yhbh)){
			str+="";
			 sql = " select a.id,a.sblb,a.sbmc,a.sbbh,a.sbxh,a.sbjb,a.sybm,e.bmmc,a.syfw,a.sccj,a.ccbh,"
						+ "DATE_FORMAT(a.ccrq,'%Y-%m-%d') as ccrq,a.sgr,f.xm,"
						+ "DATE_FORMAT(a.sgrq,'%Y-%m-%d') as sgrq,d.zdbm as sgzt,a.cgsl,g.sprxm,g.spyj  "
						+ "  from sb_sbsgxx a left join sys_yh f on a.sgr = f.yhbh "
						+ " left join sys_zzjg e on a.sybm = e.bmbh "
						+ " left join sys_sjzd d on a.sgzt =d.zdz  "
						+ " left join sb_sbsgyj  g on a.id = g.sgid and  a.sgzt = g.spzt  "
						+ " where   1=1 "
						+ "  and d.zl= 'sgztlx'  and d.jb=2"
						+ " and (a.sgzt=1 or a.sgzt=5)"+str;
				sql += " order by sgrq desc";
		}
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 后台:查询设备领导申购
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param gjz  关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgLdspList(Integer start, Integer limit,	
			String yhbh,String code,String sblb,String sqzt){
		String str ="";
		String sql ="";
		if(code!=null&&!"".equals(code)){
			str+=" and concat(ifnull(a.sbxh,''),ifnull(a.sbmc,'')) like '%"+code+"%'";
		}
		if(sqzt!=null&&!"".equals(sqzt)){
			str+=" and a.sgzt ='"+sqzt+"'";
		}
		if(sblb!=null&&!"".equals(sblb)){
			str+=" and a.sblb ='"+sblb+"'";
		}
		if (yhbh != null && !"".equals(yhbh) && !"1".equals(yhbh)) {
			str += " and  b.yhbh ='" + yhbh + "'";
		}else if( "1".equals(yhbh)){
			str +="";
		}
		if (yhbh != null && !"".equals(yhbh) &&  !"1".equals(yhbh) ) {
			 str += " and  b.yhbh ='" + yhbh + "'  and c.jsmc='ROLE_SG_BM'";
			 sql = " select a.id,a.sblb,a.sbmc,a.sbbh,a.sbxh,a.sbjb,a.sybm,e.bmmc,a.syfw,a.sccj,a.ccbh,"
						+ "DATE_FORMAT(a.ccrq,'%Y-%m-%d') as ccrq,a.sgr,f.xm,"
						+ "DATE_FORMAT(a.sgrq,'%Y-%m-%d') as sgrq,d.zdbm as sgzt,a.cgsl,g.sprxm,g.spyj  "
						+ "  from sb_sbsgxx a, sys_yh f,sys_yhjs b ,sys_js c,sys_sjzd d,sys_zzjg e,sb_sbsgyj g  "
						+ " where   a.sgr = f.yhbh "
						+" and  a.sybm = e.bmbh"
						+ " and b.jsbh = c.jsbh"
						+" and a.sgzt =d.zdz "
						+ " and a.id = g.sgid  "
						+ " and a.sgzt = g.spzt  "
						+ "  and d.zl= 'sgztlx'  and d.jb=2"
						+ " and (sgzt=4 or sgzt=7)"+str;
				sql += " order by sgrq desc";
		}else if("1".equals(yhbh)){
			str+="";
			 sql = " select a.id,a.sblb,a.sbmc,a.sbbh,a.sbxh,a.sbjb,a.sybm,e.bmmc,a.syfw,a.sccj,a.ccbh,"
						+ "DATE_FORMAT(a.ccrq,'%Y-%m-%d') as ccrq,a.sgr,f.xm,"
						+ "DATE_FORMAT(a.sgrq,'%Y-%m-%d') as sgrq,d.zdbm as sgzt,a.cgsl,g.sprxm,g.spyj  "
						+ "  from sb_sbsgxx a, sys_yh f,sys_sjzd d,sys_zzjg e,sb_sbsgyj g  "
						+ " where   a.sgr = f.yhbh "
						+" and  a.sybm = e.bmbh"
						+" and a.sgzt =d.zdz "
						+ " and a.id = g.sgid  "
						+ " and a.sgzt = g.spzt  "
						+ "  and d.zl= 'sgztlx'  and d.jb=2"
						+ " and (a.sgzt=4 or a.sgzt=7)"+str;
				sql += " order by sgrq desc";
		}
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 后台:查询设备财务申购
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param gjz  关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgCwspList(Integer start, Integer limit,	
			String yhbh,String code,String sblb){
		String str ="";
		String sql ="";
		if(code!=null&&!"".equals(code)){
			str+=" and concat(ifnull(a.sbxh,''),ifnull(a.sbmc,'')) like '%"+code+"%'";
		}
		if(sblb!=null&&!"".equals(sblb)){
			str+=" and a.sblb ='"+sblb+"'";
		}
		if (yhbh != null && !"".equals(yhbh) && !"1".equals(yhbh)) {
			str += " and  b.yhbh ='" + yhbh + "'";
		}else if( "1".equals(yhbh)){
			str +="";
		}
		if (yhbh != null && !"".equals(yhbh) &&  !"1".equals(yhbh) ) {
			 str += " and  b.yhbh ='" + yhbh + "'  and c.jsmc='ROLE_SG_BM'";
			 sql = " select a.id,a.sblb,a.sbmc,a.sbbh,a.sbxh,a.sbjb,a.sybm,e.bmmc,a.syfw,a.sccj,a.ccbh,"
						+ "DATE_FORMAT(a.ccrq,'%Y-%m-%d') as ccrq,a.sgr,f.xm,"
						+ "DATE_FORMAT(a.sgrq,'%Y-%m-%d') as sgrq,d.zdbm as sgzt,a.cgsl,g.sprxm,g.spyj  "
						+ "  from sb_sbsgxx a, sys_yh f,sys_yhjs b ,sys_js c,sys_sjzd d,sys_zzjg e,sb_sbsgyj g  "
						+ " where   a.sgr = f.yhbh "
						+" and  a.sybm = e.bmbh"
						+ " and b.jsbh = c.jsbh"
						+" and a.sgzt =d.zdz "
						+ " and a.id = g.sgid  "
						+ " and a.sgzt = g.spzt  "
						+ "  and d.zl= 'sgztlx'  and d.jb=2"
						+ " and sgzt=6 "+str;
				sql += " order by sgrq desc";
		}else if("1".equals(yhbh)){
			str+="";
			 sql = " select a.id,a.sblb,a.sbmc,a.sbbh,a.sbxh,a.sbjb,a.sybm,e.bmmc,a.syfw,a.sccj,a.ccbh,"
						+ "DATE_FORMAT(a.ccrq,'%Y-%m-%d') as ccrq,a.sgr,f.xm,"
						+ "DATE_FORMAT(a.sgrq,'%Y-%m-%d') as sgrq,d.zdbm as sgzt,a.cgsl,g.sprxm,g.spyj  "
						+ "  from sb_sbsgxx a, sys_yh f,sys_sjzd d,sys_zzjg e,sb_sbsgyj g  "
						+ " where   a.sgr = f.yhbh "
						+" and  a.sybm = e.bmbh"
						+" and a.sgzt =d.zdz "
						+ " and a.id = g.sgid  "
						+ " and a.sgzt = g.spzt  "
						+ "  and d.zl= 'sgztlx'  and d.jb=2"
						+ " and a.sgzt=6 "+str;
				sql += " order by sgrq desc";
		}
		return jdbcPager.queryPage(sql, start, limit);
	}
	
		/**
		 * 报修申购的部门审批改变申购表的状态
		 * @author oufeng
		 * @date 2015年8月21日
		 * @param dm  申购的id
		 * @return
		 */
		public void 	toUpdateSbsgBm(Integer dm) {
			String str="";
			if(dm!=null&&!"".equals(dm)){
				str+="and  id ="+dm+"" ;
			}
			String sql = "update  "
				+" sb_sbsgxx  set sgzt =4  where 1=1  "
					+str;
			 jdbcTemplate.execute(sql);
		}
		
		/**
		 * 报修申购的部门审批改变申购表的状态--驳回
		 * @author oufeng
		 * @date 2015年8月21日
		 * @param dm  申购的id
		 * @return
		 */
		public void 	toUpdateSbsgBmBh(Integer dm) {
			String str="";
			if(dm!=null&&!"".equals(dm)){
				str+="and  id ="+dm+"" ;
			}
			String sql = "update  "
				+" sb_sbsgxx  set sgzt =3  where 1=1  "
					+str;
			 jdbcTemplate.execute(sql);
		}
		
		/**
		 * 报修申购的部门审批改变申购表的状态
		 * @author oufeng
		 * @date 2015年8月21日
		 * @param dm  申购的id
		 * @return
		 */
		public void 	toUpdateSbsgLd(Integer dm) {
			String str="";
			if(dm!=null&&!"".equals(dm)){
				str+="and  id ="+dm+"" ;
			}
			String sql = "update  "
				+" sb_sbsgxx  set sgzt =6  where 1=1  "
					+str;
			 jdbcTemplate.execute(sql);
		}
		
		/**
		 * 报修申购的部门审批改变申购表的状态--驳回
		 * @author oufeng
		 * @date 2015年8月21日
		 * @param dm  申购的id
		 * @return
		 */
		public void 	toUpdateSbsgLdBh(Integer dm) {
			String str="";
			if(dm!=null&&!"".equals(dm)){
				str+="and  id ="+dm+"" ;
			}
			String sql = "update  "
				+" sb_sbsgxx  set sgzt =5  where 1=1  "
					+str;
			 jdbcTemplate.execute(sql);
		}
		
		/**
		 * 报修申购的部门审批改变申购表的状态
		 * @author oufeng
		 * @date 2015年8月21日
		 * @param dm  申购的id
		 * @return
		 */
		public void 	toUpdateSbsgCw(Integer dm) {
			String str="";
			if(dm!=null&&!"".equals(dm)){
				str+="and  id ="+dm+"" ;
			}
			String sql = "update  "
				+" sb_sbsgxx  set sgzt =8  where 1=1  "
					+str;
			 jdbcTemplate.execute(sql);
		}
		
		/**
		 * 报修申购的财务审批改变申购表的状态--驳回
		 * @author oufeng
		 * @date 2015年8月21日
		 * @param dm  申购的id
		 * @return
		 */
		public void 	toUpdateSbsgCwBh(Integer dm) {
			String str="";
			if(dm!=null&&!"".equals(dm)){
				str+="and  id ="+dm+"" ;
			}
			String sql = "update  "
				+" sb_sbsgxx  set sgzt =7  where 1=1  "
					+str;
			 jdbcTemplate.execute(sql);
		}
		
		/**
		 * 根据字典种类找出子字典
		 * @author oufeng 
		 * @version 创建时间：2015年6月3日 下午3:38:44 
		 * @param zdzl
		 * @return
		 */
		public List<Map<String, Object>> getBmByLx(String zdzl) {
			String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 获得申请人
		 * @author oufeng 
		 * @version 创建时间：2015年6月3日 下午3:38:44 
		 * @param zdzl
		 * @return
		 */
		public List<Map<String, Object>> getSgr() {
			String sql =" select yhbh,xm from sys_yh";
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 获得状态
		 * @author oufeng 
		 * @version 创建时间：2015年6月3日 下午3:38:44 
		 * @return
		 */
		public List<Map<String, Object>> getZt() {
			String sql =" select zdz,zdbm from sys_sjzd where zl= 'sgztlx'  and jb=2 and zdz=1";
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 获得审批意见查看
		 * @author oufeng 
		 * @version 创建时间：2015年8月31日
		 * @return
		 */
		public List<Map<String, Object>> getSbsgCheck(String id) {
			String sql =" select a.sprxm,spyj,DATE_FORMAT(a.spsj,'%Y-%m-%d %H:%i:%s') as spsj,"
					+ "  case when sftg=0 then '通过' else '不通过' end as sftg  "
					+ " from sb_sbsgyj a ,sb_sbsgxx b where a.sgid = b.id and  a.sgid ="+id;
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 获得申购状态的字典表
		 * @author oufeng 
		 * @version 创建时间：2015年6月3日 下午3:38:44 
		 * @return
		 */
		public List<Map<String, Object>> getSqzt() {
			String sql =" select zdz,zdbm from sys_sjzd where zl= 'sgztlx'  and jb=2 ";
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 获得部门状态的字典表
		 * @author oufeng 
		 * @version 创建时间：2015年6月3日 下午3:38:44 
		 * @return
		 */
		public List<Map<String, Object>> getBmzt() {
			String sql =" select zdz,zdbm from sys_sjzd where "
					+ " zl= 'sgztlx'  and jb=2 and (zdz = 1 or zdz =5 )";
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 获得部门状态的字典表
		 * @author oufeng 
		 * @version 创建时间：2015年6月3日 下午3:38:44 
		 * @return
		 */
		public List<Map<String, Object>> getLdzt() {
			String sql =" select zdz,zdbm from sys_sjzd where zl= 'sgztlx'  "
					+ " and jb=2 and (zdz = 4 or zdz =7 )";
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 获得部门编号
		 * @author oufeng 
		 * @version 创建时间：2015年6月3日 下午3:38:44 
		 * @param yhbh
		 * @return
		 */
		public List<Map<String, Object>> getBmbh(String yhbh) {
			String str ="";
			if(yhbh!=null&&!"".equals(yhbh)){
				str+="and  yhbh ='"+yhbh+"'" ;
			}
			String sql =" select bmbh from sys_yh where 1=1 "
					+str;
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
	}
