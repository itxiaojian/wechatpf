package com.sliu.framework.app.wzy.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.service.ZyLbtService;

/**
 * 主页轮播图 Controller
 * 
 * @author liujiansen
 * @since 2016-03-07
 */
@Controller
@RequestMapping(value = "/wzy/ZyLbt")
public class ZyLbtController extends BaseController {
	@Autowired
	private ZyLbtService service;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav=new ModelAndView("/wzy/zyLbtList");
		return mav;
	}

	/**
	 * 查询图片列表信息
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param start
	 * @param limit
	 * @param tpmc
	 * @param tplx
	 * @return
	 */
	@RequestMapping(value = "/pager")
	@ResponseBody
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit, String tpmc, String tplx) {
		return service.pager(start, limit, tpmc, tplx);
	}
	
	/**
	 * 获取图片类型
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @return
	 */
	@RequestMapping(value = "/getTplx")
	@ResponseBody
	public List<Map<String, Object>> getTplx() {
		return service.getTplx("tplx");
	}

	/**
	 * 保存
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param request
	 * @param response
	 * @param attachMentFile
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(HttpServletRequest request,HttpServletResponse response,MultipartFile attachMentFile) {
		String str="0";
		str=service.insertEntity(attachMentFile,request,response);
		return str;
	}
	
	/**
	 * 修改
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param request
	 * @param response
	 * @param attachMentFile
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(HttpServletRequest request,HttpServletResponse response,MultipartFile attachMentFile) {
		String str="0";
		str=service.updateEntity(attachMentFile,request,response);
		return str;
	}

	/**
	 * 删除
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		for (Long id : ids) {
			service.deleteEntity(id);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}

}
