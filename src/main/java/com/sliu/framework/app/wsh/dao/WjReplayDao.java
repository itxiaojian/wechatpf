package com.sliu.framework.app.wsh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.WjAnswer;
import com.sliu.framework.app.wsh.model.WjReplay;

@Repository
public class WjReplayDao extends HibernateBaseDaoImpl<WjReplay, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取所有问题回答情况
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @return
	 */
	public Map<Integer,List<Map<Integer,Integer>>> getAllAnswer(int oid) {
		int qCount = 0;
		int sCount = 0;
		int qaCount = 0;
		int saCount = 0;
		Map<Integer,List<Map<Integer,Integer>>> map = new HashMap<Integer,List<Map<Integer,Integer>>>();
		qCount = getQuesCount(oid);
		for(int i=1;i<=qCount;i++)
		{
			List<Map<Integer,Integer>> list = new ArrayList<Map<Integer,Integer>>();
			qaCount = getAnswerCount(oid,i);
			sCount = getSelCount(oid,i);
			for(int j=0;j<=sCount;j++)
			{
				Map<Integer,Integer> m = new HashMap<Integer,Integer>();
				if(j==0){
					m.put(0, qaCount);
				}else{
					saCount = getAnswerCount(oid,i,j);
					m.put(j, saCount);
				}
				list.add(m);
			}
			map.put(i, list);
		}
		return map;
	}
	
	public List<Map<String,Object>> getAnswers(String oid,String qSeq)  {
		String sql = "select * from wj_answer where oid='"+ oid + "' and qSeq="+qSeq+" order by answerId asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 得到问题总数
	 * @param con
	 * @param oid
	 * @return
	 */
	public int getQuesCount(int oid) {
		String sql = "select oid from wj_question where oid=" + oid;
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 * 得到选项总数
	 * @param con
	 * @param oid
	 * @return
	 */
	public int getSelCount(int oid, int qSeq) {
		String sql = "select oid from wj_selecter where oid=" + oid +" and qseq ="+qSeq;
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 * 根据主题ID查询回复总数
	 * @param oid 主题ID
	 * @return
	 */
	public int getAnswerCount(int oid) {
		String sql = "select oid from wj_replay where oid =" + oid;
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 * 根据主题Id和题目序号查询该问题的回答数
	 * @param con 数据库连接
	 * @param oid 主题Id
	 * @param qSeq 题目序号
	 * @return 回答数
	 */
	public int getAnswerCount(int oid,int qSeq) {
		String sql = "select oid from wj_answer where oid =" + oid +" and qSeq="+qSeq;
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 * 根据主题Id和题目序号和选项序号查询该问题选项的回答数
	 * @param con 数据库连接
	 * @param oid 主题Id
	 * @param qSeq 题目序号
	 * @param seSeq 选项序号
	 * @return 问题选项的回答数
	 */
	public int getAnswerCount(int oid,int qSeq,int seSeq) {
		String sql = "select oid from wj_answer where oid =" + oid +" and qSeq="+qSeq +" and seSeq="+seSeq;
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 * 将回复信息存储到数据库
	 * @author liujiansen
	 * @date 2015年6月10日
	 * @param replayId
	 * @param ans
	 */
	public void saveAns(String replayId,WjAnswer ans){
		String sql = "insert into wj_answer(replayId,oid,qSeq,seSeq,seValue,remark) values ("
				+ replayId+","+ans.getOid()+","+ans.getQseq()+","+ans.getSeseq()+",'"+ans.getSevalue()+"','"+ans.getRemark()+"')";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 将回复信息存储到数据库
	 * @author liujiansen
	 * @date 2015年6月10日
	 * @param r
	 */
	public void saveRep(WjReplay r){
		String sql = "insert into wj_replay(replayCode,replayIp,oid,replayTime,remark) values ('"
				+ r.getReplaycode()+"','"+r.getReplayip()+"',"+r.getOid()+",'"+r.getReplaytime()+"','"+r.getRemark()+"')";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取回复编号
	 * @author liujiansen
	 * @date 2015年6月10日
	 * @param oid
	 * @param replayIp
	 * @param replayCode
	 * @return
	 */
	public List<Map<String,Object>> getRepId(int oid,String replayIp,String replayCode){
		String sql = "select replayid from wj_replay where oid="+oid+" and replayIp='"
				+ replayIp+"' and replayCode='"+replayCode+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	// 删除问卷回复情况
	public void delRep(int oid){
		String sql = "delete from wj_replay where oid="+oid;
		jdbcTemplate.execute(sql);
	}
	// 删除问卷回复情况
	public void delAns(int oid){
		String sql = "delete from wj_answer where oid="+oid;
		jdbcTemplate.execute(sql);
	}
	
	public void updateObj(int oid){
		String sql = "update wj_object set state ='0' where oid='"+oid+"'";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 判断是否存在回复
	 * @param oid
	 * @param code
	 * @return
	 */
	public boolean exit(int oid,String replayIp,String replayCode) {
		String sql = "";
		boolean falg = false;
		int qcount = 0;
		sql = "select * from wj_replay r where oid="+oid;
		if(replayIp!=null&&!replayIp.trim().equals("")) sql +=" and r.replayIp='"+replayIp+"'";
		if(replayCode!=null&&!replayCode.trim().equals("")) sql +=" and r.replayCode='"+replayCode+"'";
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		qcount=list.size();
		if(qcount>0){
			falg=true;
		}
		return falg;
	}
	
	/**
	 * 根据openId获取微信关注用户
	 * @author liujiasen
	 * @date 2015年7月24日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getUser(String openId){
		String sql="select openid,nickname from wx_user_info where openid='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
}
