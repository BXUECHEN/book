package com.hzdl.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

public class DButils {
	
	public static String className;
	public static String url;
	public static String user;
	public static String password;
	public static Connection connection;
	
	static{
		Properties properties = new Properties();
		try {
			properties.load(DButils.class.getResourceAsStream("db.properties"));
			className = properties.getProperty("driver.className");
			url = properties.getProperty("driver.url");
			user = properties.getProperty("driver.user");
			password = properties.getProperty("driver.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		try {
			Class.forName(className);
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(PreparedStatement pStatement){
		if(pStatement != null){
			try {
				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close();
	}
	
	public static void close(Statement statement){
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close();
	}
	
	public static void close(){
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
