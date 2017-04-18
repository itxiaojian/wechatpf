package com.sliu.framework.app.stat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zs_tsfl")
public class DsTsfl implements Serializable {
/**
*
*图书分类
@Author oufeng	
@Date 2015年7月27日 下午4:04:07
@Version 1.0
*/
 
	/**主键id*/
	private Long id;
	
	/**图书种类*/
	private String zl;
	
	/**图书数量*/
	private Long sl;
	
	/**备注*/
	private String bz;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "zl", nullable = true)
	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	@Column(name = "sl", nullable = true)
	public Long getSl() {
		return sl;
	}

	public void setSl(Long sl) {
		this.sl = sl;
	}

	@Column(name = "bz", nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}