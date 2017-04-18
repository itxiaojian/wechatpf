package com.sliu.framework.app.bx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;

/**
 * 报修审批意见
 * @author liujiansen
 * @date 2015年8月6日
 */
@Repository
public class BxBxspyjbDao extends HibernateBaseDaoImpl<BxBxspyjb, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取审批信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getSpxx(String yhbh) {
		String sql="SELECT a.id, a.bxid, a.sprbh, a.sprxm,d.jsmc,d.bz as js, a.sftg, a.spyj, DATE_FORMAT(a.spsj,'%Y-%m-%d %H:%i') as spsj, a.zt,a.bz FROM bx_bxspyjb a "
				+ "left join sys_yh b on a.sprbh=b.yhbh left join  (select min(jsbh) as jsbh,yhbh from sys_yhjs group by yhbh) c on b.yhbh=c.yhbh left join sys_js d on c.jsbh=d.jsbh order by a.spsj desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取申请信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getSq(String yhbh) {
		String str="";
			List<Map<String,Object>> yh=this.getYh(yhbh);
			if(yh.size()!=0){
				String js=(yh.get(0).get("jsmc")+"").trim();
				if(!"ROLE_BXSH".equals(js)){
					//非报修审核角色
					str=str+" and xgh='"+yh.get(0).get("dlm")+"'";
				}
			}
		String sql="SELECT id, bxbh, xgh, lxhm, wxdl,sqrxm, xq, ly, lh, case when fw='1' then '教材设备' else '维修' end as fw, "
				+ "DATE_FORMAT(yysj,'%Y-%m-%d %H:%i') as yysj, sbztbh, sbztmc, dz, nr, zt, pj, bz FROM bx_bxsq where 1=1 "+str+" order by yysj desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户信息
	 * @author liujiansen
	 * @date 2015年8月10日
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
	 * 根据Id获取申请信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getSqxx(String id) {
		String sql="SELECT id, bxbh, xgh, wxdl,lxhm, sqrxm, xq, ly, lh, case when fw='1' then '教材设备' else '维修' end as fw, "
				+ "DATE_FORMAT(yysj,'%Y-%m-%d %H:%i') as yysj, sbztbh, sbztmc, dz, nr, zt, pj, bz FROM bx_bxsq where id="+id+" order by yysj desc";
		return jdbcTemplate.queryForList(sql);
	}
}
