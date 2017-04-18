package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysJs;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
 * @author:zhangyi
 * @version 创建时间：2015年9月2日 下午4:38:07 类说明
 */
@Repository
public class SysRoleDao extends HibernateBaseDaoImpl<SysJs, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	public List<Map<String, Object>> getByDlm(String yhdlm) {
		String sql = "select dlm from sys_yh where dlm='"+yhdlm+"'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据机构Id获取用户
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月25日 下午1:40:53 
	 * @param jgId
	 * @return
	 */
	public List<Map<String, Object>> getUserByJgId(String jgId) {
		//String sql = "select user.DLM as \"id\",user.XM as \"text\" from sys_yh user where user.yhzt=1 and user.bmbh='"+jgId+"' ";
		String sql = "select user.DLM as \"id\",concat(user.XM,' (',t.bmmc,')') as \"text\" from sys_yh user left join "
				+ "sys_zzjg t on user.bmbh=t.bmbh where user.yhzt=1 and user.bmbh in("+jgId+") order by t.bmbh";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据机构id获取已经被绑定的用户信息
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月5日 上午11:06:23 
	 * @param jgId
	 * @return
	 */
	public List<Map<String, Object>> getWxUserByJgId(String jgId) {
		//String sql = "select user.DLM as \"id\",user.XM as \"text\" from sys_yh user where user.yhzt=1 and user.bmbh='"+jgId+"' ";
		String sql = "select t.wxh as \"id\",concat(tt.XM,' (',t.wxnc,')') as \"text\" from sys_wxyh t left join sys_yh tt on t.yhid=tt.DLM where tt.bmbh='"+jgId+"'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获取用户列表
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月21日 上午11:10:16 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	public Pagination<Map<String, Object>> getList(Integer start, Integer limit, String jsmc, String jsms) {
		String str = "";
		
		if(StringUtils.hasText(jsmc)) {
			str = str +" and t.jsmc like '%"+jsmc+"%'";
		}
		
		if(StringUtils.hasText(jsms)) {
			str = str +" and t.bz like '%"+jsms+"%'";
		}
		
		String sql = "select t.jsbh,t.jsmc,t.bz,t.jszt from sys_js t where 1=1 "+str;
		return jdbcPager.queryPage(sql, start, limit);
	}

	/**
	 * 关联删除用户角色关系
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月22日 下午3:40:39 
	 * @param yhbh
	 */
	public void deleteYhjs(String jsbh) {
		String sql = " delete from sys_yhjs where jsbh='"+jsbh+"'";
		jdbcTemplate.execute(sql);
	}

	/**
	 * 查看是否有重复数据
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月25日 下午2:07:13 
	 * @param jsmc
	 * @return
	 */
	public List<Map<String, Object>> findRoleByName(String jsmc) {
		String sql = "select t.jsbh,t.jsmc,t.bz,t.jszt from sys_js t where t.jsmc='"+jsmc+"' ";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据角色编号删除角色菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午3:57:16 
	 * @param jsbh
	 */
	public void deleteByJsbh(String jsbh) {
		String sql = " delete from sys_jscd where jsbh='"+jsbh+"'";
		jdbcTemplate.execute(sql);
	}

	/**
	 * 配置菜单角色
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午4:01:06 
	 * @param cdbh
	 * @param jsbh
	 */
	public void addJsCd(String cdbh, String jsbh) {
		String sql = "insert into sys_jscd (jsbh,cdbh) values ('"+jsbh+"','"+cdbh+"')";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 配置菜单角色
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午4:01:06 
	 * @param cdbh
	 * @param jsbh
	 */
	public void saveUserRole(String yhbh, String jsbh) {
		String sql = "insert into sys_yhjs (yhbh,jsbh) values ('"+yhbh+"','"+jsbh+"')";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 删除菜单角色
	 * @author:zhangyi 
	 * @version 创建时间：2016年7月28日 下午4:01:06 
	 * @param cdbh
	 * @param jsbh
	 */
	public void deleteUserRole(String yhbh, String jsbh) {
		String sql = "delete from sys_yhjs where yhbh='"+yhbh+"' and jsbh= '"+jsbh+"'";
		jdbcTemplate.execute(sql);
	}

	public List<Map<String, Object>> getMyRoleCd() {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String sql = "select group_concat(DISTINCT t.cdbh) as cdbh from sys_jscd t where t.jsbh in (select tt.jsbh from sys_yhjs tt where tt.yhbh='"+yhbh+"')";
		return jdbcTemplate.queryForList(sql);
	}
	
//	public Pagination<Map<String,Object>> getWxyhList(Integer start, Integer limit,String xm,String wxh) {
//		String str="";
//		if(xm!=null&&!"".equals(xm)){
//			str=str+" and b.xm like '%"+xm+"%'";
//		}
//		if(wxh!=null&&!"".equals(wxh)){
//			str=str+" and a.wxh like '%"+wxh+"%'";
//		}
//		String sql = "select a.id,a.wxh,a.wxnc,DATE_FORMAT(a.cjsj,'%Y-%m-%d') as cjsj,a.zt,a.bz,b.dlm,b.xm,b.bmbh,c.bmmc,"
//				+ "b.gwbh,d.bmmc as gwmc,b.sjh,b.yx from sys_wxyh a left join sys_yh b on a.YHID=b.YHBH and b.yhzt=1 "
//				+ "left join sys_zzjg c on b.bmbh=c.bmbh left join sys_zzjg d on b.gwbh=d.bmbh where 1=1 "+str;
//		return jdbcPager.queryPage(sql, start, limit);
//	}
}
