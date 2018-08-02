package com.hzdl.bookDaoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hzdl.bookDao.CategoryDao;
import com.hzdl.entity.Category;
import com.hzdl.utils.C3P0Utils;

public class CategoryDaoImp implements CategoryDao{

	@Override
	public List<Category> getAllCategory() {
		String sql = "select * from categoryinfo";
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		List<Category> categories = null;
		try {
			categories = qRunner.query(sql, new BeanListHandler<>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public Category getCategory(int BCategoryID){
		String sql = "select c.CName from categoryinfo c,bookinfo b where c.CID=b."+BCategoryID;
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		Category category = null;
		try {
			category = qRunner.query(sql, new BeanHandler<>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public boolean hasBook(String cid) {
		String sql = "select count(*) from bookinfo where BCategoryID=?";
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		try {
		 	long count = qRunner.query(sql, cid,new ScalarHandler<>());
			if(count > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCategory(String cid) {
		String sql = "delete from categoryinfo where CID=?";
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		try {
			qRunner.update(sql,cid);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isExistCategory(String CName) {
		String sql = "select * from categoryinfo where CName=?";
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		try {
			List<Category> categories= qRunner.query(sql, CName,new BeanListHandler<>(Category.class));
			if(categories == null || categories.isEmpty()){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addCategory(String CName) {
		String sql = "insert into categoryinfo(CName) values(?)";
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		try {
			qRunner.update(sql,CName);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
