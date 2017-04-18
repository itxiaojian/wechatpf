package com.sliu.framework.app.bx.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
import com.sliu.framework.app.bx.service.BxBxckService;
import com.sliu.framework.app.bx.service.BxBxsqService;
import com.sliu.framework.app.common.dao.support.Pagination;

/**
*报修门户查看
@Author oufeng	
@Date 2015年8月13日 上午11:26:55
@Version 1.0
*/
@Controller
@RequestMapping("/bx/bxck")
	public class BxBxckController extends BaseController {

		protected final transient Logger logger = LoggerFactory
				.getPresentationLog(BxBxckController.class);

		@Autowired
		private BxBxckService service;
		
		@Autowired
		private BxBxsqService bxspService;

		/**
		 * 
		 * @author   chenhui
		 * @version 创建时间：2016年7月25日
		 * @return
		 */
		@RequestMapping(value = "/bxglmobilePage")
		public String openPage(){
			//ModelAndView modelAndView = new ModelAndView("/bx/bxgl/bxGlMobile01");
			return "/bx/bxck/bxCkWatch";
		}
		

		/**
		 * 报修手机端页面查看
		 * @author chenhui 
		 * @date 2015年8月10日
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/check")
	   	public ModelAndView toCheck(HttpServletRequest request, HttpServletResponse response){
	   		ModelAndView mav=new ModelAndView("/bx/bxck/bxGlCheck");
	   		String bxbh=request.getParameter("bxbh");
	   		List<Map<String,Object>> list=service.getCheck(bxbh);
	   		if(list.size()!=0){
	   			mav.addObject("list", list);
	   		}
	   		return mav;
	   	}
		
	/**
	 * 获取维修大类
	 * 
	 * @author chenhui
	 * @date 2015年8月7日
	 * @return
	 */
	@RequestMapping(value = "/getWxdl")
	@ResponseBody
	public List<Map<String, Object>> getWxdl() {
		return service.getWxdl();
	}

	/**
	 * 获取报修主题
	 * 
	 * @author chenhui
	 * @date 2015年8月7日
	 * @return
	 */
	@RequestMapping(value = "/getBxzt")
	@ResponseBody
	public List<Map<String, Object>> getBxzt(String wxdl) {
		return service.getBxzt(wxdl);
	}
	
	/**
	 * 报修手机端页面
	 * @author chenhui 
	 * @date 2016年9月3s日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/bxck")
	@ResponseBody
	public Pagination<Map<String, Object>>getList(Integer start, Integer limit,String zt,String bm,String wxdl,String bxzt){
   		return service.getList(start, limit, zt, bm, wxdl, bxzt);
   	}
	

		/**
   		 * 跳转到报修门户查看
   		 * @author oufeng 
   		 * @date 2015年8月10日
   		 * @param request
   		 * @param response
   		 * @return
   		 * @throws UnsupportedEncodingException 
   		 * @throws ParseException 
   		 */
   		@RequestMapping(value="/cklist")
   	   	public ModelAndView toCkList(HttpServletRequest request, HttpServletResponse response) 
   	   			throws UnsupportedEncodingException, ParseException{
   	   		ModelAndView mav=new ModelAndView("/bx/bxck/bxCkList");
   	   	String pages = request.getParameter("pages");
   		String zt=request.getParameter("zt");
   		String nr=request.getParameter("nr");
   		String time=request.getParameter("time");
	   		if(nr!=null){
	   	   		nr = new String(nr.getBytes("ISO-8859-1"), "UTF-8"); 
	   	   		}else{
	   	   			nr="";
	   	   		}
		if (pages == null) {
			pages = "1";
		}
 		int count = service.getCount(request);
   		List<Map<String,Object>> list=service.getList(zt, nr, time, pages);
   		List<Map<String,Object>> listZt=service.getZt();
   	//	List<Map<String,Object>> wxlist=service.getWxdl();
   			mav.addObject("list", list);
   			//mav.addObject("wxlist", wxlist); 
   			mav.addObject("pages", pages);
   			mav.addObject("count", count);
   			mav.addObject("listZt", listZt);
   			mav.addObject("zt", zt);
   			mav.addObject("nr", nr);
   			mav.addObject("time", time);
   	   		return mav;
   	   	}
   		
   		/**
   		 * 跳转到报修门户详情查看
   		 * @author oufeng 
   		 * @date 2015年8月10日
   		 * @param request
   		 * @param response
   		 * @return
   		 * @throws UnsupportedEncodingException 
   		 * @throws ParseException 
   		 */
   		@RequestMapping(value="/ckdetail")
   	   	public ModelAndView toCkDetail(HttpServletRequest request, HttpServletResponse response) 
   	   			throws UnsupportedEncodingException, ParseException{
   	   		ModelAndView mav=new ModelAndView("/bx/bxck/bxCkDetail");
	   	   	String id = request.getParameter("id");
	   	 	List<Map<String,Object>> detaillist=service.getCkList(id);
	   		mav.addObject("detaillist", detaillist);
	   		return mav;
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
   	   		List<Map<String,Object>> wxdl=bxspService.getWxDl();
   	   		List<Map<String,Object>> ly=bxspService.getLy();
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
