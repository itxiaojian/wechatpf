package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文件管理
 * 
 * @author lxt
 * @since 2014-11-10 14:25:23
 */
@Entity
@Table(name = "sys_file")
public class SysFile implements Serializable {

	/** id */
	private String id;

	/** 描述 */
	private String descn;

	/** 文件名 */
	private String fileName;

	/** 文件路径 */
	private String path;

	/** 创建时间 */
	private java.util.Date createTime;

	/** 文件类型 */
	private String type;

	/** 扩展名 */
	private String extension;

	/** 文件大小 */
	private Long size;

	/** 对象主键 */
	private String refId;

	/** 引用对象 */
	private String refObj;

	/** 上传人ID */
	private String userId;

	/** 上传人名称 */
	private String username;

	/** 文件夹ID */
	private String folderId;

	/** 视频文件时长 */
	private String duration;

	@Id
	@Column(name = "id", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "file_name", length = 256, nullable = true)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "descn", length = 256, nullable = true)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	@Column(name = "path", length = 256, nullable = true)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "create_time", length = 19, nullable = true)
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "type", length = 200, nullable = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "extension", length = 200, nullable = true)
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Column(name = "size", length = 19, nullable = true)
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@Column(name = "ref_id", length = 32, nullable = true)
	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	@Column(name = "ref_obj", length = 100, nullable = true)
	public String getRefObj() {
		return refObj;
	}

	public void setRefObj(String refObj) {
		this.refObj = refObj;
	}

	@Column(name = "user_id", length = 32, nullable = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "username", length = 32, nullable = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "folder_Id", length = 36, nullable = true)
	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	@Column(name = "duration", length = 10, nullable = true)
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
