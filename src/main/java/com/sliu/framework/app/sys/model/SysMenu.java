package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 系统菜单
 * 
 * @author  sliu
 * @since 2016-08-02
 */

@Entity
@Table(name = "sys_cd")
public class SysMenu implements Serializable {
     private String cdbh;
     
     private String cdmc;
     
     private String cdurl;
     
     private Long sjcd;
     
     private Long cdjb;
     
     private String bz;
     
     private int px;


	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cdbh", nullable = false)
	public String getCdbh() {
		return cdbh;
	}

	public void setCdbh(String cdbh) {
		this.cdbh = cdbh;
	}

	@Column(name = "cdmc", length = 200, nullable = true)
	public String getCdmc() {
		return cdmc;
	}

	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}

	@Column(name = "cdurl", length = 200, nullable = true)
	public String getCdurl() {
		return cdurl;
	}

	public void setCdurl(String cdurl) {
		this.cdurl = cdurl;
	}

	@Column(name = "sjcd", nullable = true)
	public Long getSjcd() {
		return sjcd;
	}

	public void setSjcd(Long sjcd) {
		this.sjcd = sjcd;
	}

	@Column(name = "cdjb", nullable = true)
	public Long getCdjb() {
		return cdjb;
	}

	public void setCdjb(Long cdjb) {
		this.cdjb = cdjb;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name = "px", length = 11, nullable = true)
	public int getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}

}
