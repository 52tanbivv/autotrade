package com.space.wechat.entity.role;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.space.wechat.entity.IdEntity;

@Entity
@Table(name = "role_user")
public class RoleUser extends IdEntity {

	private String roleid;
	private String userid;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
