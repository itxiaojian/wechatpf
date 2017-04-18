package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 失误招领
 * @author liujiansen
 * @since  2015-06-16
 */
@Entity
@Table(name="sh_swzl")
public class ShSwzl implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**1、遗失；2、招领*/
	private String lx;
	
	/**物品详细描述*/
	private String xwzs;
	
	/** 标题 */
	private String bt;
	
	/**发起时间*/
	private java.util.Date fqsj;
	
	/**结束时间*/
	private java.util.Date jssj;
	
	/**发布人*/
	private String fbr;
	
	/**发布人姓名*/
	private String fbrxm;
	
	/**1、正常；2、结束*/
	private String xwzt;
	
	/**地点 */
	private String dd;
	
	/**一卡通卡号*/
	private String yktkh;
	
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
	
	@Column(name="LX", length=2, nullable=true)
	public String getLx() {
		return lx;
	}
	
	public void setLx(String lx) {
		this.lx = lx;
	}
	
	@Column(name="BT", length=100, nullable=true)
	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	@Column(name="XWZS", length=400, nullable=true)
	public String getXwzs() {
		return xwzs;
	}
	
	public void setXwzs(String xwzs) {
		this.xwzs = xwzs;
	}
	
	@Column(name="FQSJ", length=19, nullable=true)
	public java.util.Date getFqsj() {
		return fqsj;
	}
	
	public void setFqsj(java.util.Date fqsj) {
		this.fqsj = fqsj;
	}
	
	@Column(name="JSSJ", length=19, nullable=true)
	public java.util.Date getJssj() {
		return jssj;
	}
	
	public void setJssj(java.util.Date jssj) {
		this.jssj = jssj;
	}
	
	@Column(name="FBR", length=200, nullable=true)
	public String getFbr() {
		return fbr;
	}
	
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	
	@Column(name="FBRXM", length=20, nullable=true)
	public String getFbrxm() {
		return fbrxm;
	}
	
	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}
	
	@Column(name="XWZT", length=2, nullable=true)
	public String getXwzt() {
		return xwzt;
	}
	
	public void setXwzt(String xwzt) {
		this.xwzt = xwzt;
	}

	@Column(name="DD", length=200, nullable=true)
	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}
	
	@Column(name="yktkh", length=100, nullable=true)
	public String getYktkh() {
		return yktkh;
	}

	public void setYktkh(String yktkh) {
		this.yktkh = yktkh;
	}

	@Column(name="BZ", length=512, nullable=true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
