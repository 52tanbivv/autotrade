package com.space.wechat.util.email;

public class Mail {

	private String toUser;
	private String subject;
	private String content;

	public Mail(String toUser, String subject, String content) {
		this.toUser = toUser;
		this.subject = subject;
		this.content = content;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
