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
 * 微上墙发布信息
 * @author liujiansen
 * @since  2015-07-07
 */
@Entity
@Table(name="sh_wsqfbxx")
public class ShWsqfbxx implements Serializable{
	
	/**ID*/
	private Long id;
	
	/**活动编号*/
	private Long hdid;
	
	/**参与人ID*/
	private Long cyrid;
	
	/**发布内容*/
	private String fbnr;
	
	/**发布时间*/
	private java.util.Date fbsj;
	
	/**是否上墙（0是，1否）*/
	private String sfsq;
	
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
	
	@Column(name="HDID", length=19, nullable=true)
	public Long getHdid() {
		return hdid;
	}
	
	public void setHdid(Long hdid) {
		this.hdid = hdid;
	}
	
	@Column(name="CYRID", length=19, nullable=true)
	public Long getCyrid() {
		return cyrid;
	}
	
	public void setCyrid(Long cyrid) {
		this.cyrid = cyrid;
	}
	
	@Column(name="FBNR", length=600, nullable=true)
	public String getFbnr() {
		return fbnr;
	}
	
	public void setFbnr(String fbnr) {
		this.fbnr = fbnr;
	}
	
	@Column(name="FBSJ", length=19, nullable=true)
	public java.util.Date getFbsj() {
		return fbsj;
	}
	
	public void setFbsj(java.util.Date fbsj) {
		this.fbsj = fbsj;
	}
	
	@Column(name="SFSQ", length=5, nullable=true)
	public String getSfsq() {
		return sfsq;
	}
	
	public void setSfsq(String sfsq) {
		this.sfsq = sfsq;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
