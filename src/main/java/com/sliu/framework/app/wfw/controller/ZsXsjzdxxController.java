package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
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
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsXsjzdxxService;

/**
 * 
 * @author chenhui
 * @version 创建时间：2015年6月9日
 */
@Controller
@RequestMapping("/wfw/ZsXsjzdxx")
public class ZsXsjzdxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXsjzdxxController.class);

	@Autowired
	private ZsXsjzdxxService zsXsjzdxxService;

	@Autowired
	private SysWxyhDao sysWxyhDao;

	@Autowired
	private SysYhBO yhBo;

	/**
	 * 奖助贷信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toZsxsjzdxx")
	@ResponseBody
	public ModelAndView toZsxsZxxx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = null;
		String openId = request.getParameter("openId");
		if (openId == null || "".equalsIgnoreCase(openId)) {
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> user = sysWxyhDao.getWxyh(openId);

		if (user.size() != 0) {
			mav = new ModelAndView("/wfw/zsXsjzdxxList");
			// String ghsj = sim.format(c.getTime());
			List<Map<String, Object>> cjlist = zsXsjzdxxService.getGrTsjyxxBysj(openId, request);
			if (cjlist.size() > 0) {
				String yhbh = "";
				for (int i = 0; i < cjlist.size(); i++) {
					if (i < cjlist.size() - 1) {
						yhbh = yhbh + cjlist.get(i).get("xh").toString() + ",";
					} else {
						yhbh = yhbh + cjlist.get(i).get("xh").toString();
					}
				}
				List<Map<String, Object>> xslist = zsXsjzdxxService.getXs(openId);
				List<Map<String, Object>> list = zsXsjzdxxService.getJzdxxList("",openId,request,yhbh);
				List<Map<String, Object>> zxlist = zsXsjzdxxService.getZxxxList("",openId,request,yhbh);
				List<Map<String, Object>> dklist = zsXsjzdxxService.getDkxxList("",openId,request,yhbh);
				if(xslist != null&&  xslist.size()!=0){
		   			mav.addObject("xslist", xslist);
		   		}
				if(cjlist != null&&  cjlist.size()!=0){
		   			mav.addObject("cjlist", cjlist);
		   		}
				if (list != null && list.size() != 0) {
					mav.addObject("list", list);
				}
				if (zxlist != null && zxlist.size() != 0) {
					mav.addObject("zxlist", zxlist);
				}
				if (dklist != null && dklist.size() != 0) {
					mav.addObject("dklist", dklist);
				}
				mav.addObject("size",list.size());
			}
				mav.addObject("openId", openId);
				mav.addObject("pages", pages);
				mav.addObject("size",cjlist.size());
			} else {
				mav = new ModelAndView("/sys/SysWxyh/wxbd");
				mav.addObject("openId", openId);
				mav.addObject("url", "/wfw/ZsXsjzdxx/toZsxsjzdxx");
			}

			String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

			// 注意 URL 一定要动态获取，不能 hardcode
			// String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String url1 = "http://" + request.getServerName() // 服务器地址
					+ ":" + request.getServerPort() // 端口号
					+ httpRequest.getContextPath() // 项目名称
					+ httpRequest.getServletPath(); // 请求页面或其他地址
			// + "?" + (httpRequest.getQueryString()); //参数
			Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
			// for (Map.Entry entry : ret.entrySet()) {
			// System.out.println(entry.getKey() + ", " + entry.getValue());
			// }
			mav.addObject("map", ret);

			return mav;
		}
	/**
	 * 奖助贷信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toFdyxsjzdxx")
	@ResponseBody
	public ModelAndView toFdyxsjzdxx(String classname,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = null;
		String myclassname = "";
		if(classname !=null && !"".equalsIgnoreCase(classname)){
			myclassname = classname;
		}
		myclassname = new String(myclassname.getBytes("ISO-8859-1"), "UTF-8"); 
		String openId = request.getParameter("openId");
		if (openId == null || "".equalsIgnoreCase(openId)) {
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> user = sysWxyhDao.getWxyh(openId);

		if(user.size()!=0){
		//	String yhid =user.get(0).get("yhid")+"";
		//	SysYh yh=yhBo.get(yhid);
		//	mav.addObject("text", yh.getUsername()+":"+yh.getXm());
			mav = new ModelAndView("/wfw/ZsXsjzdxxfdy");
			// String ghsj = sim.format(c.getTime());
			List<Map<String, Object>> cjlist = zsXsjzdxxService.getWxyh(openId);
			String yhbh = cjlist.get(0).get("yhid").toString();
			List<Map<String, Object>> list = zsXsjzdxxService.getJzdxxList(myclassname, openId,request, yhbh);
			List<Map<String, Object>>	zxlist = zsXsjzdxxService.getZxxxList(myclassname, openId, request, yhbh);
			List<Map<String, Object>>	dklist = zsXsjzdxxService.getDkxxList(myclassname, openId, request, yhbh);
			
				List<Map<String,Object>> bjs=zsXsjzdxxService.getBj(openId);
				if (list != null && list.size() != 0) {
					mav.addObject("list",list);
					
				}
				if (zxlist != null && zxlist.size() != 0) {
					mav.addObject("zxlist", zxlist);
				}
				if (dklist != null && dklist.size() != 0) {
					mav.addObject("dklist", dklist);
				}
				if(bjs.size()!=0){
	   				mav.addObject("bjs",bjs);
	   			}
				mav.addObject("classname", classname);
				mav.addObject("openId", openId);
				mav.addObject("pages", pages);
				mav.addObject("size",list.size());
				mav.addObject("zxsize",zxlist.size());
				mav.addObject("dksize",dklist.size());
			} else {
				mav = new ModelAndView("/sys/SysWxyh/wxbd");
				mav.addObject("openId", openId);
				mav.addObject("url", "/wfw/ZsXsjzdxx/toZsxsjzdxx");
			}

			String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

			// 注意 URL 一定要动态获取，不能 hardcode
			// String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String url1 = "http://" + request.getServerName() // 服务器地址
					+ ":" + request.getServerPort() // 端口号
					+ httpRequest.getContextPath() // 项目名称
					+ httpRequest.getServletPath(); // 请求页面或其他地址
			// + "?" + (httpRequest.getQueryString()); //参数
			Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
			// for (Map.Entry entry : ret.entrySet()) {
			// System.out.println(entry.getKey() + ", " + entry.getValue());
			// }
			mav.addObject("map", ret);

			return mav;
		}
	
	
	/**
	 * 奖助贷信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toCxjzdxx")
	@ResponseBody
	public ModelAndView toCxjzdxx(String classname,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav =new ModelAndView("/wfw/ZsXsjzdxxfdy");
		String lx=request.getParameter("lx");
		if(lx == null || "".equalsIgnoreCase(lx)){
			lx="1";
		}
		String myclassname = "";
		if(classname !=null && !"".equalsIgnoreCase(classname)){
			myclassname = classname;
		}
		myclassname = new String(myclassname.getBytes("ISO-8859-1"), "UTF-8"); 
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String, Object>> cjlist = zsXsjzdxxService.getWxyh(openId);
		String yhbh = cjlist.get(0).get("yhid").toString();
		List<Map<String, Object>> list=null;
		List<Map<String, Object>> zxlist=null;
		List<Map<String, Object>> dklist=null;
		if (lx.equals("1")) {
			list = zsXsjzdxxService.getJzdxxList(myclassname, openId,request, yhbh);
			 zxlist = zsXsjzdxxService.getZxxxList("", openId, request, yhbh);
			 dklist = zsXsjzdxxService.getDkxxList("", openId, request, yhbh);
		}else if (lx.equals("2")) {
			list = zsXsjzdxxService.getJzdxxList("", openId,request, yhbh);
			 zxlist = zsXsjzdxxService.getZxxxList(myclassname, openId, request, yhbh);
			 dklist = zsXsjzdxxService.getDkxxList("", openId, request, yhbh);
		}else if (lx.equals("3")) {
			list = zsXsjzdxxService.getJzdxxList("", openId,request, yhbh);
			 zxlist = zsXsjzdxxService.getZxxxList("", openId, request, yhbh);
			 dklist = zsXsjzdxxService.getDkxxList(myclassname, openId, request, yhbh);
		}

			if (list != null && list.size() != 0) {
				mav.addObject("list",list);
				
			}
			if (zxlist != null && zxlist.size() != 0) {
				mav.addObject("zxlist", zxlist);
			}
			if (dklist != null && dklist.size() != 0) {
				mav.addObject("dklist", dklist);
			}
				List<Map<String,Object>> bjs=zsXsjzdxxService.getBj(openId);
				if(bjs.size()!=0){
	   				mav.addObject("bjs",bjs);
	   			}
				mav.addObject("myclassname", myclassname);
				mav.addObject("openId", openId);
				mav.addObject("pages", pages);
				mav.addObject("lx",lx);
			String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

			// 注意 URL 一定要动态获取，不能 hardcode
			// String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String url1 = "http://" + request.getServerName() // 服务器地址
					+ ":" + request.getServerPort() // 端口号
					+ httpRequest.getContextPath() // 项目名称
					+ httpRequest.getServletPath(); // 请求页面或其他地址
			// + "?" + (httpRequest.getQueryString()); //参数
			Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
			// for (Map.Entry entry : ret.entrySet()) {
			// System.out.println(entry.getKey() + ", " + entry.getValue());
			// }
			mav.addObject("map", ret);

			return  mav;
		}
	
	/**
	 * 奖助贷信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toFdyDate1")
	@ResponseBody
	public List<Map<String, Object>> toFdyDate1(String classname,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav =new ModelAndView("/wfw/ZsXsjzdxxfdy");
		String myclassname = "";
		if(classname !=null && !"".equalsIgnoreCase(classname)){
			myclassname = classname;
		}
		myclassname = new String(myclassname.getBytes("ISO-8859-1"), "UTF-8"); 
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String, Object>> cjlist = zsXsjzdxxService.getWxyh(openId);
		String yhbh = cjlist.get(0).get("yhid").toString();
		List<Map<String, Object>> list = zsXsjzdxxService.getJzdxxList(myclassname,openId,request,yhbh);
				List<Map<String,Object>> bjs=zsXsjzdxxService.getBj(openId);
				if (list != null && list.size() != 0) {
					mav.addObject("list",list);
				}
				if(bjs.size()!=0){
	   				mav.addObject("bjs",bjs);
	   			}
				mav.addObject("classname", classname);
				mav.addObject("openId", openId);
				mav.addObject("pages", pages);
				mav.addObject("size",list.size());

			String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

			// 注意 URL 一定要动态获取，不能 hardcode
			// String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String url1 = "http://" + request.getServerName() // 服务器地址
					+ ":" + request.getServerPort() // 端口号
					+ httpRequest.getContextPath() // 项目名称
					+ httpRequest.getServletPath(); // 请求页面或其他地址
			// + "?" + (httpRequest.getQueryString()); //参数
			Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
			// for (Map.Entry entry : ret.entrySet()) {
			// System.out.println(entry.getKey() + ", " + entry.getValue());
			// }
			mav.addObject("map", ret);

			return  list;
		}
	
	/**
	 * 奖助贷信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toFdyDate2")
	@ResponseBody
	public List<Map<String, Object>> toFdyDate2(String classname,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav =new ModelAndView("/wfw/ZsXsjzdxxfdy");
		String myclassname = "";
		if(classname !=null && !"".equalsIgnoreCase(classname)){
			myclassname = classname;
		}
		myclassname = new String(myclassname.getBytes("ISO-8859-1"), "UTF-8"); 
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String, Object>> cjlist = zsXsjzdxxService.getWxyh(openId);
		String yhbh = cjlist.get(0).get("yhid").toString();
		List<Map<String, Object>> zxlist = zsXsjzdxxService.getZxxxList(myclassname,openId,request,yhbh);
				List<Map<String,Object>> bjs=zsXsjzdxxService.getBj(openId);
				if (zxlist != null && zxlist.size() != 0) {
					mav.addObject("list",zxlist);
				}
				if(bjs.size()!=0){
	   				mav.addObject("bjs",bjs);
	   			}
				mav.addObject("classname", classname);
				mav.addObject("openId", openId);
				mav.addObject("pages", pages);
				mav.addObject("zxlist",zxlist.size());

			String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

			// 注意 URL 一定要动态获取，不能 hardcode
			// String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String url1 = "http://" + request.getServerName() // 服务器地址
					+ ":" + request.getServerPort() // 端口号
					+ httpRequest.getContextPath() // 项目名称
					+ httpRequest.getServletPath(); // 请求页面或其他地址
			// + "?" + (httpRequest.getQueryString()); //参数
			Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
			// for (Map.Entry entry : ret.entrySet()) {
			// System.out.println(entry.getKey() + ", " + entry.getValue());
			// }
			mav.addObject("map", ret);

			return  zxlist;
		}
	
	/**
	 * 奖助贷信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toFdyDate3")
	@ResponseBody
	public List<Map<String, Object>> toFdyDate3(String classname,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav =new ModelAndView("/wfw/ZsXsjzdxxfdy");
		String myclassname = "";
		if(classname !=null && !"".equalsIgnoreCase(classname)){
			myclassname = classname;
		}
		myclassname = new String(myclassname.getBytes("ISO-8859-1"), "UTF-8"); 
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		List<Map<String, Object>> cjlist = zsXsjzdxxService.getWxyh(openId);
		String yhbh = cjlist.get(0).get("yhid").toString();

		List<Map<String, Object>> dklist = zsXsjzdxxService.getDkxxList(myclassname,openId,request,yhbh);
				List<Map<String,Object>> bjs=zsXsjzdxxService.getBj(openId);
				if (dklist != null && dklist.size() != 0) {
					mav.addObject("list",dklist);
				}
				if(bjs.size()!=0){
	   				mav.addObject("bjs",bjs);
	   			}
				mav.addObject("classname", classname);
				mav.addObject("openId", openId);
				mav.addObject("pages", pages);
				mav.addObject("dklist",dklist.size());

			String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

			// 注意 URL 一定要动态获取，不能 hardcode
			// String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String url1 = "http://" + request.getServerName() // 服务器地址
					+ ":" + request.getServerPort() // 端口号
					+ httpRequest.getContextPath() // 项目名称
					+ httpRequest.getServletPath(); // 请求页面或其他地址
			// + "?" + (httpRequest.getQueryString()); //参数
			Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
			// for (Map.Entry entry : ret.entrySet()) {
			// System.out.println(entry.getKey() + ", " + entry.getValue());
			// }
			mav.addObject("map", ret);

			return  dklist;
		}
	
	
	/**
	 * 奖助贷信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toZsxsjzdxxDetail")
	@ResponseBody
	public ModelAndView toZsxsjzdxxDetail(String xh,String lx,String openId)  {
		ModelAndView mav=new ModelAndView("/wfw/zsXsjzdxxList");
   	            List<Map<String, Object>> xslist = zsXsjzdxxService.getXsxx(xh);
				List<Map<String, Object>> list = zsXsjzdxxService.getJzdxxListDetail(xh);
				List<Map<String, Object>> zxlist = zsXsjzdxxService.getZxxxListDetail(xh);
				List<Map<String, Object>> dklist = zsXsjzdxxService.getDkxxListDetail(xh);
				if(xslist.size()!=0){
		   			mav.addObject("xslist", xslist);
		   		}
		   		if(list.size()!=0){
		   			mav.addObject("list", list);
		   		}
		   		if(zxlist.size()!=0){
		   			mav.addObject("zxlist", zxlist);
		   		}
		   		if(dklist.size()!=0){
		   			mav.addObject("dklist", dklist);
		   		}
		   		mav.addObject("openId",openId);
		   		mav.addObject("lx",lx);
		   		return mav;		
		}
}
