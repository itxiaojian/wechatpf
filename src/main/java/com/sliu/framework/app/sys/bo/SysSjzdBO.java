package com.sliu.framework.app.sys.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.model.SysSjzd;

/**
 * 数据字典 Business
 * 
 * @author lxt
 * @since 2014-11-06 11:15:04
 * 
 */
@Service
public class SysSjzdBO extends BaseBO<SysSjzd> {

	protected static Logger logger = LoggerFactory
			.getServiceLog(SysSjzdBO.class);

	public Result findSysDictByCode(String code) {
		Result result = new Result();
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_zdbm_S_EQ", code);
		List<SysSjzd> list = this.getAll(filter);
		if (list.size() > 0) {
			result.addErrormsg("字典代码重复");
		}
		return result;
	}

	@Override
	public Result save(SysSjzd sysDict) {
		Result sysDictResult = findSysDictByCode(sysDict.getZdbm());
		if (!sysDictResult.isSuccess()) {
			return sysDictResult;
		}
		Result r = super.save(sysDict);
		return r;
	}

}
