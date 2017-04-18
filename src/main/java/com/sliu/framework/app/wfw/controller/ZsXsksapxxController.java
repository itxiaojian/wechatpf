//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.sliu.framework.app.wfw.service.ZsXsksapxxService;

/**
 * 
 * @author liujiasen
 * @date 2015年5月29日
 */
@Controller
@RequestMapping("/wfw/ZsXsksapxx")
public class ZsXsksapxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXsksapxxController.class);
	
	@Autowired
	private ZsXsksapxxService zsXsksapxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;

	/**
	 * 跳转到考试安排页面
	 * @author liujiasen
	 * @date 2015年5月29日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toKsap")
   	public ModelAndView toKsap(String ksqh,HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		if(ksqh == null ){
			ksqh="";
		}else{ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8");}
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wfw/zsXsgrksapList");
	   		List<Map<String,Object>> xslist=zsXsksapxxService.getXsxx(ksqh,openId,request);
	   		List<Map<String,Object>> cjlist=zsXsksapxxService.getGrksqp(ksqh,openId,request);
	   		List<Map<String,Object>> qhlist=zsXsksapxxService.getKsqh(openId);
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
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
	   		mav.addObject("qh",ksqh);
	   		mav.addObject("pages", pages);
			mav.addObject("size",cjlist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXsksapxx/toKsap");
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
	 * 根据学期号跳转到考试安排页面
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toXsapByQh")
	@ResponseBody
   	public List<List<Map<String,Object>>> toXsapByQh(String ksqh,HttpServletRequest request) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgrksapList");
   		String openId =request.getParameter("openId");
   		List<List<Map<String,Object>>>  newlist=new ArrayList<List<Map<String,Object>>>();
   		List<Map<String,Object>> xslist=zsXsksapxxService.getXsxx(ksqh,openId,request);
   		List<Map<String,Object>> cjlist=zsXsksapxxService.getGrksqp(ksqh,openId,request);
   		List<Map<String,Object>> qhlist=zsXsksapxxService.getKsqh(openId);
   		if(cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		if(qhlist.size()!=0){
   			mav.addObject("qhlist", qhlist);
   		}
   		if(xslist.size()!=0){
			mav.addObject("xslist", xslist);
		}
   		/*List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			SysYh yh=yhBo.get(yhid);
			mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		}*/  
 		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("qh", ksqh); 
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
		newlist.add(xslist);
		newlist.add(cjlist);
   		return newlist;
   	}
	
	/**
	 * 学生考试安排信息-加载更多
	 @throws UnsupportedEncodingException 
	 *@Author zhangyan
	 *@Date 2016年8月20日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/ksjzgd")
	@ResponseBody
	public List<Map<String,Object>> ksjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=new ModelAndView("/wfw/zsXsgrksapList");
   		String openId =request.getParameter("openId");
   		String qh =request.getParameter("qh");
   		List<Map<String,Object>> cjlist=zsXsksapxxService.getGrksqp(qh,openId,request);
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
	 * 根据学期号跳转到考试安排页面
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	@RequestMapping(value="/toXsapByQhZj")
	@ResponseBody
   	public List<Map<String,Object>> toXsapByQhZj(String ksqh,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgrksapList");
   		String openId =request.getParameter("openId");
   		List<Map<String,Object>> cjlist=zsXsksapxxService.getGrksqp(ksqh,openId,request);
   		List<Map<String,Object>> qhlist=zsXsksapxxService.getKsqh(openId);
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
