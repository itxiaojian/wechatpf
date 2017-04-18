package com.sliu.framework.app.xzxx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.xzxx.model.XxXjxxb;

/**
 * 校长信箱-信件信息表 DAO
 * @author            liujiansen
 * @since             2015-09-01
 */
@Repository
public class XxXjxxbDao extends HibernateBaseDaoImpl<XxXjxxb,Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 校长信箱信件信息
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param pages
	 * @param code
	 * @param lx
	 * @param yhbh
	 * @return
	 */
	public List<Map<String,Object>> getXzxxList(String Currentpage,String code,String lx,String yhbh,String dlm){
		String str = "";
		String sql="";
		if(lx!=null&&!"".equals(lx)){
			List<Map<String,Object>> yh=this.getYh(yhbh);
			if(yh.size()!=0){
				str=str+" and fjrbh='"+yh.get(0).get("dlm")+"'";
			}
		}else{
			str=str+" and clzt not in ('1','2','7')";
		}
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(xjbt,''),ifnull(xjnr,'')) like '%"+code+"%'";
		}
		if(Currentpage!=null&&!"".equals(Currentpage)){
			int num=(Integer.parseInt(Currentpage)-1)*10;
			str=str+" order by xxsj desc limit "+num+",10  ";
		}
		if(dlm!=null&&!"".equals(dlm)&& "admin".equals(dlm)){
			sql="SELECT id, xjbt, clbm, clzt, fjrxm, fjrbh, slbmbh, slbmmc, txdz, lxdh, DATE_FORMAT(xxsj,'%Y-%m-%d %H:%i') as xxsj, xjnr, shsj, shr, cljg, clsj, pj, bz "
				+ "FROM xx_xjxxb where 1=1 "+str;
		}else{
			sql="SELECT id, xjbt, clbm, clzt, case when fjrbh='"+dlm+"' then fjrxm else '***' end as  fjrxm"
					+ " ,fjrbh, slbmbh, slbmmc, txdz, lxdh, DATE_FORMAT(xxsj,'%Y-%m-%d %H:%i') as xxsj, xjnr, shsj, shr, cljg, clsj, pj, bz "
					+ " FROM xx_xjxxb where 1=1 "+str;
		}
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询总信件数量
	 * @author zhangyan
	 * @date 2016年12月15日 下午3:08:56
	 * @param 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int getxjAcount(String yhbh,String lx){
		String str = "";
		if(lx!=null&&!"".equals(lx)){
			List<Map<String,Object>> yh=this.getYh(yhbh);
			if(yh.size()!=0){
				str=str+" and fjrbh='"+yh.get(0).get("dlm")+"'";
			}
		}else{
			str=str+" and clzt not in ('1','2','7')";
		}
		if(yhbh!=null&&!"".equals(yhbh)){
			str = " and fjrbh='"+yhbh+"'";
		}
		String sql=" select count(1) as xjcount from xx_xjxxb where 1=1 "+str;
	    return jdbcTemplate.queryForInt(sql);
	}
	
	
	/**
	 * 获取信件总数量
	 * @author zhangyan
	 * @date 2016年12月16日 上午8:34:17
	 * @param 
	 * @return
	 */
	public int getAcount(){
		String str = "";
			str=str+" and clzt not in ('1','2','7')";
		String sql=" select count(1) as xjcount from xx_xjxxb where 1=1 "+str;
	    return jdbcTemplate.queryForInt(sql);
	}
	
	
	/**
	 * 手机端校长信箱总页数
	 * @author zhangyan
	 * @date 2016年12月15日 下午4:38:19
	 * @param 
	 * @return
	 */
	public int getPagecount(String code,String lx,String yhbh,String dlm){
		int rows = this.getXzxxList("",code,lx,yhbh,dlm).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 获取用户信息
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getYh(String id){
		String str="";
		if(id!=null&&!"".equals(id)){
			str=str+" and a.yhbh='"+id+"'";
		}
		String sql="select a.dlm,a.xm,a.yhbh,a.bmbh,a.gwbh,c.jsmc,c.bz from sys_yh a "
				+ "left join sys_yhjs b on a.yhbh=b.yhbh left join sys_js c on b.jsbh=c.jsbh where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据openId获取绑定用户的信息
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getUser(String openId){
		String sql="SELECT a.wxnc, a.wxh,b.xm,b.dlm,a.yhid FROM sys_wxyh a "
				+ "left join sys_yh b on a.yhid=b.yhbh where a.zt='1' and a.wxh='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 在微服务主页展示的信件
	 * @author duanpeijun
	 * @date 2015年9月1日
	 * @return
	 */
	public List<Map<String, Object>> getXjxxb(String openId){
		String sql = "select a.id,a.xjbt,a.clzt,DATE_FORMAT(a.clsj,'%m-%d') as xxsj from xx_xjxxb a where a.CLZT = '5' or a.CLZT = '6' ORDER BY a.clsj  desc LIMIT 5";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 后台：校长信箱发布列表
	 * @author duanpeijun
	 * @date 2015年9月1日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String,Object>> getXjxxbList(Integer start, Integer limit,String code,String yhxm,List<Object> jsList) {

		String str = "";
		String sql= "";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.xjbt,''),ifnull(a.slbmmc,'')) like '%"+code+"%'";
		}
		
		if (jsList.size()!= 0 && ! jsList.contains("ROLE_ADMIN") && ! jsList.contains("ROLE_ADMIN_XZXX") ) {
			if (yhxm != null && !"".equals(yhxm) && !"admin".equals(yhxm)) {
				str += " and  b.dlm ='" + yhxm + "'";
			}
		 sql = "select a.id,a.xjbt,a.clbm,a.clzt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,"
		 		+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.myd,a.ipdz,a.thly,"
				+ "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.clr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,c.xm "
				+ "from xx_xjxxb a left join sys_yh b on a.fjrbh=b.dlm left join sys_yh c on a.shr = c.dlm where 1=1 "+ str;
		
		sql += " order by a.xxsj desc";
		}else if(jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_XZXX") ) {
			 sql = "select a.thly,a.id,a.xjbt,a.clbm,a.clzt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.myd,a.ipdz,"
				 + "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.clr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,c.xm "
				 + "from xx_xjxxb a left join sys_yh c on a.shr = c.dlm where 1=1 "+ str;
				
				sql += " order by a.xxsj desc";
		}
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 后台:信件审核列表
	 * @author duanpeijun
	 * @date 2015年9月2日
	 * @param start
	 * @param limit
	 * @param yhbh
	 * @return
	 */
	public Pagination<Map<String, Object>> getXjshList(Integer start, Integer limit,String code,List<Object> jsList){
		String sql = "";
		String str="";
		if(code!=null&&!"".equals(code)){
			str+=" and concat(ifnull(a.xjbt,''),ifnull(a.slbmmc,'')) like '%"+code+"%'";
		}
		if (jsList.size()!= 0  && jsList.contains("ROLE_SHR")&& ! jsList.contains("ROLE_ADMIN") && ! jsList.contains("ROLE_ADMIN_XZXX") ) {
			sql = "select a.id,a.xjbt,a.clbm,a.clzt,'*****' as fjrxm,'*****' as fjrbh,a.slbmbh,a.slbmmc,'*****' as  txdz,'*****' as lxdh,"
				+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
				+ "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from "
				+ "xx_xjxxb a where 1=1 and a.CLZT ='1' "+str;
			        sql += " order by a.xxsj desc";
		}else if(jsList.contains("ROLE_ADMIN") ||  jsList.contains("ROLE_ADMIN_XZXX")) {
			sql = "select a.id,a.xjbt,a.clbm,a.clzt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
				+ "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from "
				+ "xx_xjxxb a where 1=1 and a.CLZT ='1'"+str;
			sql += " order by a.xxsj desc";
		}
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 后台：校长信箱已审核列表
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String,Object>> getXjyshList(Integer start, Integer limit,String code,String yhxm,List<Object> jsList) {

		String str = "";
		String sql = "";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.xjbt,''),ifnull(a.slbmmc,'')) like '%"+code+"%'";
		}
		if (yhxm != null && !"".equals(yhxm) && ! jsList.contains("ROLE_ADMIN") && ! jsList.contains("ROLE_ADMIN_XZXX")) {
			str += " and  b.dlm ='" + yhxm + "'";
		}
		if (jsList.size()!= 0  && jsList.contains("ROLE_SHR")&& ! jsList.contains("ROLE_ADMIN") && ! jsList.contains("ROLE_ADMIN_XZXX")) {
		 sql = "select a.id,a.xjbt,a.clbm,a.clzt,'*****' as fjrxm,'*****' as fjrbh,a.slbmbh,a.slbmmc,'*****' as txdz,'*****' as lxdh,"
		 		+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
				+ "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,b.dlm,b.xm from "
				+ "xx_xjxxb a left join sys_yh b on a.shr=b.dlm where 1=1 and a.CLZT not in ('1') "+ str;
		
		sql += " order by a.shsj desc";
		}else if(jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_XZXX")) {
		 sql = "select a.id,a.xjbt,a.clbm,a.clzt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
			 + "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,b.dlm,b.xm from "
			 + "xx_xjxxb a left join sys_yh b on a.shr=b.dlm where 1=1 and a.CLZT not in ('1') "+ str;
			
			sql += " order by a.shsj desc";
		}
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:38:44 
	 * @param zdzl  字典种类
	 * @return 
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 后台：校长信箱受理列表
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String,Object>> getXjslList(Integer start, Integer limit,String code,String yhbm,List<Object> jsList) {

		String str = "";
		String sql = "";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.xjbt,''),ifnull(a.slbmmc,'')) like '%"+code+"%'";
		}
		if (yhbm != null && !"".equals(yhbm) && ! jsList.contains("ROLE_ADMIN") && !jsList.contains("ROLE_ADMIN_XZXX")) {
			str += " and  c.BMBH ='" + yhbm + "'";
		}
		if (jsList.size()!= 0  && jsList.contains("ROLE_SLR")&& ! jsList.contains("ROLE_ADMIN") && !jsList.contains("ROLE_ADMIN_XZXX")) {
			sql = "select a.id,a.xjbt,a.clbm,a.clzt,'*****' as fjrxm,'*****' as fjrbh,a.slbmbh,a.slbmmc,'*****' as txdz,'*****' as lxdh,"
					+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
				+ "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,d.xm from "
				+ "xx_xjxxb a left join sys_zzjg c on a.SLBMBH = c.BMBH left join sys_yh d on a.shr = d.dlm where a.clzt in ('3') "+ str;
		
		sql += " order by a.shsj desc";
		}else if (jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_XZXX")) {
			sql = "select a.id,a.xjbt,a.clbm,a.clzt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
				+ "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,d.xm from "
				+ "xx_xjxxb a left join sys_zzjg c on a.SLBMBH = c.BMBH left join sys_yh d on a.shr = d.dlm where a.clzt in ('3') "+ str;
			
			sql += " order by a.shsj desc";
			}
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 后台：校长信箱已受理列表
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String,Object>> getXjyslList(Integer start, Integer limit,String code,String yhbm,List<Object> jsList) {

		String str = "";
		String sql = "";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.xjbt,''),ifnull(a.slbmmc,'')) like '%"+code+"%'";
		}
	  //if (yhbm != null && !"".equals(yhbm) && !"jszj".equals(yhbm)) {
		if (yhbm != null && !"".equals(yhbm) && ! jsList.contains("ROLE_ADMIN") && !jsList.contains("ROLE_ADMIN_XZXX")) {
			str += " and  c.BMBH ='" + yhbm + "'";
		}
		if (jsList.size()!= 0  && jsList.contains("ROLE_SLR")&& ! jsList.contains("ROLE_ADMIN") && !jsList.contains("ROLE_ADMIN_XZXX")) {
		 sql = "select a.id,a.xjbt,a.clbm,a.clzt,'*****' as fjrxm,'*****' as fjrbh,a.slbmbh,a.slbmmc,'******' as txdz,'*****' as lxdh,"
		 		+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
			 + "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,a.clr,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,b.xm from "
			 + "xx_xjxxb a left join sys_zzjg c on a.SLBMBH = c.BMBH left join sys_yh b on a.shr = b.dlm where a.clzt in ('5','6') "+ str;
		
		sql += " order by a.clsj desc";
		}else if (jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_XZXX")) {
		 sql = "select a.id,a.xjbt,a.clbm,a.clzt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.ipdz,"
			 + "a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,a.clr,DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,b.xm from "
			 + "xx_xjxxb a left join sys_zzjg c on a.SLBMBH = c.BMBH left join sys_yh b on a.shr = b.dlm where a.clzt in ('5','6') "+ str;
			
			sql += " order by a.clsj desc";
		}
		return jdbcPager.queryPage(sql, start, limit);
	}

	/**
	 * 查找部门
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @return
	 */
	public List<Map<String,Object>> getZzjg(String bmbh){
		String str="";
		if(bmbh!=null&&!"".equals(bmbh)){
			str=str+" and bmbh='"+bmbh+"'";
		}
		String sql="SELECT id, bmbh, bmmc, sjbh, jb, px, bz FROM sys_zzjg where jb='2' "+str;
		return jdbcTemplate.queryForList(sql);
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
	
	/**
	 * 信件查看
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @return
	 */
	public List<Map<String, Object>> getXjckList(String bm,String bmmc,String clzt,List<Object> jsList,String xm){
		String str="";
		String sql = "";
		if(bm!=null&&!"".equals(bm)){
			str=str+" and( a.fjrxm like '%"+bm+"%'"   + " or a.xjnr like '%"+bm+"%'  or a.xjbt like '%"+bm+"%')" ;
		}
		if(bmmc!=null&&!"".equals(bmmc)){
			str+=" and  a.slbmmc ='"+bmmc+"'"  ;
		}
		if(clzt!=null&&!"".equals(clzt)){
			str+=" and  a.clzt ='"+clzt+"'"  ;
		}
		
		if (jsList.size()!= 0 && ! jsList.contains("ROLE_ADMIN") && !jsList.contains("ROLE_ADMIN_XZXX")) {
			/*if (yhxm != null && !"".equals(yhxm) && !"admin".equals(yhxm)) {
				str += " and  c.dlm ='" + yhxm + "'";
			}*/
		 sql = "select a.id,a.xjbt, case when '"+xm+"' =a.fjrxm then a.fjrxm else   '******'  end as fjrxm,case when '"+xm+"' =a.fjrxm then a.fjrbh else   '******'  end as fjrbh"
		 		+ ",a.slbmbh,a.slbmmc,case when '"+xm+"' =a.fjrxm then a.txdz else   '******'  end as txdz,case when '"+xm+"' =a.fjrxm then a.lxdh else   '******'  end as lxdh,a.clzt as ztbh,"
			    +"b.zdmc as zt,a.myd,a.ipdz,"
				+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,"
				+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from xx_xjxxb a left join  sys_sjzd b  on a.clzt = b.zdz where 1=1 " 
				+ " and b.zl= 'clzt'  and b.jb=2 and a.clzt in ('3','5','6') "+str;
				sql +=" order by  a.xxsj desc";
		}else if(jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_XZXX")) {
			sql = "select a.id,a.xjbt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,a.clzt as ztbh,"
				    +"b.zdmc as zt, a.myd,"
					+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,a.ipdz,"
					+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from xx_xjxxb a left join  sys_sjzd b  on a.clzt =b.zdz where 1=1 " 
					+ " and b.zl= 'clzt'  and b.jb=2 and a.clzt in ('3','5','6') "
					+str;
				sql+=" order by  a.xxsj desc";
		}
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取总条数
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @return
	 *//*
	public int getCount(String bm,String bmbh,String clzt,String yhxm,String bmmc){
		List<Object> jsList = null;
		int rows = this.getXjckList("", bmbh, bmmc, clzt, yhxm, jsList).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}*/
	
	/**
	 * 获取查看数据
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getXjckDetail(String bxbh,List<Object> jsList,String xm) {
		String str="";
		String sql = "";
		if(bxbh!=null&&!"".equals(bxbh)){
			str=str+" and a.id ='"+bxbh+"'";
		}
		if (jsList.size()!= 0 && ! jsList.contains("ROLE_ADMIN") && !jsList.contains("ROLE_ADMIN_XZXX")) {
		 sql  = "select a.id,a.xjbt,case when '"+xm+"' =a.fjrxm then a.fjrxm else   '******'  end as fjrxm,"
		 		+ "case when '"+xm+"' =a.fjrxm then a.fjrbh else   '******'  end as fjrbh,a.slbmbh,a.slbmmc,"
		 		+ "case when '"+xm+"' =a.fjrxm then a.lxdh else   '******'  end as lxdh,a.clzt as ztbh,"
		 		+ "case when '"+xm+"' =a.fjrxm then a.txdz else   '******'  end as txdz,"
			    +"b.zdmc as zt,a.myd,a.ipdz,"
				+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,"
				+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.clr,a.pj,a.bz,c.xm from xx_xjxxb a left join  sys_sjzd b  on a.clzt =b.zdz "
				+ "left join sys_yh c on a.shr = c.dlm"
				+ " where 1=1 " 
				+ " and b.zl= 'clzt'  and b.jb=2"
				+str;
		}else if(jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_XZXX")) {
			 sql  = "select a.id,a.xjbt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,a.clzt as ztbh,"
				    +"b.zdmc as zt,a.myd,a.ipdz,"
					+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,"
					+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.clr,a.pj,a.bz,c.xm from xx_xjxxb a left join  sys_sjzd b  on a.clzt =b.zdz "
					+ "left join sys_yh c on a.shr = c.dlm"
					+ " where 1=1 " 
					+ " and b.zl= 'clzt'  and b.jb=2"
					+str;
		}
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 从数据字典中获取处理状态到信件查看页面
	 * @author duanpeijun
	 * @date 2015年9月11日
	 * @return
	 */
	public List<Map<String,Object>> getClztList(){
		String sql = "select zdz,zdmc from sys_sjzd where jb = 2 and zl = 'clzt' " 
			 +" and zdz in ('1','2','3','4','5','6')";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 根据字典种类找出满意度
	 * @author duanpeijun
	 * @version 创建时间：2015年6月3日 下午3:38:44 
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByMyd(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 查询有无未完成的处理状态
	 * @author duanpeijun
	 * @date 2015年9月18日
	 * @return
	 */
	public List<Map<String, Object>> getClztsizeList(String yhxm){
		String str = "";
		if (yhxm != null && !"".equals(yhxm)) {
			str += "and a.clzt in ('5') and  b.dlm ='" + yhxm + "'";
		}
		if("admin".equals(yhxm)){
			str += "and a.clzt in ('xyz') and  b.dlm ='" + yhxm + "'";
		}
		String sql = "select a.clzt from xx_xjxxb a left join sys_yh b on a.fjrbh = b.dlm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 校长信箱的详情
	 * @author duanpeijun
	 * @date 2015年9月18日
	 * @return
	 */
	public List<Map<String, Object>> getXzxxDetail(String id,String dlm){
		String str = "";
		String sql="";
		if (id != null && !"".equals(id)) {
			str += " and  id ='" + id + "'";
		}
		if (dlm != null && !"".equals(dlm) && "admin".equals(dlm)) {
		 sql = "select  id,xjbt,clbm,clzt,fjrxm,fjrbh, slbmbh,ipdz,"
				+ " slbmmc,txdz,lxdh,DATE_FORMAT(xxsj,'%Y-%m-%d')as xxsj, xjnr,"
				+ " DATE_FORMAT(shsj,'%Y-%m-%d')as shsj,shr,cljg,clr,DATE_FORMAT(clsj,'%Y-%m-%d') "
				+ " as clsj,pj,bz,thly,myd from xx_xjxxb  where 1=1 "+ str;
		}else {
			sql = "select  id,xjbt,clbm,clzt,fjrbh,ipdz,"
					+ " case when fjrbh='"+dlm+"' then fjrxm else '***' end as fjrxm, slbmbh,"
					+ " slbmmc, DATE_FORMAT(xxsj,'%Y-%m-%d')as xxsj, xjnr,"
					+ " case when fjrbh='"+dlm+"' then txdz else '***********' end as txdz,"
					+ " case when fjrbh='"+dlm+"' then lxdh else '***********' end as lxdh,"
					+ " DATE_FORMAT(shsj,'%Y-%m-%d')as shsj,shr,cljg,clr,DATE_FORMAT(clsj,'%Y-%m-%d') "
					+ " as clsj,pj,bz,thly,myd from xx_xjxxb where 1=1  "+ str;
		}
		return jdbcTemplate.queryForList(sql);
	}
	
//////
	/**
	 * 未回复信件查看
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @return
	 */
	public List<Map<String, Object>> getYhfList(String time,String bmbh,String nr,String pagesy){
		String str="";
		String sql = "";
		if(time!=null&&!"".equals(time)){
			str+=" and DATE_FORMAT(a.xxsj,'%Y-%m-%d') ='"+time+"'";
		}
		if(bmbh!=null&&!"".equals(bmbh)){
			str+=" and  a.slbmbh ='"+bmbh+"'"  ;
		}
		if(nr!=null&&!"".equals(nr)){
			str+=" and  a.xjbt like '%"+nr+"%'"  ;
		}
		if(pagesy!=null&&!"".equals(pagesy)){
			int num=(Integer.parseInt(pagesy)-1)*10;
			str+=" order by  a.xxsj desc limit "+num+",10 ";
		}
			sql = "select a.id,a.xjbt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,a.clzt as ztbh,"
				    +"b.zdmc as zt, a.myd,a.ipdz,clbm,clsj,"
					+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,a.ipdz,"
					+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from xx_xjxxb a left join  sys_sjzd b  on a.clzt =b.zdz where 1=1 " 
					+ " and b.zl= 'clzt'  and b.jb=2 and a.clzt in ('4','3') "
					+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 已回复信件查看
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @return
	 */
	public List<Map<String, Object>> getXjmhckList(String time,String bmbh,
			String nr,List<Object> jsList,String pagesw){
		String str="";
		String sql = "";
		if(time!=null&&!"".equals(time)){
			str+=" and DATE_FORMAT(a.xxsj,'%Y-%m-%d') ='"+time+"'";
		}
		if(bmbh!=null&&!"".equals(bmbh)){
			str+=" and  a.slbmbh ='"+bmbh+"'"  ;
		}
		if(nr!=null&&!"".equals(nr)){
			str+=" and  a.xjbt like '%"+nr+"%'"  ;
		}
		if(pagesw!=null&&!"".equals(pagesw)){
			int num=(Integer.parseInt(pagesw)-1)*10;
			str+=" order by  a.clsj desc limit "+num+",10 ";
		}
		if(jsList==null ){
			sql = "select a.id,a.xjbt,'******' as fjrxm,'*****' as fjrbh,a.slbmbh,a.slbmmc,'*****' as txdz,'*****' as lxdh,a.clzt as ztbh,'*****' as ipdz,"
				    +"b.zdmc as zt,a.myd,a.ipdz,"
					+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,"
					+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from xx_xjxxb a left join  sys_sjzd b  on a.clzt = b.zdz where 1=1 " 
					+ " and b.zl= 'clzt'  and b.jb=2 and a.clzt in ('5','6') "+str;
		}else{
			if(jsList.size() == 0 ){
				sql = "select a.id,a.xjbt,'******' as fjrxm,'*****' as fjrbh,a.slbmbh,a.slbmmc,'*****' as txdz,'*****' as lxdh,a.clzt as ztbh,'*****' as ipdz,"
					    +"b.zdmc as zt,a.myd,a.ipdz,"
						+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,"
						+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from xx_xjxxb a left join  sys_sjzd b  on a.clzt = b.zdz where 1=1 " 
						+ " and b.zl= 'clzt'  and b.jb=2 and a.clzt in ('5','6') "+str;
			}else if (jsList.size()!= 0 && ! jsList.contains("ROLE_ADMIN") && !jsList.contains("ROLE_ADMIN_XZXX")) {
				/*if (yhxm != null && !"".equals(yhxm) && !"admin".equals(yhxm)) {
					str += " and  c.dlm ='" + yhxm + "'";
				}*/
			 sql = "select a.id,a.xjbt,'******' as fjrxm,'*****' as fjrbh,a.slbmbh,a.slbmmc,'*****' as txdz,'*****' as lxdh,a.clzt as ztbh,'*****' as ipdz,"
				    +"b.zdmc as zt,a.myd,a.ipdz,"
					+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,"
					+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from xx_xjxxb a left join  sys_sjzd b  on a.clzt = b.zdz where 1=1 " 
					+ " and b.zl= 'clzt'  and b.jb=2 and a.clzt in ('5','6') "+str;
			}else if(jsList.contains("ROLE_ADMIN") || jsList.contains("ROLE_ADMIN_XZXX")) {
				sql = "select a.id,a.xjbt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,a.clzt as ztbh,"
					    +"b.zdmc as zt, a.myd,a.ipdz,"
						+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,a.ipdz,"
						+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz from xx_xjxxb a left join  sys_sjzd b  on a.clzt =b.zdz where 1=1 " 
						+ " and b.zl= 'clzt'  and b.jb=2 and a.clzt in ('5','6') "
						+str;
			}
		}
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取未回复总条数
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @return
	 */
	public int getCountw(String time,String bmbh,String nr){
		List<Object> jsList =null;
		int rows = this.getXjmhckList(time, bmbh,nr,jsList,"").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 获取已回复总条数
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @return
	 */
	public int getCounty(String time,String bmbh,String nr){
		List<Object> jsList =null;
		int rows = this.getYhfList(time, bmbh,nr,"").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 获取查看数据
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getXjmhckDetailWeb(String bxbh) {
		String str="";
		String sql = "";
		if(bxbh!=null&&!"".equals(bxbh)){
			str=str+" and a.id ='"+bxbh+"'";
		}
			 sql  = "select a.id,a.xjbt,a.fjrxm,a.fjrbh,a.slbmbh,a.slbmmc,a.txdz,a.lxdh,a.clzt as ztbh,"
				    +"b.zdmc as zt,case when  a.myd =1 then '非常满意' when a.myd =2 then '满意' when a.myd=3 then '不满意' end as myd ,"
					+ "DATE_FORMAT(a.xxsj,'%Y-%m-%d %H:%i:%S') as xxsj,a.xjnr,DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i:%S') as shsj,a.shr,a.cljg,"
					+ "DATE_FORMAT(a.clsj,'%Y-%m-%d %H:%i:%S') as clsj,a.pj,a.bz,c.xm from xx_xjxxb a left join  sys_sjzd b  on a.clzt =b.zdz "
					+ "left join sys_yh c on a.shr = c.dlm"
					+ " where 1=1 " 
					+ " and b.zl= 'clzt'  and b.jb=2"
					+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得已回复总数
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getZs() {
       String	 sql  = "select  count(1) as jlzs  from  xx_xjxxb where  clzt in ('5','6')";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得未回复总数
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getWzs() {
       String	 sql  = "select  count(1) as wjlzs  from  xx_xjxxb where  clzt in ('3','4')";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param bmmc 部门名称
	 * @return
	 */
	public List<Map<String,Object>> getBmbh(String bmmc) {
		String str ="";
		if(bmmc!=null&&!"".equals(bmmc)){
			str+=" and bmmc ='"+bmmc+"'";
		}
       String	 sql  = "SELECT bmbh FROM  sys_zzjg WHERE 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得登录名
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @param bxbh 报修编号
	 * @return
	 */
	public List<Map<String,Object>> getDlm(String yhbh) {
		String str= "";
		if(yhbh!=null&&!"".equals(yhbh)){
			str+=" and yhbh ='"+yhbh+"'";
		}
       String	 sql  = "select  dlm from sys_yh where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询有未评价
	 * @author duanpeijun
	 * @date 2015年9月18日
	 * @return
	 */
	public List<Map<String, Object>> getSfpj(String yhxm){
		String str = "";
		if (yhxm != null && !"".equals(yhxm)) {
			str += "and a.clzt in ('5') and  b.dlm ='" + yhxm + "'";
		}
		if("admin".equals(yhxm)){
			str += "and a.clzt in ('xyz') and  b.dlm ='" + yhxm + "'";
		}
		String sql = "select a.clzt from xx_xjxxb a left join sys_yh b on a.fjrbh = b.dlm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询有无未完成的处理状态
	 * @author oufeng
	 * @date 2016年3月8日
	 * @return
	 */
	public List<Map<String, Object>> getSfclwc(String yhxm){
		String str = "";
		if (yhxm != null && !"".equals(yhxm)) {
			str += "and a.clzt in ('1','3','4') and  b.dlm ='" + yhxm + "'";
		}
		if("admin".equals(yhxm)){
			str += "and a.clzt in ('xyz') and  b.dlm ='" + yhxm + "'";
		}
		String sql = "select a.clzt from xx_xjxxb a left join sys_yh b on a.fjrbh = b.dlm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param bmmc 部门名称
	 * @return
	 */
	public List<Map<String,Object>> hasSlbmbh(String id) {
		String str ="";
		if(id!=null&&!"".equals(id)){
			str+=" and id ='"+id+"'";
		}else{
			str+=" and 1=2";
		}
       String	 sql  = "select slbmbh,slbmmc from  xx_xjxxb WHERE 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param bmmc 部门名称
	 * @return
	 */
	public List<Map<String,Object>> getSlbmbh(String slbmmc) {
		String str ="";
		if(slbmmc!=null&&!"".equals(slbmmc)){
			str+=" and bmmc ='"+slbmmc+"'";
		}else{str+= " and 1=2 ";}
       String	 sql  = "select bmbh,bmmc from  sys_zzjg  WHERE 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param bmmc 部门名称
	 * @return
	 */
	public void updateSlbmbh(String bmbh,String slbmmc,String id) {
		String str ="";
		if(slbmmc!=null&&!"".equals(slbmmc)){
			str+=" and slbmmc ='"+slbmmc+"'";
		}else{str +=" and 1=2";}
       String	 sql  = "update xx_xjxxb set slbmbh ='"+bmbh +"' where 1=1"+str;
		  jdbcTemplate.execute(sql);
	}
}

