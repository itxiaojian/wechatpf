package com.sliu.framework.app.wyx.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wlx.model.LxXgxx;
import com.sliu.framework.app.wyx.dao.WyxYxggDao;
import com.sliu.framework.app.wyx.model.WyxYxgg;

/**
 * 迎新公告
 * @author wangxiangyang
 * @date 2016年8月9日
 */
@Service
public class WyxYxggService extends BaseBO<WyxYxgg>{
	
	@Autowired
	private WyxYxggDao wyxYxggdao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 获取网络计费信息
	 * @author wangxiangyang
	 * @date  2016年8月9日
	 * @return
	 */
	public List<Map<String,Object>> getYxggList(String openId){
		
		return wyxYxggdao.getYxggList(openId);
		
	}
	
	/**
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月9日 
	 * @param id  主键ID
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getYxggxxById1(Long id) {
		return wyxYxggdao.get1(id);
	}
	

	
	/**
	 * 
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月10日  
	 * @param start
	 * @param limit
	 * @param type  信息类型
	 * @param title  信息标题
	 * @return
	 */
	public Pagination<Map<String, Object>> getyxList(Integer start,
			Integer limit, String type, String title){
		return wyxYxggdao.getyxList(start, limit, type, title);
	}
	
	/**
	 * 
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月10日
	 * @param zdmc   字段名称
	 * @param zdz      字段值
	 * @return
	 */
	public List<Map<String, Object>> getTjcx(String zdmc, Integer zdz){
		return wyxYxggdao.getTjcx(zdmc, zdz);
	}
	
	/**
	 * 查看离校相关信息
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月10日 
	 * @param id  主键ID
	 * @return
	 */
	@Transactional
	public WyxYxgg getXyxgxxById(Long id) {
		WyxYxgg esse = wyxYxggdao.get(id);
		if(esse!=null){
			return esse;
		}
		return null;
	}
	
	/**
	 * 修改离校相关信息
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月10日 
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional
	public String updateYxgg(WyxYxgg entity, Long id) {
		WyxYxgg esse = wyxYxggdao.get(id);
		esse.setTitle(entity.getTitle());
		esse.setType(entity.getType());
//		esse.setXwzs(entity.getXwzs());
		esse.setContent(entity.getContent());
		wyxYxggdao.update(esse);
		return "1";
	}
	
	/**
	 * 发布
	 * @author   wangxiangyang
	 * @version 创建时间：2016年8月10日  
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveYxgg(WyxYxgg entity){
		SysYh yh = AppUtil.getCurrentUser();
//		entity.setLy(yh.getLy());
		entity.setAuthor(yh.getXm());
//		entity.setTitle(yh.getTitle());
//		entity.setType(yh.getType());
		entity.setFbtime(Calendar.getInstance().getTime());
		wyxYxggdao.save(entity);
		return "1";
		
	}
	
	/**
	 * 删除
	 * @author   wangxiangyang
	 * @version 创建时间：2015年6月11日  
	 * @param ids
	 */
	@Transactional
	public void deleteYxgg(Long[] ids){
		
		for(int i=0;i<ids.length;i++){
			wyxYxggdao.deleteYxgg(ids[i]);
		}
	}
}
