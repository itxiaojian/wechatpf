package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 二手市场
 * @author liujiansen
 * @since  2015-06-30
 */
@Entity
@Table(name="sh_essc")
public class ShEssc implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**商品编号*/
	private String spbh;
	
	/**商品名称*/
	private String spmc;
	
	/**商品照片*/
	private String spzp;
	
	/**价格*/
	private String jg;
	
	/**商品描述*/
	private String spms;
	
	/**物主姓名*/
	private String wzxm;
	
	/**物主编号*/
	private String wzbh;
	
	/**商品类型*/
	private String splx;
	
	/**联系方式*/
	private String lxfs;
	
	/**1、上架；2、下架；3、已售*/
	private String wpzt;
	
	/**上架时间*/
	private java.util.Date sjsj;
	
	/**下架时间*/
	private java.util.Date xjsj;
	
	/**售出时间*/
	private java.util.Date scsj;
	
	/**备注*/
	private String bz;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="SPBH", length=64, nullable=true)
	public String getSpbh() {
		return spbh;
	}
	
	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}
	
	@Column(name="SPMC", length=200, nullable=true)
	public String getSpmc() {
		return spmc;
	}
	
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	
	@Column(name="SPZP", length=600, nullable=true)
	public String getSpzp() {
		return spzp;
	}
	
	public void setSpzp(String spzp) {
		this.spzp = spzp;
	}
	
	@Column(name="JG", length=32, nullable=true)
	public String getJg() {
		return jg;
	}
	
	public void setJg(String jg) {
		this.jg = jg;
	}
	
	@Column(name="SPMS", length=512, nullable=true)
	public String getSpms() {
		return spms;
	}
	
	public void setSpms(String spms) {
		this.spms = spms;
	}
	
	@Column(name="WZXM", length=100, nullable=true)
	public String getWzxm() {
		return wzxm;
	}
	
	public void setWzxm(String wzxm) {
		this.wzxm = wzxm;
	}
	
	@Column(name="WZBH", length=32, nullable=true)
	public String getWzbh() {
		return wzbh;
	}
	
	public void setWzbh(String wzbh) {
		this.wzbh = wzbh;
	}
	
	@Column(name="SPLX", length=3, nullable=true)
	public String getSplx() {
		return splx;
	}
	
	public void setSplx(String splx) {
		this.splx = splx;
	}
	
	@Column(name="LXFS", length=12, nullable=true)
	public String getLxfs() {
		return lxfs;
	}
	
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	
	@Column(name="WPZT", length=3, nullable=true)
	public String getWpzt() {
		return wpzt;
	}
	
	public void setWpzt(String wpzt) {
		this.wpzt = wpzt;
	}
	
	@Column(name="SJSJ", length=19, nullable=true)
	public java.util.Date getSjsj() {
		return sjsj;
	}
	
	public void setSjsj(java.util.Date sjsj) {
		this.sjsj = sjsj;
	}
	
	@Column(name="XJSJ", length=19, nullable=true)
	public java.util.Date getXjsj() {
		return xjsj;
	}
	
	public void setXjsj(java.util.Date xjsj) {
		this.xjsj = xjsj;
	}
	
	@Column(name="SCSJ", length=19, nullable=true)
	public java.util.Date getScsj() {
		return scsj;
	}
	
	public void setScsj(java.util.Date scsj) {
		this.scsj = scsj;
	}
	
	@Column(name="BZ", length=256, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
