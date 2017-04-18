package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wzy.model.ZyLbt;

/**
 * 主页轮播图 DAO
 * 
 * @author liujiansen
 * @since 2016-03-07
 */
@Repository
public class ZyLbtDao extends HibernateBaseDaoImpl<ZyLbt, Long> {
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
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit, String tpmc, String tplx) {
		String str = "";
		if (tpmc != null && !"".equals(tpmc)) {
			str = str + " and a.tpmc LIKE '%" + tpmc + "%'";
		}
		if (tplx != null && !"".equals(tplx)) {
			str = str + " and a.tplx = '" + tplx + "'";
		}
		String sql = "SELECT a.id, a.tpmc, a.tpdz, a.tplx,c.zdmc as lxmc, DATE_FORMAT(a.scsj,'%Y-%m-%d') as scsj, a.scr,b.xm, a.sfsx FROM zy_lbt a "
				+ "left join sys_yh b on a.scr=b.dlm left join (select zdz,zdmc from sys_sjzd where zl='tplx' and jb='2') c on a.tplx=c.zdz where 1=1 "+str;
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
}
