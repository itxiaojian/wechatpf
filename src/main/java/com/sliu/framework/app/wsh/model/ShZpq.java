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
 * 照片墙
 * @author wangxiangyang
 * @version 创建时间：2016年9月8日
 */
@Entity
@Table(name = "SH_ZPQ")
public class ShZpq implements Serializable{

	private Long id;
	
	private String tcbt;//吐槽标题
	
	private String tczs;//吐槽注释
	
	private String tcnr;//吐槽内容
	
	private Date tcsj;//吐槽时间
	
	private Date gqsj;//过期时间
	
	private String tcr;//吐槽人
	
	private String tcrxm;//吐槽人姓名
	
	private String bmmc;//部门名称
	
	private String bmbh;//发布部门
	
	private String zt;//状态	
	
	private String bz1;//备注
	
	private String bz2;//备注
	
	private String bz3;//备注
	
	private String bz4;//备注
	
	private String bz5;//备注
	
	private String bz6;//备注
	
	private Integer bzcs;//被赞次数
	
	private Integer hfcs;//回复次数
	
	private String txdz;//头像地址

	private Integer tclx;//吐槽类型


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tcbt", length = 50, nullable = true)
	public String getTcbt() {
		return tcbt;
	}

	public void setTcbt(String tcbt) {
		this.tcbt = tcbt;
	}

	@Column(name = "tczs", length = 200, nullable = true)
	public String getTczs() {
		return tczs;
	}

	public void setTczs(String tczs) {
		this.tczs = tczs;
	}

	@Column(name = "tcnr", length = 8000, nullable = true)
	public String getTcnr() {
		return tcnr;
	}

	public void setTcnr(String tcnr) {
		this.tcnr = tcnr;
	}

	@Column(name = "tcsj", length = 19, nullable = true)
	public Date getTcsj() {
		return tcsj;
	}

	public void setTcsj(Date tcsj) {
		this.tcsj = tcsj;
	}

	@Column(name = "gqsj", length = 19, nullable = true)
	public Date getGqsj() {
		return gqsj;
	}

	public void setGqsj(Date gqsj) {
		this.gqsj = gqsj;
	}

	@Column(name = "tcr", length = 36, nullable = true)
	public String getTcr() {
		return tcr;
	}

	public void setTcr(String tcr) {
		this.tcr = tcr;
	}

	@Column(name = "tcrxm", length = 20,nullable = true)
	public String getTcrxm() {
		return tcrxm;
	}

	public void setTcrxm(String tcrxm) {
		this.tcrxm = tcrxm;
	}

	@Column(name = "bmmc", length = 100,nullable = true)
	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	@Column(name = "bmbh", length = 36, nullable = true)
	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	@Column(name = "zt", length = 2, nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	

	@Column(name = "bzcs", nullable = true)
	public Integer getBzcs() {
		return bzcs;
	}

	public void setBzcs(Integer bzcs) {
		this.bzcs = bzcs;
	}

	@Column(name = "hfcs", nullable = true)
	public Integer getHfcs() {
		return hfcs;
	}

	public void setHfcs(Integer hfcs) {
		this.hfcs = hfcs;
	}
	
	@Column(name = "txdz", length = 300, nullable = true)
	public String getTxdz() {
		return txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	
	@Column(name = "tclx", length = 300, nullable = true)
	public Integer getTclx() {
		return tclx;
	}

	public void setTclx(Integer tclx) {
		this.tclx = tclx;
	}

	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	public String getBz2() {
		return bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	public String getBz3() {
		return bz3;
	}

	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}

	public String getBz4() {
		return bz4;
	}

	public void setBz4(String bz4) {
		this.bz4 = bz4;
	}

	public String getBz5() {
		return bz5;
	}

	public void setBz5(String bz5) {
		this.bz5 = bz5;
	}

	public String getBz6() {
		return bz6;
	}

	public void setBz6(String bz6) {
		this.bz6 = bz6;
	}
}
