package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "ZS_XSJZDXX")
public class ZsXsjzdxx implements Serializable{
	/** id */
	private Long id;
	
	/** 姓名*/
	private String xm;

	/** 性别 */
	private String xb;

	/** 学号 */
	private String xh;

	/** 奖惩类型(1：奖学金；2：助学金；3：贷款)*/
	private String lx;

	/** 时间 */
	private DateTime sj;

	/** 金额 */
	private BigDecimal je;
	
	/** 院系*/
	private String yx;

	/** 专业 */
	private BigDecimal zy;
	
	/** 班级名称 */
	private String bjmc;
	
	/** 班级编号 */
	private String bjbh;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "xm", length = 36, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "xb", length = 2, nullable = true)
	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	@Column(name = "xh", length = 32, nullable = true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "lx", length = 2, nullable = true)
	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	@Column(name = "sj", nullable = true)
	public DateTime getSj() {
		return sj;
	}

	public void setSj(DateTime sj) {
		this.sj = sj;
	}

	@Column(name = "je", nullable = true)
	public BigDecimal getJe() {
		return je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	@Column(name = "yx", length = 36, nullable = true)
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	@Column(name = "zy", length = 36, nullable = true)
	public BigDecimal getZy() {
		return zy;
	}

	public void setZy(BigDecimal zy) {
		this.zy = zy;
	}

	@Column(name = "bjmc", length = 50, nullable = true)
	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	@Column(name = "bjbh", length = 32, nullable = true)
	public String getBjbh() {
		return bjbh;
	}

	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}
}
