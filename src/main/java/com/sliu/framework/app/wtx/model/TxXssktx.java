package com.sliu.framework.app.wtx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 学生上课提醒
 * @author liujiansen
 * @since  2015-07-29
 */
@Entity
@Table(name="tx_xssktx")
public class TxXssktx implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**提醒内容*/
	private String txnr;
	
	/**提醒对象*/
	private String txdx;
	
	/**openId*/
	private String openid;
	
	/**提醒时间*/
	private java.util.Date txsj;
	
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
	
	@Column(name="TXNR", length=512, nullable=true)
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
	
}
