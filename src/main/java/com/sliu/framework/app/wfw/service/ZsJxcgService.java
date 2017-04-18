package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsJxcgDao;
import com.sliu.framework.app.wfw.model.ZsJxcg;

/**
 * 教学成果
 * @author liujiasen
 * @date 2015年6月1日
 */
@Service
public class ZsJxcgService extends BaseBO<ZsJxcg> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsJxcgService.class);

	@Autowired
	private ZsJxcgDao zsJxcgDao;

	/**
	 * 根据页数和标题查询教学成果数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getJxcgList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsJxcgDao.getJxcgList(pages, code);
	}
}
