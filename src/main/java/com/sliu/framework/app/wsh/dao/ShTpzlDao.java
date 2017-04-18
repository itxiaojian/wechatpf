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
import com.sliu.framework.app.wsh.model.ShTpzl;

/**
 * 投票专栏
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Repository
public class ShTpzlDao extends HibernateBaseDaoImpl<ShTpzl, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询投票专栏
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param start
	 * @param limit
	 * @param ztmc  专题名称
	 * @param ztbt  专题标题
	 * @return
	 */
		public Pagination<Map<String,Object>> getTpzlList(Integer start, Integer limit) {
			String sql = "select a.id,a.tpnr,DATE_FORMAT(a.fbsj,'%Y-%m-%d %H:%i:%S') as fbsj,DATE_FORMAT(a.jssj,'%Y-%m-%d %H:%i:%S') as jssj,a.fqr,a.zdxx,a.zt from WSH_TPZL a "
					+ " where 1=1";
			sql += " order by a.fbsj desc";
			return jdbcPager.queryPage(sql, start, limit);
		}
		
		/**
		 * 根据id查找投票内容
		 * @author zhangyan
		 * @date 2016年7月22日
		 * @param id
		 * @return
		 */
		public List<Map<String,Object>> getTpnr(Long id){
			String sql="select a.id,a.tpnr,a.zdxx,DATE_FORMAT(a.fbsj,'%Y-%m-%d %H:%i') as fbsj,DATE_FORMAT(a.jssj,'%Y-%m-%d %H:%i') as jssj,a.fqr,a.zt from wsh_tpzl a where a.id='" + id
					+ "'";
			return jdbcTemplate.queryForList(sql);
		}
		
		/**
		 * 前台：投票列表
		 * @author   zhangyan
		 * @version 创建时间：2016年7月26日 
		 * @return
		 */
		public List<Map<String, Object>> getList(String pages){
			String str="";
	 	   if(pages!=null&&!"".equals(pages)){
				int num=Integer.parseInt(pages)*20;
				str=str+" order by a.fbsj DESC limit "+num+" ";
			}
			
	 	  String sql = "select a.id,a.tpnr,DATE_FORMAT(a.fbsj,'%Y-%m-%d %H:%i:%S') as fbsj,DATE_FORMAT(a.jssj,'%Y-%m-%d %H:%i:%S') as jssj,a.fqr,a.zdxx,a.zt from WSH_TPZL a "
					+ " where 1=1"+str;
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			return list;
		}
		
		/**
	     *  查询相应的投票的详细内容
	     * @author   zhangyan
	     * @version 创建时间：2016年7月26日  
	     * @return
	     */
	     public List<Map<String, Object>> getDetailById(String id) {
	    	
	      String sql = "select a.id,a.tpnr,DATE_FORMAT(a.fbsj,'%Y-%m-%d %H:%i:%S') as fbsj,DATE_FORMAT(a.jssj,'%Y-%m-%d %H:%i:%S') as jssj,a.fqr,a.zdxx,a.zt from WSH_TPZL a "
					+ " where a.id='"+id+"'";
	  	List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
	  	return list;
	     }
	
	     /**
	 	 * 根据openId获取微信关注用户
	 	 * @author liujiasen
	 	 * @date 2015年7月24日
	 	 * @param openId
	 	 * @return
	 	 */
	 	public List<Map<String,Object>> getUser(String openId){
	 		String sql="select openid,nickname from wx_user_info where openid='"+openId+"'";
	 		return jdbcTemplate.queryForList(sql);
	 	}
	
}
