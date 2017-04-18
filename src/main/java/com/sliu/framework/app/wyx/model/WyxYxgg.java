package com.sliu.framework.app.wyx.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.axis2.databinding.types.soapencoding.Decimal;

/**
 * 网络计费
 * @author wangxiangyang
 * @date 2015年6月1日
 */
@Entity
@Table(name = "zs_yx_gg")
public class WyxYxgg {
	
	private Long id;

	private String type;

	private String title;

	private String content;

	private Date fbtime;

	private Decimal ly;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Decimal getLy() {
		return ly;
	}

	public void setLy(Decimal ly) {
		this.ly = ly;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getFbtime() {
		return fbtime;
	}

	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}
	
}
