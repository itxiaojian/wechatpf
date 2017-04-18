package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wfw.dao.ZsJxzlpjDao;
import com.sliu.framework.app.wfw.dao.ZsPjxxDao;
import com.sliu.framework.app.wfw.model.ZsJxzlpj;
import com.sliu.framework.app.wfw.model.ZsPjxx;

/**
 * 评教信息
 * @author duanpeijun
 * @version 创建时间：2015年6月12日
 */
@Service
public class ZsPjxxService extends BaseBO<ZsPjxx>{

	protected Logger logger = LoggerFactory.getServiceLog(ZsPjxxService.class);

	
	@Autowired
	private ZsJxzlpjDao zsJxzlpjDao;
	
	@Autowired
	private ZsPjxxDao zsPjxxDao;
	
	/**
	 * 根据页数和标题查询评教信息的列表
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getPjxxList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String code=request.getParameter("code");
		String openId=request.getParameter("openId");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsPjxxDao.getPjxxList(pages, code,openId);
	}
	
	/**
	 * 培养方案详细 
	 * @author duanpeijun
	 * @date  2015年6月19日
	 * @param filter
	 * @return
	 */
	public List<Map<String,Object>> getPjxxDetail(HttpServletRequest request,String id){
		
		return zsPjxxDao.getPjxxDetail(id);
	}
		
	/**
	 * 保存教学质量评价
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月15日  
	 * @param entity
	 * @return
	 */
   @Transactional
	public String saveJxzlpj(ZsJxzlpj entity,Long id) {
	   zsJxzlpjDao.save(entity);
		return "1";
	}
   
   /**
    * 判断是否评价过
    * @author duanpeijun
    * @date  2015年6月25日
    * @param id
    * @return
    */
	@Transactional
	public List<Map<String, Object>> getJxzlpjList(Long id) {

		return zsJxzlpjDao.getJxzlpjList(id);

	}
   
   
	
   /**
	 * 分页查询  getPjxx
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:55:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getPjxx(Integer start, Integer limit){
		
		return zsPjxxDao.getPjxx(start, limit);
		
	}
	
	/**
	 * 增加
	 * @author duanpeijun
	 * @date  2015年6月24日
	 * @param entity
	 */
	@Transactional
	public void savePjxx(ZsPjxx entity) {
		zsPjxxDao.save(entity);
	}
	
	/**
	 * 编辑
	 * @author   duanpeijun
	 * @version 2015年6月24日
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(ZsPjxx entity,String id){
		entity.setId(Long.parseLong(id));
		zsPjxxDao.update(entity);
	}
	
	/**
	 * 删除
	 * @author   duanpeijun
	 * @version 2015年6月24日
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
			zsPjxxDao.deletePjxx(ids[i]);
		}
	}
	
}
