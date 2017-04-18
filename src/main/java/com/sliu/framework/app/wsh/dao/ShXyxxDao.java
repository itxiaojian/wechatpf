package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShXyxx;

/**
 * 校友信息
 * @author duanpeijun
 * @date  2015年7月7日
 */
@Repository
public class ShXyxxDao extends HibernateBaseDaoImpl<ShXyxx, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 
	 *  校友信息
	 * @author duanpeijun
	 * @date  2015年7月7日
	 * @param pages
	 * @param code   昵称，国家，省份，城市
	 * @return
	 */
	public List<Map<String,Object>> getXyxxList(String pages,String code){
		String str="";
		if(code!=null&&!"".equals(code)){
			str=str+" and  concat(ifnull(nickname,''),ifnull(country,''),ifnull(province,''),ifnull(city,''))  like '%"+code+"%'";
			
		}
//		if(pages!=null&&!"".equals(pages)){
//			int num=Integer.parseInt(pages)*10;
//			str=str+" order by a.subscribe_time DESC limit "+num+" ";
//		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.subscribe_time DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by a.subscribe_time DESC limit "+num+",10";
			}
		}
		String sql="select a.user_id,a.openid,a.nickname,a.sex,a.u_language, a.city,a.province,a.country,a.headimgurl,a.subscribe_time,a.unionid,a.remark,a.memo from "
				+ "wx_user_info a where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
