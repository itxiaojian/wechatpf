package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsPksxxDao;
import com.sliu.framework.app.wfw.dao.ZsYktxfxxDao;
import com.sliu.framework.app.wfw.model.ZsPksxx;

/**
 * 贫困生信息
 * @author duanpeijun
 * @version 创建时间：2015年6月9日
 */
@Service
public class ZsPksxxService  extends BaseBO<ZsPksxx>{

	protected Logger logger = LoggerFactory.getServiceLog(ZsPksxx.class);
	
	@Autowired
	private ZsPksxxDao zsPksxxDao;
	@Autowired
	private ZsYktxfxxDao zsYktxfxxDao;
	
	public List<Map<String,Object>> getPksxxList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
		String openId=request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsPksxxDao.getPksxxList(pages, code,openId);
	}
	
	/**
	 * 辅导员查看班级贫困生列表
	 * @author zhangyan
	 * @date  2016年8月11日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getPksyjList(HttpServletRequest request,String code){
		String pages=request.getParameter("pages");
//		String code=request.getParameter("code");
		String openId = request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsPksxxDao.getPksyjList(pages, code,openId);
	}
	
	/**
	 * 根据id查看贫困生信息
	 * @author zhangyan
	 * @date  2016年8月12日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getPksxx(Long id,String openId){
		
		return zsPksxxDao.getPksxx(id,openId);
	}
	
	/**
	 * 根据xh查看学生的晚归考勤信息
	 * @author zhangyan
	 * @date  2016年8月9日
	 * @param xh
	 * @return
	 */
	public List<Map<String,Object>> getXfxx(String xh){
		
		return zsPksxxDao.getXfxx(xh);
	}
}
