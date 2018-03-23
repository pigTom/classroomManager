package com.tang.myCloud.webUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * if the user had login before ,
 * then next time , will go to the 
 * main page automatically
 * @author Administrator
 *
 */

public class LoginDispatcherFilter implements Filter{
	FilterConfig config;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		// get dispatcher url
		String url = this.config.getInitParameter("redirectPath");
		String userId = this.config.getInitParameter("userId");
		// get user id if the session was created and the user was login
		String user = (String) session.getAttribute(userId);
		
		// if user had login then go to the specified page
		if( user != null && !user.trim().isEmpty())
			resp.sendRedirect(url);
		else
			chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
