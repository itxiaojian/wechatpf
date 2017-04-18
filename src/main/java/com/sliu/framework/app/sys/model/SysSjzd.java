package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 数据字典
 * 
 * @author lxt
 * @since 2014-11-19 17:16:17
 */
@Entity
@Table(name = "sys_sjzd")
public class SysSjzd implements Serializable {

	/** id */
	private String id;

	/** 种类 */
	private String zl;

	/** 名称 */
	private String zdmc;

	/** 代码 */
	private String zdbm;

	/** 排序 */
	private Integer px;
	
	/** 字典值*/
	private String zdz;
	
	/** 级别（1级可作为2级的父级）*/
	private Integer jb;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "zl", length = 50, nullable = false)
	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	@Column(name = "zdmc", length = 200, nullable = false)
	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

	@Column(name = "zdbm", length = 100, nullable = false)
	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}

	@Column(name = "px", length = 10, nullable = true)
	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

	@Column(name = "zdz", length = 100, nullable = true)
	public String getZdz() {
		return zdz;
	}

	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	@Column(name = "jb", length = 10, nullable = false)
	public Integer getJb() {
		return jb;
	}

	public void setJb(Integer jb) {
		this.jb = jb;
	}

}
