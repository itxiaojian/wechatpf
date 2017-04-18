package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wtx.model.TxGlpz;

/**
 * 提醒管理配置
 * @author liujiansen
 * @date 2015年7月29日
 */
@Repository
public class TxGlpzDao extends HibernateBaseDaoImpl<TxGlpz, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 分页查询信息数据
	 * @author liujiasen
	 * @date 2015年5月19日
	 * @param start
	 * @param limit
	 * @param txlx 提醒类型
	 * @return
	 */
	public Pagination<Map<String,Object>> pager(Integer start, Integer limit,String txlx) {
		String str="";
		if(txlx!=null&&!"".equals(txlx)){
			str=str+" and a.txlx ='"+txlx+"'";
		}
		String sql = "SELECT a.id, DATE_FORMAT(a.sjgjsj,'%Y-%m-%d %H:%i:%S') as sjgjsj, DATE_FORMAT(a.txsj,'%Y-%m-%d %H:%i:%S') as txsj, "
				+ "a.txlx,b.zdmc, a.bz FROM tx_glpz a left join (SELECT id, zdbm, zdmc, zdz, jb, zl, px FROM sys_sjzd where zl='txlx' and jb=2) b "
				+ "on a.txlx=b.zdz where 1=1 "+str;
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 获取字典表中的提醒类型
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @return
	 */
	public List<Map<String,Object>> getLx(){
		String sql="SELECT zdmc, zdz FROM sys_sjzd where zl='txlx' and jb=2";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据提醒类型获取数据
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param txlx
	 * @return
	 */
	public List<Map<String,Object>> getEntityByLx(String txlx){
		String sql="SELECT id,DATE_FORMAT(sjgjsj,'%Y-%m-%d %H:%i:%S') as sjgjsj, DATE_FORMAT(txsj,'%Y-%m-%d %H:%i:%S') as txsj, "
				+ "txlx, bz FROM tx_glpz where txlx='"+txlx+"'";
		return jdbcTemplate.queryForList(sql);
	}
}
