package com.sliu.framework.app.wxutil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author:zhangyi
 * @version 创建时间：2016年4月15日 上午11:59:59 模板数据
 */
@Entity
@Table(name = "WX_TEMPLATE_DATA")
public class TemplateData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long templateId;
	private String value;
	private String color;
	private String px;
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

	@Column(name="TEMPLATEID")
	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	@Column(name = "VALUE", length = 500)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "COLOR", length = 50)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "PX", length = 10)
	public String getPx() {
		return px;
	}

	public void setPx(String px) {
		this.px = px;
	}
	
	@Column(name = "BZ", length = 500)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
