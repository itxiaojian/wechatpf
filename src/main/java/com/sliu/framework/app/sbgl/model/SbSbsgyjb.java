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
 * 设备申购意见表
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbsgyj")
public class SbSbsgyjb implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**SGID*/
	private Long sgid;
	
	/**SPRBH*/
	private String sprbh;
	
	/**SPRXM*/
	private String sprxm;
	
	/**SFTG*/
	private String sftg;
	
	/**SPYJ*/
	private String spyj;
	
	/**SPSJ*/
	private java.util.Date spsj;
	
	/**1、部门审核；2、领导审核；3、财务审核*/
	private String sphj;
	
	/**SPZT*/
	private String spzt;

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
	
	@Column(name="SGID", length=19, nullable=true)
	public Long getSgid() {
		return sgid;
	}
	
	public void setSgid(Long sgid) {
		this.sgid = sgid;
	}
	
	@Column(name="SPRBH", length=32, nullable=true)
	public String getSprbh() {
		return sprbh;
	}
	
	public void setSprbh(String sprbh) {
		this.sprbh = sprbh;
	}
	
	@Column(name="SPRXM", length=32, nullable=true)
	public String getSprxm() {
		return sprxm;
	}
	
	public void setSprxm(String sprxm) {
		this.sprxm = sprxm;
	}
	
	@Column(name="SFTG", length=2, nullable=true)
	public String getSftg() {
		return sftg;
	}
	
	public void setSftg(String sftg) {
		this.sftg = sftg;
	}
	
	@Column(name="SPYJ", length=516, nullable=true)
	public String getSpyj() {
		return spyj;
	}
	
	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}
	
	@Column(name="SPSJ", length=19, nullable=true)
	public java.util.Date getSpsj() {
		return spsj;
	}
	
	public void setSpsj(java.util.Date spsj) {
		this.spsj = spsj;
	}
	
	@Column(name="SPHJ", length=2, nullable=true)
	public String getSphj() {
		return sphj;
	}
	
	public void setSphj(String sphj) {
		this.sphj = sphj;
	}
	
	@Column(name="SPZT", length=2, nullable=true)
	public String getSpzt() {
		return spzt;
	}

	public void setSpzt(String spzt) {
		this.spzt = spzt;
	}
	
	@Column(name="BZ", length=516, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
