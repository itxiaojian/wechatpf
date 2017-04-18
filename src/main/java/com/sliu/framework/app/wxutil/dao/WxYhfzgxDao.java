package com.sliu.framework.app.wxutil.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wxutil.model.WxYhfz;
import com.sliu.framework.app.wxutil.model.WxYhfzgx;

/**
 * 微用户分组关系
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:03
 */
@Repository
public class WxYhfzgxDao extends HibernateBaseDaoImpl<WxYhfzgx, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	public void deleteByFzId(Long fzid) {
		String sql = "delete from wx_yhfzgx where fzid="+fzid;
		jdbcTemplate.execute(sql);
	}

	public Pagination<Map<String, Object>> getMenBerList(Integer start,
			Integer limit, Long fzid) {
		String sql = "select t.id,t.fzid,t.rybh,tt.xm,ttt.bmbh,ttt.bmmc from wx_yhfzgx t left join sys_yh tt on t.RYBH = tt.DLM "
				+ "LEFT JOIN sys_zzjg ttt on tt.BMBH = ttt.BMBH where t.fzid="+fzid+" order by ttt.bmbh,t.rybh desc";
		return jdbcPager.queryPage(sql, start, limit);
	}

	public List<Map<String, Object>> getmenberlist(Long fzid) {
		String sql = "select t.wxh as \"id\",concat(c.XM,'(【',t.wxnc,'】',d.bmmc,')') as \"text\" from sys_wxyh t "
				+ "left join sys_yh c on t.yhid=c.yhbh left join wx_yhfzgx tt on c.dlm=tt.rybh  "
				+ "left join sys_zzjg d on c.bmbh=d.bmbh where tt.fzid='"+fzid+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据机构id获取已经被绑定的用户信息
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月5日 上午11:06:23 
	 * @param jgId
	 * @return
	 */
	public List<Map<String, Object>> getWxUserByJgId(String fzid) {
		//String sql = "select user.DLM as \"id\",user.XM as \"text\" from sys_yh user where user.yhzt=1 and user.bmbh='"+jgId+"' ";
		String sql = "select t.wxh as \"id\",concat(c.XM,' (【',t.wxnc,'】,d.bmmc,')') as \"text\" from sys_wxyh t "
				+ "left join sys_yh c on t.yhid=c.yhbh left join wx_yhfzgx tt on c.dlm=tt.rybh  "
				+ "left join sys_zzjg d on c.bmbh=d.bmbh where tt.fzid='"+fzid+"'";
		return jdbcTemplate.queryForList(sql);
	}


}
