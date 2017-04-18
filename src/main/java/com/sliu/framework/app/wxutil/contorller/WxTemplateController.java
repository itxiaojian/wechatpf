package com.sliu.framework.app.wxutil.contorller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxutil.model.TemplateData;
import com.sliu.framework.app.wxutil.model.WxTemplate;
import com.sliu.framework.app.wxutil.model.WxTemplateNews;
import com.sliu.framework.app.wxutil.service.WxTemplateService;


/**
 * 模版消息主体
 * @author : zhangyi
 * @version 创建时间：2016年4月19日 上午9:31:10
 */
@Controller
@RequestMapping("/weixin/template")
public class WxTemplateController  extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WxTemplate.class);
	
	@Autowired
	private WxTemplateService wxTemplateService;
	
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:27:44
	 * @return
	 */
	@RequestMapping(value = "/templatenewsPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/weixin/template/wxTemplateNewsList");
		return modelAndView;
	}
	
	
	/**
	 * 获取消息主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:26:01
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getTemplateNewsList")
	@ResponseBody
	public Pagination<Map<String, Object>> getTemplateNewsList(Integer start,Integer limit){
		
		return wxTemplateService.getTemplateNewsList(start, limit);
		
	}
	
	/**
	 * 获取消息内容
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:26:01
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getTemplateList")
	@ResponseBody
	public Pagination<Map<String, Object>> getTemplateList(Integer start,Integer limit,Long templateid){
		
		return wxTemplateService.getTemplateList(start, limit,templateid);
		
	}
	
	/**
	 * 增加消息主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:27
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveNews")
	@ResponseBody
	public ResponseData saveNews(ModelMap model,WxTemplateNews entity,
			HttpServletRequest request,HttpServletResponse response){
		wxTemplateService.saveNews(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改消息主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:29:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateNews", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(WxTemplateNews entity, String id) {
		wxTemplateService.updateNews(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}  
	
	/**
	 * 删除主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:43
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteNews", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData deleteNews(Long[] ids){
		wxTemplateService.deleteNews(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	
	/**
	 * 增加消息内容
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:27
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,TemplateData entity,
			HttpServletRequest request,HttpServletResponse response){
		wxTemplateService.save(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 编辑
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:29:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(TemplateData entity, String id) {
		wxTemplateService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:43
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
		wxTemplateService.delete(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
}
