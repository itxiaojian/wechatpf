package com.sliu.framework.app.wtx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wtx.dao.TxTxxxlsjlDao;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;

/**
 * 提醒信息历史记录
 * @author liujiansen
 * @date 2015年7月29日
 */
@Service
public class TxTxxxlsjlService extends BaseBO<TxTxxxlsjl> {

	@Autowired
	private TxTxxxlsjlDao dao;
	
	/**
	 * 分页查询信息数据
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param start
	 * @param limit
	 * @param txlx 提醒类型
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> pager(Integer start,Integer limit, String txlx) {
		return dao.pager(start, limit, txlx);
	}
	
	/**
	 * 获取字典表中的提醒类型
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getLx(){
		return dao.getLx();
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param id
	 * @return
	 */
	@Transactional
	public TxTxxxlsjl getEntity(Long id){
		return dao.get(id);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param id
	 */
	@Transactional
	public void deleteEntity(Long id) {
		dao.delete(id);
	}
	
	/**
	 * 个人的提醒的历史记录
	 * @author oufeng
	 * @date 2016年8月29日
	 */
	@Transactional
	public List<Map<String,Object>> getTxlsjl(String openId,String pages){
		String yhbh="";
		List<Map<String,Object>> list= dao.getWxyh(openId);
		if(list.size()!=0){
		yhbh = list.get(0).get("yhbh").toString();	
		}
		return dao.getTxlsjl(yhbh,openId,pages);
	}
	
	/**
	 * 更新个人的提醒的状态
	 * @author oufeng
	 * @date 2016年8月29日
	 */
	@Transactional
	public String  updateGrtx(String openId,String lx,String zt){
		if(!"".equals(openId)){
		dao.updateGrtx(openId,lx,zt);
		return "1";
		}
		return "0";
	}
	
	/**
	 * 提醒的类型
	 * @author oufeng
	 * @date 2016年8月29日
	 */
	@Transactional
	public List<Map<String,Object>> getTxlx(String role){
		return dao.getTxlx(role);
	}
	
	/**
	 * 用户的角色
	 * @author oufeng
	 * @date 2016年8月29日
	 */
	@Transactional
	public String getRole(String openId){
		List<Map<String,Object>> list = dao.getRole(openId);
		String role="";
		if(list.size()!=0){
			role=list.get(0).get("jsmc").toString();
		}
		return role;
	}
}
