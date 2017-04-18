package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wfw.model.ZsDzxq;


@Repository
public class ZsDzxqDao extends HibernateBaseDaoImpl<ZsDzxq,Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 后台:提醒列表的页面
	 * @author oufeng
	 * @date 2016年8月15日
	 * @param start
	 * @param limit
	 * @param code  关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getDzxqList(Integer start, Integer limit,String code){
		String str = "";
		if(code!=null&&!"".equals(code)){
			str+=" and  xn like '%"+code+"%'";
		}
		String sql = "select id,xn,xq,ksrq,jsrq,dqzc from zs_dzxq  where 1=1 "+str+" order by xn,xq,ksrq";
		return jdbcPager.queryPage(sql,start, limit);
	}
}
