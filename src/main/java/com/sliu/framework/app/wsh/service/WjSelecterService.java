package com.sliu.framework.app.wsh.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.wsh.dao.WjSelecterDao;


@Service
public class WjSelecterService {

	@Autowired
	private WjSelecterDao dao;
	
	/**
	 * 根据题目顺序查找选项的值 返回一个结果集
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 * @return
	 */
//	public List<Map<String,Object>> listSelecterBySeq(int seq, int oid) {
//		return dao.listSelecterBySeq(seq, oid);
//	}
	
	public List<Map<String,Object>> listSelecterBySeq(String seq, int oid) {
		return dao.listSelecterBySeq(seq, oid);
	}
	
	/**
	 * 根据问卷顺序查找选项的值 返回一个结果集
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @return
	 */
	public List<Map<String,Object>> listSelecterByOid(int oid) {
		return dao.listSelecterByOid(oid);
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
		dao.addSelecter(oid, seq, content, seq_selecter);
	}
	
	/**
	 * 往选项表插入内容的时候先修改选项表中所属的题目的顺序号
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @param qseq 问题编号
	 */
	public int updateSelecterSeq(int oid, int qseq) {
		return dao.updateSelecterSeq(oid, qseq);
	}
	
	/**
	 * 传进来问卷编号和试题的顺序号 删除该题所对应的选项表中的数据
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	public int deleteSelecter(int seq, int oid) {
		dao.deleteSelecter(seq, oid);
		return 1;
	}
	
	/**
	 * 根据传进来的问卷编号和题目的序号 修改选项表中题目的顺序
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param seq 问题编号
	 * @param oid 问卷编号
	 */
	public void updateSseq(int seq, int oid) {
		dao.updateSseq(seq, oid);
	}
}
