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
 * 专题讨论
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Entity
@Table(name = "WSH_ZTTL")
public class ShZttl implements Serializable{
	
	 /** id */
	 private Long id;
	 
	 /** 专题名称 */
	 private String ztmc;
	 
	 /** 专题标题 */
	 private String ztbt;
	 
	 /** 专题内容 */
	 private String ztnr;
	 
	 /** 发布时间 */
	 private Date fbsj;
	 
	 /** 计划结束时间 */
	 private Date jhjssj;
	 
	 /** 发布人 */
	 private String fbr;
	 
	 /** 专题最后结果 */
	 private String ztzhjg;
	 
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

	@Column(name = "ztmc", length = 100, nullable = true)
	public String getZtmc() {
		return ztmc;
	}

	public void setZtmc(String ztmc) {
		this.ztmc = ztmc;
	}

	@Column(name = "ztbt", length = 100, nullable = true)
	public String getZtbt() {
		return ztbt;
	}

	public void setZtbt(String ztbt) {
		this.ztbt = ztbt;
	}

	@Column(name = "ztnr", length = 2000, nullable = true)
	public String getZtnr() {
		return ztnr;
	}

	public void setZtnr(String ztnr) {
		this.ztnr = ztnr;
	}

	@Column(name = "fbsj", length = 19, nullable = true)
	public Date getFbsj() {
		return fbsj;
	}

	public void setFbsj(Date fbsj) {
		this.fbsj = fbsj;
	}

	@Column(name = "jhjssj", length = 19, nullable = true)
	public Date getJhjssj() {
		return jhjssj;
	}

	public void setJhjssj(Date jhjssj) {
		this.jhjssj = jhjssj;
	}

	@Column(name = "fbr", length = 100, nullable = true)
	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}

	@Column(name = "ztzhjg", length = 1000, nullable = true)
	public String getZtzhjg() {
		return ztzhjg;
	}

	public void setZtzhjg(String ztzhjg) {
		this.ztzhjg = ztzhjg;
	}

	@Column(name = "zt", length = 2, nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	 
}
