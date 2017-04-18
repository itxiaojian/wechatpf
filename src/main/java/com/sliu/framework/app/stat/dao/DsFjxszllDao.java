package com.sliu.framework.app.stat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;

@Repository
public class DsFjxszllDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取非教学单位的师资力量统计
	 @Author oufeng
	 *@Date 2016年8月15日 
	 *@Version 1.0
	 */
	public List<Map<String, Object>> getYxbjData(String openId,String qh,String pages) {
		String str="";
		String str1="";
		if(qh!=null && qh!=""){
		   str+="  and yxmc like"+"'%"+qh+"%'";
		}else{
			str="";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str1+=" order by sl desc limit "+num+",10 ";
		}
		String sql = 
				" SELECT id,yxbh,yxmc,sl from tj_fjxjgjsz where 1=1 "
				+str
				+str1;
		return jdbcTemplate.queryForList(sql);
	}

}
