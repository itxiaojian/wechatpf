//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.company.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.company.service.CompanyService;

/**
 * 公司微信平台
 * @author : zhangyi
 * @version 创建时间：2015年7月30日 上午11:59:50
 */
@Controller
@RequestMapping("/com/company")
public class CompanyController extends BaseController {

	protected final transient Logger logger = LoggerFactory.getPresentationLog(CompanyController.class);

	@Autowired
	private CompanyService companyService;
	
	/**
	 * 公司简介详情
	 * @author:zhangyi 
	 * @version 创建时间：2015年7月30日 下午1:45:34 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/gsjjDetail")
	@ResponseBody
	public ModelAndView getGsjj(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/gsjjDetail";
		
		List<Map<String,Object>> gsjj = companyService.gsjjDetail();
		
		if(gsjj.size()>0){
			Map<String,Object> map= gsjj.get(0);
			modelAndView.addObject("map", map);
			
		}
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 公司资质详情
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/gszzDetail")
	@ResponseBody
	public ModelAndView getGszz(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/gszzDetail";
		
		List<Map<String,Object>> gszz = companyService.gszzDetail();
		
		if(gszz.size()>0){
			Map<String,Object> map= gszz.get(0);
			modelAndView.addObject("map", map);
			
		}
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 公司荣誉详情
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/gsryDetail")
	@ResponseBody
	public ModelAndView getGsry(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/gsryDetail";
		
		List<Map<String,Object>> gsry = companyService.gsryDetail();
		
		if(gsry.size()>0){
			Map<String,Object> map= gsry.get(0);
			modelAndView.addObject("map", map);
			
		}
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 服务体系详情
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/fwtxDetail")
	@ResponseBody
	public ModelAndView getFwtx(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/fwtxDetail";
		
		List<Map<String,Object>> fwtx = companyService.fwtxDetail();
		
		if(fwtx.size()>0){
			Map<String,Object> map= fwtx.get(0);
			modelAndView.addObject("map", map);
			
		}
		modelAndView.setViewName(url);
		return modelAndView;
	}

	/**
	 * 公司资讯列表
	 * @author duanpeijun
	 * @date 2015年8月03日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/gszxList")
	@ResponseBody
	public ModelAndView getGszx(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/gszxList";
		
		List<Map<String,Object>> list  = companyService.gszxList();
		
		if(list.size()>0){
			modelAndView.addObject("map", list);
			
		}
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 公司资讯详情
	 * @author duanpeijun
	 * @date 2015年8月03日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/gszxDetail")
	@ResponseBody
	public ModelAndView getGszxDetail(String openId,Long id) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/gszxDetail";
		
		List<Map<String,Object>> list = companyService.gszxDetail(id);
		if(list.size()>0){
			Map<String,Object> map= list.get(0);
			modelAndView.addObject("map", map);
		}
		
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 解決方案列表
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/jjfaList")
	@ResponseBody
	public ModelAndView getJjfa(String openId) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/jjfaList";
		
		List<Map<String,Object>> list = companyService.jjfaList();
		if(list.size()>0){
			modelAndView.addObject("map", list);
		}
		
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 解決方案详情
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/jjfaDetail")
	@ResponseBody
	public ModelAndView getJjfaDetail(String openId,Long id) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/jjfaDetail";
		
		List<Map<String,Object>> list = companyService.jjfaDetail(id);
		if(list.size()>0){
			Map<String,Object> map= list.get(0);
			modelAndView.addObject("map", map);
		}
		
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 成功案例列表（教育，政府，金融，企业，医疗）
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/cgalList")
	@ResponseBody
	public ModelAndView getCgal(Long ClassID) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/cgalList";
		
		List<Map<String,Object>> list = companyService.cgalList(ClassID);
		if(list.size()>0){
			modelAndView.addObject("map", list);
		}
		
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 成功案例详情（教育，政府，金融，企业，医疗）
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/cgalDetail")
	@ResponseBody
	public ModelAndView getCgalDetail(Long id,Long ClassID) {
		
		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/company/cgalDetail";
		
		List<Map<String,Object>> list = companyService.cgalDetail(id,ClassID);
		if(list.size()>0){
			Map<String,Object> map= list.get(0);
			modelAndView.addObject("map", map);
		}
		
		modelAndView.setViewName(url);
		return modelAndView;
	}

}
