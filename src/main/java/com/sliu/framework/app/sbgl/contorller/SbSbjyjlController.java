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
import com.sliu.framework.app.sbgl.model.SbSbjyjl;
import com.sliu.framework.app.sbgl.service.SbSbjyjhService;
import com.sliu.framework.app.sbgl.service.SbSbjyjlService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 设备检验记录
 * @author duanpeijun
 * @date 2015年8月21日
 */
@Controller
@RequestMapping("/sbgl/SbSbjyjl")
public class SbSbjyjlController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SbSbjyjlController.class);
	
	@Autowired
	private SbSbjyjlService sbSbjyjlService;
	@Autowired
	private SbSbjyjhService sbSbjyjhService;
	
	/**
	 * 后台：查询设备检验记录列表
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSbjyjlList")
	@ResponseBody
	public Pagination<Map<String, Object>> getSbjyjlList(Integer start,Integer limit,String jl,String jyzt){
		
		return sbSbjyjlService.getSbjyjlList(start, limit,jl,jyzt);
		
	}
	
	/**
	 * 后台：查询设备检验记录列表
	 * @author   duanpeijun
	 * @version 2015年8月21日
	 * @return
	 */
	@RequestMapping(value = "/SbjyjlPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbjy/sbSbjyjlList");
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
	@RequestMapping(value = "/saveSbjyjl", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, SbSbjyjl entity,
			HttpServletRequest request, HttpServletResponse response) {

		String result = sbSbjyjlService.saveSbjyjl(entity);
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
	public ResponseData update(SbSbjyjl entity, String id) {
		sbSbjyjlService.update(entity, id);
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
    	  sbSbjyjlService.delete(id);
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
		return sbSbjyjhService.getDicByLx("jyzt");
	}
	
	/**
	 * 根据字典种类找出检验方式
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getYxzt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getYxzt(String zdzl) {
		return sbSbjyjhService.getDicByLx("jyzt");
	}
}
