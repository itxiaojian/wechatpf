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
 * 学生贷款信息
 * @author liujiasen
 * @date 2015年6月1日
 */
@Entity
@Table(name = "zs_xsdkxx")
public class ZsXsdkxx implements Serializable {

	/** id */
	private Long id;
	
	/** 贷款类型 */
	private String dklx;

	/** 学号 */
	private String xh;

	/** 还款日期 */
	private Date hkrq;

	/** 金额 */
	private BigDecimal je;
	
	/** 备注 */
	private String bz;
	
	private String xm;//姓名

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "xh", length = 30, nullable = true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Column(name = "dklx", length = 30, nullable = true)
	public String getDklx() {
		return dklx;
	}

	public void setDklx(String dklx) {
		this.dklx = dklx;
	}

	@Column(name = "hkrq", nullable = true)
	public Date getHkrq() {
		return hkrq;
	}

	public void setHkrq(Date hkrq) {
		this.hkrq = hkrq;
	}

	@Column(name = "je", nullable = true)
	public BigDecimal getJe() {
		return je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name = "xm", length = 32, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
}
