package com.sliu.framework.app.wyx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsXscjDao;
import com.sliu.framework.app.wfw.model.ZsWljf;
import com.sliu.framework.app.wyx.model.WyxYxgg;

/**
 * 迎新公告
 * @author wangxiangyang
 * @date  2016年8月9日
 */
@Repository
public class WyxYxggDao extends HibernateBaseDaoImpl<WyxYxgg, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 迎新公告列表
	 * @author wangxiangyang
	 * @date 2016年8月9日
	 * @param str
	 * @param user
	 * @return
	 */	
	public List<Map<String,Object>> getYxggList(String openId){
		
		String sql = "select id,title,fbtime from zs_yx_gg";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 迎新公告列表
	 * @author wangxiangyang
	 * @date 2016年8月9日
	 * @param str
	 * @param user
	 * @return
	 */	
	public List<Map<String,Object>> get1(Long id){
		
		String sql = "select id,title,fbtime,type,content,ly,author from zs_yx_gg "
				   + "where id="+id;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 
	 * @author:wangxiangyang
	 * @version 创建时间：2016年8月10日  
	 * @param start
	 * @param limit
	 * @param type   信息类型
	 * @param title   信息标题
	 * @return
	 */
	public Pagination<Map<String,Object>> getyxList(Integer start, Integer limit,String type,String title) {

		String sql = "select a.id,a.title,a.content,DATE_FORMAT(a.fbtime,'%Y-%m-%d %H:%i:%S') as fbtime,a.author,a.ly,a.type from zs_yx_gg a "
				+ " where 1=1";
		if (StringUtils.hasText(type)) {
			sql += " and a.type = '"+type+"'";
		}
		if (StringUtils.hasText(title)) {
			sql += " and a.title = '"+title+"'";
		}
		
		sql += " order by a.fbtime desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 	
	 * @author:wangxiangyang
	 * @version 创建时间：2016年8月10日
	 * @param zdmc  字段名称	
	 * @param zdz     字段值
	 * @return
	 */
		public List<Map<String, Object>> getTjcx(String zdmc,Integer zdz){
			SysYh user=AppUtil.getCurrentUser();
			String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl="+"'xyxx'"+" and a.jb ="+"'2'";
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
		 * 删除
		 * @author   wangxiangyang
		 * @version 创建时间：2015年6月11日  
		 * @param id
		 */
		public void deleteYxgg(Long id){
			String sql = "delete from zs_yx_gg where id="+id;
					jdbcTemplate.execute(sql);
		}

}
