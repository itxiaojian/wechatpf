package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;


@Repository
public class DsYxblqkDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取迎新的数据
	 @Author oufeng
	 *@Date 2016年8月15日 
	 *@Version 1.0
	 */
	public List<Map<String, Object>> getYxData(String pages) {
		String str="";
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" DESC limit "+num+",10";
			}
		}
		String sql = 
				" SELECT a.xsyx,b.tachename,count(case when a.blzt=0 then stuid end)as fail,"
				+" count(case when a.blzt=1 then stuid end)as sucess"
				+" FROM  zs_yx_tachestatus a,zs_yx_tache b "
				+" WHERE a.tacheid = b.tacheid "
				+" group by a.xsyx,b.tachename,a.blzt"
				+" order by a.xsyx,b.tachename "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据查询条件获取迎新的数据
	 @Author wangxiangyang
	 *@Date 2016年8月16日 
	 *@Version 1.0
	 */
	public List<Map<String, Object>> getYxData1(String yxmc,String pages) {
		String str="";
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" DESC limit "+num+",10";
			}
		}
		String sql = 
				" SELECT a.xsyx,b.tachename,count(case when a.blzt=0 then stuid end)as fail,"
				+" count(case when a.blzt=1 then stuid end)as sucess"
				+" FROM  zs_yx_tachestatus a,zs_yx_tache b "
				+" WHERE a.tacheid = b.tacheid and a.xsyx like '%"+yxmc+"%'"
				+" group by a.xsyx,b.tachename,a.blzt"
				+" order by a.xsyx,b.tachename"+str;
		return jdbcTemplate.queryForList(sql);
	}
	

}
