package com.sliu.framework.app.sbgl.contorller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
import com.sliu.framework.app.sbgl.model.ResultVO;
import com.sliu.framework.app.sbgl.model.SbSbsgxx;
import com.sliu.framework.app.sbgl.service.SbSbsgService;
import com.sliu.framework.app.sbgl.service.SbxxService;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;

/**
 * 设备申购
 * @author oufeng
 * @date 2015年8月20日
 */
@Controller
@RequestMapping("/sbgl/SbSbsg")
public class SbSbsgController extends BaseController{

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SbSbsgController.class);
	
	@Autowired
	private SbSbsgService sbSbsgService;
	
	/**
	 * 后台：查询设备申购
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSbsgList")
	@ResponseBody
	public Pagination<Map<String, Object>> getSbSgList(Integer start,Integer limit,String code,String sblb,String sqzt){
		return sbSbsgService.getSbsgList(start, limit, code,sblb,sqzt);
	}
	
	/**
	 * 后台：查询设备申购
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getSbsgCheck")
	@ResponseBody
	public ResultVO getSbSgCheck(HttpServletRequest request, HttpServletResponse response){
		String id=request.getParameter("id");
		ResultVO vo = new ResultVO();
		vo.setRows(sbSbsgService.getSbsgCheck(id));
		vo.setTotal(0);
		return vo;
	}
	
	/**
	 * 后台：查询设备申购
	 * @author   oufeng
	 * @version 创建时间：2015-08-20
	 * @return
	 */
	@RequestMapping(value = "/SbsgPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgList");
		return modelAndView;
	}
	
	/**
	 * 后台：设备的部门的审批
	 * @author   oufeng
	 * @version 创建时间：2015-08-20
	 * @return
	 */
	@RequestMapping(value = "/SbsgBmspPage")
	public ModelAndView openPageBm(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgBmsp");
		return modelAndView;
	}
	
	/**
	 * 后台：设备的财务的审批
	 * @author   oufeng
	 * @version 创建时间：2015-08-20
	 * @return
	 */
	@RequestMapping(value = "/SbsgCwspPage")
	public ModelAndView openPageCw(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgCwsp");
		return modelAndView;
	}
	
	/**
	 * 后台：设备的领导的审批
	 * @author   oufeng
	 * @version 创建时间：2015-08-20
	 * @return
	 */
	@RequestMapping(value = "/SbsgLdspPage")
	public ModelAndView openPageLd(){
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgLdsp");
		return modelAndView;
	}
	
	/**
	 * 根据字典种类找出字典
	 * @author oufeng 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getBmByLx", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getBmByLx(String zdzl) {
		return sbSbsgService.getBmByLx("bmlx");
	}
	
	/**
	 * 获得申请人
	 * @author oufeng 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getSgr", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getSgr() {
		return sbSbsgService.getSgr();
	}
	
	/**
	 * 获得状态
	 * @author oufeng 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getZt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getZt() {
		return sbSbsgService.getZt();
	}
	
	/**
	 * 获得状态
	 * @author oufeng 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @return
	 */
	@RequestMapping(value = "/getSqzt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getSqzt() {
		return sbSbsgService.getSqzt();
	}
	
	/**
	 * 获得部门状态
	 * @author oufeng 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @return
	 */
	@RequestMapping(value = "/getBmzt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getBmzt() {
		return sbSbsgService.getBmzt();
	}
	
	/**
	 * 获得领导状态
	 * @author oufeng 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @return
	 */
	@RequestMapping(value = "/getLdzt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getLdzt() {
		return sbSbsgService.getLdzt();
	}
	
	/**
	 * 修改设备申购
	 * @author  oufeng
	 * @version 创建时间：2015年8月20日  下午15:49:44
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(SbSbsgxx entity, String id) {
		entity.setSgzt("1");
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		String sybm = yh.getBmbh();
		entity.setSgr(yhbh);
		entity.setSybm(sybm);
		sbSbsgService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除
	 * @author  oufeng
	 * @version 创建时间：2015年8月20日  下午15:49:44
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
		sbSbsgService.delete(ids);
	return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 增加
	 * @author  oufeng
	 * @version 创建时间：2015年8月20日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,SbSbsgxx entity,
			HttpServletRequest request,HttpServletResponse response){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh =yh.getYhbh();
		String yhxm = yh.getXm();
		entity.setSgr(yhbh);
		List<Map<String, Object>> list = sbSbsgService.getBmbh(yhbh);
		Map<String, Object> temp = list.get(0);
        Object value = temp.get("bmbh");
        String sybm = value.toString();
		entity.setSybm(sybm);
		sbSbsgService.save(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 申购部门审批界面
	 * @author  oufeng
	 * @version 创建时间：2015年8月20日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSbsgBmspList")
	@ResponseBody
 	public Pagination<Map<String, Object>> getSbsgBmspList(Integer start,Integer limit,String code,String sblb,String sqzt){
		return sbSbsgService.getSbsgBmList(start, limit,code,sblb,sqzt );
	}
	
	/**
	 * 申购领导审批界面
	 * @author  oufeng
	 * @version 创建时间：2015年8月20日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSbsgLdspList")
	@ResponseBody
 	public Pagination<Map<String, Object>> getSbsgLdspList(Integer start,Integer limit,String code,String sblb,
 			String sqzt){
		return sbSbsgService.getSbsgLdList(start, limit, code,sblb,sqzt);
	}
	
	/**
	 * 申购财务审批界面
	 * @author  oufeng
	 * @version 创建时间：2015年8月20日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSbsgCwspList")
	@ResponseBody
 	public Pagination<Map<String, Object>> getSbsgCwspList(Integer start,Integer limit,String code,String sblb){
		return sbSbsgService.getSbsgCwList(start, limit, code,sblb);
	}
	
	/**
	 * 申购部门审批保存
	 * @author  oufeng
	 * @version 创建时间：2015年8月20日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
		@RequestMapping(value="/saveSpyjBm")
		@ResponseBody
   	   	public ResponseData toSaveSpyjBm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
			//ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgBmsp");
   	     	sbSbsgService.toSaveSpyjBm(request,response);
   	 	    sbSbsgService.toUpdateSbsgBm(request,response);
   	 	    return ResponseData.SUCCESS_NO_DATA;
   	   	}
		
		/**
		 * 申购部门审批驳回的保存
		 * @author  oufeng
		 * @version 创建时间：2015年8月20日  
		 * @param model
		 * @param entity
		 * @param request
		 * @param response
		 * @return
		 */
			@RequestMapping(value="/saveSpyjBmBh")
			@ResponseBody
	   	   	public ResponseData toSaveSpyjBmBh(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
				//ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgBmsp");
	   	     	sbSbsgService.toSaveSpyjBmBh(request,response);
	   	 	    sbSbsgService.toUpdateSbsgBmBh(request,response);
	   	 	    return ResponseData.SUCCESS_NO_DATA;
	   	   	}
		
		/**
		 * 申购领导审批保存
		 * @author  oufeng
		 * @version 创建时间：2015年8月20日  
		 * @param model
		 * @param entity
		 * @param request
		 * @param response
		 * @return
		 */
			@RequestMapping(value="/saveSpyjLd")
			@ResponseBody
	   	   	public ResponseData toSaveSpyjLd(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
			//	ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgLdsp");
	   	     	sbSbsgService.toSaveSpyjLd(request,response);
	   	 	    sbSbsgService.toUpdateSbsgLd(request,response);
	   	 	  return ResponseData.SUCCESS_NO_DATA;
	   	   	}
			
			/**
			 * 申购领导审批驳回保存
			 * @author  oufeng
			 * @version 创建时间：2015年8月20日  
			 * @param model
			 * @param entity
			 * @param request
			 * @param response
			 * @return
			 */
				@RequestMapping(value="/saveSpyjLdBh")
				@ResponseBody
		   	   	public ResponseData toSaveSpyjLdBh(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
				//	ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgLdsp");
		   	     	sbSbsgService.toSaveSpyjLdBh(request,response);
		   	 	    sbSbsgService.toUpdateSbsgLdBh(request,response);
		   	 	  return ResponseData.SUCCESS_NO_DATA;
		   	   	}
			
			/**
			 * 申购部门财务审批保存
			 * @author  oufeng
			 * @version 创建时间：2015年8月20日  
			 * @param model
			 * @param entity
			 * @param request
			 * @param response
			 * @return
			 */
				@RequestMapping(value="/saveSpyjCw")
				@ResponseBody
				public ResponseData toSaveSpyjCw(ModelMap model,SbSbsgxx entity,
						HttpServletRequest request,HttpServletResponse response)throws UnsupportedEncodingException, ParseException{
		   	     	sbSbsgService.toSaveSpyjCw(request,response);
		   	 	    sbSbsgService.toUpdateSbsgCw(request,response);
		   	     	sbSbsgService.saveSbxx(request,response);
		   	 	  return ResponseData.SUCCESS_NO_DATA;
		   	   	}

				/**
				 * 申购部门财务审批保存
				 * @author  oufeng
				 * @version 创建时间：2015年8月20日  
				 * @param model
				 * @param entity
				 * @param request
				 * @param response
				 * @return
				 */
					@RequestMapping(value="/saveSpyjCwBh")
					@ResponseBody
			   	   	public ResponseData  toSaveSpyjCwBh(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
					//	ModelAndView modelAndView = new ModelAndView("/sbgl/sbsg/sbSbsgCwsp");
			   	     	sbSbsgService.toSaveSpyjCwBh(request,response);
			   	 	    sbSbsgService.toUpdateSbsgCwBh(request,response);
			   	 	  return ResponseData.SUCCESS_NO_DATA;
			   	   	}
}
