package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 贫困生信息
 * @author chenhui
 * @version 创建时间：2015年6月9日
 */
@Entity
@Table(name = "ZS_XSKQ")
public class ZsXskqxx implements Serializable{

	private Long id;//主键
	
	private String xsxh;//学生学号
	
	private String xsxm;//学生班级
	
	private String bjmc;//班级名称
	
	private String bjbh;//班级编号
	
	private String kcbh;//课程编号
	
	private String kcmc;//课程名称
	
	private String skrq;//上课日期
	
	private String skdd;//上课地点
	
	private String sfkq;//是否考勤
	
	private String lsgh;//老师工号
	
	private String bz;//备注

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "xsxh", length = 36, nullable = true)
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	@Column(name = "xsxm", length = 36, nullable = true)
	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}

	@Column(name = "bjmc", length = 36, nullable = true)
	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	@Column(name = "bjbh", length = 36, nullable = true)
	public String getBjbh() {
		return bjbh;
	}

	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}

	@Column(name = "kcbh", length = 36, nullable = true)
	public String getKcbh() {
		return kcbh;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	@Column(name = "kcmc", length = 36, nullable = true)
	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	@Column(name = "skrq", length = 36, nullable = true)
	public String getSkrq() {
		return skrq;
	}

	public void setSkrq(String skrq) {
		this.skrq = skrq;
	}

	@Column(name = "skdd", length = 36, nullable = true)
	public String getSkdd() {
		return skdd;
	}

	public void setSkdd(String skdd) {
		this.skdd = skdd;
	}

	@Column(name = "sfkq", length = 2, nullable = true)
	public String getSfkq() {
		return sfkq;
	}

	public void setSfkq(String sfkq) {
		this.sfkq = sfkq;
	}

	@Column(name = "lsgh", length = 36, nullable = true)
	public String getLsgh() {
		return lsgh;
	}

	public void setLsgh(String lsgh) {
		this.lsgh = lsgh;
	}

	@Column(name = "bz", length = 256, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
