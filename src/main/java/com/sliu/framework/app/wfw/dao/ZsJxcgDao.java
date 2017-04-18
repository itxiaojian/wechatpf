package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsJxcg;

/**
 * 教学成果
 * @author duanpeijun
 * @date  2015年6月19日
 */
@Repository
public class ZsJxcgDao extends HibernateBaseDaoImpl<ZsJxcg, String>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据页数和标题查询教学成果数据
	 * @author duanpeijun
	 * @date  2015年6月18日
	 * @param pages   页数
	 * @param code   成果编号和成果名称
	 * @return
	 */
	public List<Map<String,Object>> getJxcgList(String pages,String code){
		String str="";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(cgbh,''),ifnull(cgmc,'')) like '%"+code+"%'";
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
			int num=Integer.parseInt(pages)*5;
			str=str+" order by hjsj DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*5;
				str=str+" order by hjsj DESC limit "+num+" ,5";
			}
		}
		String sql="select id,cgbh,DATE_FORMAT(hjsj,'%Y-%m-%d') as hjsj,cgmc,lb,bm,gh,xm,zzjf,bz from "
				+ "zs_jxcg where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
