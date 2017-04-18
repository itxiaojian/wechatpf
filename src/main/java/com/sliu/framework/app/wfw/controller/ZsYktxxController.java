//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsYktxxService;

/**
 * 一卡通信息
 * @author liujiasen
 * @date 2015年5月25日
 */
@Controller
@RequestMapping("/wfw/ZsYktxx")
public class ZsYktxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsYktxxController.class);
	
	@Autowired
	private ZsYktxxService zsYktxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	/**
	 * 跳转到信息页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toYktxx")
   	public ModelAndView toYktxx(HttpServletRequest request){
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			mav=new ModelAndView("/wfw/zsYktxxList");
	   		//SimpleDateFormat sim=new SimpleDateFormat("yyyy.MM");
	   		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-MM");
	   		Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 0);
			String xfsj = sim1.format(c.getTime());
			List<Map<String,Object>> list=zsYktxxService.getYktxx(openId);
			List<Map<String,Object>> Xflist=zsYktxxService.getYktxfxx(xfsj,openId);
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
	   		if(list!=null && list.size()!=0){
	   			mav.addObject("map1", list.get(0));
	   		}
	   		if(Xflist!=null && Xflist.size()!=0){
	   			mav.addObject("Xflist", Xflist);
	   		}
	   		//mav.addObject("date", sim.format(c.getTime()));
//	   		mav.addObject("time", sim1.format(c.getTime()));
//	   		mav.addObject("date1", sim1.format(c.getTime()));
	   		mav.addObject("time", sim1.format(new Date()));
	   		mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsYktxx/toYktxx");
		}
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
	 * 查询上月或下月一卡通信息=
	 * @author liujiasen
	 * @date 2015年6月12日
	 * @return
	 */
	@RequestMapping(value="/toYktxxBysj")
   	public ModelAndView toZggzxxBysj(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsYktxxList");
   		String num=request.getParameter("time");
   		String openId =request.getParameter("openId");
   		//int nu=Integer.parseInt(request.getParameter("addflag"));
   		//SimpleDateFormat sim=new SimpleDateFormat("yyyy.MM");
   		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-MM");
   		Date date=new Date();
   		try {
			date=sim1.parse(num);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//c.add(Calendar.MONTH, nu);
		List<Map<String,Object>> list=zsYktxxService.getYktxx(openId);
   		List<Map<String,Object>> Xflist=zsYktxxService.getYktxfxx(sim1.format(c.getTime()),openId);
   		if(list!=null && list.size()!=0){
   			mav.addObject("map1", list.get(0));
   		}
   		if(Xflist!=null && Xflist.size()!=0){
   			mav.addObject("Xflist", Xflist);
   		}
   		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		}
   		//mav.addObject("date", sim.format(c.getTime()));
   		mav.addObject("time", num);
   		//mav.addObject("date1", sim1.format(c.getTime()));
   		mav.addObject("openId",openId);
   		
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
	 * 学生擅自离校预警
	 * @author zhangyan
	 * @date 2016年8月16日
	 * @return
	 */
	@RequestMapping(value="/toLxyj")
	@ResponseBody
	public ModelAndView toLxyj(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsXslxyjList");
		String pages = request.getParameter("pages");
		String code = request.getParameter("code");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
			List<Map<String, Object>> xflist=zsYktxxService.getXfList(request,code);
		    if (xflist.size() != 0) {
			       mav.addObject("xflist", xflist);
		    }
		mav.addObject("pages", pages);
		mav.addObject("tj", code);
		mav.addObject("openId", openId);
		mav.addObject("size",xflist.size());
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
	 * 离校预警-加载更多
	 @throws UnsupportedEncodingException 
	 *@Author zhangyan
	 *@Date 2016年8月20日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/lxjzgd")
	@ResponseBody
	public List<Map<String,Object>> sgjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/wfw/zsXslxyjList");
		String tj = request.getParameter("tj");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		List<Map<String, Object>> xflist=zsYktxxService.getXfList(request,tj);
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		    if (xflist.size() != 0) {
		    	mav.addObject("xflist", xflist);
		    }
		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("size",xflist.size());
		}
		return xflist;
	}
	
}
