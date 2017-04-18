package com.sliu.framework.app.wsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wsh.dao.ShWbmYbmrDao;
import com.sliu.framework.app.wsh.model.ShWbmybmr;

/**
 * 微报名
 * @author liujiansen
 * @date 2015年6月24日
 */
@Service
public class ShWbmYbmrService extends BaseBO<ShWbmybmr>{

	protected Logger logger = LoggerFactory.getServiceLog(ShWbmybmr.class);
	
	@Autowired
	private ShWbmYbmrDao dao;
	
	/**
	 * 根据报名编号获取报名人数
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 * @return
	 */
	@Transactional
	public int getCount(String bmid){
		return dao.getCount(bmid);
	}
}
