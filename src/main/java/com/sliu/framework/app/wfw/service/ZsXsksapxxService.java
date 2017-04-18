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

import com.sliu.framework.app.wfw.dao.ZsXsksapxxDao;

/**
 * 
 * @author liujiasen
 * @date 2015年5月29日
 */
@Service
public class ZsXsksapxxService {

	@Autowired
	private ZsXsksapxxDao zsXsksapxxDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 按学年和学期获取当前登录人的考试成绩
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksqh
	 * @return
	 */
	public List<Map<String,Object>> getGrksqp(String ksqh,String openId,HttpServletRequest request){
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			ksxn=arr[0]+"-"+arr[1];
			ksxq=arr[2];
		}else{
			ksxn=zsXsksapxxDao.getNewXn(openId).get("ksxn")+"";
			ksxq=zsXsksapxxDao.getNewXq(ksxn,openId).get("ksxq")+"";
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsksapxxDao.getGrksqp(ksxn, ksxq,openId,pages);
	}

	/**
	 * 获取个人考试成绩学年学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> ksqh=zsXsksapxxDao.getKsqh(openId);
		for(int i=0;i<ksqh.size();i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("ksqh", ksqh.get(i).get("ksxn")+"-"+ksqh.get(i).get("ksxq"));
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
	public List<Map<String,Object>> getXsxx(String ksqh,String openId,HttpServletRequest request) 
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
			if(zsXsksapxxDao.getNewXn(openId).size()!=0){
			ksxn=zsXsksapxxDao.getNewXn(openId).get("ksxn")+"";}
			if(zsXsksapxxDao.getNewXq(ksxn,openId).size()!=0){
			ksxq=zsXsksapxxDao.getNewXq(ksxn,openId).get("ksxq")+"";}
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsksapxxDao.getXsxx(ksxn, ksxq,openId,pages);
	}
}
