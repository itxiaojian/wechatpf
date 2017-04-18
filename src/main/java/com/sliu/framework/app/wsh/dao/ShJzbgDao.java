package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wsh.model.ShJzbg;

/**
 * 讲座报告
 * @author zhangyan
 * @version 创建时间：2016年8月1日  
 */
@Repository
public class ShJzbgDao extends HibernateBaseDaoImpl<ShJzbg, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询讲座报告
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @param start
	 * @param limit
	 * @param jzmc  讲座名称
	 * @param jzbt  讲座标题
	 * @return
	 */
		public Pagination<Map<String,Object>> getJzbgList(Integer start, Integer limit,String jzmc,String jzbt) {
			String sql = "select a.id,a.jzmc,a.jzbt,a.jznr,DATE_FORMAT(a.jzsj,'%Y-%m-%d %H:%i:%S') as jzsj,a.fbr,a.bz,a.zt from WSH_JZBG a "
					+ " where 1=1";
			if (StringUtils.hasText(jzmc)) {
				sql += " and a.jzmc = '"+jzmc+"'";
			}
			if (StringUtils.hasText(jzbt)) {
				sql += " and a.jzbt = '"+jzbt+"'";
			}
			sql += " order by a.jzsj desc";
			return jdbcPager.queryPage(sql, start, limit);
		}
		

		/**
		 * 查询讲座报告
		 * @author   zhangyan
		 * @version 创建时间：2016年8月1日 
		 * @return
		 */
		public List<Map<String, Object>> getCx(String pages){
			String str="";
	 	   if(pages!=null&&!"".equals(pages)){
				int num=Integer.parseInt(pages)*20;
				str=str+" order by a.jzsj DESC limit "+num+" ";
			}
			
	 	   String sql = "select a.id,a.jzmc,a.jzbt,DATE_FORMAT(a.jzsj,'%Y-%m-%d') as jzsj from WSH_JZBG a "
					+ " where 1=1"+str;
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
	     *  查询相应的专题的详细内容
	     * @author   zhangyan
	     * @version 创建时间：2016年7月21日  
	     * @return
	     */
	     public List<Map<String, Object>> getDetailById(String id) {
	    	 String str="";
	      if (StringUtils.hasText(id)) {
	 			str += " and id = '"+id+"'";
	 		}else{str+=" and  1=2";}	 
	      String sql = "select a.id,a.jzmc,a.jzbt,a.jznr,DATE_FORMAT(a.jzsj,'%Y-%m-%d %H:%i:%S') as jzsj,a.fbr,a.bz,a.zt from WSH_JZBG a "
					+ " where 1=1"+str;
	  	List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
	  	return list;
	     }
		
	    
	 	
}
