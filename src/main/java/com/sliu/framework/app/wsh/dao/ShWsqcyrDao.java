package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWsqcyr;

@Repository
public class ShWsqcyrDao extends HibernateBaseDaoImpl<ShWsqcyr, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据上墙活动编号获取此次上墙活动的参与人数
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param hdid 活动编号
	 * @return
	 */
	public int getCyrCount(String hdid){
		String sql="select id,hdid,openid,cyrxm,bz from sh_wsqcyr where hdid="+hdid;
		return jdbcTemplate.queryForList(sql).size();
	}

	/**
	 * 根据openId获取微信用户的头像和昵称
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getWxUser(String openId) {
		String sql="select openid,nickname,headimgurl from wx_user_info where openid='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 查询用户是否已签到
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getQd(String openId,String id) {
		String sql="select id,hdid,openid,cyrxm,bz from sh_wsqcyr where openid='"+openId+"' and hdid="+id;
		return jdbcTemplate.queryForList(sql);
	}
}
