package com.space.wechat.entity.role;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.space.wechat.entity.IdEntity;

@Entity
@Table(name = "role_menu")
public class RoleMenu extends IdEntity {

	private String roleid;
	private String menuid;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

}
