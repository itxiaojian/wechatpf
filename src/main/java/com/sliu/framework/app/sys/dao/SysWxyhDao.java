package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysWxyh;

@Repository
public class SysWxyhDao extends HibernateBaseDaoImpl<SysWxyh, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询信息数据
	 * @author liujiasen
	 * @date 2015年5月19日
	 * @param start
	 * @param limit
	 * @param xm
	 * @param wxh
	 * @return
	 */
	public Pagination<Map<String,Object>> getWxyhList(Integer start, Integer limit,String xm,String wxh) {
		String str="";
		if(xm!=null&&!"".equals(xm)){
			str=str+" and b.xm like '%"+xm+"%'";
		}else{
			str=str+" and b.xm like '%%'";
		}
		if(wxh!=null&&!"".equals(wxh)){
			str=str+" and a.wxh like '%"+wxh+"%'";
		}
		String sql = "select a.id,a.wxh,a.wxnc,DATE_FORMAT(a.cjsj,'%Y-%m-%d') as cjsj,a.zt,a.bz,b.dlm,b.xm,b.bmbh,c.bmmc,"
				+ "b.gwbh,d.bmmc as gwmc,b.sjh,b.yx from sys_wxyh a left join sys_yh b on a.YHID=b.YHBH and b.yhzt=1 "
				+ "left join sys_zzjg c on b.bmbh=c.bmbh left join sys_zzjg d on b.gwbh=d.bmbh where 1=1 "+str+" order by c.bmmc desc";
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 根据用户名和密码查询用户
	 * @author liujiasen
	 * @date 2015年5月18日
	 * @param username
	 * @param password
	 * @return
	 */
	public List<Map<String,Object>> getYh(String username,String password){
		String sql="select a.yhbh,b.jsbh from sys_yh a left join sys_yhjs b on a.yhbh=b.yhbh where a.dlm='"+username+"' and a.mm='"+password+"' and a.yhzt=1";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String,Object>> getWxyh(String wxh){
		String sql="select id,yhid,wxh,fzid,wxnc,xb,yhyy,yhszcs,yhszsf,yhszgj,yhtx,cjsj,zt,unionid,yhbzm,yhgzsj,bz from sys_wxyh where wxh='"+wxh+"' and zt=1";
		return jdbcTemplate.queryForList(sql);
	}

	public void update(String id,String zt) {
		String sql="update sys_wxyh set zt="+zt+" where id='"+id+"'";
		jdbcTemplate.execute(sql);
	}
	
	public List<Map<String,Object>> getBdyh(String wxh){
		String sql="select id,yhid,wxh,fzid,wxnc,xb,yhyy,yhszcs,yhszsf,yhszgj,yhtx,cjsj,zt,unionid,yhbzm,yhgzsj,bz from sys_wxyh where wxh='"+wxh+"' and zt=2";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户权限
	 * @author liujiansen
	 * @date 2015年7月31日
	 * @param yhbh
	 * @return
	 */
	public List<Map<String,Object>> getJs(String yhbh){
		String sql="select a.yhbh,a.jsbh,b.jsmc,b.bz from sys_yhjs a left join sys_js b on a.jsbh=b.jsbh where a.yhbh='"+yhbh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户绑定的微信openId
	 * @author liujiansen
	 * @date 2015年8月11日
	 * @param yhid 用户编号
	 * @return
	 */
	public List<Map<String,Object>> getOpenId(String yhid){
		String sql="SELECT id, yhid, wxh  FROM sys_wxyh where yhid='"+yhid+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String,Object>> getGwbh(String yhbh){
		String sql="SELECT gwbh FROM sys_yh where yhbh='"+yhbh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据openid获取关注公众号的微信用户
	 * @author liujiansen
	 * @date 2016年1月20日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getWxUser(String openId){
		String sql="SELECT openid, nickname FROM wx_user_info where openid='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
}
