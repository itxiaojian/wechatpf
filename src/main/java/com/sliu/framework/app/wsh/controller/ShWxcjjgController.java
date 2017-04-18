//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

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
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.service.ShWxcjjgService;

/**
 * 微信抽奖结果
 * @author liujiansen
 * @date 2015年6月17日
 */
@Controller
@RequestMapping("/wsh/ShWxcjjg")
public class ShWxcjjgController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShWxcjjgController.class);
	
	@Autowired
	private ShWxcjjgService service;
	
	/**
	 * 跳转到抽奖页面
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/toWxcj")
   	public ModelAndView toWxcj(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
   		ModelAndView mav=new ModelAndView("/wsh/ShWxcj/wxcj");
   		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
   		List<Map<String,Object>> list=service.getWxcj();
   		List<Map<String,Object>> jg=service.getZjjg(openId);
   		int num=0;
   		if(list.size()!=0){
   			List<Map<String,Object>> jglb=service.getCjjg(openId, (list.get(0).get("id")+""));
   			if(jglb.size()!=0){
   				mav.addObject("list", jglb);
   			}
   			mav.addObject("map", list.get(0));
   			mav.addObject("openId", openId);
   			num=Integer.parseInt(list.get(0).get("cjcs")+"");
   			if(jg.size()!=0){
   				num=Integer.parseInt(list.get(0).get("cjcs")+"")-jg.size();
   			}
   		}
   		mav.addObject("num", num);
		}
   		return mav;
   	}
	
	/**
	 * 微信抽奖
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="doWxcj")
	@ResponseBody
	public String doWxcj(HttpServletRequest request, HttpServletResponse response){
		return service.doWxcj(request, response);
	}
}
