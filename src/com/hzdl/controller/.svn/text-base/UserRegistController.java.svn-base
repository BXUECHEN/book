package com.hzdl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzdl.bookservice.UserService;

@WebServlet("/regist")
public class UserRegistController extends HttpServlet{

	private UserService userService;
	
	public UserRegistController() {
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String loginId = req.getParameter("loginId");
		String loginPsw = req.getParameter("loginPsw");
		String reLoginPsw = req.getParameter("reLoginPsw");
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		if(reLoginPsw.equals(loginPsw)){
			req.setAttribute("pmsg", "密码输入一致");
			String checkCode = (String) req.getSession().getAttribute("checkCode");
			if(code.equals(checkCode)){
				req.setAttribute("cmsg", "验证码正确");
				userService.registUser(loginId, loginPsw, name);
				req.getRequestDispatcher("/WEB-INF/jsp/user-login.jsp").forward(req, resp);
			}else{
				req.setAttribute("cmsg", "验证码错误");
				req.getRequestDispatcher("/WEB-INF/jsp/user-regist.jsp").forward(req, resp);
			}
			
		}else{
			req.setAttribute("pmsg", "密码输入不一致");
			req.getRequestDispatcher("/WEB-INF/jsp/user-regist.jsp").forward(req, resp);
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
