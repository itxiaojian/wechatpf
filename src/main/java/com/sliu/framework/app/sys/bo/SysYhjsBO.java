//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sys.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.dao.SysYhjsDao;
import com.sliu.framework.app.sys.model.SysYhjs;

/**
 * 系统角色 Business
 * 
 * @author lxt
 * @since 2014-10-31
 * 
 */
@Service
public class SysYhjsBO extends BaseBO<SysYhjs> {

	protected Logger logger = LoggerFactory.getServiceLog(SysYhjsBO.class);
	
	@Autowired
	private SysYhjsDao dao;

	public Result doAnthorize(String userIds, String roleId) {
		Result result = new Result();
		if (userIds != null && !userIds.equals("")) {
			// 1.先删除SysRoleResource
			this.removeSysRoleResource(userIds, roleId);
			// 2.添加SysRoleResource
			return this.addUserRole(userIds, roleId);
		} else {
			return result;
		}
	}

	public Result removeSysRoleResource(String userIds, String roleId) {
		Result result = new Result();
		if (userIds != null && !userIds.equals("")) {
			try {
				String[] userIdsArray = userIds.split(",");
				for (String userId : userIdsArray) {
					removeOneUserRole(roleId, userId);
				}
			} catch (Exception e) {
				result.addErrormsg(e.toString());
				logger.error("removeSysRoleResource", "", e);
				return result;
			}
		}
		return result;

	}

	// 根据roleId删除UserRole
	public void removeAllUserRole(String roleId) {
		// 先删除关联表里面改用户的数据
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_jsbh_STRING_**", roleId);
		List<SysYhjs> sysRoleResList = this.getAll(filter);
		if (sysRoleResList.size() > 0) {
			for (SysYhjs sysUserRole : sysRoleResList) {
				remove(sysUserRole);
			}
		}
	}

	private void removeOneUserRole(String roleId, String userId) {
		// 先删除关联表里面改用户的数据
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_jsbh_S_EQ", roleId);
		filter.addFilter("Q_yhbh_S_EQ", userId);
		List<SysYhjs> sysRoleResList = this.getAll(filter);
		if (sysRoleResList.size() > 0) {
			for (SysYhjs sysUserRole : sysRoleResList) {
				remove(sysUserRole);
			}
		}
	}

	// 新增 UserRole
	private Result addUserRole(String userIds, String roleId) {
		Result result = new Result();
		try {
			String[] userIdsArray = userIds.split(",");
			for (String userId : userIdsArray) {
				SysYhjs entity = new SysYhjs();
				entity.setJsbh(roleId);
				entity.setYhbh(userId);
				this.save(entity);
			}
		} catch (Exception e) {
			result.addErrormsg(e.toString());
			return result;
		}
		return result;
	}

	// 根据roleId 返回UserRole的ids
	public String findUserRoleIds(String roleId) {
		String s = "";
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_jsbh_STRING_**", roleId);
		List<SysYhjs> sysUserRoleList = this.getAll(filter);
		if (sysUserRoleList.size() > 0) {
			for (SysYhjs sysUserRole : sysUserRoleList) {
				s = s + sysUserRole.getYhbh() + ",";
			}
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	/**
	 * 根据RoleID获取用户
	 * @author liujiansen
	 * @date 2016年2月22日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getYhByJs(String roleId) {
		return dao.getYhByJs(roleId);
	}

}
