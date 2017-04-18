package com.sliu.framework.app.wxtuwen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.model.WxSucaiMpnews;

@Repository
public class WxSucaiMpnewsDao extends HibernateBaseDaoImpl<WxSucaiMpnews, Long>{
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据素材编号查询 图文消息详细信息
	 * @param scid 素材编号
	 * @return
	 */
	public List<Map<String,Object>> queryByName(Long scid){
		
		StringBuilder sql = new StringBuilder(" select t.THUMB_MEDIA_ID as \"thumb_media_id\", t.AUTHOR as \"author\", "
				+ "t.TITLE as \"title\", t.CONTENT_SOURCE_URL as \"content_source_url\", t.MP_CONTENT as \"content\", "
				+ "t.DIGEST as \"digest\", t.SHOW_COVER_PIC as \"show_cover_pic\" from WX_SUCAI_MPNEWS t "
				+ "where t.SC_ID = "+scid+" ");
		
		return jdbcTemplate.queryForList(sql.toString());
	}
		
		
}
