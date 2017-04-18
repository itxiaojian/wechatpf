package com.sliu.framework.app.bx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;

/**
 * 报修申请
 * @author liujiansen
 * @date 2015年8月6日
 */
@Repository
public class BxBxsqDao extends HibernateBaseDaoImpl<BxBxsq, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取维修的大类
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @return
	 */
	public List<Map<String,Object>> getWxDl(){
		String sql="SELECT id, ztmc, ztjb, sjzt, tjsj, bz, px FROM bx_bxzt where ztjb='2' order by px";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据上级主题编号获取维修的小类
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @param sjzt 上级主题编号
	 * @return
	 */
	public List<Map<String,Object>> getWxxl(String sjzt){
		String sql="SELECT id, ztmc, ztjb, sjzt, tjsj, bz, px FROM bx_bxzt where sjzt='"+sjzt+"' order by px";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取维修楼宇
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @return
	 */
	public List<Map<String,Object>> getLy(){
		String sql="SELECT zdbm, zdz FROM sys_sjzd where zl='lylx' and jb='2' order by px asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据openId获取绑定用户的信息
	 * @author liujiansen
	 * @date 2015年8月7日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getUser(String openId){
		String sql="SELECT a.wxnc, a.wxh,b.xm,b.dlm,a.yhid FROM sys_wxyh a "
				+ " left join sys_yh b on a.yhid=b.yhbh where a.zt='1' and a.wxh='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取审批信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getSpxx(String openId) {
		String sql="SELECT a.id, a.bxid, a.sprbh, a.sprxm,d.jsmc,d.bz as js, a.sftg, a.spyj, DATE_FORMAT(a.spsj,'%Y-%m-%d %H:%i') as spsj, "
				+ " a.zt,a.bz FROM bx_bxspyjb a "
				+ " left join sys_yh b on a.sprbh=b.yhbh left join  (select min(jsbh) as jsbh,yhbh "
				+ " from sys_yhjs group by yhbh) c on b.yhbh=c.yhbh "
				+ " left join sys_js d on c.jsbh=d.jsbh order by a.spsj desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取申请信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getSq(String openId) {
		String str="";
		List<Map<String,Object>> user=this.getUser(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=this.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				String js=(yh.get(0).get("jsmc")+"").trim();
				if(!"ROLE_BXSH".equals(js)){
					//非报修审核角色
					str=str+" and xgh='"+yh.get(0).get("dlm")+"'";
				}
			}
		}
		String sql="SELECT id, bxbh, xgh, lxhm, sqrxm, wxdl,xq, ly, lh, case when fw='1' then '教材设备' else '维修' end as fw, "
				+ " DATE_FORMAT(yysj,'%Y-%m-%d %H:%i') as yysj, sbztbh, sbztmc, dz, nr, zt, pj, bz FROM bx_bxsq "
				+ " where 1=1 "+str+" order by yysj desc";
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
				+ " left join sys_yhjs b on a.yhbh=b.yhbh left join sys_js c on b.jsbh=c.jsbh where 1=1 "+str;
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
		String sql="SELECT id, bxbh, xgh, lxhm, sqrxm, xq, ly, lh, wxdl,case when fw='1' then '教材设备' else '维修' end as fw, "
				+ " DATE_FORMAT(yysj,'%Y-%m-%d %H:%i') as yysj, sbztbh, sbztmc, dz, nr, zt, pj, bz FROM bx_bxsq where id="
				+id+" order by yysj desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 检查是否重复申请
	 * @author liujiansen
	 * @date 2015年8月13日
	 * @param sq
	 * @return
	 */
	public int checkSave(BxBxsq sq){
		String sql="SELECT id, bxbh, xgh, lxhm from bx_bxsq where xgh='"+sq.getXgh()
				+"' and ly='"+sq.getLy()+"' and lh='"+sq.getLh()+"' "
				+ " and sbztbh='"+sq.getSbztbh()+"' and sbztmc='"+sq.getSbztmc()+"'";
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 *获得评价
	 * @author oufeng
	 * @date 2015年9月17日
	 * @param sq
	 * @return
	 */
	public List<Map<String, Object>>  getPj(String xgh,String yhbh){
		String str="";
		if(xgh!=null&&!"".equals(xgh)){
			str+=" and xgh='"+xgh+"'";
		}
		if(yhbh!=null&&!"".equals(yhbh)){
			String dlm = (String) this.getDlm(yhbh).get(0).get("dlm");
			str+=" and xgh='"+dlm+"'";
		}
		String sql="select id,pj from bx_bxsq where zt =5  "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询有无未完成的处理状态
	 * @author oufeng
	 * @date 2016年3月8日
	 * @return
	 */
	public List<Map<String, Object>> getSfclwc(String xgh,String yhbh){
		String str = "";
	/*	if("admin".equals(yhbh)){
			str += " and a.clzt in ('xyz') and  b.dlm ='" + yhbh + "'";
		}*/
		if(xgh!=null&&!"".equals(xgh)){
			str += " and zt in ('1','2','3','4','6','7')  and  xgh ='" + xgh + "'";
		}
		if(yhbh!=null&&!"".equals(yhbh)){
			String dlm = (String) this.getDlm(yhbh).get(0).get("dlm");
			str += " and zt in ('1','2','3','4','6','7')  and  xgh ='" + dlm + "'";
		}
		String sql = "select id,pj from bx_bxsq where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 *获得登录名
	 * @author oufeng
	 * @date 2015年9月17日
	 * @param sq
	 * @return
	 */
	public List<Map<String, Object>>  getDlm(String yhbh){
		String str="";
		if(yhbh!=null&&!"".equals(yhbh)){
			str+=" and yhbh='"+yhbh+"'";
		}
		String sql="select dlm from sys_yh where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 报修的申请的评价的信息
	 * @author oufeng
	 * @date 2016年3月25日
	 * @param sq
	 * @return
	 */
	public List<Map<String, Object>> getPjById(String id){
		String str="";
		if(id!=null&&!"".equals(id)){
			str+=" and id='"+id+"'";
		}
		String sql="select pj from  bx_bxsq where 1=1"+str;
		return jdbcTemplate.queryForList(sql);
	}
}
