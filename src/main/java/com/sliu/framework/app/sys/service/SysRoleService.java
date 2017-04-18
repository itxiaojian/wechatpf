package com.sliu.framework.app.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysRoleDao;
import com.sliu.framework.app.sys.model.SysJs;

/**
 * 系统用户管理
 * @author : zhangyi
 * @version 创建时间：2015年10月23日 下午3:03:10
 */
@Service
public class SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 分页获取列表
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午2:59:09 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getList(Integer start,Integer limit, String jsmc, String jsms) {
		return sysRoleDao.getList(start, limit,jsmc,jsms);
	}
	
	/**
	 * 保存
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午2:59:38 
	 * @param sysJs
	 * @return
	 */
	@Transactional
	public String save(SysJs sysJs) {
		sysRoleDao.save(sysJs);
		return "1";	
	}
	
	/**
	 * 修改
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String update(SysJs entity,String newjsmc) {
		SysJs sysJs = sysRoleDao.get(entity.getJsbh());
		sysJs.setJsmc(newjsmc);
		sysJs.setBz(entity.getBz());
		sysJs.setJszt(entity.getJszt());
		
		sysRoleDao.update(sysJs);
		return "1";
	}

	/**
	 * 删除
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:02:37 
	 * @param id
	 */
	@Transactional
	public void delete(String[] jsbhs) {
		for (String jsbh : jsbhs) {
			sysRoleDao.delete(jsbh);
			sysRoleDao.deleteYhjs(jsbh);
		}
	}

	/**
	 * 重置密码
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月22日 下午4:30:14 
	 * @param yhbhs
	 */
	@Transactional
	public void reset(String[] yhbhs) {
		for (String yhbh : yhbhs) {
			SysJs sysJs = sysRoleDao.get(yhbh);
			sysRoleDao.update(sysJs);
		}
	}
	
	/**
	 * 根据名称查找角色
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月25日 上午11:25:22 
	 * @param jsmc
	 * @return
	 */
	public List<Map<String, Object>> findRoleByName(String jsmc) {
		return sysRoleDao.findRoleByName(jsmc);
	}

	/**
	 * 配置角色菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午3:32:04 
	 * @param jsbh
	 * @param cdbhs
	 */
	@Transactional
	public void editJsCd(String jsbh, String[] cdbhs) {
		sysRoleDao.deleteByJsbh(jsbh);
		for (String cdbh : cdbhs) {
			sysRoleDao.addJsCd(cdbh,jsbh);
		}
	}
	
	/**
	 * 配置角色菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午3:32:04 
	 * @param jsbh
	 * @param cdbhs
	 */
	@Transactional
	public void saveUserRole(String jsbh, String[] yhbhs) {
		for (String yhbh : yhbhs) {
			sysRoleDao.saveUserRole(yhbh,jsbh);
		}
	}
	
	/**
	 * 配置角色菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午3:32:04 
	 * @param jsbh
	 * @param cdbhs
	 */
	@Transactional
	public void deleteUserRole(String jsbh, String[] yhbhs) {
		for (String yhbh : yhbhs) {
			sysRoleDao.deleteUserRole(yhbh,jsbh);
		}
	}

	/**
	 * 获取当前角色的菜单值
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午5:10:08 
	 * @return
	 */
	public List<Map<String, Object>> getMyRoleCd() {
		return sysRoleDao.getMyRoleCd();
	}

}
