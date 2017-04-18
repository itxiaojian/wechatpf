package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXskqxx;

/**
 * 学生考勤信息
 * @author chenhui
 * @date  2015年6月19日
 */
@Repository
public class ZsXskqxxDao extends HibernateBaseDaoImpl<ZsXskqxx, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;

	
	/**
	 * 學生考勤信息
	 * @author chenhui
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getZsxskqxxList(String pages,String code,String openId){
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String str="";
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		if (Integer.parseInt(pages) == 1) {
			int num = Integer.parseInt(pages) * 10;
			str = str + " order by a.sfkq  limit " + num + " ";
		} else {
			int num = (Integer.parseInt(pages) - 1) * 10;
			str = str + " order by a.sfkq  limit " + num + ",10";
		}
		String sql="select a.id,a.xsxh,a.xsxm,a.bjbh,a.bjmc,a.kcbh,a.kcmc,a.skrq,a.skdd,a.sfkq,a.lsgh,a.bz from zs_xskq a  where 1=1 and xsxh='"+thisdlm+"'"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 辅导员获取学生考勤列表
	 * @author chenhui
	 * @date  2016年8月11日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getXskqyjList(String bjmc,String pages,String code,String openId){
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String str1 = "";
		if (bjmc != null && !"".equals(bjmc)) {
			str1 = str1 + "where b.bjmc like '%" + bjmc + "%'";
		}
		String str = "";
		if (Integer.parseInt(pages) == 1) {
			int num = Integer.parseInt(pages) * 10;
			str = str + " order by qql DESC limit " + num + " ";
		} else {
			int num = (Integer.parseInt(pages) - 1) * 10;
			str = str + " order by qql DESC limit " + num + ",10";
		}
		String jssql = "select b.jsbh,b.jsmc from sys_yh t left join sys_yhjs a on t.yhbh=a.YHBH left join sys_js b on a.jsbh=b.jsbh where t.yhbh='" + user.get(0).get("yhid").toString() + "'";
		List<Map<String, Object>> listjs = jdbcTemplate.queryForList(jssql);
		String sql = "select a.id,a.xsxh,a.xsxm,a.bjbh,a.bjmc,a.kcbh,a.kcmc,a.skrq,a.skdd,a.sfkq,a.lsgh,a.bz from zs_xskq a  where 1=1 and xsxh='"+thisdlm+"'";
		if (listjs.size() > 0) {
			// String jsbhString = "ROLE_INSTRUCTOR";
			for (Map<String, Object> map : listjs) {
				String jsbh = map.get("jsmc").toString();
				if ("ROLE_INSTRUCTOR".equalsIgnoreCase(jsbh)) {
				sql = " select a.xsxh,a.xsxm,a.zs,a.bjmc,a.bjbh,ROUND(a.wqq/a.zs*100,2) as qql from(SELECT"
                      +" b.xsxm as xsxm ,b.xsxh as xsxh,b.bjmc as bjmc,b.bjbh as bjbh,count(b.sfkq) as zs,count(case when b.sfkq='0' then b.xsxh end) as wqq from zs_xskq b "+str1
                      +" GROUP BY b.xsxh) a  LEFT JOIN (SELECT b.xsxh as xsxh,b.bjbh as bjbh,b.xsxm as xsxm ,b.bjmc as bjmc,"
                      +" count(b.sfkq) as zs ,count(case when b.sfkq='0' then b.xsxh end) as wqq,count(case when b.sfkq='1' then b.xsxh end) as qq"
                      +" from  zs_xskq b "+str1
                      + " GROUP BY b.xsxh) b ON a.xsxh=b.xsxh AND b.bjbh in (select a.classid as bjbh from fdy_and_bj a where a.loginid='" + thisdlm
							+ "')"+str;
				}

			}
		}
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据id查看学生考勤
	 * @author chenhui
	 * @date 2016年8月25日
	 * @return
	 */
	public List<Map<String, Object>> getQuery(Long id,String openId){
		
		String sql="select a.id,a.xsxh,a.xsxm,a.bjbh,a.bjmc,a.kcbh,a.kcmc,a.skrq,a.skdd,a.sfkq,a.lsgh,a.bz from zs_xskq a  where 1=1  and a.id='"+ id +"' ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取班级
	 * @author 班级
	 * @date 2015年8月07日
	 * @return
	 */
	public List<Map<String,Object>> getBj(String openId){
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String sql="select t.classname from fdy_and_bj t where t.loginid='" + thisdlm+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 学生考勤基本信息
	 * @author chenhui
	 * @date 2016年8月25日
	 * @return
	 */
	public List<Map<String, Object>> getXs(String openId){
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String sql="select (select count(sfkq) from zs_xskq d where sfkq ='0' and d.xsxh='"+thisdlm
				+ "')as qq,COUNT(sfkq)as cq,"
				+ "ROUND((select count(sfkq) from zs_xskq b where sfkq ='0'and b.xsxh='"+thisdlm
				+ "')/(select COUNT(sfkq) from zs_xskq c where c.xsxh='"+thisdlm+"')"
				+ "*100, 2) as qql,"
				+ "a.id,a.xsxh,a.xsxm,a.bjbh,a.bjmc,a.kcbh,a.kcmc,a.skrq,a.skdd,a.sfkq,a.lsgh,a.bz from zs_xskq a  where 1=1 AND a.xsxh='"+thisdlm+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 辅导员获得学生考勤基本信息
	 * @author chenhui
	 * @date 2016年8月25日
	 * @return
	 */
	public List<Map<String, Object>> getXsxx(String xh){

		String sql="select (select count(sfkq) from zs_xskq d where sfkq ='0' and d.xsxh='"+xh
				+ "')as qq,COUNT(sfkq)as cq,"
				+ "ROUND((select count(sfkq) from zs_xskq b where sfkq ='0'and b.xsxh='"+xh
				+ "')/(select COUNT(sfkq) from zs_xskq c where c.xsxh='"+xh+"')"
				+ "*100, 2) as qql,"
				+ "a.id,a.xsxh,a.xsxm,a.bjbh,a.bjmc,a.kcbh,a.kcmc,a.skrq,a.skdd,a.sfkq,a.lsgh,a.bz from zs_xskq a  where 1=1 and a.xsxh='"+xh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 辅导员获得学生考勤基本信息
	 * @author chenhui
	 * @date 2016年8月25日
	 * @return
	 */
	public List<Map<String, Object>> getxskqxxListDetail(String xh){

		String sql="select a.id,a.xsxh,a.xsxm,a.bjbh,a.bjmc,a.kcbh,a.kcmc,a.skrq,a.skdd,a.sfkq,a.lsgh,a.bz from zs_xskq a  where 1=1 and xsxh='"+xh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据字典种类找出字典
	   @author: chenhui 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
}
