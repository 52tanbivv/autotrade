package com.space.wechat.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.space.wechat.entity.role.Role;

@Entity
@Table(name = "user")
public class User extends IdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loginName;
	private String name;
	private String password;
	private String salt;
	private String avatar;
	private String debugUrl;// 在线调试地址
	private String testDburl;// DEBUG的数据库URL
	private String testDbuser;// DEBUG的数据库用户名
	private String testDbpwd;// DEBUG的数据库密码
	private String testMemcached;// DEBUG的MEMCACHED
	private String tapdUsername;
	private String developerType;
	private Boolean block;
	private String currentProject;

	private Boolean admin;
	private Boolean designer;
	private Boolean business;
	private Boolean developer;
	private Boolean tester;

	@Transient
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	@Transient
	public Boolean getDesigner() {
		return designer;
	}

	public void setDesigner(Boolean designer) {
		this.designer = designer;
	}

	@Transient
	public Boolean getBusiness() {
		return business;
	}

	public void setBusiness(Boolean business) {
		this.business = business;
	}

	@Transient
	public Boolean getDeveloper() {
		return developer;
	}

	public void setDeveloper(Boolean developer) {
		this.developer = developer;
	}

	@Transient
	public Boolean getTester() {
		return tester;
	}

	public void setTester(Boolean tester) {
		this.tester = tester;
	}

	public String getTapdUsername() {
		return tapdUsername;
	}

	public void setTapdUsername(String tapdUsername) {
		this.tapdUsername = tapdUsername;
	}

	public String getTestDburl() {
		return testDburl;
	}

	public void setTestDburl(String testDburl) {
		this.testDburl = testDburl;
	}

	public String getTestDbuser() {
		return testDbuser;
	}

	public void setTestDbuser(String testDbuser) {
		this.testDbuser = testDbuser;
	}

	public String getTestDbpwd() {
		return testDbpwd;
	}

	public void setTestDbpwd(String testDbpwd) {
		this.testDbpwd = testDbpwd;
	}

	public String getTestMemcached() {
		return testMemcached;
	}

	public void setTestMemcached(String testMemcached) {
		this.testMemcached = testMemcached;
	}

	private String remark;

	private List<Project> projectList;
	private Role role;
	private String roleName;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDebugUrl() {
		return debugUrl;
	}

	public void setDebugUrl(String debugUrl) {
		this.debugUrl = debugUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	@Transient
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDeveloperType() {
		return developerType;
	}

	public void setDeveloperType(String developerType) {
		this.developerType = developerType;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	public String getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(String currentProject) {
		this.currentProject = currentProject;
	}

}