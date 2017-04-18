package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.sys.model.SysYhjs;

/**
 * @author:zhangyi
 * @version 创建时间：2015年9月2日 下午4:38:07 类说明
 */
@Repository
public class SysYhjsDao extends HibernateBaseDaoImpl<SysYhjs, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 根据RoleID获取用户
	 * @author liujiansen
	 * @date 2016年2月22日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getYhByJs(String roleId) {
		String sql="select yhbh,jsbh,bz from sys_yhjs where jsbh='"+roleId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
}
