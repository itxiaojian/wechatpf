package com.sliu.framework.app.test.controllor;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.Result;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.process.model.TestProcess;
import com.sliu.framework.app.process.support.ProcessXNUtils;
import com.sliu.framework.app.test.service.TestProcessBO;
import com.sliu.framework.app.test.service.TestProcessService;
import com.sliu.framework.app.util.ResponseData;


@Controller
@RequestMapping(value="/cps/test")
public class TestProcessController extends BaseController {
	@Autowired
	private TestProcessService testProcessService;
	
	@Autowired
	private TestProcessBO testProcessBO;
	
	@RequestMapping(value="/testProcessPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/cps/test/testProcess");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getAllTestProcess")
	@ResponseBody
	public Pagination<Object> getAllTestProcess(Integer start, Integer limit) {
		Pagination<Object> list = testProcessService.getAllTestProcess(start, limit);
		return list;
	}
	

//	@RequestMapping(value = "/saveTestProcess", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseData saveTestProcess(TestProcess fbt) {
//		testProcessService.saveTestProcess(fbt);
//		return ResponseData.SUCCESS_NO_DATA;
//	}
	
	/**
	 * 添加测试流程
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveTestProcess", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model,
			TestProcess entity,
			HttpServletRequest request, HttpServletResponse response) {
	
		Result result = testProcessBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改测试流程
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateTestProcess", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ModelMap model,
			TestProcess entity,
			HttpServletRequest request, HttpServletResponse response) {
		Result result = testProcessBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	@RequestMapping(value = "/deleteTestProcess", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(String[] ids) {
		for (String id : ids) {
			testProcessBO.remove(id);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 启动流程
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/openTestProcess", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData openTestProcess(String id) {

		String result = ProcessXNUtils.startJXKHFlow("", id+"");
		if(result.equalsIgnoreCase("success")){
			return ResponseData.SUCCESS_NO_DATA;
		}else{
			return ResponseData.FAILED_QD_ACTIVITI;
		}
		
	}
	
	/**
	 * 通用环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 */
	@RequestMapping(value = "/dealAct", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData dealAct(String taskId, String lineVariable, String value, String businessKey, String otherOption,
			String optionContent, BigDecimal creditSumW, String creditRate, Integer creditYear, String signData,String writeData) {
		try {
			testProcessService.dealAct(taskId, lineVariable, value, businessKey, optionContent);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 技术总监审核环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 */
	@RequestMapping(value = "/dealJSZJAct", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData dealJSZJAct(String taskId, String lineVariable, String value, String businessKey, String otherOption,
			String optionContent, BigDecimal creditSumW, String creditRate, Integer creditYear, String signData,String writeData) {
		try {
			testProcessService.dealJSZJAct(taskId, lineVariable, value, businessKey, optionContent);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 人力部审核环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 */
	@RequestMapping(value = "/dealRlbAct", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData dealRlbAct(String taskId, String lineVariable, String value, String businessKey, String otherOption,
			String optionContent, BigDecimal creditSumW, String creditRate, Integer creditYear, String signData,String writeData) {
		try {
			testProcessService.dealRlbAct(taskId, lineVariable, value, businessKey, optionContent);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 通用环节处理逻辑
	 * @param taskId 工作项任务ID
	 * @param lineVariable 当前环节下一连线设置变量
	 * @param value 连线变量值 (1:同意  0:不同意)
	 * @return
	 */
	@RequestMapping(value = "/dealBossAct", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData dealBossAct(String taskId, String lineVariable, String value, String businessKey, String otherOption,
			String optionContent, BigDecimal creditSumW, String creditRate, Integer creditYear, String signData,String writeData) {
		try {
			testProcessService.dealBossAct(taskId, lineVariable, value, businessKey, optionContent);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ResponseData.SUCCESS_NO_DATA;
	}
}
