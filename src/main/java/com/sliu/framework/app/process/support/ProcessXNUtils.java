package com.sliu.framework.app.process.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sliu.framework.app.process.dao.BusinessXNDao;
import com.sliu.framework.app.process.support.file.CpsConstant;
import com.sliu.framework.app.spring.SpringApplicationContextHolder;
import com.sliu.framework.app.sys.bo.SysYhBO;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

//import com.untech.ipas.web.process.dao.BusinessXNDao;
//import com.unteck.tpc.framework.core.spring.SpringApplicationContextHolder;
//import com.unteck.tpc.framework.core.util.AppUtil;
//import com.unteck.tpc.framework.web.dao.admin.OrganizationDao;
//import com.unteck.tpc.framework.web.dao.admin.RoleDao;
//import com.unteck.tpc.framework.web.dao.admin.SysYhDao;
//import com.unteck.tpc.framework.web.model.admin.SysYh;
//289074767

/**
 * @author zhangyi

 */
public class ProcessXNUtils {
	
//	private static SysYhDao userDao = SpringApplicationContextHolder.getBean(SysYhDao.class);
//	private static RoleDao roleDao = SpringApplicationContextHolder.getBean(RoleDao.class);
	private static JdbcTemplate jdbcTemplate = SpringApplicationContextHolder.getBean(JdbcTemplate.class);
	
	private static RuntimeService runtimeService = SpringApplicationContextHolder.getBean(RuntimeService.class);
	private static IdentityService identityService = SpringApplicationContextHolder.getBean(IdentityService.class);
	private static BusinessXNDao businessDao = SpringApplicationContextHolder.getBean(BusinessXNDao.class);
	private static SysYhBO sysUserBO = SpringApplicationContextHolder.getBean(SysYhBO.class);
	
	
//	private static OrganizationDao organizationDao = SpringApplicationContextHolder.getBean(OrganizationDao.class);
	//亳州信贷无纸化流程KEY //休宁信贷无纸化流程KEY
//	public static final String bzProcessKey = "xnLoanProcess";
//	//歙县信贷无纸化流程KEY 
//	public static final String bzProcessKey = "sxLoanProcess";
	//绩效考核流程KEY 
	public static final String bzProcessKey = "jxkhProcess";
	//亳州信贷无纸化用信审阅流程KEY
	public static final String bzUseCreditProcessKey = "xnUseCreditProcess";
	//亳州信贷无纸化流程KEY //休宁信贷无纸化流程KEY
	public static final String xnAfterLoanProcessKey = "xnAfterLoan";
	
	//休宁信贷无纸化流程KEY
	//public static final String xnProcessKey = "xnLoanProcess";
		
	
	
//	/**
//	 * 启动流程
//	 * @param processDefinitionKey 待启动的流程定义ID
//	 * @param businessKey 业务主键(此处对应授信信息表中的CREDIT_ID)
//	 * @param applySum 申请贷款金额 
//	 */
//	public static void startSXCreditFlow(String processDefinitionKey, String businessKey, BigDecimal applySum,Integer custType,String guaranteeWay,String productType,Integer productId,String creditB) {
//		processDefinitionKey = bzProcessKey;
//		SysYh user = AppUtil.getCurrentUser();
//		String orgId = user.getId();
//		String userCode = user.getUsername();
//		Map<String, Object> variables = new HashMap<String, Object>();
//		String isFirstBank = "1"; //是否为一级支行
//		String thisHasApply = "1"; //当前支行是否有审批权限
//		String firstHasApply = "1"; //一级支行是否有审批权限
//		variables.put("businessKey", businessKey);
//		
//	    // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
//	    identityService.setAuthenticatedUserId(userCode);
//		//正式使用时用下面的API，把业务主键传给流程引擎
//	    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(businessKey, variables);
//	    //流程启动后更新业务主表数据
//	    String processInstanceId = processInstance.getProcessInstanceId();
//	    businessDao.updateTestProcess(processInstanceId, businessKey);
//	}
//	
	
	/**
	 * 启动流程
	 * @param processDefinitionKey 待启动的流程定义ID
	 * @param businessKey 业务主键
	 */
	public static String startAppFlow(String processDefinitionKey, String businessKey) {
		processDefinitionKey = bzProcessKey;
		SysYh user = AppUtil.getCurrentUser();
		String userCode = user.getUsername();
		String orgCode = user.getBmbh();
		Map<String, Object> variables = new HashMap<String, Object>();
		//将申请人的code传入流程
		variables.put("StaffCODE", userCode);
		
		String roleKind = businessDao.getUserRoleByUserCode(userCode);
		variables.put("roleName", roleKind);
		if(roleKind != null){
			if(roleKind.equalsIgnoreCase(CpsConstant.ROLE_PTRG) || roleKind.equalsIgnoreCase(CpsConstant.ROLE_XMJL) ){
				variables.put("roleKind", "1");
				//获取本部门经理
				String sjldCode = businessDao.getSjldUserCodeInOrgCode(userCode,orgCode,CpsConstant.ROLE_BMJL);
				variables.put("BmjlCODE", sjldCode);
			}else if (roleKind.equalsIgnoreCase(CpsConstant.ROLE_BMJL) ){
				variables.put("roleKind", "2");
				//获取技术总监
				String sjldCode = businessDao.getLeaderUserCode(userCode,CpsConstant.ROLE_JSZJ);
				variables.put("JszjCODE", sjldCode);
			}else if (roleKind.equalsIgnoreCase(CpsConstant.ROLE_JSZJ)  || roleKind.equalsIgnoreCase(CpsConstant.ROLE_SCRY) ){
				variables.put("roleKind", "3");
				//获取总经理
				String sjldCode = businessDao.getLeaderUserCode(userCode,CpsConstant.ROLE_ZJL);
				variables.put("ZjlCODE", sjldCode);
			}
		}else{
			return "false";
		}
				
		variables.put("businessKey", businessKey);
		
		
	    // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中x
	    identityService.setAuthenticatedUserId(userCode);
		//正式使用时用下面的API，把业务主键传给流程引擎
	    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
	    //流程启动后更新业务主表数据
	    String processInstanceId = processInstance.getProcessInstanceId();
	    businessDao.updateTestProcess(processInstanceId, businessKey);
		return "success";
	}
	
	
	
	
	/**
	 * 启动流程
	 * @param processDefinitionKey 待启动的流程定义ID
	 * @param businessKey 业务主键
	 */
	public static String startJXKHFlow(String processDefinitionKey, String businessKey) {
		processDefinitionKey = bzProcessKey;
		SysYh user = AppUtil.getCurrentUser();
		String userCode = user.getUsername();
		String orgCode = user.getBmbh();
		String gwbh = AppUtil.getCurrentUser().getGwbh();
		Map<String, Object> variables = new HashMap<String, Object>();
		//将申请人的code传入流程
		variables.put("StaffCODE", userCode);
		//将申请人的部门编号传入流程
		variables.put("orgCode", orgCode);
		//将申请人的岗位编号传入流程
		variables.put("gwbh", gwbh);
		//作为考核期号保存
		variables.put("businessKey", businessKey);
		UUID uuid = UUID.randomUUID();//uuid作为流程主键
//		String business = userCode+"@"+businessKey+"";
		
		String roleKind = businessDao.getUserRoleByUserCode(userCode);
		variables.put("roleName", roleKind);
		if(roleKind != null){
			if(roleKind.equalsIgnoreCase(CpsConstant.ROLE_PTRG) || roleKind.equalsIgnoreCase(CpsConstant.ROLE_XMJL) ){
				variables.put("roleKind", "1");
				//获取本部门经理
				String sjldCode = businessDao.getSjldUserCodeInOrgCode(userCode,orgCode,CpsConstant.ROLE_BMJL);
				if(sjldCode != null){
					variables.put("BmjlCODE", sjldCode);
				}else{
					return "false";
				}
			}else if (roleKind.equalsIgnoreCase(CpsConstant.ROLE_BMJL) ){
				variables.put("roleKind", "2");
				//获取技术总监
				String sjldCode = businessDao.getLeaderUserCode(userCode,CpsConstant.ROLE_JSZJ);
				if(sjldCode != null){
					variables.put("JszjCODE", sjldCode);
				}else{
					return "false";
				}
			}else if (roleKind.equalsIgnoreCase(CpsConstant.ROLE_JSZJ)  || roleKind.equalsIgnoreCase(CpsConstant.ROLE_SCRY) ){
				variables.put("roleKind", "3");
				//获取总经理
				String sjldCode = businessDao.getLeaderUserCode(userCode,CpsConstant.ROLE_ZJL);
				if(sjldCode != null){
					variables.put("ZjlCODE", sjldCode);
				}
			}else{
				return "false";
			}
		}else{
			return "false";
		}
				
		
	    // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中x
	    identityService.setAuthenticatedUserId(userCode);
		//正式使用时用下面的API，把业务主键传给流程引擎
	    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
	    //流程启动后更新业务主表数据
//	    String processInstanceId = processInstance.getProcessInstanceId();
		return "success";
	}
	
	
	
	
	/**
	 * 根据usercode获取用户
	 * @author bajnokw
	 * @param code
	 * @return
	 */
	public static SysYh getSysYhByCode(String code){
//		String hql = "from SysYh u where u.userCode = '" + code + "'";
		SysYh users = sysUserBO.findSysYhByUsername(code);
		
		return users == null?null:users;
	}
	
	
	
	
	/**
	 * 
	 * @param userCode
	 * @return
	 */
	private static String getJlUsers() {
		StringBuffer branchUsers = new StringBuffer("");
		List<Map<String, Object>> listRoleCheck = businessDao.getXNBranchUsersByUserCode();
		if(!listRoleCheck.isEmpty()) {
			for(int i = 0; i<listRoleCheck.size(); i++) {
				String checkUserCode = listRoleCheck.get(i).get("userCode").toString();
				if(i == listRoleCheck.size()-1) {
					branchUsers.append(checkUserCode);
				}else {
					branchUsers.append(checkUserCode + ",");
				}
			}
		}
		return branchUsers.toString();
	}
	

}
