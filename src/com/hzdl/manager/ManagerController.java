package com.hzdl.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzdl.bookservice.CategoryService;
import com.hzdl.entity.Category;

@WebServlet("/manager/*")
public class ManagerController extends HttpServlet{
	
	private CategoryService categoryService;
	
	public ManagerController() {
		categoryService = new CategoryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "";
		String uri = req.getRequestURI();
		if(uri.contains("/home")){// 后台首页
			url = "/WEB-INF/jsp/admin-home.jsp";
		}else if(uri.contains("/category-mgr")){// 跳转图书分类管理			
			getAllCategory(req, resp);
			url = "/WEB-INF/jsp/category-mgr.jsp";
		}else if(uri.contains("/book-mgr")){// 跳转图书管理
			//url = "/WEB-INF/jsp/book-mgr.jsp";
			url="/bookmanager/getall";
		}else if(uri.contains("/help")){
			url="/WEB-INF/jsp/help.jsp";
		}
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	
	private void getAllCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categories = categoryService.getAllCategory();
		req.setAttribute("categories", categories);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
