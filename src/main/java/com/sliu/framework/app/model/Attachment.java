package com.sliu.framework.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sliu.framework.app.common.model.BaseEntity;

/**
 *
 * @datetime 2015-05-29
 * @author zhangyi
 */
@Entity
@Table(name="UNTECK_ATTACHMENT")
public class Attachment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="FILE_NAME")
    private String fileName;
	@Column(name="FILE_SIZE")
    private Long fileSize;
	@Column(name="FILE_TYPE")
    private String fileType;
	@Column(name="ATTACHMENT_TYPE")
    private String attachmentType;
	@Column(name="DATA_ID")
	private Long dataId;
	@Column(name="FILE_CODE")
    private String fileCode;
	@Column(name="DESCN")
	private String descn;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public Long getDataId() {
		return dataId;
	}
	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
}
