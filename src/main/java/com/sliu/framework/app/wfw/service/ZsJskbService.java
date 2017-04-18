package com.sliu.framework.app.wfw.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsJskbDao;

/**
 * 教师课表
 * @author liujiasen
 * @date 2015年5月28日
 */
@Service
public class ZsJskbService {

	@Autowired
	private ZsJskbDao zsJskbDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取教师课表信息
	 * @author liujiasen
	 * @date 2015年5月25日
	 * @return
	 */
	public String getJskb(String day,String sxz,String openId){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat s=new SimpleDateFormat("MM\\/dd\\/yyyy");
		String start="";
		String end="";
		if(day.contains(",")){
			String[] arr=day.split(",");
			day=arr[1];
		}
		if(day!=null&&!"".equals(day)){
			start=AppUtil.getCurrentMonday(day);
			end=AppUtil.getPreviousSunday(day);
		}else{
			day=sim.format(new Date());
			start=AppUtil.getCurrentMonday(day);
			end=AppUtil.getPreviousSunday(day);
		}
		Date st=new Date();
		Date e=new Date();
		try {
			st=sim.parse(start);
			e=sim.parse(end);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		List<Map<String,Object>> list=zsJskbDao.getJskb(start,end,openId);
		String str="{\"events\":[";
		for(int i=0;i<list.size();i++){
			Random r=new Random();
			if(i<list.size()-1){
				str=str+"["+list.get(i).get("id")+",\""+list.get(i).get("kcmc")+"/"+list.get(i).get("skdd")
						+ "\",\""+list.get(i).get("sksj")+"\",\""+list.get(i).get("xksj")+"\",0,0,0,"+r.nextInt(14)+","
						+ list.get(i).get("dtskxh")+",null,\"\"],";
			}else{
				str=str+"["+list.get(i).get("id")+",\""+list.get(i).get("kcmc")+"/"+list.get(i).get("skdd")
						+ "\",\""+list.get(i).get("sksj")+"\",\""+list.get(i).get("xksj")+"\",0,0,0,"+r.nextInt(14)+","
						+ list.get(i).get("dtskxh")+",null,\"\"]";
			}
		}
		str=str+"],\"issort\":true,\"start\":\""+s.format(st)+ " 00:00"+"\",\"end\":\""+s.format(e)+ " 23:59"+"\",\"error\":null}";
		
		return str;
	}
	
	/**
	 * 获取教师课表信息(新)
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @param xn
	 * @param xq
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getJskbList(String xn,String xq,String zs,String openId,String jc){
		if("-1".equals(zs)){zs="";}
		return zsJskbDao.getJskbList(xn, xq ,zs, openId,jc);
	}
	
	/**
	 * 获取学年
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getXn(String openId){
		return zsJskbDao.getXn(openId);
	}
	
	/**
	 * 获取最大周数
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @return
	 */
	public List<Map<String,Object>> getZs(){
		List<Map<String,Object>> list=zsJskbDao.getZs();
		List<Map<String,Object>> zs=new ArrayList<Map<String,Object>>();
		int num=0;
		if(list.size()!=0){
			if(list.get(0).get("zs")!=null){
				num=Integer.parseInt(list.get(0).get("zs").toString());
			}
		}
		for(int i=1;i<=num;i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("zs", i);
			zs.add(map);
		}
		return zs;
	}
}
