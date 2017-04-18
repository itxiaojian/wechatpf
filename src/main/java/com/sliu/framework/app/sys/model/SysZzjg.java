package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 组织机构
 * 
 * @author lxt
 * @since 2014-11-19 17:16:17
 */
@Entity
@Table(name = "sys_zzjg")
public class SysZzjg implements Serializable {

	/** id */
	private String id;

	/** 上级编号 */
	private String sjbh;

	/** 部门编号*/
	private String bmbh;

	/** 部门名称 */
	private String bmmc;

	/** 排序 */
	private Integer px;
	
	/** 级别(1级为公司；2级为部门；3级为岗位)*/
	private Integer jb;
	
	/** 备注*/
	private String bz;

	@Id
	@Column(name = "id", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "sjbh", length = 36, nullable = false)
	public String getSjbh() {
		return sjbh;
	}

	public void setSjbh(String sjbh) {
		this.sjbh = sjbh;
	}

	@Column(name = "bmbh", length = 50, nullable = false)
	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	@Column(name = "bmmc", length = 256, nullable = false)
	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	@Column(name = "px", length = 11, nullable = false)
	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

	@Column(name = "jb", nullable = false)
	public Integer getJb() {
		return jb;
	}

	public void setJb(Integer jb) {
		this.jb = jb;
	}

	@Column(name = "bz", length = 200, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}