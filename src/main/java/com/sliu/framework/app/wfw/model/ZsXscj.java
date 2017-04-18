package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生成绩
 * @author liujiasen
 * @date 2015年5月18日
 */
@Entity
@Table(name = "zs_xscj")
public class ZsXscj implements Serializable {

	/** id */
	private Long id;

	/** 考试学年 */
	private String ksxn;

	/** 考试学期 */
	private String ksqh;

	/** 学号 */
	private String xh;

	/** 考试科目 */
	private String kskm;

	/** 课程编号*/
	private String kcbh;

	/** 考试成绩*/
	private String kscj;

	/** 是否通过(0不通过;1通过) */
	private Integer sftg;
	
	/** 补考成绩*/
	private String bkcj;
	
	/** 学分 */
	private Double xf;
	
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

	@Column(name = "kskm", length = 50, nullable = true)
	public String getKskm() {
		return kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	@Column(name = "kcbh", length = 30, nullable = true)
	public String getKcbh() {
		return kcbh;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	@Column(name = "kscj", length = 10, nullable = true)
	public String getKscj() {
		return kscj;
	}

	public void setKscj(String kscj) {
		this.kscj = kscj;
	}

	@Column(name = "sftg", nullable = true)
	public Integer getSftg() {
		return sftg;
	}

	public void setSftg(Integer sftg) {
		this.sftg = sftg;
	}

	@Column(name = "bkcj", length = 10, nullable = true)
	public String getBkcj() {
		return bkcj;
	}

	public void setBkcj(String bkcj) {
		this.bkcj = bkcj;
	}

	@Column(name = "xf", nullable = true)
	public Double getXf() {
		return xf;
	}

	public void setXf(Double xf) {
		this.xf = xf;
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
