package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsjc;

@Repository
public class ZsXsjcDao extends HibernateBaseDaoImpl<ZsXsjc, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 分页查询信息数据
	 * @author liujiasen
	 * @date 2015年5月19日
	 * @param start
	 * @param limit
	 * @param xm
	 * @param wxh
	 * @return
	 */
	/*public Pagination<Map<String,Object>> getXscjList(Integer start, Integer limit,String xm,String wxh) {
		String str="";
		if(xm!=null&&!"".equals(xm)){
			str=str+" and b.xm like '%"+xm+"%'";
		}
		if(wxh!=null&&!"".equals(wxh)){
			str=str+" and a.wxh like '%"+wxh+"%'";
		}
		String sql = "SELECT id, ksxn, ksxq, xh, kskm, kcbh, kscj, sftg, bkcj, xf, bz FROM zs_xscj where 1=1 "+str;
		return jdbcPager.queryPage(sql, start, limit);
	}*/
	
	/**
	 * 按学年和学期获取当前登录人的奖惩数据
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getGrjc(String ksxn,String ksxq,String openId,String pages){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
		}
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and ksxq = '"+ksxq+"'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by ksxn DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by ksxn DESC limit "+num+",5";
			}
		}
		String sql="select id,ksxn,ksxq,xh,xm,jclx,jcjg,jcsy,mc,je,bz from zs_xsjc where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的最新奖惩数据的学年
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public Map<String,Object> getNewXn(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select max(ksxn) as ksxn from zs_xsjc where 1=1 "+str;
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取当前登陆人的最新奖惩的学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @return
	 */
	public Map<String,Object> getNewXq(String ksxn,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select max(ksxq) as ksxq from zs_xsjc where 1=1 "+str+" and ksxn='"+ksxn+"'";
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取个人奖惩数据的学年学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getJcqh(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select ksxn,ksxq from zs_xsjc where 1=1 "+str+" group by KSXN,KSXQ order by KSXN desc,KSXQ asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据ID获取奖惩信息明细
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @param id
	 * @return
	 */
	public Map<String,Object> getMx(String id){
		String sql="select a.id,a.ksxn,a.ksxq,a.xh,a.xm,a.jcjg,a.jcsy,a.mc,a.je,a.bz from zs_xsjc a "
				+ "where id='"+id+"'";
		return jdbcTemplate.queryForMap(sql);
	}
}
