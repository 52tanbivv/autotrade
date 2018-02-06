package com.space.wechat.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.space.wechat.repository.UserDao;

/**
 * 家长联系老师.
 * 
 * @author hbz
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class loginService {

	private static Logger logger = LoggerFactory.getLogger(loginService.class);

	@Autowired
	private UserDao userDao;

}
