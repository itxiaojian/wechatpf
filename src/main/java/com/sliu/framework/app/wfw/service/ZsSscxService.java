package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsSscxDao;
import com.sliu.framework.app.wfw.model.ZsSscx;

@Service
public class ZsSscxService extends BaseBO<ZsSscx>{
	protected Logger logger = LoggerFactory.getServiceLog(ZsSscx.class);
	
	@Autowired
	private ZsSscxDao dao;
	/**
	 * 宿舍查询
	 * @author oufeng
	 * @date 2015年7月1日
	 */
	public List<Map<String,Object>> getSscxList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return dao.getSscxList(pages, code);
	}
}