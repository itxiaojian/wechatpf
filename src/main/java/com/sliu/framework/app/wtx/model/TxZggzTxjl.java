package com.sliu.framework.app.wtx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tx_zggztxjl")
public class TxZggzTxjl {
	
	/** id */
	private Long id;
	
	/**年份*/
	private String nf;
	
	/**月份*/
	private String yf;
	
	/** 工号 */
	private String gh;
	
	/** 姓名 */
	private String xm;
	
	/** 微信号 */
	private String wxh;
	
	/** 状态 */
	private String zt;

	/** 状态 */
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

	@Column(name = "nf", length = 10, nullable = true)
	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	@Column(name = "yf", length = 10, nullable = true)
	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	@Column(name = "gh", length=30, nullable = true)
	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	@Column(name = "xm", length = 30, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "wxh", length = 100, nullable = true)
	public String getWxh() {
		return wxh;
	}

	public void setWxh(String wxh) {
		this.wxh = wxh;
	}

	@Column(name = "zt", length = 2, nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	@Column(name = "bz", length=500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
