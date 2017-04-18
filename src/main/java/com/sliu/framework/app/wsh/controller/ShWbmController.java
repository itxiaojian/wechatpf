//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sliu.framework.app.wsh.model.ShWbm;
import com.sliu.framework.app.wsh.service.ShBmrxxtxjlService;
import com.sliu.framework.app.wsh.service.ShWbmBdService;
import com.sliu.framework.app.wsh.service.ShWbmService;

/**
 * 微报名
 * 
 * @author liujiansen
 * @date 2015年6月24日
 */
@Controller
@RequestMapping("/wsh/ShWbm")
public class ShWbmController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShWbmController.class);

	@Autowired
	private ShWbmService service;
	@Autowired
	private ShWbmBdService bdService;
	@Autowired
	private ShBmrxxtxjlService bService;

	/**
	 * 微报名数据列表
	 * 
	 * @author liujiansen
	 * @date 2015年6月24日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toWbmList")
	public ModelAndView toWbmList(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbmList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = AppUtil.getOpenId(request, response);
		List<Map<String, Object>> list = service.getSwzlList(request,response);
		int count = service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("count", count);
		}
		mav.addObject("openId", openId);
		return mav;
	}
	
	/**
	 * 跳转到手机端显示页面
	 * @author liujiasen
	 * @date 2015年7月3日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWbmSjList")
	public ModelAndView toWbmSjList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbmSjList");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = service.getSwzlList(request,response);
		int count = service.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("count", count);
		}
		mav.addObject("openId", openId);
		}
		return mav;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @author liujiasen
	 * @date 2015年6月24日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWbmAdd")
	public ModelAndView toWbmAdd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbmAdd");
		return mav;
	}

	/**
	 * 报名活动添加
	 * 
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response) {
		Long id = service.saveWbm(request, response);
		return id + "";
	}

	/**
	 * 跳转到表单添加页面
	 * 
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWbmBdAdd")
	public ModelAndView toWbmBdAdd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbmBdAdd");
		String id = request.getParameter("id");
		ShWbm wbm = service.getWbm(id);
		if (wbm != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			mav.addObject("time", sim.format(wbm.getBmjzsj()));
			mav.addObject("map", wbm);
		}
		mav.addObject("id", id);
		return mav;
	}

	/**
	 * 表单保存
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveBd")
	@ResponseBody
	public String saveBd(HttpServletRequest request,
			HttpServletResponse response) {
		String str = bdService.saveWbmBd(request, response);
		return str;
	}

	/**
	 * 跳转到表单编辑页面
	 * 
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWbmBdUpdate")
	public ModelAndView toWbmBdUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbmBdUpdate");
		String id = request.getParameter("id");
		ShWbm wbm = service.getWbm(id);
		if (wbm != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			mav.addObject("time", sim.format(wbm.getBmjzsj()));
			mav.addObject("map", wbm);
		}
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 表单修改
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateBd")
	@ResponseBody
	public String updateBd(HttpServletRequest request,
			HttpServletResponse response) {
		String str = bdService.updateWbmBd(request, response);
		return str;
	}
	
	/**
	 * 报名活动预览
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toWbmView")
	public ModelAndView toWbmView(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbm");
		String openId = AppUtil.getOpenId(request, response);
		if("".equals(openId)){
			openId=request.getParameter("openId");
		}
		List<Map<String,Object>> bmr=service.getBmr(openId);
		if(bmr.size()!=0){
			mav.addObject("bmr", bmr);
		}
		String id = request.getParameter("id");
		ShWbm wbm = service.getWbm(id);
		List<Map<String, Object>> bd = bdService.getWbmBd(id);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
		if (wbm != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			mav.addObject("time", sim.format(wbm.getBmjzsj()));
			mav.addObject("map", wbm);
		}
		if (bd.size() != 0) {
			mav.addObject("bd", bd);
			mav.addObject("size", bd.size());
			for (int i = 0; i < bd.size(); i++) {
				String bdlx = bd.get(i).get("bdlx") + "";
				String ddhz = bd.get(i).get("ddhz") + "";
				if ("3".equals(bdlx)) {
					bdService.getList(ddhz, bd, i, list);
				}
				if ("4".equals(bdlx)) {
					bdService.getList(ddhz, bd, i, list1);
				}
			}
			mav.addObject("list", list);
			mav.addObject("list1", list1);
			mav.addObject("dxs", list1.size());
		}
		mav.addObject("openId", openId);
		mav.addObject("id", id);
		return mav;
	}

	/**
	 * 跳转到报名信息页面
	 * @author liujiasen
	 * @date 2015年7月28日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/toWbmDetail")
	public ModelAndView toWbmDetail(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbmDetail");
		String openId = AppUtil.getOpenId(request, response);
		if("".equals(openId)){
			openId=request.getParameter("openId");
		}
		String id = request.getParameter("id");
		ShWbm wbm = service.getWbm(id);
		if (wbm != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			mav.addObject("time", sim.format(wbm.getBmjzsj()));
			mav.addObject("map", wbm);
		}
		mav.addObject("openId", openId);
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 跳转到报名活动编辑页面
	 * 
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toWbmUpdate")
	public ModelAndView toWbmUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shWbmUpdate");
		String id = request.getParameter("id");
		ShWbm wbm = service.getWbm(id);
		if (wbm != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sim1 = new SimpleDateFormat("HH:mm");
			String arg[] = sim1.format(wbm.getBmjzsj()).split(":");
			mav.addObject("time", sim.format(wbm.getBmjzsj()));
			mav.addObject("hour", arg[0]);
			mav.addObject("min", arg[1]);
			mav.addObject("map", wbm);
		}
		mav.addObject("id", id);
		return mav;
	}

	/**
	 * 报名活动信息修改
	 * 
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(HttpServletRequest request,
			HttpServletResponse response) {
		String str = service.updateWbm(request, response);
		return str;
	}
	
	/**
	 * 删除报名活动
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,String id,
			HttpServletResponse response) {
		String str = service.delete(request, response,id);
		return str;
	}
	
	/**
	 * 查看活动的报名人
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toBmrs")
	public ModelAndView toBmrs(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shBmrxxList");
		String id = request.getParameter("id");
//		List<Map<String,Object>> bt=bService.getBt(id);
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		List<Map<String, Object>> list = bService.getList(request);
		int count = bService.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("count", count);
		}
//		if(bt.size()!=0){
//			mav.addObject("bt", bt);
//		}
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月27日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toBmrsSj")
	public ModelAndView toBmrsSj(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/wsh/ShWbm/shBmrxxSjList");
		String id = request.getParameter("id");
//		List<Map<String,Object>> bt=bService.getBt(id);
		String pages = request.getParameter("pages");
		String openId = request.getParameter("openId");
		if (pages == null) {
			pages = "1";
		}
		mav.addObject("openId", openId);
		List<Map<String, Object>> list = bService.getList(request);
		int count = bService.getCount(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
			mav.addObject("pages", pages);
			mav.addObject("count", count);
		}
//		if(bt.size()!=0){
//			mav.addObject("bt", bt);
//		}
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 删除报名信息
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteBmxx")
	@ResponseBody
	public String deleteBmxx(HttpServletRequest request,HttpServletResponse response) {
		String str = service.deleteDmxx(request,response);
		return str;
	}
	
	/**
	 * 保存报名人填写信息
	 * @author liujiasen
	 * @date 2015年6月29日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveBm")
	@ResponseBody
	public String saveBm(HttpServletRequest request,HttpServletResponse response) {
		String str = service.saveBm(request, response);
		return str;
	}
}
