package com.hzdl.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.hzdl.bookservice.BookService;
import com.hzdl.bookservice.CategoryService;
import com.hzdl.entity.Book;
import com.hzdl.entity.Category;
import com.hzdl.entity.Page;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@WebServlet("/bookmanager/*")
public class BookManagerController extends HttpServlet {
	
	private BookService bookService;
	private CategoryService categoryService;

	public BookManagerController() {
		super();
		bookService = new BookService();
		categoryService = new CategoryService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if(uri.contains("/getall")){//获取所有图书
			getAll(req, resp);
		}else if(uri.contains("/delete")){//删除图书
			deleteBook(req, resp);
		}else if(uri.contains("/add")){//添加图书
			addBook(req, resp);
		}else if(uri.contains("/goEdit")){//跳转编辑页面
			goEdit(req, resp);
		}else if(uri.contains("/edit")){
			Edit(req, resp);
		}
	}
	
	private void Edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), req, resp);
		su.setAllowedFilesList("jpg,jpeg,gif,png");
		try {
			su.upload();
			File file = su.getFiles().getFile(0);
			if(!file.isMissing()){
				String savePath = "/photo/";
				String bPhoto = file.getFileName();
				file.saveAs(savePath+bPhoto,File.SAVEAS_AUTO);
			}
			Request request = su.getRequest();
			String BID = request.getParameter("BID");
			String bTitle = request.getParameter("BTitle");
			String bAuthor = request.getParameter("BAuthor");
			int bCategoryID = Integer.parseInt(request.getParameter("BCategoryID"));
			String bPrice = request.getParameter("BPrice");
			String bPublisher = request.getParameter("BPublisher");
		//	String bPhoto = request.getParameter("bPhoto");
			String bPhoto = file.getFileName();
			Book book = new Book(bTitle, bAuthor, bPrice, bCategoryID, bPublisher, bPhoto);
			book.setBID(Integer.parseInt(BID));
			bookService.editBook(book);
			req.getRequestDispatcher("/bookmanager/getall").forward(req, resp);
		} catch (SmartUploadException e) {
			if (e.getMessage().contains("1105")) {
				req.setAttribute("msg", "文件太大！");
			} else if (e.getMessage().contains("1010")) {
				req.setAttribute("msg", "只允许上传jpg,jpeg,gif,png格式的文件！");
			} else if (e.getMessage().contains("1120")) {
				req.setAttribute("msg", "文件名格式错误！");
			}
		}
		
	}
	
	private void goEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String BID = req.getParameter("BID");
		Book book = bookService.getBookByBID(BID);
		req.setAttribute("book", book);
		getAllCategory(req, resp);
		req.getRequestDispatcher("/WEB-INF/jsp/book-edit.jsp").forward(req, resp);
	}
	
	private void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), req, resp);
		su.setAllowedFilesList("jpg,jpeg,gif,png");
		try {
			su.upload();
			File file = su.getFiles().getFile(0);
			String savePath = "/photo/";
			String bPhoto = file.getFileName();
			file.saveAs(savePath+bPhoto,File.SAVEAS_AUTO);
			
			Request request = su.getRequest();
			String bTitle = request.getParameter("BTitle");
			String bAuthor = request.getParameter("BAuthor");
			int bCategoryID = Integer.parseInt(request.getParameter("BCategoryID"));
			String bPrice = request.getParameter("BPrice");
			String bPublisher = request.getParameter("BPublisher");
			Book book = new Book(bTitle, bAuthor, bPrice, bCategoryID, bPublisher, bPhoto);
			boolean flag = bookService.addBook(book);
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().println(flag);
			resp.getWriter().close();
			
		//	req.getRequestDispatcher("/bookmanager/getall").forward(req, resp);
			
		} catch (SmartUploadException e) {
			if (e.getMessage().contains("1105")) {
				req.setAttribute("msg", "文件太大！");
			} else if (e.getMessage().contains("1010")) {
				req.setAttribute("msg", "只允许上传jpg,jpeg,gif,png格式的文件！");
			} else if (e.getMessage().contains("1120")) {
				req.setAttribute("msg", "文件名格式错误！");
			}
		}
	}
	
	private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String BID = req.getParameter("BID");
		boolean flag = bookService.deleteBook(BID);
		JSONObject object = new JSONObject();
		if(flag){
			object.put("errorCode", 200);
			object.put("errorMsg", "图书删除成功");
		}else{
			object.put("errorCode", 201);
			object.put("errorMsg", "图书删除失败");
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().println(object.toJSONString());
		resp.getWriter().close();
	//	req.getRequestDispatcher("/bookmanager/getall").forward(req, resp);
	}
	
	protected void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cid = req.getParameter("CID");
		long total = bookService.getBooksCount(cid);
		Page page = new Page((int)total, req);
		List<Book> books = bookService.getBooksByCID(cid, page.getPageNo());
		req.setAttribute("books", books);
		getAllCategory(req, resp);
		req.getRequestDispatcher("/WEB-INF/jsp/book-mgr.jsp").forward(req, resp);
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
