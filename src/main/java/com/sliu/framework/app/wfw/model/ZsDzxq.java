package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学期周次
 * @author oufeng
 * @date 2016年8月24日
 */
@Entity
@Table(name = "zs_dzxq")
public class ZsDzxq implements Serializable {
	
	//id
	private Long id;
	
	//学年
	private String xn;
	
	//学期
	private String xq;
	
	//当前周次
	private String dqzc;
	
	//开始时间
	private Date ksrq;
	
	//结束时间
	private Date jsrq;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "xn", length = 20, nullable = true)
	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	@Column(name = "xq", length = 20, nullable = true)
	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	@Column(name = "dqzc", length = 10, nullable = true)
	public String getDqzc() {
		return dqzc;
	}

	public void setDqzc(String dqzc) {
		this.dqzc = dqzc;
	}

	@Column(name = "ksrq", nullable = true)
	public Date getKsrq() {
		return ksrq;
	}

	public void setKsrq(Date ksrq) {
		this.ksrq = ksrq;
	}

	@Column(name = "jsrq", nullable = true)
	public Date getJsrq() {
		return jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}
}
