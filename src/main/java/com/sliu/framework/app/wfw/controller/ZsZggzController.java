//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsZggzService;

/**
 * 工资查询
 * @author liujiasen
 * @date 2015年5月21日
 */
@Controller
@RequestMapping("/wfw/ZsZggz")
public class ZsZggzController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsZggzController.class);
	
	@Autowired
	private ZsZggzService zsZggzService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 跳转到工资查询页面
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	@RequestMapping(value="/toZggzxx")
   	public ModelAndView toZggzxx(HttpServletRequest request){
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
	   		mav=new ModelAndView("/wfw/zsZggzList");
	   		List<Map<String,Object>> list=zsZggzService.getGrgz(openId);
	   		
	   		//SimpleDateFormat sim=new SimpleDateFormat("yyyy.MM");
	   		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-M");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 0);
			//String gzffsj = sim.format(c.getTime());
//			String gzffsj1 = sim1.format(c.getTime());
	   		if(list!=null && list.size()!=0){
	   			mav.addObject("map1", list);
	   		}
	   		else{
	   			List<Map<String,Object>> list1=zsZggzService.getGrgz1(openId);
	   			mav.addObject("map1", list1);
	   		}
	   		//mav.addObject("date", gzffsj);
//	   		mav.addObject("time", gzffsj1);
//	   		mav.addObject("date1", gzffsj1);
	   		mav.addObject("openId",openId);
	   		mav.addObject("time", sim1.format(new Date()));
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsZggz/toZggzxx");
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
	 * 职工查询上月或下月工资
	 * @author liujiasen
	 * @date 2015年5月22日
	 * @return
	 */
	@RequestMapping(value="/toZggzxxBysj")
   	public ModelAndView toZggzxxBysj(HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wfw/zsZggzList");
   		String num=request.getParameter("time");
   		String openId =request.getParameter("openId");
//   		int nu=Integer.parseInt(request.getParameter("addflag"));
   		//SimpleDateFormat sim=new SimpleDateFormat("yyyy.MM");
   		SimpleDateFormat sim1=new SimpleDateFormat("yyyy-M");
   		Date date=new Date();
   		try {
			date=sim1.parse(num);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String gzffsj = sim1.format(c.getTime());
		//String gzffsj1 = sim.format(c.getTime());
		List<Map<String,Object>> list=zsZggzService.getGrgzBysj(gzffsj,openId);
   		if(list!=null && list.size()!=0){
   			mav.addObject("map1", list);
   		}
   		//mav.addObject("date", gzffsj1);
//   		mav.addObject("time", gzffsj);
//   		mav.addObject("date1", gzffsj);
   		mav.addObject("time", num);
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
}
