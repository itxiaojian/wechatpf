package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsPyfa;

/**
 * 培养方案
 * @author duanpeijun
 * @date  2015年6月19日
 */
@Repository
public class ZsPyfaDao extends HibernateBaseDaoImpl<ZsPyfa, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据页数和标题查询培养方案数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code  教师工号和姓名
	 * @return
	 */
	public List<Map<String,Object>> getPyfaList(String pages,String code){
		String str="";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(jsgh,''),ifnull(xm,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*5;
				str=str+" order by tjsj DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*5;
				str=str+" order by tjsj DESC limit "+num+",5";
			}
		}
		String sql="select id,jsgh,xm,DATE_FORMAT(tjsj,'%Y-%m-%d') as tjsj,fabt,famx,bz from "
				+ "zs_pyfa where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 培养方案明细
	 * @author duanpeijun
	 * @date  2015年6月19日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getPyfaDetail(String id){
		
		String sql = " select id,jsgh,fabt,famx,tjsj,bz from zs_pyfa where id="+id;
		
		return jdbcTemplate.queryForList(sql);
		
	}
}
