package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;


@Repository
public class DsYxbjsTjDao {
	
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
	public List<Map<String, Object>> getLxData(String pages) {
		String str="";
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" limit "+num+",10";
			}
		}
		String sql = "select id,yxbh,yxmc,sl from tj_yxbjs"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据查询条件获取迎新的数据
	 @Author wangxiangyang
	 *@Date 2016年8月16日 
	 *@Version 1.0
	 */
	public List<Map<String, Object>> getLxData1(String yxmc,String pages) {
		String str="";
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" limit "+num+",10";
			}
		}
		String sql = "select id,yxbh,yxmc,sl from tj_yxbjs where 1=1 and yxmc like '%"+yxmc+"%'"+str;
		return jdbcTemplate.queryForList(sql);
	}
	

}
