//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

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
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.model.WjObject;
import com.sliu.framework.app.wsh.service.WjObjectService;
import com.sliu.framework.app.wsh.service.WjQuestionService;

/**
 * 
 * @author liujiasen
 * @date 2015年6月4日
 */
@Controller
@RequestMapping("/wsh/WjObject")
public class WjObjectController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WjObjectController.class);
	
	@Autowired
	private WjObjectService wjObjectService;
	
	@Autowired
	private WjQuestionService qs;
	
	/**
	 * 问卷调查信息列表
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @return
	 */
	@RequestMapping(value="/toWjList")
   	public ModelAndView toWjList(){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjObjectList");
   		List<Map<String,Object>> list=wjObjectService.ListObjectBean("");
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
   	}
	
	/**
	 * 问卷调查列表
	 * @author liujiasen
	 * @date 2015年6月10日
	 * @return
	 */
	@RequestMapping(value="/toDcwj")
   	public ModelAndView toDcwj(String openId,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjxxList");
   		String bt=request.getParameter("bt");
   		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}
		else{
   		List<Map<String,Object>> list=wjObjectService.ListObject(bt,openId);
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
		}
   		mav.addObject("openId", openId);
   		return mav;
   	}
	
	/**
	 * 跳转到新增页面
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @return
	 */
	@RequestMapping(value="/toAdd")
   	public ModelAndView toAdd(){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjObjectAdd");
   		return mav;
   	}
	
	/**
	 * 新增调查问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response){
		WjObject obj = new WjObject();
		String title = request.getParameter("title");
		String discribe = request.getParameter("discribe");
		String anonymousFlag = request.getParameter("anonymousFlag");
		String remark = request.getParameter("remark");
		obj.setTitle(title);
		obj.setDiscribe(discribe);
		obj.setAnonymousflag(anonymousFlag);
		obj.setRemark(remark);
		obj.setCreatetime(new Date());
		obj.setState(0);
		wjObjectService.intsertObjectBean(obj);
		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjObjectList");
		
   		List<Map<String,Object>> list=wjObjectService.ListObjectBean("");
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
	}
	
	/**
	 * 问卷调查信息
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @return
	 */
	@RequestMapping(value="/toWjDetail")
   	public ModelAndView toWjDetail(String id){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjObjectDetail");
   		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		return mav;
   	}
	
	/**
	 * 跳转到修改页面
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @return
	 */
	@RequestMapping(value="/toUpdate")
   	public ModelAndView toUpdate(String id){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjObjectUpdate");
   		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		return mav;
   	}
	
	/**
	 * 修改调查问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		WjObject obj = new WjObject();
		String title = request.getParameter("title");
		String discribe = request.getParameter("discribe");
		String anonymousFlag = request.getParameter("anonymousFlag");
		String remark = request.getParameter("remark");
		String oid = request.getParameter("oid");
		obj.setOid(Integer.parseInt(oid));
		obj.setTitle(title);
		obj.setDiscribe(discribe);
		obj.setAnonymousflag(anonymousFlag);
		obj.setRemark(remark);
		obj.setCreatetime(new Date());
		obj.setState(0);
		wjObjectService.updateObjectBean(obj);
		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjObjectList");
		
   		List<Map<String,Object>> list=wjObjectService.ListObjectBean("");
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
	}
	
	/**
	 * 删除调查问卷
	 * @author liujiasen
	 * @date 2015年6月5日
	 * @param id 编号
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public ModelAndView delete(Integer id){
		wjObjectService.delObjectBean(id);
		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjObjectList");
		
   		List<Map<String,Object>> list=wjObjectService.ListObjectBean("");
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
	}
	
	/**
	 * 问卷发布
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/objFb")
	@ResponseBody
	public String objFb(HttpServletRequest request) {

		String result = wjObjectService.objFb(request);
		if (result.equals("0")) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * 问卷撤销
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/objCx")
	@ResponseBody
	public String objCx(HttpServletRequest request) {

		String result = wjObjectService.objCx(request);
		if (result.equals("0")) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * 问卷终止评议
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/objZz")
	@ResponseBody
	public String objZz(HttpServletRequest request) {
		
		String result = wjObjectService.objZz(request);
		if (result.equals("0")) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * 查看问卷调查结果
	 * @author liujiasen
	 * @date 2015年7月22日
	 * @param id 问卷编号
	 * @return
	 */
	@RequestMapping(value="/toWjDcjg")
   	public ModelAndView toWjDcjg(String id,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjDcjg");
   		int oid = Integer.parseInt(id);
   		String lx=request.getParameter("lx");
   		if(lx==null || "".equals(lx)){
   			lx="0";
   		}
   		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   		}
   		mav.addObject("lx",lx);
   		mav.addObject("oid",id);
   		return mav;
   	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月22日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toWjDcjgZzt")
   	public ModelAndView toWjDcjgZzt(String oid,String qseq){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjjgZzt");
   		/*List<Map<String,Object>> list=wjObjectService.getWjXz(oid,qseq);
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}*/
   		mav.addObject("oid",oid);
   		mav.addObject("qseq",qseq);
   		return mav;
   	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月23日
	 * @param oid
	 * @param qseq
	 * @return
	 */
	@RequestMapping(value="/toWjDcjgBt")
   	public ModelAndView toWjDcjgBt(String oid,String qseq){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjjgBt");
   		mav.addObject("oid",oid);
   		mav.addObject("qseq",qseq);
   		return mav;
   	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月23日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getWjXzBt")
	@ResponseBody
	public List<Map<String,Object>> getWjXzBt(HttpServletRequest request) {
		String oid=request.getParameter("oid");
		String qseq=request.getParameter("qseq");
		List<Map<String,Object>> list=wjObjectService.getWjXzBt(oid,qseq);
   		return list;
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月22日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getWjXz")
	@ResponseBody
	public List<Map<String,Object>> getWjXz(HttpServletRequest request) {
		String oid=request.getParameter("oid");
		String qseq=request.getParameter("qseq");
		List<Map<String,Object>> list=wjObjectService.getWjXz(oid,qseq);
   		return list;
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月23日
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toWjDcjgSj")
   	public ModelAndView toWjDcjgSj(String id,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjDcjgSj");
   		int oid = Integer.parseInt(id);
   		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   		}
   		mav.addObject("oid",id);
   		return mav;
   	}
}
