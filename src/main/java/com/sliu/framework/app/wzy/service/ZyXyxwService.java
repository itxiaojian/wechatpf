package com.sliu.framework.app.wzy.service;

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
import com.sliu.framework.app.wzy.dao.ZyXyxwDao;
import com.sliu.framework.app.wzy.model.ZyXyxw;

/**
 * 主页学院新闻
 * @author zhangyi
 * @version 创建时间：2015年6月3日  下午4:02:23
 */
@Service
public class ZyXyxwService  extends BaseBO<ZyXyxw>{

	@Autowired
	private ZyXyxwDao zyXyxwDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 分页查询学院新闻
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月11日  
	 * @param start
	 * @param limit
	 * @param xwlx
	 * @param xwbt
	 * @return
	 */
	public Pagination<Map<String, Object>> getXyxwList(Integer start,
			Integer limit, String xwlx, String xwbt) {
		return zyXyxwDao.getXyxwList(start, limit, xwlx, xwbt);
	}
	
	/**
	 * 发布新闻
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:02:30
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveXyxw(ZyXyxw entity) {
		SysYh yh = AppUtil.getCurrentUser();
		entity.setBmbh(yh.getBmbh());
		entity.setFbr(yh.getYhbh());
		entity.setFbrxm(yh.getXm());
		entity.setXwzt("1");
		entity.setSxsj(Calendar.getInstance().getTime());
		zyXyxwDao.save(entity);
		return "1";
	}

	/**
	 * 查看新闻
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:02:47
	 * @param id
	 * @return
	 */
	@Transactional
	public ZyXyxw getXyxwById(Long id) {
		ZyXyxw entity = zyXyxwDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}

   /**
    * 查询列表
    * @author   duanpeijun
    * @version 创建时间：2015年6月3日  下午4:03:03
    * @return
    */
	public List<Map<String, Object>> getXyxwList(){
		
		return zyXyxwDao.getList();
		
	}

	/**
	 * 删除
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月10日 上午11:20:04 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		zyXyxwDao.delete(id);
	}

	/**
	 * 获取主页菜单信息
	 * @author:zhangyi 
	 * @version 创建时间：2016年1月13日 上午11:08:00 
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getListMenu(String mklx) {
		List<Map<String, Object>> list = zyXyxwDao.getListMenu(mklx);
		return list;
	}
	
	/**
	 * 分页查询学院新闻
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月11日  
	 * @param start
	 * @param limit
	 * @param xwlx
	 * @param xwbt
	 * @return
	 */
	public Pagination<Map<String, Object>> getZsxwList(Integer start,
			Integer limit, String xwlx, String xwbt) {
		return zyXyxwDao.getZsxwList(start, limit, xwlx, xwbt);
	}
	
	/**
	 * 获取招生新闻类型
	 * @author:zhangyi 
	 * @version 创建时间：2016年6月15日 下午2:19:29 
	 * @param string
	 * @param i
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getTjcx(String zdmc, Integer zdz){
		return zyXyxwDao.getTjcx(zdmc, zdz);
	}
	
	public List<Map<String, Object>> getBypx(int px, String xwlx) {
		return zyXyxwDao.getBypx(px,xwlx);
	}
	
	/**
	 * 获取发布的招生考试列表
	 * @author  oufeng
	 * @version 2016年06月16日
	 * @param zbm
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getListByZdbm(String zdbm) {
		return zyXyxwDao.getListByZdbm(zdbm);
	}
	
	/**
	 * 获取发布的招生考试详细列表
	 * @author  oufeng
	 * @version 2016年06月16日
	 * @param zbm
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDetailById(String id) {
		return zyXyxwDao.getDetailById(id);
	}
	
	/**
	 * 获取title
	 * @author oufeng
	 * @date 2016年3月9日
	 * @param tplx
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getTitle(String zdbm) {
		return zyXyxwDao.getTitle(zdbm);
	}
}
