//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

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
import com.sliu.framework.app.wfw.service.ZsXssxqkService;

/**
 * 学生实习情况
 * @author liujiasen
 * @date 2015年5月28日
 */
@Controller
@RequestMapping("/wfw/ZsXssxqk")
public class ZsXssxqkController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXssxqkController.class);
	
	@Autowired
	private ZsXssxqkService zsXssxqkService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	/**
	 * 跳转到学生实习信息页面
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/toXssxqk")
   	public ModelAndView toXssxqk(HttpServletRequest request){
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
			mav=new ModelAndView("/wfw/zsXssxqkList");
	   		List<Map<String,Object>> cjlist=zsXssxqkService.getXssxqk("",openId,request);
	   		List<Map<String,Object>> qhlist=zsXssxqkService.getBjlb(openId);
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
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXssxqk/toXssxqk");
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
	 * 根据班级编号跳转到学生实习信息页面
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	@RequestMapping(value="/toXssxqkByQh")
   	public ModelAndView toXssxqkByQh(String bjbh,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsXssxqkList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> cjlist=zsXssxqkService.getXssxqk(bjbh,openId,request);
   		List<Map<String,Object>> qhlist=zsXssxqkService.getBjlb(openId);
   		if(cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		if(qhlist.size()!=0){
   			mav.addObject("qhlist", qhlist);
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
   		mav.addObject("bjbh", bjbh);
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
	@RequestMapping(value="/toXssxqkByQhZj")
	@ResponseBody
   	public List<Map<String,Object>> toXssxqkByQhZj(String bjbh,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsXssxqkList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> cjlist=zsXssxqkService.getXssxqk(bjbh,openId,request);
   		List<Map<String,Object>> qhlist=zsXssxqkService.getBjlb(openId);
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
   		mav.addObject("bjbh", bjbh);
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
   		return cjlist;
   	}
}
