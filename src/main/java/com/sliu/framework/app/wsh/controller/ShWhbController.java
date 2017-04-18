package com.sliu.framework.app.wsh.controller;

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
import com.sliu.framework.app.wsh.service.ShWhbMbService;

/**
 * 
 * 微海报
 * 
 * @Author oufeng
 * @Date 2015年7月3日 下午1:51:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/wsh/ShWhb")
public class ShWhbController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShWhbController.class);

	@Autowired
	private ShWhbMbService service;

	/**
	 * 微海报增加页面
	 * 
	 * @author oufeng
	 * @Date 2015年7月3日 下午1:51:09
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toCreatWhb")
	public ModelAndView toCreatWhb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbCreat");
		List<Map<String, Object>> muban = service.getMb(request);
		List<Map<String, Object>> mubanno = service.getMbNo(request);
		List<Map<String, Object>> mubanH = service.getMbHidden(request);
		if (muban.size() != 0) {
			mav.addObject("list", muban);
		}
		if (mubanno.size() != 0) {
			mav.addObject("map", mubanno);
		}
		if (mubanH.size() != 0) {
			mav.addObject("listMb", mubanH);
			mav.addObject("size", mubanH.size());
		}
		return mav;
	}

	@RequestMapping(value = "/deleteMb")
	public void toDeleteMb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String idString = request.getParameter("id");
		if(idString !=null){
		int id = Integer.parseInt(idString);
		service.deleteMb(id);
		}
	}
	
	/**
	 * 微海报
	 * 
	 * @author oufeng
	 * @Date 2015年7月3日 下午1:51:09
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toWhb")
	public ModelAndView toWhbList(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbList");
		// String pages = request.getParameter("pages");
		// if (pages == null) {
		// pages = "1";
		// }
		// String openId = AppUtil.getOpenId(request, response);
		// List<Map<String, Object>> list =
		// service.getWhbList(request,response);
		// int count = service.getCount(request);
		// if (list.size() != 0) {
		// mav.addObject("list", list);
		// mav.addObject("pages", pages);
		// mav.addObject("count", count);
		// mav.addObject("openId", openId);
		// }
		return mav;
	}

	/**
	 * 微海报增加页面
	 * 
	 * @author oufeng
	 * @Date 2015年7月3日 下午1:51:09
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/toAddWhb")
	public ModelAndView toAddWhb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException, ParseException {
		String music_name = request.getParameter("music_name");
		String name = request.getParameter("fileName");
		String music_route = request.getParameter("music");
		//SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String poster_name = request.getParameter("poster_name");
		Long id=service.saveZt(request, response);
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbAdd");
		List<Map<String, Object>> list = service.getZt(id);
		if (music_name != null || list.size()!=0) {
			mav.addObject("map", list.get(0));
			mav.addObject("music_name", music_name);
			mav.addObject("photo_route", name);
			mav.addObject("music_route", music_route);
			mav.addObject("poster_name", poster_name);
		}
		return mav;
	}
	
	/**
	 * 上传
	 * @author oufeng
	 * @Date 2015年7月3日 下午1:51:09
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/toUpload")
	public ModelAndView toUpload(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException, ParseException {
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbUpload");
		return mav;
	}
	
	
	/**
	 * 上传保存
	 * 
	 * @author oufeng
	 * @Date 2015年7月3日 下午1:51:09
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/save")
	public ModelAndView  toSave(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException, ParseException {
		service.saveFuJian(request, response);
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbUpload");
		return mav;
	}
	
	
	/**
	 * 创建微海报模板的保存
	 * 
	 * @author oufeng
	 * @Date 2015年7月3日 下午1:51:09
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toSaveWhb")
	public ModelAndView toSaveWhb(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbBuild");
		String bgtp=request.getParameter("bgtp");
		if(bgtp!=null&&!"".equals(bgtp)){
			mav.addObject("bgtp", bgtp);
		}
		List<Map<String, Object>> list = service.getBjMsic(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		return mav;
	}
	
	
/*	*//**
	 * 删除
	 * @author  oufeng
	 * @version 创建时间：2015年7月3日  下午15:49:44
	 * @param ids
	 * @return
	 *//*
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Integer id){
		service.delete(id);
	return ResponseData.SUCCESS_NO_DATA;
	}*/
	/**
	 * 保存页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @return
	 */
	@RequestMapping(value="/saveYm")
	@ResponseBody
	public String saveYm(HttpServletRequest request){
		String str="0";
		str=service.saveYm(request);
		return str;
	}
	
	/**
	 * 删除页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @return
	 */
	@RequestMapping(value="/deleteYm")
	@ResponseBody
	public String deleteYm(HttpServletRequest request){
		String str="0";
		str=service.deleteYm(request);
		return str;
	}
	
	/**
	 * 修改页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateYm")
	@ResponseBody
	public String updateYm(HttpServletRequest request){
		String str="0";
		str=service.updateYm(request);
		return str;
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toSjYm")
	public ModelAndView toSjYm(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbSjYm");
		List<Map<String,Object>> whb=service.getWhb();
		if(whb.size()!=0){
			mav.addObject("whb", whb.get(0));
			List<Map<String,Object>> whbym=service.getWhbYm((whb.get(0).get("id")+""));
			if(whbym.size()!=0){
				mav.addObject("whbym", whbym);
			}
			mav.addObject("ymtp", service.getWhbYmTp((whb.get(0).get("id")+"")));
		}
		return mav;
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toUpdateWhb")
	public ModelAndView toUpdateWhb(HttpServletRequest request,HttpServletResponse response) {
		String hbid = request.getParameter("hbid");
		service.deleteYm(hbid);
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbUpdate");
		List<Map<String, Object>> list = service.getWhbZt(hbid);
		if(list.size()!=0){
			mav.addObject("map", list.get(0));
		}
		return mav;
	}
	
	/**
	 * 修改微海报
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toUpdateWhbZt")
	public ModelAndView toUpdateWhbZt(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbZtUpdate");
		String id=request.getParameter("id");
		List<Map<String, Object>> map = service.getWhbZt(id);
		if(map.size()!=0){
			mav.addObject("map", map.get(0));
		}
		List<Map<String, Object>> list = service.getBjMsic(request);
		if (list.size() != 0) {
			mav.addObject("list", list);
		}
		return mav;
	}
	
	/**
	 * 修改海报
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toEditWhb")
	public ModelAndView toEditWhb(HttpServletRequest request,HttpServletResponse response) {
		String music_name = request.getParameter("music_name");
		String name = request.getParameter("fileName");
		String music_route = request.getParameter("music");
		//SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String poster_name = request.getParameter("poster_name");
		String id = request.getParameter("id");
		service.updateZt(request, response);
		ModelAndView mav = new ModelAndView("/wsh/ShWhb/shWhbAdd");
		List<Map<String, Object>> list = service.getZt(Long.parseLong(id));
		if (music_name != null || list.size()!=0) {
			mav.addObject("map", list.get(0));
			mav.addObject("music_name", music_name);
			mav.addObject("photo_route", name);
			mav.addObject("music_route", music_route);
			mav.addObject("poster_name", poster_name);
		}
		return mav;
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/whbZtfb")
	@ResponseBody
	public String whbZtfb(HttpServletRequest request){
		String str="0";
		str=service.whbZtfb(request);
		return str;
	}
}