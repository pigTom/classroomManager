package com.tang.myCloud.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.tang.myCloud.bean.CloudUser;
import com.tang.myCloud.bean.PageInfo;
import com.tang.myCloud.dao.FileDao;
import com.tang.myCloud.dao.UserDao;

@Component("userService")
public class UserService {
	@Resource(name = "userDao")
	private UserDao dao;
	@Resource(name="fileDao")
	private FileDao fileDao;
	/**
	 * 将该用户注册，并将用户名存入request域中，key为<code>userName</code>
	 * @param user
	 * @param request
	 */
	public void register(CloudUser user, HttpServletRequest request) {
		request.setAttribute("userName", user.getUserName());
		dao.addUser(user);
	}
	/**
	 * 从数据库中查询该用户是否存在，如果存在将用户的id存入session中，
	 * 并将该用户云储存的文件放入session中，key为<code>pageInfo</code>
	 * @param user
	 * @param request
	 * @return
	 */
	public boolean login(CloudUser user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		long userId = dao.login(user);
		if (userId <= 0) {
			return false;
		} else {
			// show file list 
			// store the pageInfo to session
			session.setAttribute("userId", userId+"");
			session.setAttribute("userName", user.getUserName());
			PageInfo info = fileDao.getFileInfo(userId);
			session.setAttribute("pageInfo", info);
			return true;
		}

	}
}
