package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wfw.model.ZsPjxx;

/**
 * 评教信息
 * @author duanpeijun
 * @date  2015年6月24日
 */
@Repository
public class ZsPjxxDao extends HibernateBaseDaoImpl<ZsPjxx, String>{

	
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
			if("ROLE_STUDENT".equals(js)){
				//学生角色
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
					str=str+" and concat(ifnull(a.jsgh,''),ifnull(a.jsxm,'')) like '%"+code+"%'";
				}
			}else if("ROLE_TEACHER".equals(js)){
				//老师角色
				str=str+" and a.jsgh='"+yh.get(0).get("dlm")+"'";
			}else{
				if(code!=null&&!"".equals(code)){
					str=str+" and concat(ifnull(a.jsgh,''),ifnull(a.jsxm,'')) like '%"+code+"%'";
				}
			}
		}
		return str;
	}
	
	/**
	 * 根据页数和标题查询评教信息列表
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   教师工号和教师姓名
	 * @return
	 */
	public List<Map<String,Object>> getPjxxList(String pages,String code,String openId){
		String str="";
//		if(code!=null&&!"".equals(code)){
//			str=str+" and concat(ifnull(a.jsgh,''),ifnull(a.jsxm,'')) like '%"+code+"%'";
//		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCxtj(str, user, code);
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.tjsj DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.tjsj DESC limit "+num+",5 ";
			}
		}
		String sql="select a.id,a.xh,a.jsgh,a.jsxm,a.rjkm,"
				+ " DATE_FORMAT(a.tjsj,'%Y-%m-%d') as tjsj,"
				+ " a.ksxn,a.ksxq,case when a.zt =1 then '进行中' else '过期' end as ztmc,"
				+ " a.zt,a.bz,b.bjbh from zs_pjxx a "
				+ " left join sys_xsbjb b on a.jsgh=b.dlm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 评教信息明细
	 * @author duanpeijun
	 * @date  2015年6月19日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getPjxxDetail(String id){
		
		String sql = " select id,xh,jsgh,jsxm,rjkm,DATE_FORMAT(tjsj,'%Y-%m-%d') as tjsj,ksxn,ksxq,zt,bz from "
				+ "zs_pjxx where id="+id;
		
		return jdbcTemplate.queryForList(sql);
		
	}
	
	/**
	 * 分页查询评教信息
	 * @author duanpeijun
	 * @date  2015年6月24日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getPjxx(Integer start, Integer limit){
		
		String sql = " select id,xh,jsgh,jsxm,rjkm,DATE_FORMAT(tjsj,'%Y-%m-%d') as tjsj,ksxn,ksxq,zt,bz from "
				+ "zs_pjxx  ";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 删除
	 * @author duanpeijun
	 * @version 2015年6月24日
	 * @param id  主键ID
	 */
	public void deletePjxx(Long id){
		String sql = "delete from zs_pjxx where id="+id;
				jdbcTemplate.execute(sql);
	}
	
	
}
