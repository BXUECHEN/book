package com.hzdl.bookDao;

import com.hzdl.entity.Admin;

public interface AdminDao {
	
	public Admin getAdminByNameAndPassword(String ALoginID,String ALoginPsw);

}
