package com.space.wechat.util;

public class UserInfo {

	/**
	 * 所在学校ID
	 */
	private String companyId;

	/**
	 * 所在部门ID
	 */
	private Long deptId;

	/**
	 * 所在校区ID
	 */
	private Long areaId;
	
	/**
	 * 教师或者家长ID
	 */
	private Long userid;
	
	/**
	 * 微信发送id
	 */
	private String fakeid;
	
	/**
	 * 类型：1:老师，2:家长
	 */
	private int usertype;


	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getFakeid() {
		return fakeid;
	}

	public void setFakeid(String fakeid) {
		this.fakeid = fakeid;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	
	

}
