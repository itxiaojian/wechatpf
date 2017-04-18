package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShEssc;

@Repository
public class ShEsscDao extends HibernateBaseDaoImpl<ShEssc, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 查询商品数据
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param pages 页数
	 * @param splx 商品类型
	 * @return
	 */
	public List<Map<String,Object>> getGoodList(String pages,String splx,String keyword,String orderBy){
		String str="";
		if(splx!=null&&!"".equals(splx)){
			str=str+" and splx='"+splx+"' ";
		}
		if(keyword!=null&&!"".equals(keyword)){
			str=str+" and spms like '%"+keyword+"%' ";
		}
		if(orderBy!=null&&!"".equals(orderBy)){
			if("1".equals(orderBy)){
				str=str+" order by DATE_FORMAT(sjsj,'%Y-%m-%d %H:%i') desc";
			}else if("2".equals(orderBy)){
				str=str+" order by jg asc";
			}
		}else{
			str=str+" order by DATE_FORMAT(sjsj,'%Y-%m-%d %H:%i') desc";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" limit "+num+",10 ";
		}
		String sql="select id,spbh,spmc,spzp,jg,spms,wzxm,wzbh,splx,lxfs,wpzt,DATE_FORMAT(sjsj,'%Y-%m-%d %H:%i') as sjsj,"
				+ "DATE_FORMAT(xjsj,'%Y-%m-%d %H:%i') as xjsj,DATE_FORMAT(scsj,'%Y-%m-%d %H:%i') as scsj,bz from "
				+ "sh_essc where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取商品的总条数
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param splx 商品类型
	 * @return
	 */
	public int getCount(String splx,String keyword,String orderBy){
		int rows = this.getGoodList("",splx,keyword,orderBy).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}

	/**
	 * 根据商品Id获取商品信息
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param id 商品Id
	 * @return
	 */
	public List<Map<String, Object>> getGood(String id) {
		String sql="select id,spbh,spmc,spzp,jg,spms,wzxm,wzbh,splx,lxfs,wpzt,DATE_FORMAT(sjsj,'%Y-%m-%d %H:%i') as sjsj,"
				+ "DATE_FORMAT(xjsj,'%Y-%m-%d %H:%i') as xjsj,DATE_FORMAT(scsj,'%Y-%m-%d %H:%i') as scsj,bz from "
				+ "sh_essc where id="+id;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据微信用户的openId获取微信用户的信息
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getWxUser(String openId){
		String sql="select user_id,group_id,subscribe,openid,nickname,sex,u_language,city,province,country from wx_user_info "
				+ "where openid='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 商品下架或售出
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param id 商品Id
	 * @param lx 物品状态
	 * @param sj 操作时间
	 */
	public void update(String id, String lx, String sj) {
		String sql="";
		if("2".equals(lx)){
			sql="update sh_essc t set t.wpzt = '"+lx+"', t.xjsj = '"+sj+"' where t.id = "+id;
		}else{
			sql="update sh_essc t set t.wpzt = '"+lx+"', t.scsj = '"+sj+"' where t.id = "+id;
		}
		jdbcTemplate.execute(sql);
	}
}
