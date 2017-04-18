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
 * 班级课表
 * @author liujiasen
 * @date 2015年5月25日
 */
@Entity
@Table(name = "zs_bjkb")
public class ZsBjkb implements Serializable {

	/** id */
	private Long id;

	/** 班级编号 */
	private String bjbh;

	/** 上课日期*/
	private Date skrq;
	
	/** 星期 */
	private String xq;

	/** 当月周数 */
	private String dyzs;

	/** 上课时间 */
	private Date sksj;

	/** 下课时间 */
	private Date xksj;

	/** 课程编号 */
	private String kcbh;
	
	/** 课程名称 */
	private String kcmc;

	/** 上课地点 */
	private String skdd;

	/** 授课老师工号 */
	private String sklsgh;

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

	@Column(name = "bjbh", length = 30, nullable = true)
	public String getBjbh() {
		return bjbh;
	}

	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}

	@Column(name = "skrq", nullable = true)
	public Date getSkrq() {
		return skrq;
	}

	public void setSkrq(Date skrq) {
		this.skrq = skrq;
	}

	@Column(name = "xq", length = 10, nullable = true)
	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	@Column(name = "dyzs", length = 10, nullable = true)
	public String getDyzs() {
		return dyzs;
	}

	public void setDyzs(String dyzs) {
		this.dyzs = dyzs;
	}

	@Column(name = "sksj",nullable = true)
	public Date getSksj() {
		return sksj;
	}

	public void setSksj(Date sksj) {
		this.sksj = sksj;
	}

	@Column(name = "xksj", nullable = true)
	public Date getXksj() {
		return xksj;
	}

	public void setXksj(Date xksj) {
		this.xksj = xksj;
	}

	@Column(name = "kcbh", length = 30, nullable = true)
	public String getKcbh() {
		return kcbh;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	@Column(name = "kcmc", length = 150, nullable = true)
	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	@Column(name = "skdd", length = 100, nullable = true)
	public String getSkdd() {
		return skdd;
	}

	public void setSkdd(String skdd) {
		this.skdd = skdd;
	}

	@Column(name = "sklsgh", length = 30, nullable = true)
	public String getSklsgh() {
		return sklsgh;
	}

	public void setSklsgh(String sklsgh) {
		this.sklsgh = sklsgh;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
