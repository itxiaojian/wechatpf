package com.sliu.framework.app.wxutil.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wxutil.dao.WxMeetingDao;
import com.sliu.framework.app.wxutil.dao.WxShakeInfoDao;
import com.sliu.framework.app.wxutil.model.WxShakeInfo;

/**
 * 微会议
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:28
 */
@Service
public class WxMeetingService {

	@Autowired
	private WxShakeInfoDao wxShakeInfoDao;
	
	@Autowired
	private WxMeetingDao wxMeetingDao;

	@Transactional
	public WxShakeInfo test(String ticket) {
		WxShakeInfo entity = WeixinUtils.getShakeInfo(ticket, 1);
		
		return entity;
	}

	/**
	 * 查询自己添加的会议
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月23日 下午4:11:40 
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> ownerpageList(Integer start,
			Integer limit) {
		return wxMeetingDao.ownerpageList(start,limit);
	}
	
	
	/**
	 * 微生活-历史会议
	 * @author zhangyan
	 * @date 2016年10月14日 上午9:39:29
	 * @param 
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getLshy(String openId) {
		return wxMeetingDao.getLshy(openId);
	}
	
	/**
	 * 历史会议详情
	 * @author zhangyan
	 * @date 2016年10月14日 上午10:14:56
	 * @param 
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getLshyDetail(String id,String openId) {
		return wxMeetingDao.getLshyDetail(id,openId);
	}
}
