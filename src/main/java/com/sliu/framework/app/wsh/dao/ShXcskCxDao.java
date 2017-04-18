package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShXcsk;

@Repository
public class ShXcskCxDao extends HibernateBaseDaoImpl<ShXcsk, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 根据页数和标题查询校车数据
	 * @author oufeng
	 * @date 2015年6月23日
	 * @param pages 页数
	 * @param bt 标题 
	 * @return
	 */
	public List<Map<String,Object>> getShXcskList(String pages,String bt){
		String str="";
		if(bt!=null && !"".equals(bt)){
			str=str+" and cph like '%"+bt+"%' ";
		}
		if(pages!=null && !"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" order by DATE_FORMAT(a.cfsj,'%Y-%m-%d') desc limit "+num+",10 ";
		}
		String sql="    SELECT   a.cph, DATE_FORMAT(a.cfsj,'%Y-%m-%d') as cfsj,a.cfd,"
				+ "a.mdd,a.zt FROM sh_xcsk a"
				+ " where 1=1 "
				+ "and a.cfd !=''"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 根据标题获取失物招领的总条数
     * @author oufeng
	 * @date 2015年6月23日
	 * @param bt 标题
	 * @return
	 */
	public int getCount(String bt){
		int rows = this.getShXcskList("", bt).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 根据编号获取失物招领信息
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getShxsckByCph(String cph){
		String sql="SELECT distinct a.id, a.cph, DATE_FORMAT(a.cfsj,'%H:%i') as cfsj,"
				+ " DATE_FORMAT(a.ddsj,'%H:%i') as ddsj,a.cfd,"
				+ "a.mdd,a.zt,a.fbr,a.fbrxm FROM sh_xcsk a "
				+ "where cph = '"+cph+"'"+"order by DATE_FORMAT(a.cfsj,'%H:%i') ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 校车的路线图
	 * @author oufeng
	 * @date 2015年6月28日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getShXcskRoute(String cph){
		 String str=" order by a.cfsj";
		String sql="SELECT distinct a.id, a.cph, DATE_FORMAT(a.cfsj,'%H:%i') as cfsj,"
				+ " DATE_FORMAT(a.ddsj,'%H:%i') as ddsj,a.cfd,"
				+ "a.mdd FROM sh_xcsk a "
				+ "where a.cph = '"+cph+"'"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 校车的车牌号
	 * @author oufeng
	 * @date 2015年6月28日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getCph(){
		String sql="SELECT distinct a.cph"
				+ " FROM sh_xcsk a order by a.cph";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 校车的车牌号
	 * @author oufeng
	 * @date 2015年6月28日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getFirst(){
		String sql="SELECT distinct a.id, a.cph, DATE_FORMAT(a.cfsj,'%H:%i') as cfsj,"
				+ " DATE_FORMAT(a.ddsj,'%H:%i') as ddsj,a.cfd,"
				+ "a.mdd,a.zt,a.fbr,a.fbrxm FROM sh_xcsk a "
				+ "where a.cph=(select distinct a.cph from sh_xcsk a order by a.cph limit 1 )"
				+ "order by DATE_FORMAT(a.cfsj,'%H:%i') ";
		return jdbcTemplate.queryForList(sql);
	}

}
