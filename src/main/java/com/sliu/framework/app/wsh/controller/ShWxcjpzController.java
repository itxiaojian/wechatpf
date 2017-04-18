package com.sliu.framework.app.wsh.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.model.ShWxcjpz;
import com.sliu.framework.app.wsh.service.ShWxcjpzService;

/**
 * 微信抽奖配置 Controller
 * @author            liujiansen
 * @since             2015-06-18
 */
@Controller
@RequestMapping(value="/wsh/ShWxcjpz")
public class ShWxcjpzController extends BaseController
{
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShWxcjpzController.class);
	
    @Autowired
    private ShWxcjpzService service;
    
    @RequestMapping(value = "/index")
    public ModelAndView index(){
		ModelAndView mav = new ModelAndView("/wsh/ShWxcj/ShWxcjpz");
		return mav;
	}
    
    /**
	 * 获取最大的截止时间
	 * @author liujiansen
	 * @date 2015年12月3日
	 * @return
	 */
    @RequestMapping(value = "/getMaxJssj")
	@ResponseBody
    public String getMaxJssj(){
    	String str="";
    	List<Map<String,Object>> list=service.getMaxJssj();
		if(list.size()!=0){
			str=list.get(0).get("jssj").toString();
		}
		return str;
    }
    
    /**
     * 微信抽奖配置列表
     * @author liujiansen
     * @date 2015年6月18日
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(value = "/pager")
	@ResponseBody
	public Pagination<Map<String,Object>> pager(Integer start, Integer limit,HttpServletRequest request) {
		Pagination<Map<String,Object>> list = service.getWxpzList(start, limit,request);
		return list;
	}
    
    /**
     * 微信抽奖配置保存
     * @author liujiansen
     * @date 2015年6月19日
     * @param model
     * @param entity
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ShWxcjpz entity,HttpServletRequest request, HttpServletResponse response) {
		String str = service.savePz(entity);
		if ("0".equals(str)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
    
    /**
     * 微信抽奖配置修改
     * @author liujiansen
     * @date 2015年6月19日
     * @param entity
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ShWxcjpz entity,HttpServletRequest request,String id) {
    	entity.setId(Long.parseLong(id));
    	String str = service.updatePz(entity);
    	if ("0".equals(str)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
    }
    
    /**
     * 删除抽奖配置
     * @author liujiansen
     * @date 2015年6月19日
     * @param id
     * @param qssj
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
    public ResponseData delete(String id,String qssj){
    	String str = service.deletePz(id,qssj);
    	if ("0".equals(str)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
    }
}

