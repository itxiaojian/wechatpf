package com.sliu.framework.app.sys.service;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.SettingUtil;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysAdpzDao;
import com.sliu.framework.app.sys.model.SysAdpz;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.PasswordUtil;

/**
 * ad服务器配置
 * @author : zhangyi
 * @version 创建时间：2015年10月23日 下午3:03:10
 */
@Service
public class SysAdpzService {

	@Autowired
	private SysAdpzDao sysAdpzDao;
	
	/**
	 * 分页获取列表
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午2:59:09 
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional
	public Pagination<Object> getList(Integer start,Integer limit) {
		return sysAdpzDao.getList(start, limit);
	}
	
	/**
	 * 保存
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午2:59:38 
	 * @param sysAdpz
	 * @return
	 */
	@Transactional
	public String save(SysAdpz sysAdpz) {
		sysAdpz.setXgsj(new Date());
		SysYh yh = AppUtil.getCurrentUser();
		if(yh != null){
			sysAdpz.setXgr(yh.getYhbh());
		}
		String pass = PasswordUtil.encode(sysAdpz.getTbmm());
		sysAdpz.setTbmm(pass);
		sysAdpzDao.save(sysAdpz);
		return "1";	
	}
	
	/**
	 * 修改
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String update(SysAdpz entity) {
		SysAdpz sysAdpz = sysAdpzDao.get(entity.getId());
		sysAdpz.setDkh(entity.getDkh());
		sysAdpz.setFwqdz(entity.getFwqdz());
		sysAdpz.setTbyhm(entity.getTbyhm());
		String pass = PasswordUtil.encode(entity.getTbmm());
		sysAdpz.setTbmm(pass);
		sysAdpz.setXgsj(new Date());
		sysAdpz.setDlqz(entity.getDlqz());
		sysAdpz.setYjd(entity.getYjd());
		SysYh yh = AppUtil.getCurrentUser();
		if(yh != null){
			sysAdpz.setXgr(yh.getYhbh());
		}
		sysAdpzDao.update(sysAdpz);
		return "1";
	}

	/**
	 * 删除
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午3:02:37 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		sysAdpzDao.delete(id);
	}

	/**
	 * 测试连接
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月27日 下午3:49:34 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public String testConnect(SysAdpz entity) {
		/***************************** 获取ad域用户信息begin *******************************/
		String host = ""; // AD服务器
		String port = ""; // 端口
		String username = ""; // 用户名
		String password = ""; // 密码
		String domain = ""; //登录前缀
		String searchBase = "";//域节点
		//获取ad服务器配置信息
		if (entity!=null) {
			host = entity.getFwqdz();
			port = entity.getDkh();
			username = entity.getTbyhm();
			password = entity.getTbmm();
			domain = entity.getDlqz();
			searchBase = entity.getYjd();
			String url = new String("ldap://" + host + ":" + port);
			@SuppressWarnings("rawtypes")
			Hashtable HashEnv = new Hashtable();
			String adminName = domain+"\\" + username; // 注意用户名的写法：domain\User
			String adminPassword = password; // 密码
			HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别
			HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); // AD User
			HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); // AD // Password
			HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
			HashEnv.put(Context.PROVIDER_URL, url);
			try {
	            LdapContext ctx = new InitialLdapContext(HashEnv, null);
	            String searchFilter = "objectClass=User";
	            SearchControls searchCtls = new SearchControls(); // Create the
	            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify
	            String returnedAtts[] = { "memberOf","url", "whenChanged", "employeeID",
	            "name", "userPrincipalName", "physicalDeliveryOfficeName",
	            "departmentNumber", "telephoneNumber", "homePhone",
	            "mobile", "department", "sAMAccountName", "whenChanged",
	            "mail","memberOf", "distinguishedName", "Pwd-Last-Set", "User-Password", "cn" }; // 定制返回属性
	            searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
	            @SuppressWarnings("rawtypes")
				NamingEnumeration answer = ctx.search(searchBase, searchFilter,
	                    searchCtls);// Search for objects using the filter
	            ctx.close();
	            return "1";
	        } catch (NamingException e) {
//	            e.printStackTrace();
//	            System.err.println("Throw Exception : " + e);
	            return "0";
	        }
			/***************************** 获取ad域用户信息end *******************************/
		}
		return "0";

	}
}
