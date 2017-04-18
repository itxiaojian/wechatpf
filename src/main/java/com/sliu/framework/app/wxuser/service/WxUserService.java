package com.sliu.framework.app.wxuser.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDao;
import com.sliu.framework.app.common.dao.service.BaseServiceImpl;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxUserInfo;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxuser.dao.WxUserDao;

@Service
public class WxUserService extends BaseServiceImpl<WxUserInfo, Long>{

	private Logger logger = LoggerFactory.getLogger(WxUserService.class);  
	
	@Autowired
	private WxUserDao userDao;
	
	/**
	 * 保存关注本公众号的微信用户信息,如果用户是第一次关注本公众号,则插入一条用户信息,如果是取消后又关注的则直接把关注标识更新掉
	 * @param userInfo
	 */
	@Transactional
	public void handleSubscribeUser(WxUserInfo userInfo) {
		String openid = userInfo.getOpenid();
		List<WxUserInfo> list = userDao.getWxUserByOpenid(openid);
		if(list.isEmpty()) {
			this.insertEntity(userInfo);
		}else {
			userDao.updateSubscribeStatus(openid, 1);
		}
		
	}

	/**
	 * 用户取消关注本公众号时更新用户信息
	 * @param openid
	 */
	public void handleUnSubscribeUser(String openid) {
		userDao.updateSubscribeStatus(openid, 0);
	}
	
	@Override
	public HibernateBaseDao<WxUserInfo, Long> getHibernateBaseDao() {
		// TODO Auto-generated method stub
		return userDao;
	}
	
	public Pagination<Map<String,Object>> getWxUserList(Integer start,Integer limit, String userName) {
		return userDao.getWxUserList(start, limit, userName);
	}
	
	/**
	 * 设置微信用户备注名
	 * @param remark
	 * @param openid
	 */
	@Transactional
	public ResponseData setRemarkName(String remark, String openid) {
		ResponseData responseData =  WeixinUtils.updateWxUserRemark(remark, openid);
		if(responseData.isSuccess()) {
			userDao.updateUserRemarkName(remark, openid);
		}
		return responseData;
	}
	
	/**
	 * 微信用户分组
	 * @param openid
	 * @param groupId
	 * @return
	 */
	@Transactional
	public ResponseData setUserGroup(String[] openids, Long groupId) {
		int successNum = 0;
		for(int i = 0; i<openids.length; i++) {
			String openid = openids[i];
			ResponseData responseData = WeixinUtils.setUserGroup(openid, groupId);
			if(responseData.isSuccess()) {
				userDao.updateUserGroupId(openid, groupId);
				successNum++;
			}
		}
		return new ResponseData(true, "本次成功设置【"+ successNum+ "】个用户");
	}
	
	/**
	 * 与微信平台同步
	 */
	public void refreshWxUser() {
		List<Object> list = WeixinUtils.getAllSubscribeUsers();
		int i =0;
		for(Object object:list) {
			logger.info("同步第{}个用户信息", ++i);
			getWxUserInfoAndRefresh(object.toString());
		}
	}
	
	
	/**
	 * 根据微信用户openid取得微信用户信息,并与数据库同步,如果数据库没有对应数据则入库
	 * @param openid
	 */
	@Transactional
	private void getWxUserInfoAndRefresh(String openid) {
		List<WxUserInfo> list = userDao.getWxUserByOpenid(openid);
		if(list.isEmpty()) {
			WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(WeixinCacheUtils.getAccessToken(), openid);
			String groupId = WeixinUtils.getGroupIdByOpenId(openid);
			if(!groupId.contains("error|")){
				wxUserInfo.setGroupId(Long.parseLong(groupId));
			};
			userDao.saveUser(wxUserInfo);
		}else {
			String groupId = WeixinUtils.getGroupIdByOpenId(openid);
			if(groupId.startsWith("error")) {
				logger.info("获取openid--{}的groupid失败----原因:{}", openid, groupId);
			}else {
				WxUserInfo userInfo = list.get(0);
				if(groupId !=null){
					if(userInfo.getGroupId() != null){
						if(userInfo.getGroupId() != Long.parseLong(groupId)){
							userInfo.setGroupId(Long.parseLong(groupId));
							userDao.updateUser(userInfo);
						}
					}else{
						userInfo.setGroupId(Long.parseLong(groupId));
						userDao.updateUser(userInfo);
					}
				}			
			}
		}
	}
}
