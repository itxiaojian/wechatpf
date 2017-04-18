package com.sliu.framework.app.sys.model;

import java.util.Date;

public class BackUpBean {
	private String fileName;
	private Date backTime;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
}
