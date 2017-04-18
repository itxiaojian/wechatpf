package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsSscx;

@Repository
public class ZsSscxDao extends HibernateBaseDaoImpl<ZsSscx, Long> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	/**
	 * 根据页数和标题查询宿舍
	 * @author oufeng
     * @date  2015年7月1日
	 * @param pages   页数
	 * @param code  宿舍号或学号
	 * @return
	 */
	public List<Map<String,Object>> getSscxList(String pages,String code){
		String str="";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(a.xsxm,''),ifnull(a.xh,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
			int num=Integer.parseInt(pages)*10;
			str=str+" order by a.szss DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.szss DESC limit "+num+" ";
			}
		}
		String sql="select a.xsxm,a.xssfzh,a.szss,a.bz,a.xh from "
				+ "sh_sscx a where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}