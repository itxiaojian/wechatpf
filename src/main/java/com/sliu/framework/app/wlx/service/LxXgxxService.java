package com.sliu.framework.app.wlx.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wlx.dao.LxXgxxDao;
import com.sliu.framework.app.wlx.model.LxXgxx;

/**
 * 离校相关信息
 * @author zhangyan
 * @version 创建时间：2015年6月6日  
 */
@Service
public class LxXgxxService extends BaseBO<LxXgxx>{

	@Autowired
	private LxXgxxDao lxXgxxDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 
	 * @author   zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @param start
	 * @param limit
	 * @param type  信息类型
	 * @param title  信息标题
	 * @return
	 */
	public Pagination<Map<String, Object>> getXyxgxxList(Integer start,
			Integer limit, String type, String title){
		return lxXgxxDao.getXyxgxxList(start, limit, type, title);
	}
	
	/**
	 * 
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:59:25
	 * @param zdmc   字段名称
	 * @param zdz      字段值
	 * @return
	 */
	public List<Map<String, Object>> getTjcx(String zdmc, Integer zdz){
		return lxXgxxDao.getTjcx(zdmc, zdz);
	}
	 
	/**
	 * 发布
	 * @author   zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveLyxgxx(LxXgxx entity){
		SysYh yh = AppUtil.getCurrentUser();
//		entity.setLy(yh.getLy());
		entity.setAuthor(yh.getXm());
//		entity.setTitle(yh.getTitle());
//		entity.setType(yh.getType());
		entity.setFbtime(Calendar.getInstance().getTime());
		lxXgxxDao.save(entity);
		return "1";
		
	}
		
	
	/**
	 * 查看离校相关信息
	 * @author   zhangyan
	 * @version 创建时间：2016年6月6日 
	 * @param id  主键ID
	 * @return
	 */
	@Transactional
	public LxXgxx getXyxgxxById(Long id) {
		LxXgxx esse = lxXgxxDao.get(id);
		if(esse!=null){
			return esse;
		}
		return null;
	}
	
	/**
	 * 分类查询页面跳转：学院简介，学院风光，办事流程等。。。
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月8日  下午4:14:16
	 * @param zdbm   字典编码
	 * @param zl   种类
	 * @return
	 */
	public List<Map<String, Object>> getXyjj( String type){
		   return lxXgxxDao.getXyjj(type);
	}
	
	/**
	 * 删除学院相关信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月11日  
	 * @param ids
	 */
	@Transactional
	public void deleteXyxgxx(Long[] ids){
		
		for(int i=0;i<ids.length;i++){
			lxXgxxDao.deleteXyxgxx(ids[i]);
		}
	}

	/**
	 * 修改离校相关信息
	 * @author:zhangyan 
	 * @version 创建时间：2016年6月6日 
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional
	public String updateLxxgxx(LxXgxx entity, Long id) {
		LxXgxx esse = lxXgxxDao.get(id);
		esse.setTitle(entity.getTitle());
		esse.setType(entity.getType());
//		esse.setXwzs(entity.getXwzs());
		esse.setContent(entity.getContent());
		lxXgxxDao.update(esse);
		return "1";
	}

	
	
}

