package com.sliu.framework.app.wzy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单
 * @author:wangchunlin
 * @version 创建时间：2016年1月12日  
 * @return
 */

@Entity
@Table(name = "ZY_JSCDPZ")
public class ZyJscdpz implements Serializable {
//	 主键ID
	private Integer id;
//	 排序
	private Integer cdid;
//	菜单名称
	private String jsmc;

	
//	生成get、set方法
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "jsmc", length = 50, nullable = true)
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	
	@Column(name = "cdid", nullable = true)
	public Integer getCdid() {
		return cdid;
	}
	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}	

}
