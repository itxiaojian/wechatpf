package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShTpzlxx;

/**
 * 投票专栏选项
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Repository
public class ShTpzlxxDao extends HibernateBaseDaoImpl<ShTpzlxx, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	
	/**
	 * 插入选项
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param id  
	 * @param value 选项内容
	 */
	public void addSelecter(int id,String value ) {
		String sql = "insert into wsh_tpzlxx(tpid,xxnr) values('"
				+ id + "','" + value + "')";
		jdbcTemplate.execute(sql);
	}
	

	/**
	 * 查看选项内容
	 * @author zhangyan
	 * @date 2016年7月4日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getXxlist(Long id) {
		String sql="select a.xxnr,ifnull(b.num,0)as num from wsh_tpzlxx a "
				+ "left join (select count(xxid)as num,xxid from wsh_tpjl group by xxid)b on a.id = b.xxid where a.tpid = '" + id + "' order by a.id";
 
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查看选项内容
	 * @author zhangyan
	 * @date 2016年7月4日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getXxnrlist(Long id) {
		String sql="select * from wsh_tpzlxx a where a.tpid = '" + id + "' order by a.id";
 
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 后台删除投票时删除对应的选项内容
	 * @author zhangyan
	 * @date 2016年7月4日
	 * @param id
	 * @return
	 */
	public void deletexx(Long id) {
		String sql="delete from wsh_tpzlxx where tpid = '" + id + "' ";
 
		jdbcTemplate.execute(sql);
	}
	
	
}
