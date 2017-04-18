package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsrzxx;

/**
 * 学生入住信息
 * @author zhangyan
 * @date  20165年8月9日
 */
@Repository
public class ZsXsrzxxDao extends HibernateBaseDaoImpl<ZsXsrzxx, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 辅导员获取班级学生列表
	 * @author zhangyan
	 * @date  2016年8月9日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getXsrzxxList(String pages,String code,String bjmc,String openId){
		String str="";
		String str1="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				String js=(yh.get(0).get("jsmc")+"").trim();
//				 if("ROLE_INSTRUCTOR".equals(js)){
					//辅导员角色
					List<Map<String,Object>> bj=this.getBjxx((yh.get(0).get("dlm")+"").trim());
					str1=" and a.bjbh in (";
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
					str1=str1+cx;
//				}
			}
		}
		if(code!=null&&!"".equals(code)){
			str1=str1+" and concat(ifnull(a.xh,''),ifnull(a.xm,'')) like '%"+code+"%'";
		}
		if(bjmc!=null&&!"".equals(bjmc)){
			str1=str1+" and a.bjmc='"+bjmc+"'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.bjbh,a.xh DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.bjbh,a.xh DESC limit "+num+",10";
			}
		}
		String sql="select a.id,a.xm,a.xh,a.bjmc,a.bjbh,a.nj from zs_xsrzxx a "
				+ " where 1=1 "+str1+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据id查看学生的详细入住信息
	 * @author zhangyan
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getXsrzxxDetail(Long id,String openId){
		
		String sql="select a.id,a.xm,a.xh,a.xb,a.bjmc,a.bjbh,a.nj,a.gyq,a.gyl,a.fjh,a.cwh,a.xjzt,"
				+ "DATE_FORMAT(a.rzsj,'%Y-%m-%d %H:%i:%S')as rzsj from zs_xsrzxx a where a.id='"+ id +"' ";
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
