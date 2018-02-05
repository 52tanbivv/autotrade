package com.space.wechat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project extends IdEntity {

	private String code;
	private String name;
	private String content;
	private Date creatorid;
	private Date createTime;
	private String gitBranch;
	private String gitUrl;

	public String getGitBranch() {
		return gitBranch;
	}

	public void setGitBranch(String gitBranch) {
		this.gitBranch = gitBranch;
	}

	public String getGitUrl() {
		return gitUrl;
	}

	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(Date creatorid) {
		this.creatorid = creatorid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
