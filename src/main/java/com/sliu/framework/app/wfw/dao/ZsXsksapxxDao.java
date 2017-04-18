package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsksapxx;

@Repository
public class ZsXsksapxxDao extends HibernateBaseDaoImpl<ZsXsksapxx, String>{
	
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
			if("ROLE_STUDENT".equals(js)){
				//学生角色
				str=" and xh='"+yh.get(0).get("dlm")+"'";
			}else if("ROLE_TEACHER".equals(js)){
				//老师角色
				List<Map<String,Object>> sk=dao.getLssk((yh.get(0).get("dlm")+"").trim());
				str=" and kcbh in (";
				String kc="";
				if(sk.size()!=0){
					for(int i=0;i<sk.size();i++){
						if(i<sk.size()-1){
							kc=kc+"'"+sk.get(i).get("kcbh")+"',";
						}else{
							kc=kc+"'"+sk.get(i).get("kcbh")+"')";
						}
					}
				}else{
					kc=kc+"'')";
				}
				str=str+kc;
			}else if("ROLE_INSTRUCTOR".equals(js)){
				//辅导员角色
				List<Map<String,Object>> bj=dao.getBjxx((yh.get(0).get("dlm")+"").trim());
				str=" and bjbh in (";
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
	 * 按学年和学期获取当前登录人的考试成绩
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getGrksqp(String ksxn,String ksxq,String openId,String pages){
		String str="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and ksxq = '"+ksxq+"'";
		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
//			str=str+this.getCxtj(str, user);
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by xh DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by xh DESC limit "+num+",10 ";
			}
		}
		String sql="select id,ksxn,ksxq,bjbh,xh,xm,kskm,kcbh,ksxz,ksfs,kssc, ksrq,"
				+ " kssj,ksdd,bz from zs_xsksapxx where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的最新考试成绩的学年
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public Map<String,Object> getNewXn(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
//			str=this.getCxtj(str, user);
		}
		String sql="select max(ksxn) as ksxn from zs_xsksapxx where 1=1 "+str;
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取当前登陆人的最新考试成绩的学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @return
	 */
	public Map<String,Object> getNewXq(String ksxn,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
//			str=this.getCxtj(str, user);
		}
		String sql="select max(ksxq) as ksxq from zs_xsksapxx where 1=1 "+str+" and ksxn='"+ksxn+"'";
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取个人考试成绩学年学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
			}
//			str=this.getCxtj(str, user);
		}
		String sql="select ksxn,ksxq from zs_xsksapxx where 1=1 "+str+" group by KSXN,KSXQ order by KSXN desc,KSXQ desc";
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
		if(ksxn!=null&&!"".equals(ksxn)){
			str+=" and a.ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str+=" and a.ksxq = '"+ksxq+"'";
		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and a.xh='"+yh.get(0).get("dlm")+"'";
			}
//			str=str+this.getCxtj(str,user);
		}
/*		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			str+=" order by a.ksxn DESC limit "+num+" ";
		}*/
		String sql="select distinct a.ksxn,a.ksxq,a.xh,a.xm from zs_xsksapxx a where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
