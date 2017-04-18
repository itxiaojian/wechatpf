package com.sliu.framework.app.xzxx.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.xzxx.model.XxXjxxb;
import com.sliu.framework.app.xzxx.service.XxXjxxbService;

/**
 * 校长信箱-信件信息表 Controller
 * @author            liujiansen
 * @since             2015-09-01
 */
@Controller
@RequestMapping(value="/xzxx/XxXjxxb")
public class XxXjxxbController  extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(XxXjxxbController.class);
    @Autowired
    private XxXjxxbService service;
    
	/**
	 * 后台:校长信箱发布列表
	 * @author duanpeijun
	 * @date 2015年9月1日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getXjxxbList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXjxxbList(Integer start,
			Integer limit,String code ) {
		
		return service.getxjxxbList(start, limit,code);
	}
	@RequestMapping(value = "/xjxxbPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/xzxx/xxXjxxbList");
		String str="0";
		SysYh user=AppUtil.getCurrentUser();
		List<Map<String, Object>> list = service.getJs(user.getYhbh());
		for(int i=0;i<list.size();i++){
			if("ROLE_ADMIN".equals(list.get(i).get("jsmc").toString()) || "ROLE_ADMIN_XZXX".equals(list.get(i).get("jsmc").toString())){
				str="1";
			}
		}
		modelAndView.addObject("isAdm", str);
		return modelAndView;
	}
	/**
	 * 后台:发布信件
	 * @author duanpeijun
	 * @date 2015年9月2日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="/save")
    @ResponseBody
    public ResponseData save(ModelMap model, XxXjxxb entity,
			HttpServletRequest request, HttpServletResponse response)
    {
    	String result = service.saveXxXjxxb(entity, request);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
    }
    /**
     * 后台:修改信件
     * @author duanpeijun
     * @date 2015年9月2日
     * @param entity
     * @param id
     * @return
     */
    @RequestMapping(value="/update")
    @ResponseBody
    public ResponseData update(XxXjxxb entity,String id,String slbmbh)
    {
      XxXjxxb oldEntity = service.getXxjxxbById(Long.parseLong(id));
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      String str="0";
	  SysYh user=AppUtil.getCurrentUser();
	  List<Map<String, Object>> list = service.getJs(user.getYhbh());
	  for(int i=0;i<list.size();i++){
		if("ROLE_ADMIN".equals(list.get(i).get("jsmc").toString()) || "ROLE_ADMIN_XZXX".equals(list.get(i).get("jsmc").toString())){
			str="1";
		}
	  }
      oldEntity.setXjbt(entity.getXjbt());
      oldEntity.setSlbmmc(entity.getSlbmmc());
     // oldEntity.setSlbmbh(entity.getSlbmbh());
      //oldEntity.setSlbmbh(entity.getSlbmbh());
	    if(slbmbh==null || "".equals(slbmbh)){
		if(service.getBmbh(entity.getSlbmmc()).size()!=0 && !"".equals(entity.getSlbmmc()) && entity.getSlbmmc()!=null){
		String bmbh = service.getBmbh(entity.getSlbmmc()).get(0).get("bmbh").toString();
		oldEntity.setSlbmbh(bmbh);
		}else if("".equals(entity.getSlbmmc()) || entity.getSlbmmc()==null){oldEntity.setSlbmbh(entity.getSlbmbh());}
	}else{
		oldEntity.setSlbmbh(entity.getSlbmbh());
	}
      oldEntity.setTxdz(entity.getTxdz());
      oldEntity.setLxdh(entity.getLxdh());
      oldEntity.setXjnr(entity.getXjnr());
      if("1".equals(str)){
    	  oldEntity.setPj(entity.getPj());
    	  oldEntity.setCljg(entity.getCljg());
    	  oldEntity.setMyd(entity.getMyd());
      }
      service.update(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
    /**
     * 后台:重新提交邮件
     * @author duanpeijun
     * @date 2015年9月2日
     * @param entity
     * @param id
     * @return
     */
    @RequestMapping(value="/submit")
    @ResponseBody
    public ResponseData submit(XxXjxxb entity,String id,String slbmbh)
    {
      XxXjxxb oldEntity = service.getXxjxxbById(Long.parseLong(id));
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      oldEntity.setClzt("1");
      oldEntity.setXjbt(entity.getXjbt());
      oldEntity.setSlbmmc(entity.getSlbmmc());
      //oldEntity.setSlbmbh(entity.getSlbmbh());
  	    if(slbmbh==null || "".equals(slbmbh)){
  		if(service.getBmbh(entity.getSlbmmc()).size()!=0 && !"".equals(entity.getSlbmmc()) && entity.getSlbmmc()!=null){
		String bmbh = service.getBmbh(entity.getSlbmmc()).get(0).get("bmbh").toString();
		oldEntity.setSlbmbh(bmbh);
  		}else if("".equals(entity.getSlbmmc()) || entity.getSlbmmc()==null){oldEntity.setSlbmbh(entity.getSlbmbh());}
	}else{
		oldEntity.setSlbmbh(entity.getSlbmbh());
	}
      oldEntity.setTxdz(entity.getTxdz());
      oldEntity.setLxdh(entity.getLxdh());
      oldEntity.setXjnr(entity.getXjnr());
      service.update(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
    /**
     * 后台:删除信件
     * @author duanpeijun
     * @date 2015年9月2日
     * @param ids
     * @return
     */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData delete(Long[] ids)
    {
      for (Long id : ids)
      {
        service.remove(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 后台:信件审核列表
	 * @author duanpeijun
	 * @date 2015年9月2日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getXjshList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXjshList(Integer start,
			Integer limit,String code){
		return service.getXjshList(start,limit,code);
	}
	@RequestMapping(value = "/xjshPage")
	public ModelAndView openshPage(){
		ModelAndView modelAndView = new ModelAndView("/xzxx/xxXjshList");
		return modelAndView;
	}
	/**
     * 后台:审核通过邮件
     * @author duanpeijun
     * @date 2015年9月6日
     * @param entity
     * @param id
     * @return
     */
    @RequestMapping(value="/tongguo")
    @ResponseBody
    public ResponseData tongguo(XxXjxxb entity,String id)
    {
      XxXjxxb oldEntity = service.getXxjxxbByTg(Long.parseLong(id));
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      String bmbh ="";
      SysYh yh = AppUtil.getCurrentUser();
      oldEntity.setClzt("3");
      oldEntity.setShr(yh.getUsername());
	  oldEntity.setShsj(Calendar.getInstance().getTime());
      oldEntity.setXjbt(entity.getXjbt());
      oldEntity.setSlbmmc(entity.getSlbmmc());
      oldEntity.setSlbmbh(entity.getSlbmbh());
      //oldEntity.setTxdz(entity.getTxdz());
      //oldEntity.setLxdh(entity.getLxdh());
      oldEntity.setXjnr(entity.getXjnr());
      service.update(oldEntity);
      List<Map<String, Object>> list = service.hasSlbmbh(id);
      if(list.size()!=0){
    	String slbmmc = list.get(0).get("slbmmc")+"";
    	String slbmbh = list.get(0).get("slbmbh")+"";
    	 List<Map<String, Object>> list1  = service.getSlbmbh(slbmmc);
    	 if(list1.size()!=0  && "".equals(slbmbh) && !"".equals(slbmmc)){ 
    		 bmbh= list1.get(0).get("bmbh")+""; 
    		 service.updateSlbmbh(bmbh,slbmmc,id);
    	  }
    	  }
      return ResponseData.SUCCESS_NO_DATA;
    }
    /**
     * 后台:审核退回邮件
     * @author duanpeijun
     * @date 2015年9月6日
     * @param entity
     * @param id
     * @return
     */
    @RequestMapping(value="/tuihui")
    @ResponseBody
    public ResponseData tuihui(XxXjxxb entity,String id,String thly,String slbmbh)
    {
    	if(thly.split(",").length !=0){
    	thly = thly.split(",")[1];
    	}else{
    		thly="";
    	}
      XxXjxxb oldEntity = service.getXxjxxbByTg(Long.parseLong(id));
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      SysYh yh = AppUtil.getCurrentUser();
      oldEntity.setClzt("2");
      oldEntity.setThly(thly);
      oldEntity.setShr(yh.getUsername());
	  oldEntity.setShsj(Calendar.getInstance().getTime());
      oldEntity.setXjbt(entity.getXjbt());
      oldEntity.setSlbmmc(entity.getSlbmmc());
     //oldEntity.setSlbmbh(entity.getSlbmbh());
    	if(slbmbh==null || "".equals(slbmbh)){
      		if(service.getBmbh(entity.getSlbmmc()).size()!=0 && !"".equals(entity.getSlbmmc()) && entity.getSlbmmc()!=null){
    		String bmbh = service.getBmbh(entity.getSlbmmc()).get(0).get("bmbh").toString();
    		oldEntity.setSlbmbh(bmbh);
      		}else if("".equals(entity.getSlbmmc()) || entity.getSlbmmc()==null){oldEntity.setSlbmbh(entity.getSlbmbh());}
    	}else{
    		oldEntity.setSlbmbh(entity.getSlbmbh());
    	}
    //oldEntity.setTxdz(entity.getTxdz());
   // oldEntity.setLxdh(entity.getLxdh());
      oldEntity.setXjnr(entity.getXjnr());
      service.update(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
    /**
     * 后台:审核禁用邮件
     * @author duanpeijun
     * @date 2015年9月6日
     * @param entity
     * @param id
     * @return
     */
    @RequestMapping(value="/jinyong")
    @ResponseBody
    public ResponseData jinyong(XxXjxxb entity,String id,String slbmbh)
    {
      XxXjxxb oldEntity = service.getXxjxxbByTg(Long.parseLong(id));
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      SysYh yh = AppUtil.getCurrentUser();
      oldEntity.setClzt("7");
      oldEntity.setShr(yh.getUsername());
	  oldEntity.setShsj(Calendar.getInstance().getTime());
      oldEntity.setXjbt(entity.getXjbt());
      oldEntity.setSlbmmc(entity.getSlbmmc());
      //oldEntity.setSlbmbh(entity.getSlbmbh());
  	if(slbmbh==null || "".equals(slbmbh)){
  		if(service.getBmbh(entity.getSlbmmc()).size()!=0 && !"".equals(entity.getSlbmmc()) && entity.getSlbmmc()!=null){
		String bmbh = service.getBmbh(entity.getSlbmmc()).get(0).get("bmbh").toString();
		oldEntity.setSlbmbh(bmbh);
  		}else if("".equals(entity.getSlbmmc()) || entity.getSlbmmc()==null){oldEntity.setSlbmbh(entity.getSlbmbh());}
	}else{
		oldEntity.setSlbmbh(entity.getSlbmbh());
	}
    //  oldEntity.setTxdz(entity.getTxdz());
    //  oldEntity.setLxdh(entity.getLxdh());
      oldEntity.setXjnr(entity.getXjnr());
      service.update(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
    
    /**
	 * 后台:信件已审核列表
	 * @author duanpeijun
	 * @date 2015年9月2日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getXjyshList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXjyshList(Integer start,
			Integer limit,String code){
		return service.getxjyshList(start, limit, code);
	}
	@RequestMapping(value = "/xjyshPage")
	public ModelAndView openyshPage(){
		ModelAndView modelAndView = new ModelAndView("/xzxx/xxXjyshList");
		return modelAndView;
	}
	
	/**
	 * 后台:校长信箱受理列表
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getXjslList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXjslList(Integer start,
			Integer limit,String code ) {
		return service.getxjslList(start, limit,code);
	}
	@RequestMapping(value = "/xjslPage")
	public ModelAndView openslPage() {
		ModelAndView modelAndView = new ModelAndView("/xzxx/xxXjslList");
		return modelAndView;
	}
	/**
     * 后台:受理邮件
     * @author duanpeijun
     * @date 2015年9月6日
     * @param entity
     * @param id
     * @return
     */
    @RequestMapping(value="/shouli")
    @ResponseBody
    public ResponseData shouli(XxXjxxb entity,String id)
    {
      XxXjxxb oldEntity = service.getXxjxxbById(Long.parseLong(id));
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      SysYh yh = AppUtil.getCurrentUser();
      oldEntity.setClr(yh.getXm());
      oldEntity.setClzt("5");
      oldEntity.setClsj(Calendar.getInstance().getTime());
      oldEntity.setXjbt(entity.getXjbt());
      oldEntity.setSlbmmc(entity.getSlbmmc());
      oldEntity.setSlbmbh(entity.getSlbmbh());
     // oldEntity.setTxdz(entity.getTxdz());
      //oldEntity.setLxdh(entity.getLxdh());
      oldEntity.setXjnr(entity.getXjnr());
      oldEntity.setCljg(entity.getCljg());
      service.update(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
    
    /**
	 * 后台:校长信箱已受理列表
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getXjyslList")
	@ResponseBody
	public Pagination<Map<String, Object>> getXjyslList(Integer start,
			Integer limit,String code ) {
		return service.getxjyslList(start, limit,code);
	}
	@RequestMapping(value = "/xjyslPage")
	public ModelAndView openyslPage() {
		ModelAndView modelAndView = new ModelAndView("/xzxx/xxXjyslList");
		return modelAndView;
	}
	/**
	 * 根据字典种类找出借出状态
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getClztByLx", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getClztByLx(String zdzl) {
		return service.getDicByLx("clzt");
	}
    
	/**
	 * 校长信箱列表
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toXzxxList")
   	public ModelAndView toXzxxList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxList");
   		String Currentpage = request.getParameter("Currentpage");
   		String code = request.getParameter("code");
   		if(code!=null){
			code = new String(code.getBytes("ISO-8859-1"), "UTF-8"); 
   		}else{
   			code="";
   		}
		if(Currentpage == null){
			Currentpage = "1";
		}
		String lx=request.getParameter("lx");
		if(lx==null||"".equals(lx)){
   			lx="";
   		}
		String openId = request.getParameter("openId");
		if(openId == null || "".equalsIgnoreCase(openId)){
			mav = AppUtil.runWx(openId);
		}else{
			List<Map<String,Object>> wxyh = service.getUser(openId);
			if(wxyh.size()!=0){
				mav.addObject("yhbh",wxyh.get(0).get("dlm"));
				int myacount = service.getxjAcount(wxyh.get(0).get("yhid")+"",lx);
				mav.addObject("myacount",myacount);
			}
			List<Map<String,Object>> list=service.getXzxxList(request);
			int acount  = service.getAcount();
			int pagecount = service.getPagecount(request);
			
	   		if(list!=null){
	   			mav.addObject("list",list);
	   			mav.addObject("Currentpage",Currentpage);
	   			mav.addObject("lx",lx);
	   			mav.addObject("size",list.size());
	   		}
	   		mav.addObject("openId",openId);
	   		mav.addObject("acount",acount);
	   		mav.addObject("pagecount",pagecount);
	   		mav.addObject("tj",code);
	   		
		}
   		return mav;
   	}
	
	/**
	 * 
	 * 转到校长信箱添加页面 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toXzxxAdd")
   	public ModelAndView toXzxxAdd(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxAdd");
   		String openId = request.getParameter("openId");
   		List<Map<String,Object>> list=service.getZzjg();
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		mav.addObject("openId",openId);
   		return mav;
   	}
	
	/**
	 * h5页面校长信箱新增
	 * @author zhangyan
	 * @date 2016年12月14日 上午10:59:49
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toXzxxAddNew")
   	public ModelAndView toXzxxAddNew(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxAddNew");
   		String openId = request.getParameter("openId");
   		List<Map<String,Object>> list=service.getZzjg();
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		mav.addObject("openId",openId);
   		return mav;
   	}
	
	/**
	 * 新建信件
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/saveXj")
    @ResponseBody
    public String saveXj(HttpServletRequest request,HttpServletResponse response) 
    		throws SerialException, SQLException, UnsupportedEncodingException{
		String str="";
		str=service.saveXj(request);
		return str;
    }
	
	/**
	 * 
	 * 转到校长信箱修改页面 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toXzxxUpdate")
   	public ModelAndView toXzxxUpdate(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxUpdate");
   		String openId = request.getParameter("openId");
   		String id = request.getParameter("id");
   		List<Map<String,Object>> list=service.getZzjg();
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		XxXjxxb xx=service.get(Long.parseLong(id));
   		if(xx!=null){
   			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
   			String xxsj=sim.format(xx.getXxsj());
   			mav.addObject("xxsj",xxsj);
   			mav.addObject("map",xx);
   		}
   		mav.addObject("id",id);
   		mav.addObject("openId",openId);
   		return mav;
   	}
	
	/**
	 * 校长信箱修改（新）
	 * @author zhangyan
	 * @date 2016年12月16日 上午9:05:09
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toXzxxUpdateNew")
   	public ModelAndView toXzxxUpdateNew(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxUpdateNew");
   		String openId = request.getParameter("openId");
   		String id = request.getParameter("id");
   		List<Map<String,Object>> list=service.getZzjg();
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		XxXjxxb xx=service.get(Long.parseLong(id));
   		if(xx!=null){
   			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
   			String xxsj=sim.format(xx.getXxsj());
   			mav.addObject("xxsj",xxsj);
   			mav.addObject("map",xx);
   		}
   		mav.addObject("id",id);
   		mav.addObject("openId",openId);
   		return mav;
   	}
	
	
	/**
	 * 修改信件
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/updateXj")
    @ResponseBody
    public String updateXj(HttpServletRequest request,HttpServletResponse response)
    		throws SerialException, SQLException, UnsupportedEncodingException{
		String str="";
		str=service.updateXj(request);
		return str;
    }
	
/**
	 * 
	 * 转到校长信箱详情页面 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value="/toXzxxDetail")
   	public ModelAndView toXzxxDetail(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxDetail");
   		String openId = request.getParameter("openId");
   		String id = request.getParameter("id");
   		XxXjxxb xx=service.get(Long.parseLong(id));
   		if(xx!=null){
   			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
   			String xxsj=sim.format(xx.getXxsj());
   			if(xx.getClsj()!=null&&!"".equals(xx.getClsj())){
   				String clsj=sim.format(xx.getClsj());
   				mav.addObject("clsj",clsj);
   			}
   			if(xx.getShsj()!=null&&!"".equals(xx.getShsj())){
   				String shsj=sim.format(xx.getShsj());
   				mav.addObject("shsj",shsj);
   			}
   			mav.addObject("xxsj",xxsj);
   			mav.addObject("map",xx);
   		}
   		mav.addObject("id",id);
   		mav.addObject("openId",openId);
   		return mav;
   	}*/
	
	/**
	 * 
	 * 转到校长信箱详情页面 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toXzxxDetail")
   	public ModelAndView toXzxxDetail(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxDetail");
   		String openId = request.getParameter("openId");
   		String id = request.getParameter("id");
   		String lx = request.getParameter("lx");
   		String Currentpage = request.getParameter("Currentpage");
   		List<Map<String, Object>> xx=service.getXzxxDetail(request);
   		mav.addObject("map",xx);
   		mav.addObject("id",id);
   		mav.addObject("openId",openId);
   		mav.addObject("lx",lx);
   		mav.addObject("Currentpage",Currentpage);
   		return mav;
   	}
	
	/**
	 * 删除信件
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deleteXj")
    @ResponseBody
    public String deleteXj(HttpServletRequest request,HttpServletResponse response){
		String str="";
		str=service.deleteXj(request);
		return str;
    }
	
	/**
	 * 
	 * 转到校长信箱反馈页面 
	 * @author liujiansen
	 * @date 2015年9月2日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toXzxxFk")
   	public ModelAndView toXzxxFk(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xzXxfk");
   		String openId = request.getParameter("openId");
   		String id = request.getParameter("id");
   		String lx =request.getParameter("lx");
   		XxXjxxb xx=service.get(Long.parseLong(id));
   		if(xx!=null){
   			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
   			String xxsj=sim.format(xx.getXxsj());
   			mav.addObject("xxsj",xxsj);
   			mav.addObject("map",xx);
   		}
   		mav.addObject("id",id);
   		mav.addObject("openId",openId);
   		mav.addObject("lx",lx);
   		return mav;
   	}
	
	/**
	 * 信件评价信息保存
	 * @author liujiansen
	 * @date 2015年9月2日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/savePj")
    @ResponseBody
    public String savePj(HttpServletRequest request,HttpServletResponse response) 
    		throws UnsupportedEncodingException{
		String str="";
		str=service.savePj(request);
		return str;
    }
	
	/**
	 * 后台：信件评价信息保存
	 * @author liujiansen
	 * @date 2015年9月2日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/savePjxx")
    @ResponseBody
    public ResponseData savePjxx(ModelMap model, XxXjxxb entity,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
    	String result = service.savePj(request);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 信件查看列表
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/getXjckList")
   	public ModelAndView getXjckList(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/xzxx/xxXjckList");
//   		String pages = request.getParameter("pages");
   		String yhbh=request.getParameter("yhbh");
   		String xm ="";
   		if(yhbh==null || "".equals(yhbh)){
   			SysYh user=AppUtil.getCurrentUser();
   			yhbh=user.getYhbh();
   			 xm=user.getXm();
   		}
   		String bm=request.getParameter("bm");
   		String bmmc=request.getParameter("bmmc");
   		String clzt = request.getParameter("clzt");
   		String bmbh = request.getParameter("bmbh");
   		if(bm!=null){
	   		bm = new String(bm.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bm="";
	   		}
   		if(bmmc!=null){
   			bmmc = new String(bmmc.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bmmc="";
	   		}
   		if(clzt!=null){
			clzt = new String(clzt.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			clzt="";
	   		}
//		if (pages == null) {
//			pages = "1";
//		}
		/*int count = service.getCount(request);*/
   		List<Map<String,Object>> list=service.getXjckList(bm, bmbh, clzt, bmmc,yhbh,xm);
   		List<Map<String, Object>> bmlist = service.getZzjg();
   		List<Map<String, Object>> ztlist = service.getClztList();
   			mav.addObject("list", list);
//   			mav.addObject("pages", pages);
   			/*mav.addObject("count", count);*/
   			mav.addObject("bmlist",bmlist);
   			mav.addObject("ztlist",ztlist);
   			mav.addObject("yhbh",yhbh);
   			mav.addObject("bmmc",bmmc);
   			mav.addObject("bm",bm);
   			mav.addObject("clzt",clzt);
   			mav.addObject("xm",xm);
   		return mav;
   	}
	
	/**
	 * 信件查看详情
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/getXjckDetail")
   	public ModelAndView getXjckDetail(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/xzxx/xxXjckDetail");
   		String bxbh=request.getParameter("bxbh");
   		String xm=request.getParameter("xm");
   		if(xm!=null){
   			xm= new String(xm.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			xm="";
	   		}
   		List<Map<String,Object>> list=service.getXjckDetail(bxbh,xm);
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		mav.addObject("xm", xm);
   		return mav;
   	}
	
	/**
	 * 根据字典种类找出满意度
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicByMyd", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByMyd(String zdzl) {
		return service.getDicByMyd("myd");
	}
	
	@RequestMapping(value = "/getClztsize")
	@ResponseBody
	public Integer getClztsize(HttpServletRequest request) {
	  int	  str = service.getClztsize();
		return str;
	}
	
	/**
	 * 新建信箱时查看是否有评价
	 * @author:oufeng
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getPj")
	@ResponseBody
	public String getPj(HttpServletRequest request) {
		String str;
		str = service.getPj(request);
		return str;
	}
	
	////////
	/**
	 * 我要写信
	 * @author:oufeng
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/wyxx")
   	public ModelAndView toWyxx(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xxXjwyxx");
   		List<Map<String,Object>> list=service.getZzjg();
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
   	}
	
	/**
	 * 信件查看列表
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/getXjmhckList")
   	public ModelAndView getXjmhckList(HttpServletRequest request, HttpServletResponse response) 
   			throws UnsupportedEncodingException{
   		ModelAndView mav=new ModelAndView("/xzxx/xxXjmhckList");
 		String pagesw = request.getParameter("pagesw");
 		String pagesy= request.getParameter("pagesy");
   		String time=request.getParameter("time");
   		String nr = request.getParameter("nr");
   		String bmbh = request.getParameter("bmbh");
   		if(bmbh!=null){
   			bmbh= new String(bmbh.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bmbh="";
	   		}
   		if(nr!=null){
			nr= new String(nr.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			nr="";
	   		}
	if (pagesw == null) {
		pagesw = "1";
}
	if (pagesy == null) {
		pagesy = "1";
}
		int countw = service.getCountw(request);
	    int county = service.getCounty(request);
   		List<Map<String,Object>> list=service.getXjmhckListQt( time,bmbh, nr, pagesw);
   	    List<Map<String, Object>> map = service.getYhfList(time, bmbh, nr,pagesy);
   	    List<Map<String, Object>> jlzs =service.getZs();
   	    List<Map<String, Object>> wjlzs =service.getWzs();
   	    String zs =  jlzs.get(0).get("jlzs").toString();
   	  String wzs =  wjlzs.get(0).get("wjlzs").toString();
   	    List<Map<String, Object>> bmlist = service.getZzjg();
   		List<Map<String, Object>> ztlist = service.getClztList();
   			mav.addObject("list", list);
   	        mav.addObject("map", map);
            mav.addObject("pagesy", pagesy);
            mav.addObject("pagesw", pagesw);
   		    mav.addObject("countw", countw);
   	 	    mav.addObject("county", county);
   		    mav.addObject("bmlist",bmlist);
   	        mav.addObject("ztlist",ztlist);
   	        mav.addObject("zs",zs);
   	        mav.addObject("wzs",wzs);
   	        mav.addObject("bmbh",bmbh);
   	        mav.addObject("time",time);
   	        mav.addObject("nr",nr);
   		 return mav;
   	}
	
	/**
	 * 信件查看详情
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getXjmhckDetail")
   	public ModelAndView getXjmhckDetail(HttpServletRequest request, HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/xzxx/xxXjmhckDetail");
   		String bxbh=request.getParameter("bxbh");
   		List<Map<String,Object>> list=service.getXjmhckDetailWeb(bxbh);
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
   	}
	
	/**
	 * 我要写件
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/saveWyxx")
    @ResponseBody
    public String saveWyxx(HttpServletRequest request,HttpServletResponse response) 
    		throws SerialException, SQLException, UnsupportedEncodingException{
		String str="";
		str=service.saveWyxx(request);
		return str;
    }
	
	/**
	 * 我要写信中是否有评价
	 * @author:oufeng
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/sfPj")
	@ResponseBody
	public String sfPj(HttpServletRequest request) {
		String str;
		str = service.getsfPj(request);
		return str;
	}
}
