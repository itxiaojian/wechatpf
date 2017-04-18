package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;
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
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.service.ZsXsrzxxService;

/**
 * 学生入住信息
 * @author zhangyan
 * @date 2016年8月9日
 */
@Controller
@RequestMapping("/wfw/ZsXsrzxx")
public class ZsXsrzxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZsXsrzxxController.class);
	
	@Autowired
	private ZsXsrzxxService zsXsrzxxService;
	
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private SysYhBO yhBo;
	
	/**
	 * 辅导员进入展示班级学生列表
	 * @author zhangyan
	 * @date 2016年8月9日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toXsrzxxList")
	@ResponseBody
	public ModelAndView toXsrzxxList(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wfw/zsXsrzxxList");
		String code=request.getParameter("code");
		if(code !=null &&!"".equals(code)){
		code = new String(code.getBytes("ISO-8859-1"), "UTF-8");
		}
		String bjmc=request.getParameter("bjmc");
		if(bjmc !=null &&!"".equals(bjmc)){
			bjmc = new String(bjmc.getBytes("ISO-8859-1"), "UTF-8");
		}
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
			List<Map<String, Object>> list = zsXsrzxxService.getXsrzxxList(request,code,bjmc);
			List<Map<String,Object>> bjxxlist=zsXsrzxxService.getBj(openId);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		if (bjxxlist.size() != 0) {
			mav.addObject("bjxxlist", bjxxlist);
		}
		
		if(code !=null &&!"".equals(code)){
			mav.addObject("code", code);
		}else{
			mav.addObject("code", "");
		}
		mav.addObject("pages", pages);
		mav.addObject("bjmc", bjmc);
		mav.addObject("openId", openId);
		mav.addObject("size",list.size());
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
	 * 宿管信息
	 @throws UnsupportedEncodingException 
	 *@Author zhangyan
	 *@Date 2016年8月20日
	 *@Version 1.0
	 */
	@RequestMapping(value = "/sgjzgd")
	@ResponseBody
	public List<Map<String,Object>> sgjzgd(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/wfw/zsXsrzxxList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String code = request.getParameter("code");
		String openId = request.getParameter("openId");
		String bjmc = request.getParameter("bjmc");
		List<Map<String, Object>> list = zsXsrzxxService.getXsrzxxList(request,code,bjmc);
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
			
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
//		mav.addObject("pages", pages);
		mav.addObject("openId", openId);
		mav.addObject("size",list.size());
		}
		return list;
		
	}
	
	/**
	 * 查询学生详细信息
	 * @author zhangyan
	 * @date 2016年8月10日
	 * @param id
	 * @param openId
	 * @return
	 */
	@RequestMapping(value="/toXsrzxxDetail")
	@ResponseBody
   	public ModelAndView toXsrzxxDetail(Long id,String openId){
   		ModelAndView mav=new ModelAndView("/wfw/zsXsrzxxDetail");
   		List<Map<String,Object>> rzxxlist=zsXsrzxxService.getXsrzxxDetail(id,openId);
   		String xh = rzxxlist.get(0).get("xh").toString();
   		List<Map<String,Object>> kqxxlist=zsXsrzxxService.getXskqxxDetail(xh);
   		if(rzxxlist.size()!=0){
   			mav.addObject("rzxxlist", rzxxlist);
   		}
   		if(kqxxlist.size()!=0){
   			mav.addObject("kqxxlist", kqxxlist);
   		}
   		mav.addObject("openId",openId);
   		return mav;
   	}
}
