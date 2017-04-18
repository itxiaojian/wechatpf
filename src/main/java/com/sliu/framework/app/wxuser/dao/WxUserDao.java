package com.sliu.framework.app.wxuser.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxUserInfo;

@Repository
public class WxUserDao extends HibernateBaseDaoImpl<WxUserInfo, Long>{
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 更新微信用户信息表的,用户是否订阅该公众号标识
	 * @param openid
	 */
	@Transactional
	public void updateSubscribeStatus(String openid, int subscribe) {
		String hql = "update WxUserInfo t set t.subscribe = ? where t.openid = ?";
		this.bulkUpdate(hql, subscribe, openid);
	}
	
	/**
	 * 根据openid查找数据库中是否有此用户信息
	 * @param openid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<WxUserInfo> getWxUserByOpenid(String openid) {
		String hql = " from WxUserInfo t where t.openid = ?";
		return this.findByHQL(hql, openid);
	}
	
	public Pagination<Map<String,Object>> getWxUserList(Integer start,Integer limit, String userName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select t.USER_ID as \"userId\", t.SUBSCRIBE as \"subscribe\", t.OPENID as \"openid\", t.NICKNAME as \"nickname\"" +
				" , t.SEX as \"sex\",t.U_LANGUAGE as \"ULanguage\", t.CITY as \"city\", t.PROVINCE as \"province\"," +
				" t.COUNTRY as \"country\", t.HEADIMGURL as \"headimgurl\",t.SUBSCRIBE_TIME as \"subscribeTime\"," +
				" t.UNIONID as \"unionid\", t.REMARK as \"remark\", t.MEMO as \"memo\", t.GROUP_ID as \"groupId\",b.NAME as \"groupName\"" +
				" from WX_USER_INFO t left join WX_GROUP_INFO b on t.GROUP_ID = b.GROUP_ID where 1=1";
		if(StringUtils.hasText(userName)) {
			paramMap.put("userName", "%" + userName + "%");
			sql += " and t.NICKNAME like :userName";
		}
		sql += "  order by t.SUBSCRIBE_TIME desc";
		return jdbcPager.queryPageDb2(sql, start, limit, paramMap);
		
	}
	
	public void updateUserGroupId(String openid, Long groupId) {
		String hql = "update WxUserInfo t set t.groupId = ? where t.openid = ?";
		this.bulkUpdate(hql, groupId, openid);
	}
	
	public void updateUserRemarkName(String remark, String openid) {
		String hql = "update WxUserInfo t set t.remark = ? where t.openid = ?";
		this.bulkUpdate(hql, remark, openid);
	}
	
	@Transactional
	public void saveUser(WxUserInfo userInfo) {
		this.save(userInfo);
	}
	
	@Transactional
	public void updateUser(WxUserInfo userInfo) {
		this.update(userInfo);
	}

	@SuppressWarnings("unchecked")
	public List<WxUserInfo> getGuanZhuList() {
		String hql = "from WxUserInfo t where t.subscribe = 1";
		return this.findByHQL(hql);
	}
}
