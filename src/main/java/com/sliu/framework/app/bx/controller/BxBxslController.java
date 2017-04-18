package com.sliu.framework.app.bx.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.sliu.framework.app.bx.service.BxBxslService;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;


/**
*报修受理
@Author oufeng	
@Date 2015年8月13日 上午11:26:55
@Version 1.0
*/
@Controller
@RequestMapping("/bx/bxsl")
	public class BxBxslController extends BaseController {

		protected final transient Logger logger = LoggerFactory
				.getPresentationLog(BxBxslController.class);

		@Autowired
		private BxBxslService service;
 
		/**
		 * 主界面
		 * 
		 * @author chenhui
		 * @date 2015年8月7日
		 * @return
		 */
		@RequestMapping(value = "/index")
		public ModelAndView index() {
			ModelAndView mav=new ModelAndView("/bx/bxsl/bxSlWatch");
			Calendar calendar = Calendar.getInstance();
	   		calendar.add(Calendar.DATE, -5); //得到前一天
	   		Date date = calendar.getTime();
	   		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	   		String time=df.format(date);
	   		mav.addObject("time", time);
			return mav;
		}

		/**
		 * 列表数据
		 * 
		 * @author oufeng
		 * @date 2015年8月7日
		 * @param start
		 * @param limit
		 * @param zt
		 * @return
		 */
		@RequestMapping(value = "/pager")
		@ResponseBody
		public Pagination<Map<String, Object>> getBxsl(HttpServletRequest request,Integer start, Integer limit,
				String bm, String wxdl, String bxzt) {
			if(start==null){start=0;}
			if(limit==null){limit=20;}
			SysYh yh =AppUtil.getCurrentUser();
			String yhbh =yh.getYhbh();
			String xm = yh.getXm();
			String dlm="";
			if(service.getDlm(yhbh).size()!=0){
			 dlm = service.getDlm(yhbh).get(0).get("dlm")+"";}
			Calendar calendar = Calendar.getInstance();
	   		calendar.add(Calendar.DATE, -5); //得到前一天
	   		Date date = calendar.getTime();
	   		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	   		String time=df.format(date);
			return service.getBxsl(start, limit, bm, wxdl, bxzt, xm, dlm,time);
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
		
		@RequestMapping(value="/sllist")
	   	public ModelAndView toCheck(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	   		ModelAndView mav=new ModelAndView("/bx/bxsl/bxSlView");
	   		//bm:过滤参数
	   		String bm=request.getParameter("bm");
	   		String wxdl=request.getParameter("wxdl");
	   		String bxzt=request.getParameter("bxzt");
	   		Calendar calendar = Calendar.getInstance();
	   		calendar.add(Calendar.DATE, -5); //得到前一天
	   		Date date = calendar.getTime();
	   		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	   		String time=df.format(date);
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
			String dlm="";
			if(service.getDlm(yhbh).size()!=0){
			 dlm = service.getDlm(yhbh).get(0).get("dlm")+"";}
	   		List<Map<String,Object>> list=service.getBxsl(bm,wxdl,bxzt,pages,xm,dlm);
	   		List<Map<String,Object>> wxlist=service.getWxdl();
	   		int count = service.getBxslCount(request);
	   		if(list.size()!=0){
	   			mav.addObject("list", list);
	   		}
	   		mav.addObject("wxlist", wxlist);
	   		mav.addObject("pages", pages);
			mav.addObject("count", count);
			mav.addObject("time", time);
			mav.addObject("bm", bm);
			mav.addObject("wxdl", wxdl);
			mav.addObject("bxzt", bxzt);
	   		return mav;
	   	}
		
		
		@RequestMapping(value="/see")
	   	public ModelAndView toSee(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	   		ModelAndView mav=new ModelAndView("/bx/bxsl/bxSlCheck");
	   		String id=request.getParameter("id");
	   		List<Map<String,Object>> list=service.getSee(id);
	  		mav.addObject("list", list);
	   		return mav;
	   		
		}
	   		/**
	   		 * 受理
	   		 * @author oufeng 
	   		 * @date 2015年8月10日
	   		 * @param request
	   		 * @param response
	   		 * @return
	   		 * @throws UnsupportedEncodingException 
	   		 * @throws ParseException 
	   		 */
	   		@RequestMapping(value="/deal")
	   	   	public ModelAndView toDeal(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
	   	   		ModelAndView mav=new ModelAndView("/bx/bxsl/bxSlView");
	   	     	service.toDeal(request,response);
	   	        service.toDealSpyj(request, response);
	   	        //打印报修，报修的处理的的提醒
	   	        service.dealTx(request, response);
	   	   		return mav;
	   	   	}
	   		
	   		/**
	   		 * 批量受理
	   		 * @author oufeng 
	   		 * @date 2015年8月10日
	   		 * @param request
	   		 * @param response
	   		 * @return
	   		 * @throws UnsupportedEncodingException 
	   		 * @throws ParseException 
	   		 */
	   		@RequestMapping(value="/dealCheckBox")
	   	   	public ModelAndView toDealCheckBox(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
	   	   		ModelAndView mav=new ModelAndView("/bx/bxsl/bxSlView");
	   	     	service.toDealCheckBox(request,response);
	   	        service.toDealSpyjCheckBox(request,response);
	   	   		return mav;
	   	   	}
	   		
	   		/**
	   		 * 驳回
	   		 * @author oufeng 
	   		 * @date 2015年8月10日
	   		 * @param request
	   		 * @param response
	   		 * @return
	   		 * @throws UnsupportedEncodingException 
	   		 * @throws ParseException 
	   		 */
	   		@RequestMapping(value="/rejec")
	   	   	public ModelAndView toRejec(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
	   	   		ModelAndView mav=new ModelAndView("/bx/bxsl/bxSlRejec");
	   	   	   String id=request.getParameter("id");
	   	      mav.addObject("id", id);
	   	   		return mav;
	   	   	}
	   		
	   		/**
			 * 驳回确定
			 * @author oufeng 
			 * @date 2015年8月10日
			 * @param request
			 * @param response
			 * @return
			 * @throws UnsupportedEncodingException 
			 * @throws ParseException 
			 */
			@RequestMapping(value="/rejecCon")
		   	public void toRejecCon(HttpServletRequest request, HttpServletResponse response) 
		   			throws UnsupportedEncodingException, ParseException{
				//service.toRejecCon(request,response);
				service.updateBxsq(request,response);
			}
	   		
			/**
			 * 批量驳回
			 * @author oufeng 
			 * @date 2015年8月10日
			 * @param request
			 * @param response
			 * @return
			 * @throws UnsupportedEncodingException 
			 * @throws ParseException 
			 */
			@RequestMapping(value="/rejecCheckBox")
		   	public void checkBoxRejec(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
				service.updateBxsqCheckBox(request,response);
			}
			
//	   		/**
//			 * 根据维修大类获得报修的主题
//			 * @author oufeng 
//			 * @date 2015年8月10日
//			 * @param request
//			 * @param response
//			 * @return
//			 * @throws UnsupportedEncodingException 
//			 * @throws ParseException 
//			 */
//			@RequestMapping(value="/getBxzt")
//			@ResponseBody
//		   	public List<Map<String, Object>>  getBxzt(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
//		   		List<Map<String,Object>> list=service.getBxzt(request, response);
//		   		return list;
//		   	}
			
			/**
			 * 报修派单的打印
			 * @author oufeng
			 * @date 2016年3月17日
			 * @return
			 * @throws UnsupportedEncodingException 
			 */
			@RequestMapping(value = "/openPdPage")
			public ModelAndView openPrintPage(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
				ModelAndView mav = new ModelAndView("/bx/bxsl/bxPdPage");
				/*String sender=request.getParameter("sender");
				try {
					sender= new String(sender.getBytes("ISO-8859-1"), "GBK");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}*/
			/*	bxslService.savePd(request,response);
		   		bxshService.updateSp(request,response);
		   		bxshService.updateSpyj(request,response);*/
		   		List<Map<String,Object>> list=service.getPdList(request);
		   		mav.addObject("list", list);
		   		//mav.addObject("sender", sender);
				return mav;
			}
			
			/**
	   		 * 用戶未反馈，结束报修
	   		 * @author oufeng 
	   		 * @date 2015年8月10日
	   		 * @param request
	   		 * @param response
	   		 * @return
	   		 * @throws UnsupportedEncodingException 
	   		 * @throws ParseException 
	   		 */
	   		@RequestMapping(value="/js")
	   	   	public ModelAndView toJs(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
	   	   		ModelAndView mav=new ModelAndView("/bx/bxsl/bxSlView");
	   	     	service.toSuccCon(request,response);
	   	        service.toSuccSpyjCon(request, response);
	   	   		return mav;
	   	   	}
	   		
	   		/**
	   		 * 
	   		 * @author oufeng 
	   		 * @date 2015年8月10日
	   		 * @param request
	   		 * @param response
	   		 * @return
	   		 * @throws UnsupportedEncodingException 
	   		 * @throws ParseException 
	   		 */
	   		@RequestMapping(value="/toWxgPage")
	   	   	public ModelAndView toWxgPage(HttpServletRequest request, HttpServletResponse response) {
	   	   		ModelAndView mav=new ModelAndView("/bx/bxsl/bxWxgPage");
	   	   		String id = request.getParameter("id");
	   	   		String openId = request.getParameter("openId");
	   	   	    List<Map<String,Object>> ztlist = service.getWxzt(id);
	   	   		List<Map<String,Object>> list = service.getDetail(id);
	   	   	    List<Map<String,Object>> hclist = service.getHcList();
	   	   	    if(ztlist.size()!=0){
	   	   	    mav.addObject("zt",ztlist.get(0).get("bxzt").toString());
	   	   	    }
	   	   		mav.addObject("list",list);
	   	     	mav.addObject("hclist",hclist);
	   	        mav.addObject("id",id);
	   	        mav.addObject("openId",openId);
	   	   		return mav;
	   	   	}
}
