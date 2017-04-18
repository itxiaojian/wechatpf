package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 一卡通信息
 * @author liujiasen
 * @date 2015年5月25日
 */
@Entity
@Table(name = "zs_yktxx")
public class ZsYktxx implements Serializable {

	/** id */
	private Long id;

	/** 学号工号 */
	private String bh;

	/** 姓名 */
	private String xm;

	/** 卡号 */
	private String kh;

	/** 余额 */
	private BigDecimal ye;

	/** 照片信息 */
	private String zpxx;

	/** 备注 */
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
	
	@Column(name = "bh", length = 30, nullable = true)
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	@Column(name = "xm", length = 50, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "kh", length = 30, nullable = true)
	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	@Column(name = "ye", nullable = true)
	public BigDecimal getYe() {
		return ye;
	}

	public void setYe(BigDecimal ye) {
		this.ye = ye;
	}

	@Column(name = "zpxx", length = 200, nullable = true)
	public String getZpxx() {
		return zpxx;
	}

	public void setZpxx(String zpxx) {
		this.zpxx = zpxx;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
