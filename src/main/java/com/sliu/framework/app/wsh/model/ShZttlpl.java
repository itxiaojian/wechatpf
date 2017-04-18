package com.sliu.framework.app.wsh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 专题讨论评论
 * @author zhangyan
 * @version 创建时间：2016年7月14日  
 */
@Entity
@Table(name = "WSH_ZTTLPL")
public class ShZttlpl implements Serializable{
	
	 /** id */
	 private Long id;
	 
	 /** 专题id */
	 private Long ztid;
	 
	 /** 评论内容 */
	 private String plnr;
	 
	 /** 评论时间 */
	 private Date plsj;
	 
	 /** 评论人 */
	 private String plr;
	 
	 /** 评论人微信号 */
	 private String plrwxh;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ztid", length = 100, nullable = true)
	public Long getZtid() {
		return ztid;
	}

	public void setZtid(Long ztid) {
		this.ztid = ztid;
	}

	@Column(name = "plnr", length = 500, nullable = true)
	public String getPlnr() {
		return plnr;
	}

	public void setPlnr(String plnr) {
		this.plnr = plnr;
	}

	@Column(name = "plsj", length = 19, nullable = true)
	public Date getPlsj() {
		return plsj;
	}

	public void setPlsj(Date plsj) {
		this.plsj = plsj;
	}

	@Column(name = "plr", length = 100, nullable = true)
	public String getPlr() {
		return plr;
	}

	public void setPlr(String plr) {
		this.plr = plr;
	}

	@Column(name = "plrwxh", length = 100, nullable = true)
	public String getPlrwxh() {
		return plrwxh;
	}

	public void setPlrwxh(String plrwxh) {
		this.plrwxh = plrwxh;
	}

	 
}
