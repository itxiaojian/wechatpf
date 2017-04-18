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
 * 讲座报告
 * @author zhangyan
 * @version 创建时间：2016年8月1日  
 */
@Entity
@Table(name = "WSH_JZBG")
public class ShJzbg implements Serializable{
	
	 /** id */
	 private Long id;
	 
	 /** 讲座名称 */
	 private String jzmc;
	 
	 /** 讲座标题 */
	 private String jzbt;
	 
	 /** 讲座内容 */
	 private String jznr;
	 
	 /** 发布时间 */
	 private Date jzsj;
	 
	 /** 发布人 */
	 private String fbr;
	 
	 /** 备注 */
	 private String bz;
	 
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

	@Column(name = "jzmc", length = 100, nullable = true)
	public String getJzmc() {
		return jzmc;
	}

	public void setJzmc(String jzmc) {
		this.jzmc = jzmc;
	}

	@Column(name = "jzbt", length = 100, nullable = true)
	public String getJzbt() {
		return jzbt;
	}

	public void setJzbt(String jzbt) {
		this.jzbt = jzbt;
	}

	@Column(name = "jznr", length = 2000, nullable = true)
	public String getJznr() {
		return jznr;
	}

	public void setJznr(String jznr) {
		this.jznr = jznr;
	}

	@Column(name = "jzsj", length = 19, nullable = true)
	public Date getJzsj() {
		return jzsj;
	}

	public void setJzsj(Date jzsj) {
		this.jzsj = jzsj;
	}

	@Column(name = "fbr", length = 100, nullable = true)
	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	
	@Column(name = "bz", length = 100, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name = "zt", length = 2, nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
