package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统角色和菜单关联
 * 
 * @author
 * @since 2015-04-09
 */
@Entity
@Table(name = "sys_yhjs")
public class SysYhjs implements Serializable {

	/** yhbh */
	private String yhbh;

	/** jsbh */
	private String jsbh;
	
	/** 备注*/
	private String bz;

	@Id
	@Column(name = "yhbh", length = 36, nullable = false)
	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	@Id
	@Column(name = "jsbh", length = 36, nullable = false)
	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	@Column(name = "bz", length = 200, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
