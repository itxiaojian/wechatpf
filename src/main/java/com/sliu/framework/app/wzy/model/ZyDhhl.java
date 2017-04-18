package com.sliu.framework.app.wzy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 主页--  电话黄历
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:47:35
 */

@Entity
@Table(name = "ZY_DHHL")
public class ZyDhhl implements Serializable{

    private Long id;   //编号
	
	private String bmmc;  // 名称
	
	private String dhhm;  //电话号码
	
	private String bz;  //备注

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "bmmc", length = 50, nullable = true)
	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	@Column(name = "dhhm", length = 50, nullable = true)
	public String getDhhm() {
		return dhhm;
	}

	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}

	@Column(name = "bz", length = 200, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
