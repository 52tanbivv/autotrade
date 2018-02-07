package com.space.wechat.service.kindergarten.xtgl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.space.wechat.entity.role.Role;
import com.space.wechat.repository.RoleDao;

@Component
@Transactional(readOnly = true)
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	/**
	 * 查询角色列表接口：role_list
	 * @param params
	 * @return
	 */
	public List<Role> list(JSONObject params) {
		return roleDao.findAll();
	}

}
