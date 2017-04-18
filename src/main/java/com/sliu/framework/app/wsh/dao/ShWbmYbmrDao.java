package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWbmybmr;

@Repository
public class ShWbmYbmrDao extends HibernateBaseDaoImpl<ShWbmybmr, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据报名编号获取报名人数
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 * @return
	 */
	public int getCount(String bmid){
		String sql="select id,openid,bmid,bz from sh_wbmybmr where bmid="+bmid;
		return jdbcTemplate.queryForList(sql).size();
	}

	/**
	 * 根据用户的openId获取已报名人的信息
	 * @author liujiasen
	 * @date 2015年6月29日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getBmr(String openId) {
		if("".equals(openId)){
			openId="0";
		}
		String sql="select id,openid,bmid,bz from sh_wbmybmr where openid='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
}
