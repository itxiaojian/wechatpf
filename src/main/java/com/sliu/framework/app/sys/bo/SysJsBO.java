//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sys.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.mapper.SysMapper;
import com.sliu.framework.app.sys.model.SysJs;

/**
 * 系统角色 Business
 * 
 * @author lxt
 * @since 2014-10-31
 * 
 */
@Service
public class SysJsBO extends BaseBO<SysJs> {

	protected Logger logger = LoggerFactory.getServiceLog(SysJsBO.class);

	@Autowired
	private SysMapper sysMapper;

	public SysJs findSysRoleByName(String roleName) {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_jsmc_STRING_**", roleName);
		return this.getUnique(filter);
	}

	public List<Map<String, Object>> listAllRoleResources() {
		return sysMapper.listAllRoleResources();
	}

}
