package com.sliu.framework.app.sbgl.contorller;

import java.util.List;
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
import com.sliu.framework.app.sbgl.model.SbSbwxjl;
import com.sliu.framework.app.sbgl.service.SbSbwxjhService;
import com.sliu.framework.app.sbgl.service.SbSbwxjlService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 设备检验记录
 * @author duanpeijun
 * @date 2015年8月21日
 */
@Controller
@RequestMapping("/sbgl/SbSbwxjl")
public class SbSbwxjlController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SbSbwxjlController.class);
	
	@Autowired
	private SbSbwxjlService service;
	@Autowired
	private SbSbwxjhService jhService;
	
	/**
	 * 后台：查询设备检验记录列表
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSbwxjlList")
	@ResponseBody
	public Pagination<Map<String, Object>> getSbwxjlList(Integer start,Integer limit,String code,String wxjb,String sblb){
		
		return service.getSbwxjlList(start, limit,code,wxjb,sblb);
		
	}
	
	/**
	 * 后台：查询设备检验记录列表
	 * @author   duanpeijun
	 * @version 2015年8月21日
	 * @return
	 */
	@RequestMapping(value = "/SbwxjlPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbwx/sbSbwxjlList");
		return modelAndView;
	}
	
	/**
	 * 后台：添加设备检验记录
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, SbSbwxjl entity,String wxdw,String wxfzr,String wxsqr,
			HttpServletRequest request, HttpServletResponse response) {

		String result = service.saveSbjyjl(entity,wxdw,wxfzr,wxsqr);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台：修改设备检验记录
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(SbSbwxjl entity, String id,String wxdh,String wxdw,String wxfzr,String wxsqr) {
		service.update(entity, id,wxdh,wxdw,wxfzr,wxsqr);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台：完成设备检验计划
	 * @author   liujiansen
	 * @version 创建时间：2015年6月3日  下午4:06:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateWc", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData updateWc(String id,String wxzt,String gzfxjcl,String kssj,String wcsj,String wxfy) {
		service.updateWc(id,wxzt,gzfxjcl,kssj,wcsj,wxfy);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台：删除设备检验记录
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月10日 上午11:22:07 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids)
    {
      for (Long id : ids) {
    	  service.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 根据字典种类找出检验方式
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getJyztByLx", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getJyfsByLx(String zdzl) {
		return jhService.getDicByLx("jyzt");
	}
	
	/**
	 * 根据字典种类找出维修类别
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getWxjb", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getWxjb(String zdzl) {
		return jhService.getDicByLx("wxjb");
	}
	
	/**
	 * 根据字典种类找出紧急度
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getJjd", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getJjd(String zdzl) {
		return jhService.getDicByLx("jjd");
	}
	
	/**
	 * 根据字典种类找出故障类别
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getGzlb", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getGzlb(String zdzl) {
		return jhService.getDicByLx("gzlb");
	}
}
