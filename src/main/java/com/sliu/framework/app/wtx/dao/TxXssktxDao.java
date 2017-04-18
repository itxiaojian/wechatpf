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
import com.sliu.framework.app.wtx.model.TxXssktx;

/**
 * 提醒管理配置
 * @author liujiansen
 * @date 2015年7月29日
 */
@Repository
public class TxXssktxDao extends HibernateBaseDaoImpl<TxXssktx, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
//	@Autowired
//	private NamedParameterJdbcPager jdbcPager;
	//JdbcTemplate jdbcTemplate=new JdbcTemplate();

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @param time
	 * @return
	 */
	public List<Map<String,Object>> getGjsj(String time){
		String sql="select a.id,a.xh,b.xm as xsxm,DATE_FORMAT(a.skrq,'%Y-%m-%d') as skrq,a.xq,a.kcmc,a.skdd,a.sklsgh,"
				+ "c.xm as lsxm,a.dtskxh,d.wxh,d.wxnc from zs_xskb a left join sys_yh b on a.xh=b.dlm left join sys_yh c"
				+ " on a.sklsgh=c.dlm left join (select yhid,wxh,wxnc from sys_wxyh where zt=1) d on b.yhbh=d.yhid where "
				+ "DATE_FORMAT(a.skrq,'%Y-%m-%d')='"+time+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 前台：学生上课提醒数量
	 * @author duanpeijun
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getXssktxsl(String openId){
		SimpleDateFormat sfd2=new SimpleDateFormat("yyyy/MM/dd",Locale.CHINA);
		Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-3); 
        String beginday =  sfd2.format(c.getTime());
		String sql = "select a.* from tx_txxxlsjl a LEFT JOIN sys_wxyh b "
			    +"on a.openid=b.WXH LEFT JOIN sys_yh c on b.YHID = c.YHBH WHERE a.TXLX =3 and a.openid='"+openId+"'and a.txsj >='"+beginday+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 前台：学生上课提醒列表
	 * @author duanpeijun
	 * @date 2015年8月11日
	 * @return
	 */
	public List<Map<String, Object>> getXssktxList(String openId) {
		String sql = "select a.id,a.txnr,a.txsj from tx_txxxlsjl a LEFT JOIN sys_wxyh b "
			    	+"on a.openid=b.WXH LEFT JOIN sys_yh c on b.YHID = c.YHBH WHERE a.TXLX =3 and a.openid='"+openId+"'order by a.txsj desc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	   }
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年7月29日
	 * @return
	 */
	public List<Map<String,Object>> getTime(){
		String sql="SELECT DATE_FORMAT(sjgjsj,'%Y-%m-%d %H:%i:%S') as sjgjsj, DATE_FORMAT(txsj,'%Y-%m-%d %H:%i:%S') as txsj FROM tx_glpz where txlx='3'";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String,Object>> getTxxx(){
		String sql="select id,txnr,txdx,openid,txsj,bz from tx_xssktx";
		return jdbcTemplate.queryForList(sql);
	}
	
	public void delete(){
		String sql="DELETE FROM tx_xssktx";
		jdbcTemplate.execute(sql);
	}
}
