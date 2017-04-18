package com.sliu.framework.app.wsh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sh_whbfj")
public class ShWhbFJ implements Serializable{

/**
*
*
@Author oufeng	
@Date 2015年7月6日 下午12:23:13
@Version 1.0

*/
	/**id*/
	private Long id;
	
	/**附件类型*/
	private String fjlx;

	/**附件路径*/
	private String fjlj;
	
	/**附件名称*/
	private String fjmc;
	
	/**添加时间*/
	private Date tjsj;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=100, nullable=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="FJLX", length=2, nullable=true)
	public String getFjlx() {
		return fjlx;
	}

	public void setFjlx(String fjlx) {
		this.fjlx = fjlx;
	}

	@Column(name="FJLJ", length=200, nullable=true)
	public String getFjlj() {
		return fjlj;
	}

	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}

	@Column(name="FJMC", length=36, nullable=true)
	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	@Column(name="TJSJ", length=100, nullable=true)
	public Date getTjsj() {
		return tjsj;
	}

	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
	}
	
	
	
	
	
}
