package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 科研项目
 * @author liujiansen
 * @since  2015-06-03
 */
@Entity
@Table(name="zs_kyxm")
public class ZsKyxm implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**项目名称*/
	private String xmmc;
	
	/**立项日期*/
	private java.util.Date lxrq;
	
	/**部门*/
	private String bm;
	
	/**工号*/
	private String gh;
	
	/**姓名*/
	private String xm;
	
	/**资助经费信息*/
	private String zzjf;
	
	/**备注*/
	private String bz;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="XMMC", length=30, nullable=true)
	public String getXmmc() {
		return xmmc;
	}
	
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	
	@Column(name="LXRQ", length=10, nullable=true)
	public java.util.Date getLxrq() {
		return lxrq;
	}
	
	public void setLxrq(java.util.Date lxrq) {
		this.lxrq = lxrq;
	}
	
	@Column(name="BM", length=36, nullable=true)
	public String getBm() {
		return bm;
	}
	
	public void setBm(String bm) {
		this.bm = bm;
	}
	
	@Column(name="GH", length=36, nullable=true)
	public String getGh() {
		return gh;
	}
	
	public void setGh(String gh) {
		this.gh = gh;
	}
	
	@Column(name="XM", length=36, nullable=true)
	public String getXm() {
		return xm;
	}
	
	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Column(name="ZZJF", length=500, nullable=true)
	public String getZzjf() {
		return zzjf;
	}
	
	public void setZzjf(String zzjf) {
		this.zzjf = zzjf;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
