package com.sliu.framework.app.wtx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 拾卡提醒
 * @author yanzhang
 * @date 2016年10月25日 上午9:05:08
 * @param 
 * @return
 */
@Entity
@Table(name="tx_sktx")
public class TxSktx implements Serializable{

	/*id*/
	private Long id;
	
	/*提醒内容*/
	private String txnr;
	
	/*提醒对象*/
	private String txdx;
	
	/*openId*/
	private String openid;
	
	/*添加时间*/
	private java.util.Date txsj;
	
	/*备注*/
	private String bz;
	
	/*卡号*/
	private String kh;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="txnr", length=512, nullable=true)
	public String getTxnr() {
		return txnr;
	}

	public void setTxnr(String txnr) {
		this.txnr = txnr;
	}

	@Column(name="TXDX", length=32, nullable=true)
	public String getTxdx() {
		return txdx;
	}

	public void setTxdx(String txdx) {
		this.txdx = txdx;
	}

	@Column(name="OPENID", length=64, nullable=true)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name="TXSJ", length=19, nullable=true)
	public java.util.Date getTxsj() {
		return txsj;
	}

	public void setTxsj(java.util.Date txsj) {
		this.txsj = txsj;
	}
	
	@Column(name="BZ", length=512, nullable=true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name="KH", length=20, nullable=true)
	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}
	
	
	
}
