package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wzy.model.ZyTppz;

/**
 * 主页轮播图 DAO
 * 
 * @author liujiansen
 * @since 2016-03-07
 */
@Repository
public class ZyTppzDao extends HibernateBaseDaoImpl<ZyTppz, Long> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 查询图片列表信息
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param start
	 * @param limit
	 * @param tpmc
	 * @param tplx
	 * @return
	 */
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit, String tpmc, String lx) {
		String str = "";
		if (tpmc != null && !"".equals(tpmc)) {
			str = str + " and a.tpmc LIKE '%" + tpmc + "%'";
		}
		if (lx != null && !"".equals(lx)) {
			str = str + " and a.lx = '" + lx + "'";
		}
		String sql = "SELECT a.id, a.tpmc, a.tpbcid, a.lx,a.url,a.px FROM zy_tppz a where 1=1 "+str;
		sql += " order by a.px ";
		return jdbcPager.queryPage(sql, start, limit);
	}

	/**
	 * 获取图片类型
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @return
	 */
	public List<Map<String, Object>> getTplx(String zl) {
		String sql="select zdz,zdmc from sys_sjzd where zl='"+zl+"' and jb='2'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获取轮播图
	 * @author:zhangyi 
	 * @version 创建时间：2016年6月29日 上午10:10:13 
	 * @return
	 */
	public List<Map<String, Object>> getListLbt() {
		String sql = "SELECT a.id, a.tpmc, a.tpbcid, a.lx,a.url,a.px FROM zy_tppz a order by a.px";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getIdbyfile(String fileNameU) {
		String sql="select id from sys_fjgl where fname='"+fileNameU+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取tpbcid
	 * @author zhangyan
	 * @date 2016年6月30日
	 * @return
	 */
	public List<Map<String, Object>> getTpbcidById(Long id) {
		String sql="select tpbcid from zy_tppz where id='"+id+"'";
		return jdbcTemplate.queryForList(sql);
	}
}
