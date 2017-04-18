package com.sliu.framework.app.bx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 报修审批意见表
 * @author liujiansen
 * @since  2015-08-06
 */
@Entity
@Table(name="bx_bxspyjb")
public class BxBxspyjb implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**报修申请ID*/
	private Long bxid;
	
	/**审批人编号*/
	private String sprbh;
	
	/**审批人姓名*/
	private String sprxm;
	
	/**审批是否通过*/
	private String sftg;
	
	/**审批意见*/
	private String spyj;
	
	/**审批时间*/
	private java.util.Date spsj;
	
	/**备注*/
	private String bz;
	
	/**状态*/
	private String zt;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="BXID", length=19, nullable=true)
	public Long getBxid() {
		return bxid;
	}
	
	public void setBxid(Long bxid) {
		this.bxid = bxid;
	}
	
	@Column(name="SPRBH", length=32, nullable=true)
	public String getSprbh() {
		return sprbh;
	}
	
	public void setSprbh(String sprbh) {
		this.sprbh = sprbh;
	}
	
	@Column(name="SPRXM", length=32, nullable=true)
	public String getSprxm() {
		return sprxm;
	}
	
	public void setSprxm(String sprxm) {
		this.sprxm = sprxm;
	}
	
	@Column(name="SFTG", length=2, nullable=true)
	public String getSftg() {
		return sftg;
	}
	
	public void setSftg(String sftg) {
		this.sftg = sftg;
	}
	
	@Column(name="SPYJ", length=516, nullable=true)
	public String getSpyj() {
		return spyj;
	}
	
	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}
	
	@Column(name="SPSJ", length=19, nullable=true)
	public java.util.Date getSpsj() {
		return spsj;
	}
	
	public void setSpsj(java.util.Date spsj) {
		this.spsj = spsj;
	}
	
	@Column(name="BZ", length=516, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name="ZT", length=4, nullable=true)
	public String getZt() {
		return zt;
	}
	
	public void setZt(String zt) {
		this.zt = zt;
	}
	
}
