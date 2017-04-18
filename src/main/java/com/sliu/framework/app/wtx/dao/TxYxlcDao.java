package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.wtx.model.TxYxlcTxjl;


@Repository
public class TxYxlcDao extends HibernateBaseDaoImpl<TxYxlcTxjl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取本学年迎新消息用于模版消息定时发送
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzForSendNews(int year) {
		String sql = "select t.id,(select a.tachename from zs_yx_tache a where a.tacheid=t.tacheid) as tachename,t.stuid,t.stuname,t.xsyx,t.xszy,t.blzt,t.bltime,t.blrid,"
				+ " t.blrxm,t.reason from zs_yx_tachestatus t left join sys_wxyh tt on t.stuid=tt.YHID "
				+ " where t.stuid like '%"+year+"%' and tt.wxh is not null and t.blzt is not null;";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取年度迎新信息
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getYxlcjl(int year) {
		String sql = "select t.id,t.tacheid,t.stuid,t.stuname,t.xsyx,t.xszy,t.blzt,t.blrid,"
				+ " t.blrxm,t.reason from zs_yx_tachestatus t "
				+ " where t.stuid like '%"+year+"%'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 删除上一年的迎新记录
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月16日 
	 * @return
	 */
	public void deleteYxlcjl(int lastyear) {
		String sql = "delete from tx_yxlcztxjl "
				+ " where stuid like '%"+lastyear+"%'";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取迎新消息用于模版消息定时发送
	 * @author:wangxiangyang 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzForSendNews1(int year) {
		String sql = 	
        " SELECT a.tacheid as tacheid1,a.stuid as stuid1,(select c.tachename from zs_yx_tache c where c.tacheid=b.tacheid) as tachename,a.blzt as blzt1,b.id,b.tacheid,b.stuid,"
       +" b.stuname,b.wxh,b.blzt FROM tx_yxlctxjl a RIGHT JOIN " 
	   +" (SELECT t.id,t.tacheid,t.stuid,t.stuname,tt.WXH,t.blzt"
       +" FROM zs_yx_tachestatus t , sys_wxyh tt "
	   +" WHERE t.stuid = tt.yhid and t.stuid like '%"+year+"%')b "
       +" ON a.stuid=b.stuid "
       +" where a.tacheid != b.tacheid  "
       +" or a.stuid is null "
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
