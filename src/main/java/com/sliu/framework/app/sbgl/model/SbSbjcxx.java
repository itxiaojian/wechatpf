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
 * 设备借出信息
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbjcxx")
public class SbSbjcxx implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**SBBH*/
	private String sbbh;
	
	/**SBMC*/
	private String sbmc;
	
	/**JCRBH*/
	private String jcrbh;
	
	/**JCRXM*/
	private String jcrxm;
	
	/**JCDWBH*/
	private String jcdwbh;
	
	/**JCDWXM*/
	private String jcdwxm;
	
	/**FZRBH*/
	private String fzrbh;
	
	/**FZRXM*/
	private String fzrxm;
	
	/**JCSJ*/
	private java.util.Date jcsj;
	
	/**GHSJ*/
	private java.util.Date ghsj;
	
	/**1、借出；2、归还*/
	private String jczt;
	
	/**BZ*/
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
	
	@Column(name="JCRBH", length=32, nullable=true)
	public String getJcrbh() {
		return jcrbh;
	}
	
	public void setJcrbh(String jcrbh) {
		this.jcrbh = jcrbh;
	}
	
	@Column(name="JCRXM", length=32, nullable=true)
	public String getJcrxm() {
		return jcrxm;
	}
	
	public void setJcrxm(String jcrxm) {
		this.jcrxm = jcrxm;
	}
	
	@Column(name="JCDWBH", length=32, nullable=true)
	public String getJcdwbh() {
		return jcdwbh;
	}
	
	public void setJcdwbh(String jcdwbh) {
		this.jcdwbh = jcdwbh;
	}
	
	@Column(name="JCDWXM", length=32, nullable=true)
	public String getJcdwxm() {
		return jcdwxm;
	}
	
	public void setJcdwxm(String jcdwxm) {
		this.jcdwxm = jcdwxm;
	}
	
	@Column(name="FZRBH", length=32, nullable=true)
	public String getFzrbh() {
		return fzrbh;
	}
	
	public void setFzrbh(String fzrbh) {
		this.fzrbh = fzrbh;
	}
	
	@Column(name="FZRXM", length=32, nullable=true)
	public String getFzrxm() {
		return fzrxm;
	}
	
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}
	
	@Column(name="JCSJ", length=19, nullable=true)
	public java.util.Date getJcsj() {
		return jcsj;
	}
	
	public void setJcsj(java.util.Date jcsj) {
		this.jcsj = jcsj;
	}
	
	@Column(name="GHSJ", length=19, nullable=true)
	public java.util.Date getGhsj() {
		return ghsj;
	}
	
	public void setGhsj(java.util.Date ghsj) {
		this.ghsj = ghsj;
	}
	
	@Column(name="JCZT", length=2, nullable=true)
	public String getJczt() {
		return jczt;
	}
	
	public void setJczt(String jczt) {
		this.jczt = jczt;
	}
	
	@Column(name="BZ", length=516, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
