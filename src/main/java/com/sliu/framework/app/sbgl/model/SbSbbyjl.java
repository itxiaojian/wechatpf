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
 * 设备保养记录
 * @author liujiansen
 * @since  2015-08-25
 */
@Entity
@Table(name="sb_sbbyjl")
public class SbSbbyjl implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**设备编号*/
	private String sbbh;
	
	/**保养单号*/
	private String wxdh;
	
	/**保养申请人*/
	private String wxsqr;
	
	/**保养描述*/
	private String gzms;
	
	/**保养单位*/
	private String wxdw;
	
	/**保养负责人*/
	private String wxfzr;
	
	/**保养状态1、进行中；2、已结束*/
	private String wxzt;
	
	/**保养级别1、大修；2、中修；3、部件维修；4、备件更换；5、例行修检*/
	private String wxjb;
	
	/**开始时间*/
	private java.util.Date kssj;
	
	/**完成时间*/
	private java.util.Date wcsj;
	
	/**保养评分*/
	private String bypf;
	
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
	
	@Column(name="WXDH", length=32, nullable=true)
	public String getWxdh() {
		return wxdh;
	}
	
	public void setWxdh(String wxdh) {
		this.wxdh = wxdh;
	}
	
	@Column(name="WXSQR", length=32, nullable=true)
	public String getWxsqr() {
		return wxsqr;
	}
	
	public void setWxsqr(String wxsqr) {
		this.wxsqr = wxsqr;
	}
	
	@Column(name="GZMS", length=516, nullable=true)
	public String getGzms() {
		return gzms;
	}
	
	public void setGzms(String gzms) {
		this.gzms = gzms;
	}
	
	@Column(name="WXDW", length=32, nullable=true)
	public String getWxdw() {
		return wxdw;
	}
	
	public void setWxdw(String wxdw) {
		this.wxdw = wxdw;
	}
	
	@Column(name="WXFZR", length=32, nullable=true)
	public String getWxfzr() {
		return wxfzr;
	}
	
	public void setWxfzr(String wxfzr) {
		this.wxfzr = wxfzr;
	}
	
	@Column(name="WXZT", length=12, nullable=true)
	public String getWxzt() {
		return wxzt;
	}
	
	public void setWxzt(String wxzt) {
		this.wxzt = wxzt;
	}
	
	@Column(name="WXJB", length=12, nullable=true)
	public String getWxjb() {
		return wxjb;
	}
	
	public void setWxjb(String wxjb) {
		this.wxjb = wxjb;
	}
	
	@Column(name="KSSJ", length=19, nullable=true)
	public java.util.Date getKssj() {
		return kssj;
	}
	
	public void setKssj(java.util.Date kssj) {
		this.kssj = kssj;
	}
	
	@Column(name="WCSJ", length=19, nullable=true)
	public java.util.Date getWcsj() {
		return wcsj;
	}
	
	public void setWcsj(java.util.Date wcsj) {
		this.wcsj = wcsj;
	}
	
	@Column(name="BYPF", length=2, nullable=true)
	public String getBypf() {
		return bypf;
	}
	
	public void setBypf(String bypf) {
		this.bypf = bypf;
	}
	
}
