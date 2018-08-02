package com.hzdl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	//	System.out.println("application监听器销毁了");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//System.out.println("application监听器创建了");
		
		String path = event.getServletContext().getContextPath();
		event.getServletContext().setAttribute("path", path);
		
	}

}
