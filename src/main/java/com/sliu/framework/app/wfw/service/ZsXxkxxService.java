package com.sliu.framework.app.wfw.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsXxkxxDao;
import com.sliu.framework.app.wfw.model.ZsXxkxx;

/**
 * 网络选修课信息
 * @author liujiasen
 * @date 2015年6月3日
 */
@Service
public class ZsXxkxxService extends BaseBO<ZsXxkxx> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsXxkxxService.class);

	@Autowired
	private ZsXxkxxDao zsXxkxxDao;

	/**
	 * 按学年和学期获取当前登录人的选修课信息
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getXxkxxList(String ksqh,String openId,HttpServletRequest request){
		String xn="";
		String xq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			xn=arr[0]+"-"+arr[1];
			xq=arr[2];
		}else{
			xn=zsXxkxxDao.getNewXn(openId).get("xn")+"";
			xq=zsXxkxxDao.getNewXq(xn,openId).get("xq")+"";
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXxkxxDao.getXxkxxList(xn, xq, openId,pages);
    }
	
	
	/**
	 * 获取个人选修课的学年学期
	 * @author duanpeijun
	 * @date 2015年7月17日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> ksqh=zsXxkxxDao.getKsqh(openId);
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
			if(zsXxkxxDao.getNewXn(openId).size()!=0){
			ksxn=zsXxkxxDao.getNewXn(openId).get("xn")+"";}
			if(zsXxkxxDao.getNewXq(ksxn,openId).size()!=0){
			ksxq=zsXxkxxDao.getNewXq(ksxn,openId).get("xq")+"";}
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXxkxxDao.getXsxx(ksxn, ksxq,openId,pages);
	}
	
}