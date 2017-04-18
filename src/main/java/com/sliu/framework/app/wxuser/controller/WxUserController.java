package com.sliu.framework.app.wxuser.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxuser.service.WxUserService;

@Controller
@RequestMapping(value = "/wxauth/user")
public class WxUserController extends BaseController{

	@Autowired
	private WxUserService userService;
	
	@RequestMapping(value = "/wxUser")
	public String openWxUserPage() {		
		return "/weixin/user/wxUserList";
	}
	
	/**
	 * 查找所有入库的微信用户信息
	 * @return
	 */
	@RequestMapping(value = "/getWxUserList", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String,Object>> getWxUserList(Integer start,Integer limit, String userName) {
		return userService.getWxUserList(start, limit, userName);
	}
	
	/**
	 * 设置微信用户备注名
	 * @param remark
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/setRemarkName", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData setRemarkName(String remark, String openid) {
		return userService.setRemarkName(remark, openid);
	}
	
	/**
	 * 移动用户分组,1、先调用微信接口移动用户分组,2、然后更新用户表数据
	 * @param openid
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/setUserGroup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData setUserGroup(String[] openids, Long groupId) {
		return userService.setUserGroup(openids, groupId);
	}
	
	/**
	 * 与微信平台同步用户信息
	 * @return
	 */
	@RequestMapping(value = "/refreshWxUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refreshWxUser() {
		userService.refreshWxUser();
		return ResponseData.SUCCESS_NO_DATA;
	}
}
