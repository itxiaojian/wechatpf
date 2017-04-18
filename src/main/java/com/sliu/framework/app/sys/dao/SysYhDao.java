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
import com.sliu.framework.app.sys.model.SysYh;

/**
 * @author:zhangyi
 * @version 创建时间：2015年9月2日 下午4:38:07 类说明
 */
@Repository
public class SysYhDao extends HibernateBaseDaoImpl<SysYh, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	public List<Map<String, Object>> getByDlm(String yhdlm) {
		String sql = "select dlm from sys_yh where dlm='"+yhdlm+"'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 角色分配获取用户列表
	 * @author:liusong 
	 * @version 创建时间：2016年7月21日 上午11:10:16 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	public Pagination<Map<String, Object>> getYhxxList(Integer start, Integer limit, String code,String jsbh) {
		String str = "";
		String str1 = "";
		
		if(StringUtils.hasText(code)) {
			str = str +" and (t.xm like '%"+code+"%' or t.dlm like '%"+code+"%' or t.sjh like '%"+code+"%' or a.bmmc like '%"+code+"%' )";
		}
		if(StringUtils.hasText(jsbh)) {
			str1 = " where b.jsbh = '"+jsbh+"'";
		}
		String sql = "select t.dlm,t.yhbh,t.xm,a.bmmc,a.bmbh,b.jsmc,c.jsbh,t.sjh,t.yhzt,t.yx from sys_yh t left join sys_zzjg a on t.bmbh=a.BMBH left join "
				+ " (select a.yhbh,b.JSBH from sys_yh a left join sys_yhjs b on a.YHBH = b.YHBH"+str1+" )c on c.yhbh = t.YHBH left join "
				+ " (select group_concat(y.BZ) as jsmc,x.yhbh from sys_yhjs x left join sys_js y on x.jsbh=y.jsbh  group by x.yhbh) b on t.yhbh=b.yhbh "
				+ "  where 1=1 "+str+" order by t.yhbh ";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 获取用户列表
	 * @author:chenhui 
	 * @version 创建时间：2016年8月30日 上午11:10:16 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	public Pagination<Map<String, Object>> getList(Integer start, Integer limit, String yhxm, String yhzh) {
		String str = "";
		
		if(StringUtils.hasText(yhxm)) {
			str = str +" and t.xm like '%"+yhxm+"%'";
		}
		
		if(StringUtils.hasText(yhzh)) {
			str = str +" and t.dlm like '%"+yhzh+"%'";
		}
		
//		String sql = "select t.dlm,t.yhbh,t.xm,a.bmmc,a.bmbh,b.jsmc,t.sjh,t.yhzt,t.yx from sys_yh t left join sys_zzjg a on t.bmbh=a.BMBH left join "
//				+ " (select group_concat(y.BZ) as jsmc,x.yhbh from sys_yhjs x left join sys_js y on x.jsbh=y.jsbh  group by x.yhbh) b on t.yhbh=b.yhbh where 1=1 "+str;
		String sql = " SELECT t.dlm, t.yhbh, t.xm, a.bmmc, a.bmbh, b.jsmc, t.sjh, t.yhzt, t.yx FROM sys_yh t,sys_zzjg a, "
				+ "( SELECT GROUP_CONCAT(y.BZ) AS jsmc, x.yhbh FROM sys_yhjs x LEFT JOIN sys_js y ON x.jsbh = y.jsbh GROUP BY x.yhbh ) b "
				+ "where t.bmbh=a.bmbh and t.yhbh = b.yhbh "+ str;
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 关联删除用户角色关系
	 * @author: chenhui 
	 * @version 创建时间：2016年7月22日 下午3:40:39 
	 * @param yhbh
	 */
	public void deleteYhjs(String yhbh) {
		String sql = " delete from sys_yhjs where yhbh='"+yhbh+"'";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 根据机构Id获取用户
	 * @author:chenhui 
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
	 * @author:chenhui
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
	 * 根据字典种类找出字典
	   @author:zhangyi 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl='"+zdzl+"' and a.jb='2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
}
