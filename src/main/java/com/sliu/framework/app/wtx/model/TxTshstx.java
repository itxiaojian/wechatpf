package com.sliu.framework.app.wtx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图书还书提醒
 * @author oufeng
 * @date 2016年8月6日
 */
@Entity
@Table(name="tx_tshstx")
public class TxTshstx implements Serializable{

	/*主键*/
	private Long id;
	
	/*图书编号*/
	private String tsbh;
	
	/*图书名称*/
	private String tsmc;
	
	/*微信号*/
	private String wxh;
	
	/*借出时间*/
	private java.util.Date jcsj;
	
	/*应归还时间*/
	private java.util.Date yghsj;
	
	/*归还时间*/
	private java.util.Date ghsj;
	
	/*编号*/
	private String bh;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="tsbh", length=20, nullable=true)
	public String getTsbh() {
		return tsbh;
	}

	public void setTsbh(String tsbh) {
		this.tsbh = tsbh;
	}

	@Column(name="tsmc", length=100, nullable=true)
	public String getTsmc() {
		return tsmc;
	}

	public void setTsmc(String tsmc) {
		this.tsmc = tsmc;
	}

	@Column(name="wxh", length=100, nullable=true)
	public String getWxh() {
		return wxh;
	}

	public void setWxh(String wxh) {
		this.wxh = wxh;
	}

	@Column(name="jcsj", nullable=true)
	public java.util.Date getJcsj() {
		return jcsj;
	}

	public void setJcsj(java.util.Date jcsj) {
		this.jcsj = jcsj;
	}

	@Column(name="yghsj", nullable=true)
	public java.util.Date getYghsj() {
		return yghsj;
	}

	
	public void setYghsj(java.util.Date yghsj) {
		this.yghsj = yghsj;
	}

	@Column(name="ghsj", nullable=true)
	public java.util.Date getGhsj() {
		return ghsj;
	}

	public void setGhsj(java.util.Date ghsj) {
		this.ghsj = ghsj;
	}

	@Column(name="bh", nullable=true)
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}
}
