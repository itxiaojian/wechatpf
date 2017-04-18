package com.sliu.framework.app.wfw.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsXskbDao;

/**
 * 学生课表
 * @author liujiasen
 * @date 2015年5月25日
 */
@Service
public class ZsXskbService {

	@Autowired
	private ZsXskbDao zsXskbDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 根据当年周数获取学生课表信息（默认查询当前周）
	 * @author liujiasen
	 * @date 2015年5月25日
	 * @return
	 */
	public String getXskb(String day,String sxz,String openId){
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
		List<Map<String,Object>> list=zsXskbDao.getXskb(start,end,openId);
		String str="{\"events\":[";
		//Gson gson=new Gson();
		for(int i=0;i<list.size();i++){
			//gson.toJson(list.get(i));
			Random r=new Random();
			if(i<list.size()-1){
				str=str+"["+list.get(i).get("id")+",\""+list.get(i).get("kcmc")+"/"+list.get(i).get("skdd")+"/"+list.get(i).get("xm")
						+ "\",\""+list.get(i).get("sksj")+"\",\""+list.get(i).get("xksj")+"\",0,0,0,"+r.nextInt(14)+","
						+ list.get(i).get("dtskxh")+",null,\"\"],";
			}else{
				str=str+"["+list.get(i).get("id")+",\""+list.get(i).get("kcmc")+"/"+list.get(i).get("skdd")+"/"+list.get(i).get("xm")
						+ "\",\""+list.get(i).get("sksj")+"\",\""+list.get(i).get("xksj")+"\",0,0,0,"+r.nextInt(14)+","
						+ list.get(i).get("dtskxh")+",null,\"\"]";
			}
		}
		str=str+"],\"issort\":true,\"start\":\""+s.format(st)+ " 00:00"+"\",\"end\":\""+s.format(e)+ " 23:59"+"\",\"error\":null}";
		
		return str;
	}
	
	/**
	 * 获取学生课表信息(新)
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @param xn
	 * @param xq
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getXskbList(String xn,String xq,String zs,String openId,String jc){
		if("-1".equals(zs)){zs="";}
		return zsXskbDao.getXskbList(xn, xq ,zs, openId,jc);
	}
	
	/**
	 * 获取最新周次
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @param xn
	 * @param xq
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getNewZc(String time){
		return zsXskbDao.getNewZc(time);
	}
	
	/**
	 * 获取学年
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getXn(String openId){
		return zsXskbDao.getXn(openId);
	}
	
	/**
	 * 获取最大周数
	 * @author liujiansen
	 * @date 2015年11月9日
	 * @return
	 */
	public List<Map<String,Object>> getZs(){
		//List<Map<String,Object>> list=zsXskbDao.getZs();
		List<Map<String,Object>> zs=new ArrayList<Map<String,Object>>();
		/*int num=0;
		if(list.size()!=0){
			if(list.get(0).get("zs")!=null){
				num=Integer.parseInt(list.get(0).get("zs").toString());
			}
		}*/
		for(int i=1;i<=21;i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("zs", i);
			zs.add(map);
		}
		return zs;
	}
}
