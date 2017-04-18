package com.sliu.framework.app.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * Description of the class
 * 
 * @author lxt
 * @version 1.0
 * @since 2012-12-29
 */

public class FileForm {
	private String folderId;
	private String refObj;
	private String refId;

	private List<MultipartFile> fileData = new ArrayList<MultipartFile>();

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	private List<String> comments = new ArrayList<String>();

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<MultipartFile> getFileData() {
		return fileData;
	}

	public void setFileData(List<MultipartFile> fileData) {
		this.fileData = fileData;
	}

	public String getRefObj() {
		return refObj;
	}

	public void setRefObj(String refObj) {
		this.refObj = refObj;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}
}
