package com.sliu.framework.app.sbgl.model;

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
 * 设备维修记录
 * @author liujiansen
 * @since  2015-08-20
 */
@Entity
@Table(name="sb_sbwxjl")
public class SbSbwxjl implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**SBBH*/
	private String sbbh;
	
	/**1、紧急；2、重大；3、一般*/
	private String jjd;
	
	/**WXDH*/
	private String wxdh;
	
	/**WXSQR*/
	private String wxsqr;
	
	/**GZMS*/
	private String gzms;
	
	/**SXSJ*/
	private java.util.Date sxsj;
	
	/**WXDW*/
	private String wxdw;
	
	/**WXFZR*/
	private String wxfzr;
	
	/**1、进行中；2、已结束*/
	private String wxzt;
	
	/**1、大修；2、中修；3、部件维修；4、备件更换；5、例行修检*/
	private String wxjb;
	
	/**1、机械故障；2、电源故障；3、传动故障；4、润滑故障；5、散热故障*/
	private String gzlb;
	
	/**KSSJ*/
	private java.util.Date kssj;
	
	/**WCSJ*/
	private java.util.Date wcsj;
	
	/**WXFY*/
	private BigDecimal wxfy;
	
	/**GZFXJCL*/
	private String gzfxjcl;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="SBBH", length=32, nullable=true)
	public String getSbbh() {
		return sbbh;
	}
	
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	
	@Column(name="JJD", length=12, nullable=true)
	public String getJjd() {
		return jjd;
	}
	
	public void setJjd(String jjd) {
		this.jjd = jjd;
	}
	
	@Column(name="WXDH", length=32, nullable=true)
	public String getWxdh() {
		return wxdh;
	}
	
	public void setWxdh(String wxdh) {
		this.wxdh = wxdh;
	}
	
	@Column(name="WXSQR", length=32, nullable=true)
	public String getWxsqr() {
		return wxsqr;
	}
	
	public void setWxsqr(String wxsqr) {
		this.wxsqr = wxsqr;
	}
	
	@Column(name="GZMS", length=516, nullable=true)
	public String getGzms() {
		return gzms;
	}
	
	public void setGzms(String gzms) {
		this.gzms = gzms;
	}
	
	@Column(name="SXSJ", length=19, nullable=true)
	public java.util.Date getSxsj() {
		return sxsj;
	}
	
	public void setSxsj(java.util.Date sxsj) {
		this.sxsj = sxsj;
	}
	
	@Column(name="WXDW", length=32, nullable=true)
	public String getWxdw() {
		return wxdw;
	}
	
	public void setWxdw(String wxdw) {
		this.wxdw = wxdw;
	}
	
	@Column(name="WXFZR", length=32, nullable=true)
	public String getWxfzr() {
		return wxfzr;
	}
	
	public void setWxfzr(String wxfzr) {
		this.wxfzr = wxfzr;
	}
	
	@Column(name="WXZT", length=12, nullable=true)
	public String getWxzt() {
		return wxzt;
	}
	
	public void setWxzt(String wxzt) {
		this.wxzt = wxzt;
	}
	
	@Column(name="WXJB", length=12, nullable=true)
	public String getWxjb() {
		return wxjb;
	}
	
	public void setWxjb(String wxjb) {
		this.wxjb = wxjb;
	}
	
	@Column(name="GZLB", length=12, nullable=true)
	public String getGzlb() {
		return gzlb;
	}
	
	public void setGzlb(String gzlb) {
		this.gzlb = gzlb;
	}
	
	@Column(name="KSSJ", length=19, nullable=true)
	public java.util.Date getKssj() {
		return kssj;
	}
	
	public void setKssj(java.util.Date kssj) {
		this.kssj = kssj;
	}
	
	@Column(name="WCSJ", length=19, nullable=true)
	public java.util.Date getWcsj() {
		return wcsj;
	}
	
	public void setWcsj(java.util.Date wcsj) {
		this.wcsj = wcsj;
	}
	
	@Column(name="WXFY", length=10, nullable=true)
	public BigDecimal getWxfy() {
		return wxfy;
	}
	
	public void setWxfy(BigDecimal wxfy) {
		this.wxfy = wxfy;
	}
	
	@Column(name="GZFXJCL", length=516, nullable=true)
	public String getGzfxjcl() {
		return gzfxjcl;
	}
	
	public void setGzfxjcl(String gzfxjcl) {
		this.gzfxjcl = gzfxjcl;
	}
	
}
