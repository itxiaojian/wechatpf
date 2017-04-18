package com.sliu.framework.app.process.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.process.model.TestProcess;
import com.sliu.framework.app.process.support.ProcessXNUtils;

/**
 * 授信审批流程相关
 * @author zhangyi
 */
@Repository
public class ProcessApiXNDao extends HibernateBaseDaoImpl<TestProcess, Long>{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 查询当前用户已经处理过的任务
	 * @return
	 */
	public Pagination<Map<String,Object>> getInvolvedProcess(Integer start, Integer limit, String userCode, String bkhr, String khqh,String rolekind) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ASSIGNEE_", userCode);
		paramMap.put("KEY_", ProcessXNUtils.bzProcessKey);
		String key =ProcessXNUtils.bzProcessKey;
		StringBuffer sql = new StringBuffer("");
//		if(bkhr==null){
//			bkhr="";
//		}
//		if(khqh == null){
//			khqh = "";
//		}
		if("ROLE_PTYG".equalsIgnoreCase(rolekind)){
			sql = new StringBuffer(
					"select distinct t.PROC_DEF_ID_ as \"processDefinitionId\"," +
					"t.PROC_INST_ID_ as \"processInstanceId\", t.TASK_DEF_KEY_ as \"taskDefinitionKey\", " +
					"t.NAME_ as \"taskName\",DATE_FORMAT(b.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\", DATE_FORMAT(b.END_TIME_,'%Y-%m-%d %H:%i:%S')  as \"endTime\",b.BUSINESS_KEY_ as \"businessKey\"," +
					"m.xm as \"xm\",c.KHQH as \"khqh\",c.BKHR as \"bkhr\",c.DF as \"df\",c.SHZT as \"shzt\" " +
					" from ACT_HI_TASKINST t" +
					" left join ACT_HI_PROCINST b on t.PROC_INST_ID_ = b.PROC_INST_ID_" +
					" left join ACT_RE_PROCDEF g on b.PROC_DEF_ID_ = g.ID_" +
					" inner join zs_khjg_all c on b.BUSINESS_KEY_ = c.BDLCID and c.zl='PTYG'"+ 
					" left join sys_yh m ON c.bkhr = m.dlm " +
					" where t.ASSIGNEE_ = :ASSIGNEE_ and g.KEY_=:KEY_ " 
//					+
//					" and c.bkhr like '%"+bkhr+"%' and c.khqh like '%"+khqh+"%' order by b.START_TIME_ desc"
					);
		}else if("ROLE_XMJL".equalsIgnoreCase(rolekind)){
			sql = new StringBuffer(
					"select distinct t.PROC_DEF_ID_ as \"processDefinitionId\"," +
					"t.PROC_INST_ID_ as \"processInstanceId\", t.TASK_DEF_KEY_ as \"taskDefinitionKey\", " +
					"t.NAME_ as \"taskName\",DATE_FORMAT(b.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\", DATE_FORMAT(b.END_TIME_,'%Y-%m-%d %H:%i:%S')  as \"endTime\",b.BUSINESS_KEY_ as \"businessKey\"," +
					"m.xm as \"xm\",c.KHQH as \"khqh\",c.BKHR as \"bkhr\",c.DF as \"df\",c.SHZT as \"shzt\" " +
					" from ACT_HI_TASKINST t" +
					" left join ACT_HI_PROCINST b on t.PROC_INST_ID_ = b.PROC_INST_ID_" +
					" left join ACT_RE_PROCDEF g on b.PROC_DEF_ID_ = g.ID_" +
					" inner join zs_khjg_all c on b.BUSINESS_KEY_ = c.BDLCID and c.zl='XMJL'" + 
					" left join sys_yh m ON c.bkhr = m.dlm " +
					" where t.ASSIGNEE_ = :ASSIGNEE_ and g.KEY_=:KEY_ " 
					);			
		}else if("ROLE_BMJL".equalsIgnoreCase(rolekind)){
			sql = new StringBuffer(
					"select distinct t.PROC_DEF_ID_ as \"processDefinitionId\"," +
					"t.PROC_INST_ID_ as \"processInstanceId\", t.TASK_DEF_KEY_ as \"taskDefinitionKey\", " +
					"t.NAME_ as \"taskName\",DATE_FORMAT(b.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\", DATE_FORMAT(b.END_TIME_,'%Y-%m-%d %H:%i:%S')  as \"endTime\",b.BUSINESS_KEY_ as \"businessKey\"," +
					"m.xm as \"xm\",c.KHQH as \"khqh\",c.BKHR as \"bkhr\",c.DF as \"df\",c.SHZT as \"shzt\" " +
					" from ACT_HI_TASKINST t" +
					" left join ACT_HI_PROCINST b on t.PROC_INST_ID_ = b.PROC_INST_ID_" +
					" left join ACT_RE_PROCDEF g on b.PROC_DEF_ID_ = g.ID_" +
					" inner join zs_khjg_all c on b.BUSINESS_KEY_ = c.BDLCID and c.zl='BMJL'" +
					" left join sys_yh m ON c.bkhr = m.dlm " +
					" where t.ASSIGNEE_ = :ASSIGNEE_ and g.KEY_=:KEY_ " 
					);			
		}else if("ROLE_SCRY".equalsIgnoreCase(rolekind)){
			sql = new StringBuffer(
					"select distinct t.PROC_DEF_ID_ as \"processDefinitionId\"," +
					"t.PROC_INST_ID_ as \"processInstanceId\", t.TASK_DEF_KEY_ as \"taskDefinitionKey\", " +
					"t.NAME_ as \"taskName\",DATE_FORMAT(b.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\", DATE_FORMAT(b.END_TIME_,'%Y-%m-%d %H:%i:%S')  as \"endTime\",b.BUSINESS_KEY_ as \"businessKey\"," +
					"m.xm as \"xm\",c.KHQH as \"khqh\",c.BKHR as \"bkhr\",c.DF as \"df\",c.SHZT as \"shzt\" " +
					" from ACT_HI_TASKINST t" +
					" left join ACT_HI_PROCINST b on t.PROC_INST_ID_ = b.PROC_INST_ID_" +
					" left join ACT_RE_PROCDEF g on b.PROC_DEF_ID_ = g.ID_" +
					" inner join zs_khjg_all c on b.BUSINESS_KEY_ = c.BDLCID and c.zl='SCRY'" +
					" left join sys_yh m ON c.bkhr = m.dlm " +
					" where t.ASSIGNEE_ = :ASSIGNEE_ and g.KEY_=:KEY_ " 
					);			
		}else if("ROLE_JSZJ".equalsIgnoreCase(rolekind)){
			sql = new StringBuffer(
					"select distinct t.PROC_DEF_ID_ as \"processDefinitionId\"," +
					"t.PROC_INST_ID_ as \"processInstanceId\", t.TASK_DEF_KEY_ as \"taskDefinitionKey\", " +
					"t.NAME_ as \"taskName\",DATE_FORMAT(b.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\", DATE_FORMAT(b.END_TIME_,'%Y-%m-%d %H:%i:%S')  as \"endTime\",b.BUSINESS_KEY_ as \"businessKey\"," +
					"m.xm as \"xm\",c.KHQH as \"khqh\",c.BKHR as \"bkhr\",c.DF as \"df\",c.SHZT as \"shzt\" " +
					" from ACT_HI_TASKINST t" +
					" left join ACT_HI_PROCINST b on t.PROC_INST_ID_ = b.PROC_INST_ID_" +
					" left join ACT_RE_PROCDEF g on b.PROC_DEF_ID_ = g.ID_" +
					" inner join zs_khjg_all c on b.BUSINESS_KEY_ = c.BDLCID and c.zl='JSZJ'" +
					" left join sys_yh m ON c.bkhr = m.dlm " +
					" where t.ASSIGNEE_ = :ASSIGNEE_ and g.KEY_=:KEY_ " 
					);			
		}
		
		if(StringUtils.hasText(khqh)) {
			sql.append(" and c.khqh like :khqh");
			paramMap.put("khqh", "%"+khqh+"%");
		}
		if(StringUtils.hasText(bkhr)) {
			sql.append(" and c.bkhr like :bkhr");
			paramMap.put("bkhr", "%"+bkhr+"%");
		}
		sql.append(" order by b.START_TIME_ desc");
		
//		return null;
		
		return jdbcPager.queryPageDb2(sql.toString(), start, limit, paramMap);
	}
	
	/**
	 * 查询当前用户发起的流程数据
	 * @param start
	 * @param limit
	 * @param userCode
	 * @param customerId
	 * @param custName
	 * @return
	 */
	public Pagination<Map<String,Object>> getMyProcess(Integer start, Integer limit, String userCode, String bkhr, String khqh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		paramMap.put("KEY_", ProcessXNUtils.bzProcessKey);
		StringBuffer sql = new StringBuffer("select t.PROC_DEF_ID_ as \"processDefinitionId\",t.PROC_INST_ID_ as \"processInstanceId\"," +
				"t.BUSINESS_KEY_ as \"businessKey\",DATE_FORMAT(t.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\",DATE_FORMAT(t.END_TIME_,'%Y-%m-%d %H:%i:%S') as \"endTime\",t.DELETE_REASON_ as \"deleteReason\"," +
				" g.xm as \"xm\",b.DF as \"df\",b.SHZT as \"shzt\",b.zl as \"zl\",b.tname as \"tname\",b.BDLCID as \"bdlcid\"  " +
				" from ACT_HI_PROCINST t left join ACT_RE_PROCDEF g on t.PROC_DEF_ID_ = g.ID_" +
				" left join zs_khjg_all b on t.BUSINESS_KEY_ = b.BDLCID" + 
				" left join sys_yh g ON f.bkhr = g.dlm" +
				" where t.START_USER_ID_ = :userCode and g.KEY_=:KEY_");
		if(StringUtils.hasText(bkhr)) {
			sql.append(" and b.bkhr like :bkhr");
			paramMap.put("bkhr", "%"+bkhr+"%");
		}
		if(StringUtils.hasText(khqh)) {
			sql.append(" and b.khqh like :khqh");
			paramMap.put("khqh", "%"+khqh+"%");
		}
		sql.append(" order by t.END_TIME_ desc,t.START_TIME_ desc");
//		return null;
		return jdbcPager.queryPageDb2(sql.toString(), start, limit, paramMap);
	}
	
	
	/**
	 * 与业务表关联查询运行中流程
	 * @param start
	 * @param limit
	 * @param customerId 客户号
	 * @param custName 客户名
	 * @return
	 */
	public Pagination<Map<String,Object>> getRunningProcess(Integer start, Integer limit, String bkhr, String khqh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("KEY_", ProcessXNUtils.bzProcessKey);
		StringBuffer sql = new StringBuffer("select e.ACT_ID_ as \"activityId\",e.PROC_DEF_ID_ as \"processDefinitionId\",e.PROC_INST_ID_ as \"processInstanceId\"," +
				" e.BUSINESS_KEY_ as \"businessKey\",DATE_FORMAT(e.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\",DATE_FORMAT(e.END_TIME_,'%Y-%m-%d %H:%i:%S') as \"endTime\"," +
				" e.DELETE_REASON_ as \"deleteReason\",f.KHQH as \"khqh\",f.BKHR as \"bkhr\"," +
				" g.xm as \"xm\",f.DF as \"df\",f.SHZT as \"shzt\",f.zl as \"zl\",f.tname as \"tname\",f.BDLCID as \"bdlcid\"  " +
				" from(select distinct t.*,b.ACT_ID_ from ACT_HI_PROCINST t,ACT_RU_EXECUTION b,ACT_RE_PROCDEF c" +
				" where t.PROC_INST_ID_ = b.PROC_INST_ID_ and t.PROC_DEF_ID_ = c.ID_ and b.ACT_ID_ is not null and c.KEY_ = :KEY_) e" +
				" left join zs_khjg_all f on e.BUSINESS_KEY_ = f.BDLCID " + 
				" left join sys_yh g ON f.bkhr = g.dlm where 1=1");
		
		if(StringUtils.hasText(khqh)) {
			sql.append(" and f.khqh like :khqh");
			paramMap.put("khqh", "%"+khqh+"%");
		}
		if(StringUtils.hasText(bkhr)) {
			sql.append(" and f.bkhr like :bkhr");
			paramMap.put("bkhr", "%"+bkhr+"%");
		}
		sql.append(" order by e.START_TIME_ desc");
//		return null;
		return jdbcPager.queryPageDb2(sql.toString(), start, limit, paramMap);		
	}
	
	/**
	 * 与业务表关联查询历史流程
	 * @param start
	 * @param limit
	 * @param customerId
	 * @param custName
	 * @return
	 */
	public Pagination<Map<String,Object>> getHistoryProcess(Integer start, Integer limit, String bkhr, String khqh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("KEY_", ProcessXNUtils.bzProcessKey);
		StringBuffer sql = new StringBuffer("select e.PROC_DEF_ID_ as \"processDefinitionId\",e.PROC_INST_ID_ as \"processInstanceId\"," +
				"e.BUSINESS_KEY_ as \"businessKey\",DATE_FORMAT(e.START_TIME_,'%Y-%m-%d %H:%i:%S') as \"startTime\",DATE_FORMAT(e.END_TIME_,'%Y-%m-%d %H:%i:%S') as \"endTime\"," +
				"e.DELETE_REASON_ as \"deleteReason\",f.KHQH as \"khqh\",f.BKHR as \"bkhr\"," +
				" g.xm as \"xm\",f.DF as \"df\",f.SHZT as \"shzt\",f.zl as \"zl\",f.tname as \"tname\",f.BDLCID as \"bdlcid\"  " +
				" from(select t.* from ACT_HI_PROCINST t,ACT_RE_PROCDEF k where t.PROC_DEF_ID_ = k.ID_ and k.KEY_=:KEY_" +
				" and not exists(select b.PROC_INST_ID_ from ACT_RU_EXECUTION b where t.PROC_INST_ID_ = b.PROC_INST_ID_)) e" +
				" left join zs_khjg_all f on e.BUSINESS_KEY_ = f.BDLCID " + 
				" left join sys_yh g ON f.bkhr = g.dlm where 1=1");
		if(StringUtils.hasText(bkhr)) {
			sql.append(" and f.bkhr like :bkhr");
			paramMap.put("bkhr", "%"+bkhr+"%");
		}
		if(StringUtils.hasText(khqh)) {
			sql.append(" and f.khqh like :khqh");
			paramMap.put("khqh", "%"+khqh+"%");
		}
		sql.append(" order by e.START_TIME_ desc");
//		return null;
		return jdbcPager.queryPageDb2(sql.toString(), start, limit, paramMap);			
	}
	
	public Integer getAppNum(String ste,String ste1){
		int flag = 0;
		Session session = sessionFactory.openSession();
		String sql = "select nvl(max(APP_NUM),0) from CPS_APPROVAL_NUM where CREATE_TIME >= to_date('"+ste+"',"
				+ "'yyyy-MM-dd hh24:mi:ss') and CREATE_TIME <= to_date('"+ste1+"','yyyy-MM-dd hh24:mi:ss') ";
		List appNumList = session.createSQLQuery(sql).list();
		Object object = appNumList.get(0);
		if(null != object){
			flag = Integer.parseInt(object.toString());
		}
		session.flush();
		session.clear();
		session.close();
		System.out.println(flag);
		return flag;
	}
	}
