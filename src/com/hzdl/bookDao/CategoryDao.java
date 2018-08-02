package com.hzdl.bookDao;

import java.util.List;

import com.hzdl.entity.Category;

public interface CategoryDao {

	public List<Category> getAllCategory();
	public Category getCategory(int BCategoryID);
	
	public boolean hasBook(String cid);
	public boolean deleteCategory(String cid);
	
	public boolean isExistCategory(String CName);
	public boolean addCategory(String CName);
	
}
