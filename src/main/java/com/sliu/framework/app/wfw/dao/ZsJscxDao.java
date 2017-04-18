package com.sliu.framework.app.wfw.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsJscx;

/**
 * 教室查询
 * @author duanpeijun
 * @date 2015年7月8日
 *
 */
@Repository
public class ZsJscxDao extends HibernateBaseDaoImpl<ZsJscx, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 查询教学楼以及每个教学楼有教室的数量
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @param jxl   教学楼
	 * @return
	 *
	 */
	public List<Map<String, Object>> getJscxList(String jxl){
		String sql = "select lh as  jxl,COUNT(lh) as a from zs_jsxx where lh='"+jxl+"' and lh is not null group by lh";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 查询教学楼以及每个教学楼有教室的数量
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @param jxl   教学楼
	 * @return
	 *
	 */
	public List<Map<String, Object>> getAllJscxList(){
		String sql = "select lh as jxl,COUNT(lh) as a from zs_jsxx  where lh is not null group by lh";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询教室名称
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @param jxlmc  教学楼名称
	 * @return
	 */
	public List<Map<String, Object>> getJscxListByJxl(String jxlmc) {
		String sql = "select a.lh as jxl,a.id,a.jsmc,a.jsbh,a.bz from zs_jsxx a where a.lh='"+jxlmc+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询是否有课
	 * 教学课程查询
	 * @author:zhangyi 
	 * @version 创建时间：2015年7月9日 上午11:02:13 
	 * @param i
	 * @param jsbh
	 * @return
	 */
	public List<Map<String, Object>> getJscxQk(int i, String jsbh,String kcmc,String year,String xq,String week,String dsweek) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		//String date = sdf.format(new Date());
		String str="";
		if(year !=null&&!"".equals(year)){
			str+=" and    a.xn ='"+year+"' ";
		}
		if(xq !=null&&!"".equals(xq)){
			str+= " and a.dxq = '"+xq+"'";
		}
		if(week !=null&&!"".equals(week)){
			str+= " and a.xq='"+week+"' ";
		}
		if(dsweek !=null&&!"".equals(dsweek)){
			int dsweekint =Integer.parseInt(dsweek);
			if(dsweekint % 2 ==0){
				dsweek="双";
			}else{
				dsweek="单";
			}
			str+= "  AND  a.jc =CONCAT(CONCAT('第',"+i+"),'节')" 
		              +"  AND a.dsz LIKE '%"+dsweek+"%'" 
		               +" AND  "+dsweekint+"<= SUBSTRING_INDEX(dyzs,'-',-1) ";
		}
		if(kcmc !=null&&!"".equals(kcmc)){
			str+= " and a.kcmc like '%"+kcmc+"%' ";
		}
		String sql =
				" SELECT a.*,'1' as yk FROM ("
               +"  SELECT a.xn,a.dxq,a.kcmc,a.xq,jsbh,CONCAT(SUBSTRING_INDEX(jc,'-',1),'节') jc,dsz,dyzs FROM zs_jskb a  "
               +"  UNION "
               + " SELECT b.xn,b.dxq,b.kcmc,b.xq,jsbh,CONCAT('第',SUBSTRING_INDEX(jc,'-',-1)) jc,dsz,dyzs FROM zs_jskb b "
               + " ) a " 
               + " WHERE 1=1"
               + " and a.jsbh='"+jsbh+"'"
               +str
               +"  ORDER BY xn,dxq,kcmc,xq,jc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询是否有课
	 * 教学课程查询
	 * @author:zhangyi 
	 * @version 创建时间：2015年7月9日 上午11:02:13 
	 * @param i
	 * @param jsbh
	 * @return
	 */
	public List<Map<String, Object>> getJscxAll(String jsbh,String kcmc,String year,String xq,String week,String dsweek) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		//String date = sdf.format(new Date());
		String str="";
		if(year !=null&&!"".equals(year)){
			str+=" and    a.xn ='"+year+"' ";
		}
		if(xq !=null&&!"".equals(xq)){
			str+= " and a.dxq = '"+xq+"'";
		}
		if(week !=null&&!"".equals(week)){
			str+= " and a.xq='"+week+"' ";
		}
		if(dsweek !=null&&!"".equals(dsweek)){
			str+=" and  '"+dsweek+"' between  CONVERT(SUBSTRING_INDEX(dyzs,'-',1),SIGNED) "
					+ " and CONVERT(SUBSTRING_INDEX(dyzs,'-',-1),SIGNED)";
;
		}
//		if(dsweek !=null&&!"".equals(dsweek)){
//			int dsweekint =Integer.parseInt(dsweek);
//			if(dsweekint % 2 ==0){
//				dsweek="双";
//			}else{
//				dsweek="单";
//			}
//			str+= "  AND  a.jc =CONCAT(CONCAT('第',"+i+"),'节')" 
//		              +"  AND a.dsz LIKE '%"+dsweek+"%'" 
//		               +" AND  "+dsweekint+"<= SUBSTRING_INDEX(dyzs,'-',-1) ";
//		}
		if(kcmc !=null&&!"".equals(kcmc)){
			str+= " and a.kcmc like '%"+kcmc+"%' ";
		}
//		String sql =
//				" SELECT a.*,'1' as yk FROM ("
//               +"  SELECT a.xn,a.dxq,a.kcmc,a.xq,jsbh,CONCAT(SUBSTRING_INDEX(jc,'-',1),'节') jc,dsz,dyzs FROM zs_jskb a  "
//               +"  UNION "
//               + " SELECT b.xn,b.dxq,b.kcmc,b.xq,jsbh,CONCAT('第',SUBSTRING_INDEX(jc,'-',-1)) jc,dsz,dyzs FROM zs_jskb b "
//               + " ) a " 
//               + " WHERE 1=1"
//               + " and a.jsbh='"+jsbh+"'"
//               +str
//               +"  ORDER BY xn,dxq,kcmc,xq,jc";
		String sql =
               "  SELECT a.xn,a.dxq,a.kcmc,a.xq,jsbh,dsz,dyzs,concat(a.JC,'(',a.KCMC,')') as jc FROM zs_jskb a  "
               + " WHERE 1=1"
               + " and a.jsbh='"+jsbh+"'"
               +str
               +"  ORDER BY jc ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询教学教室
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @return
	 */
	/*	public List<Map<String, Object>> getJxjsList(){
		
	String sql = "select b.jsmc,a.dtskxh,a.skdd,a.skrq,a.xq,a.sksj,a.xksj,a.kcmc from zs_jskb a left join sh_jscx b on a.skdd = b.jsbh where 1=1";
		String sql = "select a.id,a.jsmc,a.jsbh,a.jxl,a.bz from sh_jscx a left join zs_jskb b on a.jsbh = b.skdd where 1=1";
	return jdbcTemplate.queryForList(sql);
	}*/
	
}
