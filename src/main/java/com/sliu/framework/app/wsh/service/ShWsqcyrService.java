package com.sliu.framework.app.wsh.service;

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
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.dao.ShWsqcyrDao;
import com.sliu.framework.app.wsh.model.ShWsqcyr;

/**
 * 微上墙
 * @author liujiasen
 * @date 2015年7月7日
 */
@Service
public class ShWsqcyrService extends BaseBO<ShWsqcyr>{

	protected Logger logger = LoggerFactory.getServiceLog(ShWsqcyr.class);
	
	@Autowired
	private ShWsqcyrDao dao;

	/**
	 * 保存用户签到信息
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public ResponseData saveCyr(HttpServletRequest request, HttpServletResponse response,String openId) {
		ShWsqcyr cyr=new ShWsqcyr();
		String id=request.getParameter("id");
		//String openId = request.getParameter("openId");
		//openId = "ocFFwuF5bIv_T1ljx1i-Hxprf9dM";
		List<Map<String,Object>> qdYh=dao.getQd(openId,id);
		String cyrid="";
		cyr.setHdid(Long.parseLong(id));
		cyr.setOpenid(openId);
		ResponseData data=null;
		if(openId!=null&&!"".equals(openId)){
			List<Map<String,Object>> wxUser=dao.getWxUser(openId);
			if(wxUser.size()!=0){
				cyr.setCyrxm(wxUser.get(0).get("nickname")+"");
				cyr.setBz((wxUser.get(0).get("headimgurl")+"").trim());
				if(qdYh.size()==0){
					Long cid=dao.save(cyr);
					cyrid=cid+"";
					data=new ResponseData(true, cyrid);
				}else{
					cyrid=qdYh.get(0).get("id")+"";
					data=new ResponseData(true, cyrid);
				}
			}
		}
		return data;
	}
	
}
