package com.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.entity.po.User;
import com.wechat.service.user.UserService;

@Controller
@RequestMapping(value = "/hello")
public class HelloWorld {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/world", method = RequestMethod.GET)
	public String hello(Model model) {
		List<User> userList = userService.getAllUser();
		for (User user : userList) {
			System.out.println(user.getUsername());
		}
		model.addAttribute("msg", "你好spring mvc");
		model.addAttribute("success","true");
		return "hello";
	}
	
	@RequestMapping(value = "/easyui", method = RequestMethod.GET)
	public String easyui(Model model) {
		return "easyui";
	}
	
	@RequestMapping(value = "/easyuiform", method = RequestMethod.GET)
	public String easyuiform(Model model) {
		return "easyuiform";
	}

	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkUsername(HttpServletRequest request,String username) {
		System.out.println(username);
		boolean isOk = false;
		if(username.equals("刘德华")){
			isOk = true;
		}
		//System.out.println(userService.isExists(username)+"");
		//return userService.isExists(username)+"";
		return isOk+"";
	}

}
