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
 * 学生考试安排信息
 * @author liujiasen
 * @date 2015年5月29日
 */
@Entity
@Table(name = "zs_xsksapxx")
public class ZsXsksapxx implements Serializable {

	/** id */
	private Long id;

	/** 考试学年 */
	private String ksxn;

	/** 考试学期 */
	private String ksxq;
	
	/** 班级编号 */
	private String bjbh;

	/** 学号 */
	private String xh;

	/** 考试科目 */
	private String kskm;

	/** 课程编号*/
	private String kcbh;

	/** 考试性质*/
	private String ksxz;

	/** 考试方式 */
	private Integer ksfs;
	
	/** 考试时长*/
	private String kssc;
	
	/** 考试日期 */
	private Date ksrq;
	
	/** 考试时间 */
	private Date kssj;
	
	/** 考试地点 */
	private String ksdd;
	
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

	@Column(name = "ksxq", length = 10, nullable = true)
	public String getKsXq() {
		return ksxq;
	}

	public void setKsXq(String ksxq) {
		this.ksxq = ksxq;
	}

	@Column(name = "bjbh", length = 30, nullable = true)
	public String getBjbh() {
		return bjbh;
	}

	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
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

	@Column(name = "ksxz", length = 10, nullable = true)
	public String getKsxz() {
		return ksxz;
	}

	public void setKsxz(String ksxz) {
		this.ksxz = ksxz;
	}

	@Column(name = "ksfs", length = 10, nullable = true)
	public Integer getKsfs() {
		return ksfs;
	}

	public void setKsfs(Integer ksfs) {
		this.ksfs = ksfs;
	}

	@Column(name = "kssc", length = 10, nullable = true)
	public String getKssc() {
		return kssc;
	}

	public void setKssc(String kssc) {
		this.kssc = kssc;
	}

	@Column(name = "ksrq", nullable = true)
	public Date getKsrq() {
		return ksrq;
	}

	public void setKsrq(Date ksrq) {
		this.ksrq = ksrq;
	}

	@Column(name = "kssj", nullable = true)
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	@Column(name = "ksdd", length = 150, nullable = true)
	public String getKsdd() {
		return ksdd;
	}

	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
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
