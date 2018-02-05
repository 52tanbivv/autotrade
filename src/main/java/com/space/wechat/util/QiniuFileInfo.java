package com.space.wechat.util;

public class QiniuFileInfo {

	// {"fsize":100803,"hash":"Fvgupn_v2Na44NCIYrVa7M3j4dQl","mimeType":"image/jpeg","putTime":13781747786046810}

	private long fsize;
	private String hash;
	private String mimeType;
	private long puttime;

	public long getFsize() {
		return fsize;
	}

	public void setFsize(long fsize) {
		this.fsize = fsize;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getPuttime() {
		return puttime;
	}

	public void setPuttime(long puttime) {
		this.puttime = puttime;
	}

}
