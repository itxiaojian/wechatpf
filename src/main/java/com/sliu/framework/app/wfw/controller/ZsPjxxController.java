package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wfw.model.ZsJxzlpj;
import com.sliu.framework.app.wfw.model.ZsPjxx;
import com.sliu.framework.app.wfw.service.ZsPjxxService;

/**
 * 评教信息
 * 
 * @author duanpeijun
 * @version 创建时间：2015年6月14日
 */
@Controller
@RequestMapping("/wfw/ZsPjxx")
public class ZsPjxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsPjxxController.class);

	@Autowired
	private ZsPjxxService zsPjxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;

	/**
	 * 评教信息列表
	 * 
	 * @author duanpeijun
	 * @date 2015年6月24日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toPjxx")
	@ResponseBody
	public ModelAndView toPjxx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsPjxxList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		//String openId = AppUtil.getOpenId(request, response);
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = zsPjxxService.getPjxxList(request);
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		}
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
			mav.addObject("pages", pages);
			mav.addObject("openId", openId);
			mav.addObject("size",list.size());
		}
		
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
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
		
		return mav;
	}
	
	/**
	 * 评教信息列表
	 * 
	 * @author duanpeijun
	 * @date 2015年6月24日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toPjxxZj")
	@ResponseBody
	public List<Map<String, Object>> toPjxxZj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsPjxxList");
		List<Map<String, Object>> list = zsPjxxService.getPjxxList(request);
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		//String openId = AppUtil.getOpenId(request, response);
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
			mav.addObject("pages", pages);
			mav.addObject("openId", openId);
			mav.addObject("size",list.size());
		}
		
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
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
		
		return  list;
	}

	/**
	 * 评教具体信息
	 * 
	 * @author duanpeijun
	 * @date 2015年6月24日
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail(String id, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsPjxxDetail");
		String openId = AppUtil.getOpenId(request, response);
		List<Map<String, Object>> list = zsPjxxService.getPjxxDetail(request,
				id);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		}
			mav.addObject("openId", openId);
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
//	        for (Map.Entry entry : ret.entrySet()) {
//	            System.out.println(entry.getKey() + ", " + entry.getValue());
//	        }
	        mav.addObject("map", ret);
		return mav;
	}

	/**
	 * 保存教学质量评价信息
	 * 
	 * @author duanpeijun
	 * @date 2015年6月25日
	 * @param id
	 * @param zsskqk
	 *            准时上课情况
	 * @param ktqfpj
	 *            课堂气氛评价
	 * @param sshdpj
	 *            师生互动评价
	 * @param jstdpj
	 *            教师态度评价
	 * @param zysppj
	 *            专业水平评价
	 * @return
	 */
	@RequestMapping(value = "/saveJxzlpj")
	@ResponseBody
	public ResponseData saveJxzlpj(Long id, String zsskqk, String ktqfpj,
			String sshdpj, String jstdpj, String zysppj) {
		List list = zsPjxxService.getJxzlpjList(id);
		if (list.size() > 0) {
			return ResponseData.FAILED_NO_DATA;
		} else {
			ZsJxzlpj entity = new ZsJxzlpj();
			entity.setPjid(id);
			entity.setZsskqk(zsskqk);
			entity.setKtqfpj(ktqfpj);
			entity.setSshdpj(sshdpj);
			entity.setJstdpj(jstdpj);
			entity.setZysppj(zysppj);
			entity.setTjsj(Calendar.getInstance().getTime());

			String result = zsPjxxService.saveJxzlpj(entity, id);
			if (!"1".equalsIgnoreCase(result)) {
				return ResponseData.FAILED_NO_DATA;
			}
			return ResponseData.SUCCESS_NO_DATA;
		}
	}

	/**
	 * 功能：点击评教信息后台之后弹出的信息列表
	 * 
	 * @author duanpeijun
	 * @date 2015年6月24日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getPjxx")
	@ResponseBody
	public Pagination<Map<String, Object>> getPjxx(Integer start, Integer limit) {

		return zsPjxxService.getPjxx(start, limit);
	}

	/**
	 * 
	 * @author duanpeijun
	 * @date 2015年6月24日
	 * @return
	 */
	@RequestMapping(value = "/pjxxPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/wfw/zsPjxxHtList");
		return modelAndView;
	}

	/**
	 * 增加
	 * 
	 * @author duanpeijun
	 * @date 2015年6月24日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseData save(ModelMap model, ZsPjxx entity,
			HttpServletRequest request, HttpServletResponse response) {
		zsPjxxService.savePjxx(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}

	/**
	 * 编辑
	 * 
	 * @author duanpeijun
	 * @version 2015年6月24日
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ZsPjxx entity, String id) {
		zsPjxxService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}

	/**
	 * 删除
	 * 
	 * @author duanpeijun
	 * @version 2015年6月24日
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		zsPjxxService.delete(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}

}
