package com.sliu.framework.app.sbgl.model;

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
 * 设备保养计划
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbbyjh")
public class SbSbbyjh implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**SBBH*/
	private String sbbh;
	
	/**BYJB*/
	private String byjb;
	
	/**1、多次循环；2、单次*/
	private String xhfs;
	
	/**SCBYSJ*/
	private java.util.Date scbysj;
	
	/**JG*/
	private Integer jg;
	
	/**XCBYSJ*/
	private java.util.Date xcbysj;
	
	/**BYRBH*/
	private String byrbh;
	
	/**BYRXM*/
	private String byrxm;
	
	/**1、有效；2、已结束*/
	private String zt;
	
	/**GZMS*/
	private String gzms;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="SBBH", length=32, nullable=true)
	public String getSbbh() {
		return sbbh;
	}
	
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	
	@Column(name="BYJB", length=32, nullable=true)
	public String getByjb() {
		return byjb;
	}
	
	public void setByjb(String byjb) {
		this.byjb = byjb;
	}
	
	@Column(name="XHFS", length=2, nullable=true)
	public String getXhfs() {
		return xhfs;
	}
	
	public void setXhfs(String xhfs) {
		this.xhfs = xhfs;
	}
	
	@Column(name="SCBYSJ", length=10, nullable=true)
	public java.util.Date getScbysj() {
		return scbysj;
	}
	
	public void setScbysj(java.util.Date scbysj) {
		this.scbysj = scbysj;
	}
	
	@Column(name="JG", length=10, nullable=true)
	public Integer getJg() {
		return jg;
	}
	
	public void setJg(Integer jg) {
		this.jg = jg;
	}
	
	@Column(name="XCBYSJ", length=10, nullable=true)
	public java.util.Date getXcbysj() {
		return xcbysj;
	}
	
	public void setXcbysj(java.util.Date xcbysj) {
		this.xcbysj = xcbysj;
	}
	
	@Column(name="BYRBH", length=32, nullable=true)
	public String getByrbh() {
		return byrbh;
	}
	
	public void setByrbh(String byrbh) {
		this.byrbh = byrbh;
	}
	
	@Column(name="BYRXM", length=32, nullable=true)
	public String getByrxm() {
		return byrxm;
	}
	
	public void setByrxm(String byrxm) {
		this.byrxm = byrxm;
	}
	
	@Column(name="ZT", length=2, nullable=true)
	public String getZt() {
		return zt;
	}
	
	public void setZt(String zt) {
		this.zt = zt;
	}
	
	@Column(name="GZMS", length=512, nullable=true)
	public String getGzms() {
		return gzms;
	}
	
	public void setGzms(String gzms) {
		this.gzms = gzms;
	}
	
}
