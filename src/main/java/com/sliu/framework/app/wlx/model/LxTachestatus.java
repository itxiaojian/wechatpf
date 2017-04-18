package com.sliu.framework.app.wlx.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 离校--办理记录表
 * @author oufeng
 * @version 创建时间：2016年6月8日  
 */
@Entity
@Table(name = "zs_lx_tachestatus")
public class LxTachestatus implements Serializable{
	 /** id */
	private Long id;
	
	 /** 流程ID */
	private Long tacheid;
	
	 /** 学号 */
	private String stuid;
	
	 /** 学生姓名 */
	private String stuname;
	
	 /** 学生院系*/
	private String xsyx;
	
	 /** 学生专业 */
	private String xszy;
	
	 /** 办理状态(1:通过；0:不通过) */
	private Integer blzt;
	
	 /** 办理时间 */
	private Date bltime;
	
	 /** 办理人ID */
	private String blrid;
	
	 /** 班里人姓名*/
	private String blrxm;
	
	 /** 未办理通过原因 */
	private String reason;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tacheid", length = 11, nullable = true)
	public Long getTacheid() {
		return tacheid;
	}

	public void setTacheid(Long tacheid) {
		this.tacheid = tacheid;
	}

	@Column(name = "stuid", length = 36, nullable = true)
	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	@Column(name = "stuname", length = 36, nullable = true)
	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	
	@Column(name = "xsyx", length = 60, nullable = true)
	public String getXsyx() {
		return xsyx;
	}

	public void setXsyx(String xsyx) {
		this.xsyx = xsyx;
	}

	@Column(name = "xszy", length = 60, nullable = true)
	public String getXszy() {
		return xszy;
	}

	public void setXszy(String xszy) {
		this.xszy = xszy;
	}

	@Column(name = "blzt", length = 1, nullable = true)
	public Integer getBlzt() {
		return blzt;
	}

	public void setBlzt(Integer blzt) {
		this.blzt = blzt;
	}

	@Column(name = "bltime",  nullable = true)
	public Date getBltime() {
		return bltime;
	}

	public void setBltime(Date bltime) {
		this.bltime = bltime;
	}

	@Column(name = "blrid", length = 36, nullable = true)
	public String getBlrid() {
		return blrid;
	}

	public void setBlrid(String blrid) {
		this.blrid = blrid;
	}

	@Column(name = "blrxm", length = 36, nullable = true)
	public String getBlrxm() {
		return blrxm;
	}

	public void setBlrxm(String blrxm) {
		this.blrxm = blrxm;
	}

	@Column(name = "reason", length = 512, nullable = true)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
