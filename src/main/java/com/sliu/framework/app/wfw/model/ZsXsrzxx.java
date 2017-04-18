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
 * 学生入住信息
 * @author zhangyan
 * @version 创建时间：2016年8月8日
 */
@Entity
@Table(name = "ZS_XSRZXX")
public class ZsXsrzxx implements Serializable{

	private Long id;//主键
	
	private String  xm;//姓名
	
	private String  xh;//学号
	
	private String xb;//性别
	
	private String bjmc;//班级名称
	
	private String bjbh;//班级编号
	
	private String nj;//年级
	
	private String gyq;//公寓区
	
	private String gyl;//公寓楼
	
	private String fjh;//房间号
	
	private String cwh;//床位号
	
	private String xjzt;//学籍状态
	
	private Date rzsj;//入住时间
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "xh", length = 32, nullable = true)
	public String getXh() {
		return xh;
	}
	
	public void setXh(String xh) {
		this.xh = xh;
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

	@Column(name = "bjmc", length = 60, nullable = true)
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

	@Column(name = "nj", length = 20, nullable = true)
	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	@Column(name = "gyq", length = 36, nullable = true)
	public String getGyq() {
		return gyq;
	}

	public void setGyq(String gyq) {
		this.gyq = gyq;
	}

	@Column(name = "gyl", length = 36, nullable = true)
	public String getGyl() {
		return gyl;
	}

	public void setGyl(String gyl) {
		this.gyl = gyl;
	}

	@Column(name = "fjh", length = 36, nullable = true)
	public String getFjh() {
		return fjh;
	}

	public void setFjh(String fjh) {
		this.fjh = fjh;
	}

	@Column(name = "cwh", length = 36, nullable = true)
	public String getCwh() {
		return cwh;
	}

	public void setCwh(String cwh) {
		this.cwh = cwh;
	}

	@Column(name = "xjzt", length = 20, nullable = true)
	public String getXjzt() {
		return xjzt;
	}

	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}

	@Column(name = "rzsj", nullable = true)
	public Date getRzsj() {
		return rzsj;
	}

	public void setRzsj(Date rzsj) {
		this.rzsj = rzsj;
	}
}
