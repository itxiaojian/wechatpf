package com.sliu.framework.app.mass.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxSucaiInfo;

/**
 * 素材管理 Dao
 * @author zhangyi
 *
 */
@Repository
public class MaterialMgrDao extends HibernateBaseDaoImpl<WxSucaiInfo, Long>{
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 获取素材列表
	 * @param start
	 * @param limit
	 * @param scmc 素材名称
	 * @param sclx 素材类型
	 * @return
	 */
	public Pagination<Map<String,Object>> getSuCaiPagination(Integer start,Integer limit, String scmc,String sclx) {
			
			StringBuilder sql = new StringBuilder(" SELECT t.SC_ID AS \"scid\",  CASE t.SC_TYPE   WHEN 1 THEN '图片'   "
					+ "WHEN 2 THEN '语音'   WHEN 3 THEN '视频'  WHEN 4 THEN '图文'  ELSE '其他'   END AS \"sctype\", "
					+ "t.SC_NAME AS \"scname\",  b.FILE_NAME as \"filename\", b.FILE_CODE as \"fileCode\",t.MEDIA_ID AS \"mediaid\",  "
					+ "t.CREATE_USER AS \"createuser\"  , t.CREATE_TIME AS \"createtime\", t.REMARK AS \"scpath\"  "
					+ "FROM WX_SUCAI_INFO t  left join  UNTECK_ATTACHMENT b on t.ATTACHMENT_ID = b.ID WHERE  1 = 1 ");
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			if(scmc != null && !scmc.equals("")  ){
				//sql.append(" and t.WD_NAME = :orgName");
				//paramMap.put("orgName", orgName);
				sql.append(" and t.SC_NAME like " +"'%"+scmc+"%'");
			}
			
			if(sclx != null && !sclx.equals("")){
				if(sclx.equals("0") ){//查询全部
					
				}else{
					sql.append(" and t.SC_TYPE = " +sclx);
				}
				
			}
			
			sql.append(" order by t.CREATE_TIME ");
			return jdbcPager.queryPageDb2(sql.toString(), start, limit, paramMap);
		}
	
	
	public List<Map<String,Object>> queryByName(String scmc){
			
		StringBuilder sql = new StringBuilder(" SELECT t.SC_ID AS \"scid\",  CASE t.SC_TYPE   WHEN 1 THEN '图片'   "
				+ "WHEN 2 THEN '语音'   WHEN 3 THEN '视频'  WHEN 4 THEN '图文'  ELSE '其他'   END AS \"sctype\", "
				+ "t.SC_NAME AS \"scname\",  b.FILE_NAME as \"filename\", t.MEDIA_ID AS \"mediaid\",  "
				+ "t.CREATE_USER AS \"createuser\"  , t.CREATE_TIME AS \"createtime\", t.REMARK AS \"scpath\"  "
				+ "FROM WX_SUCAI_INFO t  left join  UNTECK_ATTACHMENT b on t.ATTACHMENT_ID = b.ID WHERE  1 = 1  ");		
		if(scmc != null && !scmc.equals("")  ){
			//sql.append(" and t.WD_NAME = :orgName");
			//paramMap.put("orgName", orgName);
			sql.append(" and t.SC_NAME = " +"'"+scmc+"'");
		}
		
		sql.append(" order by t.CREATE_TIME ");
			
			return jdbcTemplate.queryForList(sql.toString());
		}
		
	
	
	/**
	 * 获得图文消息详细信息
	 * @param start
	 * @param limit
	 * @param scid  素材id
	 * @return
	 */
	public Pagination<Map<String,Object>> getTuWenInfos(Integer start,Integer limit, String scid) {	
		StringBuilder sql = new StringBuilder("select t.MPNEWS_ID as \"mpnewsid\", t.SC_ID as \"scid\",  "
				+ " t.THUMB_MEDIA_ID as \"thumbmediaid\", t.AUTHOR as \"author\" , t.TITLE as \"title\",   "
				+ " t.CONTENT_SOURCE_URL as \"contentsourceurl\", t.MP_CONTENT as \"mpcontent\",  "
				+ " t.DIGEST as \"digest\", t.SHOW_COVER_PIC as \"showcoverpic2\", "
				+ " case t.SHOW_COVER_PIC when 1 then '显示' "
				+ " when 0 then '不显示' else '其他' end as \"showcoverpic\" "
				+ " from WX_SUCAI_MPNEWS t where 1 = 1  " );
		if(scid != null && !scid.equals("")  ){
			sql.append(" and  t.SC_ID = "+scid);
		}
		return jdbcPager.queryPage(sql.toString(), start, limit);			
	}
	
	public List<Map<String, Object>> getImageList() {
		String sql = "select b.FILE_NAME as \"name\",t.MEDIA_ID as \"thumbMediaId\",b.FILE_CODE as \"fileCode\""
				+ " from WX_SUCAI_INFO t,UNTECK_ATTACHMENT b"
				+ " where t.ATTACHMENT_ID = b.id and t.SC_TYPE = 1 and t.CREATE_TIME >= date_sub(now() ,interval 3 day)";
		return jdbcTemplate.queryForList(sql);
	}
}
