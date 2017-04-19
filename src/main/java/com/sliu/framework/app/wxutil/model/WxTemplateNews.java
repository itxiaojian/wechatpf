package com.sliu.framework.app.wxutil.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author:zhangyi
 * @version 创建时间：2016年4月15日 上午11:59:19 微信模板消息(保存数据)
 */
@Entity
@Table(name = "WX_TEMPLATE_NEWS")
public class WxTemplateNews implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 添加时间
	 */
	private Date addtime;
	
	/**
	 * 模板消息id
	 */
	private String templateId;
	/**
	 * URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）
	 */
	private String url;
	/**
	 * 标题颜色
	 */
	private String topcolor;

	private String bz;

	@Id
	@GeneratedValue(generator = "tableGenerator")
	@GenericGenerator(name = "tableGenerator", strategy = "com.sliu.framework.app.common.dao.key.SequenceGenerator")
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="ADDTIME")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	@Column(name = "TEMPLATE_ID", length = 100)
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	@Column(name = "URL", length = 100)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "TOPCOLOR", length = 100)
	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	@Column(name = "BZ", length = 500)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}