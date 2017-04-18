package com.sliu.framework.app.wxutil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wxutil.dao.WxShakeInfoDao;
import com.sliu.framework.app.wxutil.model.WxShakeInfo;

/**
 * 微信周边摇一摇
 * @author : zhangyi
 * @version 创建时间：2016年3月22日 下午2:21:15
 */
@Service
public class WxShakeInfoService {

	@Autowired
	private WxShakeInfoDao wxShakeInfoDao;

	@Transactional
	public WxShakeInfo test(String ticket) {
		WxShakeInfo entity = WeixinUtils.getShakeInfo(ticket, 1);
		
		return entity;
	}
	
}
