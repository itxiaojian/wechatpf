package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWxcjjg;

@Repository
public class ShWxcjjgDao extends HibernateBaseDaoImpl<ShWxcjjg, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据时间获取抽奖配置信息
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param time 时间
	 * @return
	 */
	public List<Map<String,Object>> getWxcj(String time){
		String sql="select id,cjcs,ydjmc,ydjsl,edjmc,rdjsl,DATE_FORMAT(qssj,'%Y-%m-%d') as qssj,"
				+ "DATE_FORMAT(jssj,'%Y-%m-%d') as jssj,sdjmc,sdjsl,bz from sh_wxcjpz where '"+time
				+ "' between DATE_FORMAT(qssj,'%Y-%m-%d') and DATE_FORMAT(jssj,'%Y-%m-%d')";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据用户的openId获取用户的中奖信息
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getZjjg(String openId){
		String sql="select id,cjid,openid,czjx,bz from sh_wxcjjg where openid='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据用户的openId和抽奖编号获取用户的中奖信息
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getCjjg(String openId,String cjid){
		String sql="select id,cjid,openid,czjx,bz from sh_wxcjjg where openid='"+openId+"' and cjid="+cjid;
		return jdbcTemplate.queryForList(sql);
	}
}
