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
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsPksxxService;

/**
 * 
 * @author duanpeijun
 * @version 创建时间：2015年6月9日
 */
@Controller
@RequestMapping("/wfw/ZsPksxx")
public class ZsPksxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsPksxxController.class);

	@Autowired
	private ZsPksxxService zsPksxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;

	/**
	 * 贫困生信息列表
	 * 
	 * @author duanpeijun
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toPksxx")
	@ResponseBody
	public ModelAndView toPksxx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsPksxxList");
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
		List<Map<String, Object>> list = zsPksxxService.getPksxxList(request);
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
	 * 辅导员进入展示班级贫困生列表
	 * @author zhangyan
	 * @date 2016年8月11日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toPksyjList")
	@ResponseBody
	public ModelAndView toPksyjList(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsPksyjPage");
		String code=request.getParameter("code");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
			List<Map<String, Object>> yjlist = zsPksxxService.getPksyjList(request,code);
		if (yjlist.size() != 0) {
			mav.addObject("yjlist", yjlist);
		}
		mav.addObject("pages", pages);
		mav.addObject("tj", code);
		mav.addObject("openId", openId);
		mav.addObject("size",yjlist.size());
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
	 * 贫困预警-加载更多
	 @throws UnsupportedEncodingException 
	 *@Author zhangyan
	 *@Date 2016年8月20日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/pkjzgd")
	@ResponseBody
	public List<Map<String,Object>> pkjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/wfw/zsPksyjPage");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String tj = request.getParameter("tj");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> yjlist = zsPksxxService.getPksyjList(request,tj);
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
			
		if (yjlist.size() != 0) {
			mav.addObject("yjlist", yjlist);
		}
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("yjlist",yjlist.size());
		}
		return yjlist;
		
	}
	
	/**
	 * 查询贫困生消费记录
	 * @author zhangyan
	 * @date 2016年8月11日
	 * @param id
	 * @param openId
	 * @return
	 */
	@RequestMapping(value="/toPksxfxx")
	@ResponseBody
   	public ModelAndView toPksxfxx(Long id,String openId){
   		ModelAndView mav=new ModelAndView("/wfw/zsPksxfxxPage");
   		List<Map<String,Object>> pksxxlist=zsPksxxService.getPksxx(id,openId);
   		String xh = pksxxlist.get(0).get("xh").toString();
   		List<Map<String,Object>> khlist=zsPksxxService.getXfxx(xh);
   		List<Map<String,Object>> xfxxlist=zsPksxxService.getXfxx(xh);
   		if(pksxxlist.size()!=0){
   			mav.addObject("pksxxlist", pksxxlist);
   		}
   		if(xfxxlist.size()!=0){
   			mav.addObject("xfxxlist", xfxxlist);
   		}
   		if(khlist.size()!=0){
   			mav.addObject("khlist", khlist);
   		}
   		mav.addObject("openId",openId);
   		return mav;
   	}

}
