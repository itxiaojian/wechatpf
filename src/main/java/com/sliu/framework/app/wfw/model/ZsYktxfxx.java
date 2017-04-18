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
 * 一卡通消费信息
 * @author liujiasen
 * @date 2015年5月25日
 */
@Entity
@Table(name = "zs_yktxfxx")
public class ZsYktxfxx implements Serializable {

	/** id */
	private Long id;

	/** 消费时间*/
	private Date xfsj;
	
	/** 消费地点 */
	private String xfdd;

	/** 消费金额 */
	private BigDecimal xfje;

	/** 学号工号 */
	private String bh;

	/** 卡号 */
	private String kh;

	/** 余额 */
	private BigDecimal ye;

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

	@Column(name = "xfsj", nullable = true)
	public Date getXfsj() {
		return xfsj;
	}

	public void setXfsj(Date xfsj) {
		this.xfsj = xfsj;
	}

	@Column(name = "xfdd", length = 150, nullable = true)
	public String getXfdd() {
		return xfdd;
	}

	public void setXfdd(String xfdd) {
		this.xfdd = xfdd;
	}

	@Column(name = "xfje", nullable = true)
	public BigDecimal getXfje() {
		return xfje;
	}

	public void setXfje(BigDecimal xfje) {
		this.xfje = xfje;
	}

	@Column(name = "bh", length = 30, nullable = true)
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
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

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
