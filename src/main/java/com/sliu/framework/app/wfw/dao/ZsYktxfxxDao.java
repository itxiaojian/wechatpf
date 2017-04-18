package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsYktxfxx;

@Repository
public class ZsYktxfxxDao extends HibernateBaseDaoImpl<ZsYktxfxx, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 根据xh查看贫困生的一卡通消费信息
	 * @author zhangyan
	 * @date 2016年8月12日
	 * @param xh
	 * @return
	 */
	public List<Map<String,Object>> getXfxx(String xh){
		
		String sql="select a.id,DATE_FORMAT(a.xfsj,'%Y-%m-%d %H:%i:%S') as xfsj,a.xfdd,a.xfje,a.bh,a.kh,a.ye,a.bz from zs_yktxfxx a "
				+ "where a.bh ='"+ xh +"' and datediff(week,[dateadd],getdate())=0 order by a.xfsj desc";
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
}
