package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.WjQuestion;

@Repository
public class WjQuetionDao extends HibernateBaseDaoImpl<WjQuestion, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据问卷的编号查找该属于该问卷的题目
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷id
	 * @return
	 */
	public List<Map<String,Object>> litQuesByOid(int oid) {
		String sql="select seq,content,qtype from wj_question where oid='"
				+ oid + "' order by seq asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取文本问题的数据
	 * @author liujiasen
	 * @date 2015年7月16日
	 * @param oid
	 * @return
	 */
	public List<Map<String,Object>> getTxtQByOid(int oid){
		String sql="SELECT a.oid, a.qseq, a.content, a.selseq, a.remark,b.* FROM wj_selecter a "
				+ "inner join wj_question b on a.oid=b.oid and a.qseq=b.seq and b.qtype='3' and oid="+oid;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 插入题目前先修改题目表的题目顺序号
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @param seq 问题编号
	 */
	public int updateQuesOrder(int oid, int seq) {
		String sql = "update wj_question set seq=(seq + 1) where oid = '" + oid
				+ "'and seq > '" + seq + "'";
		return jdbcTemplate.update(sql);
	}
	
	/**
	 * 编辑题目的时候 先的到它的题目TITLE ,选项类型,选项内容
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 * @return
	 */
	public List<Map<String,Object>> getQuesBySeq(int seq, int oid) {
		String sql = "select content,qtype from wj_question where oid = '" + oid
				+ "' and seq = '" + seq + "'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据传进来的问卷编号和试题的顺序号 删除题目
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	public void deleteQues(int seq, int oid) {
		String sql = "delete  from wj_question where oid=" + oid + " and seq = "+ seq ;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 根据传进来的问卷编号和题目的序号 修改题目表中题目的顺序
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	public void updateQseq(int seq, int oid) {
		String sql = "update wj_question set seq=(seq-1) where oid = " + oid+ " and seq > " + seq ;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 得到问题的总数
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @return
	 */
	public int getQuesCount(int oid) {
		String sql = "select * from wj_question where oid=" + oid;
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 * 新增问题
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @param content 问题标题
	 * @param qtype 问题类型
	 * @param seq 问题编号
	 */
	public void addQues(int oid, String content, int qtype, int seq) {
		String sql = "insert into wj_question(oid,content,qtype,seq) values('"
				+ oid + "','" + content + "','" + qtype + "','"
				+ seq + "')";
		jdbcTemplate.execute(sql);
	}
}
