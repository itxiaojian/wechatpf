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
import com.sliu.framework.app.wsh.model.ShZttl;

/**
 * 专题讨论
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Repository
public class ShZttlDao extends HibernateBaseDaoImpl<ShZttl, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询专题讨论
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param start
	 * @param limit
	 * @param ztmc  专题名称
	 * @param ztbt  专题标题
	 * @return
	 */
		public Pagination<Map<String,Object>> getZttlList(Integer start, Integer limit,String ztmc,String ztbt) {
			String sql = "select a.id,a.ztmc,a.ztbt,a.ztnr,DATE_FORMAT(a.fbsj,'%Y-%m-%d %H:%i:%S') as fbsj,DATE_FORMAT(a.jhjssj,'%Y-%m-%d %H:%i:%S') as jhjssj,a.fbr,a.ztzhjg,a.zt from WSH_ZTTL a "
					+ " where 1=1";
			if (StringUtils.hasText(ztmc)) {
				sql += " and a.ztmc = '"+ztmc+"'";
			}
			if (StringUtils.hasText(ztbt)) {
				sql += " and a.ztbt = '"+ztbt+"'";
			}
			sql += " order by a.fbsj desc";
			return jdbcPager.queryPage(sql, start, limit);
		}
		

		/**
		 * 查询专题列表
		 * @author   zhangyan
		 * @version 创建时间：2016年7月21日 
		 * @return
		 */
		public List<Map<String, Object>> getCx(String pages){
			String str="";
	 	   if(pages!=null&&!"".equals(pages)){
				int num=Integer.parseInt(pages)*20;
				str=str+" order by a.fbsj DESC limit "+num+" ";
			}
			
	 	   String sql = "select a.id,a.ztmc,a.ztbt from WSH_ZTTL a "
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
	      String sql = "select a.id,a.ztmc,a.ztbt,a.ztnr,DATE_FORMAT(a.fbsj,'%Y-%m-%d %H:%i:%S') as fbsj,DATE_FORMAT(a.jhjssj,'%Y-%m-%d %H:%i:%S') as jhjssj,a.fbr,a.ztzhjg,a.zt from WSH_ZTTL a "
					+ " where 1=1"+str;
	  	List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
	  	return list;
	     }
		
	    
	 	
}
