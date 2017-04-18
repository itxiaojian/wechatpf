package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

/**
 * 系统角色
 * 
 * @author 
 * @since 2015-04-09
 */
@Entity
@Table(name = "sys_js")
public class SysJs implements Serializable, GrantedAuthority {

	/** jsbh */
	private String jsbh;

	/** jsmc */
	private String jsmc;

	/** bz */
	private String bz;

	/** 是否生效(0:无效,1:有效) */
	private Integer jszt;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "jsbh", length = 36, nullable = false)
	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	@Column(name = "jsmc", length = 200, nullable = false)
	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	@Column(name = "bz", length = 200, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "jszt", length = 10, nullable = true)
	public Integer getJszt() {
		return jszt;
	}

	public void setJszt(Integer jszt) {
		this.jszt = jszt;
	}

	@Transient
	public String getAuthority() {
		return this.jsmc;
	}

}
