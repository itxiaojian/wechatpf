package com.sliu.framework.app.process.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.process.model.TestProcess;
import com.sliu.framework.app.process.support.file.CpsConstant;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

@Repository
public class BusinessXNDao extends HibernateBaseDaoImpl<TestProcess, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> getTestProcess(String businessKey) {
		String sql = "select t.ID as \"pid\",t.NAME as \"name\", t.PROCESSCODE as \"processCode\",t.PROCESS_STATUS as \"processStatus\",t.PROC_INST_ID as \"procInstId\" "
				+ " from TEST_PROCESS t where t.ID = ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				businessKey);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<Map<String, Object>> getListTestProcess(String businessKey) {
		String sql = "select t.ID as \"pid\",t.NAME as \"name\", t.PROCESSCODE as \"processCode\",t.PROCESS_STATUS as \"processStatus\",t.PROC_INST_ID as \"procInstId\" "
				+ " from TEST_PROCESS t where t.ID = ?";
		return jdbcTemplate.queryForList(sql, businessKey);
	}

	/**
	 * 强制结束流程时，删除主表信息
	 * 
	 * @param processInstanceId
	 */
	public void deleteTestProcess(String processInstanceId) {
		String hql = "delete from TestProcess t where t.procInstId = ?";
		this.bulkUpdate(hql, processInstanceId);
	}

	public void updateTestProcess(String processInstanceId, String businessKey) {
		String hql = "update TestProcess t set t.processStatus='2',t.procInstId=? where t.id = ?";
		String sql = "update test_process t set t.process_status='2',t.proc_inst_id='"
				+ processInstanceId + "' where t.id='" + businessKey + "' ";

		this.jdbcTemplate.update(sql);
		// this.bulkUpdate(hql, processInstanceId, businessKey);
	}

	// /**
	// * 得到经理角色
	// * @return
	// */
	// public List<Map<String, Object>> getJlUserList() {
	// String sql = "select t.USER_CODE as \"userCode\""
	// +
	// " from UNTECK_USER t,UNTECK_USER_ROLE c,UNTECK_ROLE d, UNTECK_ORGANIZATION e"
	// +
	// " where t.id=c.USER_ID and c.ROLE_ID = d.id and t.ORG_ID = e.id and d.CODE = '"
	// + CpsConstant.ROLE_SQUAD + "'";
	// return jdbcTemplate.queryForList(sql);
	// }

	/**
	 * 的到经理角色的用户
	 * 
	 * @param userCode
	 * @return
	 */
	public List<Map<String, Object>> getXNBranchUsersByUserCode() {
		String sql = "select a.username as \"userCode\" from sys_user a,sys_role b,sys_user_role c "
				+ "where a.id=c.user_id and b.role_id=c.role_id and b.role_name='"
				+ CpsConstant.ROLE_JL + "'";

		// String sql =
		// "select t.USER_NAME as \"userName\", t.USER_CODE as \"userCode\""
		// +
		// " from UNTECK_USER t,UNTECK_USER_ROLE c,UNTECK_ROLE d, UNTECK_ORGANIZATION e"
		// +
		// " where t.id=c.USER_ID and c.ROLE_ID = d.id and t.ORG_ID = e.id and d.CODE = '"
		// + CpsConstant.ROLE_JL + "'" + " and e.id=" +
		// orgId+" and t.DEL_FLAG = '0'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 的到Boss角色的用户
	 * 
	 * @param userCode
	 * @return
	 */
	public List<Map<String, Object>> getXNBossUserCode() {
		String sql = "select a.username as \"userCode\" from sys_user a,sys_role b,sys_user_role c "
				+ "where a.id=c.user_id and b.role_id=c.role_id and b.role_name='"
				+ CpsConstant.ROLE_BOSS + "'";

		// String sql =
		// "select t.USER_NAME as \"userName\", t.USER_CODE as \"userCode\""
		// +
		// " from UNTECK_USER t,UNTECK_USER_ROLE c,UNTECK_ROLE d, UNTECK_ORGANIZATION e"
		// +
		// " where t.id=c.USER_ID and c.ROLE_ID = d.id and t.ORG_ID = e.id and d.CODE = '"
		// + CpsConstant.ROLE_JL + "'" + " and e.id=" +
		// orgId+" and t.DEL_FLAG = '0'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据用户登录名找出用户的角色
	 * 
	 * @author zhangyi
	 * @since 2015-4-16
	 * @param username
	 * @return
	 */
	public String getUserRoleByUserCode(String username) {
		String sql = "select b.JSMC as \"roleKind\" from SYS_YH a,SYS_JS b,SYS_YHJS c "
				+ "where a.YHBH=c.YHBH and b.JSBH=c.JSBH and a.DLM='"
				+ username + "'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.size() > 0) {
			return list.get(0).get("roleKind").toString();
		}
		return null;
	}

	/**
	 * 获取上级本部门领导code
	 * 
	 * @author zhangyi
	 * @since 2015-4-17
	 * @param userCode
	 * @param orgCode
	 * @param roleBmjl
	 * @return
	 */
	public String getSjldUserCodeInOrgCode(String userCode, String orgCode,
			String roleName) {
		String sql = "select a.DLM as \"userCode\" from SYS_YH a,SYS_JS b,SYS_YHJS c "
				+ "where a.YHBH=c.YHBH and b.JSBH=c.JSBH and a.BMBH='"
				+ orgCode + "' and b.JSMC='" + roleName + "'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.size() > 0) {
			return list.get(0).get("userCode").toString();
		}
		return null;
	}

	/**
	 * 获取领导层code
	 * 
	 * @author zhangyi
	 * @since 2015-4-17
	 * @param userCode
	 * @param roleBmjl
	 * @return
	 */
	public String getLeaderUserCode(String userCode, String roleName) {
		String sql = "select a.DLM as \"userCode\" from SYS_YH a,SYS_JS b,SYS_YHJS c "
				+ "where a.YHBH=c.YHBH and b.JSBH=c.JSBH and b.JSMC='"
				+ roleName + "'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list.size() > 0) {
			return list.get(0).get("userCode").toString();
		}
		return null;
	}

	/**
	 * 根据角色和所在部门查询对应的考核数据
	 * 
	 * @author zhangyi
	 * @since 2015-4-20
	 * @param businessKey
	 * @param rolekind
	 * @param khqh
	 * @param bkhr
	 * @param orgCode 部门编号
	 * @return
	 */
	public List<Map<String, Object>> getListProcessByRoleKind(
			String businessKey, String rolekind, String bkhr, String bdlcid,
			String orgCode) {
		String sql = "";
		SysYh user = AppUtil.getCurrentUser();
		
		if("TH".equalsIgnoreCase(rolekind)){
			rolekind = this.getUserRoleByUserCode(user.getUsername());
			bkhr = user.getUsername();
		}
		
		
		//技术总监角色
		if (rolekind.equalsIgnoreCase("ROLE_JSZJ")) {
			
			sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
					+ "FROM zs_khjg_jszj t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
					+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			
		} else if (rolekind.equalsIgnoreCase("ROLE_SCRY")) {//市场人员
			
			sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
					+ "FROM ZS_KHJG_KHJL t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
					+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			
		} else if (rolekind.equalsIgnoreCase("ROLE_BMJL")) {//部门经理
			
			if ("jszcb".equalsIgnoreCase(orgCode)) {
				sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
						+ "FROM ZS_KHJG_JSZCBMJL t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
						+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			} else if ("gcfwb".equalsIgnoreCase(orgCode)) {
				sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
						+ "FROM ZS_KHJG_JSFWBJL t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
						+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			} else if ("tbzx".equalsIgnoreCase(orgCode)) {
				sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
						+ "FROM ZS_KHJG_TBJL t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
						+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			} else if("yyjcb".equalsIgnoreCase(orgCode)){

			}

		} else if (rolekind.equalsIgnoreCase("ROLE_XMJL")) {//项目经理
			
			if ("jszcb".equalsIgnoreCase(orgCode)) {
			
			} else if ("gcfwb".equalsIgnoreCase(orgCode)) {
				sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
						+ "FROM ZS_KHJG_JSFWBXMJL t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
						+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			} else if ("tbzx".equalsIgnoreCase(orgCode)) {
				
			} else if("yyjcb".equalsIgnoreCase(orgCode)){

			}
			
		} else if (rolekind.equalsIgnoreCase("ROLE_PTRG")) {//普通员工
			
			if ("jszcb".equalsIgnoreCase(orgCode)) {
				sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
						+ "FROM ZS_KHJG_JSZCGCSZKH t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
						+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			} else if ("gcfwb".equalsIgnoreCase(orgCode)) {
				sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
						+ "FROM ZS_KHJG_JSFWBGCS t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
						+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			} else if ("tbzx".equalsIgnoreCase(orgCode)) {
				sql = "SELECT t.KHQH AS \"khqh\", t.bkhr AS \"bkhr\", t.df AS \"df\", b.XM as \"xm\" "
						+ "FROM ZS_KHJG_TBZY t LEFT JOIN sys_yh b ON t.bkhr = b.dlm "
						+ "LEFT JOIN sys_zzjg c ON b.gwbh = c.bmbh where t.bkhr=? and t.bdlcid=?";
			} else if("yyjcb".equalsIgnoreCase(orgCode)){

			}
		}
		return jdbcTemplate.queryForList(sql, bkhr, bdlcid);
	}

	/**
	 * 强制结束流程，修改考核状态和删除公共
	 * @author zhangyi
	 * @since  2015-4-23
	 * @param bkhr
	 * @param khqh
	 * @param bdlcid
	 * @param tname
	 */
	public void forceProcess(String bkhr, String khqh, String bdlcid,
			String tname) {
		String sql = "delete from ZS_KHJG_GG where bkhr='"+bkhr+"' and khqh='"+khqh+"' ";
		String sql1 = "update "+tname+" t set t.SHZT='0' where t.BDLCID='+bdlcid+'"; 
		jdbcTemplate.execute(sql);
		jdbcTemplate.update(sql1);
		
	}

}
