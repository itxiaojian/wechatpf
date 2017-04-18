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
import com.sliu.framework.app.bx.service.BxBxglService;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;

	/**
	 * @author  oufeng
	 * @version 创建时间：2015年8月07日  上午10:49:44
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@Controller
	@RequestMapping("/bx/bxgl")
	public class BxBxglController extends BaseController {

		protected final transient Logger logger = LoggerFactory
				.getPresentationLog(BxBxglController.class);

		@Autowired
		private BxBxglService service;

		/**
		 * 主界面
		 * @author oufeng
		 * @date 2015年8月7日
		 * @return
		 */
		@RequestMapping(value = "/index")
		public String index() {
			return "/bx/bxgl/bxGlView";
		}

		/**
		 * 列表数据
		 * @author oufeng
		 * @date 2015年8月7日
		 * @param start
		 * @param limit
		 * @param zt
		 * @return
		 */
		@RequestMapping(value = "/pager")
		@ResponseBody
		public Pagination<Map<String, Object>> pager(Integer start, Integer limit,
				String zt,String code,String starttime,String endtime) {
			return service.pager(start, limit, zt,code,starttime,endtime);
		}
			
		/**
		 * 获取状态
		 * @author oufeng
		 * @date 2015年8月7日
		 * @return
		 */
			@RequestMapping(value = "/getZt")
			@ResponseBody
			public List<Map<String,Object>> getLx(){
				return service.getZt();
			}
			
			/**
			 * 删除
			 * @author oufeng
			 * @date 2015年8月7日
			 * @return
			 */
			@RequestMapping(value = "/delete")
			@ResponseBody
			public ResponseData delete(Long[] ids) {
				for (Long id : ids) {
					service.deleteEntity(id);
				}
				return ResponseData.SUCCESS_NO_DATA;
			}
//			
//			/**
//			 * 报修手机端页面
//			 * @author oufeng 
//			 * @date 2015年8月7日
//			 * @param request
//			 * @param response
//			 * @return
//			 * @throws UnsupportedEncodingException 
//			 */
//			@RequestMapping(value="/list")
//		   	public ModelAndView toBxsq(HttpServletRequest request, HttpServletResponse response) 
//		   			throws UnsupportedEncodingException{
//		   		ModelAndView mav=new ModelAndView("/bx/bxgl/bxGlMobile");
//		   		String pages = request.getParameter("pages");
//		   		String bm=request.getParameter("bm");
//		   		String wxdl=request.getParameter("wxdl");
//		   		String bxzt=request.getParameter("bxzt");
//		   		if(bm!=null){
//			   		bm = new String(bm.getBytes("ISO-8859-1"), "UTF-8"); 
//			   		}else{
//			   			bm="";
//			   		}
//			   		if(bxzt!=null){
//			   	   		bxzt = new String(bxzt.getBytes("ISO-8859-1"), "UTF-8"); 
//			   	   		}else{
//			   	   			bxzt="";
//			   	   		}
//			   		if(wxdl!=null){
//			   	   		wxdl = new String(wxdl.getBytes("ISO-8859-1"), "UTF-8"); 
//			   	   		}else{
//			   	   			wxdl="";
//			   	   		}
//				if (pages == null) {
//					pages = "1";
//				}
//		   		String zt=request.getParameter("zt");
//		 		int count = service.getCount(request);
//		   		List<Map<String,Object>> list=service.getList(zt, bm, wxdl, bxzt, pages);
//		   		List<Map<String,Object>> listZt=service.getZt();
//		   		List<Map<String,Object>> wxlist=service.getWxdl();
//		   			mav.addObject("list", list);
//		   			mav.addObject("wxlist", wxlist);
//		   			mav.addObject("pages", pages);
//		   			mav.addObject("count", count);
//		   			mav.addObject("listZt", listZt);
//		   			mav.addObject("bm", bm);
//		   			mav.addObject("wxdl", wxdl);
//		   			mav.addObject("bxzt", bxzt);
//		   		return mav;
//		   	}
//			
//			/**
//			 * 报修手机端页面查看
//			 * @author oufeng 
//			 * @date 2015年8月10日
//			 * @param request
//			 * @param response
//			 * @return
//			 */
//			@RequestMapping(value="/check")
//		   	public ModelAndView toCheck(HttpServletRequest request, HttpServletResponse response){
//		   		ModelAndView mav=new ModelAndView("/bx/bxgl/bxGlCheck");
//		   		String bxbh=request.getParameter("bxbh");
//		   		List<Map<String,Object>> list=service.getCheck(bxbh);
//		   		if(list.size()!=0){
//		   			mav.addObject("list", list);
//		   		}
//		   		return mav;
//		   	}
//			
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
//		   	public List<Map<String, Object>>  getBxzt(HttpServletRequest request, HttpServletResponse response) 
//		   			throws UnsupportedEncodingException{
//		   		List<Map<String,Object>> list=service.getBxzt(request, response);
//		   		return list;
//		   	}
			
			/**
			 * 批量的结束报修
			 * @author oufeng
			 * @date 2016年9月9日
			 * @return
			 */
			@RequestMapping(value = "/pljs")
			@ResponseBody
			public ResponseData pjjs(String starttime,String endtime) {
				String str =service.pljs(starttime,endtime);
				if("1".equals(str)){
				return ResponseData.SUCCESS_NO_DATA;
				}else{
				return ResponseData.FAILED_NO_DATA;
				}
			}
		}
