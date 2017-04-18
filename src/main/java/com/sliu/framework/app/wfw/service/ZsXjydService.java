//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsXjydDao;
import com.sliu.framework.app.wfw.model.ZsXjyd;

/**
 * 
 * @author liujiasen
 * @date 2015年6月1日
 */
@Service
public class ZsXjydService extends BaseBO<ZsXjyd> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsXjydService.class);

	@Autowired
	private ZsXjydDao zsXjydDao;

	/**
	 * 根据页数和标题查询学籍异动数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getXjydList(HttpServletRequest request,String code){
		String pages=request.getParameter("pages");
//		String code=request.getParameter("code");
		String openId=request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXjydDao.getXjydList(pages, code,openId);
	}
	
	/**
	 * 学籍异动详细 
	 * @author duanpeijun
	 * @date  2015年6月19日
	 * @param filter
	 * @return
	 */
	public List<Map<String,Object>> getXjydDetail(HttpServletRequest request,String id){
		
		return zsXjydDao.getXjydDetail(id);
	}
}
