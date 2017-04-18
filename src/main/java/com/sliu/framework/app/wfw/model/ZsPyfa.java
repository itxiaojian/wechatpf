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
 * 培养方案
 * @author liujiasen
 * @date 2015年5月29日
 */
@Entity
@Table(name = "zs_pyfa")
public class ZsPyfa implements Serializable {

	/** id */
	private Long id;

	/** 教师工号 */
	private String jsgh;

	/** 方案标题 */
	private String fabt;

	/** 方案明细*/
	private String famx;

	/** 添加时间*/
	private Date tjsj;

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

	@Column(name = "jsgh", length = 30, nullable = true)
	public String getJsgh() {
		return jsgh;
	}

	public void setJsgh(String jsgh) {
		this.jsgh = jsgh;
	}

	@Column(name = "fabt", length = 100, nullable = true)
	public String getFabt() {
		return fabt;
	}

	public void setFabt(String fabt) {
		this.fabt = fabt;
	}

	@Column(name = "famx", length = 1000, nullable = true)
	public String getFamx() {
		return famx;
	}

	public void setFamx(String famx) {
		this.famx = famx;
	}

	@Column(name = "tjsj", nullable = true)
	public Date getTjsj() {
		return tjsj;
	}

	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
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
