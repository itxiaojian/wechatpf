package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsgkxx;

@Repository
public class ZsXsgkxxDao extends HibernateBaseDaoImpl<ZsXsgkxx, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 学生挂科信息
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @param str
	 * @param user
	 * @return
	 */
	public String getCxtj(String str,List<Map<String,Object>> user){
		List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
		if(yh.size()!=0){
			String js=(yh.get(0).get("jsmc")+"").trim();
//			if("ROLE_STUDENT".equals(js)){
//				//学生角色
//				str=" and a.xh='"+yh.get(0).get("dlm")+"'";
//			 if("ROLE_TEACHER".equals(js)){
//				//老师角色
//				List<Map<String,Object>> sk=dao.getLssk((yh.get(0).get("dlm")+"").trim());
//				str=" and kcbh in (";
//				String kc="";
//				if(sk.size()!=0){
//					for(int i=0;i<sk.size();i++){
//						if(i<sk.size()-1){
//							kc=kc+"'"+sk.get(i).get("kcbh")+"',";
//						}else{
//							kc=kc+"'"+sk.get(i).get("kcbh")+"')";
//						}
//					}
//				}else{
//					kc=kc+"'')";
//				}
//				str=str+kc;
			 if("ROLE_INSTRUCTOR".equals(js)){
				//辅导员角色
				List<Map<String,Object>> bj=this.getBjxx((yh.get(0).get("dlm")+"").trim());
				str=" and b.bmbh in (";
				String cx="";
				if(bj.size()!=0){
					for(int i=0;i<bj.size();i++){
						if(i<bj.size()-1){
							cx=cx+"'"+bj.get(i).get("classid")+"',";
						}else{
							cx=cx+"'"+bj.get(i).get("classid")+"')";
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
	 * 按学年和学期获取当前登录人的挂科信息
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getGkxx(String ksxn,String ksxq,String bjmc,String openId,String pages){
		String str="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and a.xn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and a.xq = '"+ksxq+"'";
		}
		if(bjmc!=null&&!"".equals(bjmc)){
			str=str+" and c.classname = '"+bjmc+"'";
		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=str+this.getCxtj(str, user);
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.xn DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.xn DESC limit "+num+",10";
			}
		}
		String sql="select a.gks,a.xh,a.xn,a.xq,b.xm,c.classname from (select count(1) as gks,xh,xn,xq from zs_xsgkxx where cj<60 group by xh,xn,xq) a  "
				+ "left join sys_yh b on a.xh=b.dlm left join fdy_and_bj c on c.classid=b.bmbh"
				+ " where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的最新挂科信息的学年
	 * @author zhangyan
	 * @date 2016年8月8日
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
			str=this.getCxtj(str, user);
		}
		String sql="select max(a.xn) as ksxn from zs_xsgkxx a left join sys_yh b on a.xh=b.dlm where 1=1 "+str;
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取当前登陆人的最新挂科信息的学期
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @param ksxn
	 * @return
	 */
	public Map<String,Object> getNewXq(String ksxn,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=this.getCxtj(str, user);
		}
		String sql="select max(a.xq) as ksxq,b.bmbh from zs_xsgkxx a left join sys_yh b on a.xh=b.dlm where 1=1 "+str+" and a.xn='"+ksxn+"'";
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取个人挂科信息的学年学期
	 * @author zhangyan
	 * @date 2016年8月8日
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
			str=this.getCxtj(str, user);
		}
		String sql="select a.xn,a.xq,b.bmbh  from zs_xsgkxx a left join sys_yh b on a.xh=b.dlm where 1=1 "+str+" group by a.XN,a.XQ order by a.XN desc,a.XQ desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 按学年和学期获取当前登录人的信息
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getXsxx(String ksxn,String ksxq,String bjmc,String openId,String pages){
		String str="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str+=" and a.xn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str+=" and a.xq = '"+ksxq+"'";
		}
		if(bjmc!=null&&!"".equals(bjmc)){
			str+=" and c.classname='"+bjmc+"'";
		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=this.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and a.xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=str+this.getCxtj(str,user);
		}
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			str+=" order by a.xn DESC limit "+num+" ";
		}
		String sql="select distinct concat(a.xn,'第',a.xq,'学期') as xq,a.xn,a.xq,a.xh,a.xm,b.bmbh,c.classname from zs_xsgkxx a left join sys_yh b on a.xh=b.dlm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据当前登陆人获取登录人的班级信息
	 * @author zhangyan
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String,Object>> getBjxx(String dlm){
		String str="";
		if(dlm!=null&&!"".equals(dlm)){
			str=str+" and loginid='"+dlm+"'";
		}
		String sql="select id,classid,classname,loginid from fdy_and_bj where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查看贫困生详细信息
	 * @author zhangyan
	 * @date 2016年8月12日
	 * @return
	 */
	public List<Map<String, Object>> getXsxxDetail(String xh,String openId){
		
		String sql="select a.xm,a.xh from zs_xsgkxx a where a.xh='"+ xh +"' ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查看贫困生详细信息
	 * @author zhangyan
	 * @date 2016年8月12日
	 * @return
	 */
	public List<Map<String, Object>> getGkxxDetail(String xh,String ksxn,String ksxq,String openId){
		String str="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str+=" and a.xn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str+=" and a.xq = '"+ksxq+"'";
		}
		if(xh!=null&&!"".equals(xh)){
			str+=" and a.xh='"+xh+"'";
		}
		String sql="select a.xn,a.xq,a.kcbh,a.kcmc,a.kctx,a.ksxz,a.xh,a.cj,a.xf,a.rkjs,a.rkjsgh from zs_xsgkxx a where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取辅导员所带的班级
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @return
	 */
	public List<Map<String,Object>> getBj(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
		String dlm = yh.get(0).get("dlm").toString();
		if(dlm!=null&&!"".equals(dlm)){
			str=str+" and loginid='"+dlm+"'";
		}
		String sql="select classname from fdy_and_bj where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
