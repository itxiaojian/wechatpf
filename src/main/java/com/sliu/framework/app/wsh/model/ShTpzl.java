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
 * 投票专栏
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Entity
@Table(name = "WSH_TPZL")
public class ShTpzl implements Serializable{
	
	 /** id */
	 private Long id;
	 
	 /** 投票内容 */
	 private String tpnr;
	 
	 /** 发布时间 */
	 private Date fbsj;
	 
	 /** 结束时间 */
	 private Date jssj;
	 
	 /** 最多选项 */
	 private String zdxx;
	 
	 /** 发起人 */
	 private String fqr;
	 
	 /** 状态 */
	 private String zt;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tpnr", length = 500, nullable = true)
	public String getTpnr() {
		return tpnr;
	}

	public void setTpnr(String tpnr) {
		this.tpnr = tpnr;
	}

	@Column(name = "fbsj", length = 19, nullable = true)
	public Date getFbsj() {
		return fbsj;
	}

	public void setFbsj(Date fbsj) {
		this.fbsj = fbsj;
	}

	@Column(name = "jssj", length = 19, nullable = true)
	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

	@Column(name = "zdxx", length = 2, nullable = true)
	public String getZdxx() {
		return zdxx;
	}

	public void setZdxx(String zdxx) {
		this.zdxx = zdxx;
	}

	@Column(name = "fqr", length = 32, nullable = true)
	public String getFqr() {
		return fqr;
	}

	public void setFqr(String fqr) {
		this.fqr = fqr;
	}
	
	@Column(name = "zt", length = 2, nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
