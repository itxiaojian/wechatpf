package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 投票专栏选项
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Entity
@Table(name = "WSH_TPZLXX")
public class ShTpzlxx implements Serializable{
	
	 /** id */
	 private Long id;
	 
	 /** 投票id */
	 private Long tpid;
	 
	 /** 选项内容 */
	 private String xxnr;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tpid", length = 20, nullable = true)
	public Long getTpid() {
		return tpid;
	}

	public void setTpid(Long tpid) {
		this.tpid = tpid;
	}

	@Column(name = "xxnr", length = 200, nullable = true)
	public String getXxnr() {
		return xxnr;
	}

	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}


}
