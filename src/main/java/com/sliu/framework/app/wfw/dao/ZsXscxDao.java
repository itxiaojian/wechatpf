package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;



@Repository
public class ZsXscxDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	
	/**
	 * 获取学生信息
	 * @author oufeng
	 * @date 2016年12月10日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getXsxx(String yhbh,String code,String pages){
		String str="";
		if(code!=null&&!"".equals(code)){
			str+=" and concat(ifnull(a.yhbh,''),ifnull(a.xm,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str+=" order by bmbh  limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str+=" order by bmbh  limit "+num+",10";
			}
		}
		String sql="SELECT a.yhbh,a.xm,a.bmbh,d.bmmc "
				  + " FROM sys_yh a,sys_yhjs b ,"
				  + " sys_js c,sys_zzjg_test d  "
				  + " where  a.bmbh= d.bmbh "
				  + " and a.yhbh=b.yhbh "
				  + " and b.jsbh=c.jsbh "
				  + " and c.jsmc='ROLE_STUDENT'"
				  +str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学生信息
	 * @author oufeng
	 * @date 2016年12月15日
	 * @param 
	 * @return
	 */
	public int getPagecount(String yhbh,String code){
		int rows = this.getXsxx(yhbh,code,"").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
}
