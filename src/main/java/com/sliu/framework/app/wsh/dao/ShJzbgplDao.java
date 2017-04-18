package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShJzbgpl;

/**
 * 讲座报告评论
 * @author zhangyan
 * @version 创建时间：2016年8月1日  
 */
@Repository
public class ShJzbgplDao extends HibernateBaseDaoImpl<ShJzbgpl, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 查看评论
	 * @author zhangyan
	 * @date 2016年7月19日
	 * @return
	 */
	public List<Map<String, Object>> getPinglun(Long id,String pages){	
		String sql = " select a.id,a.bgid,a.plnr,DATE_FORMAT(a.plsj,'%Y-%m-%d %H:%i:%S') as plsj,a.plr,a.plrwxh,b.yhid,c.xm from wsh_jzbgpl a "
				+ " left join sys_wxyh b on a.plrwxh=b.wxh left join sys_yh c on b.yhid=c.dlm where a.bgid='"+id+"' order by  a.plsj desc ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 后台-查看评论
	 * @author zhangyan
	 * @date 2016年7月19日
	 * @return
	 */
	public List<Map<String, Object>> getPinglunht(Long id,String pages){	
		String str="";
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str+=" order by  a.plsj desc limit "+num+",10";
		}
		String sql = " select a.id,a.bgid,a.plnr,DATE_FORMAT(a.plsj,'%Y-%m-%d %H:%i:%S') as plsj,a.plr,a.plrwxh,b.yhid,c.xm from wsh_jzbgpl a "
				+ " left join sys_wxyh b on a.plrwxh=b.wxh left join sys_yh c on b.yhid=c.dlm where a.bgid='"+id+"' "+str;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 前台删除评论
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param id
	 */
	public void deleteHf(Long id){
		
		String sql = "delete from WSH_JZBGPl where id ="+id;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取总条数
	 * @author oufeng
	 * @date 2015年8月10日
	 * @return
	 */
	public int getCount(Long id){
		int rows = this.getPinglun(id,"").size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}

	 /**
 	 * 后台删除投票时删除对应的选项内容
 	 * @author zhangyan
 	 * @date 2016年7月4日
 	 * @param id
 	 * @return
 	 */
 	public void deletepl(Long id) {
 		String sql="delete from wsh_jzbgpl where bgid = '" + id + "' ";
  
 		jdbcTemplate.execute(sql);
 	}
}
