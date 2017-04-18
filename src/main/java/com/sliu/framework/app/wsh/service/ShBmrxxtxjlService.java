package com.sliu.framework.app.wsh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wsh.dao.ShBmrxxtxjlDao;
import com.sliu.framework.app.wsh.model.ShBmrxxtxjl;

/**
 * 微报名
 * @author liujiansen
 * @date 2015年6月24日
 */
@Service
public class ShBmrxxtxjlService extends BaseBO<ShBmrxxtxjl>{

	protected Logger logger = LoggerFactory.getServiceLog(ShBmrxxtxjl.class);
	
	@Autowired
	private ShBmrxxtxjlDao dao;
	
	/**
	 * 根据报名编号获取报名人数
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String bmid=request.getParameter("id");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		return dao.getList(pages, bmid);
	}
	
	/**
	 * 获取报名的总人数
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @return
	 */
	public int getCount(HttpServletRequest request){
		String bmid=request.getParameter("id");
		return dao.getCount(bmid);
	}
	
	/**
	 * 根据报名编号获取报名活动表单名称
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 * @return
	 */
	public List<Map<String,Object>> getBt(String bmid){
		return dao.getBt(bmid);
	}
}
