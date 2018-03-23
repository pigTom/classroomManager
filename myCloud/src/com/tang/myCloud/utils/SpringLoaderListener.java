package com.tang.myCloud.utils;

import javax.servlet.ServletContextEvent;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLoaderListener implements
		javax.servlet.ServletContextListener

{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("spring start destroying");
		((ClassPathXmlApplicationContext) arg0.getServletContext()
				.getAttribute("context")).close();
		System.out.println("spring end destroying");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("spring start loading");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");
		arg0.getServletContext().setAttribute("context", context);
		System.out.println("spring end loading");
	}

}
