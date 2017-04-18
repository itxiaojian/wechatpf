package com.sliu.framework.app.wxutil.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wxutil.model.WxToken;

/**
 * 微会议
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:03
 */
@Repository
public class WxTokenDao extends HibernateBaseDaoImpl<WxToken, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询
	 * @author  chenhui
	 * @version 创建时间：2015年6月3日  下午3:48:54
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTokenList(Integer start, Integer limit){
		
		String sql = " select a.id, '************************************' as token, a.expires_in,a.create_time  from WX_TOKEN a  ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	/**
	 * 删除
	 * @author chenhui
	 * @version 创建时间：2015年6月3日  下午3:49:13
	 * @param id  主键ID
	 */
	public void deleteToken(Long id){
		String sql = "delete from wx_token where id="+id;
				jdbcTemplate.execute(sql);
	}
}
