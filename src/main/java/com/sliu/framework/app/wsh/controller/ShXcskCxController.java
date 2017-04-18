package com.sliu.framework.app.wsh.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.service.ShXcskCxService;

@Controller
@RequestMapping("/wsh/ShXcsk")
public class ShXcskCxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShXcskCxController.class);

	@Autowired
	private ShXcskCxService service;

	/**
	 * 校车时刻列表
	 * 
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toShxcsk")
	public ModelAndView toShxckList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShXcsk/shXcskList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = service.getShXcskList(request);
		int count = service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		mav.addObject("pages", pages);
		mav.addObject("count", count);
		return mav;
	}

	/**
	 * 校车时刻列表
	 * 
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toShxcskDetail")
	public ModelAndView toSwzlDetail(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShXcsk/shXcskDetail");
		String cph = request.getParameter("cph");
		// cph= java.net.URLDecoder.decode(cph,"UTF-8");
		cph = new String(cph.getBytes("ISO-8859-1"), "UTF-8");
		List<Map<String, Object>> list = service.getShxsckByCph(cph);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		return mav;
	}

	/**
	 * 校车路线
	 * 
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toShxcskRoute")
	public ModelAndView route(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShXcsk/shXcskRoute");
		String openId = request.getParameter("openId");
		if (openId == null || "".equalsIgnoreCase(openId)) {
			mav = AppUtil.runWx(openId);
		} else {
			List<Map<String, Object>> listCph = service.getCph(request);
			List<Map<String, Object>> map = service.getFirst(request);
			if (listCph.size() != 0) {
				mav.addObject("listCph", listCph);
			}
			if (map != null) {
				mav.addObject("map", map);
			}
		}
		mav.addObject("openId", openId);
		return mav;
	}

	/**
	 * 校车路线
	 * 
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toShxcskRoute1")
	public ModelAndView route1(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShXcsk/shXcskRoute");
		String cph = request.getParameter("cph");
		cph = new String(cph.getBytes("ISO-8859-1"), "UTF-8");
		List<Map<String, Object>> map = service.getShXcskRoute(cph);
		List<Map<String, Object>> listCph = service.getCph(request);
		mav.addObject("map", map);
		mav.addObject("listCph", listCph);
		return mav;
	}
}