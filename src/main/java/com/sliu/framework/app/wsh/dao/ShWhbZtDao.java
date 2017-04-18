package com.sliu.framework.app.wsh.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWhbZt;

@Repository
public class ShWhbZtDao extends HibernateBaseDaoImpl<ShWhbZt, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 
	 * 获得主题
	 * 
	 * @Author oufeng
	 * @Date 2015年7月6日 下午1:51:45
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getZt(Long id) {
		String sql = "SELECT  a.id,a.hbmc,a.bjyy,a.hbfm,DATE_FORMAT(a.cjsj,'%Y-%m-%d %H:%i:%s') as cjsj "
				+ " from sh_whbzt a where a.id="+id+" order by a.id desc";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @return
	 */
	public List<Map<String, Object>> getWhbZt(String hbid) {
		String sql = "SELECT  a.id,a.hbmc,a.bjyy,a.hbfm,DATE_FORMAT(a.cjsj,'%Y-%m-%d %H:%i:%s') as cjsj,a.bz "
				+ " from sh_whbzt a where a.id="+hbid+" order by a.id";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param id
	 * @return
	 */
	public void whbZtfb(String id) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="UPDATE sh_whbzt SET CJSJ = '"+sim.format(new Date())+"', ZT = '1' WHERE ID = "+id;
		jdbcTemplate.execute(sql);
	}
}