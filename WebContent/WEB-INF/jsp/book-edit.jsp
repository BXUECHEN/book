<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="<%=application.getContextPath() %>/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>汇智动力教育--图书网后台管理系统</h1></div>
    	<div id="info">张三，您好！&nbsp;&nbsp;<a href="admin-user-login.html">登出</a></div>
    	<div class="menu">
    		<ul>
    			<li><a href="<%=application.getContextPath() %>/manager/home">首页</a></li>
    			<li><a href="<%=application.getContextPath() %>/manager/category-mgr">图书分类管理</a></li>
    			<li><a href="<%=application.getContextPath() %>/bookmanager/getall">图书管理</a></li>
    			<li><a href="#">购书订单管理</a></li>
    		</ul>	
    	</div>
    	<div id="main">
			<div class="section-left">    	
				<h2>编辑图书信息</h2>
				<form action="<%=application.getContextPath() %>/bookmanager/edit" method="post" enctype="multipart/form-data">
					<input type="hidden" name="BID" value="${book.BID }" />
<!-- 					<input type="hidden" name="bphotoOld" value="fzdxl.jpg" /> -->
					<p>图书书名：<input type="text" name="BTitle" value="${book.BTitle }"  /></p>
					<p>图书作者：<input type="text" name="BAuthor" value="${book.BAuthor }"  /></p>
					<p>图书分类：
						<select name="BCategoryID">		
							<c:forEach items="${categories }" var="cate">	
								<c:if test="${book.BCategoryID == cate.CID }">
									<option value="${cate.CID }" selected="selected">${cate.CName }</option>
								</c:if>					
								<c:if test="${not(book.BCategoryID == cate.CID) }">
									<option value="${cate.CID }">${cate.CName }</option>
								</c:if>	
							</c:forEach>	
						</select>
					</p>
					<p>图书售价：<input type="text" name="BPrice" value="${book.BPrice }" /></p>
					<p>图书出版社：<input type="text" name="BPublisher" value="${book.BPublisher }"  /></p>  
					<p>当前图片：<img width="150" height="90" id="img_photo" src="<%=application.getContextPath() %>/photo/${book.BPhoto }" /></p> 
					<p>图书图片：<input type="file" name="bPhoto" id="bphoto" /></p>    				 				
					<p><input type="submit" value=" 修 改 "  />&nbsp;</p>					
				</form>
			</div>
			<div class="section-right"></div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;汇智动力教育</p></div>
	</div>
	
		<script type="text/javascript">
		var bphoto = document.getElementById("bphoto");

		bphoto.onchange = function() {
			var file = this.files[0];
			if (window.FileReader) {
				var reader = new FileReader();
				reader.readAsDataURL(file);
				//选择文件之后
				reader.onloadend = function(e) {
					var img_photo = document.getElementById("img_photo");
					img_photo.setAttribute("src", e.target.result);
				}
			}
		}
	</script>
  </body>
</html>