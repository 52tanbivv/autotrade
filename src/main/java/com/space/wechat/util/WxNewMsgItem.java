package com.space.wechat.util;

public class WxNewMsgItem {
	
	/**
	 * 用户请求后，返回图文消息供用户查询相关数据
	 */
	private String title;
	
	private String description;
	
	private String picurl;
	
	private String url;
	
	private String fromUserName;
	
	private Long orgcode;
	
	public WxNewMsgItem() {
		
	}

	public WxNewMsgItem(String title, String description, String picUrl,
			String url, String fromUserName) {
		this.title = title;
		this.description = description;
		this.picurl = picUrl;
		this.url = url;
		this.fromUserName = fromUserName;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicUrl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(Long orgcode) {
		this.orgcode = orgcode;
	}
	
	
}
