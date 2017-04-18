package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.model.ShWsq;

@Repository
public class ShWsqDao extends HibernateBaseDaoImpl<ShWsq, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 根据页数查询微上墙数据
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param pages
	 * @return
	 */
	public List<Map<String,Object>> getWsqList(String pages){
		String str="";
		SysYh user=AppUtil.getCurrentUser();
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" order by DATE_FORMAT(kssj,'%Y-%m-%d %H:%i') desc limit "+num+",10 ";
		}
		String sql="select id,hdmc,logo,sqpf,DATE_FORMAT(kssj,'%Y-%m-%d %H:%i') as kssj,DATE_FORMAT(jzsj,'%Y-%m-%d %H:%i') as jzsj,"
				+ "bz from sh_wsq where 1=1 and bz ='"+user.getUsername()+"' "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取微上墙的总条数
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @return
	 */
	public int getCount(){
		int rows = this.getWsqList("").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}

	/**
	 * 检查当前选择的时间段内是否有其他上墙活动
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> getWsqByTime(String start, String end) {
		SysYh user=AppUtil.getCurrentUser();
		String sql="select id,hdmc,logo,sqpf,DATE_FORMAT(kssj,'%Y-%m-%d %H:%i') as kssj,DATE_FORMAT(jzsj,'%Y-%m-%d %H:%i') as jzsj,"
				+ "bz from sh_wsq where ('"+start+"' between kssj and jzsj and bz ='"+user.getUsername()+"') or "
				+ "('"+end+"' between kssj and jzsj and bz ='"+user.getUsername()+"') or "
				+ "(kssj between '"+start+"' and '"+end+"' and bz ='"+user.getUsername()+"') or "
				+ "(jzsj between '"+start+"' and '"+end+"' and bz ='"+user.getUsername()+"')";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 检查当前选择的时间段内是否有其他上墙活动
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param start
	 * @param end
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getWsqNotById(String start, String end,String id) {
		SysYh user=AppUtil.getCurrentUser();
		String sql="select id,hdmc,logo,sqpf,DATE_FORMAT(kssj,'%Y-%m-%d %H:%i') as kssj,DATE_FORMAT(jzsj,'%Y-%m-%d %H:%i') as jzsj,"
				+ "bz from sh_wsq where ('"+start+"' between kssj and jzsj and id <> "+id+" and bz ='"+user.getUsername()+"') or "
				+ "('"+end+"' between kssj and jzsj and id <> "+id+" and bz ='"+user.getUsername()+"') or "
				+ "(kssj between '"+start+"' and '"+end+"' and id <> "+id+" and bz ='"+user.getUsername()+"') or "
				+ "(jzsj between '"+start+"' and '"+end+"' and id <> "+id+" and bz ='"+user.getUsername()+"')";
		return jdbcTemplate.queryForList(sql);
	}
}
