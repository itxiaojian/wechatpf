package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生实习情况
 * @author liujiasen
 * @date 2015年5月28日
 */
@Entity
@Table(name = "zs_xssxqk")
public class ZsXssxqk implements Serializable {

	/** id */
	private Long id;

	/** 学号\工号 */
	private String xh;
	
	/** 学生姓名*/
	private String xsxm;

	/** 班级编号*/
	private String bjbh;
	
	/** 实习地点*/
	private String sxdd;

	/** 详细说明 */
	private String xxsm;

	/** 开始时间*/
	private Date kssj;

	/** 结束时间 */
	private Date jssj;

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

	@Column(name = "xh", length = 30, nullable = true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "xsxm", length = 30, nullable = true)
	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}

	@Column(name = "bjbh", length = 30, nullable = true)
	public String getBjbh() {
		return bjbh;
	}

	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}

	@Column(name = "sxdd", length = 135, nullable = true)
	public String getSxdd() {
		return sxdd;
	}

	public void setSxdd(String sxdd) {
		this.sxdd = sxdd;
	}

	@Column(name = "xxsm", length = 500, nullable = true)
	public String getXxsm() {
		return xxsm;
	}

	public void setXxsm(String xxsm) {
		this.xxsm = xxsm;
	}

	@Column(name = "kssj", nullable = true)
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	@Column(name = "jssj", nullable = true)
	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
