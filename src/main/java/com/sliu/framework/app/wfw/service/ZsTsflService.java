package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsTsflDao;
import com.sliu.framework.app.wfw.model.ZsTsfl;

/**
 * 图书分类
 * @author duanpeijun
 * @version 创建时间：2015年6月9日  上午10:42:52
 */
@Service
public class ZsTsflService extends BaseBO<ZsTsfl>{

	protected Logger logger = LoggerFactory.getServiceLog(ZsTsfl.class);

	@Autowired
	private ZsTsflDao zsTsflDao;
	
	/**
	 * 根据页数和标题查询图书分类数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getTsflList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String zl=request.getParameter("zl");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsTsflDao.getTsflList(pages, zl);
	}
	
	/**
	 * 根据页数和标题查询图书分类数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getTsflListZj(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String zl=request.getParameter("zl");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsTsflDao.getTsflListZj(pages, zl);
	}
}
