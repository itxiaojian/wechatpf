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
 * 微报名已报名人
 * @author liujiansen
 * @since  2015-06-25
 */
@Entity
@Table(name="sh_wbmybmr")
public class ShWbmybmr implements Serializable{
	
	/**id*/
	private Long id;
	
	/**报名人openid*/
	private String openid;
	
	/**报名id*/
	private Long bmid;
	
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
	
	@Column(name="OPENID", length=100, nullable=true)
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Column(name="BMID", length=19, nullable=true)
	public Long getBmid() {
		return bmid;
	}
	
	public void setBmid(Long bmid) {
		this.bmid = bmid;
	}
	
	@Column(name="BZ", length=516, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
