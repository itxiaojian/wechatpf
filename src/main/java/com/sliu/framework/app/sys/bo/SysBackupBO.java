package com.sliu.framework.app.sys.bo;

import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.sys.model.SysBackup;

/**
 * sys_backup Business
 * 
 * @author lxt
 * @since 2014-12-08 10:35:36
 * 
 */
@Service
public class SysBackupBO extends BaseBO<SysBackup> {

	protected static Logger logger = LoggerFactory
			.getServiceLog(SysBackupBO.class);

}
