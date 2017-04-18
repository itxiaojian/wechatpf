package com.sliu.framework.app.test.service;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.process.dao.BusinessXNDao;
import com.sliu.framework.app.process.model.TestProcess;
import com.sliu.framework.app.process.support.file.CpsConstant;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.test.dao.TestProcessDao;
import com.sliu.framework.app.util.AppUtil;


@Service
public class TestProcessService {

	@Autowired
	private TestProcessDao testProcessDao;
	
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private BusinessXNDao businessXNDao;  

    @Transactional
	public Pagination<Object> getAllTestProcess(Integer start, Integer limit) {
		return testProcessDao.getAllTestProcess(start,limit);
	}

	@Transactional
	public void saveTestProcess(TestProcess fbt) {
		fbt.setProcessStatus("1");
		testProcessDao.save(fbt);
	}

	/**
	 * 通用环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 */
  @Transactional
  public void dealAct(String taskId, String lineVariable, String value, String businessKey, String optionContent) throws SerialException, SQLException {
	SysYh user = AppUtil.getCurrentUser();
	//根据页面选择的操作设置连线变量完成任务
	Map<String, Object> variables = new HashMap<String, Object>();
	Map<String,Object> var =  taskService.getVariables(taskId);
	if(value.equals("1")) {
		//获取技术总监
		String sjldCode = businessXNDao.getLeaderUserCode("",CpsConstant.ROLE_JSZJ);
		variables.put("JszjCODE", sjldCode);
	}else {
		System.out.println("-------------------"+var.get("StaffCODE"));
	}
	
	variables.put(lineVariable, value);
	System.out.println("------------->"+taskService.getVariables(taskId));
	taskService.complete(taskId, variables);		
  }
  
	/**
	 * 通用环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 */
@Transactional
public void dealJSZJAct(String taskId, String lineVariable, String value, String businessKey, String optionContent) throws SerialException, SQLException {
	SysYh user = AppUtil.getCurrentUser();
	//根据页面选择的操作设置连线变量完成任务
	Map<String, Object> variables = new HashMap<String, Object>();
	Map<String,Object> var =  taskService.getVariables(taskId);
	if(value.equals("1")) {
		//获取技术总监
		String sjldCode = businessXNDao.getLeaderUserCode("",CpsConstant.ROLE_RLB);
		variables.put("rlbCODE", sjldCode);
	}else {
		System.out.println("-------------------"+var.get("BmjlCODE"));
	}
	
	variables.put(lineVariable, value);
	System.out.println("------------->"+taskService.getVariables(taskId));
	taskService.complete(taskId, variables);		
}

	/**
	 * 通用环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 */
	@Transactional
	public void dealRlbAct(String taskId, String lineVariable, String value, String businessKey, String optionContent) throws SerialException, SQLException {
	SysYh user = AppUtil.getCurrentUser();
	//根据页面选择的操作设置连线变量完成任务
	Map<String, Object> variables = new HashMap<String, Object>();
	Map<String,Object> var =  taskService.getVariables(taskId);
	

	variables.put(lineVariable, value);
	taskService.complete(taskId, variables);		
	}

  
	/**
	 * 通用环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 */
@Transactional
public void dealBossAct(String taskId, String lineVariable, String value, String businessKey, String optionContent) throws SerialException, SQLException {
	SysYh user = AppUtil.getCurrentUser();
	//根据页面选择的操作设置连线变量完成任务
	Map<String, Object> variables = new HashMap<String, Object>();
	
	variables.put(lineVariable, value);
	taskService.complete(taskId, variables);		
}
  
	
}
