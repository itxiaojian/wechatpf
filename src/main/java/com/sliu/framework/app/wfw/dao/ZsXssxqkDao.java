package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsjc;

@Repository
public class ZsXssxqkDao extends HibernateBaseDaoImpl<ZsXsjc, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月3日
	 * @param str
	 * @param user
	 * @return
	 */
	public String getCxtj(String str,List<Map<String,Object>> user){
		List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
		if(yh.size()!=0){
			String js=(yh.get(0).get("jsmc")+"").trim();
			List<Map<String,Object>> bj=dao.getBjxx((yh.get(0).get("dlm")+"").trim());
			if("ROLE_STUDENT".equals(js)||"ROLE_INSTRUCTOR".equals(js)){
				//学生角色
				str=" and a.bjbh in (";
				String cx="";
				if(bj.size()!=0){
					for(int i=0;i<bj.size();i++){
						if(i<bj.size()-1){
							cx=cx+"'"+bj.get(i).get("bjbh")+"',";
						}else{
							cx=cx+"'"+bj.get(i).get("bjbh")+"')";
						}
					}
				}else{
					cx=cx+"'')";
				}
				str=str+cx;
			}
		}
		return str;
	}
	
	/**
	 * 获取学生实习数据
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	public List<Map<String,Object>> getXssxqk(String bjbh,String openId,String pages){
		String str="";
		if(bjbh!=null&&!"".equals(bjbh)){
			str=str+" and a.bjbh = '"+bjbh+"'";
		}else{
			List<Map<String,Object>> user=dao.getWxyh(openId);
			if(user.size()!=0){
//				List<Map<String,Object>> list=getBjxx(openId);
//				if(list.size()!=0){
//					bjbh=list.get(0).get("bjbh")+"";
//					str=str+" and a.bjbh = '"+bjbh+"'";
//				}
				str=str+this.getCxtj(str, user);
			}
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.kssj DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.kssj DESC limit "+num+",5 ";
			}
		}
		String sql="select a.id,a.xh,a.xsxm,a.bjbh,a.sxdd,a.xxsm,DATE_FORMAT(a.kssj,'%Y-%m-%d') as kssj,"
				+ "DATE_FORMAT(a.jssj,'%Y-%m-%d') as jssj,a.bz,b.bjmc from zs_xssxqk a "
				+ "left join (select bjbh,bjmc from sys_xsbjb group by bjbh,bjmc) b on a.bjbh=b.bjbh where 1=1"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据当前登陆人获取登录人的班级信息
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
//	public List<Map<String,Object>> getBjxx(String openId){
//		String str="";
//		List<Map<String,Object>> user=dao.getWxyh(openId);
//		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and dlm='"+yh.get(0).get("dlm")+"'";
//			}
//		}
//		String sql="select id,dlm,xm,bjbh,bjmc,bz from sys_xsbjb where 1=1 "+str;
//		return jdbcTemplate.queryForList(sql);
//	}
	
	/**
	 * 获取班级列表
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	public List<Map<String,Object>> getBjlb(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCxtj(str, user);
		}
		String sql="select a.bjbh,b.bjmc from zs_xssxqk a left join (select bjbh,bjmc from sys_xsbjb group by bjbh,bjmc) b "
				+ "on a.bjbh=b.bjbh where 1=1 "+str+" group by a.bjbh,b.bjmc";
		return jdbcTemplate.queryForList(sql);
	}
}
