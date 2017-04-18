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
 * 菜单
 * @author:wangchunlin
 * @version 创建时间：2016年1月12日  
 * @return
 */

@Entity
@Table(name = "ZY_ZYCDPZ")
public class ZyCdpz implements Serializable {
//	 主键ID
	private Long id;
//	 排序
	private Integer px;
//	菜单名称
	private String cdmc;
//	菜单图标名称
	private String cdtbmc;
//	菜单url
	private String cdurl;
//	是否启用
	private String sfqy;
//	添加人
	private String tjr;
//	添加时间
	private Date tjsj;
//	添加人
	private String mklx;
	
//	生成get、set方法
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "cdmc", length = 50, nullable = true)
	public String getCdmc() {
		return cdmc;
	}
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
	@Column(name = "cdtbmc", length = 200, nullable = true)
	public String getCdtbmc() {
		return cdtbmc;
	}
	public void setCdtbmc(String cdtbmc) {
		this.cdtbmc = cdtbmc;
	}
	@Column(name = "cdurl", length = 500, nullable = true)
	public String getCdurl() {
		return cdurl;
	}
	public void setCdurl(String cdurl) {
		this.cdurl = cdurl;
	}
	@Column(name = "sfqy", length = 2, nullable = true)
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	@Column(name = "tjr", length = 32, nullable = true)
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	@Column(name = "tjsj", length = 19, nullable = true)
	public Date getTjsj() {
		return tjsj;
	}
	public void setTjsj(Date date) {
		this.tjsj = date;
	}
	
	@Column(name = "px", nullable = true)
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	
	@Column(name = "mklx", length = 2, nullable = true)
	public String getMklx() {
		return mklx;
	}
	public void setMklx(String mklx) {
		this.mklx = mklx;
	}
	

}
