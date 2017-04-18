package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;



/**
 * 微上墙参与人
 * @author liujiansen
 * @since  2015-07-07
 */
@Entity
@Table(name="sh_wsqcyr")
public class ShWsqcyr implements Serializable{
	
	/**ID*/
	private Long id;
	
	/**活动编号*/
	private Long hdid;
	
	/**参与人openId*/
	private String openid;
	
	/**参与人名称*/
	private String cyrxm;
	
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
	
	@Column(name="HDID", length=19, nullable=true)
	public Long getHdid() {
		return hdid;
	}
	
	public void setHdid(Long hdid) {
		this.hdid = hdid;
	}
	
	@Column(name="OPENID", length=150, nullable=true)
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Column(name="CYRXM", length=100, nullable=true)
	public String getCyrxm() {
		return cyrxm;
	}
	
	public void setCyrxm(String cyrxm) {
		this.cyrxm = cyrxm;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
