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
 * 学籍异动
 * @author liujiasen
 * @date 2015年6月1日
 */
@Entity
@Table(name = "zs_xjyd")
public class ZsXjyd implements Serializable {

	/** id */
	private Long id;

	/** 学号 */
	private String xh;

	/** 异动类型 */
	private String ydlx;

	/** 详细情况*/
	private String xxqk;

	/** 发生时间*/
	private Date fssj;

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

	@Column(name = "ydlx", length = 128,nullable = true)
	public String getYdlx() {
		return ydlx;
	}

	public void setYdlx(String ydlx) {
		this.ydlx = ydlx;
	}

	@Column(name = "xxqk", length = 1000, nullable = true)
	public String getXxqk() {
		return xxqk;
	}

	public void setXxqk(String xxqk) {
		this.xxqk = xxqk;
	}

	@Column(name = "fssj", nullable = true)
	public Date getFssj() {
		return fssj;
	}

	public void setFssj(Date fssj) {
		this.fssj = fssj;
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
