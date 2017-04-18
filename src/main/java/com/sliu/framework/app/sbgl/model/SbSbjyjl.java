package com.sliu.framework.app.sbgl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;


/**
 * 设备检验记录
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbjyjl")
public class SbSbjyjl implements Serializable{
	
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
	
	/**SJSJ*/
	private java.util.Date sjsj;
	
	/**SJR*/
	private String sjr;
	
	/**JYDW*/
	private String jydw;
	
	/**LXDH*/
	private String lxdh;
	
	/**1、在检；2、已结束*/
	private String jyzt;
	
	/**检验费用*/
	private BigDecimal jyfy;
	
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
	
	@Column(name="SJSJ", length=19, nullable=true)
	public java.util.Date getSjsj() {
		return sjsj;
	}
	
	public void setSjsj(java.util.Date sjsj) {
		this.sjsj = sjsj;
	}
	
	@Column(name="SJR", length=516, nullable=true)
	public String getSjr() {
		return sjr;
	}
	
	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	
	@Column(name="JYDW", length=32, nullable=true)
	public String getJydw() {
		return jydw;
	}
	
	public void setJydw(String jydw) {
		this.jydw = jydw;
	}
	
	@Column(name="LXDH", length=32, nullable=true)
	public String getLxdh() {
		return lxdh;
	}
	
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@Column(name="JYZT", length=2, nullable=true)
	public String getJyzt() {
		return jyzt;
	}
	
	public void setJyzt(String jyzt) {
		this.jyzt = jyzt;
	}
	
	@Column(name="JYFY", length=12, nullable=true)
	public BigDecimal getJyfy() {
		return jyfy;
	}
	
	public void setJyfy(BigDecimal jyfy) {
		this.jyfy = jyfy;
	}
	
	@Column(name="BZ", length=512, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
