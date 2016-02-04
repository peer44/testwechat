package com.wechat.dao;

import java.util.List;

import com.wechat.entity.po.User;

public interface UserDao {
	// 得到所有用户
	public List<User> getAllUser();

	// 检测用户名是否存在
	public boolean isExists(String username);

}
