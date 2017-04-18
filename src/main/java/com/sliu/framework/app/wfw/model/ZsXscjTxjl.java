package com.sliu.framework.app.wfw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zs_xscj_txjl")
public class ZsXscjTxjl {
	/** id */
	private Long id;

	/** 考试学年 */
	private String ksxn;

	/** 考试学期 */
	private String ksxq;

	/** 学号 */
	private String xsxh;

	/** 课程编号*/
	private String kcbh;

	/** 考试成绩*/
	private String kscj;

	/** 是否通过(0不通过;1通过) */
	//private String sftg;
	
	/** 补考成绩*/
	private String sftx;
	
	/** 微信号 */
	private String wxh;
	
	/** 提醒时间 */
	private Date txsj;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ksxn",length=10, nullable = true)
	public String getKsxn() {
		return ksxn;
	}

	public void setKsxn(String ksxn) {
		this.ksxn = ksxn;
	}

	@Column(name = "ksxq",length=10, nullable = true)
	public String getKsxq() {
		return ksxq;
	}

	public void setKsxq(String ksxq) {
		this.ksxq = ksxq;
	}

	@Column(name = "xsxh",length=10, nullable = true)
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	@Column(name = "kcbh",length=100, nullable = true)
	public String getKcbh() {
		return kcbh;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	@Column(name = "kscj",length=200, nullable = true)
	public String getKscj() {
		return kscj;
	}

	public void setKscj(String kscj) {
		this.kscj = kscj;
	}

/*	@Column(name = "sftg",length=2, nullable = true)
	public String getSftg() {
		return sftg;
	}

	public void setSftg(String sftg) {
		this.sftg = sftg;
	}*/

	@Column(name = "sftx",length=2, nullable = true)
	public String getSftx() {
		return sftx;
	}

	public void setSftx(String sftx) {
		this.sftx = sftx;
	}

	@Column(name = "wxh",length=100, nullable = true)
	public String getWxh() {
		return wxh;
	}

	public void setWxh(String wxh) {
		this.wxh = wxh;
	}

	@Column(name = "txsj", nullable = true)
	public Date getTxsj() {
		return txsj;
	}

	public void setTxsj(Date txsj) {
		this.txsj = txsj;
	}
	
	
}