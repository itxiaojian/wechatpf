package com.sliu.framework.app.wsh.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.wsh.model.ShSwzl;

@Repository
public class ShSwzlDao extends HibernateBaseDaoImpl<ShSwzl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	
	/**
	 * 根据页数和标题查询失物招领数据
	 * @author liujiansen
	 * @date 2015年6月16日
	 * @param pages 页数
	 * @param bt 标题 
	 * @return
	 */
	public List<Map<String,Object>> getSwzlList(String pages,String bt,String lx){
		String str="";
		if(bt!=null&&!"".equals(bt)){
			str=str+" and bt like '%"+bt+"%' ";
		}
		if(lx!=null&&!"".equals(lx)){
			str=str+" and lx='"+lx+"' ";
		}
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			str=str+" order by FQSJ desc limit "+num+" ";
		}
		String sql="select id,lx,bt,xwzs,DATE_FORMAT(fqsj,'%Y-%m-%d %H:%i:%S') as fqsj,jssj,fbr,fbrxm,xwzt,dd,yktkh,bz from "
				+ "sh_swzl where 1=1 and lx in('1','2')"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据页数和标题查询拾卡招领数据
	 * @author liujiansen
	 * @date 2015年6月16日
	 * @param pages 页数
	 * @param bt 标题 
	 * @return
	 */
	public List<Map<String,Object>> getSkzlList(String pages,String bt,String lx){
		String str="";
		if(bt!=null&&!"".equals(bt)){
			str=str+" and bt like '%"+bt+"%' ";
		}
//		if(lx!=null&&!"".equals(lx)){
//			str=str+" and lx='"+lx+"' ";
//		}
		if(pages!=null&&!"".equals(pages)){
			int num=Integer.parseInt(pages)*10;
			str=str+" order by FQSJ desc limit "+num+" ";
		}
		String sql="select id,lx,bt,xwzs,DATE_FORMAT(fqsj,'%Y-%m-%d %H:%i:%S') as fqsj,jssj,fbr,fbrxm,xwzt,dd,yktkh,bz from "
				+ "sh_swzl where 1=1 and lx='3' "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据标题获取失物招领的总条数
	 * @author liujiansen
	 * @date 2015年6月16日
	 * @param bt 标题
	 * @return
	 */
	public int getCount(String bt,String lx){
		int rows = this.getSwzlList("", bt,lx).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 根据编号获取失物招领信息
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getSwzlById(String id){
		String sql="select id,lx,bt,xwzs,DATE_FORMAT(fqsj,'%Y-%m-%d %H:%i:%S') as fqsj,"
				+ "DATE_FORMAT(jssj,'%Y-%m-%d %H:%i:%S') as jssj,fbr,fbrxm,xwzt,dd,yktkh,bz from sh_swzl where id='"+id+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 失物招领认领
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param id 编号
	 */
	public void update(String id){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="UPDATE sh_swzl SET JSSJ = '"+sim.format(new Date())+"', XWZT = '2' WHERE ID = '"+id+"'";
		jdbcTemplate.execute(sql);
	}

	public Pagination<Map<String, Object>> pager(Integer start, Integer limit,
			String code, String fbrxm) {
		String str="";
		if(code!=null&&!"".equals(code)){
			str=str+" and concat(ifnull(bt,''),ifnull(xwzs,'')) like '%"+code+"%'";
		}
		if(fbrxm!=null&&!"".equals(fbrxm)){
			str=str+" and fbrxm LIKE '%"+fbrxm+"%'";
		}
		String sql = "select id,lx,bt,xwzs,DATE_FORMAT(fqsj,'%Y-%m-%d %H:%i:%S') as fqsj,"
				+ "DATE_FORMAT(jssj,'%Y-%m-%d %H:%i:%S') as jssj,fbr,fbrxm,xwzt,dd,yktkh,bz from sh_swzl where 1=1 "+str;
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	
	public Pagination<Map<String,Object>> getSkList(Integer start,Integer limit,String code){
		String str="";
		if(code!=null&&!"".equals(code )){
			str = " ";
		}
		String sql = "select id,lx,bt,xwzs,fqsj,jssj,fbr,fbrxm,xwzt,dd,yktkh,bz from sh_swzl where lx='4' " +str;
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	public List<Map<String,Object>> skxx(Long id){
		String sql =  "SELECT a.lx,a.bt,a.xwzs,a.fbrxm,a.dd,a.yktkh,c.yhbh,c.xm,d.wxh " 
				 +" FROM sh_swzl a,sys_yh c,sys_wxyh d "
				 +" WHERE a.id='"+id+"' and  a.lx='4'  and a.yktkh = c.yhbh  "
				 +" and c.yhbh=d.yhid " ;
		return jdbcTemplate.queryForList(sql);
	}
}
