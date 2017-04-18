package com.sliu.framework.app.wtx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tx_xsjqftxjl")
public class TxXsjqftx implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**学年*/
	private String xn;
	
	/**学号*/
	private String xh;
	
	/**金额内容*/
	private String txnr;
	
	/**姓名*/
	private String xm;
	

	/**微信号*/
	private String wxh;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	 @Column(name="XN", length=10, nullable=true)
	public String getXn() {
		return xn;
	}


	public void setXn(String xn) {
		this.xn = xn;
	}


	 @Column(name="XH", length=10, nullable=true)
	public String getXh() {
		return xh;
	}


	public void setXh(String xh) {
		this.xh = xh;
	}

	 @Column(name="TXNR", length=50, nullable=true)
	public String getTxnr() {
		return txnr;
	}


	public void setTxnr(String txnr) {
		this.txnr = txnr;
	}


	 @Column(name="XM", length=10, nullable=true)
	public String getXm() {
		return xm;
	}


	public void setXm(String xm) {
		this.xm = xm;
	}

	 @Column(name="WXH", length=100, nullable=true)
	public String getWxh() {
		return wxh;
	}


	public void setWxh(String wxh) {
		this.wxh = wxh;
	}	
}
