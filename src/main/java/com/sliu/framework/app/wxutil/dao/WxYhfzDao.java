package com.sliu.framework.app.wxutil.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wxutil.model.WxYhfz;

/**
 * 微用户分组
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:03
 */
@Repository
public class WxYhfzDao extends HibernateBaseDaoImpl<WxYhfz, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 查询自己发起的列表
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月23日 下午4:27:40 
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> ownerpageList(Integer start, Integer limit) {
		SysYh user = AppUtil.getCurrentUser();
		String sql = " select t.id,t.fzmc,DATE_FORMAT(t.tjsj,'%Y-%m-%d %H:%i:%S') as tjsj,t.bz from wx_yhfz t "
				+ "where 1=1 and t.cjr='"+user.getUsername()+"' order by t.tjsj desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}

	/**
	 * 获取自己的分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月26日 下午3:05:03 
	 * @return
	 */
	public List<Map<String, Object>> getowerlist() {
		SysYh user = AppUtil.getCurrentUser();
		String sql = " select t.id,t.fzmc as name from wx_yhfz t "
				+ "where 1=1 and t.cjr='"+user.getUsername()+"' order by t.tjsj desc";
		return jdbcTemplate.queryForList(sql);
	}

}
