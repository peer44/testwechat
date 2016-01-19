package com.wechat.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.dao.UserDao;
import com.wechat.entity.po.User;
import com.wechat.service.user.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

}
