package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsXskqxxDao;
import com.sliu.framework.app.wfw.model.ZsXskqxx;

/**
 * 学生考勤信息
 * @author chenhui
 * @version 创建时间：2016年8月26日
 */
@Service
public class ZsXskqService  extends BaseBO<ZsXskqxx>{

	protected Logger logger = LoggerFactory.getServiceLog(ZsXskqxx.class);
	
	@Autowired
	private ZsXskqxxDao zsXskqxxDao;

	/**
	 * 学生考勤信息
	 * @author chenhui
	 * @date  2016年8月9日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getZsxskqxxList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
		String openId=request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXskqxxDao.getZsxskqxxList(pages, code,openId);
	}
	
	/**
	 * 辅导员查看学生考勤信息
	 * @author chenhui
	 * @date  2016年8月9日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getXskqyjList(String bjmc,HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
		String openId=request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXskqxxDao.getXskqyjList(bjmc,pages, code,openId);
	}
	
	/**
	 * 获取当前学生信息
	 * @author chenhui
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getXs(String openId){
		return zsXskqxxDao.getXs(openId);
	}
	
	/**
	 * 根据id查看学生考勤信息
	 * @author chenhui
	 * @date  2016年8月9日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getQuery(Long id,String openId){
		
		return zsXskqxxDao.getQuery(id,openId);
	}
	
	/**
	 * 获取字典表中的班级
	 * @author chenhui
	 * @date 2015年8月07日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getBj(String openId){
		return zsXskqxxDao.getBj(openId);
	}
	
	/**
	 * 辅导员获取当前学生信息
	 * @author chenhui
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getXsxx(String xh){
		return zsXskqxxDao.getXsxx(xh);
	}
	
	/**
	 * 学生考勤信息
	 * @author chenhui
	 * @version 创建时间：2015年6月9日
	 */

		public List<Map<String,Object>> getxskqxxListDetail(String xsxh){
			
			return zsXskqxxDao.getxskqxxListDetail(xsxh);
		}
	
		/**
		 * 根据字典种类找出字典
		   @author: chenhui 
		 * @version 创建时间：2016年1月12日  
		 * @param zdzl
		 * @return
		 */
		public List<Map<String, Object>> getDicByLx(String zt) {
			return zsXskqxxDao.getDicByLx(zt);
		}
		
}
