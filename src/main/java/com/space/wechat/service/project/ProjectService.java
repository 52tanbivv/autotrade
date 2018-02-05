package com.space.wechat.service.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.space.wechat.entity.Project;
import com.space.wechat.entity.User;
import com.space.wechat.repository.ProjectDao;
import com.space.wechat.util.StringUtil;

@Component
@Transactional(readOnly = true)
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	/**
	 * 接口：project_list（查询项目列表）
	 * 
	 * @param params
	 * @return
	 */
	public List<Project> list(JSONObject params) {
		User user = (User) params.get("currentUser");

		List<Project> projectList = new ArrayList<Project>();
		if (StringUtil.isNullOrEmpty(user.getId())) {
			projectList = projectDao.findAll();
		} else {
			projectList = projectDao.findProjectByUserid(user.getId());
		}

		return projectList;
	}

	public Project findById(String projectid) {
		return projectDao.findOne(projectid);
	}

}
