package com.sliu.framework.app.wxutil.dao;

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
import com.sliu.framework.app.wxutil.model.WxMeeting;

/**
 * 微会议
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:03
 */
@Repository
public class WxMeetingDao extends HibernateBaseDaoImpl<WxMeeting, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 查询自己发起的列表
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月23日 下午4:27:40 
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> ownerpageList(Integer start, Integer limit) {
		SysYh user = AppUtil.getCurrentUser();
		String sql = " select t.id,t.hybt,t.hynr,t.kssj,t.jhjssj,t.sjjssj,t.cjr,t.hyzt,t.hyrs,t.bz from wx_meeting t "
				+ "where 1=1 and t.cjr='"+user.getUsername()+"' order by t.hyzt,t.kssj";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 微生活-历史会议
	 * @author zhangyan
	 * @date 2016年10月14日 上午9:40:39
	 * @param 
	 * @return
	 */
	 public List<Map<String, Object>> getLshy(String openId) {
    	 String str="";
    	 String sql = "select a.id,a.hybt,a.hynr,DATE_FORMAT(a.kssj,'%Y-%m-%d %H:%i:%S') as kssj,DATE_FORMAT(a.jhjssj,'%Y-%m-%d %H:%i:%S') as jhjssj,"
    	 		+ "DATE_FORMAT(a.sjjssj,'%Y-%m-%d %H:%i:%S') as sjjssj,a.cjr,a.hyzt,a.hyrs from wx_meeting a left join wx_meet_menber b on b.hyid=a.id "
				+ " where 1=1 and b.hyryid='"+openId+"' and b.sfdc='1' "+str;
    	 List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    	 return list;
     }
	 
	 /**
	  * 历史会议详情
	  * @author zhangyan
	  * @date 2016年10月14日 上午10:15:37
	  * @param 
	  * @return
	  */
	 public List<Map<String, Object>> getLshyDetail(String id,String openId) {
		 
    	 String sql = "select a.id,a.hybt,a.hynr,DATE_FORMAT(a.kssj,'%Y-%m-%d %H:%i:%S') as kssj,DATE_FORMAT(a.jhjssj,'%Y-%m-%d %H:%i:%S') as jhjssj,"
    	 		+ "DATE_FORMAT(a.sjjssj,'%Y-%m-%d %H:%i:%S') as sjjssj,a.cjr,a.hyzt,a.hyrs from wx_meeting a where a.id="+id;
    	 List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    	 return list;
     }

}
