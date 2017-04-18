package com.sliu.framework.app.mass.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.sliu.framework.app.mass.dao.MassInfoDao;
import com.sliu.framework.app.mass.dao.WxSendallLogDao;
import com.sliu.framework.app.model.WxSendallLog;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.util.SecurityContextUtil;

@Service
public class MassInfoService {

	@Autowired
	private MassInfoDao massInfoDao;
	
	@Autowired
	private WxSendallLogDao sendallLogDao;
	
	@Transactional
	public List<Map<String, Object>> getGroupCombo() {
		List<Map<String, Object>> list = massInfoDao.getGroupInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", -1);
		map.put("groupName", "--全部--");
		list.add(0, map);
		return list;
	}
	
	/**
	 * 取得素材表中3天内的图片列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getImageList() {
		return massInfoDao.getImageList();
	}

	/**
	 * 取得素材表中3天内的语音列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getVoiceList() {
		return massInfoDao.getVoiceList();
	}
	
	/**
	 * 取得素材表中3天内的视频列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getVideoList() {
		return massInfoDao.getVideoList();
	}
	
	@Transactional
	public List<Map<String, Object>> getMpnewsList() {
		return massInfoDao.getMpnewsList();
	}
	
	@Transactional
	public ResponseData massMessage(Long toObject, String content, String msgtype) {
		ResponseData responseData = ResponseData.SUCCESS_NO_DATA;
		JSONObject jsonObject = WeixinUtils.sendAllMessage(toObject, content, msgtype);
    	Integer errcode = jsonObject.getInteger("errcode");
		String errmsg = jsonObject.getString("errmsg");	
		if(errcode != 0 ) {
			responseData = new ResponseData(false, errmsg);
			return responseData;
		}
		//保存群发日志
		this.saveSendAllLog(toObject, content, msgtype, jsonObject);
		return responseData;
	}
	
	/**
	 * 保存群发日志
	 * @param toObject
	 * @param content
	 * @param msgtype
	 * @param jsonObject
	 */
	@Transactional
	private void saveSendAllLog(Long toObject, String content, String msgtype, JSONObject jsonObject) {
		WxSendallLog sendallLog = new WxSendallLog();
		String msgid = jsonObject.getString("msg_id");
		Short isToAll = 1;
		if(toObject != -1) {
			isToAll = 0;
			sendallLog.setGroupId(toObject);
		}
		sendallLog.setIsToAll(isToAll);
		sendallLog.setMsgtype(msgtype);
		sendallLog.setMediaId(content);
		sendallLog.setSendUser(SecurityContextUtil.getCurrentUser().getYhbh());
		sendallLog.setSendTime(new Date());
		sendallLog.setMsgid(msgid);
		sendallLogDao.saveWxSendallLog(sendallLog);
	}
	
	/**
	 * 调用微信接口给某个用户测试群发效果
	 * @param msgtype ----mpnews：图文消息;text：文本;voice：语音;image：图片;mpvideo:视频
	 * @param touser
	 * @param content 群发内容：文本就是发送的文本,其它素材是对应的media_id
	 * @return
	 */
	@Transactional
	public ResponseData massPreview(String msgtype, String touser, String content) {
		return WeixinUtils.massPreview(msgtype, touser, content);
	}
	
	/**
	 * 循环调用、发送每个人
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月10日 下午4:33:29 
	 * @param msgtype
	 * @param touser
	 * @param content
	 * @return
	 */
	@Transactional
	public ResponseData massAllPreview(String msgtype, String media_id) {
		List<Object> all = WeixinUtils.getAllSubscribeUsers();
		for(Object oid:all){
			String openid = oid.toString();
			WeixinUtils.massPreview(msgtype, openid, media_id);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}

	/**
	 * 
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月12日 上午11:52:34 
	 * @param groupId
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getUserByGroup(Long groupId) {
		List<Map<String, Object>> list = massInfoDao.getUserByGroup(groupId);
		return list;
	}
	
}
