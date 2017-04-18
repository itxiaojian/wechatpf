package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图书分类
 * @author duanpeijun
 * @version 创建时间：2015年6月9日  上午10:37:31
 */
@Entity
@Table(name = "ZS_TSFL")
public class ZsTsfl implements Serializable{
	
	private Long id;//主键
	
	private String zl;//种类
	
	private Integer sl;//数量
	
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

	@Column(name = "zl", nullable = true)
	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	@Column(name = "sl", nullable = true)
	public Integer getSl() {
		return sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
