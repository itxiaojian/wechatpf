//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.text.SimpleDateFormat;
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
import com.sliu.framework.app.wfw.service.ZsBjkbService;
import com.sliu.framework.app.wfw.service.ZsJskbService;
import com.sliu.framework.app.wfw.service.ZsXskbService;

/**
 * 课表
 * @author liujiasen
 * @date 2015年5月25日
 */
@Controller
@RequestMapping("/wfw/ZsKb")
public class ZsXskbController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXskbController.class);
	
	@Autowired
	private ZsXskbService zsXskbService;
	
	@Autowired
	private ZsBjkbService zsBjkbService;
	
	@Autowired
	private ZsJskbService zsSjkbService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 跳转到学生课表页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toXskb")
   	public ModelAndView toXskb(HttpServletRequest request){
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		String xq =request.getParameter("xq");
		String zs =request.getParameter("zs");
		String xn =request.getParameter("xn");
		/*	Map<String,Object> map=AppUtil.getXn(new Date());
		if(map!=null){
			if(xn==null || "".equals(xn)){
				xn=map.get("xn").toString();
			}
			if(xq==null || "".equals(xq)){
				xq=map.get("xq").toString();
			}
		}*/
		Map<String,Object> map=AppUtil.fromNowGetXn(new Date());
		if(map!=null){
			if(xn==null || "".equals(xn)){
				xn=map.get("xn").toString();
			}
			if(xq==null || "".equals(xq)){
				xq=map.get("xq").toString();
			}
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
   			mav=new ModelAndView("/wfw/zsXskbList");
   			List<Map<String,Object>> xskb1=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第1节'");
   			List<Map<String,Object>> xskb2=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第2节'");
   			List<Map<String,Object>> xskb3=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第3节'");
   			List<Map<String,Object>> xskb4=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第4节'");
   			List<Map<String,Object>> xskb5=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第5节'");
   			List<Map<String,Object>> xskb6=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第6节'");
   			List<Map<String,Object>> xskb7=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第7节'");
   			List<Map<String,Object>> xskb8=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第8节'");
   			List<Map<String,Object>> xskb9=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第9节'");
   			List<Map<String,Object>> xskb10=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第10节'");
   			List<Map<String,Object>> xskb11=zsXskbService.getXskbList(xn, xq, zs, openId,"jc =' 第11节'");
   			List<Map<String,Object>> xns=zsXskbService.getXn(openId);
   			List<Map<String,Object>> zss=zsXskbService.getZs();
   			if(xskb1.size()!=0){
   				mav.addObject("xskb1",xskb1);
   			}
   			if(xskb2.size()!=0){
   				mav.addObject("xskb2",xskb2);
   			}
   			if(xskb3.size()!=0){
   				mav.addObject("xskb3",xskb3);
   			}
   			if(xskb4.size()!=0){
   				mav.addObject("xskb4",xskb4);
   			}
   			if(xskb5.size()!=0){
   				mav.addObject("xskb5",xskb5);
   			}
   			if(xskb6.size()!=0){
   				mav.addObject("xskb6",xskb6);
   			}
   			if(xskb7.size()!=0){
   				mav.addObject("xskb7",xskb7);
   			}
   			if(xskb8.size()!=0){
   				mav.addObject("xskb8",xskb8);
   			}
   			if(xskb9.size()!=0){
   				mav.addObject("xskb9",xskb9);
   			}
   			if(xskb10.size()!=0){
   				mav.addObject("xskb10",xskb10);
   			}
   			if(xskb11.size()!=0){
   				mav.addObject("xskb11",xskb11);
   			}
   			if(xns.size()!=0){
   				mav.addObject("xns",xns);
   			}
   			if(zss!=null&&zss.size()!=0){
   				mav.addObject("zss",zss);
   			}
   			mav.addObject("xn",xn);
   			mav.addObject("xq",xq);
   			mav.addObject("zs",zs);
   			mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsKb/toXskb");
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
	 * 获取学生课表信息
	 * @author liujiasen
	 * @date 2015年5月27日
	 * @param showdate
	 * @param viewtype
	 * @param timezone
	 * @return
	 */
	@RequestMapping(value="/getXskb")
	@ResponseBody
	public String getXskb(String showdate,String viewtype,String timezone,String sxz,String openId){
		return zsXskbService.getXskb(showdate,sxz,openId);
	}
	
	/**
	 * 跳转到班级课表页面
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/toBjkb")
   	public ModelAndView toBjkb(HttpServletRequest request){
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
   			mav=new ModelAndView("/wfw/zsBjkbList");
   			mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsKb/toBjkb");
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
	 * 获取班级课表信息
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @param showdate
	 * @param viewtype
	 * @param timezone
	 * @param sxz
	 * @return
	 */
	@RequestMapping(value="/getBjkb")
	@ResponseBody
	public String getBjkb(String showdate,String viewtype,String timezone,String sxz,String openId){
		return zsBjkbService.getBjkb(showdate,sxz,openId);
	}
	
	/**
	 * 跳转到教师课表页面
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/toJskb")
   	public ModelAndView toJskb(HttpServletRequest request){
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		String xq =request.getParameter("xq");
		String zs =request.getParameter("zs");
		String xn =request.getParameter("xn");
		Map<String,Object> map=AppUtil.fromNowGetXn(new Date());
		if(map!=null){
			if(xn==null || "".equals(xn)){
				xn=map.get("xn").toString();
			}
			if(xq==null || "".equals(xq)){
				xq=map.get("xq").toString();
			}
		}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
   			mav=new ModelAndView("/wfw/zsJskbList");
   			List<Map<String,Object>> jskb1=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第1节'");
   			List<Map<String,Object>> jskb2=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第2节'");
   			List<Map<String,Object>> jskb3=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第3节'");
   			List<Map<String,Object>> jskb4=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第4节'");
   			List<Map<String,Object>> jskb5=zsSjkbService.getJskbList(xn, xq, zs, openId," jc =' 第5节'");
   			List<Map<String,Object>> jskb6=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第6节'");
   			List<Map<String,Object>> jskb7=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第7节'");
   			List<Map<String,Object>> jskb8=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第8节'");
   			List<Map<String,Object>> jskb9=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第9节'");
   			List<Map<String,Object>> jskb10=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第10节'");
   			List<Map<String,Object>> jskb11=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc =' 第11节'");
   			List<Map<String,Object>> xns=zsSjkbService.getXn(openId);
   			List<Map<String,Object>> zss=zsSjkbService.getZs();
   			if(jskb1.size()!=0){
   				mav.addObject("jskb1",jskb1);
   			}
   			if(jskb2.size()!=0){
   				mav.addObject("jskb2",jskb2);
   			}
   			if(jskb3.size()!=0){
   				mav.addObject("jskb3",jskb3);
   			}
   			if(jskb4.size()!=0){
   				mav.addObject("jskb4",jskb4);
   			}
   			if(jskb5.size()!=0){
   				mav.addObject("jskb5",jskb5);
   			}
   			if(jskb6.size()!=0){
   				mav.addObject("jskb6",jskb6);
   			}
   			if(jskb7.size()!=0){
   				mav.addObject("jskb7",jskb7);
   			}
   			if(jskb8.size()!=0){
   				mav.addObject("jskb8",jskb8);
   			}
   			if(jskb9.size()!=0){
   				mav.addObject("jskb9",jskb9);
   			}
   			if(jskb10.size()!=0){
   				mav.addObject("jskb10",jskb10);
   			}
   			if(jskb11.size()!=0){
   				mav.addObject("jskb11",jskb11);
   			}
   			if(xns.size()!=0){
   				mav.addObject("xns",xns);
   			}
   			if(zss!=null&&zss.size()!=0){
   				mav.addObject("zss",zss);
   			}
   			mav.addObject("xn",xn);
   			mav.addObject("xq",xq);
   			mav.addObject("zs",zs);
   			mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsKb/toJskb");
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
	 * 获取教师课表信息
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @param showdate
	 * @param viewtype
	 * @param timezone
	 * @param sxz
	 * @return
	 */
	@RequestMapping(value="/getJskb")
	@ResponseBody
	public String getJskb(String showdate,String viewtype,String timezone,String sxz,String openId){
		return zsSjkbService.getJskb(showdate,sxz,openId);
	}
	
	
	
	/**
	 * 跳转到学生课表页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toXskbNew")
   	public ModelAndView toXskb1(HttpServletRequest request){
		ModelAndView mav=null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String time = format2.format(cal.getTime());
		String openId =request.getParameter("openId");
		String xq =request.getParameter("xq");
		String zs =request.getParameter("zs");
		List<Map<String,Object>> list =zsXskbService.getNewZc(time);
		if("".equals(zs) || zs==null){
			if(list.size()!=0){
				zs=list.get(0).get("dqzc").toString();
			}
		}
		String xn =request.getParameter("xn");
		if(xn==null || "".equals(xn)){
			   /* Map<String,Object> map=AppUtil.getXn(new Date());
				xn=map.get("xn").toString();
				xq=map.get("xq").toString();*/
				xn=list.get(0).get("xn").toString();
				xq=list.get(0).get("xq").toString();
			}else{
				xq=xn.split("-")[2];
				xn=xn.split("-")[0]+"-"+xn.split("-")[1];
			}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
   			mav=new ModelAndView("/wfw/zsXskbNewList");
   			List<Map<String,Object>> xskb1=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%01%'");
   			List<Map<String,Object>> xskb2=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%02%'");
   			List<Map<String,Object>> xskb3=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%03%'");
   			List<Map<String,Object>> xskb4=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%04%'");
   			List<Map<String,Object>> xskb5=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%05%'");
   			List<Map<String,Object>> xskb6=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%06%'");
   			List<Map<String,Object>> xskb7=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%07%'");
   			List<Map<String,Object>> xskb8=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%08%'");
   			List<Map<String,Object>> xskb9=zsXskbService.getXskbList(xn, xq, zs, openId,"jc like '%09%'");
   			List<Map<String,Object>> xskb10=zsXskbService.getXskbList(xn, xq, zs, openId," (jc like '%110%' or jc like '%210%' "
                       + " or jc like '%310%' or jc like '%410%' or jc like '%510%'"
                       + " or jc like '%610%' or jc like '%710%') ");
   			List<Map<String,Object>> xskb11=zsXskbService.getXskbList(xn, xq, zs, openId," (jc like '%111%' or jc like '%211%' "
                    + " or jc like '%311%' or jc like '%411%' or jc like '%511%'"
                    + " or jc like '%611%' or jc like '%711%') ");
   			List<Map<String,Object>> xns=zsXskbService.getXn(openId);
   			List<Map<String,Object>> zss=zsXskbService.getZs();
   			if(xskb1.size()!=0){
   				mav.addObject("xskb1",xskb1);
   			}
   			if(xskb2.size()!=0){
   				mav.addObject("xskb2",xskb2);
   			}
   			if(xskb3.size()!=0){
   				mav.addObject("xskb3",xskb3);
   			}
   			if(xskb4.size()!=0){
   				mav.addObject("xskb4",xskb4);
   			}
   			if(xskb5.size()!=0){
   				mav.addObject("xskb5",xskb5);
   			}
   			if(xskb6.size()!=0){
   				mav.addObject("xskb6",xskb6);
   			}
   			if(xskb7.size()!=0){
   				mav.addObject("xskb7",xskb7);
   			}
   			if(xskb8.size()!=0){
   				mav.addObject("xskb8",xskb8);
   			}
   			if(xskb9.size()!=0){
   				mav.addObject("xskb9",xskb9);
   			}
   			if(xskb10.size()!=0){
   				mav.addObject("xskb10",xskb10);
   			}
   			if(xskb11.size()!=0){
   				mav.addObject("xskb11",xskb11);
   			}
   			if(xns.size()!=0){
   				mav.addObject("xns",xns);
   			}
   			if(zss!=null&&zss.size()!=0){
   				mav.addObject("zss",zss);
   			}
   			mav.addObject("xn",xn+"-"+xq);
   			//mav.addObject("xq",xq);
   			mav.addObject("zs",zs);
   			mav.addObject("week",cal.get(Calendar.DAY_OF_WEEK));
   			mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsKb/toXskbNew");
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
	 * 跳转到学生课表页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toJskbNew")
   	public ModelAndView toJskbNew(HttpServletRequest request){
		ModelAndView mav=null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String time = format2.format(cal.getTime());
		String openId =request.getParameter("openId");
		String xq =request.getParameter("xq");
		String zs =request.getParameter("zs");
		List<Map<String,Object>> list =zsXskbService.getNewZc(time);
		if("".equals(zs) || zs==null){
			if(list.size()!=0){
				zs=list.get(0).get("dqzc").toString();
			}
		}
		String xn =request.getParameter("xn");
		if(xn==null || "".equals(xn)){
			   /* Map<String,Object> map=AppUtil.getXn(new Date());
				xn=map.get("xn").toString();
				xq=map.get("xq").toString();*/
				xn=list.get(0).get("xn").toString();
				xq=list.get(0).get("xq").toString();
			}else{
				xq=xn.split("-")[2];
				xn=xn.split("-")[0]+"-"+xn.split("-")[1];
			}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
   			mav=new ModelAndView("/wfw/zsJskbNewList");
   			List<Map<String,Object>> jskb1=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%01%'");
   			List<Map<String,Object>> jskb2=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%02%'");
   			List<Map<String,Object>> jskb3=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%03%'");
   			List<Map<String,Object>> jskb4=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%04%'");
   			List<Map<String,Object>> jskb5=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%05%'");
   			List<Map<String,Object>> jskb6=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%06%'");
   			List<Map<String,Object>> jskb7=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%07%'");
   			List<Map<String,Object>> jskb8=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%08%'");
   			List<Map<String,Object>> jskb9=zsSjkbService.getJskbList(xn, xq, zs, openId,"jc like '%09%'");
   			List<Map<String,Object>> jskb10=zsSjkbService.getJskbList(xn, xq, zs, openId," (jc like '%110%' or jc like '%210%' "
   					                                                                  + " or jc like '%310%' or jc like '%410%' or jc like '%510%'"
   					                                                                  + " or jc like '%610%' or jc like '%710%') ");
   			List<Map<String,Object>> jskb11=zsSjkbService.getJskbList(xn, xq, zs, openId," (jc like '%111%' or jc like '%211%' "
                       + " or jc like '%311%' or jc like '%411%' or jc like '%511%'"
                       + " or jc like '%611%' or jc like '%711%') ");
   			List<Map<String,Object>> xns=zsSjkbService.getXn(openId);
   			List<Map<String,Object>> zss=zsXskbService.getZs();
   			if(jskb1.size()!=0){
   				mav.addObject("jskb1",jskb1);
   			}
   			if(jskb2.size()!=0){
   				mav.addObject("jskb2",jskb2);
   			}
   			if(jskb3.size()!=0){
   				mav.addObject("jskb3",jskb3);
   			}
   			if(jskb4.size()!=0){
   				mav.addObject("jskb4",jskb4);
   			}
   			if(jskb5.size()!=0){
   				mav.addObject("jskb5",jskb5);
   			}
   			if(jskb6.size()!=0){
   				mav.addObject("jskb6",jskb6);
   			}
   			if(jskb7.size()!=0){
   				mav.addObject("jskb7",jskb7);
   			}
   			if(jskb8.size()!=0){
   				mav.addObject("jskb8",jskb8);
   			}
   			if(jskb9.size()!=0){
   				mav.addObject("jskb9",jskb9);
   			}
   			if(jskb10.size()!=0){
   				mav.addObject("jskb10",jskb10);
   			}
   			if(jskb11.size()!=0){
   				mav.addObject("jskb11",jskb11);
   			}
   			if(xns.size()!=0){
   				mav.addObject("xns",xns);
   			}
   			if(zss!=null&&zss.size()!=0){
   				mav.addObject("zss",zss);
   			}
   			mav.addObject("xn",xn+"-"+xq);
   			//mav.addObject("xq",xq);
   			mav.addObject("zs",zs);
   			mav.addObject("week",cal.get(Calendar.DAY_OF_WEEK));
   			mav.addObject("openId",openId);
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsKb/toJskbNew");
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
}
