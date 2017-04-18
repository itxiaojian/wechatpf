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
 * 教学成果
 * @author liujiasen
 * @date 2015年6月1日
 */
@Entity
@Table(name = "zs_jxcg")
public class ZsJxcg implements Serializable {

	/** id */
	private Long id;

	/** 成果编号 */
	private String cgbh;

	/** 成果名称 */
	private String cgmc;

	/** 奖励类别*/
	private String lb;
	
	/** 颁奖单位*/
	private String bm;
	
	/** 完成人工号*/
	private String gh;
	
	/** 完成人姓名*/
	private String xm;

	/** 获奖时间*/
	private Date hjsj;
	
	/** 获奖年份*/
	private String zzjf;

	/** 备注 */
	private String bz;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cgbh", length = 30, nullable = true)
	public String getCgbh() {
		return cgbh;
	}

	public void setCgbh(String cgbh) {
		this.cgbh = cgbh;
	}

	@Column(name = "cgmc", length = 60, nullable = true)
	public String getCgmc() {
		return cgmc;
	}

	public void setCgmc(String cgmc) {
		this.cgmc = cgmc;
	}

	@Column(name = "lb", length = 36, nullable = true)
	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	@Column(name = "bm", length = 36, nullable = true)
	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	@Column(name = "gh", length = 36, nullable = true)
	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	@Column(name = "xm", length = 50, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "hjsj", nullable = true)
	public Date getHjsj() {
		return hjsj;
	}

	public void setHjsj(Date hjsj) {
		this.hjsj = hjsj;
	}

	@Column(name = "zzjf", length = 500, nullable = true)
	public String getZzjf() {
		return zzjf;
	}

	public void setZzjf(String zzjf) {
		this.zzjf = zzjf;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
