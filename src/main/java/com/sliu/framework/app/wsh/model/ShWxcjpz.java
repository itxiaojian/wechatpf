package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 微信抽奖配置
 * @author liujiansen
 * @since  2015-06-18
 */
@Entity
@Table(name="sh_wxcjpz")
public class ShWxcjpz implements Serializable{
	
	/**ID*/
	private Long id;
	
	/**起始时间*/
	private java.util.Date qssj;
	
	/**结束时间*/
	private java.util.Date jssj;
	
	/**抽奖次数*/
	private Integer cjcs;
	
	/**一等奖名称*/
	private String ydjmc;
	
	/**一等奖数量*/
	private Integer ydjsl;
	
	/**二等奖名称*/
	private String edjmc;
	
	/**二等奖数量*/
	private Integer rdjsl;
	
	/**三等奖名称*/
	private String sdjmc;
	
	/**三等奖数量*/
	private Integer sdjsl;
	
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
	
	@Column(name="QSSJ", length=19, nullable=true)
	public java.util.Date getQssj() {
		return qssj;
	}
	
	public void setQssj(java.util.Date qssj) {
		this.qssj = qssj;
	}
	
	@Column(name="JSSJ", length=19, nullable=true)
	public java.util.Date getJssj() {
		return jssj;
	}
	
	public void setJssj(java.util.Date jssj) {
		this.jssj = jssj;
	}
	
	@Column(name="CJCS", length=10, nullable=true)
	public Integer getCjcs() {
		return cjcs;
	}
	
	public void setCjcs(Integer cjcs) {
		this.cjcs = cjcs;
	}
	
	@Column(name="YDJMC", length=200, nullable=true)
	public String getYdjmc() {
		return ydjmc;
	}
	
	public void setYdjmc(String ydjmc) {
		this.ydjmc = ydjmc;
	}
	
	@Column(name="YDJSL", length=10, nullable=true)
	public Integer getYdjsl() {
		return ydjsl;
	}
	
	public void setYdjsl(Integer ydjsl) {
		this.ydjsl = ydjsl;
	}
	
	@Column(name="EDJMC", length=200, nullable=true)
	public String getEdjmc() {
		return edjmc;
	}
	
	public void setEdjmc(String edjmc) {
		this.edjmc = edjmc;
	}
	
	@Column(name="RDJSL", length=10, nullable=true)
	public Integer getRdjsl() {
		return rdjsl;
	}
	
	public void setRdjsl(Integer rdjsl) {
		this.rdjsl = rdjsl;
	}
	
	@Column(name="SDJMC", length=200, nullable=true)
	public String getSdjmc() {
		return sdjmc;
	}
	
	public void setSdjmc(String sdjmc) {
		this.sdjmc = sdjmc;
	}
	
	@Column(name="SDJSL", length=10, nullable=true)
	public Integer getSdjsl() {
		return sdjsl;
	}
	
	public void setSdjsl(Integer sdjsl) {
		this.sdjsl = sdjsl;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
