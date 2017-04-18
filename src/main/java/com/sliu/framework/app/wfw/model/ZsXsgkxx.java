package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生挂科信息
 * @author zhangyan
 * @since  2016-08-08
 */
@Entity
@Table(name="zs_xsgkxx")
public class ZsXsgkxx implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**学年*/
	private String xn;
	
	/**学期*/
	private String xq;
	
	/**课程编号*/
	private String kcbh;
	
	/**课程名称*/
	private String kcmc;
	
	/**课程体系*/
	private String kctx;
	
	/**课程性质*/
	private String ksxz;
	
	/**学号*/
	private String xh;
	
	private String xm;//姓名
	
	/**成绩*/
	private String cj;
	
	/**学分*/
	private String xf;
	
	/**任课教师*/
	private String rkjs;
	
	/**任课教师工号*/
	private String rkjsgh;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="XH", length=36, nullable=true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name="XN", length=20, nullable=true)
	public String getXn() {
		return xn;
	}
	
	public void setXn(String xn) {
		this.xn = xn;
	}
	
	@Column(name="XQ", length=10, nullable=true)
	public String getXq() {
		return xq;
	}
	
	public void setXq(String xq) {
		this.xq = xq;
	}
	
	@Column(name="KCBH", length=32, nullable=true)
	public String getKcbh() {
		return kcbh;
	}
	
	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}
	
	@Column(name="KCMC", length=60, nullable=true)
	public String getKcmc() {
		return kcmc;
	}
	
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	
	@Column(name = "xm", length = 36, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name="kctx", length=36, nullable=true)
	public String getKctx() {
		return kctx;
	}

	public void setKctx(String kctx) {
		this.kctx = kctx;
	}

	@Column(name="ksxz", length=36, nullable=true)
	public String getKsxz() {
		return ksxz;
	}

	public void setKsxz(String ksxz) {
		this.ksxz = ksxz;
	}

	@Column(name="cj", length=10, nullable=true)
	public String getCj() {
		return cj;
	}

	public void setCj(String cj) {
		this.cj = cj;
	}

	@Column(name="xf", length=10, nullable=true)
	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	@Column(name="rkjs", length=36, nullable=true)
	public String getRkjs() {
		return rkjs;
	}

	public void setRkjs(String rkjs) {
		this.rkjs = rkjs;
	}

	@Column(name="rkjsgh", length=36, nullable=true)
	public String getRkjsgh() {
		return rkjsgh;
	}

	public void setRkjsgh(String rkjsgh) {
		this.rkjsgh = rkjsgh;
	}
	
}
