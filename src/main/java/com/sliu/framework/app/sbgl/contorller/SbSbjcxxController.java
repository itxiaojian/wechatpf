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
import com.sliu.framework.app.sbgl.model.SbSbjcxx;
import com.sliu.framework.app.sbgl.service.SbSbjcxxService;
import com.sliu.framework.app.sbgl.service.SbSbjyjhService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 设备借出信息
 * @author duanpeijun
 * @date 2015年8月24日
 */
@Controller
@RequestMapping("/sbgl/SbSbjcxx")
public class SbSbjcxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SbSbjcxxController.class);
	
	@Autowired
	private SbSbjcxxService sbSbjcxxService;
	@Autowired
	private SbSbjyjhService sbSbjyjhService;
	
	/**
	 * 后台：查询设备借出信息列表
	 * @author duanpeijun
	 * @date 2015年8月24日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSbjcxxList")
	@ResponseBody
	public Pagination<Map<String, Object>> getSbjyjhList(Integer start,Integer limit,String jczt,String jc){
		
		return sbSbjcxxService.getSbjyjhList(start, limit,jczt,jc);
		
	}
	
	/**
	 * 后台：查询设备借出信息列表
	 * @author   duanpeijun
	 * @date 2015年8月24日
	 * @return
	 */
	@RequestMapping(value = "/SbjcxxPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbjcxx/sbSbjcxxList");
		return modelAndView;
	}
	
	/**
	 * 后台：添加设备借出信息
	 * @author duanpeijun
	 * @date 2015年8月24日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveSbjcxx", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, SbSbjcxx entity,
			HttpServletRequest request, HttpServletResponse response) {

		String result = sbSbjcxxService.saveSbjcxx(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台：修改设备借出信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午4:06:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(SbSbjcxx entity, String id) {
		sbSbjcxxService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台：删除设备借出信息
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
    	  sbSbjcxxService.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 根据字典种类找出借出状态
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getJcztByLx", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getJcztByLx(String zdzl) {
		return sbSbjyjhService.getDicByLx("jczt");
	}
}
