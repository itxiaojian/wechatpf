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
 * 设备信息
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbxx")
public class SbSbxx implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**SBLB*/
	private String sblb;
	
	/**SBMC*/
	private String sbmc;
	
	/**SBBH*/
	private String sbbh;
	
	/**SBXH*/
	private String sbxh;
	
	/**SBJB*/
	private String sbjb;
	
	/**SYBMBH*/
	private String sybmbh;
	
	/**SYBM*/
	private String sybm;
	
	/**SYFW*/
	private String syfw;
	
	/**SCCJ*/
	private String sccj;
	
	/**CCBH*/
	private String ccbh;
	
	/**CCRQ*/
	private java.util.Date ccrq;
	
	/**GMRQ*/
	private java.util.Date gmrq;
	
	/**GMJG*/
	private String gmjg;
	
	/**JDZQ*/
	private String jdzq;
	
	/**SYQX*/
	private String syqx;
	
	/**SCJDRQ*/
	private java.util.Date scjdrq;
	
	/**XCJDRQ*/
	private java.util.Date xcjdrq;
	
	/**SYZT*/
	private String syzt;
	
	/**SBWHR*/
	private String sbwhr;
	
	/**ZJFF*/
	private String zjff;
	
	/**ZCYZ*/
	private BigDecimal zcyz;
	
	/**JCL*/
	private BigDecimal jcl;
	
	/**ZJNX*/
	private Integer zjnx;
	
	/** 资产净值*/
	private BigDecimal zcjz;
	
	/** 检定单位*/
	private String jddw;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="SBLB", length=32, nullable=true)
	public String getSblb() {
		return sblb;
	}
	
	public void setSblb(String sblb) {
		this.sblb = sblb;
	}
	
	@Column(name="SBMC", length=32, nullable=true)
	public String getSbmc() {
		return sbmc;
	}
	
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	
	@Column(name="SBBH", length=32, nullable=true)
	public String getSbbh() {
		return sbbh;
	}
	
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	
	@Column(name="SBXH", length=32, nullable=true)
	public String getSbxh() {
		return sbxh;
	}
	
	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}
	
	@Column(name="SBJB", length=32, nullable=true)
	public String getSbjb() {
		return sbjb;
	}
	
	public void setSbjb(String sbjb) {
		this.sbjb = sbjb;
	}
	
	@Column(name="SYBMBH", length=32, nullable=true)
	public String getSybmbh() {
		return sybmbh;
	}
	
	public void setSybmbh(String sybmbh) {
		this.sybmbh = sybmbh;
	}
	
	@Column(name="SYBM", length=32, nullable=true)
	public String getSybm() {
		return sybm;
	}
	
	public void setSybm(String sybm) {
		this.sybm = sybm;
	}
	
	@Column(name="SYFW", length=256, nullable=true)
	public String getSyfw() {
		return syfw;
	}
	
	public void setSyfw(String syfw) {
		this.syfw = syfw;
	}
	
	@Column(name="SCCJ", length=256, nullable=true)
	public String getSccj() {
		return sccj;
	}
	
	public void setSccj(String sccj) {
		this.sccj = sccj;
	}
	
	@Column(name="CCBH", length=32, nullable=true)
	public String getCcbh() {
		return ccbh;
	}
	
	public void setCcbh(String ccbh) {
		this.ccbh = ccbh;
	}
	
	@Column(name="CCRQ", length=19, nullable=true)
	public java.util.Date getCcrq() {
		return ccrq;
	}
	
	public void setCcrq(java.util.Date ccrq) {
		this.ccrq = ccrq;
	}
	
	@Column(name="GMRQ", length=19, nullable=true)
	public java.util.Date getGmrq() {
		return gmrq;
	}
	
	public void setGmrq(java.util.Date gmrq) {
		this.gmrq = gmrq;
	}
	
	@Column(name="GMJG", length=32, nullable=true)
	public String getGmjg() {
		return gmjg;
	}
	
	public void setGmjg(String gmjg) {
		this.gmjg = gmjg;
	}
	
	@Column(name="JDZQ", length=32, nullable=true)
	public String getJdzq() {
		return jdzq;
	}
	
	public void setJdzq(String jdzq) {
		this.jdzq = jdzq;
	}
	
	@Column(name="SYQX", length=32, nullable=true)
	public String getSyqx() {
		return syqx;
	}
	
	public void setSyqx(String syqx) {
		this.syqx = syqx;
	}
	
	@Column(name="SCJDRQ", length=19, nullable=true)
	public java.util.Date getScjdrq() {
		return scjdrq;
	}
	
	public void setScjdrq(java.util.Date scjdrq) {
		this.scjdrq = scjdrq;
	}
	
	@Column(name="XCJDRQ", length=19, nullable=true)
	public java.util.Date getXcjdrq() {
		return xcjdrq;
	}
	
	public void setXcjdrq(java.util.Date xcjdrq) {
		this.xcjdrq = xcjdrq;
	}
	
	@Column(name="SYZT", length=32, nullable=true)
	public String getSyzt() {
		return syzt;
	}
	
	public void setSyzt(String syzt) {
		this.syzt = syzt;
	}
	
	@Column(name="SBWHR", length=32, nullable=true)
	public String getSbwhr() {
		return sbwhr;
	}
	
	public void setSbwhr(String sbwhr) {
		this.sbwhr = sbwhr;
	}
	
	@Column(name="ZJFF", length=32, nullable=true)
	public String getZjff() {
		return zjff;
	}
	
	public void setZjff(String zjff) {
		this.zjff = zjff;
	}
	
	@Column(name="ZCYZ", length=10, nullable=true)
	public BigDecimal getZcyz() {
		return zcyz;
	}
	
	public void setZcyz(BigDecimal zcyz) {
		this.zcyz = zcyz;
	}
	
	@Column(name="JCL", length=10, nullable=true)
	public BigDecimal getJcl() {
		return jcl;
	}
	
	public void setJcl(BigDecimal jcl) {
		this.jcl = jcl;
	}
	
	@Column(name="ZJNX", length=10, nullable=true)
	public Integer getZjnx() {
		return zjnx;
	}
	
	public void setZjnx(Integer zjnx) {
		this.zjnx = zjnx;
	}

	@Column(name="ZCJZ", length=10, nullable=true)
	public BigDecimal getZcjz() {
		return zcjz;
	}

	public void setZcjz(BigDecimal zcjz) {
		this.zcjz = zcjz;
	}

	@Column(name="JDDW", length=32, nullable=true)
	public String getJddw() {
		return jddw;
	}

	public void setJddw(String jddw) {
		this.jddw = jddw;
	}
	
}
