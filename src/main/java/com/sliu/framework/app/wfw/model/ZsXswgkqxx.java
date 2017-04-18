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
 * 学生晚归考勤信息
 * @author zhangyan
 * @version 创建时间：2016年8月8日
 */
@Entity
@Table(name = "ZS_XSWGKQXX")
public class ZsXswgkqxx implements Serializable{

	private Long id;//主键
	
	private String  xm;//姓名
	
	private String  xh;//学号
	
	private String bjmc;//班级名称
	
	private String bjbh;//班级编号
	
	private String ly;//楼宇
	
	private String fjh;//房间号
	
	private Date wgsj;//晚归时间
	
	private String djls;//登记老师
	
	private String djlsgh;//登记老师工号
	
	private String bz;//备注
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "xh", length = 32, nullable = true)
	public String getXh() {
		return xh;
	}
	
	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "xm", length = 36, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "bjmc", length = 60, nullable = true)
	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	@Column(name = "bjbh", length = 32, nullable = true)
	public String getBjbh() {
		return bjbh;
	}

	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}

	@Column(name = "ly", length = 50, nullable = true)
	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	@Column(name = "fjh", length = 36, nullable = true)
	public String getFjh() {
		return fjh;
	}

	public void setFjh(String fjh) {
		this.fjh = fjh;
	}

	@Column(name = "wgsj",length=19, nullable = true)
	public Date getWgsj() {
		return wgsj;
	}

	public void setWgsj(Date wgsj) {
		this.wgsj = wgsj;
	}

	@Column(name = "djls", length = 36, nullable = true)
	public String getDjls() {
		return djls;
	}

	public void setDjls(String djls) {
		this.djls = djls;
	}

	@Column(name = "djlsgh", length = 32, nullable = true)
	public String getDjlsgh() {
		return djlsgh;
	}

	public void setDjlsgh(String djlsgh) {
		this.djlsgh = djlsgh;
	}
	
	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
}
