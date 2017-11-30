package com.tang.myCloud.webUtils;

import java.io.IOException;
import java.util.regex.Pattern;

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
 * check if user is login successfully
 * @author Administrator
 *
 */

public class SessionFilter implements Filter {
	FilterConfig filterConfig;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
	}
	private boolean contains(String url, String regex){
		String[] re = {};
		if( regex.contains(";"))
			re = regex.split(";");
		else 
			re = new String[]{regex};
		for( String s : re){
			if( Pattern.matches(s, url))
				return true;
		}
		return false;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		// 使过滤器失效
		String disableFilter = this.filterConfig.getInitParameter("disableFilter");
		if( disableFilter.equalsIgnoreCase("Y")){
			chain.doFilter(request, response);
			return;
		}
		
		//请求的uri
		String path = req.getContextPath();
		String requestURI = req.getRequestURI();
		requestURI = requestURI.substring(requestURI.indexOf(path)+path.length()+1);

		if( requestURI.contains("?"))
			requestURI = requestURI.split("?")[0];
		
		// 不用过滤的uri
		String ignoreURI = this.filterConfig.getInitParameter("ignoreURI");
		if( this.contains(requestURI, ignoreURI)){
			chain.doFilter(request, response);
			return;
		}
		
		// 需要过滤的uri
		String uri = this.filterConfig.getInitParameter("includeURI");
		if( !this.contains(requestURI, uri)){
			chain.doFilter(request, response);
			return;
		}
		
		
		// 重定向的地址
		String redirectPath = this.filterConfig.getInitParameter("redirectPath");
		
		String userId = (String) session.getAttribute("userId");
		if( userId != null)
			chain.doFilter(request, response);
		else
			resp.sendRedirect(redirectPath);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
