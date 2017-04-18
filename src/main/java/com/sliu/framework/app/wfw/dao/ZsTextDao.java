package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShSwzl;

@Repository
public class ZsTextDao extends HibernateBaseDaoImpl<ShSwzl, Long> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	

	/**
	 * 获取失物招领的信息
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getSwzlxx(String pageSize,String pageNum,String lostType){
		String str="";
		if(lostType!=null&&!"".equals(lostType)){
			str+= " and a.lx= '"+lostType+"'";
		}
		if("".equals(pageNum)){
			pageNum="1";
		}
		int count=(Integer.parseInt(pageNum)-1)*5;
		int num=Integer.parseInt(pageNum)*5;
			str+=" order by a.fqsj DESC limit "+count+","+num+" ";
		String sql="select a.id,a.bt,a.lx,a.xwzs,a.fqsj,a.jssj,a.fbr,a.fbrxm,"
				+ " a.xwzt,a.dd,b.zdmc as lxmc,c.zdmc as ztmc   "
				+ " from sh_swzl a,sys_sjzd b,sys_sjzd c  where 1=1"
				+ " and a.lx = b.zdz and b.zl='xwlx' "
				+ " and a.xwzt = c.zdz and c.zl='xwzt' "
				+ " and b.jb=2 and c.jb=2  "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取失物招领的总页数
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public int getPageAmount(String pageSize,String pageNum,String lostType){
		int totalRecord =0;
		int totalPage =0;
		List<Map<String,Object>> resultAmount = this.resultAmount(lostType);
			if(resultAmount.size()!=0){
				totalRecord=Integer.parseInt((resultAmount.get(0).get("zs")+""));
				totalPage = (totalRecord + Integer.parseInt(pageSize) -1)/Integer.parseInt(pageSize);
			}	
		return totalPage;
	}
	
	/**
	 * 获取失物招领的总页数
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public int myGetPageAmount(String pageSize,String pageNum,String lostType,String openId){
		int totalRecord =0;
		int totalPage =0;
		List<Map<String,Object>> resultAmount = this.myResultAmount(lostType,openId);
			if(resultAmount.size()!=0){
				totalRecord=Integer.parseInt((resultAmount.get(0).get("zs")+""));
				totalPage = (totalRecord + Integer.parseInt(pageSize) -1)/Integer.parseInt(pageSize);
			}	
		return totalPage;
	}
	
	/**
	 * 获取失物招领的总条数
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> resultAmount(String lostType){
		String str="";
		if(lostType!=null&&!"".equals(lostType)){
			str+= " and a.lx= '"+lostType+"'";
		}
		String sql="select count(1) as zs from sh_swzl a where 1=1  "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取失物招领的总条数
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> myResultAmount(String lostType,String openId){
		String str="";
		if(lostType!=null&&!"".equals(lostType)){
			str+= " and a.lx= '"+lostType+"'";
		}
		if(openId!=null&&!"".equals(openId)){
			str+= " and a.fbr= '"+openId+"'";
		}
		String sql="select count(1) as zs from sh_swzl a where 1=1  "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取失物招领的信息
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> myGetSwzlxx(String pageSize,String pageNum,String lostType,String openId){
		String str="";
		if(lostType!=null&&!"".equals(lostType)){
			str+= " and a.lx= '"+lostType+"'";
		}
		if(openId!=null&&!"".equals(openId)){
			str+= " and a.fbr= '"+openId+"'";
		}
		if("".equals(pageNum)){
			pageNum="1";
		}
		int count=(Integer.parseInt(pageNum)-1)*5;
		int num=Integer.parseInt(pageNum)*5;
			str+=" order by a.fqsj DESC limit "+count+","+num+" ";
			String sql="select a.id,a.bt,a.lx,a.xwzs,a.fqsj,a.jssj,a.fbr,a.fbrxm,"
					+ " a.xwzt,a.dd,b.zdmc as lxmc,c.zdmc as ztmc   "
					+ " from sh_swzl a,sys_sjzd b,sys_sjzd c  where 1=1"
					+ " and a.lx = b.zdz and b.zl='xwlx' "
					+ " and a.xwzt = c.zdz and c.zl='xwzt' "
					+ " and b.jb=2 and c.jb=2  "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取微信用户
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getUser(String openId){
		String sql="SELECT a.wxnc, a.wxh,b.xm,b.dlm,a.yhid FROM sys_wxyh a "
				+ " left join sys_yh b on a.yhid=b.yhbh where a.zt='1' and a.wxh='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取微信用户
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getSwzlxx(String lostId){
		String sql="select a.id,a.bt,a.lx,a.xwzs,a.fqsj,a.jssj,a.fbr,a.fbrxm,"
				+ " a.xwzt,a.dd,b.zdmc as lxmc,c.zdmc as ztmc   "
				+ " from sh_swzl a,sys_sjzd b,sys_sjzd c  where 1=1"
				+ " and a.lx = b.zdz and b.zl='xwlx' "
				+ " and a.xwzt = c.zdz and c.zl='xwzt' "
				+ " and b.jb=2 and c.jb=2  "
				+"  and a.id='"+lostId+"'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 完成
	 * @author oufeng
	 * @date  2016年7月14日
	 * @param request
	 * @return
	 */
	public void endLost(String lostId,String lostZt){
		String sql="update sh_swzl  set xwzt='"+lostZt+"'   where id='"+lostId+"'";
		jdbcTemplate.execute(sql);
	}

	
}
