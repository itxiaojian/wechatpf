package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsJskb;

@Repository
public class ZsJskbDao extends HibernateBaseDaoImpl<ZsJskb, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 获取教师课表信息
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @param zs
	 * @return
	 */
	public List<Map<String,Object>> getJskb(String start,String end,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and a.jsgh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select a.id,a.jsgh,DATE_FORMAT(a.skrq,'%Y.%m.%d') as skrq,a.xq,a.dyzs,DATE_FORMAT(a.sksj,'%m\\\\/%d\\\\/%Y %H:%i') as sksj,"
				+ "DATE_FORMAT(a.xksj,'%m\\\\/%d\\\\/%Y %H:%i') as xksj,a.kcbh,a.kcmc,a.skdd,a.dtskxh,a.bz from zs_jskb a "
				+ "where 1=1 "+str+" and DATE_FORMAT(a.skrq,'%Y-%m-%d') between '"+start+"' and '"+end+"' order by a.skrq,a.sksj";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取学年
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getXn(String openId){
	/*	String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and jsgh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql=" SELECT "
       +" CONCAT(CONCAT(xn,'-'),dxq)AS xn"
       +" FROM zs_jskb WHERE 1=1 "
       +str
       +" GROUP BY  xn,dxq"
       +" ORDER BY xn DESC,xq DESC"
       +"  LIMIT 4";*/
		String sql="select zdz as xn,zdmc as xn1 from sys_sjzd where zl='xnxq' and jb=2 ";
	   return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取最大周数
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @return
	 */
	public List<Map<String,Object>> getZs(){
		String sql="  SELECT   MAX(CONVERT(SUBSTRING_INDEX(dyzs,',',-1),SIGNED)) AS zs  FROM  zs_jskb ";
//		String sql="SELECT max(case when instr(substring(dyzs,3),'-')<1 then CONVERT(substring(dyzs,3),SIGNED) else "
//				+ "CONVERT(substring(substring(dyzs,3),2),SIGNED) end) as zs FROM zs_jskb";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取最大周数
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @return
	 */
	public List<Map<String,Object>> getZs1(){
		String sql="SELECT  max(dyzs) as zs FROM zs_jskb";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取教师课表信息(新)
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @param xn
	 * @param xq
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getJskbList(String xn,String xq,String zs,String openId,String jc){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str+=" and jsgh='"+yh.get(0).get("dlm")+"'";
		}else{
			str+=" and 1=2 ";
		}
	}
		if(xn!=null&&!"".equals(xn)){
			str=str+" and xn='"+xn+"'";
		}
		if(xq!=null&&!"".equals(xq)){
			str=str+" and dxq='"+xq+"'";
		}
		if(zs!=null&&!"".equals(zs)){
			int   zsInt = Integer.parseInt(zs);
		/*	if(zsInt<10){
				String zsInt1 = "1"+zsInt;
				String zsInt2 = "2"+zsInt;
				str+= " and dyzs like '%"+zsInt+"%' and  dyzs not like '%"+zsInt1+"%' and  dyzs not like'%"+zsInt2+"%'" ;
			}else{
			//str += " and "+zs+" between  CONVERT(SUBSTRING_INDEX(dyzs,'-',1),SIGNED) and CONVERT(SUBSTRING_INDEX(dyzs,'-',-1),SIGNED)";
				str+= " and dyzs like '%"+zsInt+"%'";
		   }*/
			str+=" and REPLACE(SUBSTR(dyzs,1,3),',','') <= "+zsInt+" and "
			        +zsInt+"<= REPLACE(SUBSTR(dyzs,-3,3),',','')  and dyzs LIKE '%,"+zsInt+"%'";
		}
		if(jc!=null&&!"".equals(jc)){
			str=str+" and "+jc;
		}
		String sql=" select b.zdmc,b.xn,b.dxq,b.jsgh,b.xm,b.xq,b.dsz,b.jc,b.kcbh, "
				+" b.jsbh, b.skdd,GROUP_CONCAT(b.kcmc SEPARATOR ';')  AS kcmc,"
                +"  GROUP_CONCAT( b.skdd SEPARATOR ';')  AS jsmc  from"
                +" (select xn,dxq,jsgh,xm,xq,dyzs,dsz,jc,kcbh,kcmc,jsbh,skdd,zdmc,px "
				+" from(select zdz,zdmc,px from sys_sjzd where zl='mzxq' and jb='2') a left join "
				+" (SELECT  xn, dxq, jsgh, xm, xq, dyzs, dsz, jc, kcbh, kcmc, jsbh, skdd "
				//+ " FROM zs_jskb where 1=1 "+str+") b on a.zdmc=b.xq GROUP BY a.zdmc order by a.px ";
				+" FROM zs_jskb where 1=1 "+str+") b on a.px=b.xq "
				+" group by zdmc,xn,dxq,jsgh,xq,dyzs,jc,kcbh,skdd ) b GROUP BY b.zdmc order by b.px ";
		return jdbcTemplate.queryForList(sql);
	}
}
