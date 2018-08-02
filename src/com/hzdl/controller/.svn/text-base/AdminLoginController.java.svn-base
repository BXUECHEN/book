package com.hzdl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzdl.bookservice.AdminService;
import com.hzdl.entity.Admin;
import com.hzdl.entity.User;

@WebServlet("/admin/*")
public class AdminLoginController extends HttpServlet {
	
	private AdminService adminService;

	public AdminLoginController() {
		adminService = new AdminService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if(uri.contains("/toLogin")){
			toLogin(req, resp);
		}else if(uri.contains("/login")){
			adminLogin(req, resp);
		}
	}
	
	private void toLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/admin-login.jsp").forward(req, resp);
	}
	
	private void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String adminName = req.getParameter("loginId");
		String password = req.getParameter("loginPsw");		
		Admin admin = adminService.adminLogin(adminName, password);	
		String rememberMe = req.getParameter("rememberMe");	
				
		if("on".equals(rememberMe)){
			if(admin != null){
				HttpSession session = req.getSession();			
				session.setAttribute("Admin", admin);
				session.setMaxInactiveInterval(24*60*60);								
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(24*60*60);
				resp.addCookie(cookie);	
				
				req.getRequestDispatcher("/WEB-INF/jsp/admin-home.jsp").forward(req, resp);
			}else{
				toLogin(req, resp);
			}
												
		}else{
			if(admin != null){
				req.getRequestDispatcher("/WEB-INF/jsp/admin-home.jsp").forward(req, resp);
			}else{
				toLogin(req, resp);
			}
		}			
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
