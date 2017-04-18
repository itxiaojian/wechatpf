package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * wj_question
 * @author liujiansen
 * @since  2015-06-04
 */
@Entity
@Table(name="wj_question")
public class WjQuestion implements Serializable{
	
	/**回复主题Id*/
	private Integer oid;
	
	/**问题序号*/
	private Integer seq;
	
	/**标题内容*/
	private String content;
	
	/**问题类别*/
	private Integer qtype;
	
	/**备注*/
	private String remark;
	
	@Id
	@Column(name="oid", length=10, nullable=false)
	public Integer getOid() {
		return oid;
	}
	
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	@Id
	@Column(name="seq", length=10, nullable=false)
	public Integer getSeq() {
		return seq;
	}
	
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Column(name="content", length=1000, nullable=false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="qtype", length=10, nullable=false)
	public Integer getQtype() {
		return qtype;
	}

	public void setQtype(Integer qtype) {
		this.qtype = qtype;
	}

	@Column(name="remark", length=200, nullable=true)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
