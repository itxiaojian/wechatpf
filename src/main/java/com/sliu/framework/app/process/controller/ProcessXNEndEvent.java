package com.sliu.framework.app.process.controller;


import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sliu.framework.app.process.controller.ProcessXNController;
import com.sliu.framework.app.process.dao.ProcessApiXNDao;
import com.sliu.framework.app.process.service.BusinessXNService;


/**
 * 流程结束后触发事件
 * @author zhangyi
 */
@Component
public class ProcessXNEndEvent implements JavaDelegate {

	  @Autowired
	  private BusinessXNService businessService;
//	  @Autowired
//	  private ProcessXNController processController;
	  @Autowired
	  private ProcessApiXNDao processApiDao;
	  @SuppressWarnings("deprecation")
	  @Override
	  public void execute(DelegateExecution execution) throws Exception {
		  HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		  String businessKey = execution.getBusinessKey();
		  String procInstId = execution.getProcessInstanceId();
		  String currentActId = execution.getCurrentActivityId();
		  if(StringUtils.hasText(currentActId)) {//正常结束的流程才执行以下业务逻辑,如果currentActId为NULL说明是强制结束的流程
			  //流程结束后更新业务主表状态
//			  businessService.updateTestProcess(businessKey);
			 
			  
			  //附件表暂时不移至历史表，如果要移CPS_LOAN_ATTACHMENT里面的数据也得移
//			  businessDao.transferAttachmentToHis(businessKey);			  
		  }
	  }	
   
}
