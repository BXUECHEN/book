package com.hzdl.fliter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.hzdl.entity.Admin;

public class AdminLoginFliter implements Filter{

	String[] ignoreList;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		boolean flag = true;//默认拦截
		for (String uri : ignoreList) {
			if(req.getRequestURI().contains(uri)){
				flag = false;//不拦截
			}
		}
		if(flag){
			Admin admin = (Admin) req.getSession().getAttribute("Admin");
			if(admin == null){// 未登录转发到登录页面
				req.getRequestDispatcher("/admin/toLogin").forward(request, response);
			}
		}		
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//从web.xml读取忽略列表
		ignoreList = config.getInitParameter("ignorelist").split(",");
	}

}
