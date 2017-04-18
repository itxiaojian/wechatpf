package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.wtx.model.TxLxlcTxjl;
import com.sliu.framework.app.wtx.model.TxZggzTxjl;


@Repository
public class TxLxlcDao extends HibernateBaseDaoImpl<TxLxlcTxjl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取本年度离校消息用于模版消息定时发送
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzForSendNews(int year) {
		String sql = "select t.id,(select a.tachename from zs_lx_tache a where a.tacheid=t.tacheid) as tachename,t.stuid,t.stuname,t.xsyx,t.xszy,t.blzt,t.bltime,t.blrid,"
				+ " t.blrxm,t.reason from zs_lx_tachestatus t left join sys_wxyh tt on t.stuid=tt.YHID "
				+ " where t.bltime like '%"+year+"%' and tt.wxh is not null and t.blzt is not null";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取年度离校信息
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getLxlcjl(int year) {
		String sql = "select t.id,t.tacheid,t.stuid,t.stuname,t.xsyx,t.xszy,t.blzt,t.bltime,t.blrid,"
				+ " t.blrxm,t.reason from zs_lx_tachestatus t "
				+ " where t.bltime like '%"+year+"%'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 删除上一年的离校记录
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月16日 
	 * @return
	 */
	public void deleteLxlcjl(int lastyear) {
		String sql = "delete from tx_yxlcztxjl "
				+ " where stuid like '%"+lastyear+"%'";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取本年度离校消息用于模版消息定时发送
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzForSendNews1(int year) {
		String sql = 	
        " SELECT a.tacheid as tacheid1,(select c.tachename from zs_lx_tache c where c.tacheid=b.tacheid) as tachename,a.stuid as stuid1,a.blzt as blzt1,b.id,b.tacheid,b.stuid,"
       +" b.stuname,b.wxh,b.blzt,b.bltime FROM tx_lxlctxjl a RIGHT JOIN " 
	   +" (SELECT t.id,t.tacheid,t.bltime,t.stuid,t.stuname,tt.WXH,t.blzt"
       +" FROM zs_lx_tachestatus t , sys_wxyh tt "
	   +" WHERE t.stuid = tt.yhid and t.bltime like '%"+year+"%')b "
       +" ON a.stuid=b.stuid "
       +" where a.tacheid != b.tacheid  "
       +" or a.stuid is null"
       +" or a.blzt != b.blzt";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据字典查询路径
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月8日 
	 * @param zdlx
	 * @return
	 */
	public List<Map<String, Object>> getDict(String zdlx) {
		String sql = "select t.zdbm,t.zdz from sys_sjzd t where t.zdbm ='"+zdlx+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
}
