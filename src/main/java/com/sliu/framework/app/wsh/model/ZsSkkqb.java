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
 * 上课考勤表
 * @author liujiansen
 * @since  2016-03-28
 */
@Entity
@Table(name="zs_skkqb")
public class ZsSkkqb implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**课程号*/
	private String kch;
	
	/**课程名称*/
	private String kcmc;
	
	/**发布人工号*/
	private String gh;
	
	/**发布人姓名*/
	private String xm;
	
	/**上课时间*/
	private java.util.Date sksj;
	
	/**二维码失效时间*/
	private java.util.Date ewmsxsj;
	
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
	
	@Column(name="KCH", length=30, nullable=true)
	public String getKch() {
		return kch;
	}
	
	public void setKch(String kch) {
		this.kch = kch;
	}
	
	@Column(name="KCMC", length=100, nullable=true)
	public String getKcmc() {
		return kcmc;
	}
	
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	
	@Column(name="GH", length=32, nullable=true)
	public String getGh() {
		return gh;
	}
	
	public void setGh(String gh) {
		this.gh = gh;
	}
	
	@Column(name="XM", length=100, nullable=true)
	public String getXm() {
		return xm;
	}
	
	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Column(name="SKSJ", length=19, nullable=true)
	public java.util.Date getSksj() {
		return sksj;
	}
	
	public void setSksj(java.util.Date sksj) {
		this.sksj = sksj;
	}
	
	@Column(name="EWMSXSJ", length=19, nullable=true)
	public java.util.Date getEwmsxsj() {
		return ewmsxsj;
	}
	
	public void setEwmsxsj(java.util.Date ewmsxsj) {
		this.ewmsxsj = ewmsxsj;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
