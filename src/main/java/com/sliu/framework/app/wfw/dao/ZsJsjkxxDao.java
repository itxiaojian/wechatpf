package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsJsjkxx;

@Repository
public class ZsJsjkxxDao extends HibernateBaseDaoImpl<ZsJsjkxx, String>{

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
	 * @param code
	 * @return
	 */
	public String getCxtj(String str,List<Map<String,Object>> user,String code){
		List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
		if(yh.size()!=0){
			String js=(yh.get(0).get("jsmc")+"").trim();
			if("ROLE_TEACHER".equals(js)){
				//老师角色
				str=" and a.jsgh like '%"+yh.get(0).get("dlm")+"%'";
			}else{
				if(code!=null&&!"".equals(code)){
					str=str+" and concat(ifnull(a.jsgh,''),ifnull(a.xm,'')) like '%"+code+"%'";
				}
			}
		}
		return str;
	}
	/**
	 * 根据页数和标题查询教师监考信息
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   教师工号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getJsjkxxList(String pages,String code,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCxtj(str, user, code);
		}	
		if(Integer.parseInt(pages)==1){
			int num=Integer.parseInt(pages)*10;
			str=str+" order by a.jsgh DESC limit "+num+" ";
		}else{
		int num=(Integer.parseInt(pages)-1)*10;
		str=str+" order by a.jsgh DESC limit "+num+",5 ";
		}
//		String sql="select a.id,a.jsgh,a.xm,DATE_FORMAT(a.jkrq,'%Y-%m-%d') as jkrq,DATE_FORMAT(a.kssj,'%Y-%m-%d') as kssj,a.jkkcbh,a.jkkcmc,a.ksxz,"
//				+ "a.ksfs,a.kssc,a.jkdd,a.cjbj,a.ksrs,a.bz from zs_jsjkxx a left join sys_xsbjb b on a.jsgh=b.dlm where 1=1 "+str;
		String sql="select a.id,a.jsgh,a.xm,DATE_FORMAT(a.jkrq,'%Y-%m-%d') as jkrq,DATE_FORMAT(a.kssj,'%Y-%m-%d') as kssj,a.jkkcbh,a.jkkcmc,a.ksxz,"
				+ "a.ksfs,a.kssc,a.jkdd,a.cjbj,a.ksrs,a.bz from zs_jsjkxx a where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
