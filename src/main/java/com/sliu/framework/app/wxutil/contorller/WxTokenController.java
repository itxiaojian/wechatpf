package com.sliu.framework.app.wxutil.contorller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxutil.model.WxToken;
import com.sliu.framework.app.wxutil.service.WxTokenService;


/**
*
@Author chenhui	
@Date 2015年8月10日 下午4:36:21
@Version 1.0
*/
@Controller
@RequestMapping("/weixin/meeting")
public class WxTokenController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WxToken.class);

	@Autowired
	private WxTokenService wxTokenService;
	
	/**
	 * Token主页面
	 * @author chenhui 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/shlist")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/weixin/meeting/tokenlist");
		return modelAndView;
	}

	/**
	 * 列表数据
	 * @author oufeng
	 * @date 2015年8月7日
	 * @param start
	 * @param limit
	 * @param zt
	 * @return
	 */
	@RequestMapping(value = "/pager")
	@ResponseBody
	public Pagination<Map<String, Object>> getTokenList(Integer start,Integer limit) {
		return wxTokenService.getTokenList(start, limit);
	}
	
	/**
	 * 删除
	 * @author   chenhui
	 * @version 创建时间：2015年6月3日  下午4:06:43
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
		wxTokenService.delete(ids);
	return ResponseData.SUCCESS_NO_DATA;
	}
}
