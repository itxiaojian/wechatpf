package com.sliu.framework.app.wxutil.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wxutil.model.WxTwxxnr;



/**
 * 图文消息内容
 * @author : zhangyi
 * @version 创建时间：2016年9月2日 下午5:27:05
 */
@Repository
public class WxTwxxnrDao  extends HibernateBaseDaoImpl<WxTwxxnr, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 查询一键救援详情
	 * @author zhangyi
	 * @version 创建时间：2016年4月18日  下午3:19:06
	 * @return
	 */
	public List<Map<String, Object>> getCx(){
		String sql = " select a.id, a.template_Id, a.bz from WX_TEMPLATE_SAMPLE a  ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> getTemplateDateBytem(Long templateId) {
		String sql = " select a.id, a.TEMPLATEID as \"templateid\" ,a.value,a.color, a.bz,a.px from WX_TEMPLATE_DATA a where a.templateid="+templateId+" order by a.px asc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public Pagination<Map<String, Object>> getTwxxnrList(Integer start, Integer limit, Long ztid) {
		String sql = "select t.ztid,t.xxbt,t.xxnr,t.tjsj,t.sltid,t.px,t.id from wx_twxxnr t where t.ztid="+ztid+" order by t.px asc";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	public List<Map<String, Object>> getTwxxnr(int ztid) {
		String sql = "select t.ztid,t.xxbt,t.xxnr,t.tjsj,t.sltid,t.px,t.id from wx_twxxnr t where t.id="+ztid+" order by t.px asc";
		return jdbcTemplate.queryForList(sql);
	}
}
