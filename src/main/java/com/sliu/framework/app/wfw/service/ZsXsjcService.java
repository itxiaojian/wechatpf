package com.sliu.framework.app.wfw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wfw.dao.ZsXsjcDao;

/**
 * 学生奖惩
 * @author liujiasen
 * @date 2015年5月20日
 */
@Service
public class ZsXsjcService {

	@Autowired
	private ZsXsjcDao zsXsjcDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/*public Pagination<Map<String, Object>> getXscjList(Integer start,
			Integer limit, String xm, String wxh) {
		return zsXsjcDao.getXscjList(start, limit, xm, wxh);
	}*/
	
	/**
	 * 按学年和学期获取当前登录人的奖惩数据
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksqh
	 * @return
	 */
	public List<Map<String,Object>> getGrcj(String ksqh,String openId,HttpServletRequest request){
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			ksxn=arr[0]+"-"+arr[1];
			ksxq=arr[2];
		}else{
			ksxn=zsXsjcDao.getNewXn(openId).get("ksxn")+"";
			ksxq=zsXsjcDao.getNewXq(ksxn,openId).get("ksxq")+"";
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsjcDao.getGrjc(ksxn, ksxq,openId,pages);
	}

	/**
	 * 获取个人奖惩的学年学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> ksqh=zsXsjcDao.getJcqh(openId);
		for(int i=0;i<ksqh.size();i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("ksqh", ksqh.get(i).get("ksxn")+"-"+ksqh.get(i).get("ksxq"));
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 根据ID获取奖惩的详细信息
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @param id
	 * @return
	 */
	public Map<String,Object> getMx(String id){
		return zsXsjcDao.getMx(id);
	}
}
