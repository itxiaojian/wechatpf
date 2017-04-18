package com.sliu.framework.app.wxutil.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wxutil.model.WxTemplateNews;



/**
 * 消息主体
 * @author zhangyi
 * @version 创建时间：2016年4月18日  下午3:13:46
 */
@Repository
public class WxTemplateNewsDao  extends HibernateBaseDaoImpl<WxTemplateNews, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页模版消息
	 * @author  zhangyi
	 * @version 创建时间：2016年4月18日  下午3:13:54
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTemplateList(Integer start, Integer limit){
		
		String sql = "select a.id, a.title,a.template_Id as \"templateid\", a.url,a.TOPCOLOR as \"topcolor\",a.bz,b.bz as cssm,DATE_FORMAT(a.addtime,'%Y-%m-%d %H:%i:%S') as addtime from WX_TEMPLATE_NEWS a left join wx_template_sample b on a.template_Id=b.template_Id order by a.addtime desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 查询
	 * @author zhangyi
	 * @version 创建时间：2016年4月18日  下午3:19:06
	 * @return
	 */
	public List<Map<String, Object>> getCx(){
		String sql = " select a.id, a.template_Id, a.bz from WX_TEMPLATE_SAMPLE a  ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
}
