package com.tang.myCloud.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tang.myCloud.bean.CloudUser;
import com.tang.myCloud.service.UserService;
@Controller
@RequestMapping("user")
public class UserController {
	@Resource(name = "userService")
	private UserService service;

	@RequestMapping("login.do")
	public String login(String userName, String password, Model model,
			HttpServletRequest request) throws ServletException, IOException {
		if (userName == null || userName.trim().isEmpty()) {
			model.addAttribute("userNameErrorMsg", "用户名不能为空");
			return "index";
		}
		if (password == null || password.trim().isEmpty()) {
			model.addAttribute("passwordErrorMsg", "密码不能为空");
			return "index";
		}
		if (service.login(new CloudUser(userName, password), request)) {
			return "WEB-INF/jsp/myCloud";
		} else {
			model.addAttribute("loginErrorMsg", "用户名或密码错误");
			return "index";
		}
	}
	

	@RequestMapping("register.do")
	public String register(String userName, String password, Model model,
			HttpServletRequest request) {
		// 没有验证参数不合法的问题
		if (userName == null || userName.trim().isEmpty()) {
			model.addAttribute("usernameErrorMsg", "用户名不能为空");
			return "register";
		}
		if (password == null || password.trim().isEmpty()) {
			model.addAttribute("passwordErrorMsg", "密码不能为空");
			return "register";
		}
		// 注册 入数据库
		service.register(new CloudUser(userName, password), request);
		model.addAttribute("username", userName);
		return "index";
	}
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
	}

}
