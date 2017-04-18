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

package com.sliu.framework.app.process.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.process.dao.BusinessXNDao;
import com.sliu.framework.app.process.support.ProcessXNUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
 * @author zhangyi
 */
@Service
public class ManualTaskXNService {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private BusinessXNDao businessDao;
	/**
	 * 根据当前登陆用户取得该用户的待办任务列表,包括流程直接分配到个人的和分配到组未签收的
	 */
	public List<Map<String, Object>> getPendPoolList(Integer start, Integer limit,String rolekind) {	
		SysYh user = AppUtil.getCurrentUser();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Task> tasks = new ArrayList<Task>();
		String userCode = user.getUsername();
//		userCode = "kermit";
		//取得直接分配给个人的任务
		List<Task> tasksAssignee = taskService.createTaskQuery().taskAssignee(userCode).active().processDefinitionKey(ProcessXNUtils.bzProcessKey).orderByTaskCreateTime().desc().list();
		//取得分配给当前登陆用户所属组的任务
		List<Task> taskCandidateGroup = taskService.createTaskQuery().taskCandidateUser(userCode).active().processDefinitionKey(ProcessXNUtils.bzProcessKey).orderByTaskCreateTime().desc().list();
		tasks.addAll(tasksAssignee);
		tasks.addAll(taskCandidateGroup);
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); 
		for(Task task:tasks) {
			Map<String, Object> map = new HashMap<String, Object>();
			String processInstanceId = task.getProcessInstanceId();
		    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
		    String businessKey = processInstance.getBusinessKey();
		    

		    //流程相关数据
			map.put("taskId", task.getId());
			map.put("processDefinitionId", task.getProcessDefinitionId());
			map.put("processInstanceId", task.getProcessInstanceId());
			map.put("name", task.getName());
			map.put("taskDefinitionKey", task.getTaskDefinitionKey());
			map.put("assignee", task.getAssignee());
			map.put("owner", task.getOwner());
			map.put("priority", task.getPriority());
			if(task.getCreateTime() != null){
				map.put("createTime", sdf.format(task.getCreateTime()));
			}else{
				map.put("createTime", task.getCreateTime());
			}

			if(task.getDueDate() != null){
				map.put("dueDate", sdf.format(task.getDueDate()));
			}else{
				map.put("dueDate", task.getDueDate());
			}
			map.put("description", task.getDescription());
			
			Map<String,Object> var = taskService.getVariables(task.getId());
			String bkhr = "";
			String bdlcid = "";
			String orgCode = "";
			if(var.get("StaffCODE")!= null){
				bkhr= var.get("StaffCODE").toString();
			}
			if(var.get("businessKey")!= null){
				bdlcid= var.get("businessKey").toString();
			}
			if(var.get("orgCode")!= null){
				orgCode= var.get("orgCode").toString();
			}
		    //业务数据
		    if(StringUtils.hasText(businessKey)) {
		    	map.put("businessKey", businessKey);
//			    List<Map<String, Object>> listBusi = businessDao.getListTestProcess(businessKey);
			    List<Map<String, Object>> listBusi = businessDao.getListProcessByRoleKind(businessKey,rolekind,bkhr,bdlcid,orgCode);
			    if(!listBusi.isEmpty()) {
			    	Map<String, Object> mapBusi = listBusi.get(0);
			    	map.put("khqh", mapBusi.get("khqh"));
			    	map.put("bkhr", mapBusi.get("bkhr"));
			    	map.put("df", mapBusi.get("df"));
			    	map.put("xm", mapBusi.get("xm"));
			    	list.add(map);
			    }		    	
		    }
			
		}
		return list;
	}
	
	/**
	 * 当前登陆用户签收任务
	 * @param taskId 任务ID
	 * @return
	 */
	public void claimTask(String taskId) {
		SysYh user = AppUtil.getCurrentUser();
		String userCode = user.getUsername();
		taskService.claim(taskId, userCode);
	}

}
