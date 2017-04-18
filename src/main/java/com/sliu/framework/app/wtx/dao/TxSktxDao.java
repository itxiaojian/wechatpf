package com.sliu.framework.app.wtx.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.wtx.model.TxSktx;

/**
 * 一卡通消费记录提醒
 * @author duanpeijun
 * @date 2015年8月6日
 */
@Repository
public class TxSktxDao extends HibernateBaseDaoImpl<TxSktx, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	/**
	 * 根据字典查询路径
	 * @author:oufeng 
	 * @version 创建时间：2016年8月8日 
	 * @param zdlx
	 * @return
	 */
	public List<Map<String, Object>> getDict(String zdlx) {
		String sql = "select t.zdbm,t.zdz from sys_sjzd t where t.ZL='"+zdlx+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 拾卡提醒有数据时
	 * @author zhangyan
	 * @date 2016年10月25日 上午11:13:21
	 * @param 
	 * @return
	 */
	public List<Map<String, Object>> skxx(String date){
		String sql=
				 "SELECT  a.fqsj,a.bt,a.xwzs,a.fbrxm,a.dd,a.bh,a.yktkh,a.xm,a.wxh FROM "
				 +" (SELECT DATE_FORMAT(a.fqsj,'%Y-%m-%d %H:%i:%S') AS fqsj, "
				 +" a.lx,a.bt,a.xwzs,a.fbr,a.fbrxm,a.xwzt,a.dd,a.yktkh,b.bh,c.xm,d.wxh " 
				 +" FROM sh_swzl a,zs_yktxx b,sys_yh c,sys_wxyh d "
				 +" WHERE  a.lx='3' and a.xwzt='1' and a.yktkh = b.kh  "
				 +" and b.bh = c.yhbh and c.yhbh=d.yhid "
				 +" AND DATE_FORMAT(a.fqsj,'%Y-%m-%d') ='"+date+"')a LEFT JOIN "
				 +" (SELECT kh,openid,DATE_FORMAT(txsj,'%Y-%m-%d %H:%i:%S') AS txsj "
				 +" FROM tx_sktx WHERE DATE_FORMAT(txsj,'%Y-%m-%d') ='"+date+"'"
				 +" ) b ON "
				 +" a.yktkh=b.kh AND a.wxh=b.openid "
				 +" AND a.fqsj = b.txsj "
				 +" WHERE b.kh IS NULL ";
				 //+" AND a.bh='2012010497'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 拾卡提醒没有数据时
	 * @author zhangyan
	 * @date 2016年10月25日 上午11:09:08
	 * @param 
	 * @return
	 */
	public List<Map<String, Object>> skxx1(String date,Long id){
		String sql=
				 "SELECT DATE_FORMAT(a.fqsj,'%Y-%m-%d %H:%i:%S') AS fqsj,"
				 +" a.lx,a.bt,a.xwzs,a.fbr,a.fbrxm,a.xwzt,a.dd,a.yktkh,c.yhbh,c.xm,d.wxh " 
				 +" FROM sh_swzl a,sys_yh c,sys_wxyh d "
				 +" WHERE a.id='"+id+"' and  a.lx='3' and a.xwzt='1' and a.yktkh = c.yhbh  "
				 +" and c.yhbh=d.yhid "
				 //+" AND a.bh='2012010497'"
				 +" AND DATE_FORMAT(a.fqsj,'%Y-%m-%d') ='"+date+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 拾卡是否有数据
	 * @author zhangyan
	 * @date 2016年10月25日 上午10:58:03
	 * @param 
	 * @return
	 */
	public List<Map<String, Object>> getSktxjlSize(String date) {
		String sql = "select id,txnr,txdx,txsj,txlx from tx_txxxlsjl where txlx='6' "
				+ " and DATE_FORMAT(txsj,'%Y-%m-%d') ='"+date+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
}
