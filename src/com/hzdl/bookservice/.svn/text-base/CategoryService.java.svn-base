package com.hzdl.bookservice;

import java.util.List;

import com.hzdl.bookDao.CategoryDao;
import com.hzdl.bookDaoImp.CategoryDaoImp;
import com.hzdl.entity.Category;

public class CategoryService {
	
	private CategoryDao categoryDao;

	public CategoryService() {
		super();
		categoryDao = new CategoryDaoImp();
	}
	
	public List<Category> getAllCategory(){
		return categoryDao.getAllCategory();
	}
	
	public Category getCategory(int BCategoryID){
		return categoryDao.getCategory(BCategoryID);
	}
	
	public String deleteCategory(String cid){
		if(categoryDao.hasBook(cid)){
			return "删除失败，该分类下已有书存在，无法删除，请换其他分类试试！";
		}else{
			categoryDao.deleteCategory(cid);
			return "删除成功";
		}
	}
	
	public String addCategory(String CName){
		if(categoryDao.isExistCategory(CName)){
			return "该分类已存在，请换其他分类添加试试！";
		}else{
			if(categoryDao.addCategory(CName)){
				return "分类添加成功！";
			}else{
				return "分类添加失败！";
			}					
		}
	}

}
