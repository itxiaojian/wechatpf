package com.sliu.framework.app.wzy.service;

import java.util.Date;
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
import com.sliu.framework.app.wzy.dao.ZyCdpzDao;
import com.sliu.framework.app.wzy.dao.ZyJscdpzDao;
import com.sliu.framework.app.wzy.model.ZyCdpz;
import com.sliu.framework.app.wzy.model.ZyJscdpz;

@Service
public class ZyCdpzService extends BaseBO<ZyCdpz> {
	
	@Autowired
	private ZyCdpzDao zyCdpzDao;
	
	@Autowired
	private ZyJscdpzDao zyJscdpzDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 删除菜单
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	@Transactional
	public void delete(Long id) {
		 zyCdpzDao.delete(id);
	}
	
	/**
	 * 保存菜单
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	@Transactional
	public String saveCdpz(ZyCdpz zyCdpd) {
		zyCdpd.setTjsj(new Date());
		SysYh user = AppUtil.getCurrentUser();
		if(user != null){
			zyCdpd.setTjr(user.getUsername());
		}
		Long re = zyCdpzDao.save(zyCdpd);
		return "1";
	}
	
	/**
	 * 修改菜单
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	@Transactional
	public String updateCdpz(ZyCdpz entity) {
		ZyCdpz zyCdpt = zyCdpzDao.get(entity.getId());
		zyCdpt.setCdmc(entity.getCdmc());
		zyCdpt.setSfqy(entity.getSfqy());
		zyCdpt.setCdtbmc(entity.getCdtbmc());
		zyCdpt.setCdurl(entity.getCdurl());
		zyCdpt.setPx(entity.getPx());
		zyCdpt.setTjr(entity.getTjr());
//		zyCdpt.setMc(entity.getMc());
		zyCdpt.setTjsj(entity.getTjsj());
		zyCdpzDao.update(zyCdpt);
		return "1";
	}
	
	/**
	 *查询菜单
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getList(Integer start, Integer limit) {
		return zyCdpzDao.getList(start, limit,"");
	}
	
	/**
	 *查询角色列表
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getJsList(Integer start, Integer limit) {
		return zyCdpzDao.getJsList(start, limit,"");
	}
	
	/**
	 *查询分类
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getAllList(String mxlx,Integer start, Integer limit) {
		return zyCdpzDao.getList(start, limit,mxlx);
	}
	
	/**
	 *查询分类角色菜单
	 * @author:zhangyi
	 * @version 创建时间：2016年1月12日  
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getAllJsList(String mxlx,String jsmc,Integer start, Integer limit) {
		return zyCdpzDao.getJsCdList(mxlx,jsmc,start, limit);
	}
	
	/**
	 * 根据字典种类找出字典
	   @author:zhangyi 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return zyCdpzDao.getDicByLx(zdzl);
	}
	
	/**
	 * 根据id查看菜单信息
	 * @author:zhangyi 
	 * @version 创建时间：2016年1月12日  
	 * @param id
	 * @return
	 */
	@Transactional
	public ZyCdpz getCdpzById(long id) {
		ZyCdpz entity = zyCdpzDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}
	
	/**
	 * 配置菜单功能
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月7日 下午1:29:59 
	 * @param jsmc
	 * @param cdid
	 */
	@Transactional
	public void upCd(String jsmc, Integer cdid) {
		ZyJscdpz zyJscdpz = new ZyJscdpz();
		zyJscdpz.setJsmc(jsmc);
		zyJscdpz.setCdid(cdid);
		zyJscdpzDao.save(zyJscdpz);
	}
	
	/**
	 * 取消配置菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月7日 下午1:37:13 
	 * @param pzid
	 */
	@Transactional
	public void downCd(Integer pzid) {
		zyJscdpzDao.delete(pzid);
	}

}
