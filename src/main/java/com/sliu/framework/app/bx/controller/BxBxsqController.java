//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.bx.controller;

import java.util.Date;
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
import com.sliu.framework.app.bx.service.BxBxsqService;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;

/**
 * 报修申请
 * @author liujiansen
 * @date 2015年8月6日
 */
@Controller
@RequestMapping("/wx/Bxsq")
public class BxBxsqController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(BxBxsqController.class);
	
	@Autowired
	private BxBxsqService service;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 报修申请
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsq")
   	public ModelAndView toBxsq(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/bx/bxsq/bxSqView");
   		String openId=request.getParameter("openId");
   		List<Map<String,Object>> wxdl=service.getWxDl();
   		List<Map<String,Object>> ly=service.getLy();
   		if(wxdl.size()!=0){
   			mav.addObject("wxdl", wxdl);
   		}
   		if(ly.size()!=0){
   			mav.addObject("ly", ly);
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
        mav.addObject("map1", ret);
   		mav.addObject("openId", openId);
   		return mav;
   	}
	
	/**
	 * 根据上级主题编号获取维修的小类
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getWxxl")
	@ResponseBody
	public List<Map<String,Object>> getWxxl(HttpServletRequest request) {
		String sjzt=request.getParameter("sjzt");
		return service.getWxxl(sjzt);
	}
	
	/**
	 * 申请数据保存
	 * @author liujiansen
	 * @date 2015年8月7日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(HttpServletRequest request) {
		String str="";
		str=service.save(request);
		return str;
	}
	
	/**
	 * 流程申请信息列表及审批记录显示页面
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsqlcjl")
   	public ModelAndView toBxsqlcjl(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav = new ModelAndView();
		String url = "";
//   		String id=request.getParameter("id");
//   		List<Map<String,Object>> sq=service.getSq(id);
//   		List<Map<String,Object>> spxx=service.getSpxx(id);
   		String openId=request.getParameter("openId");
   		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			url="/bx/bxsq/bxSqlcjl";
		}else{
			url="/sys/SysWxyh/wxbd";
			mav.addObject("url","/wx/Bxsq/toBxsqlcjl");
		}
   		List<Map<String,Object>> sq=service.getSq(openId);
   		List<Map<String,Object>> spxx=service.getSpxx(openId);
   		if(sq.size()!=0){
//   		mav.addObject("map", sq.get(0));
   			mav.addObject("map", sq);
   			mav.addObject("size", sq.size());
   		}
   		if(spxx.size()!=0){
   			mav.addObject("spxx", spxx);
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
        mav.addObject("map1", ret);
   		mav.addObject("openId", openId);
   		mav.setViewName(url);
   		return mav;
   	}
	
	/**
	 * 申请修改
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsqUpdate")
   	public ModelAndView toBxsqUpdate(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/bx/bxsq/bxSqUpdate");
   		String openId=request.getParameter("openId");
   		String id=request.getParameter("id");
   		List<Map<String,Object>> sq=service.getSqxx(id);
   		List<Map<String,Object>> wxdl=service.getWxDl();
   		List<Map<String,Object>> ly=service.getLy();
   		if(wxdl.size()!=0){
   			mav.addObject("wxdl", wxdl);
   		}
   		if(ly.size()!=0){
   			mav.addObject("ly", ly);
   		}
   		if(sq.size()!=0){
			mav.addObject("map", sq.get(0));
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
        mav.addObject("map1", ret);
   		mav.addObject("openId", openId);
   		return mav;
   	}
	
	/**
	 * 申请数据修改保存
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(HttpServletRequest request) {
		String str="";
		str=service.update(request);
		return str;
	}
	
	/**
	 * 流程申请信息反馈页面
	 * @author liujiansen
	 * @date 2015年8月11日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsqlcfk")
   	public ModelAndView toBxsqlcfk(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/bx/bxsq/bxSqlcfk");
//   		String id=request.getParameter("id");
//   		List<Map<String,Object>> sq=service.getSq(id);
//   		List<Map<String,Object>> spxx=service.getSpxx(id);
   		String openId=request.getParameter("openId");
   		String id=request.getParameter("id");
   		List<Map<String,Object>> sq=service.getSqxx(id);
   		if(sq.size()!=0){
  			mav.addObject("map", sq.get(0));
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
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map1", ret);
   		return mav;
   	}
	
	/**
	 * 报修评价
	 * @author liujiansen
	 * @date 2015年8月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bxFwpj")
	@ResponseBody
	public String getPj(HttpServletRequest request) {
		String str="";
		str=service.bxFwpj(request);
		return str;
	}
	
	/**
	 * 获得评价，判断该用户是否有未评价的
	 * @author oufeng
	 * @date 2015年9月17日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPj")
	@ResponseBody
	public String bxFwpj(HttpServletRequest request) {
		String str="";
		str=service.getPj(request);
		return str;
	}
}

