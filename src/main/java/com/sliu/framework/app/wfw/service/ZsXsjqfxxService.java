package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsXsjqfxxDao;
import com.sliu.framework.app.wfw.model.ZsXsjqfxx;

/**
 * 学生缴欠费信息
 * @author liujiasen
 * @date 2015年6月1日
 */
@Service
public class ZsXsjqfxxService extends BaseBO<ZsXsjqfxx> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsXsjqfxxService.class);

	@Autowired
	private ZsXsjqfxxDao zsXsjqfxxDao;

	/**
	 * 根据页数和标题查询学生缴欠费信息数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getXsjqfxxList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
		String openId = request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsjqfxxDao.getXsjqfxxList(pages, code,openId);
	}
	
	/**
	 * 根据页数和标题查询学生缴欠费信息数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getXsjqfList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
		String openId = request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsjqfxxDao.getXsjqfList(pages, code,openId);
	}
}
