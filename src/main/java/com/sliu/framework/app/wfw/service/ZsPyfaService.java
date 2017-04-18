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
import com.sliu.framework.app.wfw.dao.ZsPyfaDao;
import com.sliu.framework.app.wfw.mapper.WfwMapper;
import com.sliu.framework.app.wfw.model.ZsPyfa;

/**
 * 培养方案
 * @author liujiasen
 * @date 2015年5月29日
 */
@Service
public class ZsPyfaService extends BaseBO<ZsPyfa> {

	protected Logger logger = LoggerFactory.getServiceLog(ZsPyfaService.class);

	@Autowired
	private WfwMapper wfwMapper;
	
	@Autowired
	private ZsPyfaDao zsPyfaDao;

	/**
	 * 根据页数和标题查询培养方案数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getPyfaList(HttpServletRequest request,String code){
		String pages=request.getParameter("pages");
//		String code=request.getParameter("code");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsPyfaDao.getPyfaList(pages, code);
	}
	
	/**
	 * 培养方案详细 
	 * @author duanpeijun
	 * @date  2015年6月19日
	 * @param filter
	 * @return
	 */
	public List<Map<String,Object>> getPyfaDetail(HttpServletRequest request,String id){
		
		return zsPyfaDao.getPyfaDetail(id);
	}
}
