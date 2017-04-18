package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWbm;

@Repository
public class ShWbmDao extends HibernateBaseDaoImpl<ShWbm, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 根据页数查询微报名数据
	 * @author liujiansen
	 * @date 2015年6月24日
	 * @param pages 页数
	 * @return
	 */
	public List<Map<String,Object>> getSwzlList(String pages,String bt){
		String str="";
		if(bt!=null&&!"".equals(bt)){
			str=str+" and bmbt like '%"+bt+"%' ";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" order by bmjzsj desc limit "+num+",10 ";
		}
		String sql="select id,bmbt,bmjs,bmcgts,DATE_FORMAT(bmjzsj,'%Y-%m-%d %H:%i') as bmjzsj,zbflogo,kjmp from sh_wbm "
				+ " where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取微报名的总条数
	 * @author liujiansen
	 * @date 2015年6月24日
	 * @return
	 */
	public int getCount(String bt){
		int rows = this.getSwzlList("",bt).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
}
