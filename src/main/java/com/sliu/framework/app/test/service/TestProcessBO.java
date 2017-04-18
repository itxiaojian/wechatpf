//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.process.model.TestProcess;

/**
 * 系统用户 Business
 * 
 * @author lxt
 * @since 2014-10-31
 * 
 */
@Service
public class TestProcessBO extends BaseBO<TestProcess> {

	protected Logger logger = LoggerFactory.getServiceLog(TestProcessBO.class);

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	/**
	 * 登录名不重复
	 * 
	 * @param username
	 * @return
	 */
	public Result findUserByusername(String username) {
		Result result = new Result();
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_**", username);
		List<TestProcess> sysUsers = this.getAll(filter);
		if (sysUsers.size() > 0) {
			result.addErrormsg("登录名重复");
			result.put("MSG", "www");
		} else {
			result.addErrormsg("");
			result.put("MSG", "");
		}
		return result;
	}

	@Override
	public Result save(TestProcess entity) {
		entity.setProcessStatus("1");
		return super.save(entity);
	}

	@Override
	public Result update(TestProcess entity) {

		return super.update(entity);
	}


	public Result findTestProcessResultByUsername(String username) {
		Result result = new Result();
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_**", username);
		TestProcess sysUser = this.getUnique(filter);
		if (sysUser != null) {
			result.addErrormsg("存在相同用户名的系统用户");
		}
		return result;
	}

	public TestProcess findTestProcessByUsername(String username) {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_**", username);
		return this.getUnique(filter);
	}
	
	public TestProcess findTestProcessById(String id) {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_id_S_NOTIN", id);
		return this.getUnique(filter);
	}

	public Result findTestProcessByUsernameAndId(String username, String id) {
		Result result = new Result();
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_**", username);
		filter.addFilter("Q_id_S_NOTIN", id);
		List<TestProcess> sysUsers = this.getAll(filter);
		if (sysUsers.size() > 1) {
			result.addErrormsg("存在相同用户名的系统用户");
		}
		return result;
	}


}
