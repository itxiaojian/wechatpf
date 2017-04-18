package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.sliu.framework.app.wfw.service.ZsXsgkxxService;

/**
 * 学生挂科信息
 * @author zhangyan
 * @date 2016年8月8日
 */
@Controller
@RequestMapping("/wfw/ZsXsgkxx")
public class ZsXsgkxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXsgkxxController.class);
	
	@Autowired
	private ZsXsgkxxService zsXsgkxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;

	/**
	 * 跳转到学生挂科信息页面
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toGkxx")
   	public ModelAndView toGkxx(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wfw/zsXsgkxxList");
//	   		List<Map<String,Object>> xslist=zsXsgkxxService.getXsxx("",openId,request);
	   		List<Map<String,Object>> gklist=zsXsgkxxService.getGkxx("","",openId,request);
	   		List<Map<String,Object>> bjxxlist=zsXsgkxxService.getBj(openId);
	   		List<Map<String,Object>> qhlist=zsXsgkxxService.getKsqh(openId);
	   		if(gklist.size()!=0){
	   			mav.addObject("gklist", gklist);
	   		}
	   		if(qhlist.size()!=0){
	   			mav.addObject("qhlist", qhlist);
	   		}
	   		if(bjxxlist.size()!=0){
				mav.addObject("bjxxlist", bjxxlist);
			}
	   		mav.addObject("openId",openId);
	   		mav.addObject("pages", pages);
			mav.addObject("size",gklist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXsgkxx/toGkxx");
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
	 * 挂科信息-加载更多
	 @throws UnsupportedEncodingException 
	 *@Author zhangyan
	 *@Date 2016年8月20日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/gkjzgd")
	@ResponseBody
	public List<Map<String,Object>> gkjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=new ModelAndView("/wfw/zsXsgkxxList");
   		String openId =request.getParameter("openId");
   		String qh =request.getParameter("qh");
   		String bjmc =request.getParameter("bjmc");
   		List<Map<String,Object>> gklist=zsXsgkxxService.getGkxx(qh,bjmc,openId,request);
   		if(gklist.size()!=0){
   			mav.addObject("gklist", gklist);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("qh", qh); 
   		mav.addObject("bjmc", bjmc); 
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",gklist.size());
		
		return gklist;
		
	}
	
	/**
	 * 根据学期号查询对应的挂科信息
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toGkxxByQh")
   	public ModelAndView toXsapByQh(String ksqh,String bjmc,HttpServletRequest request) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgkxxList");
   		String openId =request.getParameter("openId");
   		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8"); 
   		bjmc = new String(bjmc.getBytes("ISO-8859-1"),"UTF-8");
//   		List<Map<String,Object>> xslist=zsXsgkxxService.getXsxx(ksqh,bjmc,openId,request);
   		List<Map<String,Object>> gklist=zsXsgkxxService.getGkxx(ksqh,bjmc,openId,request);
   		List<Map<String,Object>> bjxxlist=zsXsgkxxService.getBj(openId);
   		List<Map<String,Object>> qhlist=zsXsgkxxService.getKsqh(openId);
   		if(gklist.size()!=0){
   			mav.addObject("gklist", gklist);
   		}
   		if(qhlist.size()!=0){
   			mav.addObject("qhlist", qhlist);
   		}
   		if(bjxxlist.size()!=0){
			mav.addObject("bjxxlist", bjxxlist);
		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("qh", ksqh); 
   		mav.addObject("bjmc", bjmc); 
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",gklist.size());
		
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
	 * 查询学生挂科详细信息
	 * @author zhangyan
	 * @date 2016年8月12日
	 * @param id
	 * @param openId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toGkxxDetail")
	@ResponseBody
   	public ModelAndView toGkxxDetail(String xh,String ksqh,String openId) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgkxxDetail");
   		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8"); 
   		List<Map<String,Object>> xslist=zsXsgkxxService.getXsxxDetail(xh,openId);
   		List<Map<String,Object>> xxlist=zsXsgkxxService.getGkxxDetail(xh,ksqh,openId);
   		if(xslist.size()!=0){
   			mav.addObject("xslist", xslist);
   		}
   		if(xxlist.size()!=0){
   			mav.addObject("xxlist", xxlist);
   		}
   		mav.addObject("openId",openId);
   		return mav;
   	}
	
}
