package com.sliu.framework.app.wsh.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.wsh.dao.WjQuetionDao;
import com.sliu.framework.app.wsh.model.WjQuestion;


@Service
public class WjQuestionService {

	@Autowired
	private WjQuetionDao dao;
	
	/**
	 * 根据问卷的编号查找该属于该问卷的题目
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷id
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> litQuesByOid(int oid) {
		return dao.litQuesByOid(oid);
	}
	
	/**
	 * 获取文本问题的数据
	 * @author liujiasen
	 * @date 2015年7月16日
	 * @param oid
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTxtQByOid(int oid) {
		return dao.getTxtQByOid(oid);
	}
	
	/**
	 * 插入题目前先修改题目表的题目顺序号
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @param seq 问题编号
	 */
	@Transactional
	public int updateQuesOrder(int oid, int seq) {
		 return dao.updateQuesOrder(oid, seq);
	}
	
	/**
	 * 编辑题目的时候 先的到它的题目TITLE ,选项类型,选项内容
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getQuesBySeq(int seq, int oid) {
		return dao.getQuesBySeq(seq, oid);
	}
	
	/**
	 * 根据传进来的问卷编号和试题的顺序号 删除题目
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	@Transactional
	public int deleteQues(int seq, int oid) {
		dao.deleteQues(seq, oid);
		return 1;
	}
	
	/**
	 * 根据传进来的问卷编号和题目的序号 修改题目表中题目的顺序
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	@Transactional
	public int updateQseq(int seq, int oid) {
		dao.updateQseq(seq, oid);
		return 1;
	}
	
	/**
	 * 得到问题的总数
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @return
	 */
	@Transactional
	public int getQuesCount(int oid) {
		return dao.getQuesCount(oid);
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
	@Transactional
	public int addQues(int oid, String content, int qtype, int seq) {
		WjQuestion qes=new WjQuestion();
		qes.setContent(content);
		qes.setOid(oid);
		qes.setQtype(qtype);
		qes.setRemark("");
		qes.setSeq(seq);
		dao.save(qes);
		return 1;
	}
}
