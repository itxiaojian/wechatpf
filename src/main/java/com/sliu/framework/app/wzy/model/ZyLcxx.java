package com.sliu.framework.app.wzy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 主页--流程信息
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:47:43
 */

@Entity
@Table(name = "ZY_LCXX")
public class ZyLcxx implements Serializable{

	private Long id;   //id
	
	private String lczl;  //流程种类
	
	private String lcmc;   //流程名称
	
	private String lcnr;  //流程内容
	
	private  Date shsj;  //审核时间
	
	private String shyj;  //审核意见
	
	private Date kssj;  //开始时间
	
	private Date jssj;  //结束时间
	
	private String shr; //审核人
	
	private String fqr;  //流程发起人
	
	private String fqrxm;  //发起人姓名
	
	private String bmmc;  //部门名称
	
	private String bmbh;  //发布部门
	
	private String lczt;  //流程状态
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "lczl", length = 50, nullable = true)
	public String getLczl() {
		return lczl;
	}

	public void setLczl(String lczl) {
		this.lczl = lczl;
	}

	@Column(name = "lcmc", length = 50, nullable = true)
	public String getLcmc() {
		return lcmc;
	}

	public void setLcmc(String lcmc) {
		this.lcmc = lcmc;
	}

	@Column(name = "lcnr", length = 200, nullable = true)
	public String getLcnr() {
		return lcnr;
	}

	public void setLcnr(String lcnr) {
		this.lcnr = lcnr;
	}
	
	@Column(name = "shsj", length = 19, nullable = true)
	public Date getShsj() {
		return shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	@Column(name = "shyj", length = 2000, nullable = true)
	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	@Column(name = "kssj", length = 19, nullable = true)
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	@Column(name = "jssj", length = 19, nullable = true)
	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}
	
	@Column(name = "shr", length = 36, nullable = true)
	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	@Column(name = "fqr", length = 36, nullable = true)
	public String getFqr() {
		return fqr;
	}

	public void setFqr(String fqr) {
		this.fqr = fqr;
	}

	@Column(name = "fqrxm", length = 20)
	public String getFqrxm() {
		return fqrxm;
	}

	public void setFqrxm(String fqrxm) {
		this.fqrxm = fqrxm;
	}
	
	@Column(name = "bmmc", length = 100)
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

	@Column(name = "lczt", length = 36, nullable = true)
	public String getLczt() {
		return lczt;
	}

	public void setLczt(String lczt) {
		this.lczt = lczt;
	}

}
