package com.sliu.framework.app.stat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
@Author oufeng	
@Date 2015年8月1日 上午11:13:39
@Version 1.0
*/
@Entity
@Table(name = "zs_jsxl_text")
public class DsJsxl implements Serializable{
	//主键id
 private Long id;
 
 //教师工号
 private Long jsgh;
 
 //教师学历
 private String jsxl;
 
 //教师姓名
 private String xm;

 //学历编号
 private Long xlbh;
 
@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

@Column(name = "jsgh", nullable = true)
public Long getJsgh() {
	return jsgh;
}

public void setJsgh(Long jsgh) {
	this.jsgh = jsgh;
}

@Column(name = "jsxl", nullable = true)
public String getJsxl() {
	return jsxl;
}

public void setJsxl(String jsxl) {
	this.jsxl = jsxl;
}

@Column(name = "xm", nullable = true)
public String getXm() {
	return xm;
}

public void setXm(String xm) {
	this.xm = xm;
}

@Column(name = "xlbh", nullable = true)
public Long getXlbh() {
	return xlbh;
}

public void setXlbh(Long xlbh) {
	this.xlbh = xlbh;
}

}

