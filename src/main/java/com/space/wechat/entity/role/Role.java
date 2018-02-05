package com.space.wechat.entity.role;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.space.wechat.entity.IdEntity;

@Entity
@Table(name = "role")
public class Role extends IdEntity {

	private String name;
	private String remark;
	private Integer sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
