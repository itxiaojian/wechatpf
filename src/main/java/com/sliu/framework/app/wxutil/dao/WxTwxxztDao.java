package com.sliu.framework.app.wxutil.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wxutil.model.WxTwxxzt;



/**
 * 图文消息主体
 * @author : zhangyi
 * @version 创建时间：2016年9月2日 下午5:27:05
 */
@Repository
public class WxTwxxztDao  extends HibernateBaseDaoImpl<WxTwxxzt, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	

	/**
	 * 获取图文消息主体
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月6日 下午2:26:02 
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTwxxztList(Integer start,Integer limit) {
		String sql = " select t.id,t.xxbt,t.tjsj,t.tjrbh,t.tjrxm,t.xxsm from WX_TWXXZT t ";
		return jdbcPager.queryPage(sql, start, limit);
	}

	
	/**
	 * 根据ztid 查询图文信息
	 * @author chenhui
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getTwxxbt(int id){
		String sql=  "select t.id,t.ztid,t.xxbt,t.xxnr,t.tjsj,t.sltid,t.px,t.id from wx_twxxnr t where 1=1 and t.ztid="+id;
		return jdbcTemplate.queryForList(sql);
	}
}
