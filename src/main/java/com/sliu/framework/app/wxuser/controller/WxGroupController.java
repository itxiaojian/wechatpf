package com.sliu.framework.app.wxuser.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxuser.service.WxGroupService;

@Controller
@RequestMapping(value = "/wxauth/group")
public class WxGroupController extends BaseController{

	@Autowired
	private WxGroupService wxGroupService;
	
	@RequestMapping(value = "/wxGroup")
	public String openWxGroupPage() {		
		return "/weixin/user/wxGroupList";
	}
	
	/**
	 * 查找微信分组信息,下拉列表使用
	 * @return
	 */
	@RequestMapping(value = "/getWxGroupList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getWxGroupList() {
		return wxGroupService.getWxGroupList();
	}
	
	/**
	 * 分页查询微信分组信息
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getWxGroupPaging", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Object> getWxGroupPaging(Integer start,Integer limit) {
		return wxGroupService.getWxGroupPaging(start, limit);
	}
	
	/**
	 * 添加微信分组信息
	 * @param groupName
	 * @return
	 */
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData addGroup(String groupName) {
		return wxGroupService.addGroup(groupName);
	}
	
	/**
	 * 更新微信分组信息
	 * @param groupId 分组ID
	 * @param groupName 分组名称
	 * @return
	 */
	@RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData updateGroup(Long groupId, String groupName) {
		return wxGroupService.updateGroup(groupId, groupName);
	}
	
	/**
	 * 与微信平台同步微信分组信息
	 * @return
	 */
	@RequestMapping(value = "/refreshWxGroup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refreshWxGroup() {
		wxGroupService.refreshWxGroup();
		return ResponseData.SUCCESS_NO_DATA;
	}
}
