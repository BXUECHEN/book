package com.hzdl.bookDaoImp;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hzdl.bookDao.UserDao;
import com.hzdl.entity.User;
import com.hzdl.utils.C3P0Utils;
import com.hzdl.utils.DButils;

public class UserDaoImp implements UserDao{

	@SuppressWarnings("deprecation")
	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		Connection conn = DButils.getConn();
		QueryRunner qRunner = new QueryRunner();
		String sql = "select * from userinfo where ULoginID=? and ULoginPsw=?";
		User user = null;
		try {
			user = qRunner.query(conn, sql,new Object[]{username,password},new BeanHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean addUser(String loginId, String loginPsw, String name) {
		String sql = "insert into userinfo (ULoginID,ULoginPsw,UName) values(?,?,?)";
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		try {
			qRunner.update(sql, loginId,loginPsw,name);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
