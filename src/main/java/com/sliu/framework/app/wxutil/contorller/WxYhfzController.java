package com.sliu.framework.app.wxutil.contorller;

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
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxutil.model.WxYhfz;
import com.sliu.framework.app.wxutil.service.WxYhfzService;

/**
 * 用户分组信息
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:45
 */
@Controller
@RequestMapping(value = "/wxutil/yhfz")
public class WxYhfzController extends BaseController{

	@Autowired
	private WxYhfzService wxYhfzService;
	
	@RequestMapping(value = "/ownerpage")
	public ModelAndView openWxYhfzPage(String ticket) {
		ModelAndView modelAndView = new ModelAndView("/weixin/yhfz/ownerlist");
//		String url = "/weixin/yhfz/ownerlist";
//		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	/**
	 * 获取自己发起的用户分组列表
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月23日 下午4:02:38 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/ownerpageList", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String, Object>> ownerpageList(Integer start,Integer limit) {
		return wxYhfzService.ownerpageList(start,limit);
	}
	
	/**
	 * 增加
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:28:27
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,WxYhfz entity,String toUser,
			HttpServletRequest request,HttpServletResponse response){
		wxYhfzService.save(entity,toUser);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:28:27
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public ResponseData update(ModelMap model,WxYhfz entity,String toUser,
			HttpServletRequest request,HttpServletResponse response){
		wxYhfzService.update(entity,toUser);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 结束用户分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月26日 下午4:55:18 
	 * @param hyid
	 * @return
	 */
	@RequestMapping(value="/over")
	@ResponseBody
	public ResponseData over(Long hyid){
		wxYhfzService.over(hyid);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 获取用户分组关系信息
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月25日 下午4:46:25 
	 * @param start
	 * @param limit
	 * @param hyid
	 * @return
	 */
	@RequestMapping(value = "/menberList")
	@ResponseBody
	public Pagination<Map<String, Object>> menberList(Integer start,Integer limit,Long fzid){
		return wxYhfzService.menberList(start, limit,fzid);
	}
	
	/**
	 * 删除用户分组
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:43
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData deleteNews(Long[] ids){
		wxYhfzService.delete(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 获取自己的分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月26日 下午2:56:43 
	 * @return
	 */
	@RequestMapping(value="getowerlist")
	@ResponseBody
	public  List<Map<String, Object>> getowerlist(){
		List<Map<String, Object>> list = wxYhfzService.getowerlist();
		return list;
	}
	
	/**
	 * 获取自己的分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月26日 下午2:56:43 
	 * @return
	 */
	@RequestMapping(value="getmenberlist")
	@ResponseBody
	public  List<Map<String, Object>> getmenberlist(Long fzid){
		List<Map<String, Object>> list = wxYhfzService.getmenberlist(fzid);
		return list;
	}
	
}
