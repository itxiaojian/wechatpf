package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWsqfbxx;

@Repository
public class ShWsqfbxxDao extends HibernateBaseDaoImpl<ShWsqfbxx, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据上墙活动编号获取此次上墙活动发布的信息数
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param hdid 活动编号
	 * @return
	 */
	public int getFbxxCount(String hdid){
		String sql="select id,hdid,cyrid,fbnr,fbsj,sfsq,bz from sh_wsqfbxx where hdid="+hdid;
		return jdbcTemplate.queryForList(sql).size();
	}

	/**
	 * 获取发布信息
	 * @author liujiasen
	 * @date 2015年7月8日
	 * @param id
	 * @param cyrid
	 * @return
	 */
	public List<Map<String, Object>> getFbxx(String id, String cyrid,String length) {
		String str="";
		if(cyrid!=null&&!"".equals(cyrid)){
			//str=str+" and a.cyrid="+cyrid;
		}else{
			str=str+" order by a.fbsj asc";
//			if(length!=null&&!"".equals(length)){
//				str=str+" limit "+length;
//			}
		}
		String sql="select a.id,a.hdid,a.cyrid,a.fbnr,DATE_FORMAT(a.fbsj,'%Y-%m-%d %H:%i') as fbsj,a.sfsq,b.cyrxm,b.bz,b.openid from sh_wsqfbxx a "
				+ "left join sh_wsqcyr b on a.cyrid=b.id where a.sfsq='0' and a.hdid="+id+str;
		return jdbcTemplate.queryForList(sql);
	}
}
