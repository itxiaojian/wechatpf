package com.sliu.framework.app.bx.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.sliu.framework.app.bx.service.BxBxglService;
import com.sliu.framework.app.bx.service.BxBxshService;
import com.sliu.framework.app.bx.service.BxBxslService;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;

/**
*
@Author oufeng	
@Date 2015年8月10日 下午4:36:21
@Version 1.0
*/
@Controller
@RequestMapping("/bx/bxsh")
public class BxBxshController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(BxBxshController.class);

	@Autowired
	private BxBxshService bxshService;
	
	@Autowired
	private BxBxglService bxglService;
	
	@Autowired
	private BxBxslService bxslService;


	/**
	 * 报修审核主页面
	 * @author chenhui 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/shlist")
	public String index() {
		return "/bx/bxsh/bxShWatch";
	}

	/**
	 * 获取维修工
	 * 
	 * @author chenhui
	 * @date 2015年8月7日
	 * @return
	 */
	@RequestMapping(value = "/getWxg")
	@ResponseBody
	public List<Map<String, Object>> getWxg() {
		return bxshService.getWxg();
	}
	
	/**
	 * 列表数据
	 * @author chenhui
	 * @date 2015年8月7日
	 * @param start
	 * @param limit
	 * @param zt
	 * @return
	 */
	@RequestMapping(value = "/pager")
	@ResponseBody
	public Pagination<Map<String, Object>> getBxsh(Integer start,Integer limit,String bm,String wxdl,String bxzt) {
		return bxglService.getBxsh(start, limit, bm, wxdl, bxzt);
	}
		
	/**
	 * 获取维修大类
	 * @author oufeng
	 * @date 2015年8月7日
	 * @return
	 */
		@RequestMapping(value = "/getWxdl")
		@ResponseBody
		public List<Map<String,Object>> getWxdl(){
			return bxshService.getWxdl();
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
			return bxshService.getBxzt(wxdl);
		}

		/**
		 * 驳回理由
		 * @author oufeng 
		 * @date 2015年8月10日
		 * @param request
		 * @param response
		 * @return
		 * @throws UnsupportedEncodingException 
		 * @throws ParseException 
		 */
		@RequestMapping(value="/rejecCon")
		@ResponseBody
	   	public ResponseData toRejecCon(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException, ParseException{
			bxshService.toRejecCon(request,response);
			bxshService.rejecTx(request,response);
			return ResponseData.SUCCESS_NO_DATA;
		}
		
		
	/**
	 * 报修审核主页面
	 * @author oufeng 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/list")
   	public ModelAndView toCheck(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/bx/bxsh/bxShView");
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
   		List<Map<String,Object>> list=bxglService.getBxsh(bm,wxdl,bxzt,pages);
   		List<Map<String,Object>> wxlist=bxshService.getWxdl();
   		int count = bxglService.getBxshCount(request);
   		mav.addObject("list", list);		
   		mav.addObject("wxlist", wxlist);  		
   		mav.addObject("pages", pages);
		mav.addObject("count", count);
		mav.addObject("bm", bm);
		mav.addObject("wxdl", wxdl);
		mav.addObject("bxzt", bxzt);
   		return mav;
   	}
	
	
	/**
	 * 审批
	 * @author oufeng 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/makesh")
   	public ModelAndView toSh(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/bx/bxsh/bxShCheck");
   		String bxbh=request.getParameter("dm");
   		List<Map<String,Object>> list=bxglService.getCheck(bxbh);
  		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
   	}
	
	/**
	 * 派单
	 * @author oufeng 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/send")
   	public ModelAndView toSend(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/bx/bxsh/bxShSend");
   		String id=request.getParameter("id");
   		//List<Map<String,Object>> list=bxshService.getWxzl(request, response);
   		List<Map<String,Object>> list=bxshService.getWxg();
   		//List<Map<String,Object>> listWxdl=bxshService.getWxdl(id);
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
//   		if(listWxdl.size()!=0){
//   			mav.addObject("listWxdl", listWxdl);
//   		}
   		return mav;
   	}
	
	/**
	 * 派单
	 * @author oufeng 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/getsender")
	@ResponseBody
   	public List<Map<String, Object>>  getSender(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException{
   		String id=request.getParameter("id");
   		List<Map<String,Object>> list=bxshService.getSerder(id);
   		return list;
   	}
	
	/**
	 * 派单处理
	 * @author oufeng 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/sendCon")
   	public void saveSend(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException{
   		bxslService.saveSender(request,response);
   		bxshService.updateSp(request,response);
   		bxshService.updateSpyj(request,response);
   	}
	
	/**
	 * 审批通过
	 * @author oufeng 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping(value="/moditg")
   	public ModelAndView toTg(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
   		ModelAndView mav=new ModelAndView("/bx/bxsh/bxShModify");
   		String bxbh=request.getParameter("ztbh");
   	   bxglService.settg(bxbh);
      bxshService.savetg(request, response);
   		return mav;
   	}
	
	/**
	 * 审批驳回
	 * @author oufeng 
	 * @date 2015年8月10日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping(value="/modibh")
   	public ModelAndView toBh(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException, ParseException{
   		ModelAndView mav=new ModelAndView("/bx/bxsh/bxShModify");
   		String bxbh=request.getParameter("ztbh");
   	   bxglService.setbh(bxbh);
      bxshService.savebh(request, response);
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
	   	public ModelAndView toRejec(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException, ParseException{
	   		ModelAndView mav=new ModelAndView("/bx/bxsh/bxShRejec");
	   	   String id=request.getParameter("id");
	      mav.addObject("id", id);
	   		return mav;
	   	}
		
//		/**
//		 * 驳回理由
//		 * @author oufeng 
//		 * @date 2015年8月10日
//		 * @param request
//		 * @param response
//		 * @return
//		 * @throws UnsupportedEncodingException 
//		 * @throws ParseException 
//		 */
//		@RequestMapping(value="/rejecCon")
//	   	public void toRejecCon(HttpServletRequest request, HttpServletResponse response) 
//	   			throws UnsupportedEncodingException, ParseException{
//			bxshService.toRejecCon(request,response);
//		}

		/**
		 * 批量派单
		 * @author oufeng 
		 * @date 2015年8月10日
		 * @param request
		 * @param response
		 * @return
		 * @throws UnsupportedEncodingException 
		 * @throws ParseException 
		 */
		@RequestMapping(value="/checkBoxSend")
		@ResponseBody
	   	public ResponseData checkBoxSend(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException, ParseException{
	   		//List<Map<String,Object>> listWxdl=bxshService.getWxdl(id);
				bxslService.saveSenderCheckBox(request,response);
		   		bxshService.updateSpCheckBox(request,response);
		   		bxshService.saveSpyjCheckBox(request,response);
		   	    //报修派单微信后台提醒
		   		bxshService.pdTxCheckBox(request,response);
		   		return ResponseData.SUCCESS_NO_DATA;
		}
		
//		/**
//		 * 批量派单
//		 * @author oufeng 
//		 * @date 2015年8月10日
//		 * @param request
//		 * @param response
//		 * @return
//		 * @throws UnsupportedEncodingException 
//		 * @throws ParseException 
//		 */
//		@RequestMapping(value="/checkBoxSend")
//	   	public void checkBoxSend(HttpServletRequest request, HttpServletResponse response) 
//	   			throws UnsupportedEncodingException, ParseException{
//				bxslService.saveSenderCheckBox(request,response);
//		   		bxshService.updateSpCheckBox(request,response);
//		   		bxshService.saveSpyjCheckBox(request,response);
//		}
		
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
		@RequestMapping(value="/rejecChecBox")
		@ResponseBody
	   	public ResponseData checkBoxRejec(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException, ParseException{
			bxshService.toRejecCheckBox(request,response);
			bxshService.rejecTxCheckBox(request,response);
			return ResponseData.SUCCESS_NO_DATA;
		}
		
//		/**
//		 * 批量驳回
//		 * @author oufeng 
//		 * @date 2015年8月10日
//		 * @param request
//		 * @param response
//		 * @return
//		 * @throws UnsupportedEncodingException 
//		 * @throws ParseException 
//		 */
//		@RequestMapping(value="/rejecChecBox")
//	   	public void checkBoxRejec(HttpServletRequest request, HttpServletResponse response) 
//	   			throws UnsupportedEncodingException, ParseException{
//			bxshService.toRejecCheckBox(request,response);
//		}
		
//		/**
//		 * 根据维修大类获得报修的主题
//		 * @author oufeng 
//		 * @date 2015年8月10日
//		 * @param request
//		 * @param response
//		 * @return
//		 * @throws UnsupportedEncodingException 
//		 * @throws ParseException 
//		 */
//		@RequestMapping(value="/getBxzt")
//		@ResponseBody
//	   	public List<Map<String, Object>>  getBxzt(HttpServletRequest request, HttpServletResponse response) 
//	   			throws UnsupportedEncodingException{
//	   		List<Map<String,Object>> list=bxshService.getBxzt(request, response);
//	   		return list;
//	   	}
//		
}


