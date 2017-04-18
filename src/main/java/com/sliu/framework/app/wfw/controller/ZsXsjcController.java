//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.sliu.framework.app.wfw.service.ZsXsjcService;

/**
 * 学生奖惩
 * @author liujiasen
 * @date 2015年5月21日
 */
@Controller
@RequestMapping("/wfw/ZsXsjc")
public class ZsXsjcController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXsjcController.class);
	
	@Autowired
	private ZsXsjcService zsXsjcService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	/**
	 * 跳转到学生奖惩页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toXsjc")
   	public ModelAndView toXsjc(HttpServletRequest request){
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
	   		mav=new ModelAndView("/wfw/zsXsgrjcList");
	   		List<Map<String,Object>> cjlist=zsXsjcService.getGrcj("",openId,request);
	   		List<Map<String,Object>> qhlist=zsXsjcService.getKsqh(openId);
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
	   		if(cjlist.size()!=0){
	   			mav.addObject("cjlist", cjlist);
	   		}
	   		if(qhlist.size()!=0){
	   			mav.addObject("qhlist", qhlist);
	   		}
	   		mav.addObject("openId",openId);
	   		mav.addObject("pages", pages);
			mav.addObject("size",cjlist.size());
	   		return mav;
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXsjc/toXsjc");
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
	 * 根据学年学期跳转到学生奖惩页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toXsjcByQh")
   	public ModelAndView toXsjcByQh(String ksqh,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgrjcList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> cjlist=zsXsjcService.getGrcj(ksqh,openId,request);
   		List<Map<String,Object>> qhlist=zsXsjcService.getKsqh(openId);
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
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
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
	 * 根据学年学期跳转到学生奖惩页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toXsjcByQhZj")
	@ResponseBody
   	public List<Map<String,Object>> toXsjcByQhZj(String ksqh,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgrjcList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> cjlist=zsXsjcService.getGrcj(ksqh,openId,request);
   		List<Map<String,Object>> qhlist=zsXsjcService.getKsqh(openId);
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
	
	/**
	 * 跳转到奖惩详细页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @param id
	 * @return
	 */
	/*@RequestMapping(value="/toXscjById")
   	public ModelAndView toXscjById(String id){
   		ModelAndView mav=new ModelAndView("/wfw/XsgrjcXq");
   		Map<String,Object> map=zsXsjcService.getMx(id);
   		if(map.size()!=0){
   			mav.addObject("map", map);
   		}
   		return mav;
   	}*/
	
	/**
	 * 跳转到奖惩详细页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toXsjcById")
   	public ModelAndView toXsjcById(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgrjcDetail");
   		String id=request.getParameter("id");
   		Map<String,Object> map=zsXsjcService.getMx(id);
   		if(map.size()!=0){
   			mav.addObject("map1", map);
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
