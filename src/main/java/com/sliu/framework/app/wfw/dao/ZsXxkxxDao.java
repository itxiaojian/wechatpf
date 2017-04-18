package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXxkxx;

/**
 * 选修课信息
 * @author duanpeijun
 * @date  2015年6月19日
 */
@Repository
public class ZsXxkxxDao extends HibernateBaseDaoImpl<ZsXxkxx, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月4日
	 * @param str
	 * @param user
	 * @return
	 */
	public String getCxtj(String str,List<Map<String,Object>> user){
		List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
		if(yh.size()!=0){
			String js=(yh.get(0).get("jsmc")+"").trim();
			if("ROLE_STUDENT".equals(js)){
				//学生角色
				str=" and a.xh='"+yh.get(0).get("dlm")+"'";
			}else if("ROLE_TEACHER".equals(js)){
				//老师角色
				str=" and a.sklsgh='"+yh.get(0).get("dlm")+"'";
			}
		}
		return str;
	}
	
	/**
	 *  按学年和学期获取当前登录人的选修课信息
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param xn   学年
	 * @param xq   学期
	 * @return
	 */
	public List<Map<String,Object>> getXxkxxList(String xn,String xq,String openId,String pages){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=str+this.getCxtj(str, user);
		}
		if(xn!=null&&!"".equals(xn)){
			str=str+" and a.xn = '"+xn+"'";
		}
		if(xq!=null&&!"".equals(xq)){
			str=str+" and a.xq = '"+xq+"'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*5;
				str=str+" order by a.XN desc,a.XQ desc  limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*5;
				str=str+" order by a.XN desc,a.XQ desc  limit "+num+",5 ";
			}
		}
		String sql="select distinct a.id,a.xh,b.xm as xsmc,a.xn,a.xq,a.kcbh,a.kcmc,a.skdd,a.sklsgh,c.xm as lsmc,a.bz from zs_xxkxx a "
				+ "left join sys_yh b on a.xh=b.dlm left join sys_yh c on a.sklsgh=c.dlm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的最新选修课的学年
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public Map<String,Object> getNewXn(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=str+this.getCxtj(str, user);
		}
		String sql="select max(a.xn) as xn from zs_xxkxx a where 1=1 "+str;
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取当前登陆人的最新选修课的学期
	 * @author duanpeijun
	 * @date 2015年7月17日
	 * @param xn  学年
	 * @param openId
	 * @return
	 */
	public Map<String,Object> getNewXq(String xn,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=str+this.getCxtj(str, user);
		}
		String sql="select max(a.xq) as xq from zs_xxkxx a where 1=1 "+str+" and a.xn='"+xn+"'";
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取个人选修课的学年学期
	 * @author duanpeijun
	 * @date 2015年7月17日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=str+this.getCxtj(str, user);
		}
		String sql="select a.xn,a.xq from zs_xxkxx a where 1=1 "+str+" group by a.XN,a.XQ order by a.XN desc,a.XQ desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 按学年和学期获取当前登录人的信息
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getXsxx(String ksxn,String ksxq,String openId,String pages){
		String str="";
		String sql="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str+=" and a.xn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str+=" and a.xq = '"+ksxq+"'";
		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			str=str+this.getCxtj(str,user);
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				String js=(yh.get(0).get("jsmc")+"").trim();
				if("ROLE_STUDENT".equals(js)){
					//学生角色
					str=" and a.xh='"+yh.get(0).get("dlm")+"'";
				    sql="select distinct a.xh,b.xm as xsxm,d.jsmc from zs_xxkxx a "
							+ " left join sys_yh b on a.xh = b.dlm "
							+" left join sys_yhjs c on c.yhbh=b.yhbh left join sys_js d on d.jsbh=c.jsbh "
							+ " where 1=1 "+str;
				}else if("ROLE_TEACHER".equals(js)){
					//老师角色
					str=" and a.sklsgh='"+yh.get(0).get("dlm")+"'";
				    sql="select distinct a.sklsgh,d.jsmc,b.xm as lsmc from zs_xxkxx a "
							+ " left join sys_yh b on a.sklsgh = b.dlm"
							+" left join sys_yhjs c on c.yhbh=b.yhbh left join sys_js d on d.jsbh=c.jsbh "
							+ " where 1=1 "+str;
				}else{
					str=" and a.sklsgh='"+yh.get(0).get("dlm")+"'";
				    sql="select distinct a.sklsgh,d.jsmc,b.xm as lsmc from zs_xxkxx a "
							+ " left join sys_yh b on a.sklsgh = b.dlm"
							+" left join sys_yhjs c on c.yhbh=b.yhbh left join sys_js d on d.jsbh=c.jsbh "
							+ " where 1=1 "+str;
				}
			}
		}
		
		return jdbcTemplate.queryForList(sql);
	}
	
	
}
