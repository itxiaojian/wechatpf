package com.sliu.framework.app.wlx.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zs_txjl")
public class LxTxjl implements Serializable{
	
	 /** id */
	private Long id;
	
	 /** 记录ID */
	private Long jlid;
	
	 /**学生学号*/
	private String xsxh;
	
	 /** 提醒内容*/
	private String txnr;
	
	 /** 提醒时间 */
	private Date txsj;
	
	/** 微信号 */
	private String wxh;
	
	/** 办理状态 */
	private String blzt;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "jlid", length = 11, nullable = true)
	public Long getJlid() {
		return jlid;
	}

	public void setJlid(Long jlid) {
		this.jlid = jlid;
	}

	@Column(name = "xsxh", length = 36, nullable = true)
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	@Column(name = "txnr", length = 600, nullable = true)
	public String getTxnr() {
		return txnr;
	}

	public void setTxnr(String txnr) {
		this.txnr = txnr;
	}

	@Column(name = "txsj", nullable = true)
	public Date getTxsj() {
		return txsj;
	}

	public void setTxsj(Date txsj) {
		this.txsj = txsj;
	}

	@Column(name = "wxh", length = 100, nullable = true)
	public String getWxh() {
		return wxh;
	}

	public void setWxh(String wxh) {
		this.wxh = wxh;
	}

	@Column(name = "blzt", length = 2, nullable = true)
	public String getBlzt() {
		return blzt;
	}

	public void setBlzt(String blzt) {
		this.blzt = blzt;
	}
	
}
