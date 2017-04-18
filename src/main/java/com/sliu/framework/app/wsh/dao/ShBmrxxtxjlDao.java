package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShBmrxxtxjl;

@Repository
public class ShBmrxxtxjlDao extends HibernateBaseDaoImpl<ShBmrxxtxjl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据报名编号获取报名人数
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 * @return
	 */
	public List<Map<String,Object>> getList(String pages,String bmid){
		String str="";
		if(pages!=null&&!"".equals(pages)){
			int num=(Integer.parseInt(pages)-1)*10;
			str=str+" group by a.ybmrid order by a.bt limit "+num+",10 ";
		}
//		String sql="select a.id,a.ybmrid,a.bt,a.nr,b.openid,b.bmid,b.bz,c.nickname from sh_bmrxxtxjl a "
//				+ "left join sh_wbmybmr b on a.YBMRID=b.ID "
//				+ "left join wx_user_info c on b.openid=c.openid where b.bmid="+bmid+str;
		String sql="select a.ybmrid,max(b.openid) as openid,max(b.bmid) as bmid,max(c.nickname) as nickname from sh_bmrxxtxjl a "
				+ "left join sh_wbmybmr b on a.YBMRID=b.ID "
				+ "left join wx_user_info c on b.openid=c.openid where b.bmid="+bmid+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取报名的总人数
	 * @author liujiansen
	 * @date 2015年6月26日
	 * @return
	 */
	public int getCount(String bmid){
		int rows = this.getList("",bmid).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 根据报名编号获取报名活动表单名称
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 * @return
	 */
	public List<Map<String,Object>> getBt(String bmid){
		String sql="select id,bt from sh_wbmbd where bmid="+bmid+" order by bt";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据已报名人编号删除报名信息
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param id 已报名人编号
	 */
	public void delete(String id){
		String sql="delete from sh_bmrxxtxjl where ybmrid="+id;
		jdbcTemplate.execute(sql);
	}
}
