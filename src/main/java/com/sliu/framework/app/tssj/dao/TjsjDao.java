package com.sliu.framework.app.tssj.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.process.model.TestProcess;
import com.sliu.framework.app.sys.model.SysYh;

/**
 * @author:zhangyi
 * @version 创建时间：2015年9月2日 下午4:38:07 类说明
 */
@Repository
public class TjsjDao extends HibernateBaseDaoImpl<TestProcess, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取推送数据表中的数据
	 * @author:zhangyi 
	 * @version 创建时间：2015年9月2日 下午4:52:38 
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getTssj() {
		String sql = "SELECT t.yhbh,t.dlm,t.xm,t.mm,t.bmbh,t.bmmc,t.gwbh,t.gwmc,t.jsbh,t.jsmc,t.zt,t.tssj " + 
					 "FROM xzxxsjts.view_yhxxtbsj T";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 获取部门
	 * @author liujiansen
	 * @date 2015年9月10日
	 * @return
	 */
	public List<Map<String,Object>> getBm(){
		String sql="SELECT bmdm, sjbmdm, bmmc, tbsj, jb FROM xzxxsjts.view_bmxx";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取推送数据表中的增量数据
	 * @author:zhangyi 
	 * @version 创建时间：2015年9月2日 下午4:52:38 
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getTssjZl() {
		String sql = "SELECT t.yhbh,t.dlm,t.xm,t.mm,t.bmbh,t.bmmc,t.gwbh,t.jsbh,t.jsmc,t.zt FROM xzxxsjts.view_yhxxtbsj T where yhbh not in (select yhbh from sys_yh)";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 获取迎新新生的数据统计
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月15日 下午2:52:11 
	 * @return
	 */
	public List<Map<String, Object>> getXsTssjZl() {
		String sql = "select t.xm,t.ksh,t.xh,t.xb,t.mz,t.classid,t.sfzh from student_info t where t.ksh not in (select yhbh from sys_yh)";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 获取增量部门
	 * @author liujiansen
	 * @date 2015年9月10日
	 * @return
	 */
	public List<Map<String,Object>> getBmZl(){
		String sql="SELECT bmdm, sjbmdm, bmmc, jb FROM xzxxsjts.view_bmxx where bmdm not in (select bmbh as bmdh from sys_zzjg)";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 删除用户
	 * @author liujiansen
	 * @date 2015年9月10日
	 */
	public void deleteYh(){
		String sql="DELETE FROM sys_yh WHERE DLM <> 'admin'";
		System.out.println(sql);
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 删除部门
	 * @author liujiansen
	 * @date 2015年9月10日
	 */
	public void deleteBm(){
		String sql="DELETE FROM sys_zzjg";
		System.out.println(sql);
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 删除用户角色
	 * @author liujiansen
	 * @date 2015年9月10日
	 */
	public void deleteYhjs(){
		String sql="DELETE FROM sys_yhjs WHERE YHBH <> '1' ";
		System.out.println(sql);
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取系统角色
	 * @author liujiansen
	 * @date 2015年9月10日
	 * @param js
	 * @return
	 */
	public List<Map<String,Object>> getJsbh(String js){
		String sql="SELECT jsbh, jsmc, bz, jszt FROM sys_js where jsmc = '"+js+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 保存用户
	 * @author liujiansen
	 * @date 2015年9月10日
	 * @param yh
	 */
	public void saveYh(SysYh yh){
		String sql="INSERT INTO sys_yh (YHBH, DLM, XM, MM, BMBH, GWBH, SJH, YX, ZHDLSJ, YHZT) "
				+ "VALUES ('"+yh.getYhbh()+"', '"+yh.getUsername()+"', '"+yh.getXm()+"', '"
				+ yh.getPassword()+"', '"+yh.getBmbh()+"', "+yh.getGwbh()+", '"+yh.getSjh()+"', "
				+ yh.getYx()+", "+yh.getZhdlsj()+", "+yh.getYhzt()+")";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取ad服务器配置信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 上午10:43:11 
	 * @return
	 */
	public List<Map<String, Object>> getAdpzxx() {
		String sql = "select t.id,t.fwqdz,t.dkh,t.tbyhm,t.tbmm,t.dlqz,t.yjd from SYS_ADPZ t ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据部门名称获取部门编号
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午4:57:12 
	 * @param bmmc
	 * @return
	 */
	public List<Map<String, Object>> getBmbh(String bmmc) {
		String sql = "select bmbh from sys_zzjg where bmmc='"+bmmc+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
}
