package com.hzdl.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzdl.bookservice.BookService;
import com.hzdl.bookservice.CategoryService;
import com.hzdl.entity.Book;
import com.hzdl.entity.Category;
import com.hzdl.entity.Page;

@WebServlet("/books/*")
public class BookController extends HttpServlet {

	private BookService bookService;
	private CategoryService categoryService;

	public BookController() {
		super();
		bookService = new BookService();
		categoryService = new CategoryService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		if(uri.contains("allbooks")){
			getBooks(req,resp);			
			getAllCategory(req,resp);						
			
			String condition = req.getParameter("condition");
			List<Book> books2 = bookService.searchBook(condition);
			req.setAttribute("searchbooks", books2);
			req.getSession().setAttribute("condition", condition);
			
			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
		}else if(uri.contains("page")){
			String cid = req.getParameter("CID");
			long total = bookService.getBooksCount(cid);//总共有多少本书
			Page page = new Page((int)total, req);
			List<Book> books = bookService.getBooksByCID(cid,page.getPageNo());
			req.setAttribute("books", books);
			
			getAllCategory(req,resp);
			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
		}else if(uri.contains("/toLogin")){
			req.getRequestDispatcher("/WEB-INF/jsp/user-login.jsp").forward(req, resp);
		}else if(uri.contains("/toRegist")){
			req.getRequestDispatcher("/WEB-INF/jsp/user-regist.jsp").forward(req, resp);
		}
		
	}



	private void getBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*		String cid = req.getParameter("CID");
		req.setAttribute("CID", cid);
		List<Book> books = bookService.getBooksByCID(cid);
		req.setAttribute("books", books);*/
		String cid = req.getParameter("CID");
		long total = bookService.getBooksCount(cid);//总共有多少本书
		Page page = new Page((int)total, req);
		List<Book> books = bookService.getBooksByCID(cid,page.getPageNo());
		req.setAttribute("books", books);
	}
	
	private void getAllCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categories = categoryService.getAllCategory();
		req.setAttribute("categories", categories);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
	
}
