package com.sliu.framework.app.wzy.controller;

//import java.beans.PropertyDescriptor;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
//import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.logger.Logger;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyCdpz;
import com.sliu.framework.app.wzy.service.ZyCdpzService;
//import com.sliu.framework.app.sbgl.model.SbSbxx;
//import com.sliu.framework.app.sys.model.SysYh;
//import com.sliu.framework.app.util.AppUtil;
/**
 * 主页菜单平台
 * @author zhangyi
 * @version 创建时间：2016年1月12日  
 */
@Controller
@RequestMapping("/wzy/ZyCdpt")
public class ZyCdptController extends BaseController{
	protected final transient Logger logger=LoggerFactory.getPresentationLog(ZyCdptController.class);

		@Autowired
		private ZyCdpzService zyCdptService;
		
		/**
		 *点击进入的页面
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日   
		 * @return
		 */
		@RequestMapping(value = "/cdptPage")
		public ModelAndView openPage() {
			ModelAndView modelAndView = new ModelAndView("/wzy/zyCdptList");
			return modelAndView;
		}
		
		/**
		 *点击进入的页面(根据参数保存不同的类型)
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日   
		 * @return
		 */
		@RequestMapping(value = "/cdptPageAll")
		public ModelAndView zyCdptListAll() {
			ModelAndView modelAndView = new ModelAndView("/wzy/zyCdptListAll");
			return modelAndView;
		}
		
		/**
		 *点击进入的页面(根据参数保存不同的类型)
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日   
		 * @return
		 */
		@RequestMapping(value = "/zyCdptViewAll")
		public ModelAndView zyCdptViewAll(String mklx) {
			ModelAndView modelAndView = new ModelAndView("/wzy/zyCdptViewAll");
			modelAndView.addObject("mklx", mklx);
			return modelAndView;
		}
		
		/**
		 *点击进入角色菜单配置
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日   
		 * @return
		 */
		@RequestMapping(value = "/cdptJsCdPage")
		public ModelAndView openJsCdPage() {
			ModelAndView modelAndView = new ModelAndView("/wzy/zyJsCdpt");
			return modelAndView;
		}
		
		/**
		 * 分页查询角色列表
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日  
		 * @param start
		 * @param limit
		 * @return
		 */
		@RequestMapping(value = "/getJsList", method = RequestMethod.POST)
		@ResponseBody
		public Pagination<Map<String, Object>> getJsList(Integer start,Integer limit) {
			return zyCdptService.getJsList(start, limit);
		}
		
		/**
		 * 添加
		 * @author   zhangyi
		 * @version 创建时间：2016年1月12日  
		 * @param model
		 * @param entity
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		@ResponseBody
		public ResponseData save(ModelMap model, ZyCdpz entity,
				HttpServletRequest request, HttpServletResponse response) {
			String result = zyCdptService.saveCdpz(entity);
			if (!"1".equalsIgnoreCase(result)) {
				return ResponseData.FAILED_NO_DATA;
			}
			return ResponseData.SUCCESS_NO_DATA;
		}
		
		/**
		 * 角色配置功能
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日   
		 * @param ids
		 * @return
		 */
		@RequestMapping(value="/upCd")
	    @ResponseBody
	    public ResponseData upCd(String jsmc,Integer cdid){
		  zyCdptService.upCd(jsmc,cdid);
	      return ResponseData.SUCCESS_NO_DATA;
	    }
		
		/**
		 * 角色配置功能
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日   
		 * @param ids
		 * @return
		 */
		@RequestMapping(value="/downCd")
	    @ResponseBody
	    public ResponseData downCd(Integer pzid){
		  zyCdptService.downCd(pzid);
	      return ResponseData.SUCCESS_NO_DATA;
	    }
		
		/**
		 * 删除功能
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日   
		 * @param ids
		 * @return
		 */
		@RequestMapping(value="/delete")
	    @ResponseBody
	    public ResponseData deleteTuWenMcInfo(Long[] ids)
	    {
	      for (Long id : ids) {
	    	  zyCdptService.delete(id);
	      }
	      return ResponseData.SUCCESS_NO_DATA;
	    }
		
		/**
		 * 修改
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日  
		 * @return
		 */
		@RequestMapping(value = "/update")
		@ResponseBody
		public ResponseData update(ModelMap model, ZyCdpz entity,String id,
				HttpServletRequest request, HttpServletResponse response) {
			ZyCdpz oldEntity = zyCdptService.getCdpzById(Long.parseLong(id));
		      if (oldEntity == null) {
		          return new ResponseData(false, "记录不存在");
		      }
		      oldEntity.setCdmc(entity.getCdmc());
		      oldEntity.setCdtbmc(entity.getCdtbmc());
		      oldEntity.setCdurl(entity.getCdurl());
//		      oldEntity.setTjr(entity.getTjr());
		      oldEntity.setSfqy(entity.getSfqy());
		      //oldEntity.setTjsj(entity.getTjsj());
		      oldEntity.setPx(entity.getPx());
		      zyCdptService.update(oldEntity);
			return ResponseData.SUCCESS_NO_DATA;
		}
		
		/**
		 * 分页查询
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日  
		 * @param start
		 * @param limit
		 * @return
		 */
		@RequestMapping(value = "/getList", method = RequestMethod.POST)
		@ResponseBody
		public Pagination<Map<String, Object>> getList(Integer start,Integer limit) {
			return zyCdptService.getList(start, limit);
		}
		
		/**
		 * 分类查询
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日  
		 * @param start
		 * @param limit
		 * @return
		 */
		@RequestMapping(value = "/getAllList", method = RequestMethod.POST)
		@ResponseBody
		public Pagination<Map<String, Object>> getAllList(String mklx,Integer start,Integer limit) {
			return zyCdptService.getAllList(mklx, start, limit);
		}
		
		/**
		 * 分类查询角色菜单配置
		 * @author:zhangyi 
		 * @version 创建时间：2016年1月12日  
		 * @param start
		 * @param limit
		 * @return
		 */
		@RequestMapping(value = "/getAllJsList", method = RequestMethod.POST)
		@ResponseBody
		public Pagination<Map<String, Object>> getAllJsList(String mklx,String jsmc,Integer start,Integer limit) {
			return zyCdptService.getAllJsList(mklx,jsmc, start, limit);
		}
		
		/**
		 * 根据字典种类找出字典
		   @author:zhangyi 
		 * @version 创建时间：2016年1月12日  
		 * @param zdzl
		 * @return
		 */
		@RequestMapping(value = "/getDicByLx", method = RequestMethod.POST)
		@ResponseBody
		public List<Map<String, Object>> getDicByLx(String zdzl) {
			return zyCdptService.getDicByLx("sfqy");
		}

}
