package com.sliu.framework.app.wtx.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * OA的待办事项
 * @author oufeng
 * @date 2016年8月4日
 */
@Entity
@Table(name="zs_oadbsx")
public class TxOaDbsx implements Serializable{
	private Long id;
	private Long lxid;
	private String title;
	private Date time;
	private String url;
	private String readed;
	private String yhbh;
	private String bz;
	
	public TxOaDbsx() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TxOaDbsx(Long id, Long lxid, String title, Date time, String url,
			String readed, String yhbh, String bz) {
		super();
		this.id = id;
		this.lxid = lxid;
		this.title = title;
		this.time = time;
		this.url = url;
		this.readed = readed;
		this.yhbh = yhbh;
		this.bz = bz;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=20, nullable=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="lxid", length=20, nullable=true)
	public Long getLxid() {
		return lxid;
	}
	public void setLxid(Long lxid) {
		this.lxid = lxid;
	}
	
	@Column(name="title", length=2000, nullable=true)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="time",nullable=true)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Column(name="url", length=500, nullable=true)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="readed", length=2, nullable=true)
	public String getReaded() {
		return readed;
	}
	public void setReaded(String readed) {
		this.readed = readed;
	}
	
	@Column(name="yhbh", length=100, nullable=true)
	public String getYhbh() {
		return yhbh;
	}
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	
	@Column(name="bz", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}

}
