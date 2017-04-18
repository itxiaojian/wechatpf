package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsjqfxx;

/**
 * 学生缴欠费信息
 * @author duanpeijun
 * @date  2015年6月19日
 */
@Repository
public class ZsXsjqfxxDao extends HibernateBaseDaoImpl<ZsXsjqfxx, String>{
	
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
	public String getCxtj(String str,List<Map<String,Object>> user,String code){
		List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
		if(yh.size()!=0){
			String js=(yh.get(0).get("jsmc")+"").trim();
			if("ROLE_STUDENT".equals(js)){
				//学生角色
				str=" and a.xh='"+yh.get(0).get("dlm")+"'";
			}else if("ROLE_INSTRUCTOR".equals(js)){
				//辅导员角色
				List<Map<String,Object>> bj=dao.getBjxx((yh.get(0).get("dlm")+"").trim());
				str=" and a.bz in (";
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
				if(code!=null&&!"".equals(code)){
					str=str+" and concat(ifnull(a.xh,''),ifnull(a.xm,'')) like '%"+code+"%'";
				}
			}else{
				if(code!=null&&!"".equals(code)){
					str=str+" and concat(ifnull(a.xh,''),ifnull(a.xm,'')) like '%"+code+"%'";
				}
			}
		}
		return str;
	}
	
	/**
	 * 根据页数和标题查询学生缴欠费信息
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getXsjqfxxList(String pages,String code,String openId){
		String str="";
		String str1="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			str1=this.getCxtj(str, user, code);
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.xn desc,a.xm DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.xn desc,a.xm DESC limit "+num+",10 ";
			}
		}
		String sql="select a.id,a.xn,a.xh,a.xm,a.lx,ifnull(a.bz,'')as bz from zs_xsjqfxx a "
				+ " where 1=1 "+str1+" group by xh,xn"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据页数和标题查询学生缴欠费信息
	 * @author zhangyan
	 * @date  2016年4月29日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getXsjqfList(String pages,String code,String openId){
		String str="";
		String str1 ="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			str1= this.getCxtj(str, user, code);
		}
		/*if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.xn,a.xm DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.xn,a.xm DESC limit "+num+",10 ";
			}
		}*/
		String sql="select a.xh,a.xm,a.xn,a.lx,a.jfxm,a.yjje,a.sjje,a.qfje,ifnull(a.bz,'')as bz "
				+ " from zs_xsjqfxx a where 1=1 ";
				//+ "left join sys_xsbjb b on a.xh=b.dlm where 1=1 "+str1+str;
		return jdbcTemplate.queryForList(sql);
	}
}
