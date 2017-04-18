package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wtx.model.TxXsjqftx;


@Repository
public class TxXsjqfDao extends HibernateBaseDaoImpl<TxXsjqftx, Long> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	
	/**
	 * 根据字典查询路径
	 * @author:oufeng 
	 * @version 创建时间：2016年8月8日 
	 * @param zdlx
	 * @return
	 */
	public List<Map<String,Object>> getDict(String zdbm){
		String sql="select zdz,zdmc FROM sys_sjzd WHERE zl='txlj' and zdbm ='"+zdbm+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取缴欠费提醒数据
	 * @author oufeng
	 * @date 2016年8月11日
	 * @return
	 */
	public List<Map<String,Object>> getJqftx(String xn){
		String sql="SELECT a.xn,a.xh,a.xm,GROUP_CONCAT(a.jfxm) AS jfxm ,"
				+" GROUP_CONCAT(a.qfje) AS qfje,b.wxh "
				+" FROM  zs_xsjqfxx a,sys_wxyh b WHERE a.xh =b.yhid "
				+" AND a.xn='"+xn+"'"
				+"  AND a.qfje !=0"
				+"  AND b.bz IS  NULL"
				+" OR (b.bz IS NOT NULL AND b.bz != '7')"
				+"  GROUP BY a.xh,b.wxh";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取缴欠费提醒数据
	 * @author oufeng
	 * @date 2016年8月11日
	 * @return
	 */
	public List<Map<String,Object>> getJqftx1(String xn){
		String sql="SELECT    a.xn,a.xh,a.xm,  a. jfxm,a.qfje,a.wxh ,b.wxh as wxhb     " 
                     +"  FROM (SELECT a.xn,a.xh,a.xm,GROUP_CONCAT(a.jfxm) AS jfxm ,"
                     +" GROUP_CONCAT(a.qfje) AS qfje,b.wxh "
				     +" FROM  zs_xsjqfxx a,sys_wxyh b WHERE a.xh =b.yhid "
				    +" AND a.xn='"+xn+"' "
				    +" AND a.qfje !=0 "
					+" AND b.bz IS  NULL"
					+" OR (b.bz IS NOT NULL AND b.bz != '7')"
				   +" GROUP BY a.xh,b.wxh) a LEFT JOIN  tx_xsjqftxjl  b "
				   +" ON a.xh=b.xh AND a.wxh=b.wxh "
				   +" WHERE b.wxh IS NULL "
				   +" AND b.xh IS NULL";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取缴欠费提醒记录是否有数据
	 * @author oufeng
	 * @date 2016年8月11日
	 * @return
	 */
	public List<Map<String,Object>> getJqfjlSize(String xn){
		String sql=" select xn,xh,wxh,xm from   tx_xsjqftxjl where xn='"+xn+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 删除上个月的欠缴费
	 * @author oufeng
	 * @date 2016年8月11日
	 * @return
	 */
	public void deleteJqfjl(int xn){
		String sql=" delete  from   tx_xsjqftxjl where xn='"+xn+"'";
		 jdbcTemplate.execute(sql);
	}
}
