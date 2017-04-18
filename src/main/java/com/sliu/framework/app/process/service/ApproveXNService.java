package com.sliu.framework.app.process.service;
///* Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * 
// *      http://www.apache.org/licenses/LICENSE-2.0
// * 
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.sliu.framework.app.process.service;
//
//import java.math.BigDecimal;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.sql.rowset.serial.SerialException;
//
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.task.Task;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.unteck.cps.support.process.ProcessXNUtils;
//import com.unteck.cps.web.aftercredit.dao.AfterCreditInfoXNDao;
//import com.unteck.cps.web.cust.dao.LoanInfoXNDao;
//import com.unteck.cps.web.flowmoneycontrol.dao.FlowmoneycontrolXNDao;
//import com.unteck.cps.web.model.CpsAftercreditInfo;
//import com.unteck.cps.web.model.CpsLoanInfo;
//import com.unteck.cps.web.model.CpsProcessOption;
//import com.unteck.cps.web.process.dao.BusinessXNDao;
//import com.unteck.cps.web.process.dao.ProcessOptionXNDao;
//import com.unteck.tpc.framework.core.util.SecurityContextUtil;
//import com.unteck.tpc.framework.web.model.admin.Attachment;
//import com.unteck.tpc.framework.web.model.admin.User;
//import com.unteck.tpc.framework.web.service.admin.AttachmentService;
//
///**
// * @author zhuzengpeng
// */
//@Service
//public class ApproveXNService {
//
//	  @Autowired
//	  private TaskService taskService;
//	  
//	  @Autowired
//	  private ProcessOptionXNDao processOptionDao;
//	  
//	  @Autowired
//	  private BusinessXNDao businessDao;
//	  
//	  @Autowired
//	  private RuntimeService runtimeService;
//	  
//	  @Autowired
//	  private LoanInfoXNDao loanInfoDao;
//	  
//	  @Autowired
//	  private AttachmentService attachmentService;
//	  
//	  @Autowired
//	  private AfterCreditInfoXNDao afterCreditInfoXNDao;
//	  
//	  @Autowired
//	  private FlowmoneycontrolXNDao flowmoneycontrolXNDao;
//	  
//	  
//	  
//	  /**
//	   * 秘书处理环节
//	   * @param taskId 工作项任务ID
//	 * @param signData 
//	 * @param creditYear 
//	 * @param creditRate 
//	 * @param creditSumW 
//	 * @param optionContent 
//	 * @param businessKey 
//	 * @param value 
//	 * @param lineVariable 
//	   * @return
//	   * @throws SQLException 
//	   * @throws SerialException 
//	  */
//	  @Transactional
//	  public void dealActMS(String taskId, String lineVariable, String value, String businessKey, String optionContent,String otherOption, BigDecimal creditSumW, String creditRate, Integer creditYear, String signData,String writeData) throws SerialException, SQLException {
//			User user = SecurityContextUtil.getCurrentUser();
//			String userCode = user.getUserCode();//任务处理完成后保存处理意见
//			CpsLoanInfo loanInfo = loanInfoDao.get(Integer.parseInt(businessKey));
//			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//			String procInstId = task.getProcessInstanceId();//流程实例ID
//			String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//			String actName = task.getName();//环节名称
//			String optionAction = value;//审批动作
//			String optionName = value;//审批动作名称	
//			//保存流程审批意见(同时保存电子签章)
//			CpsProcessOption processOption = new CpsProcessOption();
//			if(value.equals("0")) {
//				optionName = "不批准";
//				processOption.setExtra1("0");
//				processOption.setExtra2("");
//				processOption.setExtra3("");
//			}else if(value.equals("1")) {
//				optionName = "批准";
//				processOption.setExtra1(creditSumW + "");
//				processOption.setExtra2(creditRate + "%");
//				if(loanInfo!=null){
//					if(loanInfo.getExtra5() != null){
//						if("1".equalsIgnoreCase(loanInfo.getExtra5())){
//							processOption.setExtra3(creditYear + "年");
//						}else if("2".equalsIgnoreCase(loanInfo.getExtra5())){
//							processOption.setExtra3(creditYear + "月");
//						}else if("3".equalsIgnoreCase(loanInfo.getExtra5())){
//							processOption.setExtra3(creditYear + "日");
//						}else{
//							processOption.setExtra3(creditYear + "");
//						}
//					}
//				}
//				//只有在批准的时候才更新同意授信金额、同意授信利率、同意授信年限
//				if(creditSumW != null) {
////					BigDecimal creditSum = creditSumW.multiply(new BigDecimal("10000"));
//					BigDecimal creditSum = creditSumW;
//					businessDao.updateLoanCreditSum(creditSum, creditRate, creditYear, businessKey);
//				}
//			}	
//			processOption.setProcInstId(procInstId);
//			processOption.setBusinessKey(businessKey);
//			processOption.setTaskId(taskId);
//			processOption.setExecutionId(executionId);
//			processOption.setActName(actName);
//			processOption.setOptionAction(optionAction);
//			processOption.setOptionName(optionName);
//			processOption.setOptionContent(optionContent);
//			processOption.setExtra4(otherOption);
//			processOption.setApproveTime(new Date());
//			processOption.setApproveUser(userCode);
//			processOption.setSignData(signData);
//			processOption.setWriteData(writeData);
//			processOption.setProcessType(ProcessXNUtils.bzProcessKey);
//			//保存审批意见表入库
//			processOptionDao.save(processOption);	
//			//根据页面选择的操作设置连线变量完成任务
//			Map<String, Object> variables = new HashMap<String, Object>();
//			variables.put(lineVariable, value);
//			taskService.complete(taskId, variables);	
//	  }
//	  
//	  /**
//	   * 贷审小组审批环节，主要多了授信条件是否改变
//	   * @param taskId 工作项任务ID
//	   * @param lineVariable 当前环节下一连线设置变量
//	   * @param value 连线变量值 (1:同意  0:不同意)
//	   * @return
//	   * @throws SQLException 
//	   * @throws SerialException 
//	  */
//	  @Transactional
//	  public void dealActDSH(String taskId, String lineVariable,String value, String businessKey,String optionContent,String otherOption, BigDecimal creditSumW,String creditRate, Integer creditYear, String signData,String writeData) throws SerialException, SQLException {
//		//Clob clob=new javax.sql.rowset.serial.SerialClob(signData.toCharArray());  
//		User user = SecurityContextUtil.getCurrentUser();
//		CpsLoanInfo loanInfo = loanInfoDao.get(Integer.parseInt(businessKey));
//		String userCode = user.getUserCode();//任务处理完成后保存处理意见
//		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//		String procInstId = task.getProcessInstanceId();//流程实例ID
//		String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//		String actName = task.getName();//环节名称
//		String optionAction = value;//审批动作
//		String optionName = value;//审批动作名称	
//		//保存流程审批意见(同时保存电子签章)
//		CpsProcessOption processOption = new CpsProcessOption();
//		if(value.equals("0")) {
//			optionName = "不批准";
//			processOption.setRemark("0");
//		}else if(value.equals("1")) {
//			optionName = "批准";
//			processOption.setExtra1(creditSumW + "");
//			processOption.setExtra2(creditRate + "%");
//			if(loanInfo!=null){
//				if(loanInfo.getExtra5() != null){
//					if("1".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "年");
//					}else if("2".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "月");
//					}else if("3".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "日");
//					}else{
//						processOption.setExtra3(creditYear + "");
//					}
//				}
//			}
//		}	
//		processOption.setProcInstId(procInstId);
//		processOption.setBusinessKey(businessKey);
//		processOption.setTaskId(taskId);
//		processOption.setExecutionId(executionId);
//		processOption.setActName(actName);
//		processOption.setOptionAction(optionAction);
//		processOption.setOptionName(optionName);
//		processOption.setOptionContent(optionContent);
//		processOption.setExtra4(otherOption);
//		processOption.setApproveTime(new Date());
//		processOption.setApproveUser(userCode);
//		processOption.setSignData(signData);
//		processOption.setWriteData(writeData);
//		processOption.setProcessType(ProcessXNUtils.bzProcessKey);
//		//保存入库及更新授信主表允许授信额  
//		processOptionDao.save(processOption);	
////		if(creditSumW != null) {
////			BigDecimal creditSum = creditSumW.multiply(new BigDecimal("10000"));
////			BigDecimal creditSum = creditSumW;
////			businessDao.updateLoanCreditSum(creditSum, businessKey);
////		}
//		//根据页面选择的操作设置连线变量完成任务
//		Map<String, Object> variables = new HashMap<String, Object>();
////		variables.put(lineVariable, value);
//		String agreeCounterName = "agreeDshCounter";
//        Object agreeCounter = runtimeService.getVariable(executionId,agreeCounterName);
//        if (agreeCounter == null) {
//            // 初始化计数器
//        	if("1".equalsIgnoreCase(value)){
//        		runtimeService.setVariable(executionId, agreeCounterName, 1);
//                runtimeService.setVariable(executionId, "countersignDSHResult", "2");
//        	}else{
//        		runtimeService.setVariable(executionId, agreeCounterName, 0);
//                runtimeService.setVariable(executionId, "countersignDSHResult", "2");
//        	}
//        } else {
//            // 计数器累加
//        	if("1".equalsIgnoreCase(value)){
//	            Integer integerCounter = (Integer) runtimeService.getVariable(executionId, agreeCounterName);
//	            runtimeService.setVariable(executionId, agreeCounterName,++integerCounter);
//	            Integer dshUserSize = (Integer)runtimeService.getVariable(executionId, "dshUserSize");
//	            Double completeRate = new Double(integerCounter) / dshUserSize;
//	            boolean canComlete = completeRate > 0.5;
//	            if(canComlete) {
//	            	runtimeService.setVariable(executionId, "countersignDSHResult", "1");
//	            }
//            }
//        }
//        
//		taskService.complete(taskId, variables);
//			
//	  }
//	  
//	  /**
//	   * 贷审小组审批环节，主要多了授信条件是否改变
//	   * @param taskId 工作项任务ID
//	   * @param lineVariable 当前环节下一连线设置变量
//	   * @param value 连线变量值 (1:同意  0:不同意)
//	   * @return
//	   * @throws SQLException 
//	   * @throws SerialException 
//	  */
//	  @Transactional
//	  public void dealActDSXZ(String taskId, String lineVariable,String value, String businessKey,String optionContent,String otherOption, BigDecimal creditSumW,String creditRate, Integer creditYear, String signData,String writeData) throws SerialException, SQLException {
//		//Clob clob=new javax.sql.rowset.serial.SerialClob(signData.toCharArray());  
//		User user = SecurityContextUtil.getCurrentUser();
//		CpsLoanInfo loanInfo = loanInfoDao.get(Integer.parseInt(businessKey));
//		String userCode = user.getUserCode();//任务处理完成后保存处理意见
//		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//		String procInstId = task.getProcessInstanceId();//流程实例ID
//		String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//		String actName = task.getName();//环节名称
//		String optionAction = value;//审批动作
//		String optionName = value;//审批动作名称	
//		//保存流程审批意见(同时保存电子签章)
//		CpsProcessOption processOption = new CpsProcessOption();
//		if(value.equals("0")) {
//			optionName = "不批准";
//			processOption.setRemark("0");
//		}else if(value.equals("1")) {
//			optionName = "批准";
//			processOption.setExtra1(creditSumW + "");
//			processOption.setExtra2(creditRate + "%");
//			if(loanInfo!=null){
//				if(loanInfo.getExtra5() != null){
//					if("1".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "年");
//					}else if("2".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "月");
//					}else if("3".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "日");
//					}else{
//						processOption.setExtra3(creditYear + "");
//					}
//				}
//			}
//			//只有在批准的时候才更新同意授信金额、同意授信利率、同意授信年限
//		}	
//		processOption.setProcInstId(procInstId);
//		processOption.setBusinessKey(businessKey);
//		processOption.setTaskId(taskId);
//		processOption.setExecutionId(executionId);
//		processOption.setActName(actName);
//		processOption.setOptionAction(optionAction);
//		processOption.setOptionName(optionName);
//		processOption.setOptionContent(optionContent);
//		processOption.setExtra4(otherOption);
//		processOption.setApproveTime(new Date());
//		processOption.setApproveUser(userCode);
//		processOption.setSignData(signData);
//		processOption.setWriteData(writeData);
//		processOption.setProcessType(ProcessXNUtils.bzProcessKey);
//		//保存入库及更新授信主表允许授信额  
//		processOptionDao.save(processOption);	
////		if(creditSumW != null) {
////			BigDecimal creditSum = creditSumW.multiply(new BigDecimal("10000"));
////			BigDecimal creditSum = creditSumW;
////			businessDao.updateLoanCreditSum(creditSum, businessKey);
////		}
//		//根据页面选择的操作设置连线变量完成任务
//		Map<String, Object> variables = new HashMap<String, Object>();
////		variables.put(lineVariable, value);
//		String agreeCounterName = "agreeCounter";
//        Object agreeCounter = runtimeService.getVariable(executionId,agreeCounterName);
//        if (agreeCounter == null) {
//            // 初始化计数器
//        	if("1".equalsIgnoreCase(value)){
//        		runtimeService.setVariable(executionId, agreeCounterName, 1);
//                runtimeService.setVariable(executionId, "countersignResult", "2");
//        	}else{
//        		runtimeService.setVariable(executionId, agreeCounterName, 0);
//                runtimeService.setVariable(executionId, "countersignResult", "2");
//        	}
//        } else {
//            // 计数器累加
//        	if("1".equalsIgnoreCase(value)){
//	            Integer integerCounter = (Integer) runtimeService.getVariable(executionId, agreeCounterName);
//	            runtimeService.setVariable(executionId, agreeCounterName,++integerCounter);
//	            Integer dsxzUserSize = (Integer)runtimeService.getVariable(executionId, "dsxzUserSize");
//	            Double completeRate = new Double(integerCounter) / dsxzUserSize;
//	            boolean canComlete = completeRate > 0.5;
//	            if(canComlete) {
//	            	runtimeService.setVariable(executionId, "countersignResult", "1");
//	            }
//            }
//        }
//        
//		taskService.complete(taskId, variables);
//			
//	  }
//	  
//	  /**
//	   * 审查中心审批环节，主要多了授信条件是否改变
//	   * @param taskId 工作项任务ID
//	   * @param lineVariable 当前环节下一连线设置变量
//	   * @param value 连线变量值 (1:同意  0:不同意)
//	   * @return
//	   * @throws SQLException 
//	   * @throws SerialException 
//	  */
//	  @Transactional
//	  public Integer dealActSCZX(String taskId, String lineVariable,String hasChang,
//			  String value, String businessKey,String optionContent, String otherOption,
//			  BigDecimal creditSumW,String creditRate, Integer creditYear, String signData,String writeData, String levelOption, String rateOption,String candi) throws SerialException, SQLException {
//		User user = SecurityContextUtil.getCurrentUser();
//		CpsLoanInfo loanInfo = loanInfoDao.get(Integer.parseInt(businessKey));
//		if("1".equalsIgnoreCase(value)){
//			if(loanInfo.getCustType() != null){
//				if(loanInfo.getCustType() == 1){
//					businessDao.updatePrivaiteInfoForSCZX(levelOption,rateOption,Integer.parseInt(businessKey));
//				}else{
//					businessDao.updateCompanyInfoForSCZX(levelOption,rateOption,Integer.parseInt(businessKey));
//				}
//			}
//		}
//		String userCode = user.getUserCode();//任务处理完成后保存处理意见
//		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//		String procInstId = task.getProcessInstanceId();//流程实例ID
//		String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//		String actName = task.getName();//环节名称
//		String optionAction = value;//审批动作
//		String optionName = value;//审批动作名称	
//		//保存流程审批意见(同时保存电子签章)
//		CpsProcessOption processOption = new CpsProcessOption();
//		if(value.equals("0")) {
//			optionName = "不批准";
//			processOption.setRemark("0");
//		}else if(value.equals("1")) {
//			optionName = "批准";
//			processOption.setExtra1(creditSumW + "");
//			processOption.setExtra2(creditRate + "%");
//			if(loanInfo!=null){
//				if(loanInfo.getExtra5() != null){
//					if("1".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "年");
//					}else if("2".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "月");
//					}else if("3".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "日");
//					}else{
//						processOption.setExtra3(creditYear + "");
//					}
//				}
//			}
//			//只有在批准的时候才更新同意授信金额、同意授信利率、同意授信年限
//			if(creditSumW != null) {
////				BigDecimal creditSum = creditSumW.multiply(new BigDecimal("10000"));
//				BigDecimal creditSum = creditSumW;
//				businessDao.updateLoanCreditSum(creditSum, creditRate, creditYear, businessKey);
//			}
//		}	
//		processOption.setProcInstId(procInstId);
//		processOption.setBusinessKey(businessKey);
//		processOption.setTaskId(taskId);
//		processOption.setExecutionId(executionId);
//		processOption.setActName(actName);
//		processOption.setOptionAction(optionAction);
//		processOption.setOptionName(optionName);
//		processOption.setOptionContent(optionContent);
//		processOption.setExtra4(otherOption);
//		processOption.setApproveTime(new Date());
//		processOption.setApproveUser(userCode);
//		processOption.setSignData(signData);
//		processOption.setWriteData(writeData);
//		processOption.setProcessType(ProcessXNUtils.bzProcessKey);
//		//保存入库及更新授信主表允许授信额  
//		Integer opinionId = processOptionDao.save(processOption);	
//		
////		if(creditSumW != null) {
////			BigDecimal creditSum = creditSumW.multiply(new BigDecimal("10000"));
////			BigDecimal creditSum = creditSumW;
////			businessDao.updateLoanCreditSum(creditSum, businessKey);
////		}
//		//根据页面选择的操作设置连线变量完成任务
//		Map<String, Object> variables = new HashMap<String, Object>();
//		
//		if(candi!=null && !"".equalsIgnoreCase(candi)){
//			List<String> dshUserList = getDshUserList();
//			String[] scandi=candi.split(",");
//			for(String scan:scandi){
//				dshUserList.add(scan);
//			}
//			if(dshUserList.size()>0) {
//				variables.put("dshUserList", dshUserList);	
//				variables.put("dshUserSize", dshUserList.size());
//			}
//		}
//		
//		List<Map<String, Object>> list = flowmoneycontrolXNDao.findFlowMoneyControl();
//		if(list!=null){
//			Map<String,Object> map = list.get(0);
//			if(map.get("businessmost") != null){
//				variables.put("businessmost", map.get("businessmost"));
//			}else{
//				variables.put("businessmost", 500000);
//			}
//			if(map.get("specificleadermost") != null){
//				variables.put("specificleadermost", map.get("specificleadermost"));
//			}else{
//				variables.put("specificleadermost", 2000000);
//			}
//			if(map.get("loangroupmost") != null){
//				variables.put("loangroupmost", map.get("loangroupmost"));
//			}else{
//				variables.put("loangroupmost", 10000000);
//			}
//		}else{
//			variables.put("businessmost", 500000);
//			variables.put("specificleadermost", 2000000);
//			variables.put("loangroupmost", 10000000);
//		}
//		
//		variables.put(lineVariable, value);
//		variables.put("hasChang", hasChang);
//		taskService.complete(taskId, variables);
//		return opinionId;
//	  }
//	  
//		/**
//		 * 获取贷审会成员
//		 * @param userCode
//		 * @return
//		 */
//		private List<String> getDshUserList () {
//			List<String> list = new ArrayList<String>();
//			List<Map<String, Object>> listRoleCheck = businessDao.getDSHUserList();
//			if(!listRoleCheck.isEmpty()) {
//				for(int i = 0; i<listRoleCheck.size(); i++) {
//					String checkUserCode = listRoleCheck.get(i).get("userCode").toString();
//					list.add(checkUserCode);
//				}
//			}
//			return list;
//		}
//	  
//		/**
//		 * 通用环节处理逻辑
//		 * @param taskId 工作项任务ID
//		 * @param lineVariable 当前环节下一连线设置变量
//		 * @param value 连线变量值 (1:同意  0:不同意)
//		 * @param rateOption 
//		 * @param levelOption 
//		 * @return
//		 * @throws SQLException 
//		 * @throws SerialException 
//		 */
//	  @Transactional
//	  public void dealActZHHZ(String taskId, String lineVariable, String value, String businessKey, String optionContent,String otherOption, BigDecimal creditSumW, String creditRate, Integer creditYear, String signData,String writeData, String levelOption, String rateOption) throws SerialException, SQLException {
//			User user = SecurityContextUtil.getCurrentUser();
//			CpsLoanInfo loanInfo = loanInfoDao.get(Integer.parseInt(businessKey));
//			if("1".equalsIgnoreCase(value)){
//				if(loanInfo.getCustType() != null){
//					if(loanInfo.getCustType() == 1){
//						businessDao.updatePrivaiteInfo(levelOption,rateOption,Integer.parseInt(businessKey));
//					}else{
//						businessDao.updateCompanyInfo(levelOption,rateOption,Integer.parseInt(businessKey));
//					}
//				}
//			}
//			String userCode = user.getUserCode();//任务处理完成后保存处理意见
//			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//			String procInstId = task.getProcessInstanceId();//流程实例ID
//			String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//			String actName = task.getName();//环节名称
//			String optionAction = value;//审批动作
//			String optionName = value;//审批动作名称	
//			//保存流程审批意见(同时保存电子签章)
//			CpsProcessOption processOption = new CpsProcessOption();
//			if(value.equals("0")) {
//				optionName = "不批准";
//				processOption.setExtra1("0");
//				processOption.setExtra2("");
//				processOption.setExtra3("");
//			}else if(value.equals("1")) {
//				optionName = "批准";
//				processOption.setExtra1(creditSumW + "");
//				processOption.setExtra2(creditRate + "%");
//				if(loanInfo!=null){
//					if(loanInfo.getExtra5() != null){
//						if("1".equalsIgnoreCase(loanInfo.getExtra5())){
//							processOption.setExtra3(creditYear + "年");
//						}else if("2".equalsIgnoreCase(loanInfo.getExtra5())){
//							processOption.setExtra3(creditYear + "月");
//						}else if("3".equalsIgnoreCase(loanInfo.getExtra5())){
//							processOption.setExtra3(creditYear + "日");
//						}else{
//							processOption.setExtra3(creditYear + "");
//						}
//					}
//				}
//				//只有在批准的时候才更新同意授信金额、同意授信利率、同意授信年限
//				if(creditSumW != null) {
//	//				BigDecimal creditSum = creditSumW.multiply(new BigDecimal("10000"));
//					BigDecimal creditSum = creditSumW;
//					businessDao.updateLoanCreditSum(creditSum, creditRate, creditYear, businessKey);
//				}
//			}	
//			processOption.setProcInstId(procInstId);
//			processOption.setBusinessKey(businessKey);
//			processOption.setTaskId(taskId);
//			processOption.setExecutionId(executionId);
//			processOption.setActName(actName);
//			processOption.setOptionAction(optionAction);
//			processOption.setOptionName(optionName);
//			processOption.setOptionContent(optionContent);
//			processOption.setExtra4(otherOption);
//			processOption.setApproveTime(new Date());
//			processOption.setApproveUser(userCode);
//			processOption.setSignData(signData);
//			processOption.setWriteData(writeData);
//			processOption.setProcessType(ProcessXNUtils.bzProcessKey);
//			//保存审批意见表入库
//			processOptionDao.save(processOption);	
//			//根据页面选择的操作设置连线变量完成任务
//			Map<String, Object> variables = new HashMap<String, Object>();
//			variables.put(lineVariable, value);
//			taskService.complete(taskId, variables);		
//	  	}
//	  	
//		/**
//		 * 通用环节处理逻辑
//		 * @param taskId 工作项任务ID
//		 * @param lineVariable 当前环节下一连线设置变量
//		 * @param value 连线变量值 (1:同意  0:不同意)
//		 * @return
//		 * @throws SQLException 
//		 * @throws SerialException 
//		 */
//	  @Transactional
//	  public void dealAct(String taskId, String lineVariable, String value, String businessKey, String optionContent,String otherOption, BigDecimal creditSumW, String creditRate, Integer creditYear, String signData,String writeData) throws SerialException, SQLException {
//		//Clob clob=new javax.sql.rowset.serial.SerialClob(signData.toCharArray());  
//		User user = SecurityContextUtil.getCurrentUser();
//		String userCode = user.getUserCode();//任务处理完成后保存处理意见
//		CpsLoanInfo loanInfo = loanInfoDao.get(Integer.parseInt(businessKey));
//		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//		String procInstId = task.getProcessInstanceId();//流程实例ID
//		String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//		String actName = task.getName();//环节名称
//		String optionAction = value;//审批动作
//		String optionName = value;//审批动作名称	
//		//保存流程审批意见(同时保存电子签章)
//		CpsProcessOption processOption = new CpsProcessOption();
//		if(value.equals("0")) {
//			optionName = "不批准";
//			processOption.setExtra1("0");
//			processOption.setExtra2("");
//			processOption.setExtra3("");
//		}else if(value.equals("1")) {
//			optionName = "批准";
//			processOption.setExtra1(creditSumW + "");
//			processOption.setExtra2(creditRate + "%");
//
//			if(loanInfo!=null){
//				if(loanInfo.getExtra5() != null){
//					if("1".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "年");
//					}else if("2".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "月");
//					}else if("3".equalsIgnoreCase(loanInfo.getExtra5())){
//						processOption.setExtra3(creditYear + "日");
//					}else{
//						processOption.setExtra3(creditYear + "");
//					}
//				}
//			}
//			
//			//只有在批准的时候才更新同意授信金额、同意授信利率、同意授信年限
//			if(creditSumW != null) {
////				BigDecimal creditSum = creditSumW.multiply(new BigDecimal("10000"));
//				BigDecimal creditSum = creditSumW;
//				businessDao.updateLoanCreditSum(creditSum, creditRate, creditYear, businessKey);
//			}
//		}	
//		processOption.setProcInstId(procInstId);
//		processOption.setBusinessKey(businessKey);
//		processOption.setTaskId(taskId);
//		processOption.setExecutionId(executionId);
//		processOption.setActName(actName);
//		processOption.setOptionAction(optionAction);
//		processOption.setOptionName(optionName);
//		processOption.setOptionContent(optionContent);
//		processOption.setExtra4(otherOption);
//		processOption.setApproveTime(new Date());
//		processOption.setApproveUser(userCode);
//		processOption.setSignData(signData);
//		processOption.setWriteData(writeData);
//		processOption.setProcessType(ProcessXNUtils.bzProcessKey);
//		//保存审批意见表入库
//		processOptionDao.save(processOption);	
//		//根据页面选择的操作设置连线变量完成任务
//		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put(lineVariable, value);
//		taskService.complete(taskId, variables);		
//	  	}
//	  
//		/**
//		 * 客户经理修改资料审批逻辑
//		 * @param taskId 工作项任务ID
//		 * @param lineVariable 当前环节下一连线设置变量
//		 * @param value 连线变量值 (1:同意  0:不同意)
//		 * @param businessKey 业务主键(即授信信息表主键)
//		 * @param optionContent(审批意见)
//		 * @return
//		 */
//	    @Transactional
//		public void dealCustomUpdateAct(String taskId, String lineVariable, String value, String businessKey, 
//				String optionContent) {
//			User user = SecurityContextUtil.getCurrentUser();
//			String userCode = user.getUserCode();//任务处理完成后保存处理意见
//			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//			String procInstId = task.getProcessInstanceId();//流程实例ID
//			String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//			String actName = task.getName();//环节名称
//			String optionAction = value;//审批动作
//			String optionName = value;//审批动作名称
//			if(value.equals("0")) {
//				optionName = "不批准";
//			}else if(value.equals("1")) {
//				optionName = "批准";
//			}		
//			//保存流程审批意见(同时保存电子签章)
//			CpsProcessOption processOption = new CpsProcessOption();
//			processOption.setProcInstId(procInstId);
//			processOption.setBusinessKey(businessKey);
//			processOption.setTaskId(taskId);
//			processOption.setExecutionId(executionId);
//			processOption.setActName(actName);
//			processOption.setOptionAction(optionAction);
//			processOption.setOptionName(optionName);
//			processOption.setOptionContent(optionContent);
//			processOption.setApproveTime(new Date());
//			processOption.setApproveUser(userCode);
//			processOption.setExtra1("");
//			processOption.setExtra2("");
//			processOption.setExtra3("");
//			processOption.setSignData("");
//			processOption.setProcessType(ProcessXNUtils.bzProcessKey);
//			processOptionDao.save(processOption);	
//			//根据页面选择的操作设置连线变量完成任务
//			Map<String, Object> variables = new HashMap<String, Object>();
//		//	variables.put(lineVariable, value);
//			taskService.complete(taskId);				
//			}
//
//		public void saveHeadBankExaminerAttachment(Integer opinionId,
//				String uploadFilePath, String fileName,String attachmentType) {
//			Long opinionIdValue = opinionId.longValue();
//			User user = SecurityContextUtil.getCurrentUser();
//			String userCode = user.getUserCode();
//			List<Attachment> attachments = attachmentService.findByNamedParam(new String[]{"dataId","attachmentType","delFlag"}, new Object[]{opinionIdValue,attachmentType,0});
//			Attachment attachment = null;
//			Long attachmentId = null;
//			if(attachments == null || attachments.size() <= 0){
//				//insert
//				attachment = new Attachment();
//				attachment.setDataId(opinionIdValue);
//				attachment.setDescn(uploadFilePath);
//				attachment.setFileName(fileName);
//				attachment.setFileCode(fileName);
//				attachment.setDelFlag(0);
//				attachment.setAttachmentType(attachmentType);
//				attachment.setCreateUser(userCode);
//				attachment.setCreateTime(new Date());
//				attachmentId = attachmentService.insertEntity(attachment);
//				
//			}else{
//				//update
//				attachment = attachments.get(0);
//				attachment.setDataId(opinionIdValue);
//				attachment.setDescn(uploadFilePath);
//				attachment.setFileName(fileName);
//				attachment.setFileCode(fileName);
//				attachment.setUpdateTime(new Date());
//				attachment.setUpdateUser(userCode);
//				attachmentService.updateEntity(attachment);
//			}
////			return attachmentId;
//		}
//		public void saveBlackExaminerAttachment(Integer opinionId,
//				String uploadFilePath, String fileName,String attachmentType) {
//			Long opinionIdValue = opinionId.longValue();
//			User user = SecurityContextUtil.getCurrentUser();
//			String userCode = user.getUserCode();
//			List<Attachment> attachments = attachmentService.findByNamedParam(new String[]{"dataId","attachmentType","delFlag"}, new Object[]{opinionIdValue,attachmentType,0});
//			Attachment attachment = null;
//			Long attachmentId = null;
//			if(attachments == null || attachments.size() <= 0){
//				//insert
//				attachment = new Attachment();
//				attachment.setDataId(opinionIdValue);
//				attachment.setDescn(uploadFilePath);
//				attachment.setFileName(fileName);
//				attachment.setFileCode(fileName);
//				attachment.setDelFlag(0);
//				attachment.setAttachmentType(attachmentType);
//				attachment.setCreateUser(userCode);
//				attachment.setCreateTime(new Date());
//				attachmentId = attachmentService.insertEntity(attachment);
//				
//			}else{
//				//update
//				attachment = attachments.get(0);
//				attachment.setDataId(opinionIdValue);
//				attachment.setDescn(uploadFilePath);
//				attachment.setFileName(fileName);
//				attachment.setFileCode(fileName);
//				attachment.setUpdateTime(new Date());
//				attachment.setUpdateUser(userCode);
//				attachmentService.updateEntity(attachment);
//			}
////			return attachmentId;
//		}
//		public Map<String, Object> getAttachmentByOpinionId(Integer opinionId) {
//			return businessDao.getAttachmentByOpinionId(opinionId);
//		}
//
//		/**
//		 * 贷后审批支行行长审批
//		 * @param taskId
//		 * @param lineVariable
//		 * @param value
//		 * @param businessKey
//		 * @param optionContent
//		 * @param fiveLevel
//		 * @param afterLoanSum
//		 */
//		@Transactional
//		public void dealZHHZAfterLoan(String taskId, String lineVariable,String value, String businessKey, String optionContent,String fiveLevel, String afterLoanSum) {
//			//Clob clob=new javax.sql.rowset.serial.SerialClob(signData.toCharArray());  
//			CpsAftercreditInfo aftInfo = afterCreditInfoXNDao.get(Integer.parseInt(businessKey));
//			businessDao.updateAfterLoanLevel(fiveLevel, aftInfo.getCreditId());
//			if(afterLoanSum!=null){
//				BigDecimal afSum = new BigDecimal(afterLoanSum);
//				if(aftInfo.getCustType() == 1){
//					if(afSum.compareTo(BigDecimal.valueOf(1000000.00)) == 1){
//						businessDao.updatePersonalConfirmationForm(businessKey,optionContent,fiveLevel,"branchOpinion");
//					}else{
//						businessDao.updateLowPersonalConfirmationForm(businessKey,optionContent,fiveLevel,"branchOpinion");
//					}
//				}else if(aftInfo.getCustType() == 2){
//						businessDao.updateCompanyConfForm(businessKey,optionContent,fiveLevel,"reviewComments");
//				}
//			}
//			
//			
//			Map<String, Object> variables = new HashMap<String, Object>();
//			variables.put(lineVariable, value);
//			taskService.complete(taskId, variables);
//	  	}
//
//		/**
//		 * 贷后流程业务管理部审批
//		 * @param taskId
//		 * @param lineVariable
//		 * @param value
//		 * @param businessKey
//		 * @param optionContent
//		 * @param fiveLevel
//		 * @param afterLoanSum
//		 */
//		@Transactional
//		public void dealYWGLBAfterLoan(String taskId, String lineVariable, String value, String businessKey, String optionContent, String fiveLevel, String afterLoanSum) {
//			CpsAftercreditInfo aftInfo = afterCreditInfoXNDao.get(Integer.parseInt(businessKey));
//			businessDao.updateAfterLoanLevel(fiveLevel,  aftInfo.getCreditId());
//			if(afterLoanSum!=null){
//				BigDecimal afSum = new BigDecimal(afterLoanSum);
//				if(aftInfo.getCustType() == 1){
//					if(afSum.compareTo(BigDecimal.valueOf(1000000.00)) == 1){
//						businessDao.updatePersonalConfirmationForm(businessKey,optionContent,fiveLevel,"headAcceptsOpinion");
//					}
//				}else if(aftInfo.getCustType() == 2){
//						businessDao.updateCompanyConfForm(businessKey,optionContent,fiveLevel,"riskGroupIdentified");
//				}
//			}
//			
//			Map<String, Object> variables = new HashMap<String, Object>();
//			variables.put(lineVariable, value);
//			taskService.complete(taskId, variables);
//		}
//		
//		/**
//		 * 贷后流程审批小组审批
//		 * @param taskId
//		 * @param lineVariable
//		 * @param value
//		 * @param businessKey
//		 * @param optionContent
//		 * @param fiveLevel
//		 * @param afterLoanSum
//		 */
//		@Transactional
//		public void dealSPXZAfterLoan(String taskId, String lineVariable,String value, String businessKey, String optionContent,String fiveLevel, String afterLoanSum) {
//			CpsAftercreditInfo aftInfo = afterCreditInfoXNDao.get(Integer.parseInt(businessKey));
//			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//			String executionId = task.getExecutionId();//对应流程引擎中 的EXECUTION_ID_
//			businessDao.updateAfterLoanLevel(fiveLevel,  aftInfo.getCreditId());
//			if(afterLoanSum!=null){
//				BigDecimal afSum = new BigDecimal(afterLoanSum);
//				if(aftInfo.getCustType() == 1){
//					if(afSum.compareTo(BigDecimal.valueOf(1000000.00)) == 1){
//						businessDao.updatePersonalConfirmationForm(businessKey,optionContent,fiveLevel,"headRiskOpinion");
//					}
//				}else if(aftInfo.getCustType() == 2){
//						businessDao.updateCompanyConfForm(businessKey,optionContent,fiveLevel,"companyManagerApp");
//				}
//			}
//			
//			//根据页面选择的操作设置连线变量完成任务
//			Map<String, Object> variables = new HashMap<String, Object>();
////			variables.put(lineVariable, value);
//			String agreeCounterName = "agreeCounter";
//	        Object agreeCounter = runtimeService.getVariable(executionId,agreeCounterName);
//	        if (agreeCounter == null) {
//	            // 初始化计数器
//	            runtimeService.setVariable(executionId, agreeCounterName, 1);
//	            runtimeService.setVariable(executionId, "countersignResult", "2");
//	        } else {
//	            // 计数器累加
//	        	if("1".equalsIgnoreCase(value)){
//		            Integer integerCounter = (Integer) runtimeService.getVariable(executionId, agreeCounterName);
//		            runtimeService.setVariable(executionId, agreeCounterName,++integerCounter);
//		            Integer dhspzUserSize = (Integer)runtimeService.getVariable(executionId, "dhspzUserSize");
//		            Double completeRate = new Double(integerCounter) / dhspzUserSize;
//		            boolean canComlete = completeRate > 0.5;
//		            if(canComlete) {
//		            	runtimeService.setVariable(executionId, "countersignResult", "1");
//		            }
//	            }
//	        }
//	        
//			taskService.complete(taskId, variables);
//		}
//		
//}
