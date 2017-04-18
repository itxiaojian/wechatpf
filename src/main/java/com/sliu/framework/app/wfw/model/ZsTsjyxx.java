package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图书借阅信息
 * @author liujiasen
 * @date 2015年5月21日
 */
@Entity
@Table(name = "zs_tsjyxx")
public class ZsTsjyxx implements Serializable {

	/** id */
	private Long id;

	/** 学号工号 */
	private String bh;

	/** 卡号 */
	private String kh;

	/** 图书编号 */
	private String tsbh;

	/** 图书名称 */
	private String tsmc;

	/** 借出时间*/
	private Date jcsj;

	/** 归还时间*/
	private Date ghsj;

	/** 应归还时间*/
	private Date yghsj;

	
	/** 备注 */
	private String bz;
	
	private String xm;//姓名

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "bh", length = 30, nullable = true)
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	@Column(name = "kh", length = 30, nullable = true)
	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	@Column(name = "tsbh", length = 30, nullable = true)
	public String getTsbh() {
		return tsbh;
	}

	public void setTsbh(String tsbh) {
		this.tsbh = tsbh;
	}

	@Column(name = "tsmc", length = 150, nullable = true)
	public String getTsmc() {
		return tsmc;
	}

	public void setTsmc(String tsmc) {
		this.tsmc = tsmc;
	}

	@Column(name = "jcsj", nullable = true)
	public Date getJcsj() {
		return jcsj;
	}

	public void setJcsj(Date jcsj) {
		this.jcsj = jcsj;
	}

	@Column(name = "ghsj", nullable = true)
	public Date getGhsj() {
		return ghsj;
	}

	public void setGhsj(Date ghsj) {
		this.ghsj = ghsj;
	}

	@Column(name = "yghsj", nullable = true)
	public Date getYghsj() {
		return yghsj;
	}

	public void setYghsj(Date yghsj) {
		this.yghsj = yghsj;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name = "xm", length = 32, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
}
