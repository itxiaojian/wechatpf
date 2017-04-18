package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wfw.model.ZsTxltj;

/**
 * 主页--通讯录添加
 * @author wangxiangyang
 * @version 创建时间：2016年7月1日
 */
@Repository
public class ZsTxltjDao  extends HibernateBaseDaoImpl<ZsTxltj, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * @author  wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTxltjList(Integer start, Integer limit){
		
		String sql = " select a.id, a.yhxm, a.dhhm, a.bz from ZS_TXL a  ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 查询一键救援详情
	 * @author wangxiangyang
	 * @version 创建时间：2015年6月3日  下午3:49:06
	 * @return
	 */
	public List<Map<String, Object>> getCx(){
		
		String sql = " select a.id, a.yhxm, a.dhhm, a.bz from ZS_TXL a  ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询重复
	 * @author wangxiangyang
	 * @version 创建时间：2015年6月3日  下午3:49:06
	 * @return
	 */
	public List<Map<String, Object>> CC(String yhxm,String dhhm){
		String sql = " select a.id, a.yhxm, a.dhhm, a.bz from ZS_TXL a where a.yhxm = '"+yhxm+"' and a.dhhm = '"+dhhm+"'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 删除
	 * @author wangxiangyang
	 * @version 创建时间：2016年7月4日
	 * @param id  主键ID
	 */
	public void deleteTxltj(Long id){
		String sql = "delete from zs_txl where id="+id;
				jdbcTemplate.execute(sql);
	}
	
	/**
	 * 导出excel
	 * @author wangxiangyang
	 * @version 创建时间：2016年7月4日
	 * @return
	 */
	public List<Map<String, Object>> exportExcel(String code){
		
		String sql = " select a.id, a.yhxm, a.dhhm, a.bz from ZS_TXL a  ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
}
