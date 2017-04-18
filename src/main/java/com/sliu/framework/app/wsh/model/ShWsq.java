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
 * 微上墙
 * @author liujiansen
 * @since  2015-07-07
 */
@Entity
@Table(name="sh_wsq")
public class ShWsq implements Serializable{
	
	/**ID*/
	private Long id;
	
	/**活动名称*/
	private String hdmc;
	
	/**活动LOGO*/
	private String logo;
	
	/**上墙皮肤*/
	private String sqpf;
	
	/**开始时间*/
	private java.util.Date kssj;
	
	/**截止时间*/
	private java.util.Date jzsj;
	
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
	
	@Column(name="HDMC", length=150, nullable=true)
	public String getHdmc() {
		return hdmc;
	}
	
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	
	@Column(name="LOGO", length=200, nullable=true)
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Column(name="SQPF", length=200, nullable=true)
	public String getSqpf() {
		return sqpf;
	}
	
	public void setSqpf(String sqpf) {
		this.sqpf = sqpf;
	}
	
	@Column(name="KSSJ", length=19, nullable=true)
	public java.util.Date getKssj() {
		return kssj;
	}
	
	public void setKssj(java.util.Date kssj) {
		this.kssj = kssj;
	}
	
	@Column(name="JZSJ", length=19, nullable=true)
	public java.util.Date getJzsj() {
		return jzsj;
	}
	
	public void setJzsj(java.util.Date jzsj) {
		this.jzsj = jzsj;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
