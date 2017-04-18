package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsKyxm;

/**
 * 科研项目
 * @author duanpeijun
 * @date  2015年6月18日
 */
@Repository
public class ZsKyxmDao extends HibernateBaseDaoImpl<ZsKyxm, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据页数和标题查询科研项目数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   项目名称,工号,姓名
	 * @return
	 */
	public List<Map<String,Object>> getKyxmList(String pages,String code){
		String str="";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(gh,''),ifnull(xm,''),ifnull(xmmc,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by lxrq DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by lxrq DESC limit "+num+",5 ";
			}
		}
		String sql="select id,xmmc,DATE_FORMAT(lxrq,'%Y-%m-%d') as lxrq,bm,gh,xm,zzjf,bz from "
				+ "zs_kyxm where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
