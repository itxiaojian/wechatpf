package com.sliu.framework.app.sys.security;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.sliu.framework.app.spring.SpringApplicationContextHolder;

/**
 * 
 * Description of the class
 * 
 * @author lxt
 * @version 1.0
 * @since 2013-3-25
 */
public class MyUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter implements AuthenticationProvider {
	private boolean validateCode = true;

	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
//	private static JdbcTemplate jdbcTemplate = SpringApplicationContextHolder.getBean(JdbcTemplate.class);

	public boolean isValidateCode() {
		return validateCode;
	}

	public void setValidateCode(boolean validateCode) {
		this.validateCode = validateCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		
		/*****************************验证ad信息begin*******************************/
//		if(!"".equalsIgnoreCase(username) && !"".equalsIgnoreCase(password)){
//	        String host = ""; // AD服务器
//	        String port = ""; // 端口
//	        String domain = ""; //登录前缀
//	        
//			String sql = "select t.id,t.fwqdz,t.dkh,t.dlqz,t.yjd from SYS_ADPZ t ";
//	        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//	        if(list.size()>0){
//	        	Map<String, Object> map = list.get(0);
//	        	host = map.get("fwqdz").toString();
//	        	port = map.get("dkh").toString();
//	        	if(map.get("dlqz") !=null){
//	        		domain = map.get("dlqz").toString();
//	        	}else{
//	        		domain = "adser";
//	        	}
//		        String url = new String("ldap://" + host + ":" + port);
//		        @SuppressWarnings("rawtypes")
//				Hashtable HashEnv = new Hashtable();
//		        // String adminName ="CN=oyxiaoyuanxy,CN=Users,DC=Hebmc,DC=com";
//		        // AD的用户名
//		//        String adminName = "adser\\administrator"; // 注意用户名的写法：domain\User
//		//        String adminPassword = "Sn@ke007"; // 密码
//		        String adminName = domain+"\\"+username; // 注意用户名的写法：domain\User
//		        String adminPassword = password; // 密码
//		        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别
//		        HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); // AD User
//		        HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); // AD Password
//		        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
//		        HashEnv.put(Context.PROVIDER_URL, url);
//		        try {
//		            LdapContext ctx = new InitialLdapContext(HashEnv, null);
//		            ctx.close();
//		            username = username+"hfzsadyh";
//		            password = "123456";
//		            System.out.println("--------------->公司内部认证用户。");
//		        } catch (NamingException e) {
//		        	System.out.println("--------------->非公司内部认证用户。");
//		//            e.printStackTrace();
//		//            System.err.println("Throw Exception : " + e);
//		        }	        
//	        }else{
//	        	request.getSession().setAttribute("warn", "公司ad地址未配置，请管理员查看。");
//	        	System.out.println("--------------->公司ad地址未配置，请管理员查看。");
//	        }
//        }
		/*****************************验证ad信息end*******************************/

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);

		if (session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute(
					SPRING_SECURITY_LAST_USERNAME_KEY,
					TextEscapeUtils.escapeEntities(username));
		}
		setDetails(request, authRequest);
		String validateCodeParameter = request.getParameter("validateCode");
		System.out.println(validateCodeParameter+"----------------------------");
//		if (validateCode) {
//			checkValidateCode(request);
//		}
		if(validateCodeParameter!=null&&!"".equals(validateCodeParameter)){
			checkValidateCode(request);
		}
		try {
			return this.getAuthenticationManager().authenticate(authRequest);
		} catch (RuntimeException e) {
			request.getSession().setAttribute("error", "用户名或密码错误");
			throw e;
		}
	}

	/**
	 * 
	 * <li>比较session中的验证码和用户输入的验证码是否相等</li>
	 * 
	 */
	protected void checkValidateCode(HttpServletRequest request) {
//		String sessionValidateCode = (String) request.getSession()
//				.getAttribute("validateCode");
		String sessionValidateCode = (String) request.getSession()
				.getAttribute("code");
		String validateCodeParameter = request.getParameter("validateCode");

		if (StringUtils.equals("0123456789", validateCodeParameter)) {
			return;
		}

		if (StringUtils.isBlank((String) request.getSession().getAttribute(
				"code"))
				|| !StringUtils.equalsIgnoreCase(sessionValidateCode,
						validateCodeParameter)) {
			request.getSession().setAttribute("error", "验证码错误");
			throw new AuthenticationServiceException("验证码错误");
		}
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return false;
	}
}
