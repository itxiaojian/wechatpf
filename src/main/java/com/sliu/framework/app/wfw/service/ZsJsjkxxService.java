package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsJsjkxxDao;
import com.sliu.framework.app.wfw.model.ZsJsjkxx;

/**
 * 教师监考信息
 * @author liujiasen
 * @date 2015年6月3日
 */
@Service
public class ZsJsjkxxService extends BaseBO<ZsJsjkxx> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsJsjkxxService.class);

	@Autowired
	private ZsJsjkxxDao zsJsjkxxDao;

	/**
	 * 根据页数和标题查询教师监考信息
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getJsjkxxList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
		String openId=request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsJsjkxxDao.getJsjkxxList(pages, code, openId);
	}
}
