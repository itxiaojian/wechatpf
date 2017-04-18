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
 * 校车时刻
 * @author duanpeijun
 * @version 创建时间：2015年6月9日
 */
@Entity
@Table(name = "SH_XCSK")
public class ShXcsk implements Serializable{

	private Long id;//主键
	
	private String cph;//车牌号
	
	private Date rq;//日期
	
	private Date cfsj;//出发时间
	
	private Date ddsj;//到达时间
	
	private String cfd;//出发地
	
	private String mdd;//目的地
	
	private Long flag;//标识
	
	private String fbr;//发布人
	
	private String fbrxm;//发布人姓名
	
	private String zt;//状态

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cph", length = 30, nullable = true)
	public String getCph() {
		return cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	@Column(name = "rq", length = 30, nullable = true)
	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	@Column(name = "cfsj", length = 30, nullable = true)
	public Date getCfsj() {
		return cfsj;
	}

	@Column(name = "ddsj", length = 30, nullable = true)
	public Date getDdsj() {
		return ddsj;
	}
	
	public void setDdsj(Date ddsj) {
		this.ddsj = ddsj;
	}

	
	public void setCfsj(Date cfsj) {
		this.cfsj = cfsj;
	}

	@Column(name = "cfd", length = 80, nullable = true)
	public String getCfd() {
		return cfd;
	}

	public void setCfd(String cfd) {
		this.cfd = cfd;
	}

	@Column(name = "mdd", length = 80, nullable = true)
	public String getMdd() {
		return mdd;
	}

	public void setMdd(String mdd) {
		this.mdd = mdd;
	}

	@Column(name = "flag", length = 2, nullable = true)
	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
	
	@Column(name = "fbr", length = 36, nullable = true)
	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}

	@Column(name = "fbrxm", length = 20, nullable = true)
	public String getFbrxm() {
		return fbrxm;
	}

	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}

	@Column(name = "zt", length = 2, nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
}
