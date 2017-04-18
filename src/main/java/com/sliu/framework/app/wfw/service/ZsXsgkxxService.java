package com.sliu.framework.app.wfw.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsXsgkxxDao;

/**
 * 学生挂科信息
 * @author zhangyan
 * @date 2016年8月8日
 */
@Service
public class ZsXsgkxxService {

	@Autowired
	private ZsXsgkxxDao zsXsgkxxDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 按学年和学期获取当前登录人的考试成绩
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksqh
	 * @return
	 */
	public List<Map<String,Object>> getGkxx(String ksqh,String bjmc,String openId,HttpServletRequest request){
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			ksxn=arr[0]+"-"+arr[1];
			ksxq=arr[2];
		}else{
			ksxn=zsXsgkxxDao.getNewXn(openId).get("ksxn")+"";
			ksxq=zsXsgkxxDao.getNewXq(ksxn,openId).get("ksxq")+"";
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsgkxxDao.getGkxx(ksxn, ksxq,bjmc,openId,pages);
	}

	/**
	 * 获取个人考试成绩学年学期
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> ksqh=zsXsgkxxDao.getKsqh(openId);
		for(int i=0;i<ksqh.size();i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("ksqh", ksqh.get(i).get("xn")+"-"+ksqh.get(i).get("xq"));
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 按学年和学期获取当前登录人的信息
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getXsxx(String ksqh,String bjmc,String openId,HttpServletRequest request) 
			throws UnsupportedEncodingException{
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			ksxn=arr[0]+"-"+arr[1];
			ksxq=arr[2];
				//ksxn = new String(ksxn.getBytes("ISO-8859-1"), "UTF-8"); 
				//ksxq = new String(ksxq.getBytes("ISO-8859-1"), "UTF-8"); 
		}else{
			String whStr = "";
			//List<Map<String,Object>> user = zsXsksapxxDao.getWxyh(openId);
			/*if(user.size()!=0){
				String yhid =user.get(0).get("yhid")+"";
				whStr = zsXsksapxxDao.getCxtj("", user);
			}*/
			if(zsXsgkxxDao.getNewXn(openId).size()!=0){
			ksxn=zsXsgkxxDao.getNewXn(openId).get("ksxn")+"";}
			if(zsXsgkxxDao.getNewXq(ksxn,openId).size()!=0){
			ksxq=zsXsgkxxDao.getNewXq(ksxn,openId).get("ksxq")+"";}
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsgkxxDao.getXsxx(ksxn, ksxq,bjmc,openId,pages);
	}
	
	/**
	 * 贫困生详细信息-查找学生信息
	 * @author zhangyan
	 * @date  2016年8月12日
	 * @param xh
	 * @return
	 */
	public List<Map<String,Object>> getXsxxDetail(String xh,String openId){
		
		return zsXsgkxxDao.getXsxxDetail(xh,openId);
	}
	
	/**
	 * 查看贫困生详细信息
	 * @author zhangyan
	 * @date  2016年8月12日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getGkxxDetail(String xh,String ksqh,String openId){
		
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			ksxn=arr[0]+"-"+arr[1];
			ksxq=arr[2];
				//ksxn = new String(ksxn.getBytes("ISO-8859-1"), "UTF-8"); 
				//ksxq = new String(ksxq.getBytes("ISO-8859-1"), "UTF-8"); 
		}else{
			String whStr = "";
			//List<Map<String,Object>> user = zsXsksapxxDao.getWxyh(openId);
			/*if(user.size()!=0){
				String yhid =user.get(0).get("yhid")+"";
				whStr = zsXsksapxxDao.getCxtj("", user);
			}*/
			if(zsXsgkxxDao.getNewXn(openId).size()!=0){
			ksxn=zsXsgkxxDao.getNewXn(openId).get("ksxn")+"";}
			if(zsXsgkxxDao.getNewXq(ksxn,openId).size()!=0){
			ksxq=zsXsgkxxDao.getNewXq(ksxn,openId).get("ksxq")+"";}
		}
		return zsXsgkxxDao.getGkxxDetail(xh,ksxn,ksxq,openId);
	}
	
	/**
	 * 获取辅导员所带的班级
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @return
	 */
	public List<Map<String,Object>> getBj(String openId){
		
		return zsXsgkxxDao.getBj(openId);
	}
}
