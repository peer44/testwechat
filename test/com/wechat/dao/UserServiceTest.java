package com.wechat.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wechat.entity.po.User;
import com.wechat.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-core.xml" })
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void test() {
		List<User> userList = userService.getAllUser();
		for(User user:userList){
			System.out.println(user.getUsername());
		}
		
	}

}
