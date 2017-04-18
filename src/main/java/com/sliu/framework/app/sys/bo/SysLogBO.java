package com.sliu.framework.app.sys.bo;

import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.sys.model.SysLog;

/**
 * 系统日志 Business
 * 
 * @author lxt
 * @since 2014-11-20 09:40:56
 * 
 */
@Service
public class SysLogBO extends BaseBO<SysLog> {

	protected static Logger logger = LoggerFactory
			.getServiceLog(SysLogBO.class);

}
