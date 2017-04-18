package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 吐槽被赞
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "SH_ZPQDZ")
public class ShZpqdz implements Serializable {

	private Long id;// 主键

	private Long tczj;// 吐槽主键

	private String zr;// 赞人

	private String zrxm;// 赞人姓名

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tczj", nullable = true)
	public Long getTczj() {
		return tczj;
	}

	public void setTczj(Long tczj) {
		this.tczj = tczj;
	}

	@Column(name = "zr", nullable = true)
	public String getZr() {
		return zr;
	}

	public void setZr(String zr) {
		this.zr = zr;
	}

	@Column(name = "zrxm", nullable = true)
	public String getZrxm() {
		return zrxm;
	}

	public void setZrxm(String zrxm) {
		this.zrxm = zrxm;
	}

}
