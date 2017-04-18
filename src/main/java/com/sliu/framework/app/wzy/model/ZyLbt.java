package com.sliu.framework.app.wzy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 主页轮播图
 * @author liujiansen
 * @since  2016-03-07
 */
@Entity
@Table(name="zy_lbt")
public class ZyLbt implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**图片名称*/
	private String tpmc;
	
	/**图片地址*/
	private String tpdz;
	
	/**图片类型*/
	private Integer tplx;
	
	/**上传时间*/
	private java.util.Date scsj;
	
	/**上传人*/
	private String scr;
	
	/**是否生效（0有效1无效）*/
	private Integer sfsx;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="TPMC", length=200, nullable=true)
	public String getTpmc() {
		return tpmc;
	}
	
	public void setTpmc(String tpmc) {
		this.tpmc = tpmc;
	}
	
	@Column(name="TPDZ", length=500, nullable=true)
	public String getTpdz() {
		return tpdz;
	}
	
	public void setTpdz(String tpdz) {
		this.tpdz = tpdz;
	}
	
	@Column(name="TPLX", length=10, nullable=true)
	public Integer getTplx() {
		return tplx;
	}
	
	public void setTplx(Integer tplx) {
		this.tplx = tplx;
	}
	
	@Column(name="SCSJ", length=19, nullable=true)
	public java.util.Date getScsj() {
		return scsj;
	}
	
	public void setScsj(java.util.Date scsj) {
		this.scsj = scsj;
	}
	
	@Column(name="SCR", length=36, nullable=true)
	public String getScr() {
		return scr;
	}
	
	public void setScr(String scr) {
		this.scr = scr;
	}
	
	@Column(name="SFSX", length=10, nullable=true)
	public Integer getSfsx() {
		return sfsx;
	}
	
	public void setSfsx(Integer sfsx) {
		this.sfsx = sfsx;
	}
	
}
