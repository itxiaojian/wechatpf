package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生缴欠费信息
 * @author logician
 * @date 2015年6月1日
 */
@Entity
@Table(name = "zs_xsjqfxx")
public class ZsXsjqfxx implements Serializable {

	/** id */
	private Long id;

	/** 学年 */
	private String xn;

	/** 学号 */
	private String xh;

	/** 类型(1、缴费；2、欠费) */
	private Integer lx;

	/** 缴费项目*/
	private String jfxm;

	/** 应缴金额*/
	private String yjje;

	/** 实缴金额 */
	private Integer sjje;
	
	/** 欠费金额*/
	private String qfje;
	
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

	@Column(name = "xn", length = 20, nullable = true)
	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	@Column(name = "xh", length = 30, nullable = true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "lx", nullable = true)
	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	@Column(name = "jfxm", length = 30, nullable = true)
	public String getJfxm() {
		return jfxm;
	}

	public void setJfxm(String jfxm) {
		this.jfxm = jfxm;
	}

	@Column(name = "yjje", length = 50, nullable = true)
	public String getYjje() {
		return yjje;
	}

	public void setYjje(String yjje) {
		this.yjje = yjje;
	}

	@Column(name = "sjje", length = 50, nullable = true)
	public Integer getSjje() {
		return sjje;
	}

	public void setSjje(Integer sjje) {
		this.sjje = sjje;
	}

	@Column(name = "qfje", length = 50, nullable = true)
	public String getQfje() {
		return qfje;
	}

	public void setQfje(String qfje) {
		this.qfje = qfje;
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
