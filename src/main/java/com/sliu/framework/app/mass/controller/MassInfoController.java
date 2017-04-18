package com.sliu.framework.app.mass.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.mass.service.MassInfoService;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxutil.service.WxTemplateService;
import com.sliu.framework.app.wxutil.service.WxTwxxztService;

@Controller
@RequestMapping("/wxauth/mass")
public class MassInfoController extends BaseController{

	@Autowired
	private MassInfoService massInfoService;
	
	@Autowired
	private WxTemplateService wxTemplateService;
	
	@Autowired
	private WxTwxxztService wxTwxxztService;
	
	/**
	 * 打开微信群发页面
	 * @return
	 */
	@RequestMapping("/massInfoPage")
	public String openMassInfoPage() {
		return "/weixin/mass/massInfoList";
	}
	
	/**
	 * 取得所有分组信息,在额外加上"--全部--"
	 * @return
	 */
	@RequestMapping(value = "/getGroupCombo", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getGroupCombo() {
		return massInfoService.getGroupCombo();
	}
	
	/**
	 * 取得所有分组信息,在额外加上"--全部--"
	 * @return
	 */
	@RequestMapping(value = "/getUserByGroup", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getUserByGroup(Long groupId) {
		return massInfoService.getUserByGroup(groupId);
	}
	
	/**
	 * 取得素材表中3天内的图片列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@RequestMapping(value = "/getImageList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getImageList() {
		return massInfoService.getImageList();
	}

	/**
	 * 取得素材表中3天内的语音列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@RequestMapping(value = "/getVoiceList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getVoiceList() {
		return massInfoService.getVoiceList();
	}
	
	/**
	 * 取得素材表中3天内的视频列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@RequestMapping(value = "/getVideoList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getVideoList() {
		return massInfoService.getVideoList();
	}

	/**
	 * 取得素材表中3天内的图文消息列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@RequestMapping(value = "/getMpnewsList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getMpnewsList() {
		return massInfoService.getMpnewsList();
	}
	
	/**
	 * 调用微信接口实现群发功能
	 * @param toObject
	 * @param content
	 * @param msgtype 群发消息类型,mpnews：图文消息;text：文本;voice：语音;image：图片;
	 * @return
	 */
	@RequestMapping(value = "/massMessage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData massMessage(Long toObject, String content, String msgtype, String media_id) {
		return massInfoService.massMessage(toObject, content, msgtype);
	}
	
//	/**
//	 * 调用微信接口实现群发功能
//	 * @param toObject
//	 * @param content
//	 * @param msgtype 群发消息类型,mpnews：图文消息;text：文本;voice：语音;image：图片;
//	 * @return
//	 */
//	@RequestMapping(value = "/massMessageOneByOne", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseData massMessageOneByOne(Long toObject,String toUser, String content, String msgtype, String media_id) {
//		String [] list = toUser.split(",");
//		ResponseData res = null;
//		for(int i = 0;i<list.length;i++){
//			res = massInfoService.massPreview(msgtype, list[i], content);
//			if(!res.isSuccess()){
//				return res;
//			}
////			System.out.println("------------->"+list[i]);
//		}
//		return ResponseData.SUCCESS_NO_DATA;
//	}
	
	/**
	 * 调用微信接口实现群发功能
	 * @param toObject
	 * @param content
	 * @param msgtype 群发消息类型,mpnews：图文消息;text：文本;voice：语音;image：图片;
	 * @return
	 */
	@RequestMapping(value = "/massMessageOneByOne", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData massMessageOneByOne(Long toObject,String toUser,String isAll, String content, String msgtype, String media_id,HttpServletRequest request,HttpServletResponse response) {
		if("1".equalsIgnoreCase(isAll)){
			List<Object> list =  WeixinUtils.getAllSubscribeUsers();
			ResponseData res = null;
			for(Object object:list) {
				if("templatenews".equalsIgnoreCase(msgtype)){
					wxTemplateService.massTemplatenews(object.toString(),content,msgtype);
				}else if("twxx".equalsIgnoreCase(msgtype)){
					wxTwxxztService.sentTwxx(object.toString(),content,request,response);
				}else{
					res = massInfoService.massPreview(msgtype, object.toString(), content);
					if(!res.isSuccess()){
						return res;
					}
				}
			}
		}else{
			String [] list = toUser.split(",");
			ResponseData res = null;
			
			for(int i = 0;i<list.length;i++){
				if("templatenews".equalsIgnoreCase(msgtype)){
					wxTemplateService.massTemplatenews(list[i],content,msgtype);
				}else if("twxx".equalsIgnoreCase(msgtype)){
					wxTwxxztService.sentTwxx(list[i],content,request,response);
				}else{
					res = massInfoService.massPreview(msgtype, list[i], content);
					if(!res.isSuccess()){
						return res;
					}
				}
	//			System.out.println("------------->"+list[i]);
			}
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 调用微信接口给某个用户测试群发效果
	 * @param msgtype ----mpnews：图文消息;text：文本;voice：语音;image：图片;mpvideo:视频
	 * @param touser
	 * @param content 群发内容：文本就是发送的文本,其它素材是对应的media_id
	 * @return
	 */
	@RequestMapping(value = "/massPreview", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData massPreview(String msgtype, String touser, String content) {
		return massInfoService.massPreview(msgtype, touser, content);
	}
}
