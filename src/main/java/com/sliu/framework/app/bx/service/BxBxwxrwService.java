package com.sliu.framework.app.bx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.bx.dao.BxBxsqDao;
import com.sliu.framework.app.bx.dao.BxBxwxrwDao;
import com.sliu.framework.app.bx.model.BxBxwxrw;


/**
*报修维修任务表
@Author oufeng	
@Date 2016年11月2日 下午8:40:26
@Version 1.0
*/
@Service
public class BxBxwxrwService extends BaseBO<BxBxwxrw> {
	
	@Autowired
	private BxBxwxrwDao dao;
	
	@Autowired
	private BxBxsqDao sqDao;
	
	
	/**
	 * 获得任务
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getRw(String openId,String page){
		List<Map<String,Object>> list = sqDao.getUser(openId);
		String yhbh="";
		if(list.size()>0){
			yhbh=list.get(0).get("yhid").toString();
		}
		return dao.getRw(yhbh,page);
	}
	
	/**
	 * 获得任务
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public  String  delete(String id){
		dao.delete(id);
		return "1";
	}
}
