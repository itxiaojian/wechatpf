package com.sliu.framework.app.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysLogDao;



/**
 * 系统管理--  系统日志
 * @author chenhui
 * @version 创建时间：2016年7月25日
 */
@Service
public class SysLogService extends BaseBO<SysLogDao>{

	@Autowired
	private SysLogDao sysLogDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	
	/**
	 * 分页查询  
	 * @author   chenhui
	 * @version 创建时间：2016年7月25日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getLogList(Integer start, Integer limit){
		
		return sysLogDao.getLogList(start, limit);
		
	}
	
	
	/**
	 *  查看系统日志信息
	 * @author   chenhui
	 * @version 创建时间：2016年7月25日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getLogList(){
		return sysLogDao.getCx();
	}
	
	/**
	 * 删除
	 * @author   chenhui
	 * @version 创建时间：2016年7月25日
	 * @param ids
	 */
	@Transactional
	public void delete(String[] ids) {
		for(int i=0;i<ids.length;i++){
		sysLogDao.delete(ids[i]);
		}
	}
}
