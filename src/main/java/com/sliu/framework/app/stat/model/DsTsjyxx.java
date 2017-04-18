package com.sliu.framework.app.stat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zs_tsjyxx")
public class DsTsjyxx implements Serializable{
/**
*图书借阅信息
@Author oufeng	
@Date 2015年7月27日 下午4:11:31
@Version 1.0
*/
	/**主键id*/
	private  Long id ;
	
	/**学号工号*/
	private String bh;
	
	/**卡号*/
	private String kh;
	
	/**图书编号*/
	private String tsbh;
	
	/**图书名称*/
	private  String tsmc;
	
	/**借出时间*/
	private Date jcsj;
	
	/**归还时间*/
	private Date ghsj;
	
	private String xm;
	
	/**备注*/
	private String bz;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "bh", nullable = true)
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	@Column(name = "kh", nullable = true)
	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	@Column(name = "tsbh", nullable = true)
	public String getTsbh() {
		return tsbh;
	}

	public void setTsbh(String tsbh) {
		this.tsbh = tsbh;
	}

	@Column(name = "tsmc", nullable = true)
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

	@Column(name = "xm", nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "bz", nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
