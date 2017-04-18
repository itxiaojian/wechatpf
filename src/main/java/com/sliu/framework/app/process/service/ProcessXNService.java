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

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.process.dao.BusinessXNDao;
import com.sliu.framework.app.process.dao.ProcessApiXNDao;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;


/**
 * @author zhangyi
 */
@Service
public class ProcessXNService {

    @Autowired
	private RuntimeService runtimeService;
	  
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private BusinessXNDao businessDao;
		
	@Autowired
	private ProcessApiXNDao processApiDao;
	/**
	 * 取得系统中已经部署过的流程,与ACT_RE_PROCDEF中数据一致
	 */
	@Transactional
    public Pagination<Map<String, Object>> getDeployProcessList(Integer start, Integer limit) {
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc();
        long totalRecords = processDefinitionQuery.count();
        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(start, limit);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        for (ProcessDefinition processDefinition : processDefinitionList) {
        	String deploymentId = processDefinition.getDeploymentId();
        	Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("processDefId", processDefinition.getId());
        	map.put("name", processDefinition.getName());
        	map.put("deploymentId", deploymentId);
        	map.put("key", processDefinition.getKey());
        	map.put("version", processDefinition.getVersion());
        	map.put("category", processDefinition.getCategory());
        	map.put("resourceName", processDefinition.getResourceName());
        	map.put("description", processDefinition.getDescription());
        	map.put("diagramResourceName", processDefinition.getDiagramResourceName());
        	map.put("suspensionState", processDefinition.isSuspended());
        	
        	if(deployment.getDeploymentTime() != null){
        		map.put("deploymentTime", df.format(deployment.getDeploymentTime()));
        	}else{
        		map.put("deploymentTime", deployment.getDeploymentTime());
        	}
        	list.add(map);
        }
		double totalPages = Math.ceil(totalRecords * 1d / limit);
		Pagination<Map<String, Object>> pagination = new Pagination<Map<String, Object>>((long)totalPages, start, limit, totalRecords, list);
		return pagination;
    }
    

	/**
     * 取得运行中的流程实例列表
     */
	public Pagination<Map<String, Object>> getRunningProcessList(Integer start, Integer limit, String bkhr, String khqh) {
		Pagination<Map<String, Object>> pagination = processApiDao.getRunningProcess(start, limit, bkhr, khqh);
		for(Map<String, Object> mapResult: pagination.getResult()) {
			String processDefinitionId = mapResult.get("processDefinitionId").toString();
			String activityId = null;
			if(mapResult.get("activityId")!=null){
				activityId = mapResult.get("activityId").toString();
			}
			if(StringUtils.hasText(activityId)) {
				try{
					ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
				    for (ActivityImpl activityImpl : ((ProcessDefinitionEntity) processDefinition).getActivities()) {
				    	if(activityId.equals(activityImpl.getId())) {
				    		mapResult.put("activityName", activityImpl.getProperty("name"));
					    	break;
				    	}
				    }						
				} catch(Exception e) {
					e.printStackTrace();
				}
			
			}			
		}
		return pagination;
	}

	/**
	 * 挂起流程实例
	 */
	public void suspendProcessInst(String processInstanceId) {
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 激活流程实例
	 */
	public void activateProcessInst(String processInstanceId) {
		 runtimeService.activateProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 根据流程定义ID激活流程,激活的同时把该流程下已经启动的流程实例也全部激活
	 */
	public void activateProcessDefinition(String processDefinitionId) {
		repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
	}
	
	/**
	 * 根据流程定义ID挂起流程,挂起的同时把该流程下已经启动的流程实例也全部挂起
	 * @param processDefinitionId 流程定义ID
	 */
	public void suspendProcessDefinition(String processDefinitionId) {
		repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
	}
	
	/**
	 * 查询当前用户已经处理过的流程
	 * @return
	 */
	public Pagination<Map<String, Object>> getInvolvedProcess(Integer start, Integer limit, String bkhr, String khqh,String rolekind) {
		SysYh user = AppUtil.getCurrentUser();
		String userCode = user.getUsername();
		Pagination<Map<String, Object>> pagination = processApiDao.getInvolvedProcess(start, limit, userCode, bkhr, khqh,rolekind);
		//根据本次分页结果查询出对应的业务数据
		for(Map<String, Object> mapResult: pagination.getResult()) {
			String processInstanceId = mapResult.get("processInstanceId").toString();
			String processDefinitionId = mapResult.get("processDefinitionId").toString();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if(processInstance == null) {
				mapResult.put("processFlag", "1");//流程已结束
			}else {
				String activityId = processInstance.getActivityId();
				mapResult.put("processFlag", "0");//流程运行中
				mapResult.put("activityId", activityId);//当前环节
				if(StringUtils.hasText(activityId)) {
					try{
						ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
					    for (ActivityImpl activityImpl : ((ProcessDefinitionEntity) processDefinition).getActivities()) {
					    	if(activityId.equals(activityImpl.getId())) {
					    		mapResult.put("activityName", activityImpl.getProperty("name"));
						    	break;
					    	}
					    }						
					} catch(Exception e) {
						e.printStackTrace();
					}
				
				}
			}						
		}		
		return pagination;
	}
	
	
	/**
	 * 取得已经运行结束的流程
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getHistoryProcessList(Integer start, Integer limit, String bkhr, String khqh) {
		Pagination<Map<String, Object>> pagination = processApiDao.getHistoryProcess(start, limit, bkhr, khqh);
		return pagination;
	}
	
	/**
	 * 分页查询当前用户发起的流程信息
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getMyProcess(Integer start, Integer limit, String bkhr, String khqh) {
		SysYh user = AppUtil.getCurrentUser();
		String userCode = user.getUsername();
		Pagination<Map<String, Object>> pagination = processApiDao.getMyProcess(start, limit, userCode, bkhr, khqh);
		//根据本次分页结果查询出对应的业务数据
		for(Map<String, Object> mapResult: pagination.getResult()) {
			String processInstanceId = mapResult.get("processInstanceId").toString();
			String processDefinitionId = mapResult.get("processDefinitionId").toString();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if(processInstance == null) {
				mapResult.put("processFlag", "1");//流程已结束
			}else {
				mapResult.put("processFlag", "0");//流程运行中
				String activityId = processInstance.getActivityId();
				mapResult.put("activityId", activityId);//当前环节
				try{
					ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
				    for (ActivityImpl activityImpl : ((ProcessDefinitionEntity) processDefinition).getActivities()) {
				    	if(activityId.equals(activityImpl.getId())) {
				    		mapResult.put("activityName", activityImpl.getProperty("name"));
					    	break;
				    	}
				    }						
				} catch(Exception e) {
					e.printStackTrace();
				}				
			}						
		}		
		return pagination;		
	}
	

	/**
	 * 根据流程实例ID查找运行中流程的运行轨迹
	 * @param processInstanceId
	 * @return
	 */
	public InputStream showProcessImage(String processInstanceId) {
		RepositoryServiceImpl repositoryServiceImpl = (RepositoryServiceImpl) repositoryService; 
		//需高亮显示的环节
		List<String> highLightedActivities = runtimeService.getActiveActivityIds(processInstanceId);
		//需高亮显示的连线
		List<String> highLightedFlows = new ArrayList<String>();
		HistoricProcessInstance  historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryServiceImpl.getDeployedProcessDefinition(processDefinitionId);
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        highLightedFlows = getHighLightedFlows(processDefinition, processInstanceId);
		InputStream inputStream = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities, highLightedFlows);
		return inputStream;
	}
	
	/**
	 * 根据流程实例ID查找结束流程的运行轨迹
	 * @param processInstanceId
	 * @return
	 */
	public InputStream showHistoryProcessImage(String processInstanceId) {
		RepositoryServiceImpl repositoryServiceImpl = (RepositoryServiceImpl) repositoryService; 
		//需高亮显示的环节
		List<String> highLightedActivities = new ArrayList<String>();
		//需高亮显示的连线
		List<String> highLightedFlows = new ArrayList<String>();
		HistoricProcessInstance  historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryServiceImpl.getDeployedProcessDefinition(processDefinitionId);
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).list();
        for (HistoricActivityInstance historicActivityInstance : activityInstances) {
            String historicActivityId = historicActivityInstance.getActivityId();
            highLightedActivities.add(historicActivityId);
        }
        highLightedFlows = getHighLightedFlows(processDefinition, processInstanceId);
		InputStream inputStream = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities, highLightedFlows);
		return inputStream;
	}
	
	/**
	 * getHighLightedFlows
	 * 
	 * @param processDefinition
	 * @param processInstanceId
	 * @return
	 */
	private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinition, String processInstanceId) {
	    
	    List<String> highLightedFlows = new ArrayList<String>();
	    
	    List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery()
	        .processInstanceId(processInstanceId)
	        //order by startime asc is not correct. use default order is correct.
	        //.orderByHistoricActivityInstanceStartTime().asc()/*.orderByActivityId().asc()*/
	        .list();
	      
	    LinkedList<HistoricActivityInstance> hisActInstList = new LinkedList<HistoricActivityInstance>();
	    hisActInstList.addAll(historicActivityInstances);
	      
	    getHighlightedFlows(processDefinition.getActivities(), hisActInstList, highLightedFlows);
	    
	    return highLightedFlows;
	}	
	
	/**
	 * getHighlightedFlows
	 * 
	 * code logic: 
	 * 1. Loop all activities by id asc order;
	 * 2. Check each activity's outgoing transitions and eventBoundery outgoing transitions, if outgoing transitions's destination.id is in other executed activityIds, add this transition to highLightedFlows List;
	 * 3. But if activity is not a parallelGateway or inclusiveGateway, only choose the earliest flow.
	 * 
	 * @param activityList
	 * @param hisActInstList
	 * @param highLightedFlows
	 */
	private void getHighlightedFlows(List<ActivityImpl> activityList, LinkedList<HistoricActivityInstance> hisActInstList, List<String> highLightedFlows){
	    
	    //check out startEvents in activityList
	    List<ActivityImpl> startEventActList = new ArrayList<ActivityImpl>();
	    Map<String, ActivityImpl> activityMap = new HashMap<String, ActivityImpl>(activityList.size());
	    for(ActivityImpl activity : activityList){
	        
	      activityMap.put(activity.getId(), activity);
	      
	      String actType = (String) activity.getProperty("type");
	      if (actType != null && actType.toLowerCase().indexOf("startevent") >= 0){
	        startEventActList.add(activity);
	      }
	    }
	  
	    //These codes is used to avoid a bug: 
	    //ACT-1728 If the process instance was started by a callActivity, it will be not have the startEvent activity in ACT_HI_ACTINST table 
	    //Code logic:
	    //Check the first activity if it is a startEvent, if not check out the startEvent's highlight outgoing flow.
	    HistoricActivityInstance firstHistActInst = hisActInstList.getFirst();
	    String firstActType = (String) firstHistActInst.getActivityType();
	    if (firstActType != null && firstActType.toLowerCase().indexOf("startevent") < 0){
	      PvmTransition startTrans = getStartTransaction(startEventActList, firstHistActInst);
	      if (startTrans != null){
	        highLightedFlows.add(startTrans.getId());
	      }
	    } 
	      
	    while (hisActInstList.size() > 0) {
	      HistoricActivityInstance histActInst = hisActInstList.removeFirst();
	      ActivityImpl activity = activityMap.get(histActInst.getActivityId());
	      if (activity != null) {
	        boolean isParallel = false;
	        String type = histActInst.getActivityType();
	        if ("parallelGateway".equals(type) || "inclusiveGateway".equals(type)){
	          isParallel = true;
	        } else if ("subProcess".equals(histActInst.getActivityType())){
	          getHighlightedFlows(activity.getActivities(), hisActInstList, highLightedFlows);
	        }
	        
	        List<PvmTransition> allOutgoingTrans = new ArrayList<PvmTransition>();
	        allOutgoingTrans.addAll(activity.getOutgoingTransitions());
	        allOutgoingTrans.addAll(getBoundaryEventOutgoingTransitions(activity));
	        List<String> activityHighLightedFlowIds = getHighlightedFlows(allOutgoingTrans, hisActInstList, isParallel);
	        highLightedFlows.addAll(activityHighLightedFlowIds);
	      }
	    }
	}
	

	/**
	 * Check out the outgoing transition connected to firstActInst from startEventActList
	 * 
	 * @param startEventActList
	 * @param firstActInst
	 * @return
	 */
	private PvmTransition getStartTransaction(List<ActivityImpl> startEventActList, HistoricActivityInstance firstActInst){
	    for (ActivityImpl startEventAct: startEventActList) {
	      for (PvmTransition trans : startEventAct.getOutgoingTransitions()) {
	        if (trans.getDestination().getId().equals(firstActInst.getActivityId())) {
	          return trans;
	        }
	      }
	    }
	    return null;
	}
	
	/**
	 * getBoundaryEventOutgoingTransitions
	 * 
	 * @param activity
	 * @return
	 */
	private List<PvmTransition> getBoundaryEventOutgoingTransitions(ActivityImpl activity){
	    List<PvmTransition> boundaryTrans = new ArrayList<PvmTransition>();
	    for(ActivityImpl subActivity : activity.getActivities()){
	      String type = (String)subActivity.getProperty("type");
	      if(type!=null && type.toLowerCase().indexOf("boundary")>=0){
	        boundaryTrans.addAll(subActivity.getOutgoingTransitions());
	      }
	    }
	    return boundaryTrans;
	}
	
	/**
	 * find out single activity's highlighted flowIds
	 * 
	 * @param activity
	 * @param hisActInstList
	 * @param isExclusive if true only return one flowId(Such as exclusiveGateway, BoundaryEvent On Task)
	 * @return
	 */
	private List<String> getHighlightedFlows(List<PvmTransition> pvmTransitionList, LinkedList<HistoricActivityInstance> hisActInstList, boolean isParallel){
	    
	    List<String> highLightedFlowIds = new ArrayList<String>();
		    
	    PvmTransition earliestTrans = null;
	    HistoricActivityInstance earliestHisActInst = null;
	    
	    for (PvmTransition pvmTransition : pvmTransitionList) {
	                        
	      String destActId = pvmTransition.getDestination().getId();
	      HistoricActivityInstance destHisActInst = findHisActInst(hisActInstList, destActId);
	      if (destHisActInst != null) {
	        if (isParallel) {
	          highLightedFlowIds.add(pvmTransition.getId());
	        } else if (earliestHisActInst == null || (earliestHisActInst.getId().compareTo(destHisActInst.getId()) > 0)) {
	          earliestTrans = pvmTransition;
	          earliestHisActInst = destHisActInst;
	        }
	      }
	    }
	    
	    if ((!isParallel) && earliestTrans!=null){
	      highLightedFlowIds.add(earliestTrans.getId());
	    }
	    
	    return highLightedFlowIds;
		}
		
		private HistoricActivityInstance findHisActInst(LinkedList<HistoricActivityInstance> hisActInstList, String actId){
	    for (HistoricActivityInstance hisActInst : hisActInstList){
	      if (hisActInst.getActivityId().equals(actId)){
	        return hisActInst;
	      }
	    }
	    return null;
		}	
		
		/**
		 * 强行结束流程,结束的同时逻辑删除对应信息
		 * @author zhangyi
		 * @since  2015-4-23
		 * @param processInstanceId
		 * @param deleteReason
		 * @param bkhr
		 * @param khqh
		 * @param bdlcid
		 * @param tname
		 */
		@Transactional
		public void forceOverProcess(String processInstanceId, String deleteReason, String bkhr, String khqh, String bdlcid, String tname) {
			runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
			businessDao.forceProcess(bkhr,khqh,bdlcid,tname);
		}	
		
		
}
