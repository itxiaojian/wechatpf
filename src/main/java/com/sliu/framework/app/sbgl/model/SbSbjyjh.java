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
 * 设备检验计划
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbjyjh")
public class SbSbjyjh implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**SBBH*/
	private String sbbh;
	
	/**SBMC*/
	private String sbmc;
	
	/**SBXH*/
	private String sbxh;
	
	/**SYBM*/
	private String sybm;
	
	/**1、內检；2、外检*/
	private String jyfs;
	
	/**SCWXSJ*/
	private java.util.Date scwxsj;
	
	/**JG*/
	private Integer jg;
	
	/**XCWXSJ*/
	private java.util.Date xcwxsj;
	
	/**1、安全检验；2、压力检验；3、精度检验*/
	private String wxrbh;
	
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
	
	@Column(name="SBBH", length=32, nullable=true)
	public String getSbbh() {
		return sbbh;
	}
	
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	
	@Column(name="SBMC", length=32, nullable=true)
	public String getSbmc() {
		return sbmc;
	}
	
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	
	@Column(name="SBXH", length=32, nullable=true)
	public String getSbxh() {
		return sbxh;
	}
	
	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}
	
	@Column(name="SYBM", length=32, nullable=true)
	public String getSybm() {
		return sybm;
	}
	
	public void setSybm(String sybm) {
		this.sybm = sybm;
	}
	
	@Column(name="JYFS", length=2, nullable=true)
	public String getJyfs() {
		return jyfs;
	}
	
	public void setJyfs(String jyfs) {
		this.jyfs = jyfs;
	}
	
	@Column(name="SCWXSJ", length=10, nullable=true)
	public java.util.Date getScwxsj() {
		return scwxsj;
	}
	
	public void setScwxsj(java.util.Date scwxsj) {
		this.scwxsj = scwxsj;
	}
	
	@Column(name="JG", length=10, nullable=true)
	public Integer getJg() {
		return jg;
	}
	
	public void setJg(Integer jg) {
		this.jg = jg;
	}
	
	@Column(name="XCWXSJ", length=10, nullable=true)
	public java.util.Date getXcwxsj() {
		return xcwxsj;
	}
	
	public void setXcwxsj(java.util.Date xcwxsj) {
		this.xcwxsj = xcwxsj;
	}
	
	@Column(name="WXRBH", length=2, nullable=true)
	public String getWxrbh() {
		return wxrbh;
	}
	
	public void setWxrbh(String wxrbh) {
		this.wxrbh = wxrbh;
	}
	
	@Column(name="JDDW", length=32, nullable=true)
	public String getJddw() {
		return jddw;
	}

	public void setJddw(String jddw) {
		this.jddw = jddw;
	}
	
}
