package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 网络选修课信息
 * @author liujiansen
 * @since  2015-06-03
 */
@Entity
@Table(name="zs_xxkxx")
public class ZsXxkxx implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**学号*/
	private String xh;
	
	/**学年*/
	private String xn;
	
	/**学期*/
	private String xq;
	
	/**课程编号*/
	private String kcbh;
	
	/**课程名称*/
	private String kcmc;
	
	/**上课地点*/
	private String skdd;
	
	/**授课老师工号*/
	private String sklsgh;
	
	/**备注*/
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
	
	@Column(name="XH", length=30, nullable=true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name="XN", length=30, nullable=true)
	public String getXn() {
		return xn;
	}
	
	public void setXn(String xn) {
		this.xn = xn;
	}
	
	@Column(name="XQ", length=30, nullable=true)
	public String getXq() {
		return xq;
	}
	
	public void setXq(String xq) {
		this.xq = xq;
	}
	
	@Column(name="KCBH", length=30, nullable=true)
	public String getKcbh() {
		return kcbh;
	}
	
	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}
	
	@Column(name="KCMC", length=150, nullable=true)
	public String getKcmc() {
		return kcmc;
	}
	
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	
	@Column(name="SKDD", length=100, nullable=true)
	public String getSkdd() {
		return skdd;
	}
	
	public void setSkdd(String skdd) {
		this.skdd = skdd;
	}
	
	@Column(name="SKLSGH", length=30, nullable=true)
	public String getSklsgh() {
		return sklsgh;
	}
	
	public void setSklsgh(String sklsgh) {
		this.sklsgh = sklsgh;
	}
	
	@Column(name="BZ", length=500, nullable=true)
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
