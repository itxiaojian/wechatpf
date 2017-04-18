package com.sliu.framework.app.wzy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 吐槽回复
 * @author duanpeijun
 * @version 创建时间：2015年6月16日
 */
@Entity
@Table(name = "ZY_TCHF")
public class ZyTchf implements Serializable{

	private Long id;//主键
	
	private Long tczj;//吐槽主键
	
	private String hfnr;//回复内容
	
	private Date hfsj;//回复时间
	
	private Date gqsj;//过期时间
	
	private String hfr;//回复人
	
	private String hfrxm;//回复人姓名
	
	private String bmmc;//部门名称
	
	private String bmbh;//发布部门
	
	private String zt;//状态

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tczj", nullable = false)
	public Long getTczj() {
		return tczj;
	}

	public void setTczj(Long tczj) {
		this.tczj = tczj;
	}

	@Column(name = "hfnr", length = 8000, nullable = true)
	public String getHfnr() {
		return hfnr;
	}

	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}

	@Column(name = "hfsj", length = 19, nullable = true)
	public Date getHfsj() {
		return hfsj;
	}

	public void setHfsj(Date hfsj) {
		this.hfsj = hfsj;
	}

	@Column(name = "gqsj", length = 19, nullable = true)
	public Date getGqsj() {
		return gqsj;
	}

	public void setGqsj(Date gqsj) {
		this.gqsj = gqsj;
	}

	@Column(name = "hfr", length = 36, nullable = true)
	public String getHfr() {
		return hfr;
	}

	public void setHfr(String hfr) {
		this.hfr = hfr;
	}

	@Column(name = "hfrxm", length = 20,nullable = true)
	public String getHfrxm() {
		return hfrxm;
	}

	public void setHfrxm(String hfrxm) {
		this.hfrxm = hfrxm;
	}

	@Column(name = "bmmc", length = 100,nullable = true)
	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	@Column(name = "bmbh", length = 100,nullable = true)
	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	@Column(name = "zt", length = 100,nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	
	
	
	
	
	
	
}
