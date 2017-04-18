package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysWxyh;
import com.sliu.framework.app.wfw.model.ZsBjkb;

/**
*解除绑定
@Author oufeng	
@Date 2016年1月13日 下午3:16:32
@Version 1.0
*/
@Repository
public class WfwZyDao extends HibernateBaseDaoImpl<SysWxyh, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 解除绑定
	 * @author oufeng
	 * @date  2016年1月14日
	 * @return
	 */
	public String  jcbd(String openId){
		String str="";
		String str1="";
		if(openId!=null&&!"".equals(openId)){
	    str+= " and wxh = '"+openId+"' ";
		}else{ str += " and wxh =''";}
		String sql="delete from sys_wxyh  where 1=1 "+str;
		jdbcTemplate.execute(sql);
		str1="1";
		return str1;
	}


	/**
	 * 显示失物招领信息
	 * @author liujiansen
	 * @date 2016年3月10日
	 * @param pages
	 * @return
	 */
	public List<Map<String,Object>> getSwzlList(String pages){
		String str="";
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*5;
			str=str+" order by FQSJ desc limit "+num+" ";
		}
		String sql="select id,lx,bt,xwzs,DATE_FORMAT(fqsj,'%Y.%m.%d') as fqsj,jssj,fbr,fbrxm,xwzt,dd from "
				  +" sh_swzl where 1=1  and bt <> '' and bt is not null "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
}

