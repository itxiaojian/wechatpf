package com.sliu.framework.app.wsh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 投票记录
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Entity
@Table(name = "WSH_TPJL")
public class ShTpjl implements Serializable{
	
	 /** id */
	 private Long id;
	 
	 /** 投票人 */
	 private String tpr;
	 
	 /** 投票时间 */
	 private Date tpsj;
	 
	 /** 投票选项id */
	 private Long xxid;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tpr", length = 32, nullable = true)
	public String getTpr() {
		return tpr;
	}

	public void setTpr(String tpr) {
		this.tpr = tpr;
	}

	@Column(name = "tpsj", length = 19, nullable = true)
	public Date getTpsj() {
		return tpsj;
	}

	public void setTpsj(Date tpsj) {
		this.tpsj = tpsj;
	}

	@Column(name = "xxid", length = 20, nullable = true)
	public Long getXxid() {
		return xxid;
	}

	public void setXxid(Long xxid) {
		this.xxid = xxid;
	}


}
