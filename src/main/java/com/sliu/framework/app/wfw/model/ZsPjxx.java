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
 * 评教信息
 * @author duanpeijun
 * @version 创建时间：2015年6月12日
 */
@Entity
@Table(name = "zs_pjxx")
public class ZsPjxx implements Serializable {

	private Long id;//主键
	
	private Integer xh;//序号
	
	private String jsgh;//教师工号
	
	private String jsxm;//教师姓名
	
	private String rjkm;//任教科目
	
	private Date tjsj;//添加时间
	
	private String ksxn;//评教学年
	
	private String ksxq;//评教学期
	
	private String bz;//备注

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
	
	@Column(name = "xh", nullable = true)
	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	@Column(name = "jsgh", length = 30, nullable = true)
	public String getJsgh() {
		return jsgh;
	}

	public void setJsgh(String jsgh) {
		this.jsgh = jsgh;
	}

	@Column(name = "jsxm", length = 30, nullable = true)
	public String getJsxm() {
		return jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	@Column(name = "rjkm", length = 30, nullable = true)
	public String getRjkm() {
		return rjkm;
	}

	public void setRjkm(String rjkm) {
		this.rjkm = rjkm;
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
	
	@Column(name = "ksxn", length = 500, nullable = true)
	public String getKsxn() {
		return ksxn;
	}

	public void setKsxn(String ksxn) {
		this.ksxn = ksxn;
	}

	@Column(name = "ksxq", length = 500, nullable = true)
	public String getKsxq() {
		return ksxq;
	}

	public void setKsxq(String ksxq) {
		this.ksxq = ksxq;
	}
	
	@Column(name = "zt", length = 500, nullable = true)
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	
	
	
	
	
	
}
