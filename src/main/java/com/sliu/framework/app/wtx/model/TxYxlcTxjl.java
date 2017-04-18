package com.sliu.framework.app.wtx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tx_yxlctxjl")
public class TxYxlcTxjl {
	
	/** id */
	private Long id;
	
	/**流程号*/
	private String tacheid;
	
	/**学生学号*/
	private String stuid;
	
	/**学生姓名 */
	private String stuname;
	
	/** 办理状态 */
	private String blzt;
	
	/** 办理时间 */
	private String bltime;
	
	/** 微信号 */
	private String wxh;

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

	@Column(name = "wxh", length = 30, nullable = true)
	public String getWxh() {
		return wxh;
	}

	public void setWxh(String wxh) {
		this.wxh = wxh;
	}

	@Column(name = "bz", length=500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getTacheid() {
		return tacheid;
	}

	public void setTacheid(String tacheid) {
		this.tacheid = tacheid;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getBlzt() {
		return blzt;
	}

	public void setBlzt(String blzt) {
		this.blzt = blzt;
	}

	public String getBltime() {
		return bltime;
	}

	public void setBltime(String bltime) {
		this.bltime = bltime;
	}
	
}
