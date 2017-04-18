package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 系统日志
 * 
 * @author lxt
 * @since 2014-11-20 09:40:56
 */
@Entity
@Table(name = "sys_log")
public class SysLog implements Serializable {

	/** id */
	private String id;

	/** 操作 */
	private String operation;

	/** 操作内容 */
	private String operContent;

	/** 操作人账号 */
	private String userId;

	/** 操作人 */
	private String username;

	/** 客户端 */
	private String userAgent;

	/** IP */
	private String ip;

	/** 操作日期 */
	private java.util.Date operDate;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "operation", length = 50, nullable = true)
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "oper_content", length = 255, nullable = true)
	public String getOperContent() {
		return operContent;
	}

	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}

	@Column(name = "user_id", length = 36, nullable = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "username", length = 255, nullable = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "user_agent", length = 256, nullable = true)
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Column(name = "IP", length = 100, nullable = true)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "oper_date", length = 19, nullable = true)
	public java.util.Date getOperDate() {
		return operDate;
	}

	public void setOperDate(java.util.Date operDate) {
		this.operDate = operDate;
	}

}
