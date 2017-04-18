//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsTsjyxxService;

/**
 * 图书借阅信息
 * @author liujiasen
 * @date 2015年5月21日
 */
@Controller
@RequestMapping("/wfw/ZsTsjyxx")
public class ZsTsjyxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsTsjyxxController.class);
	
	@Autowired
	private ZsTsjyxxService zsTsjyxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 跳转到图书借阅信息页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toTsjyxx")
   	public ModelAndView toTsjyxx(HttpServletRequest request){
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
	   		mav=new ModelAndView("/wfw/zsTsjyxxList");
	   		//SimpleDateFormat sim=new SimpleDateFormat("yyyy.MM");
//	   		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 0);
			//String ghsj = sim.format(c.getTime());
			
			List<Map<String,Object>> cjlist=zsTsjyxxService.getGrTsjyxxBysj(openId,request);
			String yhbh = "";
//			for(Map<String,Object> map:cjlist){
//				yhbh = yhbh+map.get("bh").toString()+",";
//			}
			for (int i = 0; i < cjlist.size(); i++){
				if(i<cjlist.size()-1){
				yhbh += "'"+cjlist.get(i).get("bh").toString()+"',";
				}
				else{
				yhbh += "'"+cjlist.get(i).get("bh").toString()+"'";
				}
			}
			if(cjlist.size()==0){yhbh="''";}
			List<Map<String,Object>> wghlist=zsTsjyxxService.getwghxx("",openId,request,yhbh);
			List<Map<String,Object>> yghlist=zsTsjyxxService.getyghxx("",openId,request,yhbh);
	   		if(cjlist != null&&  cjlist.size()!=0){
	   			mav.addObject("cjlist", cjlist);
	   		}
	   		if(wghlist != null&&  wghlist.size()!=0){
	   			mav.addObject("wghlist", wghlist);
	   		}
	   		if(yghlist != null&&  yghlist.size()!=0){
	   			mav.addObject("yghlist", yghlist);
	   		}
	   		//mav.addObject("date", ghsj);
	   		mav.addObject("openId",openId);
	   		//mav.addObject("time", sim1.format(new Date()));
	   		mav.addObject("pages", pages);
			mav.addObject("size",cjlist.size());
			mav.addObject("wghsize",wghlist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsTsjyxx/toTsjyxx");
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
	 * 加载更多查询借阅信息
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toTsjyxxZj")
	@ResponseBody
   	public List<List<Map<String,Object>>> toTsjyxxZj(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsTsjyxxList");
   		List<List<Map<String,Object>>> newlist=new ArrayList<List<Map<String,Object>>>(); 
   		String num=request.getParameter("time");
   		String openId =request.getParameter("openId");
   		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-MM");
   		Date date=new Date();
   		if(num!= null && !"".equals(num)){
   			try {
   				date=sim1.parse(num);
   			} catch (ParseException e) {
   				e.printStackTrace();
   			}
   		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		List<Map<String,Object>> cjlist=zsTsjyxxService.getGrTsjyxxBysj(openId,request);
		String yhbh="";
		for (int i = 0; i < cjlist.size(); i++){
			if(i<cjlist.size()-1){
			yhbh += "'"+cjlist.get(i).get("bh").toString()+"',";
			}
			else{
			yhbh += "'"+cjlist.get(i).get("bh").toString()+"'";
			}
		}
		if(cjlist.size()==0){yhbh="''";}
		List<Map<String,Object>> wghlist=zsTsjyxxService.getwghxx(num,openId,request,yhbh);
		List<Map<String,Object>> yghlist=zsTsjyxxService.getyghxx(num,openId,request,yhbh);
   		if(cjlist!=null && cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		if(wghlist != null&&  wghlist.size()!=0){
   			mav.addObject("wghlist", wghlist);
   		}
   		if(yghlist != null&&  yghlist.size()!=0){
   			mav.addObject("yghlist", yghlist);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("time", num);
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
		newlist.add(cjlist);
		newlist.add(wghlist);
		newlist.add(yghlist);
   		return newlist;
   	}
	
	/**
	 * 按照借出时间查询借阅信息
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toTsjyxxBysj")
   	public ModelAndView toTsjyxxBysj(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsTsjyxxList");
   		String num=request.getParameter("time");
   		String openId =request.getParameter("openId");
//   		int nu=Integer.parseInt(request.getParameter("addflag"));
   		//SimpleDateFormat sim=new SimpleDateFormat("yyyy.MM");
   		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-MM");
   		Date date=new Date();
   		if(num!= null && !"".equals(num)){
   			try {
   				date=sim1.parse(num);
   			} catch (ParseException e) {
   				e.printStackTrace();
   			}
   		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
//		String ghsj = sim1.format(c.getTime());
		//String ghsj1 = sim.format(c.getTime());
		List<Map<String,Object>> cjlist=zsTsjyxxService.getGrTsjyxxBysj(openId,request);
		String yhbh="";
		for (int i = 0; i < cjlist.size(); i++){
			if(i<cjlist.size()-1){
			yhbh += "'"+cjlist.get(i).get("bh").toString()+"',";
			}
			else{
			yhbh += "'"+cjlist.get(i).get("bh").toString()+"'";
			}
		}
		if(cjlist.size()==0){yhbh="''";}
//		for(Map<String,Object> map:cjlist){
//			yhbh = yhbh+map.get("bh").toString()+",";
//		}
		
		List<Map<String,Object>> wghlist=zsTsjyxxService.getwghxx(num,openId,request,yhbh);
		List<Map<String,Object>> yghlist=zsTsjyxxService.getyghxx(num,openId,request,yhbh);
   		if(cjlist!=null && cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		if(wghlist != null&&  wghlist.size()!=0){
   			mav.addObject("wghlist", wghlist);
   		}
   		if(yghlist != null&&  yghlist.size()!=0){
   			mav.addObject("yghlist", yghlist);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("time", num);
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
	 * 根据班级编号跳转到学生实习信息页面
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/toTsjyph")
   	public ModelAndView toXssxqkByQh(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsTsjyphbList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> phList=zsTsjyxxService.getPh(request);
   		if(phList.size()!=0){
   			mav.addObject("phList",phList);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();
        // 注意 URL 一定要动态获取，不能 hardcode
        //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
        HttpServletRequest httpRequest=(HttpServletRequest)request; 
        String url1 = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + httpRequest.getContextPath()      //项目名称  
                + httpRequest.getServletPath()  ;    //请求页面或其他地址  
                //+ "?" + (httpRequest.getQueryString()); //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
   		mav.addObject("openId",openId);
   		mav.addObject("pages",pages);
   		return mav;
   	}
	
	/**
	 * 根据班级编号跳转到学生实习信息页面
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/toTsjyphZj")
	@ResponseBody
   	public List<Map<String,Object>> toTsjyph(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsTsjyphbList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> phList=zsTsjyxxService.getPh(request);
   		if(phList.size()!=0){
   			mav.addObject("phList",phList);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
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
               // + "?" + (httpRequest.getQueryString()); //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
   		mav.addObject("openId",openId);
   		mav.addObject("pages",pages);
   		return phList;
   	}
	
	
	/**
	 * 图书详情
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/getTsxqById")
   	public ModelAndView toGetTsxq(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsTsjyxqDetail");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> XqList=zsTsjyxxService.getXq(request);
   		if(XqList.size()!=0){
   			mav.addObject("XqList",XqList);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();
        // 注意 URL 一定要动态获取，不能 hardcode
        //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
        HttpServletRequest httpRequest=(HttpServletRequest)request; 
        String url1 = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + httpRequest.getContextPath()      //项目名称  
                + httpRequest.getServletPath()      //请求页面或其他地址  
                + "?" + (httpRequest.getQueryString()); //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
   		mav.addObject("openId",openId);
   		mav.addObject("pages",pages);
   		return mav;
   	}
	
	/**
	 * 图书归还的列表
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/toTsjygh")
   	public ModelAndView toTsjygh(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsTsjyghList");
   		String openId =request.getParameter("openId");
   		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
   		Calendar c = Calendar.getInstance();
   		c.add(Calendar.DATE, -3);
   		String dateStr =  sim.format(c.getTime());
   		Calendar _c = Calendar.getInstance();
   		_c.add(Calendar.DATE, 0);
   		String _dateStr =  sim.format(_c.getTime());
   		List<Map<String,Object>> ghListBe=zsTsjyxxService.getGhBe(request,dateStr,_dateStr);
   		List<Map<String,Object>> ghListTo=zsTsjyxxService.getGhTo(request,_dateStr);
   		if(ghListBe.size()!=0){
   			mav.addObject("ghListBe",ghListBe);
   		}
   		if(ghListTo.size()!=0){
   			mav.addObject("ghListTo",ghListTo);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();
        // 注意 URL 一定要动态获取，不能 hardcode
        //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
        HttpServletRequest httpRequest=(HttpServletRequest)request; 
        String url1 = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + httpRequest.getContextPath()      //项目名称  
                + httpRequest.getServletPath()      //请求页面或其他地址  
                + "?" + (httpRequest.getQueryString()); //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
   		mav.addObject("openId",openId);
   		mav.addObject("pages",pages);
   		return mav;
   	}
	
}
