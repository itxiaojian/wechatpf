//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.bx.controller;

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
import com.sliu.framework.app.bx.service.BxBxspyjbService;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
 * 报修申请
 * @author liujiansen
 * @date 2015年8月6日
 */
@Controller
@RequestMapping("/wx/Bxspyjb")
public class BxBxspyjbController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(BxBxspyjbController.class);
	
	@Autowired
	private BxBxspyjbService service;
	
	/**
	 * 申请数据保存
	 * @author liujiansen
	 * @date 2015年8月7日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(HttpServletRequest request) {
		String str="";
		str=service.save(request);
		return str;
	}

	/**
	 * 申请数据修改保存
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(HttpServletRequest request) {
		String str="";
		str=service.update(request);
		return str;
	}
	
	/**
	 * 流程申请信息列表及审批记录显示页面
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsqlcjl")
   	public ModelAndView toBxsqlcjl(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav = new ModelAndView();
		String url = "";
		url="/bx/bxsq/bxSqlcjl";
   		SysYh user = AppUtil.getCurrentUser();
   		List<Map<String,Object>> sq=service.getSq(user.getYhbh());
   		List<Map<String,Object>> spxx=service.getSpxx(user.getYhbh());
   		if(sq.size()!=0){
//   			mav.addObject("map", sq.get(0));
   			mav.addObject("map", sq);
   			mav.addObject("size", sq.size());
   		}
   		if(spxx.size()!=0){
   			mav.addObject("spxx", spxx);
   		}
   		mav.addObject("yhbh", user.getYhbh());
   		mav.setViewName(url);
   		return mav;
   	}
	
	/**
	 * 申请修改
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsqUpdate")
   	public ModelAndView toBxsqUpdate(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/bx/bxsq/bxSqUpdate");
   		String yhbh=request.getParameter("yhbh");
   		String id=request.getParameter("id");
   		List<Map<String,Object>> sq=service.getSqxx(id);
   		List<Map<String,Object>> wxdl=service.getWxDl();
   		List<Map<String,Object>> ly=service.getLy();
   		if(wxdl.size()!=0){
   			mav.addObject("wxdl", wxdl);
   		}
   		if(ly.size()!=0){
   			mav.addObject("ly", ly);
   		}
   		if(sq.size()!=0){
			mav.addObject("map", sq.get(0));
		}
   		mav.addObject("yhbh", yhbh);
   		return mav;
   	}
	
	/**
	 * 流程申请信息反馈页面
	 * @author liujiansen
	 * @date 2015年8月11日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsqlcfk")
   	public ModelAndView toBxsqlcfk(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/bx/bxsq/bxSqlcfk");
//   		String id=request.getParameter("id");
//   		List<Map<String,Object>> sq=service.getSq(id);
//   		List<Map<String,Object>> spxx=service.getSpxx(id);
   		String yhbh=request.getParameter("yhbh");
   		String id=request.getParameter("id");
   		List<Map<String,Object>> sq=service.getSqxx(id);
   		if(sq.size()!=0){
  			mav.addObject("map", sq.get(0));
   		}
   		mav.addObject("yhbh", yhbh);
   		return mav;
   	}
	
	/**
	 * 报修评价
	 * @author liujiansen
	 * @date 2015年8月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bxFwpj")
	@ResponseBody
	public String bxFwpj(HttpServletRequest request) {
		String str="";
		str=service.bxFwpj(request);
		return str;
	}
	
	/**
	 * 报修申请
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toBxsq")
   	public ModelAndView toBxsq(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/bx/bxsq/bxSqView");
   		String openId=request.getParameter("openId");
   		List<Map<String,Object>> wxdl=service.getWxDl();
   		List<Map<String,Object>> ly=service.getLy();
   		if(wxdl.size()!=0){
   			mav.addObject("wxdl", wxdl);
   		}
   		if(ly.size()!=0){
   			mav.addObject("ly", ly);
   		}
   		mav.addObject("openId", openId);
   		return mav;
   	}
}
