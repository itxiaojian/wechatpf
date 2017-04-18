package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsPksxx;

/**
 * 贫困生信息
 * @author duanpeijun
 * @date  2015年6月19日
 */
@Repository
public class ZsPksxxDao extends HibernateBaseDaoImpl<ZsPksxx, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月5日
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
				str=" and bj in (";
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
					str=str+" and concat(ifnull(xh,''),ifnull(xm,'')) like '%"+code+"%'";
				}
			}else{
				if(code!=null&&!"".equals(code)){
					str=str+" and concat(ifnull(xh,''),ifnull(xm,'')) like '%"+code+"%'";
				}
			}
		}
		return str;
	}
	
	/**
	 * 根据页数和标题查询失物招领数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getPksxxList(String pages,String code,String openId){
		String str="";
//		if(code!=null&&!"".equals(code)){
//			str=str+" and concat(ifnull(xh,''),ifnull(xm,'')) like '%"+code+"%'";
//		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCxtj(str, user, code);
		}
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			str=str+" order by id DESC limit "+num+" ";
		}
		String sql="select id,zy,pksdj,xm,bj,xh,xy,bz from "
				+ "zs_pksxx where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 辅导员获取班级贫困生列表
	 * @author zhangyan
	 * @date  2016年8月11日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getPksyjList(String pages,String code,String openId){
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
					str1=" and a.bj in (";
					String cx="";
					if(bj.size()!=0){
						for(int i=0;i<bj.size();i++){
							if(i<bj.size()-1){
								cx=cx+"'"+bj.get(i).get("classname")+"',";
							}else{
								cx=cx+"'"+bj.get(i).get("classname")+"')";
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
			str1=str1+" and concat(ifnull(a.bj,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.xh DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.xh DESC limit "+num+",10";
			}
		}
//		if(pages!=null&&!"".equals(pages)){
//			int num=Integer.parseInt(pages)*10;
//			str=str+" order by a.xh DESC limit "+num+" ";
//		}
		String sql="select a.id,a.zy,a.pksdj,a.xm,a.bj,a.xh,a.xy,a.bz from zs_pksxx a "
				+ " where 1=1 "+str1+" group by a.xh,a.zy"+str;
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
	 * 根据id查看贫困生信息
	 * @author zhangyan
	 * @date 2016年8月12日
	 * @return
	 */
	public List<Map<String, Object>> getPksxx(Long id,String openId){
		
		String sql="select a.id,a.zy,a.pksdj,a.xm,a.bj,a.xh,a.xy,a.bz from zs_pksxx a where a.id='"+ id +"' ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据xh查看贫困生的一卡通消费信息
	 * @author zhangyan
	 * @date 2016年8月12日
	 * @param xh
	 * @return
	 */
	public List<Map<String,Object>> getXfxx(String xh){
		
		String sql="select distinct a.id,DATE_FORMAT(a.xfsj,'%Y-%m-%d %H:%i:%S') as xfsj,a.xfdd,a.xfje,a.bh,a.kh,a.ye,a.bz from zs_yktxfxx a "
				+ "where a.bh ='"+ xh +"' and  date_sub(curdate(), INTERVAL 7 DAY) <= date(a.xfsj) order by a.xfsj desc";
		return jdbcTemplate.queryForList(sql);
	}
}
