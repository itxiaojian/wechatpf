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
import com.sliu.framework.app.wzy.model.ZyCdpz;


@Repository
public class ZyCdpzDao extends HibernateBaseDaoImpl<ZyCdpz, Long> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
//	private String sfqy;
	/**
	 * 查询菜单
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	public Pagination<Map<String, Object>> getList(Integer start, Integer limit,String mklx) {
		String str = "";
		if(StringUtils.hasText(mklx)){
			str=" and a.mklx='"+mklx+"'";
		}
		String sql=" select a.id,a.cdmc,a.tjr,a.cdtbmc,a.cdurl,a.sfqy,a.px,DATE_FORMAT(a.tjsj,'%Y-%m-%d %H:%i:%S') as tjsj,a.mklx from ZY_ZYCDPZ a where 1=1 "+str+" order by a.px";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 查询角色菜单
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	public Pagination<Map<String, Object>> getJsCdList(String mklx,String jsmc,Integer start, Integer limit) {
		String str = "";
		if(StringUtils.hasText(mklx)){
			str=str+" and a.mklx='"+mklx+"'";
		}
		
		String sql=" select a.id,a.cdmc,a.tjr,a.cdtbmc,a.cdurl,a.sfqy,a.px,DATE_FORMAT(a.tjsj,'%Y-%m-%d %H:%i:%S') as tjsj,a.mklx,"
				+ "b.jsmc,b.id as pzid,b.cdid, case WHEN b.jsmc='"+jsmc+"' then '1' when b.jsmc !='"+jsmc+"' || b.jsmc is NULL then '0' end as pzzt "
				+ "from ZY_ZYCDPZ a left join (select t.id,t.jsmc,t.cdid from zy_jscdpz t where t.jsmc='"+jsmc+"') b on a.id=b.CDID "
				+ "where a.SFQY='1' "+str+" and (b.jsmc='"+jsmc+"' or b.jsmc is null) order by a.px";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 查询角色列表
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	public Pagination<Map<String, Object>> getJsList(Integer start, Integer limit,String mklx) {
		String sql=" select t.jsbh,t.jsmc,t.bz,t.jszt from sys_js t where t.jszt='1' order by t.bz";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 根据字典种类找出字典
	   @author:zhangyi 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
   /**
    * 根据类型和角色获取相应菜单
    * @author:zhangyi 
    * @version 创建时间：2016年1月13日 上午11:09:08 
    * @return
    */
	public List<Map<String, Object>> getListMenu(String mklx,String jsmc) {
		String sql = "select t.cdmc, t.id,t.cdtbmc,t.cdurl,t.px from zy_zycdpz t left join ZY_JSCDPZ tt on t.id=tt.cdid where t.sfqy='1' and t.mklx='"+mklx+"' "
				+ "and tt.jsmc='"+jsmc+"' order by t.px asc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	/**
	 * 根据类型和角色集获取相应菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月29日 下午12:00:37 
	 * @param mklx
	 * @param jsmc
	 * @return
	 */
	public List<Map<String, Object>> getAllListMenu(String mklx, String jsmc) {
		String sql = "select distinct t.cdmc, t.id,t.cdtbmc,t.cdurl,t.px from zy_zycdpz t left join ZY_JSCDPZ tt on t.id=tt.cdid where t.sfqy='1' and t.mklx='"+mklx+"' "
				+ "and tt.jsmc in ("+jsmc+") order by t.px asc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

}
