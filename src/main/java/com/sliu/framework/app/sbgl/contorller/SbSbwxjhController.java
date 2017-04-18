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
import com.sliu.framework.app.sbgl.model.SbSbwxjh;
import com.sliu.framework.app.sbgl.service.SbSbwxjhService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 设备维修计划
 * @author liujiansen
 * @date 2015年8月24日
 */
@Controller
@RequestMapping("/sbgl/SbSbwxjh")
public class SbSbwxjhController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SbSbwxjhController.class);
	
	@Autowired
	private SbSbwxjhService service;
	
	/**
	 * 后台：查询设备维修计划列表
	 * @author liujiansen
	 * @date 2015年8月24日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSbwxjhList")
	@ResponseBody
	public Pagination<Map<String, Object>> getSbwxjhList(Integer start,Integer limit,String wxjh,String wxjb,String sblb){
		
		return service.getSbwxjhList(start, limit,wxjh,wxjb,sblb);
		
	}
	
	/**
	 * 后台：查询设备检验计划列表
	 * @author   liujiansen
	 * @version 创建时间：2015年6月3日  下午4:07:44
	 * @return
	 */
	@RequestMapping(value = "/SbwxjhPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbwx/sbSbwxjhList");
		return modelAndView;
	}
	
	/**
	 * 后台：添加设备维修计划
	 * @author liujiansen
	 * @date 2015年8月24日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, SbSbwxjh entity,
			HttpServletRequest request, HttpServletResponse response) {

		String result = service.save(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台：修改设备检验计划
	 * @author   liujiansen
	 * @version 创建时间：2015年6月3日  下午4:06:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(SbSbwxjh entity, String id) {
		service.update(entity, id);
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
	public ResponseData updateWc(String id,String zt,String gzms) {
		service.updateWc(id,zt,gzms);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 后台：删除设备检验计划
	 * @author:liujiansen 
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
	 * 根据字典种类找出计划状态
	 * @author:liujiansen
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getZt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getZt(String zdzl) {
		return service.getDicByLx("jhzt");
	}
	
	/**
	 * 根据字典种类找出计划状态
	 * @author:liujiansen
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getYxzt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getYxzt(String zdzl) {
		return service.getDicByYxlx("jhzt");
	}
	
	/**
	 * 根据字典种类找出循环方式
	 * @author:liujiansen
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getXhfs", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getXhfs(String zdzl) {
		return service.getDicByLx("xhfs");
	}
	
	/**
	 * 根据字典种类找出维修级别
	 * @author:liujiansen
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getWxjb", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getWxjb(String zdzl) {
		return service.getDicByLx("wxjb");
	}
}
