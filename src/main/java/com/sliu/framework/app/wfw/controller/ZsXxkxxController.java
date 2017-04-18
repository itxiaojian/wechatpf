//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
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
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsXxkxxService;

/**
 * 网络选修课信息
 * @author liujiasen
 * @date 2015年6月3日
 */
@Controller
@RequestMapping("/wfw/ZsXxkxx")
public class ZsXxkxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXxkxxController.class);
	
	@Autowired
	private ZsXxkxxService zsXxkxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	/**
	 * 网络选修课信息
	 * @author duanpeijun
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toXxkxx")
	@ResponseBody
	public ModelAndView toXxkxx(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wfw/zsXxkxxList");
	   		List<Map<String,Object>> xslist=zsXxkxxService.getXsxx("",openId,request);
	   		List<Map<String,Object>> cjlist=zsXxkxxService.getXxkxxList("", openId,request);
	   		List<Map<String,Object>> qhlist=zsXxkxxService.getKsqh(openId);
	   		
	   		if(cjlist.size()!=0){
	   			mav.addObject("cjlist", cjlist);
	   		}
	   		if(qhlist.size()!=0){
	   			mav.addObject("qhlist", qhlist);
	   		}
	   		if(xslist.size()!=0){
	   			mav.addObject("xslist", xslist);
	   		}
	   		mav.addObject("openId",openId);
	   		mav.addObject("pages", pages);
			mav.addObject("size",cjlist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXxkxx/toXxkxx");
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
	 * 根据学期号跳转到选修课页面
	 * @author duanpeijun
	 * @date 2015年7月17日
	 * @param ksqh
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toXxkxxByQh")
   	public ModelAndView toXxkxxByQh(String ksqh,HttpServletRequest request) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wfw/zsXxkxxList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> xslist=zsXxkxxService.getXsxx(ksqh,openId,request);
   		List<Map<String,Object>> cjlist=zsXxkxxService.getXxkxxList(ksqh, openId,request);
   		List<Map<String,Object>> qhlist=zsXxkxxService.getKsqh(openId);
   		
   		if(cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		if(qhlist.size()!=0){
   			mav.addObject("qhlist", qhlist);
   		}
   		if(xslist.size()!=0){
   			mav.addObject("xslist", xslist);
   		}
   		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("qh", ksqh);
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
		
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
	 * 选修课信息-加载更多
	 @throws UnsupportedEncodingException 
	 *@Author zhangyan
	 *@Date 2016年8月20日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/xxkjzgd")
	@ResponseBody
	public List<Map<String,Object>> xsjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=new ModelAndView("/wfw/zsXxkxxList");
   		String openId =request.getParameter("openId");
   		String qh =request.getParameter("qh");
   		List<Map<String,Object>> cjlist=zsXxkxxService.getXxkxxList(qh,openId,request);
   		if(cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("qh", qh); 
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
		
		return cjlist;
		
	}
	
	/**
	 * 根据学期号跳转到选修课页面
	 * @author duanpeijun
	 * @date 2015年7月17日
	 * @param ksqh
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toXxkxxByQhZj")
	@ResponseBody
   	public List<Map<String,Object>> toXxkxxByQhZj(String ksqh,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsXxkxxList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> cjlist=zsXxkxxService.getXxkxxList(ksqh, openId,request);
   		List<Map<String,Object>> qhlist=zsXxkxxService.getKsqh(openId);
   		if(cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		if(qhlist.size()!=0){
   			mav.addObject("qhlist", qhlist);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("qh", ksqh);
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
   		return cjlist;
   	}
}
