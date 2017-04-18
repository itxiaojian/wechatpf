package com.sliu.framework.app.wlx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wlx.model.LxXgxx;

/**
 * 离校相关信息
 * @author zhangyan
 * @version 创建时间：2016年6月6日  
 */
@Repository
public class LxXgxxDao  extends HibernateBaseDaoImpl<LxXgxx, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 
	 * @author:zhangyan
	 * @version 创建时间：2016年6月6日  
	 * @param start
	 * @param limit
	 * @param type   信息类型
	 * @param title   信息标题
	 * @return
	 */
	public Pagination<Map<String,Object>> getXyxgxxList(Integer start, Integer limit,String type,String title) {

		String sql = "select a.id,a.title,a.content,DATE_FORMAT(a.fbtime,'%Y-%m-%d %H:%i:%S') as fbtime,a.author,a.ly,a.type from ZS_LXGG a "
				+ " where 1=1";
		if (StringUtils.hasText(type)) {
			sql += " and a.type = '"+type+"'";
		}
		if (StringUtils.hasText(title)) {
			sql += " and a.title = '"+title+"'";
		}
		
		sql += " order by a.fbtime desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
/**
 * 	
 * @author:duanpeijun
 * @version 创建时间：2015年6月3日  下午3:44:26
 * @param zdmc  字段名称	
 * @param zdz     字段值
 * @return
 */
	public List<Map<String, Object>> getTjcx(String zdmc,Integer zdz){
		SysYh user=AppUtil.getCurrentUser();
		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl="+"'xyxx'"+" and a.jb ="+"'2'";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
	
	/**
	 *  分类查询页面跳转：学院简介，学院风光，办事流程等。。。
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月5日  下午3:42:47
	 * @param zdbm  字典编码
	 * @return
	 */
	public List<Map<String, Object>> getXyjj(String type){
		String sql = "SELECT a.id, a.title,a.ly,date_format(a.fbtime,'%Y-%m-%d') as fbtime,a.content  "
				+ " FROM  zs_lxgg a WHERE a.type='"+type+"' order by a.fbtime desc ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}
	
	/**
	 * 删除学院相关信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月11日  
	 * @param id
	 */
	public void deleteXyxgxx(Long id){
		String sql = "delete from ZS_LXGG where id="+id;
				jdbcTemplate.execute(sql);
	}
	
	

	
}

