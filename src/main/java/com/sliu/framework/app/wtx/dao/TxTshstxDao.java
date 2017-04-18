package com.sliu.framework.app.wtx.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.wtx.model.TxTshstx;

/**
 * 图书还书提醒
 * @author duanpeijun
 * @date 2015年8月6日
 */
@Repository
public class TxTshstxDao extends HibernateBaseDaoImpl<TxTshstx, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @author duanpeijun
	 * @date 2015年7月29日
	 * @param time
	 * @return
	 */
	public List<Map<String,Object>> getGjsj(String time){
		String sql="select a.id,a.bh,b.xm as xm,DATE_FORMAT(a.jcsj,'%Y-%m-%d') as jcsj,a.kh,a.tsbh,a.tsmc,"
				+"DATE_FORMAT(a.ghsj,'%Y-%m-%d') as ghsj,d.wxh,d.wxnc from zs_tsjyxx a left join sys_yh b on a.bh=b.dlm "
				+"left join (select yhid,wxh,wxnc from sys_wxyh where zt=1) d on b.yhbh=d.yhid where "
				+ "DATE_FORMAT(a.jcsj,'%Y-%m-%d')='"+time+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 前台：图书还书提醒数量
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getTshstxsl(String openId){
		SimpleDateFormat sfd2=new SimpleDateFormat("yyyy/MM/dd",Locale.CHINA);
		Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-3); 
        String beginday =  sfd2.format(c.getTime());
		String sql = "select a.* from tx_txxxlsjl a LEFT JOIN sys_wxyh b "
				    +"on a.openid=b.WXH LEFT JOIN sys_yh c on b.YHID = c.YHBH WHERE a.TXLX =2 and a.openid='"+openId+"' and a.txsj >='"+beginday+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 前台：图书还书提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @return
	 */
	public List<Map<String, Object>> getTshstxList(String openId) {
		String sql = "select a.id,a.txnr,a.txsj from tx_txxxlsjl a LEFT JOIN sys_wxyh b "
			    	+"on a.openid=b.WXH LEFT JOIN sys_yh c on b.YHID = c.YHBH WHERE a.TXLX =2 and a.openid='"+openId+"' order by a.txsj desc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	   }
	
	public List<Map<String,Object>> getTxxx(){
		String sql="select id,txnr,txdx,openid,txsj,bz from tx_tshstx";
		return jdbcTemplate.queryForList(sql);
	}
	
	public void delete(){
		String sql="DELETE FROM tx_tshstx";
		jdbcTemplate.execute(sql);
	}
	
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
	 * 根据图书借阅的有否有数据
	 * @author:oufeng 
	 * @version 创建时间：2016年8月8日 
	 * @param zdlx
	 * @return
	 */
	public List<Map<String, Object>> getTstxjlSize() {
		String sql = "select bh,tsbh,tsmc from tx_tshstx";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取图书提醒数据
	 * @author oufeng
	 * @date 2016年8月11日
	 * @return
	 */
	public List<Map<String,Object>> getTshstx(String date){
		String sql="SELECT a.bh,a.tsbh,a.tsmc,jcsj,"
                    +" yghsj,"
                    +" ghsj,a.xm,a.wxh,a.kgq"
					+" FROM "
					+" (SELECT a.bh,a.tsbh,a.tsmc,a.jcsj,a.yghsj,a.ghsj,a.xm,b.wxh,"
					+" CASE WHEN a.yghsj <='"+date+"' THEN 1 "
					+" WHEN  DATE_ADD(SUBSTR(a.yghsj,1,10),INTERVAL -2 DAY) "
					+" =SUBSTR('"+date+"',1,10) THEN 2"
					+" WHEN  DATE_ADD(SUBSTR(a.yghsj,1,10),INTERVAL -1 DAY) "
					+" =SUBSTR('"+date+"',1,10) THEN 2"
					+" END  AS kgq"
					+" FROM zs_tsjyxx a , sys_wxyh b WHERE  a.bh= b.yhid"
					+" AND a.ghsj IS NULL "
					+" AND b.bz IS  NULL  "
				    +" OR (b.bz IS NOT NULL AND b.bz != '2'))a"
					+" WHERE a.kgq IS NOT NULL";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取图书提醒数据
	 * @author oufeng
	 * @date 2016年8月11日
	 * @return
	 */
	public List<Map<String,Object>> getTshstx1(String date){
		String sql=
				"SELECT a.bh,a.tsbh,a.tsmc,jcsj,yghsj,"
	            +" ghsj,a.xm,a.wxh,a.kgq"
				+" from (SELECT  a.bh,a.tsbh,a.tsmc,a.ghsj,a.yghsj,"
				+" a.jcsj,a.xm,a.wxh,b.wxh AS wxhb,b.bh AS bhb,"
				+" CASE WHEN a.yghsj <='"+date+"' THEN 1 "
				+" WHEN  DATE_ADD(SUBSTR(a.yghsj,1,10),INTERVAL -2 DAY) "
				+" =SUBSTR('"+date+"',1,10) THEN 2"
				+" WHEN  DATE_ADD(SUBSTR(a.yghsj,1,10),INTERVAL -1 DAY) "
				+" =SUBSTR('"+date+"',1,10) THEN 2"
				+" END  AS kgq from "
				+" (SELECT  a.bh,a.tsbh,a.tsmc,a.ghsj,a.yghsj,a.jcsj,a.xm,b.wxh " 
				+" FROM zs_tsjyxx a ,sys_wxyh b WHERE a.bh = b.yhid  "
                +" AND a.ghsj IS NULL "
             	+" AND b.bz IS  NULL  "
			    +" OR (b.bz IS NOT NULL AND b.bz != '2')"
                + ") a LEFT JOIN tx_tshstx b "
                +" ON a.bh=b.bh "
                +" AND a.wxh=b.wxh "
                +" WHERE b.wxh IS NULL)a"
                +" WHERE a.kgq IS NOT NULL";
		return jdbcTemplate.queryForList(sql);
	}
	
}
