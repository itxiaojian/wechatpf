package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * wj_selecter
 * @author liujiansen
 * @since  2015-06-04
 */
@Entity
@Table(name="wj_selecter")
public class WjSelecter implements Serializable{
	
	/**回复主题Id*/
	private Integer oid;
	
	/**问题序号*/
	private Integer qseq;
	
	/**内容*/
	private String content;
	
	/**选项编号*/
	private Integer selseq;
	
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
	@Column(name="qseq", length=10, nullable=false)
	public Integer getQseq() {
		return qseq;
	}
	
	public void setQseq(Integer qseq) {
		this.qseq = qseq;
	}
	
	@Column(name="content", length=1000, nullable=false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Id
	@Column(name="selseq", length=10, nullable=false)
	public Integer getSelseq() {
		return selseq;
	}

	public void setSelseq(Integer selseq) {
		this.selseq = selseq;
	}

	@Column(name="remark", length=200, nullable=true)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
