package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.sliu.framework.app.wfw.service.ZsJscxService;

/**
 * 教室查询
 * @author duanpeijun
 * @date 2015年7月8日
 *
 */
@Controller
@RequestMapping("/wfw/ZsJscx")
public class ZsJscxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsJscxController.class);

	@Autowired
	private ZsJscxService ZsJscxService;  
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	/**
	 * 查询所有教室信息
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @param request
	 * @param response
	 * @param openId
	 * @param kcmc  课程名称
	 * @param jxl 教学楼
	 * @return
	 * @throws UnsupportedEncodingException
	 *
	 */
	@RequestMapping(value = "/toJscx")
	public ModelAndView toJscx(HttpServletRequest request,
			HttpServletResponse response,String openId, String kcmc, String jxl,String year,String xq,String week,String dsweek) 
					throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsJscxList");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(jxl != null && !"".equals(jxl)){
			jxl = new String(jxl.getBytes("ISO-8859-1"), "UTF-8");
		}
		if(week!=null&&!"".equals(week)){
			week= new String(week.getBytes("ISO-8859-1"), "UTF-8");
		}
		List<Map<String, Object>> listAllJxl = ZsJscxService.getAllJscxList();
		if(jxl == null || "".equalsIgnoreCase(jxl)){
			list = ZsJscxService.getAllJscxList();
		}else{
			list = ZsJscxService.getJscxList(request,jxl);
		}
		List<Map<String, Object>> listJSXX = new ArrayList<Map<String, Object>>();
		/*List<Map<String, Object>> JXJSlist = shJscxService.getJxjsList();*/
		for(Map<String, Object> map:list){
			Map<String, Object> mymap = new HashMap<String, Object>();
			String jxlmc = map.get("jxl").toString();
			List<Map<String, Object>> listJS = ZsJscxService.getJscxListByJxl(jxlmc);//listJS:根据教学楼获取教室信息
			List<Map<String, Object>> listJsKc = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> listjs:listJS){
				Map<String, Object> myKcMap = new HashMap<String, Object>();
				myKcMap.put("name", listjs.get("jsmc"));//name:教室名称
				String jsbh = listjs.get("jsbh").toString();//jsbh:教室编号
				List<Map<String, Object>> kcList = new ArrayList<Map<String, Object>>();
				kcList= ZsJscxService.getJscxAll(jsbh,kcmc,year,xq,week,dsweek);
//				for(int i = 1;i<=10;i++){
//					Map<String, Object> jskc = ZsJscxService.getJscxQk(i,jsbh,kcmc,year,xq,week,dsweek);
//					kcList.add(jskc);
//				}
				String jsqk = "0";
				if(kcList.size()>0){
					for(Map<String, Object> jsxx:kcList){
						if("0".equalsIgnoreCase(jsqk)){
							jsqk = jsxx.get("jc").toString();
						}else{
							if(jsqk.indexOf(String.valueOf(jsxx.get("jc").toString())) == -1){
								jsqk = jsqk+"、"+jsxx.get("jc").toString();
							}
						}
					}
				}
				myKcMap.put("jsqk", jsqk);
				myKcMap.put("kcList", kcList);
				listJsKc.add(myKcMap);
			}
			mymap.put("jxl", jxlmc);
			mymap.put("listJS", listJsKc);
			mymap.put("num", listJS.size()+1);
			listJSXX.add(mymap);
		}
		if (listJSXX.size() != 0) {
			mav.addObject("list", listJSXX);
		}
		
		if(openId!=null&&!"".equals(openId)){
			List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
			if(user.size()!=0){
				String yhid =user.get(0).get("yhid")+"";
				SysYh yh=yhBo.get(yhid);
				mav.addObject("text", yh.getUsername()+":"+yh.getXm());
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
		
		mav.addObject("listjxl", list);
		mav.addObject("listAllJxl",listAllJxl);
		mav.addObject("jxl",jxl);
		mav.addObject("year",year);
		mav.addObject("xq",xq);
		mav.addObject("week",week);
		mav.addObject("dsweek",dsweek);
		mav.addObject("openId", openId);
       return mav;
	}
	
	@RequestMapping(value = "/toPage")
	public ModelAndView toPage(HttpServletRequest request,
			HttpServletResponse response,String kcmc, String jxl,String year,String xq,String week,String dsweek) 
					throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsJscxPage");
		String openId = request.getParameter("openId");
	/*	if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}else{*/
		List<Map<String, Object>> listAllJxl = ZsJscxService.getAllJscxList();
		if(jxl != null && !"".equals(jxl)){
			jxl = new String(jxl.getBytes("ISO-8859-1"), "UTF-8");
		}
		if(week!=null&&!"".equals(week)){
			week= new String(week.getBytes("ISO-8859-1"), "UTF-8");
		}
		
		if(openId!=null&&!"".equals(openId)){
			List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
			if(user.size()!=0){
				String yhid =user.get(0).get("yhid")+"";
				SysYh yh=yhBo.get(yhid);
				mav.addObject("text", yh.getUsername()+":"+yh.getXm());
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
		
		mav.addObject("listAllJxl",listAllJxl);
		mav.addObject("year",year);
		mav.addObject("xq",xq);
		mav.addObject("week",week);
		mav.addObject("dsweek",dsweek);
		mav.addObject("openId", openId);
	//	}
		return mav;
	}
}
