package com.hzdl.bookservice;

import com.hzdl.bookDao.UserDao;
import com.hzdl.bookDaoImp.UserDaoImp;
import com.hzdl.entity.User;

public class UserService {

	private UserDao userDao;
	
	public UserService(){
		userDao = new UserDaoImp();
	}
	
	public User login(String username,String password){
		return userDao.getUserByUsernameAndPassword(username, password);		
	}
	
	public boolean registUser(String loginId,String loginPsw,String name){
		return userDao.addUser(loginId, loginPsw, name);
	}
}
