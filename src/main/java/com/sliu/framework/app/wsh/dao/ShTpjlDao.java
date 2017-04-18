package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ShTpjl;

/**
 * 投票记录
 * @author zhangyan
 * @version 创建时间：2016年7月27日  
 */
@Repository
public class ShTpjlDao extends HibernateBaseDaoImpl<ShTpjl, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 判断是否存在回复
	 * @param oid
	 * @param code
	 * @return
	 */
	public boolean exit(String oid,String replayIp) {
		String sql = "";
		boolean falg = false;
		int qcount = 0;
		sql = "select * from wsh_tpjl a where a.xxid="+oid;
		if(replayIp!=null&&!replayIp.trim().equals("")) sql +=" and a.tpr='"+replayIp+"'";
//		if(replayCode!=null&&!replayCode.trim().equals("")) sql +=" and r.replayCode='"+replayCode+"'";
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		qcount=list.size();
		if(qcount>0){
			falg=true;
		}
		return falg;
	}
	
	/**
	 * 将回复信息存储到数据库
	 * @author zhangyan
	 * @date 2016年7月27日
	 * @param r
	 */
	public void saveRep(ShTpjl r){
		String sql = "insert into wsh_tpjl(tpr,tpsj,xxid) values ('"
				+ r.getTpr()+"','"+r.getTpsj()+"','"+r.getXxid()+"')";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取投票编号
	 * @author zhangyan
	 * @date 2016年7月27日
	 * @param tpr
	 * @param xxid
	 * @return
	 */
	public List<Map<String,Object>> getRepId(String tpr,Long xxid){
		String sql = "select id from wsh_tpjl where xxid="+xxid+" and tpr='"+tpr+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 将回复信息存储到数据库
	 * @author zhangyan
	 * @date 2015年6月10日
	 * @param replayId
	 * @param ans
	 */
	public void saveAns(String replayId,ShTpjl ans){
		String sql = "insert into wj_answer(tpr,tpsj,xxid) values ("
				+ replayId+","+ans.getTpsj()+"','"+ans.getXxid()+"')";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 根据选项id查找投票记录
	 * @author zhangyan
	 * @date 2016年7月27日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getTpjl(String xxid){
		String sql="select count(id) as count from wsh_tpjl where xxid='" + xxid + "'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查看选项内容
	 * @author zhangyan
	 * @date 2016年7月4日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getJllist(String id,String openId) {
		String sql="select a.tpr,b.xxnr from wsh_tpjl a LEFT JOIN wsh_tpzlxx b on a.xxid=b.id where b.tpid = '" + id	+ "' and a.tpr='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 求投票总数
	 * @author zhangyan
	 * @date 2016年7月4日
	 * @param id
	 * @return
	 */
	public int getSum(String id){
		String sql="select count(a.xxid)as sum from wsh_tpjl a left join wsh_tpzlxx b on a.XXID = b.ID where b.TPID = '"+id+"'";
//		return jdbcTemplate.queryForList(sql).get(0).get("sum").toString();
		return jdbcTemplate.queryForObject(sql,Integer.class);

	}
	
	/**
	 * 后台删除投票是删除对应的投票记录
	 * @author zhangyan
	 * @date 2016年7月4日
	 * @param id
	 * @return
	 */
	public void deletejl(Long id){
		String sql="delete a.* from wsh_tpjl a left join wsh_tpzlxx b on a.XXID = b.ID  where b.tpid = '"+id+"'";
		jdbcTemplate.execute(sql);

	}
	
}
