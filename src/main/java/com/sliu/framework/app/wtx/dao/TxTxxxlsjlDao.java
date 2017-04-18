package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;

/**
 * 提醒信息历史记录
 * @author liujiansen
 * @date 2015年7月29日
 */
@Repository
public class TxTxxxlsjlDao extends HibernateBaseDaoImpl<TxTxxxlsjl, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 分页查询信息数据
	 * @author liujiasen
	 * @date 2015年5月19日
	 * @param start
	 * @param limit
	 * @param txlx 提醒类型
	 * @return
	 */
	public Pagination<Map<String,Object>> pager(Integer start, Integer limit,String txlx) {
		String str="";
		if(txlx!=null&&!"".equals(txlx)){
			str=str+" and a.txlx ='"+txlx+"'";
		}
		String sql = "SELECT a.id, DATE_FORMAT(a.txsj,'%Y-%m-%d %H:%i:%S') as txsj,a.txnr,a.txdx,a.openid,"
				+ "c.nickname,a.txlx,b.zdmc, a.bz FROM tx_txxxlsjl a left join (SELECT id, zdbm, zdmc, zdz, jb, zl, "
				+ "px FROM sys_sjzd where zl='txlx' and jb=2) b on a.txlx=b.zdz  left join wx_user_info c on a.openid=c.openid where 1=1 "+str;
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 获取字典表中的提醒类型
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @return
	 */
	public List<Map<String,Object>> getLx(){
		String sql="SELECT zdmc, zdz FROM sys_sjzd where zl='txlx' and jb=2";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户的微信号和用户编号
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @return
	 */
	public List<Map<String,Object>> getWxyh(String wxh){
		String sql="select b.yhbh,b.xm,a.wxh  from sys_wxyh a ,sys_yh b "
				+ " where a.yhid=b.yhbh and b.yhzt='1' and a.zt='1' and  wxh='"+wxh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 个人提醒的历史的记录
	 * @author oufeng
	 * @date 2016年8月29日
	 * @return
	 */
	public List<Map<String,Object>> getTxlsjl(String yhbh,String openId,String pages){
		String str="";
		String str1="";
		if(yhbh!=null&&!"".equals(yhbh)){
			str+=" and a.txdx='"+yhbh+"'";
		}else{
			str+=" and 1=2";
		}
		if(openId!=null&&!"".equals(openId)){
			str+=" and a.openId='"+openId+"'";
		}else{
			str+=" and 1=2";
		}
		if (Integer.parseInt(pages) == 1) {
			int num = Integer.parseInt(pages) * 10;
			str1  += " ORDER BY a.txlx,a.txsj DESC limit " + num + " ";
		} else {
			int num = (Integer.parseInt(pages) - 1) * 10;
			str1 += " ORDER BY a.txlx,a.txsj DESC limit " + num + ",10";
		}
		String sql="SELECT  a.txdx,a.openId,a.txlx,c.zdmc,"
				+ " date_format(a.txsj,'%Y-%m-%d %H:%i:%s') as txsj,a.txnr,b.xm "
				+ " FROM tx_txxxlsjl a ,sys_yh b,sys_sjzd c  "
				+"  where 1=1 "
				+"  and a.txdx = b.yhbh "
				+"  and a.txlx=c.zdz "
				+"  and c.zl='txlx'"
				+"  and c.jb=2"
				+str
				+ " GROUP BY a.txdx,a.openId,a.txlx,a.txsj,a.txnr "
				+str1;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户的提醒
	 * @author oufeng
	 * @date 2015年7月29日
	 * @return
	 */
	public List<Map<String,Object>> getTxlx(String role){
		String str="";
		if("ROLE_STUDENT".equals(role)){
			str+=" and zdz in('2','7','8')";
		}else if("ROLE_TEACHER".equals(role) 
				|| "ROLE_INSTRUCTOR".equals(role) || "ROLE_LEADER".equals(role)){
			str+=" and zdz in('2','6')";
		}else if ("".equals(role) || "ROLE_ADMIN".equals(role)){
			str+= " and zdz in('1','2','3','4','5','6','7','8','9')";
		}
		String sql="select zdz,zdmc from sys_sjzd where zl='txlx' and jb=2 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取用户的角色
	 * @author oufeng
	 * @date 2015年7月29日
	 * @return
	 */
	public List<Map<String,Object>> getRole(String openId){
		String sql="SELECT d.jsmc FROM sys_yh a,sys_wxyh b,sys_yhjs c,sys_js d"
				+" WHERE a.yhbh= b.yhid "
				+" AND a.yhbh=c.yhbh "
				+" AND c.jsbh= d.jsbh"
			    +" AND b.wxh='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取用户的提醒
	 * @author oufeng
	 * @date 2015年7月29日
	 * @return
	 */
	public void updateGrtx(String openId,String lx,String zt){
		String sql="";
		if("0".equals(zt)){
			sql="update sys_wxyh set bz=NULL  where wxh='"+openId+"'";
		}else if("1".equals(zt)){
			sql="update sys_wxyh set bz='"+lx+"'  where wxh='"+openId+"'";
		}
		jdbcTemplate.execute(sql);
	}
}
