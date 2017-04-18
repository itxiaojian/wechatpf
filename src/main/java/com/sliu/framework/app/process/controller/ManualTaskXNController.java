/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sliu.framework.app.process.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.process.dao.BusinessXNDao;
import com.sliu.framework.app.process.service.ManualTaskXNService;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;

/**
 * @author zhangyi
 */
@Controller
@RequestMapping(value = "/cps/process/taskXN")
public class ManualTaskXNController extends BaseController {

	@Autowired
	private ManualTaskXNService manualTaskService;

	@Autowired
	private FormService formService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private BusinessXNDao businessDao;
	

	@RequestMapping(value = "/pendWorkPoolIndex")
	public String openPendWorkOrderPoolPage() {
		return "/cps/process/pendWorkOrderPool";
	}

	// 审批技术总监页面
	@RequestMapping(value = "/pendWorkPoolIndexJSZJ")
	public String openPendWorkOrderPoolPageJSZJ() {
		return "/cps/process/pendWorkOrderPoolJSZJ";
	}

	// 审批市场人员页面
	@RequestMapping(value = "/pendWorkPoolIndexSCRY")
	public String openPendWorkOrderPoolPageSCEY() {
		return "/cps/process/pendWorkOrderPoolSCRY";
	}

	// 审批部门经理页面
	@RequestMapping(value = "/pendWorkPoolIndexBMJL")
	public String openPendWorkOrderPoolPageBMJL() {
		return "/cps/process/pendWorkOrderPoolBMJL";
	}

	// 审批项目经理页面
	@RequestMapping(value = "/pendWorkPoolIndexXMJL")
	public String openPendWorkOrderPoolPageXMJL() {
		return "/cps/process/pendWorkOrderPoolXMJL";
	}

	// 审批普通员工页面
	@RequestMapping(value = "/pendWorkPoolIndexPTYG")
	public String openPendWorkOrderPoolPagePTYG() {
		return "/cps/process/pendWorkOrderPoolPTYG";
	}
	
	// 修改自己被退回的绩效
	@RequestMapping(value = "/pendWorkPoolIndexTH")
	public String openPendWorkOrderPoolPageTH() {
		return "/cps/process/pendWorkOrderPoolTH";
	}


	/**
	 * 根据传入的任务ID找到对应的FormKey并打开此FormKey指向的页面,
	 * 同时取得该任务环节配置的FORM变量(即此环节后面的连线变量),传至前台
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/openTaskDealPage/{taskId}/{businessKey}/{khqh}/{bkhr}/{rolekind}")
	public ModelAndView openTaskDealPage(@PathVariable String taskId,
			@PathVariable String businessKey, @PathVariable String khqh,
			@PathVariable String bkhr, @PathVariable String rolekind) {
		String orgCode = "";//部门编号
		String khgw = "";//岗位编号
		Map<String, Object> var = taskService.getVariables(taskId);
		if (var.get("orgCode") != null) {
			orgCode = var.get("orgCode").toString();
		}
		
		if (var.get("gwbh") != null) {
			khgw = var.get("gwbh").toString();
		}
		SysYh user = AppUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("bkhr",bkhr);//被考核人编号
		modelAndView.addObject("userCode", user.getUsername());
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
		String formKey = taskFormData.getFormKey();
		List<FormProperty> listProperty = taskFormData.getFormProperties();
		if (!listProperty.isEmpty()) {
			String lineVar = listProperty.get(0).getId();//获取传入下层的连线值
			modelAndView.addObject("lineVar", lineVar);
		}
		String url = "";
		
		if("TH".equalsIgnoreCase(rolekind)){
			rolekind = businessDao.getUserRoleByUserCode(user.getUsername());
			bkhr = user.getUsername();
		}
			
			
		modelAndView.setViewName(url);
		// 查询对应业务数据传至处理页面
		if (StringUtils.hasText(businessKey)) {
			modelAndView.addObject("businessKey", businessKey);
			Map<String, Object> mapBusi = businessDao
					.getTestProcess(businessKey);
			if (mapBusi != null) {
				modelAndView.addObject("mapBusi", mapBusi);
			}
		}
		return modelAndView;
	}

	// /**
	// * 根据传入的任务ID找到对应的FormKey并打开此FormKey指向的页面,
	// * 同时取得该任务环节配置的FORM变量(即此环节后面的连线变量),传至前台
	// * @param taskId
	// * @return
	// */
	// @RequestMapping(value = "/openTaskDealPage/{taskId}/{businessKey}")
	// public ModelAndView openTaskDealPage(@PathVariable String taskId,
	// @PathVariable String businessKey) {
	// SysYh user = AppUtil.getCurrentUser();
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.addObject("userCode", user.getUsername());
	// TaskFormData taskFormData = formService.getTaskFormData(taskId);
	// String formKey = taskFormData.getFormKey();
	// List<FormProperty> listProperty = taskFormData.getFormProperties();
	// if(!listProperty.isEmpty()) {
	// String lineVar = listProperty.get(0).getId();
	// modelAndView.addObject("lineVar", lineVar);
	// }
	// modelAndView.setViewName(formKey);
	// //查询对应业务数据传至处理页面
	// if(StringUtils.hasText(businessKey)) {
	// modelAndView.addObject("businessKey", businessKey);
	// Map<String, Object> mapBusi = businessDao.getTestProcess(businessKey);
	// if(mapBusi != null) {
	// modelAndView.addObject("mapBusi", mapBusi);
	// }
	// }
	// return modelAndView;
	// }

	/**
	 * 根据当前登陆用户取得该用户的待办任务列表
	 */
	@RequestMapping(value = "/getPendPoolList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getPendPoolList(Integer start,
			Integer limit, String rolekind) {
		return manualTaskService.getPendPoolList(start, limit, rolekind);
	}

	/**
	 * 当前登陆用户签收任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 */
	@RequestMapping(value = "/claimTask", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData claimTask(String taskId) {
		manualTaskService.claimTask(taskId);
		return ResponseData.SUCCESS_NO_DATA;
	}

	/**
	 * 根据流程实例ID和是否已经结束流程标志动态显示流程图及运行轨迹
	 * 
	 * @param processInstanceId
	 *            流程实例ID
	 * @param hisFlag
	 *            是否历史流程 1:是 , 0:否
	 * @return
	 */
	@RequestMapping(value = "/showProcessTrack")
	public ModelAndView showProcessTrack(String processInstanceId,
			Integer hisFlag) {
		String showProcessUrl = "";
		ModelAndView modelAndView = new ModelAndView(
				"/cps/process/showProcessTrack");
		if (hisFlag == 1) {
			showProcessUrl = "/cps/processXN/showHistoryProcessImage?processInstanceId="
					+ processInstanceId;
		} else if (hisFlag == 0) {
			List<Map<String, Object>> runningAct = new ArrayList<Map<String, Object>>();
			showProcessUrl = "/cps/processXN/showProcessImage?processInstanceId="
					+ processInstanceId;
			List<HistoricActivityInstance> activityInstances = historyService
					.createHistoricActivityInstanceQuery()
					.processInstanceId(processInstanceId).orderByActivityId()
					.asc().list();
			for (HistoricActivityInstance historicActivityInstance : activityInstances) {
				if (historicActivityInstance.getEndTime() == null) {// 节点正在运行中
					Boolean flag = true;
					Map<String, Object> tempMap = new HashMap<String, Object>();
					tempMap.put("actName",
							historicActivityInstance.getActivityName());
					if (runningAct.size() > 0) {
						for (Map<String, Object> map : runningAct) {
							if (map.get("actName")
									.toString()
									.equalsIgnoreCase(
											historicActivityInstance
													.getActivityName())) {
								flag = false;
							}
						}
					}
					if (flag) {
						runningAct.add(tempMap);
					}
				}
			}
			modelAndView.addObject("runningAct", runningAct);
		}
		modelAndView.addObject("showProcessUrl", showProcessUrl);
		return modelAndView;
	}

}
