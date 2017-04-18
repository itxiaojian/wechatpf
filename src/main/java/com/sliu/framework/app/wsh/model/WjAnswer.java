package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * wj_answer
 * @author liujiansen
 * @since  2015-06-04
 */
@Entity
@Table(name="wj_answer")
public class WjAnswer implements Serializable{
	
	/**答案Id*/
	private Integer answerid;
	
	/**回答者Id*/
	private Integer replayid;
	
	/**回复主题Id*/
	private Integer oid;
	
	/**问题序号*/
	private Integer qseq;
	
	/**选项序号*/
	private Integer seseq;
	
	/**选项内容*/
	private String sevalue;
	
	/**备注*/
	private String remark;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="answerId", length=10, nullable=true)
	public Integer getAnswerid() {
		return answerid;
	}
	
	public void setAnswerid(Integer answerid) {
		this.answerid = answerid;
	}
	
	@Column(name="replayId", length=10, nullable=true)
	public Integer getReplayid() {
		return replayid;
	}
	
	public void setReplayid(Integer replayid) {
		this.replayid = replayid;
	}
	
	@Column(name="oid", length=10, nullable=true)
	public Integer getOid() {
		return oid;
	}
	
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	@Column(name="qSeq", length=10, nullable=false)
	public Integer getQseq() {
		return qseq;
	}
	
	public void setQseq(Integer qseq) {
		this.qseq = qseq;
	}
	
	@Column(name="seSeq", length=10, nullable=false)
	public Integer getSeseq() {
		return seseq;
	}
	
	public void setSeseq(Integer seseq) {
		this.seseq = seseq;
	}
	
	@Column(name="seValue", length=1000, nullable=false)
	public String getSevalue() {
		return sevalue;
	}
	
	public void setSevalue(String sevalue) {
		this.sevalue = sevalue;
	}
	
	@Column(name="remark", length=200, nullable=false)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
