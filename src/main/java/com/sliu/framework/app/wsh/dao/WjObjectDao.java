package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.WjObject;

@Repository
public class WjObjectDao extends HibernateBaseDaoImpl<WjObject, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 查看问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @return
	 */
	public List<Map<String,Object>> ListObjectBean(String bt){
		String str="";
		if(bt!=null&&!"".equals(bt)){
			str=str+" and title like '%"+bt+"%' ";
		}
		String sql="select oid,title,DATE_FORMAT(createtime,'%Y-%m-%d %H:%i') as createtime,state from wj_object where 1=1 "+str+" order by oid desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查找发布后的问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findPublishedObjectBeanByID(String id){
		String sql="select oid,title,discribe,state,anonymousFlag from wj_object where oid='" + id
				+ "' and state in(1,2)";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据编号查找标题和内容
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findObjectBeanByID(String id){
		String sql="select oid,title,discribe,state,anonymousFlag,DATE_FORMAT(createtime,'%Y-%m-%d %H:%i') as createtime from wj_object where oid='" + id
				+ "'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据编号查找详细信息
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getObjectBean(String id){
		String sql="select * from wj_object where oid='" + id
				+ "'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查找问卷一共几条数据
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid 问卷id
	 * @return
	 */
	public int getCount(int oid) {
		String sql="select * from wj_question where oid = '" + oid	+ "'";
		return jdbcTemplate.queryForList(sql).size();
	}
	
	/**
	 * 删除问卷数据
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid
	 */
	public void delObj(int oid){
		String sql="delete from wj_object where oid="+oid;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 删除问题数据
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid
	 */
	public void delQue(int oid){
		String sql="delete from wj_question where oid="+oid;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 删除答题人数据
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid
	 */
	public void delRep(int oid){
		String sql="delete from wj_replay where oid="+oid;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 删除提交答案数据
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid
	 */
	public void delAns(int oid){
		String sql="delete from wj_answer where oid="+oid;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 删除问题选项数据
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid
	 */
	public void delSel(int oid){
		String sql="delete from wj_selecter where oid="+oid;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 发布
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id
	 */
	public void objFb(String id) {
		String sql = "update wj_object set state ='1' where oid='"+id+"'";
		jdbcTemplate.execute(sql);
	}

	/**
	 * 撤销
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id
	 */
	public void objCx(String id) {
		String sql = "update wj_object set state ='0' where oid='"+id+"'";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 终止评议
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id
	 */
	public void objZz(String id) {
		String sql = "update wj_object set state ='2' where oid='"+id+"'";
		jdbcTemplate.execute(sql);
	}

	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月22日
	 * @param oid
	 * @param qseq
	 * @return
	 */
	public List<Map<String, Object>> getWjXz(String oid, String qseq) {
		String sql="select a.oid,a.qseq,a.content,a.selseq,a.remark,"
				+ "(select count(1) from wj_answer b where a.oid=b.oid and a.qseq=b.qseq and a.selseq=b.seSeq) as xzs "
				+ "from wj_selecter a where a.oid="+oid+" and a.qseq="+qseq;
		return jdbcTemplate.queryForList(sql);
	}
}
