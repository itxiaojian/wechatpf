//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.model.ShWsq;
import com.sliu.framework.app.wsh.service.ShWsqService;
import com.sliu.framework.app.wsh.service.ShWsqcyrService;
import com.sliu.framework.app.wsh.service.ShWsqfbxxService;

/**
 * 微上墙
 * @author liujiasen
 * @date 2015年7月7日
 */
@Controller
@RequestMapping("/wsh/ShWsq")
public class ShWsqController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShWsqController.class);

	@Autowired
	private ShWsqService service;
	@Autowired
	private ShWsqcyrService cyrService;
	@Autowired
	private ShWsqfbxxService fbService;

	/**
	 * 微上墙数据列表
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toWsqList")
	public ModelAndView toWsqList(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = AppUtil.getOpenId(request, response);
		List<Map<String, Object>> list = service.getWsqList(request,response);
		int count = service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("count", count);
			mav.addObject("openId", openId);
		}
		return mav;
	}
	
	/**
	 * 转到活动添加页面
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWsqAdd")
	public ModelAndView toWsqAdd(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqAdd");
		return mav;
	}
	
	/**
	 * 微上墙活动添加
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		service.saveWsq(request, response);
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = AppUtil.getOpenId(request, response);
		List<Map<String, Object>> list = service.getWsqList(request,response);
		int count = service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("count", count);
			mav.addObject("openId", openId);
		}
		return mav;
	}
	
	/**
	 * 检查当前选择的时间段内是否有其他上墙活动
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public ResponseData check(HttpServletRequest request, HttpServletResponse response){
		return service.check(request,response);
	}
	
	/**
	 * 删除上墙活动
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,String id,
			HttpServletResponse response) {
		String str = service.delete(id);
		return str;
	}
	
	/**
	 * 跳转到用户签到页面
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toWsqYhqd")
	public ModelAndView toWsqYhqd(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqYhqd");
		String id=request.getParameter("id");
		ShWsq sq=service.getEntity(Long.parseLong(id));
		String openId = AppUtil.getOpenId(request, response);
		mav.addObject("openId", openId);
		mav.addObject("wsq", sq);
		return mav;
	}
	
	/**
	 * 跳转到信息发布页面
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toWsqYhfb")
	public ModelAndView toWsqYhfb(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqYhfb");
		String openId = request.getParameter("openId");
		if(openId==null || "".equals(openId)){
			openId = AppUtil.getOpenId(request, response);
		}
		String id=request.getParameter("id");
		String cyrid = cyrService.saveCyr(request,response,openId).getMessage()+"";
		List<Map<String,Object>> fbxx=fbService.getFbxx(id,cyrid);
		mav.addObject("openId", openId);
		mav.addObject("hdid", id);
		mav.addObject("cyrid", cyrid);
		mav.addObject("fbxx", fbxx);
		return mav;
	}
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping(value = "/saveCyr")
//	@ResponseBody
//	public ResponseData saveCyr(HttpServletRequest request, HttpServletResponse response) {
//		return cyrService.saveCyr(request,response);
//	}
	
	/**
	 * 跳转到上墙活动编辑页面
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWsqUpdate")
	public ModelAndView toWsqUpdate(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqUpdate");
		SimpleDateFormat sim=new SimpleDateFormat("HH:mm");
		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-MM-dd");
		String id=request.getParameter("id");
		ShWsq sq=service.getEntity(Long.parseLong(id));
		if(sq!=null){
			String[] start=sim.format(sq.getKssj()).split(":");
			String[] end=sim.format(sq.getJzsj()).split(":");
			mav.addObject("starthour", start[0]);
			mav.addObject("startmin", start[1]);
			mav.addObject("endhour", end[0]);
			mav.addObject("endmin", end[1]);
			mav.addObject("startdate", sim1.format(sq.getKssj()));
			mav.addObject("enddate", sim1.format(sq.getJzsj()));
		}
		mav.addObject("wsq", sq);
		return mav;
	}
	
	/**
	 * 微上墙信息修改
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		service.updateWsq(request, response);
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = AppUtil.getOpenId(request, response);
		List<Map<String, Object>> list = service.getWsqList(request,response);
		int count = service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("count", count);
			mav.addObject("openId", openId);
		}
		return mav;
	}
	
	/**
	 * 跳转到微上墙大屏幕
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWsqView")
	public ModelAndView toWsqView(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqView");
		String id=request.getParameter("id");
		ShWsq sq=service.getEntity(Long.parseLong(id));
		List<Map<String,Object>> fbxx=fbService.getSqxx(id, "");
		mav.addObject("wsq", sq);
		mav.addObject("fbxx", fbxx);
		return mav;
	}
	
	/**
	 * 保存上墙信息
	 * @author liujiasen
	 * @date 2015年7月9日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toSend")
	public ModelAndView toSend(HttpServletRequest request, HttpServletResponse response) {
		String data=fbService.toSend(request,response);
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqYhfb");
		String openId = request.getParameter("openId");
		String id=request.getParameter("id");
		String cyrid = request.getParameter("cyrid");
		List<Map<String,Object>> fbxx=fbService.getFbxx(id,cyrid);
		mav.addObject("openId", openId);
		mav.addObject("hdid", id);
		mav.addObject("cyrid", cyrid);
		mav.addObject("fbxx", fbxx);
		return mav;
	}
	
	/**
	 * 上墙信息
	 * @author liujiasen
	 * @date 2015年7月9日
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value = "/getSqxx")
	@ResponseBody
	public String getSqxx(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		String length=request.getParameter("length");
		String time_start=request.getParameter("time_start");
		String data=fbService.getSqxx(id, "");
		return data;
	}*/
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月21日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toDemo")
	public ModelAndView toDemo(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWsq/shWsqSys");
		//String openId = request.getParameter("openId");
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

        // 注意 URL 一定要动态获取，不能 hardcode
        //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
        HttpServletRequest httpRequest=(HttpServletRequest)request; 
        String url1 = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + httpRequest.getContextPath()      //项目名称  
                + httpRequest.getServletPath();      //请求页面或其他地址  
            //+ "?" + (httpRequest.getQueryString()); //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        mav.addObject("map", ret);
		return mav;
	}
}
