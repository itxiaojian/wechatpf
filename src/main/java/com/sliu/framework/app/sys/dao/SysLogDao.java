package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysLog;



/**
 * 系统管理--系统日志
 * @author chenhui
 * @version 创建时间：2016年7月25日  
 */
@Repository
public class SysLogDao  extends HibernateBaseDaoImpl<SysLog, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询系统日志
	 * @author  chenhui
	 * @version 创建时间：2016年7月25日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getLogList(Integer start, Integer limit){
		
		String sql = " select a.id, a.operation, a.oper_content, a.user_id,a.username, a.user_agent, a.IP,DATE_FORMAT(a.oper_date,'%Y-%m-%d') as oper_date from SYS_LOG a  ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 查询系统日志详情
	 * @author chenhui
	 * @version 创建时间：2016年7月25日
	 * @return
	 */
	public List<Map<String, Object>> getCx(){
		
		String sql =  " select a.id, a.operation, a.oper_content, a.user_id,a.username, a.user_agent, a.IP,a.oper_date from SYS_LOG a  ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 删除
	 * @author chenhui
	 * @version 创建时间：2016年7月25日
	 * @param id  主键ID
	 */
	public void deleteLog(String id){
		String sql = "delete from sys_log where id="+id;
				jdbcTemplate.execute(sql);
	}
}
