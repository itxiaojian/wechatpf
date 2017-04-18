package com.sliu.framework.app.wtx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 提醒开关
 * @author oufeng
 * @date 2016年8月6日
 */
@Entity
@Table(name="tx_kg")
public class TxTxkg implements Serializable{
	
	/*主键*/
	private Long id;
	
	/*开关名称*/
	private String kgmc;
	
	/*开关种类*/
	private String kgzl;
	
	/*开关状态*/
	private String kgzt;
	
	/*备注*/
	private String bz;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=20, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="kgmc", length=32, nullable=true)
	public String getKgmc() {
		return kgmc;
	}

	public void setKgmc(String kgmc) {
		this.kgmc = kgmc;
	}

	@Column(name="kgzl", length=32, nullable=true)
	public String getKgzl() {
		return kgzl;
	}

	public void setKgzl(String kgzl) {
		this.kgzl = kgzl;
	}


	@Column(name="kgzt", length=12, nullable=true)
	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	@Column(name="bz", length=100, nullable=true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
