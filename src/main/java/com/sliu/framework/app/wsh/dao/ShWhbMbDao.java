package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWhbFJ;

@Repository
public class ShWhbMbDao extends HibernateBaseDaoImpl<ShWhbFJ, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 
	 * 获得模板未发布
	 * 
	 * @Author oufeng
	 * @Date 2015年7月6日 下午1:51:45
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getMb() {
		String sql = "SELECT a.id, a.hbmc as mbzt, a.bjyy as mbzw, a.hbfm as mbtp, "
				+ "a.cjrbh,DATE_FORMAT(a.cjsj,'%Y-%m-%d %H:%i:%s') as cjsj, a.bz, "
				+ "case when a.zt=0 then '未发布' else '发布' end as sffb  FROM sh_whbzt a where a.zt='0'";
		return jdbcTemplate.queryForList(sql);
	}

	/*
	 * public void delete(Integer id){ String sql
	 * ="delete from sh_whbmb where id ="+id; jdbcTemplate.execute(sql);
	 * 
	 * }
	 */

	/**
	 * 
	 * 获得背景音乐列表
	 * 
	 * @Author oufeng
	 * @Date 2015年7月6日 下午1:51:45
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getBjMusic() {
		String sql = "select id,fjlx,fjlj,fjmc,tjsj from sh_whbfj where  fjlx=0";
		return jdbcTemplate.queryForList(sql);

	}

	/**
	 * 
	 * 删除添加的海报的页面
	 * 
	 * @Author oufeng
	 * @Date 2015年7月6日 下午1:51:45
	 * @Version 1.0
	 */
	public void deleteYm(int id) {
		String sql = "delete from sh_whbym where hbid= " + id;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 删除海报
	 * 
	 * @author liujiansen
	 * @date 2015年8月18日
	 * @param id
	 */
	public void deleteHb(int id) {
		String sql = "delete from sh_whbzt where id=" + id;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 
	 * 获得模板已发布
	 * 
	 * @Author oufeng
	 * @Date 2015年7月6日 下午1:51:45
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getMbNo() {
		String sql = "SELECT a.id, a.hbmc as mbzt, a.bjyy as mbzw, a.hbfm as mbtp, "
				+ "a.cjrbh,DATE_FORMAT(a.cjsj,'%Y-%m-%d %H:%i:%s') as cjsj, a.bz, "
				+ "case when a.zt=0 then '未发布' else '发布' end as sffb  FROM sh_whbzt a where a.zt='1'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 
	 * 获得供选择的模板
	 * 
	 * @Author oufeng
	 * @Date 2015年7月6日 下午1:51:45
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getMbHidden() {
		String sql = "SELECT  a.id,a.mbzt,a.mbzw,a.mbtp,a.bz,DATE_FORMAT(a.cjsj,'%Y-%m-%d %H:%i:%s') as cjsj,"
				+ "case when a.sffb=0 then '未发布' else '已发布' end as sffb "
				+ " from sh_whbmb a  order by a.id";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获取最新发布的海报
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @return
	 */
	public List<Map<String, Object>> getWhb() {
		String sql = "SELECT a.id, a.hbmc, a.bjyy, a.hbfm, "
				+ "a.cjrbh,DATE_FORMAT(a.cjsj,'%Y-%m-%d %H:%i:%s') as cjsj, a.bz, "
				+ "case when a.zt=0 then '未发布' else '发布' end as sffb  FROM sh_whbzt a where a.zt='1' order by cjsj desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据海报编号获取海报的页面
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @return
	 */
	public List<Map<String, Object>> getWhbYm(String hbid) {
		String sql = "SELECT id, hbid, mbid, fmzt, fmzw, fmtp, bz FROM sh_whbym where hbid="+hbid+" order by id";
		return jdbcTemplate.queryForList(sql);
	}
}