package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWbmbd;

@Repository
public class ShWbmBdDao extends HibernateBaseDaoImpl<ShWbmbd, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据报名编号获得报名的表单数据
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 * @return
	 */
	public List<Map<String,Object>> getWbmBd(String bmid){
		String sql="select id,bmid,bdlx,bt,zdxzz,ddhz,sfbt,bz from sh_wbmbd where bmid="+bmid;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据报名编号删除表单数据
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 */
	public void deleteByBmId(String bmid){
		String sql="DELETE FROM sh_wbmbd WHERE bmid = "+bmid;
		jdbcTemplate.execute(sql);
	}
}
