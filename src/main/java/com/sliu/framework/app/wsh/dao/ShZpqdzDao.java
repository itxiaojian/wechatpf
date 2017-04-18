package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShZpqdz;

/**
 * 吐槽被赞
 * @author Administrator
 *
 */
@Repository
public class ShZpqdzDao extends HibernateBaseDaoImpl<ShZpqdz, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 判断吐槽是否被当前用户赞
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月18日 上午9:28:59
	 * @param id
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getZanlist(Long id,String openId){
		String sql = "select a.id from sh_zpqdz a where a.tczj = "+id+" and a.zr='"+openId+"'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
}
