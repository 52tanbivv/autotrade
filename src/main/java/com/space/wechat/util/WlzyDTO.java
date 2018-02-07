package com.space.wechat.util;

public class WlzyDTO {
	private Long id;
	private String content;
	private String filepath;
	private String fileids;
	private String stuids;
	private String picpath;
	private int wlzytype;

	public String getFileids() {
		return fileids;
	}

	public void setFileids(String fileids) {
		this.fileids = fileids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getWlzytype() {
		return wlzytype;
	}

	public void setWlzytype(int wlzytype) {
		this.wlzytype = wlzytype;
	}

	public String getStuids() {
		return stuids;
	}

	public void setStuids(String stuids) {
		this.stuids = stuids;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

}
