package com.sliu.framework.app.wfw.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsZggz;

@Repository
public class ZsZggzDao extends HibernateBaseDaoImpl<ZsZggz, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 获取当前登录人的工资信息（默认上个月）
	 * @author liujiasen
	 * @date 2015年5月22日
	 * @return
	 */
	public List<Map<String,Object>> getGrgz(String gzffsj,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and gh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select  id,nf,yf,gh,xm,jbgz,jkbz,zbbz,skbz,lwbz,gzhj,sbkc,gjjkc,sfgz,xj,gw,jcxjx,yfhj,yb,ghf,gjj,ykylj,ysgz,grsds,kkhj,sfhj"
				+ " from zs_zggz where 1=1 "+str+" and concat(CONCAT(nf,'-'),yf)  = '"+gzffsj+"'";
		return jdbcTemplate.queryForList(sql);
	}
/**
	 * 获取当前登录人的工资信息（默认上个月）
	 * @author liujiasen
	 * @date 2015年5月22日
	 * @return
	 */
	public List<Map<String,Object>> getGrgz1(String gzffsj,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and gh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select  id,nf,yf,gh,xm,jbgz,jkbz,zbbz,skbz,lwbz,gzhj,sbkc,gjjkc,sfgz,xj,gw,jcxjx,yfhj,yb,ghf,gjj,ykylj,ysgz,grsds,kkhj,sfhj"
				+ " from zs_zggz where 1=1 "+str+" and concat(CONCAT(nf,'-'),yf)  = '"+gzffsj+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取本月工资用于模版消息定时发送
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月10日 下午3:14:52 
	 * @return
	 */
	public List<Map<String, Object>> getGrgzForSendNews() {
		Calendar a=Calendar.getInstance();
//		System.out.println(a.get(Calendar.YEAR));//得到年
//		System.out.println(a.get(Calendar.MONTH)+1);//由于月份是从0开始的所以加1
//		System.out.println(a.get(Calendar.DATE));
		int year = a.get(Calendar.YEAR);
		int month = a.get(Calendar.MONTH)+1;
		
		String sql = "select t.GH,t.XM,t.NF,t.YF,tt.WXH,t.SFGZ as jbgz,(t.JKBZ+t.ZBBZ+t.SKBZ+t.LWBZ) as bzgz,"
				+ "(t.SFGZ+t.JKBZ+t.ZBBZ+t.SKBZ+t.LWBZ) as sfgz from zs_zggz t left join sys_wxyh tt on t.gh=tt.YHID "
				+ "where t.nf="+year+" and t.yf="+month+" and tt.wxh is not null;";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据字典查询路径
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月10日 下午4:56:34 
	 * @param zdlx
	 * @return
	 */
	public List<Map<String, Object>> getDict(String zdlx) {
		String sql = "select t.zdbm,t.zdz from sys_sjzd t where t.ZL='"+zdlx+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
}
