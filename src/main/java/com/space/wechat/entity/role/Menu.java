package com.space.wechat.entity.role;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.space.wechat.entity.IdEntity;

@Entity
@Table(name = "menu")
public class Menu extends IdEntity {

	private String title;
	private String action;
	private Integer level;
	private String parentid;
	private Integer sort;
	private Boolean isparent;
	private String icon;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsparent() {
		return isparent;
	}

	public void setIsparent(Boolean isparent) {
		this.isparent = isparent;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
