package com.hzdl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzdl.entity.User;
import com.hzdl.bookservice.UserService;

@WebServlet("/login")
public class UserLoginController extends HttpServlet {
	
	private UserService userService;
	
	public UserLoginController() {
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		req.setCharacterEncoding("utf-8");
		
		String username = req.getParameter("loginId");
		String password = req.getParameter("loginPsw");		
		User user = userService.login(username, password);		
		String rememberMe = req.getParameter("rememberMe");	
				
		if("on".equals(rememberMe)){	
			if(user != null){
				HttpSession session = req.getSession();			
				session.setAttribute("User", user);
				session.setMaxInactiveInterval(24*60*60);								
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(24*60*60);
				resp.addCookie(cookie);	
				
				
			}else{
				req.getRequestDispatcher("/WEB-INF/jsp/user-login.jsp").forward(req, resp);
			}
												
		}else{
			if(user != null){
				
			}else{
				req.getRequestDispatcher("/WEB-INF/jsp/user-login.jsp").forward(req, resp);
			}
		}		
						
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
