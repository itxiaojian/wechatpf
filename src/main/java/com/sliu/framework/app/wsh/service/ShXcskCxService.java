package com.sliu.framework.app.wsh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wsh.dao.ShXcskCxDao;
import com.sliu.framework.app.wsh.model.ShXcsk;

/**
 * 校车时刻JSP
 * @author oufeng
 * @date 2015年6月23日
 */
@Service
public class ShXcskCxService extends BaseBO<ShXcsk>{

	protected Logger logger = LoggerFactory.getServiceLog(ShXcsk.class);
	
	@Autowired
	private ShXcskCxDao dao;
	
	/**
	 * 根据页数和标题查询校车时刻招领数据
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getShXcskList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String bt=request.getParameter("bt");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return dao.getShXcskList(pages, bt);
	}
	
	/**
	 * 总条数
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param request
	 * @return
	 */
	public int getCount(HttpServletRequest request){
		String bt=request.getParameter("bt");
		return dao.getCount(bt);
	}
	
	/**
	 * 根据查询条件查询数据
     * @author oufeng
	 * @date 2015年6月23日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getShxsckByCph(String cph){
		return dao.getShxsckByCph(cph);
	}
	
	
	/**
	 * 查询车牌号数目
     * @author oufeng
	 * @date 2015年6月23日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getCph(HttpServletRequest request){
		return dao.getCph();
	}
	
	/**
	 * 查询车牌号数目
     * @author oufeng
	 * @date 2015年6月23日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getFirst(HttpServletRequest request){
		return dao.getFirst();
	}
	
	/**
	 * 校车的路线的数目
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getShXcskRoute(String cph){
		return dao.getShXcskRoute(cph);
	}
	
}
