package com.space.wechat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_project")
public class UserProject extends IdEntity {
	
	public static final Integer DEFAULT_STATE_TRUE = 1;
	
	public static final Integer DEFAULT_STATE_FALSE = 0;
	
	private String userid;
	private String projectid;
	private Integer defaultState;
	private Date createTime;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public Integer getDefaultState() {
		return defaultState;
	}
	public void setDefaultState(Integer defaultState) {
		this.defaultState = defaultState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}
