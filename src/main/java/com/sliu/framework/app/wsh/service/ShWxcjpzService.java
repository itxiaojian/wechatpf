package com.sliu.framework.app.wsh.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.dao.ShWxcjpzDao;
import com.sliu.framework.app.wsh.model.ShWxcjpz;

/**
 * 微信抽奖配置 Service
 *
 * @author            liujiansen
 * @since             2015-06-18
 */

@Service
public class ShWxcjpzService extends BaseBO<ShWxcjpz> {

	protected Logger logger = LoggerFactory.getServiceLog(ShWxcjpz.class);
	
	@Autowired
	private ShWxcjpzDao dao;
	
	/**
	 * 微信抽奖配置列表
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getWxpzList(Integer start,Integer limit,HttpServletRequest request) {
		return dao.getWxpzList(start, limit);
	}
	
	/**
	 * 微信抽奖配置保存
	 * @author liujiansen
	 * @date 2015年6月19日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String savePz(ShWxcjpz entity){
		String str="0";
		List<Map<String,Object>> list=dao.getWxcj();
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				ShWxcjpz pz=dao.get(Long.parseLong(list.get(i).get("id")+""));
				pz.setJssj(AppUtil.getBefCurrentDay(entity.getQssj()));
				dao.update(pz);
			}
			dao.save(entity);
			str="1";
		}
		return str;
	}
	
	/**
	 * 微信抽奖配置修改
	 * @author liujiansen
	 * @date 2015年6月19日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String updatePz(ShWxcjpz entity){
		String str="0";
		dao.update(entity);
		str="1";
		return str;
	}
	
	/**
	 * 删除抽奖配置
	 * @author liujiansen
	 * @date 2015年6月19日
	 * @param id
	 * @param qssj
	 * @return
	 */
	@Transactional
	public String deletePz(String id,String qssj){
		String str="0";
    	dao.delete(Long.parseLong(id));
    	str="1";
    	return str;
	}

	/**
	 * 获取最大的截止时间
	 * @author liujiansen
	 * @date 2015年12月3日
	 * @return
	 */
	public List<Map<String, Object>> getMaxJssj() {
		return dao.getMaxJssj();
	}
}

