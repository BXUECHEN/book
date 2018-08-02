package com.hzdl.entity;

import javax.servlet.http.HttpServletRequest;

import com.hzdl.utils.Constants;

public class Page {
	
	private int pageNo;
	private int totalNo;
	private int preNo;
	private int nextNo;
	
	
	public Page(int total,HttpServletRequest req) {
		String p = req.getParameter("pageNo");
		if(p == null || p == ""){
			p = "1";
		}
		pageNo = Integer.parseInt(p);
		//尾页
		totalNo = (total%Constants.PAGE_SIZE == 0 ? total/Constants.PAGE_SIZE : total/Constants.PAGE_SIZE +1);
		//上一页
		preNo = (pageNo == 1 ? 1 : pageNo-1);
		//下一页
		nextNo = (pageNo == totalNo ? totalNo : pageNo+1);
		
		req.setAttribute("total",total);
		req.setAttribute("pageNo",pageNo);
		req.setAttribute("totalNo",totalNo);
		req.setAttribute("preNo",preNo);
		req.setAttribute("nextNo",nextNo);
	}
	
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalNo() {
		return totalNo;
	}
	public void setTotalNo(int totalNo) {
		this.totalNo = totalNo;
	}
	public int getPreNo() {
		return preNo;
	}
	public void setPreNo(int preNo) {
		this.preNo = preNo;
	}
	public int getNextNo() {
		return nextNo;
	}
	public void setNextNo(int nextNo) {
		this.nextNo = nextNo;
	}
	
	

}
