package com.space.wechat.service.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.space.wechat.entity.User;
import com.space.wechat.entity.role.Menu;
import com.space.wechat.repository.MenuDao;

//Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class MenuService {

	@Autowired
	private MenuDao menuDao;

	public List<Menu> list(JSONObject params) {
		User user = (User) params.get("currentUser");
		return menuDao.findByUser(user.getId());
	}
}
