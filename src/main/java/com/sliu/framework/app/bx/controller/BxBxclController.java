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
import com.sliu.framework.app.bx.service.BxBxclService;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;


/**
*报修处理
@Author oufeng	
@Date 2015年8月13日 上午11:26:55
@Version 1.0
*/
@Controller
@RequestMapping("/bx/bxcl")
	public class BxBxclController extends BaseController {

		protected final transient Logger logger = LoggerFactory
				.getPresentationLog(BxBxclController.class);

		@Autowired
		private BxBxclService service;
 
		@RequestMapping(value="/cllist")
	   	public ModelAndView toCheck(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException{
	   		ModelAndView mav=new ModelAndView("/bx/bxcl/bxClView");
	   		//bm:过滤参数
	   		String bm=request.getParameter("bm");
	   		String wxdl=request.getParameter("wxdl");
	   		String bxzt=request.getParameter("bxzt");
	   		if(bm!=null){
	   		bm = new String(bm.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bm="";
	   		}
	   		if(bxzt!=null){
	   	   		bxzt = new String(bxzt.getBytes("ISO-8859-1"), "UTF-8"); 
	   	   		}else{
	   	   			bxzt="";
	   	   		}
	   		if(wxdl!=null){
	   	   		wxdl = new String(wxdl.getBytes("ISO-8859-1"), "UTF-8"); 
	   	   		}else{
	   	   			wxdl="";
	   	   		}
	   		String pages = request.getParameter("pages");
			if (pages == null) {
				pages = "1";
			}
			SysYh yh =AppUtil.getCurrentUser();
			String yhbh =yh.getYhbh();
			String xm = yh.getXm();
	   		List<Map<String,Object>> list=service.getBxcl(bm, wxdl, bxzt, pages, xm);
	   		List<Map<String,Object>> wxlist=service.getWxdl();
	   		int count = service.getBxclCount(request);
	   		if(list.size()!=0){
	   			mav.addObject("list", list);
	   		}
	   	/*	if(listXq.size()!=0){
	   			mav.addObject("listXq", listXq);
	   		}*/
	   		mav.addObject("wxlist", wxlist);
	   		mav.addObject("pages", pages);
			mav.addObject("count", count);
	   		return mav;
	   	}
		
		/**
		*报修处理查看
		@Author oufeng	
		@Date 2015年8月13日 上午11:26:55
		@Version 1.0
		*/
		@RequestMapping(value="/see")
	   	public ModelAndView toSee(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException{
	   		ModelAndView mav=new ModelAndView("/bx/bxcl/bxClCheck");
	   		String id=request.getParameter("id");
	   		List<Map<String,Object>> list=service.getSee(id);
	  		mav.addObject("list", list);
	   		return mav;
		}
		
		/**
   		 * 完成
   		 * @author oufeng 
   		 * @date 2015年8月10日
   		 * @param request
   		 * @param response
   		 * @return
   		 * @throws UnsupportedEncodingException 
   		 * @throws ParseException 
   		 */
   		@RequestMapping(value="/succ")
   	   	public ModelAndView toSucc(HttpServletRequest request, HttpServletResponse response) 
   	   			throws UnsupportedEncodingException, ParseException{
   	   		ModelAndView mav=new ModelAndView("/bx/bxcl/bxClSucc");
   	   		return mav;
   	   	}
   		
 		/**
   		 * 处理完成
   		 * @author oufeng 
   		 * @date 2015年8月10日
   		 * @param request
   		 * @param response
   		 * @return
   		 * @throws UnsupportedEncodingException 
   		 * @throws ParseException 
   		 */
   		@RequestMapping(value="/succCon")
   	   	public void toSuccCon(HttpServletRequest request, HttpServletResponse response) 
   	   			throws UnsupportedEncodingException, ParseException{
   			service.toSuccCon(request,response);
   			service.toSuccSpyjCon(request, response);
   			//微信后台提醒
   			service.hfTx(request,response);
   		}
   		
   		/**
   		 * 批量处理完成
   		 * @author oufeng 
   		 * @date 2015年8月10日
   		 * @param request
   		 * @param response
   		 * @return
   		 * @throws UnsupportedEncodingException 
   		 * @throws ParseException 
   		 */
   		@RequestMapping(value="/succCheckBox")
   	   	public void toSuccCheckBox(HttpServletRequest request, HttpServletResponse response) 
   	   			throws UnsupportedEncodingException, ParseException{
   			service.toSuccCheckBox(request, response);
   			service.toSuccSpyjCheckBox(request, response);
   		}
   		
   		/**
		 * 根据维修大类获得报修的主题
		 * @author oufeng 
		 * @date 2015年8月10日
		 * @param request
		 * @param response
		 * @return
		 * @throws UnsupportedEncodingException 
		 * @throws ParseException 
		 */
		@RequestMapping(value="/getBxzt")
		@ResponseBody
	   	public List<Map<String, Object>>  getBxzt(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException{
	   		List<Map<String,Object>> list=service.getBxzt(request, response);
	   		return list;
	   	}
}
