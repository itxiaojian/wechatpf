package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 微报名人信息填写记录
 * @author liujiansen
 * @since  2015-06-25
 */
@Entity
@Table(name="sh_bmrxxtxjl")
public class ShBmrxxtxjl implements Serializable{
	
	/**id*/
	private Long id;
	
	/**已报名人id*/
	private Long ybmrid;
	
	/**标题*/
	private String bt;
	
	/**内容*/
	private String nr;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="YBMRID", length=19, nullable=true)
	public Long getYbmrid() {
		return ybmrid;
	}
	
	public void setYbmrid(Long ybmrid) {
		this.ybmrid = ybmrid;
	}
	
	@Column(name="BT", length=100, nullable=true)
	public String getBt() {
		return bt;
	}
	
	public void setBt(String bt) {
		this.bt = bt;
	}
	
	@Column(name="NR", length=516, nullable=true)
	public String getNr() {
		return nr;
	}
	
	public void setNr(String nr) {
		this.nr = nr;
	}
	
}
