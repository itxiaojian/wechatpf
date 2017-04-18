package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysAdpz;

/**
 * 设备管理Dao
 * @author : zhangyi
 * @version 创建时间：2015年8月20日 上午9:28:45
 */
@Repository
public class SysAdpzDao extends HibernateBaseDaoImpl<SysAdpz, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取列表
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:11:21 
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Object> getList(Integer start, Integer limit) {
		String rowHql = "from SysAdpz t";
		String countHql = "select count(t.id) from SysAdpz t";
		return this.findPageByHQL(rowHql, countHql, start, limit);
	}

}
