package com.tang.myCloud.bean;

import java.util.Date;

public class CloudFile {
	private long fileId;
	private long userId;
	private String fileName;
	private String filePath;
	private long fileSize;
	private Date alterTime;

	public CloudFile() {
		// TODO Auto-generated constructor stub
	}
	public CloudFile(String fileName,long userId, String filePath, long fileSize,
			Date alterTime) {
		super();
		this.userId = userId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.alterTime = alterTime;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(Date alterTime) {
		this.alterTime = alterTime;
	}

}
