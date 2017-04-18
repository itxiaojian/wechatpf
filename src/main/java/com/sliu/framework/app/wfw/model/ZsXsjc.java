package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生奖惩
 * @author liujiasen
 * @date 2015年5月20日
 */
@Entity
@Table(name = "zs_xsjc")
public class ZsXsjc implements Serializable {

	/** id */
	private Long id;
	
	/** 奖惩类型(0奖励;1惩罚;2奖学金;3助学金)*/
	private Integer jclx;

	/** 奖惩学年 */
	private String ksxn;

	/** 奖惩学期 */
	private String ksqh;

	/** 学号 */
	private String xh;

	/** 奖惩结果 */
	private String jcjg;

	/** 奖惩事由*/
	private String jcsy;

	/** 名称*/
	private String mc;

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

	@Column(name = "ksxn", length = 20, nullable = true)
	public String getKsxn() {
		return ksxn;
	}

	public void setKsxn(String ksxn) {
		this.ksxn = ksxn;
	}

	@Column(name = "ksqh", length = 10, nullable = true)
	public String getKsqh() {
		return ksqh;
	}

	public void setKsqh(String ksqh) {
		this.ksqh = ksqh;
	}

	@Column(name = "xh", length = 30, nullable = true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Column(name = "jclx", nullable = true)
	public Integer getJclx() {
		return jclx;
	}

	public void setJclx(Integer jclx) {
		this.jclx = jclx;
	}

	@Column(name = "jcjg", length = 50, nullable = true)
	public String getJcjg() {
		return jcjg;
	}

	public void setJcjg(String jcjg) {
		this.jcjg = jcjg;
	}

	@Column(name = "jcsy", length = 200, nullable = true)
	public String getJcsy() {
		return jcsy;
	}

	public void setJcsy(String jcsy) {
		this.jcsy = jcsy;
	}

	@Column(name = "mc", length = 30, nullable = true)
	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
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
