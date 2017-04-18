//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.stat.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;

/**
 * 进入生活主页
 * 
 * @author duanpeijun
 * @date  2015年7月2日
 */
@Controller
@RequestMapping("/stat/zy")
public class SjtjZyController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SjtjZyController.class);

	/**
	 * 
	 * @author duanpeijun
	 * @date  2015年7月2日
	 * @param request
	 * @param response
	 * @param openId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/sjtjzy")
	public ModelAndView sjtjzy(HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		url = "/stat/sjtjzy";
		String openId = request.getParameter("openId");
		modelAndView.addObject("openId", openId);
		modelAndView.setViewName(url);
		return modelAndView;
	}
}
