package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXjyd;


@Repository
public class ZsXjydDao extends HibernateBaseDaoImpl<ZsXjyd, String>{

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
			if("ROLE_INSTRUCTOR".equals(js)){
				//辅导员角色
				List<Map<String,Object>> bj=dao.getBjxx((yh.get(0).get("dlm")+"").trim());
				str=" and b.bjbh in (";
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
	 * 根据页数和标题查询学籍异动数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getXjydList(String pages,String code,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCxtj(str, user, code);
		}
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.xh,''),ifnull(a.xm,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*5;
				str=str+" order by a.fssj DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*5;
				str=str+" order by a.fssj DESC limit "+num+",5 ";
			}
		}
		String sql="select a.id,a.xh,a.xm,DATE_FORMAT(a.fssj,'%Y-%m-%d') as fssj,a.ydlx,a.xxqk,a.bz,b.bjbh from zs_xjyd a "
				+ "left join sys_xsbjb b on a.xh=b.dlm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 学籍异动明细
	 * @author duanpeijun
	 * @date  2015年6月19日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getXjydDetail(String id){
		
		String sql = " select id,xh,fssj,ydlx,xxqk,bz from zs_xjyd where id="+id;
		
		return jdbcTemplate.queryForList(sql);
		
	}
}
