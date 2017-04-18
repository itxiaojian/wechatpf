package com.sliu.framework.app.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.SettingUtil;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysYhDao;
import com.sliu.framework.app.sys.model.SysGjzgl;
import com.sliu.framework.app.sys.model.SysYh;

/**
 * 系统用户管理
 * @author : chenhui
 * @version 创建时间：2015年10月23日 下午3:03:10
 */
@Service
public class SysUserService {

	@Autowired
	private SysYhDao sysYhDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 分页获取列表
	 * @author: chenhui 
	 * @version 创建时间：2016年8月30日  
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getList(Integer start,Integer limit, String yhxm, String yhzh) {
		return sysYhDao.getList(start, limit,yhxm,yhzh);
	}
	
	/**
	 * 角色分配分页获取列用户表
	 * @author:chenhui 
	 * @version 创建时间：2015年10月23日 下午2:59:09 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getYhxxList(Integer start,Integer limit, String code,String jsbh) {
		return sysYhDao.getYhxxList(start, limit,code,jsbh);
	}
	
	/**
	 * 保存
	 * @author: chenhui
	 * @version 创建时间：2015年10月23日 下午2:59:38 
	 * @param sysYh
	 * @return
	 */
	@Transactional
	public String save(SysYh sysYh) {
		sysYh.setPassword(md5PasswordEncoder.encodePassword((String) SettingUtil.getSetting("default.password", null),
				null));
		sysYhDao.save(sysYh);
		return "1";	
	}
	
	/**
	 * 修改
	 * @author: chenhui
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String update(SysYh entity) {
		SysYh sysYh = sysYhDao.get(entity.getYhbh());
		sysYh.setBmbh(entity.getBmbh());
		sysYh.setSjh(entity.getSjh());
		sysYh.setYx(entity.getYx());
		sysYh.setXm(entity.getXm());
		sysYh.setYhzt(entity.getYhzt());
		sysYhDao.update(sysYh);
		return "1";
	}
	
	/**
	 * 删除
	 * @author: chenhui 
	 * @version 创建时间：2015年10月23日 下午3:02:37 
	 * @param id
	 */
	@Transactional
	public void delete(String[] yhbhs) {
		for (String yhbh : yhbhs) {
			sysYhDao.delete(yhbh);
			sysYhDao.deleteYhjs(yhbh);
		}
	}

	/**
	 * 重置密码
	 * @author:chenhui 
	 * @version 创建时间：2016年7月22日 下午4:30:14 
	 * @param yhbhs
	 */
	@Transactional
	public void reset(String[] yhbhs) {
		for (String yhbh : yhbhs) {
			SysYh sysYh = sysYhDao.get(yhbh);
			sysYh.setPassword(md5PasswordEncoder.encodePassword(
					(String) SettingUtil.getSetting("default.password", null),
					null));
			sysYhDao.update(sysYh);
		}
	}
	
	/**
	 * 根据字典种类找出字典
	   @author:chenhui 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zt) {
		return sysYhDao.getDicByLx(zt);
	}

}
