package com.sliu.framework.app.wsh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wsh.dao.ShXyxxDao;
import com.sliu.framework.app.wsh.model.ShXyxx;

/**
 * 校友信息
 * @author duanpeijun
 * @date  2015年7月7日
 */
@Service
public class ShXyxxService extends BaseBO<ShXyxx>{

	protected Logger logger = LoggerFactory.getServiceLog(ShXyxx.class);
	
	@Autowired
	private ShXyxxDao shXyxxDao;
	
	/**
	 * 校友信息查询
	 * @author duanpeijun
	 * @date  2015年7月7日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getXyxxList(HttpServletRequest request,String code){
		String pages=request.getParameter("pages");
//		String code=request.getParameter("code");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return shXyxxDao.getXyxxList(pages, code);
	}
}
