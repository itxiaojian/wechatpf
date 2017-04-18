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
 * 设备维修计划
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbwxjh")
public class SbSbwxjh implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**SBBH*/
	private String sbbh;
	
	/**WXJB*/
	private String wxjb;
	
	/**1、多次循环；2、单次*/
	private String xhfs;
	
	/**SCWXSJ*/
	private java.util.Date scwxsj;
	
	/**JG*/
	private Integer jg;
	
	/**XCWXSJ*/
	private java.util.Date xcwxsj;
	
	/**WXRBH*/
	private String wxrbh;
	
	/**WXRXM*/
	private String wxrxm;
	
	/**1、有效；2、已结束*/
	private String zt;
	
	/**GZMS*/
	private String gzms;
	
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
	
	@Column(name="WXJB", length=32, nullable=true)
	public String getWxjb() {
		return wxjb;
	}
	
	public void setWxjb(String wxjb) {
		this.wxjb = wxjb;
	}
	
	@Column(name="XHFS", length=2, nullable=true)
	public String getXhfs() {
		return xhfs;
	}
	
	public void setXhfs(String xhfs) {
		this.xhfs = xhfs;
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
	
	@Column(name="WXRBH", length=32, nullable=true)
	public String getWxrbh() {
		return wxrbh;
	}
	
	public void setWxrbh(String wxrbh) {
		this.wxrbh = wxrbh;
	}
	
	@Column(name="WXRXM", length=32, nullable=true)
	public String getWxrxm() {
		return wxrxm;
	}
	
	public void setWxrxm(String wxrxm) {
		this.wxrxm = wxrxm;
	}
	
	@Column(name="ZT", length=2, nullable=true)
	public String getZt() {
		return zt;
	}
	
	public void setZt(String zt) {
		this.zt = zt;
	}
	
	@Column(name="GZMS", length=512, nullable=true)
	public String getGzms() {
		return gzms;
	}
	
	public void setGzms(String gzms) {
		this.gzms = gzms;
	}
	
}
