package com.sliu.framework.app.sys.bo;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.model.SysFile;
import com.sliu.framework.app.sys.model.SysFolder;

/**
 * 文件夹 Business
 * 
 * @author lxt
 * @since 2014-11-17 14:01:11
 * 
 */
@Service
public class SysFolderBO extends BaseBO<SysFolder> {

	protected static Logger logger = LoggerFactory
			.getServiceLog(SysFolderBO.class);

	@Override
	public <K extends Serializable> Result remove(K folderId) {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_folderId_S_EQ", folderId);
		SysFile file = dao.getUnique(filter, SysFile.class);
		if (file != null) {
			Result result = new Result();
			result.addErrormsg("文件夹下有文件");
			return result;
		}
		return super.remove(folderId);
	}
}
