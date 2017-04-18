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
 * 教师监考信息
 * @author liujiasen
 * @date 2015年6月3日
 */
@Entity
@Table(name = "zs_jsjkxx")
public class ZsJsjkxx implements Serializable {

	/** id */
	private Long id;

	/** 教师工号 */
	private String jsgh;

	/** 监考课程编号*/
	private String jkkcbh;
	
	/** 监考课程名称 */
	private String jkkcmc;

	/** 考试性质 */
	private String ksxz;

	/** 考试方式 */
	private String ksfs;

	/** 考试时长 */
	private String kssc;

	/** 监考日期 */
	private Date jkrq;
	
	/** 考试时间 */
	private Date kssj;

	/** 监考地点 */
	private String jkdd;
	
	/** 参考班级 */
	private String cjbj;
	
	/** 考试人数 */
	private Integer ksrs;

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

	@Column(name = "jsgh", length = 30, nullable = true)
	public String getJsgh() {
		return jsgh;
	}

	public void setJsgh(String jsgh) {
		this.jsgh = jsgh;
	}

	@Column(name = "jkkcbh", length = 30, nullable = true)
	public String getJkkcbh() {
		return jkkcbh;
	}

	public void setJkkcbh(String jkkcbh) {
		this.jkkcbh = jkkcbh;
	}

	@Column(name = "jkkcmc", length = 150, nullable = true)
	public String getJkkcmc() {
		return jkkcmc;
	}

	public void setJkkcmc(String jkkcmc) {
		this.jkkcmc = jkkcmc;
	}

	@Column(name = "ksxz", length = 30, nullable = true)
	public String getKsxz() {
		return ksxz;
	}

	public void setKsxz(String ksxz) {
		this.ksxz = ksxz;
	}

	@Column(name = "ksfs", length = 30, nullable = true)
	public String getKsfs() {
		return ksfs;
	}

	public void setKsfs(String ksfs) {
		this.ksfs = ksfs;
	}

	@Column(name = "kssc", length = 10, nullable = true)
	public String getKssc() {
		return kssc;
	}

	public void setKssc(String kssc) {
		this.kssc = kssc;
	}

	@Column(name = "jkrq", nullable = true)
	public Date getJkrq() {
		return jkrq;
	}

	public void setJkrq(Date jkrq) {
		this.jkrq = jkrq;
	}

	@Column(name = "kssj", nullable = true)
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	@Column(name = "jkdd", length = 100, nullable = true)
	public String getJkdd() {
		return jkdd;
	}

	public void setJkdd(String jkdd) {
		this.jkdd = jkdd;
	}

	@Column(name = "cjbj", length = 30, nullable = true)
	public String getCjbj() {
		return cjbj;
	}

	public void setCjbj(String cjbj) {
		this.cjbj = cjbj;
	}

	@Column(name = "ksrs", nullable = true)
	public Integer getKsrs() {
		return ksrs;
	}

	public void setKsrs(Integer ksrs) {
		this.ksrs = ksrs;
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
