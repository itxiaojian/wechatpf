package com.sliu.framework.app.xzxx.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 校长信箱-信件信息表
 * @author liujiansen
 * @since  2015-09-01
 */
@Entity
@Table(name="xx_xjxxb")
public class XxXjxxb implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**信件标题*/
	private String xjbt;
	
	/**处理部门*/
	private String clbm;
	
	/**1、审批中；2、审批不通过退回；3‘、审批通过；4、未回复；5、回复未评价；6、已评价结束；7、禁用*/
	private String clzt;
	
	/**发件人姓名*/
	private String fjrxm;
	
	/**发件人编号*/
	private String fjrbh;
	
	/**受理部门编号*/
	private String slbmbh;
	
	/**受理部门名称*/
	private String slbmmc;
	
	/**通讯地址*/
	private String txdz;
	
	/**联系电话*/
	private String lxdh;
	
	/**写信时间*/
	private java.util.Date xxsj;
	
	/**信件内容*/
	private String xjnr;
	
	/**审核时间*/
	private java.util.Date shsj;
	
	/**审核人*/
	private String shr;
	
	/**处理结果*/
	private String cljg;
	
	/**处理人*/
	private String clr;
	
	/**处理时间*/
	private java.util.Date clsj;
	
	/**评价*/
	private String pj;
	
	/**备注*/
	private String bz;
	
	/**满意度*/
	private Integer myd;
	
	/**ip地址*/
	private String ipdz;
	
	/**退回理由*/
	private String thly;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="XJBT", length=1024, nullable=true)
	public String getXjbt() {
		return xjbt;
	}
	
	public void setXjbt(String xjbt) {
		this.xjbt = xjbt;
	}
	
	@Column(name="CLBM", length=32, nullable=true)
	public String getClbm() {
		return clbm;
	}
	
	public void setClbm(String clbm) {
		this.clbm = clbm;
	}
	
	@Column(name="CLZT", length=2, nullable=true)
	public String getClzt() {
		return clzt;
	}
	
	public void setClzt(String clzt) {
		this.clzt = clzt;
	}
	
	@Column(name="FJRXM", length=32, nullable=true)
	public String getFjrxm() {
		return fjrxm;
	}
	
	public void setFjrxm(String fjrxm) {
		this.fjrxm = fjrxm;
	}
	
	@Column(name="FJRBH", length=32, nullable=true)
	public String getFjrbh() {
		return fjrbh;
	}
	
	public void setFjrbh(String fjrbh) {
		this.fjrbh = fjrbh;
	}
	
	@Column(name="SLBMBH", length=32, nullable=true)
	public String getSlbmbh() {
		return slbmbh;
	}
	
	public void setSlbmbh(String slbmbh) {
		this.slbmbh = slbmbh;
	}
	
	@Column(name="SLBMMC", length=32, nullable=true)
	public String getSlbmmc() {
		return slbmmc;
	}
	
	public void setSlbmmc(String slbmmc) {
		this.slbmmc = slbmmc;
	}
	
	@Column(name="TXDZ", length=300, nullable=true)
	public String getTxdz() {
		return txdz;
	}
	
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	
	@Column(name="LXDH", length=32, nullable=true)
	public String getLxdh() {
		return lxdh;
	}
	
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@Column(name="XXSJ", length=10, nullable=true)
	public java.util.Date getXxsj() {
		return xxsj;
	}
	
	public void setXxsj(java.util.Date xxsj) {
		this.xxsj = xxsj;
	}
	
	@Column(name="XJNR",  length=65533,nullable=true)
	public String getXjnr() {
		return xjnr;
	}
	
	public void setXjnr(String xjnr) {
		this.xjnr = xjnr;
	}
	
	@Column(name="SHSJ", length=10, nullable=true)
	public java.util.Date getShsj() {
		return shsj;
	}
	
	public void setShsj(java.util.Date shsj) {
		this.shsj = shsj;
	}
	
	@Column(name="SHR", length=32, nullable=true)
	public String getShr() {
		return shr;
	}
	
	public void setShr(String shr) {
		this.shr = shr;
	}
	
	@Column(name="CLJG", length=65533, nullable=true)
	public String getCljg() {
		return cljg;
	}
	
	public void setCljg(String cljg) {
		this.cljg = cljg;
	}
	
	@Column(name="CLR", length=32, nullable=true)
	public String getClr() {
		return clr;
	}

	public void setClr(String clr) {
		this.clr = clr;
	}
	
	@Column(name="CLSJ", length=10, nullable=true)
	public java.util.Date getClsj() {
		return clsj;
	}
	
	public void setClsj(java.util.Date clsj) {
		this.clsj = clsj;
	}
	
	@Column(name="PJ", length=1024, nullable=true)
	public String getPj() {
		return pj;
	}
	
	public void setPj(String pj) {
		this.pj = pj;
	}
	
	@Column(name="BZ", length=512, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name="MYD", length=2, nullable=true)
	public Integer getMyd() {
		return myd;
	}

	public void setMyd(Integer myd) {
		this.myd = myd;
	}

	@Column(name="IPDZ", length=32, nullable=true)
	public String getIpdz() {
		return ipdz;
	}

	public void setIpdz(String ipdz) {
		this.ipdz = ipdz;
	}

	@Column(name="THLY", length=512, nullable=true)
	public String getThly() {
		return thly;
	}

	public void setThly(String thly) {
		this.thly = thly;
	}
}
