//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sys.bo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.SettingUtil;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.dao.SysYhDao;
import com.sliu.framework.app.sys.mapper.SysMapper;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.sys.model.SysZzjg;
import com.sliu.framework.app.util.AppUtil;

/**
 * 系统用户 Business
 * 
 * @author lxt
 * @since 2014-10-31
 * 
 */
@Service
public class SysYhBO extends BaseBO<SysYh> {

	protected Logger logger = LoggerFactory.getServiceLog(SysYhBO.class);

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@Autowired
	private SysZzjgBO zzjgBO;
	
	@Autowired
	private SysMapper sysMapper;
	
	@Autowired
	private SysWxyhDao dao; 
	
	@Autowired
	private SysYhDao sysYhDao;
	
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
		List<SysYh> SysYhs = this.getAll(filter);
		if (SysYhs.size() > 0) {
			result.addErrormsg("登录名重复");
			result.put("MSG", "www");
		} else {
			result.addErrormsg("");
			result.put("MSG", "");
		}
		return result;
	}

	@Override
	public Result save(SysYh entity) {
//		entity.setPassword(md5PasswordEncoder.encodePassword(
//				(String) SettingUtil.getSetting("default.password", null),
//				entity.getUsername()));
		entity.setPassword(md5PasswordEncoder.encodePassword(
				(String) SettingUtil.getSetting("default.password", null),
				null));
		return super.save(entity);
	}

	@Override
	public Result update(SysYh entity) {
//		entity.setPassword(md5PasswordEncoder.encodePassword(
//				(String) SettingUtil.getSetting("default.password", null),
//				entity.getUsername()));
		entity.setPassword(md5PasswordEncoder.encodePassword(
				(String) SettingUtil.getSetting("default.password", null),
				null));
		String yhbh=entity.getYhbh();
		String gwbh=dao.getGwbh(yhbh).get(0).get("gwbh")+"";
		if(entity.getYhzt()!=1){
			this.updateWxyh(yhbh);
		}
		entity.setGwbh(gwbh);
		return super.update(entity);
	}

	public void updateWxyh(String yhbh){
		List<Map<String,Object>> bdyh=dao.getOpenId(yhbh);
		if(bdyh.size()!=0){
			for(int i=0;i<bdyh.size();i++){
				dao.update(bdyh.get(i).get("id")+"","2");
			}
		}
	}
	
	/**
	 * 密码重置
	 * 
	 * @param id
	 * @return
	 */
	public SysYh updateDefaultPassword(String id) {
		SysYh su = (SysYh) get(id);
		su.setPassword(md5PasswordEncoder.encodePassword(
				(String) SettingUtil.getSetting("default.password", null),
				null));
//		su.setPassword(md5PasswordEncoder.encodePassword(
//				(String) SettingUtil.getSetting("default.password", null),
//				su.getUsername()));
		merge(su);
		return su;
	}

	/**
	 * 修改密码
	 * 
	 * @param pwd
	 * @param newPwd
	 * @return
	 */
	public Result updateSelfPassword(String pwd, String newPwd) {
		Result result = new Result();
		SysYh entity = (SysYh) super.get(AppUtil.getCurrentUser().getYhbh());
		if (!StringUtils.equals(entity.getPassword(),
//				md5PasswordEncoder.encodePassword(pwd, entity.getUsername()))) {
				md5PasswordEncoder.encodePassword(pwd, null))) {
			result.addErrormsg("当前密码不正确");
			return result;
		}
		entity.setPassword(md5PasswordEncoder.encodePassword(newPwd,
				null));
//		entity.setPassword(md5PasswordEncoder.encodePassword(newPwd,
//				entity.getUsername()));
		merge(entity);
		result.addErrormsg("密码修改成功");
		return result;
	}

	public Result changePassword(String oldPwd, String newPwd, String newPwd2) {
		Result result = new Result();
		SysYh SysYh = get(AppUtil.getCurrentUser().getYhbh());
		String s1 = SysYh.getPassword();
		String s2 = md5PasswordEncoder.encodePassword(oldPwd,
				null);
//		String s2 = md5PasswordEncoder.encodePassword(oldPwd,
//				SysYh.getUsername());
		String s3 = md5PasswordEncoder.encodePassword(newPwd,
				null);
//		String s3 = md5PasswordEncoder.encodePassword(newPwd,
//				SysYh.getUsername());
		if (!s1.equals(s2)) {
			result.addErrormsg("当前密码输入不正确！");
			return result;
		}

		if ("".equals(newPwd)) {
			result.addErrormsg("请输入新密码！");
			return result;
		}
		if (oldPwd.equals(newPwd)) {
			result.addErrormsg("新密码和旧密码一致,修改密码失败");
			return result;
		}

		if (!newPwd.equals(newPwd2)) {
			result.addErrormsg("两次新密码输入不一致，修改密码失败");
			return result;
		} else {

		}
		SysYh.setPassword(s3);

		return merge(SysYh);
	}

	public Result findSysYhResultByUsername(String username) {
		Result result = new Result();
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_**", username);
		SysYh SysYh = this.getUnique(filter);
		if (SysYh != null) {
			result.addErrormsg("存在相同用户名的系统用户");
		}
		return result;
	}

	public SysYh findSysYhByUsername(String username) {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_**", username);
		return this.getUnique(filter);
	}

	public Result findSysYhByUsernameAndId(String username, String id) {
		Result result = new Result();
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_**", username);
		filter.addFilter("Q_yhbh_S_NOTIN", id);
		List<SysYh> SysYhs = this.getAll(filter);
		if (SysYhs.size() > 1) {
			result.addErrormsg("存在相同用户名的系统用户");
		}
		return result;
	}
	
	public List<SysZzjg> getBm(){
		QueryFilter filter=new QueryFilter();
		filter.addFilter("Q_jb_S_EQ", 2);
		List<SysZzjg> zzjg = zzjgBO.getAll(filter);
		return zzjg;
	}
	
	public List<SysZzjg> getGw(String sjbh){
		QueryFilter filter=new QueryFilter();
		filter.addFilter("Q_sjbh_S_EQ", sjbh);
		filter.addFilter("Q_jb_S_EQ", 3);
		List<SysZzjg> zzjg = zzjgBO.getAll(filter);
		return zzjg;
	}
	
	/*public String getGw(String sjbh){
		if(!"".equals(sjbh)&&sjbh!=null){
			QueryFilter filter=new QueryFilter();
			filter.addFilter("Q_sjbh_S_EQ", sjbh);
			filter.addFilter("Q_jb_S_EQ", 3);
			List<SysZzjg> zzjg = zzjgBO.getAll(filter);
			StringBuffer sysAreaJson = new StringBuffer();
			sysAreaJson.append("[");
			if(zzjg.size()>0){
				for (int i = 0; i < zzjg.size(); i++) {
					if (i < zzjg.size() - 1) {
						sysAreaJson.append("{\"id\":\"" + zzjg.get(i).getBmbh()
								+ "\",\"name\":\"" + zzjg.get(i).getBmmc()
								+ "\"},");
					}else{
						sysAreaJson.append("{\"id\":\"" + zzjg.get(i).getBmbh()
								+ "\",\"name\":\"" + zzjg.get(i).getBmmc()
								+ "\"}");
					}
				}
			}
			sysAreaJson.append("]");
			return sysAreaJson.toString();
		}else{
			return null;
		}
	}*/
	
	public Pager listAllUser(QueryFilter filter){
		return sysMapper.listAllUser(filter);
	}
	
	public Pager listAllUserAtRole(QueryFilter filter){
		return sysMapper.listAllUserAtRole(filter);
	}
	
	public List<Map<String, Object>> listUser(QueryFilter filter){
		return sysMapper.listUser(filter);
	}
	

	@Transactional
	public List<Map<String, Object>> getUserByJgId(String jgId) {
		return sysYhDao.getUserByJgId(jgId);
	}

	/**
	 * 获取已经绑定的用户
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月5日 上午11:05:48 
	 * @param jgId
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getWxUserByJgId(String jgId) {
		return sysYhDao.getWxUserByJgId(jgId);
	}
}
