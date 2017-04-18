package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsXsrzxxDao;
import com.sliu.framework.app.wfw.dao.ZsXswgkqxxDao;
import com.sliu.framework.app.wfw.model.ZsXsrzxx;

/**
 * 学生入住信息
 * @author zhangyan
 * @date 2016年8月9日
 */
@Service
public class ZsXsrzxxService extends BaseBO<ZsXsrzxx> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsXsrzxxService.class);

	@Autowired
	private ZsXsrzxxDao zsXsrzxxDao;
	@Autowired
	private ZsXswgkqxxDao zsXswgkqxxDao;
	
	/**
	 * 辅导员查看班级学生列表
	 * @author zhangyan
	 * @date  2016年8月9日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getXsrzxxList(HttpServletRequest request,String code,String bjmc){
		String pages=request.getParameter("pages");
//		String code=request.getParameter("code");
		String openId = request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsrzxxDao.getXsrzxxList(pages, code,bjmc,openId);
	}
	
	/**
	 * 获取辅导员所带的班级
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @return
	 */
	public List<Map<String,Object>> getBj(String openId){
		
		return zsXsrzxxDao.getBj(openId);
	}
	
	/**
	 * 根据id查看学生的详细入住信息
	 * @author zhangyan
	 * @date  2016年8月9日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getXsrzxxDetail(Long id,String openId){
		
		return zsXsrzxxDao.getXsrzxxDetail(id,openId);
	}
	
	/**
	 * 根据xh查看学生的晚归考勤信息
	 * @author zhangyan
	 * @date  2016年8月9日
	 * @param xh
	 * @return
	 */
	public List<Map<String,Object>> getXskqxxDetail(String xh){
		
		return zsXswgkqxxDao.getXskqxxDetail(xh);
	}
	
	
	
}