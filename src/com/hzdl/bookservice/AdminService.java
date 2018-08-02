package com.hzdl.bookservice;

import com.hzdl.bookDao.AdminDao;
import com.hzdl.bookDaoImp.AdminDaoImp;
import com.hzdl.entity.Admin;

public class AdminService {
	
	private AdminDao adminDao;

	public AdminService() {
		adminDao = new AdminDaoImp();
	}
	
	public Admin adminLogin(String ALoginID,String ALoginPsw){
		return adminDao.getAdminByNameAndPassword(ALoginID, ALoginPsw);
	}
	
	

}
