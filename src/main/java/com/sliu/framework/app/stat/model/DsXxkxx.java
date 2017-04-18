package com.sliu.framework.app.stat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "zs_zs_xxkxx")
public class DsXxkxx  implements Serializable{
	/**
	*网络选修课信息
	@Author oufeng	
	@Date 2015年7月28日 上午10:58:24
	@Version 1.0
	*/
	
	/**主键id*/
private  Long id;


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

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", nullable = false)
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

@Column(name = "xh", nullable = true)
public String getXh() {
	return xh;
}

public void setXh(String xh) {
	this.xh = xh;
}

@Column(name = "xn", nullable = true)
public String getXn() {
	return xn;
}

public void setXn(String xn) {
	this.xn = xn;
}

@Column(name = "xq", nullable = true)
public String getXq() {
	return xq;
}

public void setXq(String xq) {
	this.xq = xq;
}

@Column(name = "kcbh", nullable = true)
public String getKcbh() {
	return kcbh;
}

public void setKcbh(String kcbh) {
	this.kcbh = kcbh;
}

@Column(name = "kcmc", nullable = true)
public String getKcmc() {
	return kcmc;
}

public void setKcmc(String kcmc) {
	this.kcmc = kcmc;
}

@Column(name = "skdd", nullable = true)
public String getSkdd() {
	return skdd;
}

public void setSkdd(String skdd) {
	this.skdd = skdd;
}

@Column(name = "sklsgh", nullable = true)
public String getSklsgh() {
	return sklsgh;
}

public void setSklsgh(String sklsgh) {
	this.sklsgh = sklsgh;
}

@Column(name = "bz", nullable = true)
public String getBz() {
	return bz;
}

public void setBz(String bz) {
	this.bz = bz;
}
}


