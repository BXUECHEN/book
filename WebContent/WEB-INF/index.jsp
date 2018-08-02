<%@page import="com.hzdl.entity.Category"%>
<%@page import="com.hzdl.entity.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汇智动力图书网</title>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath() %>/css/styles.css"/>
</head>
<body>
	<%request.setCharacterEncoding("utf-8");%>
	<div id="container">
    	<div id="header">
    		<div id="tool-bar">&nbsp;&nbsp; 欢迎光临汇智动力图书网，[<a href="<%=application.getContextPath() %>/books/toLogin">请登录</a>]&nbsp;[<a href="${path }/books/toRegist">免费注册</a>]&nbsp;&nbsp;<a href="<%=application.getContextPath() %>/books/allbooks">首页</a> | <a href="cart.html">购物车</a> | <a href="#">我的订单</a> | <a href="#">帮助</a></div>
    		<h1>汇智动力图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
    	</div>
    	<form id="search-bar" action="bookstore/books/allbooks" method="post">   		
    		书名：<input type="text" class="txt" name="condition" value="${condition }"/>
    		<input id="search-btn" type="submit" value=" 搜索图书 " />
    	</form>
    	<div id="main">
    		<div class="section-left">
    			<div class="box-left">
    				<div class="box-title">分类畅销榜</div>  				
    				<div class="box-content">
						<p>·<a href="<%=application.getContextPath() %>/books/allbooks" >全部</a></p>
						<%-- <%List<Category> categories = (List<Category>)request.getAttribute("categories"); %> --%>
    					<c:forEach items="${categories}" var="category">
    						<p>·<a href="<%=application.getContextPath() %>/books/allbooks?CID=${category.CID}">${category.CName }</a></p>
    					</c:forEach>
    				</div>  				
    			</div>
    		</div>
    		<div class="section-right">
    			<div class="box-right">
    				<div class="box-title">您目前浏览的图书【搜索条件——分类：全部；书名：无】</div>
    				<div class="paging" style="border-bottom: 1px solid  #64A26F; color: #006666; ">
    					 <span class="fr">
    					 	<a href="<%=application.getContextPath() %>/books/page?pageNo=1">首页</a>
    					 	<a href="<%=application.getContextPath() %>/books/page?pageNo=${preNo}">上一页</a>
    					 	<a href="<%=application.getContextPath() %>/books/page?pageNo=${nextNo}">下一页</a>
    					 	<a href="<%=application.getContextPath() %>/books/page?pageNo=${totalNo}">尾页</a>
    					 </span>
    					 共有图书${total }本书，分${totalNo }页显示，每页显示10个商品，当前页数为${pageNo}
    				</div>
    				<%	request.setCharacterEncoding("utf-8"); 			
    				List<Book> books2 = (List<Book>)request.getAttribute("searchbooks");    					
    				%>
    				<c:choose>
    				<c:when test="${empty param.condition }">
    					<c:forEach items="${books}" var="book">
    					<div class="box-item">
    						<div class="img-box"><img src="<%=application.getContextPath() %>/photo/${book.BPhoto}" /></div>
    							<div class="info-box">
    								<span style="font-size: 14px; "><a href="#">${book.BTitle}</a></span><br />
									作者：${book.BAuthor}&nbsp;&nbsp;著<br />									
									 分类：${book.categoryName}<br /> 
									出版社：${book.BPublisher}<br />							
									售价：￥<span style="color: #990000;">${book.BPrice}</span>		<br />					
    							</div>
    							<a href="cart.html" class="btn-buy">购&nbsp;&nbsp;买</a>    					
    						<div class="cf"></div>
    					</div>    
    					</c:forEach>	
    				</c:when>
    				<c:otherwise>
						<%for(Book book:books2){%>  					
   						<div class="box-item">
    						<div class="img-box"><img src="<%=application.getContextPath() %>/photo/<%=book.BPhoto %>" /></div>
    							<div class="info-box">
    								<span style="font-size: 14px; "><a href="#"><%=book.BTitle%></a></span><br />
									作者：<%=book.BAuthor%>&nbsp;&nbsp;著<br />
									分类：<%=book.getCategoryName()%><br />
									出版社：<%=book.BPublisher%><br />							
									售价：￥<span style="color: #990000;"><%=book.BPrice%></span>		<br />					
    							</div>
    							<a href="cart.html" class="btn-buy">购&nbsp;&nbsp;买</a>    					
    						<div class="cf"></div>
    					</div>    
    					<%} %>  
<%-- 						<c:forEach items="${books2 }" var="book">
    					<div class="box-item">
    						<div class="img-box"><img src="<%=application.getContextPath() %>/photo/${book.BPhoto}" /></div>
    							<div class="info-box">
    								<span style="font-size: 14px; "><a href="#">${book.BTitle}</a></span><br />
									作者：${book.BAuthor}&nbsp;&nbsp;著<br />
									分类：${book.categoryName}<br />
									出版社：${book.BPublisher}<br />							
									售价：￥<span style="color: #990000;">${book.BPrice}</span>		<br />					
    							</div>
    							<a href="cart.html" class="btn-buy">购&nbsp;&nbsp;买</a>    					
    						<div class="cf"></div>
    					</div>    
    					</c:forEach>   --%>
    				</c:otherwise>
    				</c:choose>			
    				<div class="paging">
    					 <span class="fr">
							<a href="<%=application.getContextPath() %>/books/page?pageNo=1">首页</a>
    					 	<a href="<%=application.getContextPath() %>/books/page?pageNo=${preNo}">上一页</a>
    					 	<a href="<%=application.getContextPath() %>/books/page?pageNo=${nextNo}">下一页</a>
    					 	<a href="<%=application.getContextPath() %>/books/page?pageNo=${totalNo}">尾页</a>
    					 </span>    					
    				</div>
    			</div>
    		</div>
    		<div class="cf"></div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;汇智动力教育</p></div>
	</div>
</body>
</html>