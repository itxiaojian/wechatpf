package com.sliu.framework.app.wzy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 主页--录取信息
 * @author zhangyan
 * @version 创建时间：2016年7月4日  
 */

@Entity
@Table(name="GZ_LQXX")
public class ZyLqxx implements Serializable {
	
	/** id */
	private Long id; 
	
	/** 考生号 */
	private String ksh;
	
	/** 身份证号 */
	private String sfzh;
	
	/** 姓名 */
	private String xm;
	
	/** 录取专业 */
	private String lqzy;
	
	/** 总分 */
	private String zf;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ksh", length = 50, nullable = false)
	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}

	@Column(name = "sfzh", length = 50, nullable = false)
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	@Column(name = "xm", length = 50, nullable = false)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "lqzy", length = 50, nullable = false)
	public String getLqzy() {
		return lqzy;
	}

	public void setLqzy(String lqzy) {
		this.lqzy = lqzy;
	}

	@Column(name = "zf", length = 50, nullable = false)
	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}

}
