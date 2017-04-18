package com.sliu.framework.app.wzy.service;

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
import com.sliu.framework.app.wzy.dao.ZyXyxgxxDao;
import com.sliu.framework.app.wzy.model.ZyXyxgxx;

/**
 * 主页学院相关信息
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:59:04
 */
@Service
public class ZyXyxgxxService extends BaseBO<ZyXyxgxx>{

	@Autowired
	private ZyXyxgxxDao zyXyxgxxDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:59:08
	 * @param start
	 * @param limit
	 * @param xwlx  信息类型
	 * @param xwbt  信息标题
	 * @return
	 */
	public Pagination<Map<String, Object>> getXyxgxxList(Integer start,
			Integer limit, String xwlx, String xwbt){
		return zyXyxgxxDao.getXyxgxxList(start, limit, xwlx, xwbt);
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
		return zyXyxgxxDao.getTjcx(zdmc, zdz);
	}
	 
	/**
	 * 发布
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:59:42
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveXyxgxx(ZyXyxgxx entity){
		SysYh yh = AppUtil.getCurrentUser();
		entity.setBmbh(yh.getBmbh());
		entity.setFbr(yh.getYhbh());
		entity.setFbrxm(yh.getXm());
		entity.setXwzt("1");
		entity.setSxsj(Calendar.getInstance().getTime());
		zyXyxgxxDao.save(entity);
		return "1";
	}
		
	
	/**
	 * 查看学院相关信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:59:54
	 * @param id  主键ID
	 * @return
	 */
	@Transactional
	public ZyXyxgxx getXyxgxxById(Long id) {
		ZyXyxgxx esse = zyXyxgxxDao.get(id);
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
	public List<Map<String, Object>> getXyjj( String zdbm){
		   return zyXyxgxxDao.getXyjj(zdbm);
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
			zyXyxgxxDao.deleteXyxgxx(ids[i]);
		}
	}

	/**
	 * 修改学院相关信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年12月15日 上午11:26:57 
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional
	public String updateXyxgxx(ZyXyxgxx entity, Long id) {
		ZyXyxgxx esse = zyXyxgxxDao.get(id);
		esse.setXwbt(entity.getXwbt());
		esse.setXwlx(entity.getXwlx());
		esse.setXwzs(entity.getXwzs());
		esse.setXwnr(entity.getXwnr());
		zyXyxgxxDao.update(esse);
		return "1";
	}
	
	
}
