//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsXscjService;

/**
 * 微信用户
 * @author liujiasen
 * @date 2015年5月18日
 */
@Controller
@RequestMapping("/wfw/ZsXscj")
public class ZsXscjController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXscjController.class);
	
	@Autowired
	private ZsXscjService zsXscjService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	@RequestMapping(value="/xscjPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wfw/zsXscjList");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getXscjList")
	@ResponseBody
	public Pagination<Map<String,Object>> getXscjList(Integer start, Integer limit,String xm,String wxh) {
		Pagination<Map<String,Object>> list = zsXscjService.getXscjList(start, limit,xm,wxh);
		return list;
	}
	
	/**
	 * 跳转到学生成绩页面
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toXscj")
   	public ModelAndView toXscj(String ksqh,HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String openId =request.getParameter("openId");
		if(ksqh == null ){
			ksqh="";
		}else{ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8");}
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String str ="";
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			String yhid =user.get(0).get("yhid")+"";
			//SysYh yh=yhBo.get(yhid);
			String whStr = zsXscjService.getCxtj(str, yhid);
			mav=new ModelAndView("/wfw/zsXsgrcjList");
			List<Map<String,Object>> cjlist=zsXscjService.getGrcj(ksqh,yhid,request,whStr);
			List<Map<String,Object>> xslist=zsXscjService.getXsxx(ksqh,openId,request);
		//	List<Map<String,Object>> qhlist=zsXscjService.getKsqh(whStr);
			List<Map<String,Object>> qhlist=zsXscjService.getXnAndXq();
		    if(xslist.size()!=0){
					mav.addObject("xslist", xslist);
					Map<String, Object> mapXn = AppUtil.getXnNew(new Date());
					String xn = (String) mapXn.get("xn");
					String xq = (String) mapXn.get("xq");
					mav.addObject("xq", xq);
			}
			if(cjlist.size()!=0){
				mav.addObject("cjlist", cjlist);
			}
			if(qhlist.size()!=0){
				mav.addObject("qhlist", qhlist);
			}
			mav.addObject("qh", ksqh);
			mav.addObject("openId",openId);
			mav.addObject("pages", pages);
			mav.addObject("size",cjlist.size());
			//mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXscj/toXscj");
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
	 * 根据考试期号跳转到学生成绩页面
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toXscjByQh")
	@ResponseBody
   	public List<List<Map<String,Object>>> toXscjByQh(String ksqh,String openId,HttpServletRequest request) 
   			throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgrcjList");
   		//ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8"); 
   		//String openId =request.getParameter("openId");
   		String str="";
   		String yhid =zsXscjService.getWxyh(openId).get(0).get("yhid")+"";
   		String jsmc =zsXscjService.getWxyh(openId).get(0).get("jsmc")+"";
		String whStr = zsXscjService.getCxtj(str, yhid);
		mav=new ModelAndView("/wfw/zsXsgrcjList");
		List<Map<String,Object>> cjlist=zsXscjService.getGrcj(ksqh,yhid,request,whStr);
		List<Map<String,Object>> xslist=zsXscjService.getXsxx(ksqh,openId,request);
		List<List<Map<String,Object>>>  newlist=new ArrayList<List<Map<String,Object>>>();
		List<Map<String,Object>> qhlist=zsXscjService.getXnAndXq();
		if(xslist.size()!=0){
			mav.addObject("xslist", xslist);
			if(ksqh!=null&&!"".equals(ksqh)){
				String[] arr=ksqh.split("~");
				String xq=arr[1];
				//mav.addObject("xq", xq);
				for(int i=0;i<xslist.size();i++){
					xslist.get(i).put("jsmc", jsmc);
				}
			}
			if(ksqh==null || "".equals(ksqh)){
				Map<String, Object> mapXn = AppUtil.getXnNew(new Date());
				String xq = (String) mapXn.get("xq");
				//mav.addObject("xq", xq);
				for(int i=0;i<xslist.size();i++){
					xslist.get(i).put("xq", xq);
					xslist.get(i).put("jsmc", jsmc);
				}
			}
		}
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
		//SysYh yh=yhBo.get(yhid);
   		//mav.addObject("text", yh.getUsername()+":"+yh.getXm());
		mav.addObject("qh", ksqh);
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
		newlist.add(xslist);
		newlist.add(cjlist);
   		return newlist;
   	}
	
	/**
	 * 根据考试期号跳转到学生成绩页面
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toXscjByQhZj")
	@ResponseBody
   	public List<Map<String,Object>> toXscjByQhZj(String ksqh,String openId,HttpServletRequest request) 
   			throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wfw/zsXsgrcjList");
   		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8"); 
   		//String openId =request.getParameter("openId");
   		String str="";
   		String yhid =zsXscjService.getWxyh(openId).get(0).get("yhid")+"";
		String whStr = zsXscjService.getCxtj(str, yhid);
		mav=new ModelAndView("/wfw/zsXsgrcjList");
		List<Map<String,Object>> cjlist=zsXscjService.getGrcj(ksqh,yhid,request,whStr);
		//List<Map<String,Object>> qhlist=zsXscjService.getKsqh(whStr);
		List<Map<String,Object>> qhlist=zsXscjService.getXnAndXq();
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
	 * 成绩预警
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toCjyj")
   	public ModelAndView toGkxx(String ksqh,String bjbh,HttpServletRequest request,
   			HttpServletResponse response) throws UnsupportedEncodingException{
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
	   		mav=new ModelAndView("/wfw/zsXscjyjList");
//	   		List<Map<String,Object>> bjlist=zsXscjService.getBjxx("",openId,request);
	   		List<Map<String,Object>> cjlist=zsXscjService.getCjList("","",openId,request);
	   		List<Map<String,Object>> bjxxlist=zsXscjService.getBj(openId);
	   		List<Map<String,Object>> qhlist=zsXscjService.getKsqh(openId);
	   		if(cjlist.size()!=0){
	   			mav.addObject("cjlist", cjlist);
	   		}
	   		if(qhlist.size()!=0){
	   			mav.addObject("qhlist", qhlist);
	   		}
//	   		if(bjlist.size()!=0){
//				mav.addObject("bjlist", bjlist);
//			}
	   		if(bjxxlist.size()!=0){
				mav.addObject("bjxxlist", bjxxlist);
			}
	   		mav.addObject("openId",openId);
	   		mav.addObject("pages", pages);
			mav.addObject("size",cjlist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXscj/toCjyj");
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
	 * 成绩预警查询
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/toCjyjcx")
   	public ModelAndView toCjyjcx( HttpServletRequest request,
   			HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView mav=null;
		String ksqh=request.getParameter("ksqh");
		String bjbh=request.getParameter("bjbh");
		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8"); 
		bjbh = new String(bjbh.getBytes("ISO-8859-1"),"UTF-8");
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
	   		mav=new ModelAndView("/wfw/zsXscjyjList");
//	   		List<Map<String,Object>> bjlist=zsXscjService.getBjxxcx("",openId,request);
	   		List<Map<String,Object>> cjlist=zsXscjService.getCjList(ksqh,bjbh,openId,request);
	   		List<Map<String,Object>> bjxxlist=zsXscjService.getBj(openId);
	   		List<Map<String,Object>> qhlist=zsXscjService.getKsqh(openId);
	   		if(cjlist.size()!=0){
	   			mav.addObject("cjlist", cjlist);
	   		}
	   		if(bjxxlist.size()!=0){
	   			mav.addObject("bjxxlist", bjxxlist);
	   		}
	   		if(qhlist.size()!=0){
	   			mav.addObject("qhlist", qhlist);
	   		}
	   		mav.addObject("openId",openId);
	   		mav.addObject("qh",ksqh);
	   		mav.addObject("bjbh",bjbh);
	   		mav.addObject("pages", pages);
			mav.addObject("size",cjlist.size());
		}else{
			mav=new ModelAndView("/sys/SysWxyh/wxbd");
			mav.addObject("openId",openId);
			mav.addObject("url","/wfw/ZsXscj/toCjyj");
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
	 *成绩预警-加载更多
	 @throws UnsupportedEncodingException 
	 *@Author zhangyan
	 *@Date 2016年8月20日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/cjjzgd")
	@ResponseBody
	public List<Map<String,Object>> gkjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav=new ModelAndView("/wfw/zsXscjyjList");
   		String openId =request.getParameter("openId");
   		String qh =request.getParameter("qh");
   		String bjmc =request.getParameter("bjbh");
   		List<Map<String,Object>> cjlist=zsXscjService.getCjList(qh,bjmc,openId,request);
//   		List<Map<String,Object>> bjxxlist=zsXsgkxxService.getBj(openId);
//   		List<Map<String,Object>> qhlist=zsXsgkxxService.getKsqh(openId);
   		if(cjlist.size()!=0){
   			mav.addObject("cjlist", cjlist);
   		}
   		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
   		mav.addObject("qh", qh); 
   		mav.addObject("bjbh", bjmc); 
   		mav.addObject("openId",openId);
   		mav.addObject("pages", pages);
		mav.addObject("size",cjlist.size());
		
		return cjlist;
		
	}
	
	/**
	 * 查询班级成绩
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @param id
	 * @param openId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/tobjcj")
	@ResponseBody
   	public ModelAndView tobjcj(String code,String qh,String openId) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/wfw/zsBjcjyjDetail");
   		code = new String(code.getBytes("ISO-8859-1"), "UTF-8"); 
   		String bjbh="";
   		String kskm="";
   		String pjf="";
   		if(code!=null&&!"".equals(code)){
			String[] arr=code.split(",");
			bjbh=arr[0];
			kskm=arr[1];
			pjf=arr[2];
		}
   		List<Map<String,Object>> bjcjlist=zsXscjService.getBjcj(bjbh,kskm,qh,openId);
   		if(bjcjlist.size()!=0){
   			mav.addObject("bjcjlist", bjcjlist);
   		}
   		mav.addObject("openId",openId);
   		mav.addObject("pjf",pjf);
   		mav.addObject("kskm",kskm);
   		return mav;
   	}

}
