package com.sliu.framework.app.wlx.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 离校--相关信息
 * @author zhangyan
 * @version 创建时间：2016年6月6日  
 */
@Entity
@Table(name = "ZS_LXGG")
public class LxXgxx  implements Serializable{

	 /** id */
	private Long id;
	
	/** 信息类别*/
	private long type;
	
	/** 信息标题 */
	private String title;
	
	/** 信息内容 */
	private String content;
	
	/** 发布时间 */
	private Date fbtime;
	
	/** 信息来源*/
	private String ly;
	
	/** 作者*/
	private String author;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "type", length = 11, nullable = true)
	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}


	@Column(name = "title", length = 256, nullable = true)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 8000, nullable = true)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "fbtime", length = 19, nullable = true)
	public Date getFbtime() {
		return fbtime;
	}

	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}

	@Column(name = "ly", length = 60, nullable = true)
	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	@Column(name = "author", length = 60, nullable = true)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}

