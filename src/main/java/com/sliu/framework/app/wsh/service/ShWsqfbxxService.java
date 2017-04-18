package com.sliu.framework.app.wsh.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wsh.dao.ShWsqfbxxDao;
import com.sliu.framework.app.wsh.model.ShWsqfbxx;

/**
 * 微上墙
 * @author liujiasen
 * @date 2015年7月7日
 */
@Service
public class ShWsqfbxxService extends BaseBO<ShWsqfbxx>{

	protected Logger logger = LoggerFactory.getServiceLog(ShWsqfbxx.class);
	
	@Autowired
	private ShWsqfbxxDao dao;
	
	/**
	 * 获取发布信息
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param id
	 * @param cyrid
	 * @return
	 */
	public List<Map<String, Object>> getFbxx(String id, String cyrid) {
		return dao.getFbxx(id,cyrid,"");
	}
	
	/**
	 * 上墙信息
	 * @author liujiasen
	 * @date 2015年7月9日
	 * @param id
	 * @param cyrid
	 * @return
	 */
	public List<Map<String, Object>> getSqxx(String id, String cyrid) {
		return dao.getFbxx(id,cyrid,"5");
	}
/*	public String getSqxx(String id, String cyrid,String length,String time_start) {
		String str="{ \"code\":0,\"contents\":[";
		List<Map<String, Object>> sqxx=dao.getFbxx(id,cyrid,length);
		if(sqxx.size()!=0){
			for(int i=0;i<sqxx.size();i++){
				if(i<sqxx.size()-1){
					str=str+"{\"id\":\""+sqxx.get(i).get("id")+"\",\"nickname:\":\""+sqxx.get(i).get("cyrxm")
							+"\",\"avatar_image\":\""+sqxx.get(i).get("bz")+"\",\"content\":\""+sqxx.get(i).get("fbnr")
							+"\",\"wall_activity_content\":{\"id\":\""+sqxx.get(i).get("hdid")+"\"},\"user_id\":\""
							+sqxx.get(i).get("cyrid")+"\"},";
				}else{
					str=str+"{\"id\":\""+sqxx.get(i).get("id")+"\",\"nickname:\":\""+sqxx.get(i).get("cyrxm")
							+"\",\"avatar_image\":\""+sqxx.get(i).get("bz")+"\",\"content\":\""+sqxx.get(i).get("fbnr")
							+"\",\"wall_activity_content\":{\"id\":\""+sqxx.get(i).get("hdid")+"\"},\"user_id\":\""
							+sqxx.get(i).get("cyrid")+"\"}";
				}
			}
		}
		str=str+"],\"time_start\":\""+time_start+"\",\"freq\":"+sqxx.size();
		return str;
	}*/

	/**
	 * 保存发布信息
	 * @author liujiasen
	 * @date 2015年7月9日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String toSend(HttpServletRequest request,HttpServletResponse response) {
		String str="";
		String openId=request.getParameter("openId");
		String cyrid=request.getParameter("cyrid");
		String hdid=request.getParameter("id");
		String fbxx=request.getParameter("fbxx");
		ShWsqfbxx xx=new ShWsqfbxx();
		xx.setCyrid(Long.parseLong(cyrid));
		xx.setFbnr(fbxx);
		xx.setFbsj(new Date());
		xx.setHdid(Long.parseLong(hdid));
		xx.setSfsq("0");
		dao.save(xx);
		str="1";
		return str;
	}
	
}
