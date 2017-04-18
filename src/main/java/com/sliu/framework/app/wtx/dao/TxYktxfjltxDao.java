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
import com.sliu.framework.app.wtx.model.TxYktxfjltx;

/**
 * 一卡通消费记录提醒
 * @author duanpeijun
 * @date 2015年8月6日
 */
@Repository
public class TxYktxfjltxDao extends HibernateBaseDaoImpl<TxYktxfjltx, Long>{

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
		String sql="select a.id,a.bh,b.xm as xm,DATE_FORMAT(a.xfsj,'%Y-%m-%d') as xfsj,a.xfdd,a.xfje,a.ye,a.kh,"
					+"d.wxh,d.wxnc from zs_yktxfxx a left join sys_yh b on a.bh=b.dlm "
					+"left join (select yhid,wxh,wxnc from sys_wxyh where zt=1) d on b.yhbh=d.yhid where "
					+ "DATE_FORMAT(a.xfsj,'%Y-%m-%d')='"+time+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 前台：一卡通消费记录提醒数量
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getYktxfjltxsl(String openId){
		SimpleDateFormat sfd2=new SimpleDateFormat("yyyy/MM/dd",Locale.CHINA);
		Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-3); 
        String beginday =  sfd2.format(c.getTime());
		String sql = "select a.* from tx_txxxlsjl a LEFT JOIN sys_wxyh b "
				    +"on a.openid=b.WXH LEFT JOIN sys_yh c on b.YHID = c.YHBH WHERE a.TXLX =1 and a.openid='"+openId+"'and a.txsj >='"+beginday+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 前台：一卡通消费记录提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @return
	 */
	public List<Map<String, Object>> getYktxfjltxList(String openId) {
		String sql = "select a.id,a.txnr,a.txsj from tx_txxxlsjl a LEFT JOIN sys_wxyh b "
			    	+"on a.openid=b.WXH LEFT JOIN sys_yh c on b.YHID = c.YHBH WHERE a.TXLX =1 and a.openid='"+openId+"' order by a.txsj desc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	   }
	
	public List<Map<String,Object>> getTxxx(){
		String sql="select id,txnr,txdx,openid,txsj,bz from tx_yktxfjltx";
		return jdbcTemplate.queryForList(sql);
	}
	
	public void delete(){
		String sql="DELETE FROM tx_yktxfjltx";
		jdbcTemplate.execute(sql);
	}
	
}
