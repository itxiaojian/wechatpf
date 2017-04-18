package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsKyxmDao;
import com.sliu.framework.app.wfw.model.ZsKyxm;

/**
 * 科研项目信息
 * @author liujiasen
 * @date 2015年6月3日
 */
@Service
public class ZsKyxmService extends BaseBO<ZsKyxm> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsKyxmService.class);

	@Autowired
	private ZsKyxmDao zsKyxmDao;

	/**
	 * 根据页数和标题查询科研项目数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getKyxmList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsKyxmDao.getKyxmList(pages, code);
	}
}
