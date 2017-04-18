package com.sliu.framework.app.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * sys_backup
 * 
 * @author lxt
 * @since 2014-12-08 10:35:36
 */
@Entity
@Table(name = "sys_backup")
public class SysBackup implements Serializable {

	/** id */
	private String id;

	/** 文件备份名 */
	private String fileName;

	/** 文件备份时间 */
	private java.util.Date backupTime;

	/** 备份文件路径 */
	private String path;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 32, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "file_name", length = 100, nullable = true)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "backup_time", length = 19, nullable = true)
	public java.util.Date getBackupTime() {
		return backupTime;
	}

	public void setBackupTime(java.util.Date backupTime) {
		this.backupTime = backupTime;
	}

	@Column(name = "path", length = 100, nullable = true)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
