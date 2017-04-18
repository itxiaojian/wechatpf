package com.sliu.framework.app.wxutil.contorller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wxutil.model.WxShakeInfo;
import com.sliu.framework.app.wxutil.service.WxShakeInfoService;

/**
 * 微信周边摇一摇
 * @author : zhangyi
 * @version 创建时间：2016年3月22日 下午2:23:46
 */
@Controller
@RequestMapping(value = "/wxauth/shake")
public class WxShakeInfoController extends BaseController{

	@Autowired
	private WxShakeInfoService wxShakeInfoService;
	
	@RequestMapping(value = "/test")
	public ModelAndView openWxShakeInfoPage(String ticket) {
		ModelAndView modelAndView = new ModelAndView();
		String url = "/weixin/shake/test";
		WxShakeInfo sinfo = wxShakeInfoService.test(ticket);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (sinfo instanceof Map) ? (Map<String, Object>) sinfo : AppUtil.beanToMap(sinfo);
		
		modelAndView.addObject("map", map);
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 分页查询微信分组信息
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getFirstMenu", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Object> getFirstMenu(Integer start,Integer limit) {
		
		return null;
	}
}
