package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;



/**
 * wj_object
 * @author liujiansen
 * @since  2015-06-04
 */
@Entity
@Table(name="wj_object")
public class WjObject implements Serializable{
	
	/**oid*/
	private Integer oid;
	
	/**title*/
	private String title;
	
	/**discribe*/
	private String discribe;
	
	/**createtime*/
	private java.util.Date createtime;
	
	/**state*/
	private Integer state;
	
	/**remark*/
	private String remark;
	
	/**是否匿名投递*/
	private String anonymousflag;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="oid", length=10, nullable=true)
	public Integer getOid() {
		return oid;
	}
	
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	@Column(name="title", length=1000, nullable=true)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="discribe", length=1000, nullable=true)
	public String getDiscribe() {
		return discribe;
	}
	
	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}
	
	@Column(name="createtime", length=19, nullable=true)
	public java.util.Date getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	@Column(name="state", length=10, nullable=true)
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name="remark", length=200, nullable=true)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="anonymousFlag", length=1, nullable=true)
	public String getAnonymousflag() {
		return anonymousflag;
	}
	
	public void setAnonymousflag(String anonymousflag) {
		this.anonymousflag = anonymousflag;
	}
	
}
