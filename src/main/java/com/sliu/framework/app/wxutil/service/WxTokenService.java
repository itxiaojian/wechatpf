package com.sliu.framework.app.wxutil.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wxutil.dao.WxTokenDao;
import com.sliu.framework.app.wxutil.model.WxToken;

/**
 * 存储微信token值
 * @author : zhangyi
 * @version 创建时间：2016年8月3日 下午4:15:03
 */
@Service
public class WxTokenService {
	
	@Autowired
	private WxTokenDao wxTokenDao;
	
	/**
	 * 分页查询  getThokenList
	 * @author   chenhui
	 * @version 创建时间：2015年6月3日  下午3:55:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTokenList(Integer start, Integer limit){
		
		return wxTokenDao.getTokenList(start, limit);
		
	}
	
	/**
	 * 获取token
	 * @author:zhangyi
	 * @version 创建时间：2016年8月3日 下午4:40:48
	 * @return
	 */
	@Transactional
	public WxToken get(){
		@SuppressWarnings("unchecked")
		List<WxToken> list = wxTokenDao.findByHQL("from WxToken t ");
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 保存token值
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月3日 下午4:52:43 
	 * @param token
	 */ 
	@Transactional
	public void save(WxToken token){
		wxTokenDao.save(token);
	}
	
	/**
	 * 修改token值
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月3日 下午4:52:43 
	 * @param token
	 */
	@Transactional
	public void update(WxToken token){
		wxTokenDao.update(token);
	}
	
	/**
	 * 删除
	 * @author   chenhui
	 * @version 创建时间：2015年6月3日  下午3:57:36
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
		wxTokenDao.delete(ids[i]);
		}
	}
	
			
}
