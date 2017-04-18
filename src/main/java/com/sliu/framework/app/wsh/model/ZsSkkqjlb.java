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
 * 上课考勤记录表
 * @author liujiansen
 * @since  2016-03-28
 */
@Entity
@Table(name="zs_skkqjlb")
public class ZsSkkqjlb implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**考勤编号*/
	private Long kqid;
	
	/**学号*/
	private String xh;
	
	/**姓名*/
	private String xm;
	
	/**签到时间*/
	private java.util.Date qdsj;
	
	/**备注*/
	private String bz;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="KQID", length=19, nullable=true)
	public Long getKqid() {
		return kqid;
	}
	
	public void setKqid(Long kqid) {
		this.kqid = kqid;
	}
	
	@Column(name="XH", length=100, nullable=true)
	public String getXh() {
		return xh;
	}
	
	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Column(name="XM", length=100, nullable=true)
	public String getXm() {
		return xm;
	}
	
	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Column(name="QDSJ", length=19, nullable=true)
	public java.util.Date getQdsj() {
		return qdsj;
	}
	
	public void setQdsj(java.util.Date qdsj) {
		this.qdsj = qdsj;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
