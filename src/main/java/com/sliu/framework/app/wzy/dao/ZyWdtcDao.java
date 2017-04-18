package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wzy.model.ZyWdtc;

/**
 * 主页--我的吐槽
 * @author duanpeijun
 * @version 创建时间：2015年6月8日  下午4:32:30
 */
@Repository
public class ZyWdtcDao extends HibernateBaseDaoImpl<ZyWdtc, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 查询吐槽列表
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月8日  下午4:35:46
	 * @param start
	 * @param limit
	 * @param tcbt   吐槽标题
	 * @param zt     状态
	 * @return
	 */
	public Pagination<Map<String,Object>> getWdtcList(Integer start, Integer limit,String tclx) {

		String str="";
		if(tclx!=null&&!"".equals(tclx)){
			str=str+" and a.tclx ='"+tclx+"'";
		}
		
		String sql = "SELECT a.id,a.tcbt,a.tczs,a.tcnr,DATE_FORMAT(a.tcsj,'%Y-%m-%d %H:%i:%S') as tcsj,"
			     + "DATE_FORMAT(a.gqsj,'%Y-%m-%d %H:%i:%S') as gqsj,a.tcr,a.tcrxm,a.bmmc,a.bmbh,"
			     + "a.zt, a.tclx FROM zy_wdtc a left join (SELECT id, zdbm, zdmc, zdz, jb, zl,px "
			     + "FROM sys_sjzd where zl='tclx' and jb=2) b on a.TCLX = b.zdz where 1=1 "+str;
		sql += " order by a.zt asc";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 *  获取字典表中的吐槽类型
	 * @author duanpeijun
	 * @date 2015年8月7日
	 * @return
	 */
	public List<Map<String,Object>> getLx(){
		String sql="SELECT zdmc, zdz FROM sys_sjzd where zl='tclx' and jb=2";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询我的吐槽
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月8日  下午6:19:01
	 * @return
	 */
	public List<Map<String, Object>> getCx(String pages){
		String str="";
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			int num1=(Integer.parseInt(pages)-1)*10;
			str=str+" order by a.tcsj DESC limit "+num1+","+num+" ";
		}
		
		String sql = " select a.id, a.tcbt, a.tczs, a.tcnr, DATE_FORMAT(a.tcsj,'%Y-%m-%d') as tcsj, a.gqsj, a.tcr, a.tcrxm, a.bmmc, a.bmbh, a.zt, a.bzcs, a.hfcs, a.txdz from "
				+ "ZY_WDTC a where a.tclx='1' "+str;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 话题：吐槽的数量
	 * @author duanpeijun
	 * @date 2015年7月14日
	 * @return
	 */
	public List<Map<String, Object>> getHuati(){
		
		String sql = "select count(*) size from zy_wdtc where tclx='1' ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	
	/**
	 * 管理员删除吐槽
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月9日  上午9:40:39
	 * @param id
	 */
	public void deleteWdtc(Long id){
		
		String sql = "delete from zy_wdtc where id ="+id;
		jdbcTemplate.execute(sql);
		
	}
	
	/**
	 * 前台删除吐槽回复
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param id
	 */
	public void deleteHf(Long id){
		
		String sql = "delete from zy_tchf where tczj ="+id;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 前台删除吐槽被赞
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param id
	 */
	public void deleteBz(Long id){
		
		String sql = "delete from zy_tcbz where tczj ="+id;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 查询表白墙
	 * @author duanpeijun
	 * @date 2015年7月13日
	 * @return
	 */
	public List<Map<String, Object>> getBbq(String pages){
		String str="";
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			int num1=(Integer.parseInt(pages)-1)*10;
			str=str+" order by a.tcsj DESC limit "+num1+","+num+" ";
		}
		
		String sql = " select a.id, a.tcbt, a.tczs, a.tcnr, DATE_FORMAT(a.tcsj,'%Y-%m-%d') as tcsj, a.gqsj, a.tcr, a.tcrxm, a.bmmc, a.bmbh, a.zt, a.bzcs, a.hfcs, a.txdz from"
				+ " ZY_WDTC a where a.tclx = 2 "+str;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * @author  wangxiangyang
	 * @version 创建时间：2016年9月29日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getBbqList(Integer start, Integer limit){
		
		String sql = " select a.id, a.tcbt, a.tczs, a.tcnr, DATE_FORMAT(a.tcsj,'%Y-%m-%d') as tcsj, a.gqsj, a.tcr, a.tcrxm, a.bmmc, a.bmbh, a.zt, a.bzcs, a.hfcs, a.txdz from"
				   + " ZY_WDTC a where a.tclx = 2 ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 
	 * @author wangxiangyang
	 * @date 2016年9月8日
	 * @return
	 */
	public List<Map<String, Object>> getYh(String openId){
		
		String sql = " select a.id, a.yhid, a.wxh, b.dlm, b.yhbh, b.xm from"
				+ " sys_wxyh a left join sys_yh b on b.yhbh = a.yhid where a.wxh = '"+openId+"'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 根据字典种类找出字典
	   @author:wangchunlin 
	 * @version 创建时间：2016年1月21日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByZt(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}

	
	
}
