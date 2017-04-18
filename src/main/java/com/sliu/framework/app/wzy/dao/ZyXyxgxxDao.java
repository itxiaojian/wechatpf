package com.sliu.framework.app.wzy.dao;

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
import com.sliu.framework.app.wzy.model.ZyXyxgxx;

/**
 * 主页学院相关信息
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:52:11
 */
@Repository
public class ZyXyxgxxDao  extends HibernateBaseDaoImpl<ZyXyxgxx, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 
	 * @author:duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:43:02
	 * @param start
	 * @param limit
	 * @param xwlx   信息类型
	 * @param xwbt   信息标题
	 * @return
	 */
	public Pagination<Map<String,Object>> getXyxgxxList(Integer start, Integer limit,String xwlx,String xwbt) {

		String sql = "select a.id,a.xwbt,a.xwzs,a.xwnr,DATE_FORMAT(a.sxsj,'%Y-%m-%d %H:%i:%S') as sxsj,DATE_FORMAT(a.gqsj,'%Y-%m-%d %H:%i:%S') as gqsj,a.fbr,a.fbrxm,b.bmmc,a.bmbh,a.xwlx,a.xwzt from ZY_XYXGXX a "
				+ " left join SYS_ZZJG b on a.BMBH=b.BMBH where 1=1";
		if (StringUtils.hasText(xwlx)) {
			sql += " and a.xwlx = '"+xwlx+"'";
		}
		if (StringUtils.hasText(xwbt)) {
			sql += " and a.xwbt = '"+xwbt+"'";
		}
		
		sql += " order by a.sxsj desc";
		
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
	public List<Map<String, Object>> getXyjj(String zdbm){
		String sql = "SELECT a.id, a.xwbt,a.bmbh,a.sxsj,a.xwnr  FROM  zy_xyxgxx a, "
				+ "sys_sjzd b WHERE a.xwlx = b.zdz and b.zdbm='"+zdbm+"' AND b.zl = 'xyxx' AND b.jb='2' order by a.sxsj desc ";
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
		String sql = "delete from zy_xyxgxx where id="+id;
				jdbcTemplate.execute(sql);
	}
	
	

	
}
