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
 * 微报名已报名人
 * @author oufeng
 * @since  2015-07-06
 */
@Entity
@Table(name="sh_whbzt")
public class ShWhbZt implements Serializable{
	/**id*/
	private Long id;
	
	/**海报名称hbmc*/
	private String hbmc;
	
	/**背景音乐bjyy*/
	private String bjyy;
	
	/**海报封面 hbfm*/
	private String hbfm;
	
	/**创建人编号 cjrbh*/
	private String cjrbh;
	
	/**创建时间 cjsj*/
	private Date cjsj;
	
	/**备注 bz*/
	private String bz;
	
	/**状态zt 0:未发布1:已发布*/
	private String zt;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=200, nullable=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="HBMC", length=100, nullable=true)
	public String getHbmc() {
		return hbmc;
	}

	public void setHbmc(String hbmc) {
		this.hbmc = hbmc;
	}
	
	@Column(name="BJYY", length=100, nullable=true)
	public String getBjyy() {
		return bjyy;
	}

	public void setBjyy(String bjyy) {
		this.bjyy = bjyy;
	}
	
	@Column(name="HBFM", length=100, nullable=true)
	public String getHbfm() {
		return hbfm;
	}

	public void setHbfm(String hbfm) {
		this.hbfm = hbfm;
	}

	@Column(name="CJRBH", length=100, nullable=true)
	public String getCjrbh() {
		return cjrbh;
	}

	public void setCjrbh(String cjrbh) {
		this.cjrbh = cjrbh;
	}
	
	@Column(name="CJSJ", length=100, nullable=true)
	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	@Column(name="BZ", length=100, nullable=true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name="ZT", length=10, nullable=true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	
}
	