package com.sliu.framework.app.wsh.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShWhbYm;

@Repository
public class ShWhbYmDao extends HibernateBaseDaoImpl<ShWhbYm,Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 删除页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @return
	 */
	public String deleteYm(String hbid,String zt){
		String str="0";
		String sql="DELETE FROM sh_whbym WHERE HBID = "+hbid+" AND FMZT='"+zt+"'";
		jdbcTemplate.execute(sql);
		str="1";
		return str;
	}

	/**
	 * 修改页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @param request
	 * @return
	 */
	public String updateYm(HttpServletRequest request) {
		String str="0";
		String hbid=request.getParameter("hbid");
		String bgtp=request.getParameter("bgtp");
		String zw=request.getParameter("zw");
		String zt=request.getParameter("zt");
		String sql="UPDATE sh_whbym SET FMZW = '"+zw+"', FMTP = '"+bgtp+"' WHERE HBID = "+hbid+" AND FMZT='"+zt+"'";
		jdbcTemplate.execute(sql);
		str="1";
		return str;
	}

	/**
	 * 删除页面
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param hbid
	 */
	public void deleteYm(String hbid) {
		String sql="DELETE FROM sh_whbym WHERE HBID = "+hbid;
		jdbcTemplate.execute(sql);
	}
}