package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wsh.model.ShZpq;


/**
 * 主页--我的吐槽
 * @author wangxiangyang
 * @version 创建时间：2016年9月7日
 */
@Repository
public class ShZpqDao extends HibernateBaseDaoImpl<ShZpq, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 删除
	 * @author   wangxiangyang
	 * @version 创建时间：2016年9月7日
	 * @param id
	 */
	public void deleteZpq(Long id){
		
		String sql = "delete from sh_zpq where id = "+id;
		jdbcTemplate.execute(sql);
		
	}
	
	/**
	 * 
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @return
	 */
	public List<Map<String, Object>> getBbq(String pages){
		String str="";
	 	   if(pages!=null&&!"".equals(pages)){
				int num=Integer.parseInt(pages)*10;
				int num1=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.tcsj DESC limit "+num1+","+num+" ";
			}
		
		String sql = " select a.id, a.tcbt, a.tczs, a.tcnr, DATE_FORMAT(a.tcsj,'%Y-%m-%d') as tcsj, a.gqsj, a.tcr, a.tcrxm, a.bmmc, a.bmbh, a.zt, a.bzcs, a.hfcs, a.txdz,"
				+ "  substring(a.bz1,33,length(a.bz1)-31) as bz1,  substring(a.bz2,33,length(a.bz2)-31) as bz2,"
				+ "  substring(a.bz3,33,length(a.bz3)-31) as bz3,  substring(a.bz4,33,length(a.bz4)-31) as bz4,"
				+ "  substring(a.bz5,33,length(a.bz5)-31) as bz5,  substring(a.bz6,33,length(a.bz6)-31) as bz6 "
				+ "  from sh_zpq a "+str;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 
	 * @author wangxiangyang
	 * @date 2016年9月8日
	 * @return
	 */
	public List<Map<String, Object>> getTP(String openId){
		
		String sql = " select a.id, a.fname, a.content, a.wjlx, a.lxid, a.bz, a.wxh, a.tcsj from"
				+ " sys_fjglzpq a ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
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
	 * 
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @return
	 */
	public List<Map<String, Object>> getWxyh(String openId){
		
		String sql = " select a.id, a.yhid, a.wxh, a.fzid, a.wxnc, a.xb, a.yhyy, a.yhszcs, a.yhszsf, a.yhszgj, a.yhtx, a.cjsj, a.unionid, a.yhbzm ,a.zt,a.bz from"
				+ " sys_wxyh a where a.wxh = '"+openId+"'";
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
	public Pagination<Map<String, Object>> getZpqList(Integer start, Integer limit){
		
		String sql = " select a.id, a.tcbt, a.tczs, a.tcnr, DATE_FORMAT(a.tcsj,'%Y-%m-%d %H:%i:%S') as tcsj, a.gqsj, a.tcr, a.tcrxm, a.bmmc, a.bmbh, a.zt, a.bzcs, a.hfcs, a.txdz,a.bz1,a.bz2,a.bz3,a.bz4,a.bz5,a.bz6 from"
				   + " SH_ZPQ a ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
}
