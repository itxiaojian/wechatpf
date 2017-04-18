package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wzy.model.ZyTcbz;

/**
 * 吐槽被赞
 * @author Administrator
 *
 */
@Repository
public class ZyTcbzDao extends HibernateBaseDaoImpl<ZyTcbz, Long> {

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
		
	String sql = "select a.id from zy_tcbz a where a.tczj = "+id+" and a.zr='"+openId+"'";
	List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
	return list;
	}
	
	/**
	 * 赞数量
	 * @author duanpeijun
	 * @date 2015年7月14日
	 * @return
	 */
	public List<Map<String, Object>> getZan(){
		
		String sql = " select count(*) size from zy_tcbz b LEFT JOIN zy_wdtc a on a.ID = b.TCZJ WHERE a.tclx = 1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
}
