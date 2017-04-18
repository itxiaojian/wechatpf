package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsTsjyxxDao;

/**
 * 微信用户
 * @author liujiasen
 * @date 2015年5月18日
 */
@Service
public class ZsTsjyxxService {

	@Autowired
	private ZsTsjyxxDao zsTsjyxxDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取当前登录人的图书借阅信息
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 
	public List<Map<String,Object>> getGrTsjyxx(String openId){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		String ghsj = sim.format(c.getTime());
		return zsTsjyxxDao.getGrTsjyxx(ghsj,openId);
	}*/
	
	/**当前登录人的信息
	 * 
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getGrTsjyxxBysj(String openId,HttpServletRequest request){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsTsjyxxDao.getGrTsjyxx(openId,pages);
	}
	
	/**未归还图书信息
	 * 
	 * @author zhangyan
	 * @date 2016年4月26日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getwghxx(String jcsj,String openId,HttpServletRequest request,String yhbh){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsTsjyxxDao.getwghxx(jcsj,openId,pages,yhbh);
	}
	
	/**已归还图书信息
	 * 
	 * @author zhangyan
	 * @date 2016年4月26日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getyghxx(String jcsj,String openId,HttpServletRequest request,String yhbh){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsTsjyxxDao.getyghxx(jcsj,openId,pages,yhbh);
	}
	
	
	/**
	 * 
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getPh(HttpServletRequest request){
		String openId=request.getParameter("openId");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		return zsTsjyxxDao.getPh(openId,pages);
	}
	
	
	/**
	 * 
	 * @author 获取图书的详情介绍
	 * @date 2015年7月15日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getXq(HttpServletRequest request){
		String openId=request.getParameter("openId");
	/*	String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}*/
		String tsbh=request.getParameter("tsbh");
		return zsTsjyxxDao.getXq(openId,tsbh);
	}
	
	/**
	 * 
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getGhBe(HttpServletRequest request,String dateStr,String _dateStr){
		String openId=request.getParameter("openId");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		return zsTsjyxxDao.getGhBe(openId,dateStr,_dateStr);
	}
	
	/**
	 * 
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getGhTo(HttpServletRequest request,String dateStr){
		String openId=request.getParameter("openId");
		String pages = request.getParameter("pages");
		if (pages == null) {
			pages = "1";
		}
		return zsTsjyxxDao.getGhTo(openId,dateStr);
	}
}
