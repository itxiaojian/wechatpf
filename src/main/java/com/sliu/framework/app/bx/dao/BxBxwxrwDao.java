package com.sliu.framework.app.bx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxwxrw;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;


/**
*报修维修任务
@Author oufeng	
@Date 2016年11月2日 下午8:30:13
@Version 1.0
*/
@Repository
public class BxBxwxrwDao extends HibernateBaseDaoImpl<BxBxwxrw, Long> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 报修任务
	 * @author oufeng
	 * @date 2016年11月2日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getRw  (String yhid,String pages) {
		String str = "";
		if(yhid!=null&&!"".equals(yhid)){
			str+=" and a.wxrbh='"+yhid+"'";
		}else{str+=" and 1=2";}
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str+=" order by  a.cjsj desc limit "+num+",10 ";
		}
		String sql = "select  a.id,a.wxrbh,a.bxid,a.bxdz,date_format(a.yysj,'%y-%m-%d') as yysj,"
						+" a.bxzt,a.bxzl,date_format(a.cjsj,'%Y-%m-%d %H:%i:%s') as cjsj,b.xm  "
						+ "  from bx_bxwxrw a,sys_yh b where  a.wxrbh=b.yhbh"+str ;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 删除报修任务
	 * @author oufeng
	 * @date 2016年11月2日
	 * @param bxbh
	 * @return
	 */
	public void delete  (String id) {
		String sql = "delete from bx_bxwxrw where  id='"+id+"' ";
		jdbcTemplate.execute(sql);
	}
}


