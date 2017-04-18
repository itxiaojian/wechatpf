package com.sliu.framework.app.wtx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.wtx.model.TxZggzTxjl;


@Repository
public class TxZggzDao extends HibernateBaseDaoImpl<TxZggzTxjl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取本月工资用于模版消息定时发送
	 * @author:oufeng 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzForSendNews(int year,int month) {
		String sql = "select t.GH,t.XM,t.NF,t.YF,tt.WXH,t.YFHJ,t.kkhj,t.yb,t.ghf,t.gjj,"
				+ " t.ykylj,t.ysgz,t.grsds,t.sfhj from zs_zggz t left join sys_wxyh tt on t.gh=tt.YHID "
				+ " where t.nf="+year+" and t.yf="+month+" and tt.wxh is not null "
				+" AND tt.bz IS  NULL"
				+" OR (tt.bz IS NOT NULL AND tt.bz != '6')";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取本月个人工资记录
	 * @author:oufeng 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzjl(int year,int month) {
		String sql = "select t.GH,t.XM,t.NF,t.YF,t.YFHJ,t.kkhj,t.yb,t.ghf,t.gjj,"
				+ " t.ykylj,t.ysgz,t.grsds,t.sfhj from zs_zggz t "
				+ " where t.nf="+year+" and t.yf="+month+" ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 删除上个月的工资记录
	 * @author:oufeng 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public void deleteGrgzjl(int _year,int _month) {
		String monthstr="";
		if(_month<10){monthstr="0"+_month;}else{monthstr=_month+"";}
		String sql = "delete from tx_zggztxjl "
				+ " where nf="+_year+" and yf="+monthstr+" ";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取本月工资用于模版消息定时发送
	 * @author:oufeng 
	 * @version 创建时间：2016年8月8日 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzForSendNews1(int year,int month) {
		String monthstr="";
		if(month<10){monthstr="0"+month;}
		String sql = 	
        " SELECT a.gh AS gh1,a.xm AS xm1,b.GH,b.XM,b.NF,"
       +" b.YF,b.wxh,b.YFHJ,b.kkhj,b.yb,b.ghf,b.gjj,"
       +" b.ykylj,b.ysgz,b.grsds,b.sfhj FROM  tx_zggztxjl a RIGHT JOIN " 
	   +" (SELECT t.GH,t.XM,t.NF,t.YF,tt.WXH,t.YFHJ,t.kkhj,t.yb,t.ghf,t.gjj,"
       +" t.ykylj,t.ysgz,t.grsds,t.sfhj FROM zs_zggz t , sys_wxyh tt "
	   +" WHERE t.gh=tt.YHID AND t.nf='"+year+"' and t.yf='"+monthstr+"' "
	   +" AND tt.bz IS  NULL"
	   +" OR (tt.bz IS NOT NULL AND tt.bz != '6')"
	   + ")b "
       +" ON a.gh=b.gh "
       +" WHERE a.gh IS NULL "
       +" AND a.xm IS NULL";
		return jdbcTemplate.queryForList(sql);
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
	
}
