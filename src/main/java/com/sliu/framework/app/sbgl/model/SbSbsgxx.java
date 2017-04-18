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
 * 设备申购信息
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbsgxx")
public class SbSbsgxx implements Serializable{
	
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
	
	/**SGR*/
	private String sgr;
	
	/**SGRQ*/
	private java.util.Date sgrq;
	
	/**1、申请中；2、部门审核中；3、领导审核；4、财务审核；5、审核成功入库*/
	private String sgzt;
	
	/**CGSL*/
	private String cgsl;
	
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
	
	@Column(name="SGR", length=32, nullable=true)
	public String getSgr() {
		return sgr;
	}
	
	public void setSgr(String sgr) {
		this.sgr = sgr;
	}
	
	@Column(name="SGRQ", length=19, nullable=true)
	public java.util.Date getSgrq() {
		return sgrq;
	}
	
	public void setSgrq(java.util.Date sgrq) {
		this.sgrq = sgrq;
	}
	
	@Column(name="SGZT", length=2, nullable=true)
	public String getSgzt() {
		return sgzt;
	}
	
	public void setSgzt(String sgzt) {
		this.sgzt = sgzt;
	}
	
	@Column(name="CGSL", length=13, nullable=true)
	public String getCgsl() {
		return cgsl;
	}
	
	public void setCgsl(String cgsl) {
		this.cgsl = cgsl;
	}
	
}
