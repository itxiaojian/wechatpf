package com.sliu.framework.app.wsh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wfw.mapper.WfwMapper;
import com.sliu.framework.app.wsh.dao.ShXcskDao;
import com.sliu.framework.app.wsh.model.ShXcsk;

/**
 * 校车时刻
  * @author  oufeng
  * @version 创建时间：2015年6月16日  下午13:49:44
 */
@Service
public class ShXcskService extends BaseBO<ShXcskDao>{

	protected Logger logger = LoggerFactory.getServiceLog(ShXcsk.class);
	
	@Autowired
	private WfwMapper wfwMapper;
	
	@Autowired
	private ShXcskDao shXcskDao;
	
	/**
	 * 
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44 
	 * @param filter
	 * @return
	 */
	public Pager listAllXcsk(QueryFilter filter){
		return wfwMapper.listAllXcsk(filter);
	}
	
	public Pagination<Map<String, Object>> getXcskList(Integer start, Integer limit){
		
		return shXcskDao.getXcskList(start, limit);
		
	}
	
	/**
	 *  校车时刻查询（按车牌号查询）
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @return
	 */
	@Transactional
   public Pagination<Map<String, Object>> getXcskListByCph(Integer start, Integer limit,String cph){
		
		return shXcskDao.getCph(start,limit,cph);
		
	}
	
	/**
	 *  查看电话黄页信息
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getXcskList(){
		return shXcskDao.getCx();
	}
	
	/**
	 * 增加
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param entity  
	 */
	@Transactional
	public void save(ShXcsk entity) {
		
		shXcskDao.save(entity);
	}
	
	/**
	 * 编辑
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(ShXcsk entity,String id){
		entity.setId(Long.parseLong(id));
		shXcskDao.update(entity);
	}
	
	/**
	 * 删除
	 * @author  oufeng
	 * @version 创建时间：2015年6月16日  下午13:49:44
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
			shXcskDao.delete(ids[i]);
		}
	}
}
