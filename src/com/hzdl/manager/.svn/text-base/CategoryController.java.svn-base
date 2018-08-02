package com.hzdl.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.hzdl.bookservice.CategoryService;

/**
 * 处理图书分类管理
 * */
@WebServlet("/category/*")
public class CategoryController extends HttpServlet {
	
	private CategoryService categoryService;
	
	public CategoryController() {
		categoryService = new CategoryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		req.setCharacterEncoding("utf-8");
		if(uri.contains("/delete")){
			String cid = req.getParameter("CID");
			String errorMsg = categoryService.deleteCategory(cid);
			JSONObject object = new JSONObject();
			if("删除成功".equals(errorMsg)){
				object.put("errorCode", 200);
			}else{
				object.put("errorCode", 201);
			}
			object.put("errorMsg", errorMsg);
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().println(object.toJSONString());
			resp.getWriter().close();
			
			/*req.setAttribute("msg", errorMsg);
			req.getRequestDispatcher("/manager/category-mgr").forward(req, resp);*/
		}else if(uri.contains("/add")){
			String CName = req.getParameter("CName");
			String errorMsg = categoryService.addCategory(CName);
			req.setAttribute("Msg", errorMsg);
			req.getRequestDispatcher("/manager/category-mgr").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
