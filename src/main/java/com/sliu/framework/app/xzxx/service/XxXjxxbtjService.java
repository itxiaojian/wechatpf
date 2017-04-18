package com.sliu.framework.app.xzxx.service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.xzxx.dao.XxXjxxbDao;
import com.sliu.framework.app.xzxx.dao.XxXjxxbtjDao;
import com.sliu.framework.app.xzxx.model.XxXjxxb;

/**
 * 校长信箱-数据统计
 * @author            oufeng
 * @since             2015-09-01
 */

@Service
public class XxXjxxbtjService extends BaseBO<XxXjxxb>{
      @Autowired
      private XxXjxxbtjDao XxXjxxbtjDao;
      
  	/**
  	*校长邮箱统计图表
  	@throws UnsupportedEncodingException 
  	* @Author oufeng	
  	@Date 2015年9月2日 
  	@Version 1.0
  	*/
  	public List<Map<String,Object>> getDataXxXjxxtj(HttpServletRequest request,HttpServletResponse response)
  			throws UnsupportedEncodingException {
  		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		String id= request.getParameter("id");
		if(id !=null){
			try {
	   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
  		return XxXjxxbtjDao.getDataXxXjxxtj(id,stime,etime);
         }
  	
  	/**
  	*校长邮箱统计tab
  	@throws UnsupportedEncodingException 
  	* @Author oufeng	
  	@Date 2015年9月2日 
  	@Version 1.0
  	*/
  	public List<Map<String,Object>> getData(HttpServletRequest request,HttpServletResponse response)
  			throws UnsupportedEncodingException {
  		String id= request.getParameter("id");
  		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		if(id !=null){
		try {
   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
  		return XxXjxxbtjDao.getDataTab(id,stime,etime);
         }
  	
	/**
	 *获得部门名称 
	 * @author oufeng
	 * @date 2015年9月1日
	 * @return
	 */
	public List<Map<String, Object>> getBmmc() {
		return XxXjxxbtjDao.getBmmc();
	}
	
	/**
  	*校长邮箱统计tab
  	@throws UnsupportedEncodingException 
  	* @Author oufeng	
  	@Date 2015年9月2日 
  	@Version 1.0
  	*/
  	public List<Map<String,Object>> getXzyxmydt(HttpServletRequest request,HttpServletResponse response)
  			throws UnsupportedEncodingException {
  		String id= request.getParameter("id");
  		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		if(id !=null){
		try {
   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
  		return XxXjxxbtjDao.getXzyxmydt(id,stime,etime);
         }
  	
	/**
  	*校长邮箱统计tab
  	@throws UnsupportedEncodingException 
  	* @Author oufeng	
  	@Date 2015年9月2日 
  	@Version 1.0
  	*/
  	public List<Map<String,Object>> getXzyxmydb(HttpServletRequest request,HttpServletResponse response)
  			throws UnsupportedEncodingException {
  		String id= request.getParameter("id");
  		String stime= request.getParameter("stime");
		String etime= request.getParameter("etime");
		if(id !=null){
		try {
   			id= new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
  		return XxXjxxbtjDao.getXzyxmydb(id,stime,etime);
         }
}
