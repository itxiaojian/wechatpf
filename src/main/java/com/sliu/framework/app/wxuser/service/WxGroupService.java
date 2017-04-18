package com.sliu.framework.app.wxuser.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxGroupInfo;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxuser.dao.WxGroupDao;

@Service
public class WxGroupService {

	@Autowired
	private WxGroupDao groupDao;
	
	@Transactional
	public List<Map<String, Object>> getWxGroupList() {
		return groupDao.getWxGroupList();
	}
	
	@Transactional
	public Pagination<Object> getWxGroupPaging(Integer start,Integer limit) {
		return groupDao.getWxGroupPaging(start, limit);
	}
	
	/**
	 * 添加微信分组信息
	 * @param groupName
	 * @return
	 */
	@Transactional
	public ResponseData addGroup(String groupName) {
		Long groupId = WeixinUtils.createGroup(groupName);
		if(groupId == 0L) {
			return ResponseData.FAILED_NO_DATA;
		}
		WxGroupInfo groupInfo = new WxGroupInfo();
		groupInfo.setGroupId(groupId);
		groupInfo.setName(groupName);
		groupDao.saveGroupInfo(groupInfo);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改微信分组信息
	 * @param groupId
	 * @param groupName
	 * @return
	 */
	@Transactional
	public ResponseData updateGroup(Long groupId, String groupName) {
		ResponseData responseData =  WeixinUtils.updateGroup(groupId, groupName);
		if(responseData.isSuccess()) {
			groupDao.updateWxGroupInfo(groupId, groupName);
		}
		return responseData;
	}
	
	/**
	 * 与微信平台同步微信分组信息
	 * @return
	 */
	@Transactional
	public void refreshWxGroup() {
		List<WxGroupInfo> list = WeixinUtils.getAllGroupInfo();
		for(WxGroupInfo groupInfo:list) {
			groupDao.saveGroupInfo(groupInfo);
		}
	}
}
