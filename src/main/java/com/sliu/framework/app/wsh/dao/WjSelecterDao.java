package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.WjSelecter;

@Repository
public class WjSelecterDao extends HibernateBaseDaoImpl<WjSelecter, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 根据题目顺序查找选项的值 返回一个结果集
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 * @return
	 */
	public List<Map<String,Object>> listSelecterBySeq(int seq, int oid) {
		String sql="select qseq,selseq,content from wj_selecter where qseq = '"
				+ seq + "' and oid = '" + oid + "' order by selseq asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String,Object>> listSelecterBySeq(String seq, int oid) {
		String sql="select qseq,selseq,content from wj_selecter where qseq = '"
				+ seq + "' and oid = '" + oid + "' order by selseq asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据问卷顺序查找选项的值 返回一个结果集
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @return
	 */
	public List<Map<String,Object>> listSelecterByOid(int oid) {
		String sql="select qseq,selseq,content from wj_selecter where oid = '" + oid + "' order by selseq asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 插入选项
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号 
	 * @param seq 问题编号
	 * @param content 选项内容
	 * @param seq_selecter 选项编号
	 */
	public void addSelecter(int oid, int seq, String content, int seq_selecter) {
		String sql = "insert into wj_selecter(oid,qseq,content,selseq) values('"
				+ oid + "','" + seq + "','" + content + "','" + seq_selecter + "')";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 往选项表插入内容的时候先修改选项表中所属的题目的顺序号
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @param qseq 问题编号
	 */
	public int updateSelecterSeq(int oid, int qseq) {
		String sql = "update wj_selecter set qseq=(qseq+1) where oid = '" + oid
				+ "'and qseq > '" + qseq + "'";
		return jdbcTemplate.update(sql);
	}
	
	/**
	 * 传进来问卷编号和试题的顺序号 删除该题所对应的选项表中的数据
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	public void deleteSelecter(int seq, int oid) {
		String sql = "delete from wj_selecter where oid=" + oid + " and qseq=" + seq ;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 根据传进来的问卷编号和题目的序号 修改选项表中题目的顺序
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	public void updateSseq(int seq, int oid) {
		String sql = "update wj_selecter set qseq=(qseq-1) where oid = " + oid
				+ " and qseq > " + seq ;
		jdbcTemplate.execute(sql);
	}
}
