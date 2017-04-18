package com.sliu.framework.app.stat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*生源地统计
@Author oufeng	
@Date 2015年8月1日 上午11:47:27
@Version 1.0
*/
@Entity
@Table(name = "zs_syd_test")
public class DsSyd  implements Serializable{
 
	//主键id
	private Long id;
	
	//姓名
	private String xm;
	
	//学生学号
	private  String xsxh;
	
	//学分所在省份
	private String xssf;
	
	//省份编号
	private String sfbh;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "xm", nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "xsxh", nullable = true)
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	@Column(name = "xssf", nullable = true)
	public String getXssf() {
		return xssf;
	}

	public void setXssf(String xssf) {
		this.xssf = xssf;
	}

	@Column(name = "sfbh", nullable = true)
	public String getSfbh() {
		return sfbh;
	}

	public void setSfbh(String sfbh) {
		this.sfbh = sfbh;
	}

}


