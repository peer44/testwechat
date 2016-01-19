package com.wechat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wechat.entity.po.User;
import com.wechat.service.user.UserService;

@Controller
@RequestMapping(value="/hello")
public class HelloWorld {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/world",method=RequestMethod.GET)
	public String hello(Model model){
		List<User> userList = userService.getAllUser();
		for(User user:userList){
			System.out.println(user.getUsername());
		}
		model.addAttribute("msg", "你好spring mvc");
		return "hello";
	}

}
