package com.sliu.framework.app.wxutil.contorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wxutil.service.WxMeetingService;

/**
 * 微会议
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:45
 */
@Controller
@RequestMapping(value = "/wxutil/meeting")
public class WxMeetingController extends BaseController{

	@Autowired
	private WxMeetingService wxMeetingService;
	
	@RequestMapping(value = "/ownerpage")
	public ModelAndView openWxShakeInfoPage(String ticket) {
		ModelAndView modelAndView = new ModelAndView();
		String url = "/weixin/meeting/ownerlist";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 获取自己发起的会议列表
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月23日 下午4:02:38 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/ownerpageList", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String, Object>> ownerpageList(Integer start,Integer limit) {
		return wxMeetingService.ownerpageList(start,limit);
	}
	
	/**
	 * 微生活主页历史会议
	 * @author zhangyan
	 * @date 2016年10月14日 上午9:35:06
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/lshyList")
	public ModelAndView lshyList(String openId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Map<String, Object>> list = wxMeetingService.getLshy(openId);
		modelAndView.addObject("list",list);
		modelAndView.addObject("openId",openId);
		String url = "";
		url = "/wsh/hyqdList";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 历史会议详情
	 * @author zhangyan
	 * @date 2016年10月14日 上午9:56:31
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/lshyDetail")
	public ModelAndView lshyDetail(String id,String openId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Map<String, Object>> list = wxMeetingService.getLshyDetail(id,openId);
		modelAndView.addObject("list",list);
		modelAndView.addObject("openId",openId);
		String url = "";
		url = "/wsh/hyqdDetail";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
}
