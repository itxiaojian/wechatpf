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
 * @since 2014-10-31
 */
@Entity
@Table(name = "sys_jscd")
public class SysJscd implements Serializable {

	/** jsbh */
	private String jsbh;

	/** cdbh */
	private String cdbh;

	@Id
	@Column(name = "jsbh", length = 36, nullable = false)
	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	@Id
	@Column(name = "cdbh", length = 36, nullable = false)
	public String getCdbh() {
		return cdbh;
	}

	public void setCdbh(String cdbh) {
		this.cdbh = cdbh;
	}

}
