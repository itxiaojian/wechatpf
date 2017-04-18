package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsYktxx;

@Repository
public class ZsYktxxDao extends HibernateBaseDaoImpl<ZsYktxx, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 获取当前登录人的一卡通信息
	 * @author liujiasen
	 * @date 2015年5月22日
	 * @return
	 */
	public List<Map<String,Object>> getYktxx(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and s.dlm='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select k.id,k.bh,k.xm,k.kh,k.ye,k.zpxx,k.bz from zs_yktxx k,sys_yh s where s.dlm = k.bh "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的一卡通消费信息（默认当前月的的消费信息）
	 * @author liujiasen
	 * @date 2015年5月25日
	 * @param xfsj
	 * @return
	 */
	public List<Map<String,Object>> getYktxfxx(String xfsj,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=str+" and bh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql="select id,DATE_FORMAT(xfsj,'%Y.%m.%d %H:%i:%S') as xfsj,xfdd,xfje,bh,kh,ye,bz from zs_yktxfxx where "
				+ "1=1 "+str+" and xfsj like '%"+xfsj+"%' order by xfsj desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 学生擅自离校预警-最后消费时间
	 * @author zhangyan
	 * @date 2016年8月16日
	 * @param pages   页数
	 * @param code   学号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getXfList(String pages,String code,String openId){
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
					str1=" and b.bmbh in (";
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
			str1=str1+" and concat(ifnull(d.classname,''),ifnull(c.xm,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.bh DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.bh DESC limit "+num+",10";
			}
		}
		String sql="select a.bh,b.xm,DATE_FORMAT(max(a.xfsj),'%Y-%m-%d %H:%i:%S') as zhxfsj,b.bmbh,DATE_FORMAT(c.scdlsj,'%Y-%m-%d %H:%i:%S') as scdlsj,d.classname from zs_yktxfxx a "
				+ " left join sys_yh b on a.bh=b.dlm left join zs_wljf c on c.zh=a.bh left join fdy_and_bj d on d.classid=b.bmbh"
				+ " where (now()-a.xfsj)>=5 and (now()-c.scdlsj)>=5"+str1+" group by a.bh"+str;
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
}
