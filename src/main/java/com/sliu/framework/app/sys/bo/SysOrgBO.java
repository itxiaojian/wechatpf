//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sys.bo;

import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.sys.model.SysOrg;

/**
 * 组织机构 Business
 * 
 * @author lxt
 * @since 2014-10-31
 * 
 */
@Service
public class SysOrgBO extends BaseBO<SysOrg> {

	protected Logger logger = LoggerFactory.getServiceLog(SysOrgBO.class);

}
