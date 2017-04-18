//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sys.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.model.SysJscd;

/**
 * 系统角色 Business
 * 
 * @author lxt
 * @since 2014-10-31
 * 
 */
@Service
public class SysJscdBO extends BaseBO<SysJscd> {

	protected Logger logger = LoggerFactory
			.getServiceLog(SysJscdBO.class);

	public Result doSubmitResource(String resourceIds, String roleId) {
		// 1.先删除SysRoleResource
		removeRoleResource(roleId);
		// 2.添加SysRoleResource
		return this.addRoleResource(resourceIds, roleId);
	}

	// 根据roleId删除SysRoleResource
	public void removeRoleResource(String roleId) {
		// 先删除关联表里面改用户的数据
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_jsbh_STRING_**", roleId);
		List<SysJscd> sysRoleResList = this.getAll(filter);
		if (sysRoleResList.size() > 0) {
			for (SysJscd sysRoleRes : sysRoleResList) {
				remove(sysRoleRes);
			}
		}
	}

	// 新增 RoleResource
	public Result addRoleResource(String resourceIds, String roleId) {
		Result result = new Result();
		try {
			String[] resourceIdsArray = resourceIds.split(",");
			for (String resourceId : resourceIdsArray) {
				SysJscd entity = new SysJscd();
				entity.setJsbh(roleId);
				entity.setCdbh(resourceId);
				this.save(entity);
			}
		} catch (Exception e) {
			result.addErrormsg(e.toString());
			return result;
		}
		return result;
	}

	public String findRoleResourceIds(String roleId) {
		String s = "";
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_jsbh_STRING_**", roleId);
		List<SysJscd> sysRoleResList = this.getAll(filter);
		if (sysRoleResList.size() > 0) {
			for (SysJscd sysRoleRes : sysRoleResList) {
				s = s + sysRoleRes.getCdbh() + ",";
			}
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

}
