package com.sliu.framework.app.tssj.service;

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
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.SettingUtil;
import com.sliu.framework.app.sys.dao.SysAreaDao;
import com.sliu.framework.app.sys.dao.SysYhDao;
import com.sliu.framework.app.sys.dao.SysYhjsDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.sys.model.SysYhjs;
import com.sliu.framework.app.sys.model.SysZzjg;
import com.sliu.framework.app.tssj.dao.TjsjDao;
import com.sliu.framework.app.util.PasswordUtil;

/**
 * @author:zhangyi
 * @version 创建时间：2015年9月2日 下午4:37:52 类说明
 */
@Service
public class TjsjService {

	@Autowired
	private TjsjDao tjsjDao;

	@Autowired
	private SysYhDao sysYhDao;

	@Autowired
	private SysYhjsDao sysYhjsDao;

	@Autowired
	private SysAreaDao zzjgDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 定时同步用户信息
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月7日 上午9:14:20
	 */
	public void timerSaveTssj(){
		this.saveTssj("1");
	}

	/**
	 * 保存推送数据列表
	 * 
	 * @author:zhangyi
	 * @version 创建时间：2015年9月2日 下午4:58:57
	 * @return
	 */
	@Transactional
	public void saveTssj(String type) {
		if ("0".equals(type)) {// 全量
			// 获取新用户
			List<Map<String, Object>> list = tjsjDao.getTssj();
			if (list.size() != 0) {
				// 删除用户角色信息
				tjsjDao.deleteYhjs();
				// 删除用户信息
				tjsjDao.deleteYh();
			}
			// 获取新机构
			List<Map<String, Object>> xbm = tjsjDao.getBm();
			if (xbm.size() != 0) {
				// 删除部门
				tjsjDao.deleteBm();
			}
			for (int i = 0; i < xbm.size(); i++) {
				SysZzjg jg = new SysZzjg();
				jg.setBmbh(xbm.get(i).get("bmdm").toString());
				jg.setBmmc(xbm.get(i).get("bmmc").toString());
				jg.setBz(xbm.get(i).get("bmmc").toString());
				jg.setId(xbm.get(i).get("bmdm").toString());
				jg.setJb(Integer.parseInt(xbm.get(i).get("jb").toString()));
				if (xbm.get(i).get("jb").toString().equals("1")) {
					jg.setSjbh("0");
					jg.setPx(0);
				} else {
					jg.setSjbh(xbm.get(i).get("sjbmdm").toString());
					jg.setPx(1);
				}
				// 保存机构
				zzjgDao.save(jg);
			}
			for (int i = 0; i < list.size(); i++) {
				SysYh yh = new SysYh();
				yh.setYhbh(list.get(i).get("yhbh").toString());
				yh.setXm(list.get(i).get("xm").toString());
				yh.setUsername(list.get(i).get("dlm").toString());
				if (list.get(i).get("mm") != null) {
					yh.setPassword(list.get(i).get("mm").toString());
				} else {
					yh.setPassword(md5PasswordEncoder.encodePassword(
							(String) SettingUtil.getSetting("default.password",
									null), null));
				}
				yh.setBmbh(list.get(i).get("bmbh").toString());
				yh.setSjh("");
				if (list.get(i).get("zt") != null) {
					yh.setYhzt(Integer.parseInt(list.get(i).get("zt").toString()));
				} else {
					yh.setYhzt(1);
				}
				// 保存新用户
				tjsjDao.saveYh(yh);
				SysYhjs js = new SysYhjs();
				List<Map<String, Object>> xtjs = null;
				js.setYhbh(list.get(i).get("yhbh").toString());
				if (list.get(i).get("jsbh").toString().equals("1")) {// 老师
					xtjs = tjsjDao.getJsbh("ROLE_TEACHER");
					if (xtjs != null && xtjs.size() != 0) {
						js.setJsbh(xtjs.get(0).get("jsbh").toString());
					}
				} else if (list.get(i).get("jsbh").toString().equals("2")) {// 辅导员
					xtjs = tjsjDao.getJsbh("ROLE_INSTRUCTOR");
					if (xtjs != null && xtjs.size() != 0) {
						js.setJsbh(xtjs.get(0).get("jsbh").toString());
					}
				} else {// 学生
					xtjs = tjsjDao.getJsbh("ROLE_STUDENT");
					if (xtjs != null && xtjs.size() != 0) {
						js.setJsbh(xtjs.get(0).get("jsbh").toString());
					}
				}
				// 保存新用户的角色信息
				sysYhjsDao.save(js);
			}
		} else {
			// 获取新用户
			List<Map<String, Object>> list = tjsjDao.getTssjZl();
			// 获取新机构
			List<Map<String, Object>> xbm = tjsjDao.getBmZl();
			for (int i = 0; i < xbm.size(); i++) {
				SysZzjg jg = new SysZzjg();
				jg.setBmbh(xbm.get(i).get("bmdm").toString());
				jg.setBmmc(xbm.get(i).get("bmmc").toString());
				jg.setBz(xbm.get(i).get("bmmc").toString());
				jg.setId(xbm.get(i).get("bmdm").toString());
				jg.setJb(Integer.parseInt(xbm.get(i).get("jb").toString()));
				if (xbm.get(i).get("jb").toString().equals("1")) {
					jg.setSjbh("0");
					jg.setPx(0);
				} else {
					jg.setSjbh(xbm.get(i).get("sjbmdm").toString());
					jg.setPx(1);
				}
				// 保存机构
				zzjgDao.save(jg);
			}
			for (int i = 0; i < list.size(); i++) {
				SysYh yh = new SysYh();
				yh.setYhbh(list.get(i).get("yhbh").toString());
				yh.setXm(list.get(i).get("xm").toString());
				yh.setUsername(list.get(i).get("dlm").toString());
				if (list.get(i).get("mm") != null) {
					yh.setPassword(list.get(i).get("mm").toString());
				} else {
					yh.setPassword(md5PasswordEncoder.encodePassword(
							(String) SettingUtil.getSetting("default.password",
									null), null));
				}
				yh.setBmbh(list.get(i).get("bmbh").toString());
				yh.setSjh("");
				if (list.get(i).get("zt") != null) {
					yh.setYhzt(Integer.parseInt(list.get(i).get("zt")
							.toString()));
				} else {
					yh.setYhzt(1);
				}
				// 保存新用户
				tjsjDao.saveYh(yh);
				SysYhjs js = new SysYhjs();
				List<Map<String, Object>> xtjs = null;
				js.setYhbh(list.get(i).get("yhbh").toString());
				if (list.get(i).get("jsbh").toString().equals("1")) {// 老师
					xtjs = tjsjDao.getJsbh("ROLE_TEACHER");
					if (xtjs != null && xtjs.size() != 0) {
						js.setJsbh(xtjs.get(0).get("jsbh").toString());
					}
				} else if (list.get(i).get("jsbh").toString().equals("2")) {// 辅导员
					xtjs = tjsjDao.getJsbh("ROLE_INSTRUCTOR");
					if (xtjs != null && xtjs.size() != 0) {
						js.setJsbh(xtjs.get(0).get("jsbh").toString());
					}
				} else {// 学生
					xtjs = tjsjDao.getJsbh("ROLE_STUDENT");
					if (xtjs != null && xtjs.size() != 0) {
						js.setJsbh(xtjs.get(0).get("jsbh").toString());
					}
				}
				// 保存新用户的角色信息
				sysYhjsDao.save(js);
			}
		}
	}
	
	
	
	/**
	 * 新生数据同步
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月15日 下午2:25:55 
	 * @param
	 */
	@Transactional
	public void saveXsTssj() {
		// 获取迎新新用户
		List<Map<String, Object>> list = tjsjDao.getXsTssjZl();
		for (int i = 0; i < list.size(); i++) {
			SysYh yh = new SysYh();
			yh.setYhbh(list.get(i).get("ksh").toString());
			yh.setXm(list.get(i).get("xm").toString());
			yh.setUsername(list.get(i).get("ksh").toString());
			String sfzh = list.get(i).get("sfzh").toString();
			if(sfzh.length()>=8){
				String mm = sfzh.substring(sfzh.length()-8, sfzh.length());
				yh.setPassword(md5PasswordEncoder.encodePassword(mm, null));
			}else{
				yh.setPassword(md5PasswordEncoder.encodePassword((String) SettingUtil.getSetting("default.password",null), null));
			}
			yh.setBmbh("xsglc");
			yh.setSjh("");
			yh.setYhzt(1);
			// 保存新用户
			tjsjDao.saveYh(yh);
			
			SysYhjs yhjs = new SysYhjs();
			List<Map<String, Object>> xtjs = null;
			yhjs.setYhbh(list.get(i).get("ksh").toString());
			xtjs = tjsjDao.getJsbh("ROLE_XS");
			if (xtjs != null && xtjs.size() != 0) {
				yhjs.setJsbh(xtjs.get(0).get("jsbh").toString());
			}
			// 保存新用户的角色信息
			sysYhjsDao.save(yhjs);
		}
	}

	/**
	 * 同步ad服务器用户信息
	 * 
	 * @author:zhangyi
	 * @version 创建时间：2015年10月23日 上午9:54:51
	 * @param type
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public String saveAdTssj(String type) {

		/***************************** 获取ad域用户信息begin *******************************/
		String host = ""; // AD服务器
		String port = ""; // 端口
		String username = ""; // 用户名
		String password = ""; // 密码
		String domain = ""; //登录前缀
		String searchBase = "";//域节点
		//获取ad服务器配置信息
		List<Map<String, Object>> list = tjsjDao.getAdpzxx();
		if (list.size() > 0) {
			Map<String, Object> map = list.get(0);
			host = map.get("fwqdz").toString();
			port = map.get("dkh").toString();
			username = map.get("tbyhm").toString();
			password = PasswordUtil.decode(map.get("tbmm").toString());
			domain = map.get("dlqz").toString();
			searchBase = map.get("yjd").toString();
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
	            // 域节点
//	            String searchBase = "OU=合肥智圣,DC=adser,DC=com";
	            // LDAP搜索过滤器类
	            String searchFilter = "objectClass=User";
	            // 搜索控制器
	            SearchControls searchCtls = new SearchControls(); // Create the
	            // 创建搜索控制器
	            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify
	            String returnedAtts[] = { "memberOf","url", "whenChanged", "employeeID",
	            "name", "userPrincipalName", "physicalDeliveryOfficeName",
	            "departmentNumber", "telephoneNumber", "homePhone",
	            "mobile", "department", "sAMAccountName", "whenChanged",
	            "mail","memberOf", "distinguishedName", "Pwd-Last-Set", "User-Password", "cn" }; // 定制返回属性
	            searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集

	            // 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
	            @SuppressWarnings("rawtypes")
				NamingEnumeration answer = ctx.search(searchBase, searchFilter,
	                    searchCtls);// Search for objects using the filter
	            while (answer.hasMoreElements()) {// 遍历结果集
	                SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN
	                if(sr!=null){
	                	if(sr.getAttributes()!=null){
	                		if(sr.getAttributes().get("samaccountname")!=null){
	                			String yhdlm = sr.getAttributes().get("samaccountname").get().toString();
	                			List<Map<String, Object>> listYh =sysYhDao.getByDlm(yhdlm);
	                			if(listYh.size() == 0){
	                				SysYh yh = new SysYh();
	                				yh.setUsername(yhdlm);//登录名
	                				String dn = sr.getName();
	                				String[] listbm = dn.split(",");
	                				if(listbm.length>1){
	                	                String match1 = dn.split(",")[1];
	                	                if(match1 != null){
	                	                	String bmmc = match1.replace("OU=", "");
	                	                	List<Map<String, Object>> bmbhlist = tjsjDao.getBmbh(bmmc);
	                	                	if(bmbhlist.size()>0){
	                	                		if(bmbhlist.get(0).get("bmbh") != null){
	                	                			yh.setBmbh(bmbhlist.get(0).get("bmbh").toString());
	                	                		}else{
	                	                			yh.setBmbh((String)SettingUtil.getSetting("default.bmbh",null));
	                	                		}
	                	                	}else{
	                	                		yh.setBmbh((String)SettingUtil.getSetting("default.bmbh",null));
	                	                	}
	                	                }else{
	                	                	yh.setBmbh((String)SettingUtil.getSetting("default.bmbh",null));
	                	                }
                	                }else{
                	                	yh.setBmbh((String)SettingUtil.getSetting("default.bmbh",null));
                	                }
	                				yh.setYhzt(1);//状态
		                			if(sr.getAttributes().get("name")!=null){//姓名
			                			yh.setXm(sr.getAttributes().get("name").get().toString());
			                		}
		                			if(sr.getAttributes().get("telephonenumber")!=null){//号码
			                			yh.setSjh(sr.getAttributes().get("telephonenumber").get().toString());
			                		}
		                			if(sr.getAttributes().get("mail")!=null){//邮箱
			                			yh.setYx(sr.getAttributes().get("mail").get().toString());
			                		}
		                			yh.setPassword(md5PasswordEncoder.encodePassword((String) SettingUtil.getSetting("default.password",null), null));//密码
		                			sysYhDao.save(yh);
	                			}
	                		}
	                	}
	                }
	            }
	            ctx.close();
	        } catch (NamingException e) {
//	            e.printStackTrace();
//	            System.err.println("Throw Exception : " + e);
	            return "0";
	        }
			/***************************** 获取ad域用户信息end *******************************/
		}
		return "1";
	}

}
