package com.hzdl.bookDaoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hzdl.bookDao.AdminDao;
import com.hzdl.entity.Admin;
import com.hzdl.utils.C3P0Utils;

public class AdminDaoImp implements AdminDao{

	@Override
	public Admin getAdminByNameAndPassword(String ALoginID, String ALoginPsw) {
		String sql = "Select * from admininfo where ALoginID=? and ALoginPsw=?";
		QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
		Admin admin = null;
		try {
			admin = qRunner.query(sql, new Object[]{ALoginID,ALoginPsw}, new BeanHandler<>(Admin.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

}
